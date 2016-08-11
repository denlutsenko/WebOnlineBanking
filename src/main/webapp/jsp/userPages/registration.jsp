<%--
  Created by IntelliJ IDEA.
  User: Denis Lutsenko
  Date: 5/23/2016
  Time: 9:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/layout/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title><fmt:message key="REGISTRATION"/></title>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>
<form action="OnlineBanking24/" method="post">

   <fmt:message key="ENTER_FNAME"/>
    <input type="text" class="form-control" name="firstName" required
           oninvalid="this.setCustomValidity(<fmt:message key="HINT_ENTER_FNAME"/>)" oninput="setCustomValidity('')"
           pattern="([а-яА-ЯёЁ][а-яА-ЯёЁ\s]*)|([A-Za-z][A-Za-z\s]*)" value=""/><br/>

    <fmt:message key="ENTER_LNAME"/>
    <input type="text" class="form-control" name="lastName" required
           oninvalid="this.setCustomValidity(<fmt:message key="HINT_ENTER_LNAME"/>)" oninput="setCustomValidity('')"
           pattern="([а-яА-ЯёЁ][а-яА-ЯёЁ\s]*)|([A-Za-z][A-Za-z\s]*)" value=""/><br/>

    <fmt:message key="ENTER_SNAME"/>
    <input type="text" class="form-control" name="middleName" required
           oninvalid="this.setCustomValidity(<fmt:message key="HINT_ENTER_SNAME"/>)" oninput="setCustomValidity('')"
           pattern="([а-яА-ЯёЁ][а-яА-ЯёЁ\s]*)|([A-Za-z][A-Za-z\s]*)" value=""/><br/>

    <fmt:message key="ENTER_PHONE"/>
    <input type="text" class="form-control" name="phone" required
           oninvalid="this.setCustomValidity(<fmt:message key="HINT_ENTER_PHONE"/>)" oninput="setCustomValidity('')"
           pattern="([+3]?[8]?)?[0-9]{3,4}[0-9]{7,8}" value=""/><br/>

    <fmt:message key="ENTER_EMAIL"/>
    <input type="text" class="form-control" name="email" required
           oninvalid="this.setCustomValidity(<fmt:message key="HINT_ENTER_EMAIL"/>)" oninput="setCustomValidity('')"
           pattern="([\w]+)([\.]*[\w]+)*['@'][\w]+[\.]([a-zA-Z]{2,3})+" value=""/><br/>

    Пароль:
    <input type="password" class="form-control" name="password" required
           oninvalid="this.setCustomValidity(<fmt:message key="HINT_ENTER_PASSWORD"/>)" oninput="setCustomValidity('')"
           pattern="([а-яА-ЯёЁ][а-яА-ЯёЁ\s]*)|([A-Za-z][A-Za-z\s]*)|(0-9)*" value=""/><br/>

    <input type="hidden" name="ok" value="RegistrationSuccess"/>
    <input type="submit" class="btn btn-success" value=<fmt:message key="BTN_REGISTRATION"/>/>


</form>
</body>
</html>
