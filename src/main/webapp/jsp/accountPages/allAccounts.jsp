<%--
  Created by IntelliJ IDEA.
  User: Denis Lutsenko
  Date: 7/25/2016
  Time: 9:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/layout/userMenu.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<html>
<head>
    <title><fmt:message key="MY_ACCOUNTS"/></title>
</head>
<body>

<div class="accountss">

    <c:forEach items="${requestScope.accountConditions}" var="listAccountsElem">
        <div><b><fmt:message key="ACCOUNT"/>: ${listAccountsElem.account.accountCode}</b></div>
        <div><fmt:message key="ACC_TYPE"/>: ${listAccountsElem.type}</div>
        <div><fmt:message key="ACC_DEADLINE"/> ${listAccountsElem.deadLine}</div>
        <br/>
    </c:forEach>
</div>


<div class="accountsOne">
    <c:forEach items="${requestScope.listDebitConditions}" var="accountRootElem">
        <!--my tag start-->
        <ctg:account-list accountRootElem="${accountRootElem}" lang="${sessionScope.lang}"/>
        <!--my tag end-->
    </c:forEach>
    <br>

    <div>
        <c:forEach items="${requestScope.listCreditConditions}" var="listAccountsElem">
            <div><b>${listAccountsElem.type}: ${listAccountsElem.account.accountCode}</b></div>
            <div><fmt:message
                    key="CREDIT_LIMIT"/>${listAccountsElem.account.defaultBalance} ${listAccountsElem.account.currency} </div>
            <div><fmt:message key="AVALIABLE_BALANCE"/> ${listAccountsElem.account.currentBalance}</div>
            <div><fmt:message key="DEBT"/>
                    ${listAccountsElem.account.currentBalance-listAccountsElem.account.defaultBalance}
            </div>
            <div><fmt:message key="WITHDRAWAL_PERCENT"/> ${listAccountsElem.percentOfWithdrawal} %</div>
            <div><fmt:message key="MONTHLY_PERCENT"/> ${listAccountsElem.monthlyPercent} %</div>
            <br/>
        </c:forEach>
    </div>
</div>
</body>
</html>