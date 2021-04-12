<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="user_details.title" var="title"/>
<fmt:message bundle="${loc}" key="user_details.first_name" var="first_name"/>
<fmt:message bundle="${loc}" key="user_details.second_name" var="second_name"/>
<fmt:message bundle="${loc}" key="user_details.city" var="city"/>
<fmt:message bundle="${loc}" key="user_details.phone" var="phone"/>
<fmt:message bundle="${loc}" key="user_details.address" var="address"/>
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
<div class="container mt-5">
    <div class="row">
        <table class="table text-center mt-1 table-bordered">
            <thead>
            <tr>
                <th class="text-center">${first_name}</th>
                <th class="text-center">${second_name}</th>
                <th class="text-center">${phone}</th>
                <th class="text-center">${city}</th>
                <th class="text-center">${address}</th>
            </tr>
            </thead>
            <tbody>
                <c:set var="info" value="${requestScope.userDetails}"/>
                    <td>${info.firstName}</td>
                    <td>${info.lastName}</td>
                    <td>${info.phone}</td>
                    <td>${info.city}</td>
                    <td>${info.address}</td>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
