<%@ page pageEncoding = "utf-8" import = "java.util.*" %>
<html>
<!-- 修改，删除居中出现bug, bootstrap css和book.css 出现冲突 -->

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://pingendo.com/assets/bootstrap/bootstrap-4.0.0-beta.1.css" type="text/css"> </head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/book.css">
  <title>图书信息</title>
  
<body>
  <nav class="navbar navbar-expand-md bg-secondary navbar-dark">
    <div class="container">
      <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp">图书管理系统</a>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"> <span class="navbar-toggler-icon"></span> </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/add.html">添加图书</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/book/BookInfo.action">列出图书</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/about.jsp">About</a>
          </li>
        </ul>
        <form class="form-inline m-0" name = "query" action = "book/getBookByName" method = "post">
          <input class="form-control mr-2" type="text" name = "bookname" placeholder="书名查询">
          <button class="btn btn-primary" type="submit">Search</button>
        </form>
        <form class="form-inline m-0" name = "query" action = "book/getBookByAuthor" method = "post">
          <input class="form-control mr-2" type="text" name = "name" placeholder="作者查询">
          <button class="btn btn-primary" type="submit">Search</button>
        </form>
      </div>
    </div>
  </nav>
<br>
<%
	HashMap bookInfo = (HashMap) session.getAttribute("book");
    if(bookInfo != null) {
 %> 
    <h3 align = "center">图书信息 </h3>
    <br>
    <form name = "book"  action = "book/update"  method = "post">
    <table align = "center" width = "30%" border = "1" >
    <tr><th>ISBN: </th>
        <!-- ISBN 不可编辑，隐藏传值 -->
        <input type = "hidden" name = "id" value = "<%= bookInfo.get("id")%>">
        <td><%= bookInfo.get("id")%></td></tr>
    <tr><th width = "30%">书名：</th>
        <td><input type = "text" name = "bookname" value = "<%= bookInfo.get("bookname")%>" required = "required"  ></td></tr>
    <tr><th>作者ID：</th>
        <td><%= bookInfo.get("author")%></td></tr>
    <tr><th>出版社：</th>
        <td><input type = "text" name = "press" value = "<%= bookInfo.get("press")%>"></td></tr>
    <tr><th>出版日期：</th>
    	<td><input type = "text" name = "pubdate" value = "<%= bookInfo.get("pubdate")%>" pattern = "[0-9]{4}-[01]?[0-9]-[0-3]?[0-9]" title = "1970-01-01"  placeholder="1970-01-01"></td></tr>
    <tr><th>价格： </th>
        <td><input type = "text" name = "price" value = "<%= bookInfo.get("price")%>" pattern = "[0-9]*\.?[0-9]{0,2}" required = "required" title = "价格错误！" ></td></tr>
    <tr><th colspan = "3">
            <input type = "submit" value = "修改">
            <input type = "reset" value = "重置"></th></tr>
            
            
    </table>
    </form>
  <%
    }
    %>
    
    <br>
    <br>
          
<%    
    HashMap author = (HashMap) session.getAttribute("authorInfo");
    if(author != null) { 	
 %>
    <h3 align = "center">作者信息</h3>
    <br>
    <table align = "center" width = "30%" border = "1" >
        <tr><th>作者ID：</th>
       		 <td><%= author.get("authorid")%></td></tr>
    	<tr><th width = "30%">名字: </th>
       		 <td><%= author.get("name")%></td></tr>
   	 	<tr><th>年龄：</th>
       		 <td><%= author.get("age")%></td></tr>
   		 <tr><th>国籍：</th>
       		 <td><%= author.get("country")%></td></tr>    	
        
    </table>
  <%
    }
    %>
</body>
</html>
    