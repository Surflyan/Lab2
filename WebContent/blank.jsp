<%@ page pageEncoding = "utf-8" import = "java.util.*" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<title>STATUS</title>
</head>
<body>
<% 
String isExists = (String) session.getAttribute("isExists");
if (isExists !=null && isExists.equals("true")) {
%>
<script type="text/javascript" language="javascript">
	alert("此书已存在");                                            // 弹出错误信息
	window.location="<%=request.getContextPath()%>/index.jsp" ;                            // 跳转到登录界面
</script> 

<%
}
else {
%>

<script type="text/javascript" language="javascript">
	alert("没有查到书籍信息");                                            
	window.location="<%=request.getContextPath()%>/index.jsp" ;
</script> 
<%
}
%>


</body>
</html>