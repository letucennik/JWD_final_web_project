<%@ page language="java" contentType="text/html;
    charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="main.title" var="title"/>
<fmt:message bundle="${loc}" key="main.confirmed" var="confirmed"/>
<fmt:message bundle="${loc}" key="main.inserted_product" var="inserted"/>
<fmt:message bundle="${loc}" key="main.inserted_delivery" var="inserted_delivery"/>
<html lang="${sessionScope.locale}">
<head>
    <meta charset="utf-8">
    <title>${title}</title>
    <link rel="stylesheet" href="<c:url value="/css/main.css"/>" type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="<c:url value="/js/lib/jquery-3.1.1.js"/>"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
    <jsp:include page="template/header.jsp"/>
    <jsp:include page="template/navbar.jsp"/>
</head>
<body>
<c:if test="${param.insertedUserDetails!=null}">
    <div class="login-hint alert alert-success">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${confirmed}
    </div>
</c:if>
<c:if test="${param.insertedProduct!=null}">
    <div class="login-hint alert alert-success">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${inserted}
    </div>
</c:if>
<c:if test="${param.message!=null}">
    <div class="login-hint alert alert-success">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${inserted_delivery}
    </div>
</c:if>

<div class="wrapper">
<div class="container">
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner">
            <div class="item active">
               <span>
                <img src="<c:url value="/img/main_page1.jpg"/>" style="width: 100%">
                </span>
            </div>

            <div class="item">
                <span>
                <img src="<c:url value="/img/main_page2.jpg"/>" style="width: 100%">
                </span>
            </div>

            <div class="item">
                <span>
                <img src="<c:url value="/img/main_page3.jpg"/>" style="width: 100%">
                </span>
            </div>
        </div>

        <!-- Left and right controls -->
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>
</div>
<jsp:include page="template/footer.jsp"/>
</body>

</html>