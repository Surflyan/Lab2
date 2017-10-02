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
  
    <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h1 class="text-center display-3 text-primary">About</h1>
          
          <h3>功能介绍</h3>
          <ol>
             <li>通过书名查询书籍</li>
             <li>通过作者查询书籍</li>
             <li>点击图书题目获取书籍及作者详细信息</li>
             <li>添加书籍信息</li>
             <li>删除书籍信息</li>
             <li>更新书籍信息</li></ol>
             
          <h3>使用介绍</h3>
             <ol>
               <li>添加书籍ISBN、题目、作者ID、价格为必填项</li>
               <li>ISBN 唯一，添加时做了重复检测</li>
               <li>ISBN、作者ID、出版日期、价格做了格式检测</li>
               <li>添加书籍时，若作者，不在作者库中，则要求增加作者信息</li>
               <li>删除书籍时，不删除作者库信息</li>
             </ol>
             
          <h3>实现介绍</h3>
          <p class="">本图书管理系统通过后台Java + Tomcat + Struts + Mysql 实现，前端由 Bootstrap 实现。未做手机适配。</p>
          <p class= ""> 由于水平、时间有限，本系统难免有bug。如遇到问题，欢迎反馈，不甚感激。 QQ:1790328095
        </div>
      </div>
    </div>
  </div>
  
</body>

</html>
  
  