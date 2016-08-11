<%--
  Created by IntelliJ IDEA.
  User: Denis Lutsenko
  Date: 6/9/2016
  Time: 8:44 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${not empty param.language ? param.language : sessionScope.lang}" scope="session"/>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>

<html>
<head>
    <title><fmt:message key="ERROR"/></title>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>

<H3 align="center"><fmt:message key="ERR_MSG"/></H3>

</body>
</html>
