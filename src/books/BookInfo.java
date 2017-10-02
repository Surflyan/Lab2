
/*
 * ͼ�����ϵͳ
 * ��ISBN(id) ��Ϊ�鼮bookinfo ����������authorid��Ϊ���߱���������������ӣ�����������Ϣ authorid��ֵ��û��ʹ�ö�����ӣ�ʹ���Ӳ�ѯ��
 * ����һ����󣬸���author ��������Ƿ���ڣ��粻���ڣ�����ת���������ҳ�棬����authorid���ɸ��ģ������,��������. ������Ϣֻ��һ�Ρ�
 * ɾ���鼮��ֻɾ�� bookinfo �������Ϣ��������Ϣ��������
 * ���˲�����飬ISBN�����ظ������ظ��򵯳�ISBN ���ظ���price isbn pubdate���˼��.
 * �ɲ���ͬ����ͬ�˲���,�г����д����µ��鼮��Ϣ.
 */


//TO-DO isbn ���� �����ش�����档 throw ������
//TO-DO ���� author id
//edit ����ȱ��ɾ������

package books;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.DBUtil;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class BookInfo<session> extends ActionSupport{

	private String id;
	private String bookname;
	private String author;        //author_id
	private String press;
	private String pubdate;
	private String price;
	private DBUtil db;
	

	private String authorid;
	private String name;         //author_name
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
	
	
	//��������Ϣ���Ҽ�����authorid�������ߣ������߷���"NOAUTHOR",���ߴ���,����ADDSUCCESS;
	public String addBook() {
		
		Map session = (Map)ActionContext.getContext().getSession();
		
		// ISBN ����
		String s = "select * from bookinfo where id =?";
		String[] p = {id};
		Map bookInfo = db.getMap(s, p);
		if (bookInfo != null) {
			session.put("isExists", "true");
			return "BOOKEXISTS";
		}
		
		
		String sql = "insert into bookinfo values(?,?,?,?,?,?)";
		String[] params = {id,bookname, author, press, pubdate,price};
		int recNo = db.update(sql, params);
		
		
		Map authorInfo = this.getAuthorInfo();
		if(authorInfo == null)
		{   
		    session.put("authorid", author);
			return "NOAUTHOR";
		}
		
		else
			return "ADDSUCCESS";
	}
	
	
	//���������Ϣ ��addBook ���ã���������ʹ��
	public String addAuthor() {
		Map session = (Map)ActionContext.getContext().getSession();
		authorid = (String) session.get("authorid");
		
		String sql = "insert into author_t values(?,?,?,?)";
		String[] params = {authorid,name, age, country};
		int recNo = db.update(sql, params);
		
		return "ADDSUCCESS";
	}

	
	//�������ݿ� ��ı�
	public String update() {
		String sql = "update bookinfo set bookname=?,"
				+"press=?,pubdate=?,price=? where id=?";
		String[] params = {bookname, press, pubdate,price,id};
		int recNo = db.update(sql, params);

		return "UPDATESUCCESS";
	}
	
	public String delBook() {

		
		String s = "delete from bookinfo where id = ?";
		String[] p = {id};
		int recNo = db.update(s, p);
		return "DELSUCCESS";
	}
	
	//ͨ�� ISBN �� id ��ѯ�鼮��ͬʱ����authoridȡ�������������Ϣ������session
	public String getBook() {
		Map book = null;
		Map authorInfo = null;
		String sql = "select * from bookinfo where id = ?";
		String[] params = {id};
		book = db.getMap(sql, params);
		
		authorid = (String) book.get("author");
		
		String s = "select * from author_t where authorid = ?";
		String[] p = {authorid};
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
			return "NOBOOK";
		
		authorid = (String) book.get("author");
		String s = "select * from author_t where authorid = ?";
		String[] p = {authorid};
		authorInfo= db.getMap(s, p);	
		
		// getAuthor
		@SuppressWarnings("rawtypes")
		Map session = (Map)ActionContext.getContext().getSession();
		
		session.put("book", book);
		session.put("authorInfo", authorInfo);
		
		return "SUCCESS";
	}
	
	// ͨ�����߲�ѯ�����������������鼮,��ͬ����ͬ���߲���
	public String getBookByAuthor() {
		List books = new ArrayList();
		List authorInfo = null;
		
		String s = "select * from author_t where name = ?";
		String[] p = {name};
		authorInfo = db.getResultList(s, p);
		if (authorInfo == null) {
			return "NOBOOK";
		}
		for (Object o : authorInfo) {
			List temp;
			Map m = (HashMap) o;
			author = (String) m.get("authorid");
			
			String sql = "select * from bookinfo where author = ?";
			String[] params = {author};
			temp = db.getResultList(sql, params);
			books.addAll(temp);
				
		}
		
	
		if (books.isEmpty())
			return "NOBOOK";
		else
		{
			@SuppressWarnings("rawtypes")
			Map session = (Map)ActionContext.getContext().getSession();
			
			session.put("books", books);
		}
		return "SUCCESS";
			
	}
	
	
	public Map getAuthorInfo() {
		
		String s = "select * from author_t where authorid = ?";
		String[] p = {author};
		Map authorInfo = db.getMap(s, p);
	    
		return authorInfo;
		
	}

}
