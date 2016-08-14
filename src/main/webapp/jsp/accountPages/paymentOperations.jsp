<%--
  Created by IntelliJ IDEA.
  User: Denis Lutsenko
  Date: 7/26/2016
  Time: 12:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/layout/userMenu.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="MY_PAYMENTS"/></title>
</head>
<body>

<div class="cardsOp">
    <fmt:message key="INNER_TRANSFER"/>
    <br><fmt:message key="FROM_CARD"/>
    <form method="post" action="OnlineBanking24/">
        <select name="idFromCard" class="chargeCard" required
                oninvalid="this.setCustomValidity(<fmt:message key="HINT_SELECT_CARD"/>)"
                oninput="setCustomValidity('')">
            <option disabled selected value=""><fmt:message key="SELECT_CARD"/></option>
            <c:forEach items="${sessionScope.accountList}" var="accountElem">
                <option value="${accountElem.account.id}">${accountElem.type} ${accountElem.account.accountCode}
                        ${accountElem.account.currentBalance} ${accountElem.account.currency} </option>
            </c:forEach>
        </select><br>

        <br/><fmt:message key="TO_CARD"/><br/>
        <select name="idToCard" class="chargeCard" required
                oninvalid="this.setCustomValidity(<fmt:message key="HINT_SELECT_CARD"/>)"
                oninput="setCustomValidity('')">
            <option disabled selected value=""><fmt:message key="SELECT_CARD"/></option>
            <c:forEach items="${sessionScope.accountList}" var="accountElem">
                <option value="${accountElem.account.id}">${accountElem.type} ${accountElem.account.accountCode}
                        ${accountElem.account.currentBalance} ${accountElem.account.currency}</option>
            </c:forEach>
        </select><br>

        <br/> <fmt:message key="AMOUNT"/><br/>
        <input type="hidden" name="operationType" value="Перевод между собственными картами"/>
        <input type="text" class="form-control" name="operationSumm" value="" required
               oninvalid="this.setCustomValidity(<fmt:message key="HINT_ENTER_AMOUNT"/>)"
               oninput="setCustomValidity('')" pattern="[\d]+[\.]?([\d]{1,2})?"/><br/>

        <input type="hidden" name="ok" value="PersonalCabinet/InnerTransferSuccess"/>
        <input type="submit" class="btn btn-success" value=<fmt:message key="BTN_INNER_TRANSFER"/>>
    </form>
    <br/><br/>

    <fmt:message key="WITHDRAWAL"/>
    <br><fmt:message key="FROM_CARD"/>
    <form method="post" action="OnlineBanking24/">
        <select name="idFromCard" class="chargeCard" required
                oninvalid="this.setCustomValidity(<fmt:message key="HINT_SELECT_CARD"/>)"
                oninput="setCustomValidity('')">
            <option disabled selected value=""><fmt:message key="SELECT_CARD"/></option>
            <c:forEach items="${sessionScope.accountList}" var="accountElem">
                <option value="${accountElem.account.id}">${accountElem.type} ${accountElem.account.accountCode}
                ${accountElem.account.currentBalance} ${accountElem.account.currency}</option>
            </c:forEach>
        </select>
        <input type="hidden" name="operationType" value="<fmt:message key="MSG_WITHDRAWAL"/>"/><br>
        <br/> <fmt:message key="AMOUNT"/><br/>
        <input type="text" class="form-control" name="operationSumm" value="" required
               oninvalid="this.setCustomValidity(<fmt:message key="HINT_ENTER_AMOUNT"/>)"
               oninput="setCustomValidity('')" pattern="[\d]+[\.]?([\d]{1,2})?"/><br/>

        <input type="hidden" name="ok" value="PersonalCabinet/WithdrawalSuccess"/>
        <input type="submit" class="btn btn-success" value=<fmt:message key="BTN_TRANSFER_MONEY"/>>
    </form>
    <br>

    <fmt:message key="DO_PAYMENT"/>
    <form method="post" action="OnlineBanking24/">
        <select name="idFromCard" class="chargeCard" required
                oninvalid="this.setCustomValidity(<fmt:message key="HINT_SELECT_CARD"/>)"
                oninput="setCustomValidity('')">
            <option disabled selected value=""><fmt:message key="SELECT_CARD"/></option>
            <c:forEach items="${sessionScope.accountList}" var="accountElem">
                <option value="${accountElem.account.id}">${accountElem.type} ${accountElem.account.accountCode}
                ${accountElem.account.currentBalance} ${accountElem.account.currency}</option>
            </c:forEach>
        </select><br/>
        <br/> <fmt:message key="AMOUNT"/><br/>
        <input type="text" class="form-control" name="operationSumm" value="" required
               oninvalid="this.setCustomValidity(<fmt:message key="HINT_ENTER_AMOUNT"/>)"
               oninput="setCustomValidity('')" pattern="[\d]+[\.]?([\d]{1,2})?"/><br/>
        <fmt:message key="RECIPIENT"/><br/>
        <input type="hidden" name="operationType" value="Оплата услуг, счет №:"/>
        <input type="text" class="form-control" name="idToCard" value=
        <fmt:message
                key="INPUT_ACCOUNT_NUMBER"/>><br/>

        <input type="hidden" name="ok" value="PersonalCabinet/PaymentSuccess"/>
        <input type="submit" class="btn btn-success" value=<fmt:message key="CONFIRM_PAYMENT"/>>
    </form>
</div>
</body>
</html>
