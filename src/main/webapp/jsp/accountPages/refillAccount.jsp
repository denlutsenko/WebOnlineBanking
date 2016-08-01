<%--
  Created by IntelliJ IDEA.
  User: Denis Lutsenko
  Date: 5/24/2016
  Time: 6:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/layout/userMenu.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="language"/>
<html>
<head>
    <title></title>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>


<div align="center">
    <form action="<c:url value="/bank24/managements"/>" method="post">

        <select name="currCardId" class="chargeCard" required oninvalid="this.setCustomValidity(<fmt:message key="HINT_SELECT_CARD"/>)" oninput="setCustomValidity('')">
            <option disabled selected value=""><fmt:message key="SELECT_CARD"/></option>
            <c:forEach items="${requestScope.accountList}" var="accountElem">
                <option value="${accountElem.account.id}">${accountElem.type} ${accountElem.account.accountCode} ${accountElem.account.currentBalance} ${accountElem.account.currency}</option>
            </c:forEach>
        </select><br><br>
        <input type="hidden" name="operationType" value="Пополнение средств">
        <input type="text" class="form-control" name="operationSumm" required oninvalid="this.setCustomValidity(<fmt:message key="HINT_ENTER_AMOUNT"/>)" oninput="setCustomValidity('')" pattern="[\d]+[\.]?([\d]{1,2})?" value="amount"/><br/>
        <input type="hidden" name="ok" value="confirmPayment"/>
        <input type="submit" class="btn btn-success" value=<fmt:message key="REFILL_ACCOUNT"/>>
    </form>
</div>
</body>
</html>