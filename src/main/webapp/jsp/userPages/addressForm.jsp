<%--
  Created by IntelliJ IDEA.
  User: Denis Lutsenko
  Date: 5/24/2016
  Time: 11:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/layout/userMenu.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title><fmt:message key="ADDRESS_FORM"/></title>
</head>
<body>

<form action="OnlineBanking24/" method="post">

    <fmt:message key="INPUT_COUNTRY"/>
    <br/> <input type="text" name="country" required
                 oninvalid="this.setCustomValidity(<fmt:message key="HINT_COUNTRY"/>)"
                 oninput="setCustomValidity('')" pattern="([а-яА-ЯёЁ]*)|([A-Za-z]*)" value=""/>
    <br/><br/><fmt:message key="INPUT_CITY"/><br/>

     <input type="text" name="city" required oninvalid="this.setCustomValidity(<fmt:message key="HINT_CITY"/>)"
                 oninput="setCustomValidity('')" pattern="([а-яА-ЯёЁ]*)|([A-Za-z]*)" value=""/>
    <br/><br/><fmt:message key="INPUT_STREET"/><br/>

    <input type="text" name="street" required oninvalid="this.setCustomValidity(<fmt:message key="HINT_STREET"/>)"
                oninput="setCustomValidity('')" pattern="([а-яА-ЯёЁ]*)|([A-Za-z]*)" value=""/>
    <br/><br/><fmt:message key="INPUT_NUMBERS"/><br/>

    <input type="text" name="houseNumber" required
                oninvalid="this.setCustomValidity(<fmt:message key="HINT_HOUSE_NUMBER"/>)"
                oninput="setCustomValidity('')" value=""/>

    <input type="hidden" name="ok" value="PersonalCabinet/Form"/><br/><br/>
    <input type="submit" name="ok" value=<fmt:message key="BTN_NEXT"/>>
</form>


</body>
</html>