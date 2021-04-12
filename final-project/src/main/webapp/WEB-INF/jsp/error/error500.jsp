<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="error.title" var="title"/>
<fmt:message bundle="${loc}" key="error.500.title" var="errorTitle"/>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="<c:url value="/css/error.css"/>">
    <jsp:include page="../template/header.jsp"/>
    <jsp:include page="../template/navbar.jsp"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1>${errorTitle}</h1>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../template/footer.jsp"/>
</body>
</html>
