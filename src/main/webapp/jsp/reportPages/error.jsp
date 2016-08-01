<%--
  Created by IntelliJ IDEA.
  User: Denis Lutsenko
  Date: 6/9/2016
  Time: 8:44 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title></title>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>

<H3 align="center"><fmt:message key="ERR_MSG"/></H3>
<H4 align="center"><fmt:message key="${msg}"/></H4>


</body>
</html>
