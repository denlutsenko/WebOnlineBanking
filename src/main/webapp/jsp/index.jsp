<%--
  Created by IntelliJ IDEA.
  User: Denis Lutsenko
  Date: 5/23/2016
  Time: 9:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/layout/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
  <title><fmt:message key="LOGIN"/></title>
  <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">

</head>
<body>

<div>
  <form name="checkLogin" action="OnlineBanking24/"  method="post">
    <fmt:message key="LOGIN"/>
    <input type="text" class="form-control" name="email"
           required oninvalid="this.setCustomValidity(<fmt:message key="HINT_ENTER_LOGIN"/>)"
           oninput="setCustomValidity('')" value="" pattern="([\w]+)([\.]*[\w]+)*['@'][\w]+[\.]([a-zA-Z]{2,3})+"/><br/>

    <fmt:message key="PASSWORD"/>
    <input type="password" class="form-control" name="password" required
           oninvalid="this.setCustomValidity(<fmt:message key="HINT_ENTER_PASSWORD"/>)" oninput="setCustomValidity('')"
           value="" pattern="[\w]+"/>

    <input type="hidden" name="ok" value="PersonalCabinet"/>
    <input type="submit" class="btn btn-success" value= <fmt:message key="LOGIN"/>>

  </form>
</div>

<a href="<c:url value="/jsp/userPages/registration.jsp"/>"> <fmt:message key="REGISTRATION"/></a>

</body>
</html>
