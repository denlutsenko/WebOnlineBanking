<%--
  Created by IntelliJ IDEA.
  User: Denis Lutsenko
  Date: 5/15/2016
  Time: 1:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/layout/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>
<div align="right">
    <form action="<c:url value="/welcomePage"/>" method="post">
        <input type="hidden" name="ok" value="logOut"/>
        <input type="submit"  class="btn btn-success" value=<fmt:message key="BTN_LOG_OUT"/>>
    </form>
</div>

<ul class="headerMenu">
    <li>
        <form action="<c:url value="/bank24/blockedAccounts"/>" method="post">
            <input type="hidden" name="ok" value="blockedAccounts"/>
            <input type="submit"  class="btn btn-default btn-lg active"  value=<fmt:message key="BTN_BLOCKEDLIST"/>>
        </form>
    </li>

    <li>
        <form action="<c:url value="/bank24/newApplications"/>" method="post">
            <input type="hidden" name="ok" value="applicationsList"/>
            <input type="submit"  class="btn btn-default btn-lg active"  value=<fmt:message key="BTN_QUERY_FOR_NEW_ACCOUNT"/>>
        </form>
    </li>

</ul>
</body>
</html>
