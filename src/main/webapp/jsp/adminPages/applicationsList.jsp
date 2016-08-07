<%--
  Created by IntelliJ IDEA.
  User: Denis Lutsenko
  Date: 6/6/2016
  Time: 3:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/layout/adminMenu.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title></title>
</head>
<body>

<table border="1">
    <tr>
        <td><fmt:message key="ID"/></td>
        <td><fmt:message key="FLP"/></td>
        <td><fmt:message key="ACC_TYPE"/></td>
        <td><fmt:message key="BALANCE"/></td>
        <td><fmt:message key="CURRENCY"/></td>
        <td><fmt:message key="DATE_OF_QUERY"/></td>
        <td><fmt:message key="ACTION"/></td>
    </tr>


    <c:forEach items="${requestScope.applicationList}" var="applicationElem">
        <tr>
            <td>
                    ${applicationElem.id}
            </td>
            <td>${applicationElem.user.lastName} ${applicationElem.user.firstName} ${applicationElem.user.middleName}</td>
            <td>${applicationElem.type}</td>
            <td>${applicationElem.balance}</td>
            <td>${applicationElem.currency}</td>
            <td>${applicationElem.date}</td>
            <td>
                <form action="<c:url value="OnlineBanking24"/>" method="post">
                    <input type="hidden" name="applicationId" value="${applicationElem.id}">
                    <input type="hidden" name="userId" value="${applicationElem.user.id}">
                    <input type="hidden" name="type" value="${applicationElem.type}">
                    <input type="hidden" name="balance" value="${applicationElem.balance}">
                    <input type="hidden" name="currency" value="${applicationElem.currency}">

                    <input type="hidden" name="ok" value="createAccount"/>
                    <input type="submit" name="ok" value=<fmt:message key="APPROVE_QUERY"/>>
                </form>
                <form action="<c:url value="OnlineBanking24"/>" method="post">
                    <input type="hidden" name="applicationId" value="${applicationElem.id}">
                    <input type="hidden" name="ok" value="declineApplication"/>
                    <input type="submit" name="ok" value=<fmt:message key="REJECT_QUERY"/>>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>