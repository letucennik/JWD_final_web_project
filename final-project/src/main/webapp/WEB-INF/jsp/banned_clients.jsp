<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="clients.banned.title" var="title"/>
<fmt:message bundle="${loc}" key="clients.login" var="login"/>
<fmt:message bundle="${loc}" key="clients.password" var="password"/>
<fmt:message bundle="${loc}" key="clients.email" var="email"/>
<fmt:message bundle="${loc}" key="clients.info" var="info"/>
<fmt:message bundle="${loc}" key="clients.unban" var="ban"/>
<fmt:message bundle="${loc}" key="clients.active_orders" var="active_orders"/>
<fmt:message bundle="${loc}" key="active_orders.action" var="action"/>
<fmt:message bundle="${loc}" key="user_details.success" var="success"/>
<fmt:message bundle="${loc}" key="clients.sure" var="sure"/>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="<c:url value="/js/lib/jquery-3.1.1.js"/>"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
    <jsp:include page="template/header.jsp"/>
</head>
<body>
<jsp:include page="template/navbar.jsp"/>
<c:if test="${requestScope.unbanned!=null}">
    <div class="login-hint alert alert-success">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${success}
    </div>
</c:if>
<div class="container mt-5">
    <div class="row">
        <table class="table text-center mt-1 table-bordered">
            <thead>
            <tr>
                <th class="text-center">ID</th>
                <th class="text-center">${login}</th>
                <th class="text-center">${password}</th>
                <th class="text-center">${email}</th>
                <th class="text-center">${action}</th>
                <th class="text-center">${action}</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="client" items="${requestScope.bannedClients}">
                <tr>
                    <input id="ban" type="hidden" value="${sure}"/>
                    <td>${client.id}</td>
                    <td>${client.username}</td>
                    <td>${client.password}</td>
                    <td>${client.email}</td>
                    <td><a href="Controller?command=viewuserdetails&userId=${client.id}">${info}</a> </td>
                    <td><a onclick="ban()" href="Controller?command=unbanuser&userId=${client.id}&url=${requestScope.fullUrl}"> ${ban}</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="<c:url value="/js/clients.js"/>"></script>
<jsp:include page="template/footer.jsp"/>
</body>
</html>
