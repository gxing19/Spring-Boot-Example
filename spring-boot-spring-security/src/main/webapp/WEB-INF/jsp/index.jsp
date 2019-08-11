<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<title>首页</title>
<head>

</head>

<body>

<div>
    <h1>欢迎来到首页:
        <%--从authentication中取值--%>
        <sec:authentication property="principal.username"/>

        <%--从model中取值--%>
        ${username}
    </h1>

    <a href="/admin">管理页面</a><br>
    <a href="/user">USER页面</a><br>
    <a href="/logout">退出登录</a>

    <sec:authorize url="/admin">
        <p>有权向 /admin 路径发送请求才可显示</p>
    </sec:authorize>
</div>

</body>
</html>