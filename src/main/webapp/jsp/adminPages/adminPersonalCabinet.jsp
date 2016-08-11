<%--
  Created by IntelliJ IDEA.
  User: Denis Lutsenko
  Date: 6/1/2016
  Time: 2:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/layout/adminMenu.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title><fmt:message key="PERSONAL_CABINET"/></title>
</head>
<body>
<h4><fmt:message key="PERSONAL_CABINET"/></h4>
<H5>${sessionScope.user.firstName} ${sessionScope.user.lastName}</H5>
<h2 ALIGN="center"><fmt:message key="${requestScope.msg}"/></h2>
</body>
</html>