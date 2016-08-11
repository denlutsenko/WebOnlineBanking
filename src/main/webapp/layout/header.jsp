<%--
  Created by IntelliJ IDEA.
  User: Denis Lutsenko
  Date: 6/9/2016
  Time: 11:04 AM
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
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>

<c:if test="${empty requestScope.msg}">
    <c:set var="msg" value="DEFAULT" scope="request"/>
</c:if>

<div align="right">
    <a href="?language=ru_RU"><fmt:message key="RUS"/></a>
    <a href="?language=en_US"><fmt:message key="ENG"/></a>
</div>
</body>
</html>
