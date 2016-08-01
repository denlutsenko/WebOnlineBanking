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
    <link href=" <c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>

<ul class="headerMenu">
    <li>
        <form action="<c:url value="/bank24/allActiveAccounts"/>" method="post">
            <input type="hidden" name="ok" value="userAccounts"/>
            <input type="submit" class="btn btn-default btn-lg active" value=<fmt:message key="BTN_MY_ACCOUNTS"/>>
        </form>
    </li>
    <li>
        <form action="<c:url value="/bank24/currentOperationsHistory"/>" method="post">
            <input type="hidden" name="ok" value="userOperations"/>
            <input type="submit" class="btn btn-default btn-lg active" value=<fmt:message
                    key="BTN_OPERATION_BY_CARDS"/>>
        </form>
    </li>
    <li>
        <form action="<c:url value="/bank24/currentOperationsHistory"/>" method="post">
            <input type="hidden" name="ok" value="userPayments"/>
            <input type="submit" class="btn btn-default btn-lg active" value=<fmt:message key="BTN_MY_PAYMENTS"/>>
        </form>
    </li>
    <li>
        <form action="<c:url value="/bank24/managements"/>" method="post">
            <input type="hidden" name="ok" value="userManagement"/>
            <input type="submit" class="btn btn-default btn-lg active" value=<fmt:message
                    key="BTN_ACCOUNT_MANAGEMENTS"/>>
        </form>
    </li>
</ul>
</body>
</html>
