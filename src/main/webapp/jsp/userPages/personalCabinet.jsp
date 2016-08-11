<%--
  Created by IntelliJ IDEA.
  User: Denis Lutsenko
  Date: 7/25/2016
  Time: 4:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/layout/userMenu.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
