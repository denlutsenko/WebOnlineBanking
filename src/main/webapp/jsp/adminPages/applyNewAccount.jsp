<%--
  Created by IntelliJ IDEA.
  User: Denis Lutsenko
  Date: 6/7/2016
  Time: 11:07 PM
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


<fmt:message key="QUERIES_FROM_ACCOUNT"/>
<table border="1">
    <tr>
        <td><fmt:message key="TYPE_OF_ACCOUNT"/></td>
        <td>${sessionScope.type}</td>
    </tr>
    <tr>
        <td><fmt:message key="INITIAL_BAL"/></td>
        <td>${sessionScope.balance}</td>
    </tr>
    <tr>
        <td><fmt:message key="CURRENCY"/></td>
        <td>${sessionScope.currency}</td>
    </tr>
</table>
<br/>

<td><fmt:message key="FILL_FORM"/></td>
<form action="<c:url value="/bank24/personalCabinet/success"/>" method="post">

    <table border="1">
        <tr>
            <td><fmt:message key="ACCOUNT_CODE"/></td>
            <td>
                <input type="text" name="accountCode" required oninvalid="this.setCustomValidity(<fmt:message key="HINT_WRITE_CARD"/>)" oninput="setCustomValidity('')" pattern="[\d]{16}" value=""/>
            </td>
        </tr>
        <tr>
            <td><fmt:message key="ACCOUNT_TYPE"/></td>
            <td>
                <input type="text" name="accountType" required oninvalid="this.setCustomValidity(<fmt:message key="HINT_INSERT_TYPE"/>)" oninput="setCustomValidity('')" pattern="CREDIT|DEBIT" value=""/>
            </td>
        </tr>
        <tr>
            <td><fmt:message key="WITHDRAWAL_PERCENT"/></td>
            <td>
                <input type="text" name="accountWPercent" required oninvalid="this.setCustomValidity(<fmt:message key="HINT_WITHDRAWAL_PERCENT"/>)" oninput="setCustomValidity('')" pattern="[\d]{1,2}[.]?[\d]{1,2}" value=""/>
            </td>
        </tr>
        <tr>
            <td><fmt:message key="MONTHLY_PERCENT"/>,%</td>
            <td>
                <input type="text" name="monthlyPercent" required oninvalid="this.setCustomValidity(<fmt:message key="HINT_MONTHLY_PERCENT"/>)" oninput="setCustomValidity('')" pattern="[\d]{1,2}[.]?[\d]{1,2}" value=""/>
            </td>
        </tr>
        <tr>
            <td><fmt:message key="CARD_DEADLINE"/></td>
            <td>
                <input type="text" name="accountDeadline" required oninvalid="this.setCustomValidity(<fmt:message key="HINT_DEADLINE"/>)" oninput="setCustomValidity('')" pattern="[\d]{4}[-]{1}[\d]{2}[-]{1}[\d]{2}" value=""/>
            </td>
        </tr>
    </table>


    <input type="hidden" name="ok" value="confirmNewAccount"/>
    <input type="submit" name="ok" value=<fmt:message key="BTN_CREATE_AN_ACCOUNT"/>>
</form>

</body>
</html>