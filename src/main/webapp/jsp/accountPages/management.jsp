<%--
  Created by IntelliJ IDEA.
  User: Denis Lutsenko
  Date: 5/24/2016
  Time: 10:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/layout/userMenu.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title></title>
</head>
<body>

<ul class="managementMenu">
    <li>
        <form action="<c:url value="/bank24/managements/openingNewAccount/form"/>" method="post">
            <input type="hidden" name="ok" value="newApplication"/>
            <input type="submit" name="ok" value=<fmt:message key="OPEN_NEW_ACCOUNT"/>>
        </form>
    </li>
    <li>
        <form action="<c:url value="/bank24/managements/blockAccount"/>" method="post">
            <input type="hidden" name="ok" value="lockAccount"/>
            <input type="submit" name="ok" value=<fmt:message key="LOCK_ACCOUNT"/>>
        </form>
    </li>
    <li>
        <form action="<c:url value="/bank24/managements/refillBalance"/>" method="post">
            <input type="hidden" name="ok" value="refillBalance"/>
            <input type="submit" name="ok" value=<fmt:message key="REFILL_ACCOUNT"/>>
        </form>
    </li>
</ul>






</body>
</html>