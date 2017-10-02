<%@ page pageEncoding = "utf-8" import = "java.util.*" %>
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://pingendo.com/assets/bootstrap/bootstrap-4.0.0-beta.1.css" type="text/css"> </head>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/book.css">
  <script type = "text/javascript" src = "<%=request.getContextPath()%>/paraCheck.js"></script>
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

   
<h3 align = "center">图书信息</h3>

<table align = "center" border = "3" width = "50%" >
    <tr><th>ISBN</th><th>书名</th><th>作者ID</th><th>出版社</th><th>出版日期</th><th>价格</th><th>管理</th></tr>
    
    <%
    List booksInfo = (List) session.getAttribute("books");
    if(booksInfo != null) {
    	for(Object o: booksInfo) {
    		Map m = (HashMap) o;
    %>

    <tr><td><%= m.get("id") %></td>
        <td><a href = "getBook.action?id=<%= m.get("id")%>"><%= m.get("bookname") %></a></td>
        <td><%= m.get("author") %></td>
        <td><%= m.get("press") %></td>
        <td><%= m.get("pubdate") %></td>
        <td><%= m.get("price") %></td>
        <td>
            <a href = "getBook.action?id=<%= m.get("id")%>">修改</a>&nbsp;
			<a href = "delBook.action?id=<%= m.get("id")%>" onclick = "return confirm('确定要删除吗?')">删除</a>
        </td>
    </tr>
    

    <%
    	}
       }
     %>

</table>
</body>
</html>