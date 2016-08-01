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
    <title>Title</title>
</head>
<body>
personal page
${sessionScope.user}
${sessionScope.user.firstName}
${sessionScope.user.lastName}
${sessionScope.user.middleName}
${sessionScope.user.phoneNumber}

${sessionScope.user.password}
${sessionScope.user.email}

</body>
</html>
