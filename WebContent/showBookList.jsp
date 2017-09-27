<%@ page pageEncoding = "utf-8" import = "java.util.*" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/book.css">
<script type = "text/javascript" src = "<%=request.getContextPath()%>/paraCheck.js"></script>
<title>图书信息</title>
</head>

<body>

   
<p align = "center">图书信息</a></p>

<table align = "center" border = "3" width = "50%" >
    <tr><th>ISBN</th><th>书名</th><th>作者</th><th>出版社</th><th>出版日期</th><th>价格</th><th>管理</th></tr>
    
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