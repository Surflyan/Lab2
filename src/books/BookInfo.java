
/*
 * ͼ�����ϵͳ
 * ��ISBN(id) ��Ϊ�鼮bookinfo ����������authorid��Ϊ���߱����������authorid �洢��ʵΪISBN��ֵ���������ӣ�����������ϢISBN��ֵ��û��ʹ�ö�����ӣ�ʹ���Ӳ�ѯ��
 * ����һ�����ʱ�򣬼�������Ƿ���ڣ��粻���ڣ�����ת���������ҳ�棬�����������ɸ��ģ�����ڣ���������Ϣauthorid ��Ϊ�����ISBN��������Ϣ���Ѵ�����Ϣ��ͬ���ٴ������߱���̨��ɡ�
 * ���˲�����飬ISBN�����ظ������ظ��򵯳�ISBN ���ظ���price isbn ���˼�飬���������
 */


package books;

import java.util.List;
import java.util.Map;
import util.DBUtil;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//TO-DO isbn ����
//TO-DO �����ʽ��飬������
public class BookInfo<session> extends ActionSupport{

	private String id;
	private String bookname;
	private String author;
	private String press;
	private String pubdate;
	private String price;
	private DBUtil db;
	

	private String authorid;
	private String name;
	private String age;
	private String country;
	
	
	//���캯��
	public BookInfo() {
		db = new DBUtil();
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getPubdate() {
		return pubdate;
	}
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

	

	
	public String getAuthorid() {
		return authorid;
	}


	public void setAuthorid(String authorid) {
		this.authorid = authorid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}
	

	
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		
		List books = null;
		String sql = "select * from bookinfo";
		String[] params = {};
        
		books = db.getResultList(sql,params);
		
		@SuppressWarnings("rawtypes")
		Map session = (Map)ActionContext.getContext().getSession();
		session.put("books", books);
		
		return "LIST";
	}
	
	
	//��������Ϣ���Ҽ���������ߣ������߷���"NOAUTHOR",����������Ӷ�Ӧ��authorid��Ϊ���id���ٲ���һ��������Ϣ������"SUCCESS";
	public String addBook() {
		String sql = "insert into bookinfo values(?,?,?,?,?,?)";
		String[] params = {id,bookname, author, press, pubdate,price};
		int recNo = db.update(sql, params);
		
		
		Map authorInfo = this.getAuthorInfo();
		if(authorInfo == null)
		{   Map session = (Map)ActionContext.getContext().getSession();
		    session.put("authorName",author);
		    session.put("authorid", id);
			return "NOAUTHOR";
		}
		
		
		else {
			this.age =(String)authorInfo.get("age");
			this.country =(String)authorInfo.get("country");
			this.addAuthorHadExist();
		}
		return "ADDSUCCESS";
	}
	
	//��������ݿ����߿��д��ڵ����ߣ�ֻ��authorid ��ͬ��
	public String addAuthorHadExist() {
		authorid = id;
		String sql = "insert into author_t values(?,?,?,?)";
		String[] params = {authorid,author, age, country};
		int recNo = db.update(sql, params);
		
		return "SUCCESS";
	}
	
	
	
	//��ӱ��������ݿ���������Ϣ
	public String addAuthor() {
		Map session = (Map)ActionContext.getContext().getSession();
		authorid = (String) session.get("authorid");
		String authorName = (String)session.get("authorName");
		
		String sql = "insert into author_t values(?,?,?,?)";
		String[] params = {authorid,authorName, age, country};
		int recNo = db.update(sql, params);
		
		return "ADDSUCCESS";
	}

	
	//�������ݿ� ��ı�
	public String update() {
		String sql = "update bookinfo set bookname=?,author=?,"
				+"press=?,pubdate=?,price=? where id=?";
		String[] params = {bookname, author, press, pubdate,price,id};
		int recNo = db.update(sql, params);

		return "UPDATESUCCESS";
	}
	
	public String delBook() {
		String sql = "delete from bookinfo where id = ?";
		String[] params = {id};
		int recNo = db.update(sql, params);
		
		this.delAuthor();
		return "DELSUCCESS";
	}
	//ɾ�����ߣ���ɾ���鼮ʱ��ͬʱɾ����Ӧ�鼮�����߼�¼�� ֻɾ�����ߣ���˺�����������������
	public String delAuthor() {
		String sql = "delete from author_t where authorid = ?";
		authorid = id;
		String[] params = {authorid};
		int recNo = db.update(sql, params);
		
		return "SUCCESS";
				
	}
	
	//ͨ�� ISBN �� id ��ѯ�鼮��ͬʱȡ�������������Ϣ������session
	public String getBook() {
		Map book = null;
		Map authorInfo = null;
		String sql = "select * from bookinfo where id = ?";
		String[] params = {id};
		book = db.getMap(sql, params);
		
		String s = "select * from author_t where authorid = ?";
		String[] p = {id};
		authorInfo= db.getMap(s, p);
		
		
		
		// getAuthor
		@SuppressWarnings("rawtypes")
		Map session = (Map)ActionContext.getContext().getSession();
		
		session.put("book", book);
		session.put("authorInfo", authorInfo);
		
		return "GETSUCCESS";
	}
	
	// ͨ��������ѯ��Ϣ��ͬʱȡ������������Ϣ������session
	public String getBookByName() {
		Map book = null;
		Map authorInfo = null;
		String sql = "select * from bookinfo where bookname = ?";
		String[] params = {bookname};
		book = db.getMap(sql, params);
		
		if (book == null)
			return "FAILURE";
		
		id = (String) book.get("id");
		String s = "select * from author_t where authorid = ?";
		String[] p = {id};
		authorInfo= db.getMap(s, p);	
		
		// getAuthor
		@SuppressWarnings("rawtypes")
		Map session = (Map)ActionContext.getContext().getSession();
		
		session.put("book", book);
		session.put("authorInfo", authorInfo);
		
		return "SUCCESS";
	}
	
	// ͨ�����߲�ѯ�����������������鼮
	public String getBookByAuthor() {
		List books = null;
		String sql = "select * from bookinfo where author = ?";
		String[] params = {author};
		books = db.getResultList(sql, params);
		if (books.isEmpty())
			return "FAILURE";
		else
		{
			@SuppressWarnings("rawtypes")
			Map session = (Map)ActionContext.getContext().getSession();
			
			session.put("books", books);
		}
		return "SUCCESS";
			
	}
	public Map getAuthorInfo() {
		
		String s = "select * from author_t where name = ?";
		String[] p = {author};
		Map authorInfo = db.getMap(s, p);
	    
		return authorInfo;
		
	}

}
