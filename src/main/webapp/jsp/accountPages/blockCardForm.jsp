<%--
  Created by IntelliJ IDEA.
  User: Denis Lutsenko
  Date: 5/24/2016
  Time: 12:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/layout/userMenu.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title></title>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>

<form action="<c:url value="/bank24/managements"/>" method="post">
    <fmt:message key="ENTER_CARD_NUMBER"/>
    <input type="text" name="cardNumber" required  oninvalid="this.setCustomValidity(<fmt:message key="HINT_WRITE_CARD"/>)" oninput="setCustomValidity('')" pattern="[\d]{16}" value=""/>
    <input type="hidden" name="ok" value="confirmLocking"/>
    <input type="submit" name="ok" value=<fmt:message key="LOCK_ACCOUNT"/>>
</form>

<table class="table table-striped">

    <tr>
        <td><fmt:message key="ACC_TYPE"/></td>
        <td><fmt:message key="ACCOUNT_NUMBER"/></td>
        <td><fmt:message key="CURRENT_BALANCE"/></td>
        <td><fmt:message key="CURRENCY"/></td>
        <td><fmt:message key="ACC_DEADLINE"/></td>
    </tr>
    <br/>

    <c:forEach items="${requestScope.activeAccounts}" var="accountElem">
        <tr>
            <form action="handler" method="post">

                <td>
                        ${accountElem.type}
                </td>
                <td>
                        ${accountElem.account.accountCode}
                </td>
                <td>
                        ${accountElem.account.currentBalance}
                </td>
                <td>
                        ${accountElem.account.currency}
                </td>
                <td>
                        ${accountElem.deadLine}
                </td>
            </form>
        </tr>

    </c:forEach>
</table
</body>
</html>