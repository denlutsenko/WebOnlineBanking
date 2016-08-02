<%--
  Created by IntelliJ IDEA.
  User: Denis Lutsenko
  Date: 6/6/2016
  Time: 1:32 PM
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


<div>
    <c:forEach items="${requestScope.blockedCardsList}" var="listAccountsElem">
        <div>
                ${listAccountsElem.user.firstName}
                ${listAccountsElem.user.lastName}
                ${listAccountsElem.user.middleName}
                ${listAccountsElem.accountCode}
                ${listAccountsElem.currentBalance}
                ${listAccountsElem.currency}
        </div>
    </c:forEach>
</div>

<div>
    <form action="<c:url value="/bank24/personalCabinet"/>" method="post">
        <input type="text" name="cardNumber" required
               oninvalid="this.setCustomValidity(<fmt:message key="HINT_WRITE_CARD"/>)"
               oninput="setCustomValidity('')" pattern="[\d]{16}" value=""/><br/>
        <input type="hidden" name="ok" value="unlockAccount"/>
        <input type="submit" name="ok" value=<fmt:message key="BTN_UNLOCK_ACCOUNT"/>>
    </form>
</div>
</body>
</html>