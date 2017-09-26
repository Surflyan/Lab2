<%@ page pageEncoding = "utf-8" import = "java.util.*" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<link rel = "stylesheet" href = "../book.css">
<title>添加作者信息</title>
</head>

<body>
<h2 align = "center">添加作者信息</h2>

<form name = "addAuthor" action = "book/addAuthor"  method = "post">
    <% String authorid = (String) session.getAttribute("authorid");%>
    <!--  <input type = hidden name = "authorid" value = authorid> -->
    
	<table align = "center" width = "30%" border = "1" >
   	 <tr><th>名字: </th>
        <td><%= session.getAttribute("authorName")%></td></tr>
   	 <tr><th width = "30%">年龄：</th>
        <td><input type = "text" name = "age"></td></tr>
   	 <tr><th>国籍：</th>
        <td><input type = "text" name = "country"></td></tr>
   	 <tr><th colspan = "2">
            <input type = "submit" value = "添加">
            <input type = "reset" value = "重置"></th></tr>
</table>
</form>
</body>
</html>

