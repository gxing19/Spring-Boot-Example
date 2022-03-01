<%--
  Created by IntelliJ IDEA.
  User: gxing
  Date: 2018/9/26
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>


<div>
    <form action="/doLogin" method="post">
        <label>用户名: <input type="text" name="username" placeholder="user1"></label>
        <label>密 码: <input type="password" name="password" placeholder="123456"></label>
        <input type="submit" value="登录">
    </form>
</div>


</body>
</html>
