<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8"/>
    <title>聊天室登录页面</title>
</head>
<body>
<div>
    <c:if test="${param.error}">
        无效的账号和密码
    </c:if>
</div>
<div>
    <c:if test="${param.logout}">
        您已注销
    </c:if>
</div>

<form action="/login" method="post" met>
    <div><label>账号：<input type="text" name="username"/></label></div>
    <div><label>密码：<input type="password" name="password"/></label></div>
    <div><input type="submit" value="登录"/></div>
</form>

</body>
</html>