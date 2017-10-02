
/*
 * 图书管理系统
 * 以ISBN(id) 作为书籍bookinfo 的主键，以authorid作为作者表的主键，两表连接，依靠共有信息 authorid的值，没有使用多表连接，使用子查询。
 * 插入一本书后，根据author 检查作者是否存在，如不存在，则跳转到添加作者页面，作者authorid不可更改；如存在,不做更改. 作者信息只插一次。
 * 删除书籍，只删除 bookinfo 表里的信息，作者信息不做处理。
 * 做了参数检查，ISBN不可重复，如重复则弹出ISBN 已重复。price isbn pubdate做了检查.
 * 可查找同名不同人操作,列出所有此名下的书籍信息.
 */


//TO-DO isbn 查重 ，返回错误界面。 throw 出错误
//TO-DO 增加 author id
//edit 界面缺少删除功能

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
	
	
	//构造函数
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
	
	
	//添加书的信息并且检查根据authorid有无作者，无作者返回"NOAUTHOR",作者存在,返回ADDSUCCESS;
	public String addBook() {
		
		Map session = (Map)ActionContext.getContext().getSession();
		
		// ISBN 查重
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
	
	
	//添加作者信息 在addBook 调用，独立不可使用
	public String addAuthor() {
		Map session = (Map)ActionContext.getContext().getSession();
		authorid = (String) session.get("authorid");
		
		String sql = "insert into author_t values(?,?,?,?)";
		String[] params = {authorid,name, age, country};
		int recNo = db.update(sql, params);
		
		return "ADDSUCCESS";
	}

	
	//更新数据库 书的表
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
	
	//通过 ISBN 即 id 查询书籍，同时根据authorid取出该书的作者信息，放入session
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
	
	// 通过书名查询信息，同时取出该书作者信息，放入session
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
	
	// 通过作者查询该作者所著的所有书籍,可同名不同作者查找
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
