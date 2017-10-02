<%@ page pageEncoding = "utf-8" import = "java.util.*" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<link rel = "stylesheet" href = "<%=request.getContextPath()%>/book.css">

<title>添加作者信息</title>
</head>

<body>
<h3 align = "center">添加作者信息</h3>

<form name = "author"  action = "book/addAuthor"  method = "post">
    
	<table align = "center" width = "30%" border = "1" >
   	 <tr><th>作者ID: </th>
        <td><%= session.getAttribute("authorid")%></td></tr>
     <tr><th>姓名：</th>
        <td><input type = "text" name = "name" required = "required"></td></tr>
   	 <tr><th width = "30%">年龄：</th>
        <td><input type = "text" name = "age" pattern = "[0-9]*" title = "年龄必须为数值！"></td></tr>
   	 <tr><th>国籍：</th>
        <td><input type = "text" name = "country"></td></tr>
   	 <tr><th colspan = "2">
            <input type = "submit" value = "添加">
            <input type = "reset" value = "重置"></th></tr>
	</table>
</form>
</body>
</html>

