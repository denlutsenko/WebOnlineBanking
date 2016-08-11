<%--
  Created by IntelliJ IDEA.
  User: Denis Lutsenko
  Date: 5/26/2016
  Time: 4:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/layout/userMenu.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
  <title><fmt:message key="HISTORY"/></title>
</head>
<body>
<fmt:message key="PAYMENTS_HISTORY"/>

<form action="OnlineBanking24/" method="POST">
  <select name="idCard" class="chargeCard">
    <c:forEach items="${requestScope.accountList}" var="accountElem">
        <option value="${accountElem.account.id}">${accountElem.type} ${accountElem.account.accountCode}
        ${accountElem.account.currentBalance} ${accountElem.account.currency}</option>
    </c:forEach>
  </select>

  <input type="hidden" name="ok" value="PersonalCabinet/CurrentHistory"/>
  <input type="submit" class="btn btn-success" value=<fmt:message key="BTN_REFRESH"/>>
</form>


<table class="table table-striped">
  <tr>
    <td><fmt:message key="TRANSACTION_DATE"/></td>
    <td><fmt:message key="PAYMENT_TYPE"/></td>
    <td><fmt:message key="PAYMENT_SUMM"/></td>
  </tr>
  <br/>

  <c:forEach items="${requestScope.historyList}" var="historyElem">
    <tr>
        <td>
            ${historyElem.operationDate}
        </td>
        <td>
            ${historyElem.operationType}
        </td>
        <td>
            ${historyElem.operationSumm}
        </td>
    </tr>
  </c:forEach>
</table>

</body>
</html>
