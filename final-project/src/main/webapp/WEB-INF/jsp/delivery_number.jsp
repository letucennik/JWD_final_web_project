<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="delivery.title" var="title"/>
<fmt:message bundle="${loc}" key="delivery.products" var="delivered"/>
<fmt:message bundle="${loc}" key="delivery.next" var="next"/>
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
<form id="form" class="form-horizontal">
    <input type="hidden" name="command" value="gotodeliverypage"/>
<div class="wrapper">
    <h2 class="text-center">${delivered} </h2>
    <h2 class="text-center"><input id="deliveries" name="numberOfProductTypes" type="number" min="1" value="1" required/></h2>
</div>
<div class="text-center">
    <button class="btn-register btn btn-lg btn-primary" id="register-button" name="submit"
            type="submit"> ${next} </button>
</div>
</form>
<jsp:include page="template/footer.jsp"/>
</body>
</html>
