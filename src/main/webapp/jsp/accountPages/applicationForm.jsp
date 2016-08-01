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
<div align="center">
    <form action="<c:url value="/bank24/managements"/>" method="post">

        <fmt:message key="SPECIFY_TYPE_OF_ACCOUNT"/>
        <input type="text" class="form-control" name="accountType" required oninvalid="this.setCustomValidity(<fmt:message key="HINT_ACCOUNT_TYPE"/>)" oninput="setCustomValidity('')" pattern="CREDIT|DEBIT" value=""/>

        <fmt:message key="SPECIFY_CURRENCY"/>
        <input type="text" class="form-control" name="accountCurrency" required oninvalid="this.setCustomValidity(<fmt:message key="HINT_CURRENCY"/>)" oninput="setCustomValidity('')" pattern="(USD|EUR|UAH)" value=""/>

        <fmt:message key="INITIAL_BALANCE"/>
        <input type="text" class="form-control" name="accountBalance" required oninvalid="this.setCustomValidity(<fmt:message key="HINT_ENTER_AMOUNT"/>)" oninput="setCustomValidity('')" pattern="[\d]+[\.]?[\d]{1,2}?" value=""/>


        <input type="hidden" name="ok" value="saveNewApplication"/>
        <input type="submit" class="btn btn-success" value=<fmt:message key="SEND_REQUEST"/>>
    </form>
</div>
</body>
</html>