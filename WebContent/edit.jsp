<%@ page pageEncoding = "utf-8" import = "java.util.*" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/book.css">
<script type = "text/javascript" src = "<%=request.getContextPath()%>/paraCheck.js"></script>
<title>图书信息</title>
</head>

<body>

<%
	HashMap bookInfo = (HashMap) session.getAttribute("book");
    if(bookInfo != null) {
 %>
    <h2 align = "center">图书信息</h2>
    
    <form name = "book" onSubmit = "return check()" action = "book/update"  method = "post">
    <table align = "center" width = "30%" border = "1" >
    <tr><th>ISBN: </th>
        <!-- ISBN 不可编辑，隐藏传值 -->
        <input type = "hidden" name = "id" value = "<%= bookInfo.get("id")%>">
        <td><%= bookInfo.get("id")%></td></tr>
    <tr><th width = "30%">书名：</th>
        <td><input type = "text" name = "bookname" value = "<%= bookInfo.get("bookname")%>"></td></tr>
    <tr><th>作者：</th>
        <td><input type = "text" name = "author" value = "<%= bookInfo.get("author")%>"></td></tr>
    <tr><th>出版社：</th>
        <td><input type = "text" name = "press" value = "<%= bookInfo.get("press")%>"></td></tr>
    <tr><th>出版日期：</th>
    	<td><input type = "text" name = "pubdate" value = "<%= bookInfo.get("pubdate")%>" placeholder="1970-01-01"></td></tr>
    <tr><th>价格： </th>
        <td><input type = "text" name = "price" value = "<%= bookInfo.get("price")%>"></td></tr>
    <tr><th colspan = "3">
            <input type = "submit" value = "修改">
            <input type = "reset" value = "重置">
            
            
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
    <h2 align = "center">作者信息</h2>
    
    <table align = "center" width = "30%" border = "1" >
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
    
