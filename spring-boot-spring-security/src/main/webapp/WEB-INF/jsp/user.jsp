<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>


<!DOCTYPE html>
<html>
<title>首页</title>
<head>

</head>

<body>

<div>
    <h1>USER, ADMIN 角色页面</h1>
    <sec:authorize access="hasAuthority('ADMIN')">
        <p>只有 ADMIN 角色可看</p>
    </sec:authorize>

    <sec:authorize access="hasAuthority('USER')">
        <p>只有 USER 角色可看</p>
    </sec:authorize>
</div>

</body>
</html>