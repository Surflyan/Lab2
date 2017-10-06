package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil {

	private String driver;
	private String username;
	private String url;
	private String password;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public DBUtil() {
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost:3306/book?autoReconnect=true&useSSL=false";
		username = "root";
		password = "mypassword";
		
	}
	
	//获取连接对象
	private Connection getConnection() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,username,password);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	

	
	//给pstmt 的SQL语句设置参数（参数以数组形式给出）
	
	private void setParams(String sql, String[] params) {
		
		this.getConnection();
		try {
			//创建一个PreparedStatement对象，用于执行预编译的SQL语句
			pstmt = con.prepareStatement(sql);
			
			for (int i = 0; i<params.length; i++)
				 pstmt.setString(i+1, params[i]);
		}catch(SQLException e) {
			e.printStackTrace();
		}

		
	}
	
	//执行数据库查询操作时，将返回的结果封装到List对象中

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getResultList(String sql, String[] params) {
		List list = new ArrayList();
		try {
			this.setParams(sql, params);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()) {
				Map m = new HashMap();
				// i =1;
				for (int i = 1; i<=rsmd.getColumnCount(); i++) {
					String colName = rsmd.getColumnName(i);
					m.put(colName, rs.getString(colName));
				}
				list.add(m);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
	
	//查询唯一性结果
	@SuppressWarnings("rawtypes")
	public Map getMap(String sql, String[] params) {
		List list = getResultList(sql, params);
		if(list.isEmpty()) return null;
		else  return (Map)list.get(0);
	}
	
	//更新数据库时调用的update 方法
	public int update (String sql, String[] params) {
		int recNo = 0;          //表示受影响的记录行数
		try {
			this.setParams(sql, params);         //根据sql 和 params ，设置 pstmt 对象
			recNo = pstmt.executeUpdate();       // 执行更新操作
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return recNo;
	}
	

	
	//关闭对象
	private void close() {
		try {
			if(rs!= null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
			if(con != null)
				con.close();
		}catch(SQLException e){
		
	}
	
	
	}	

}
