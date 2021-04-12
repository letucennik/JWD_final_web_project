<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="header.application.name" var="application_name"/>
<fmt:message bundle="${loc}" key="login.button.login" var="login_button"/>
<fmt:message bundle="${loc}" key="header.button.signup" var="signup_button"/>
<fmt:message bundle="${loc}" key="header.button.signup" var="signup_button"/>
<fmt:message bundle="${loc}" key="header.button.change_language" var="change_lang"/>
<fmt:message bundle="${loc}" key="header.button.lang.ru" var="lang_ru"/>
<fmt:message bundle="${loc}" key="header.button.lang.en" var="lang_en"/>
<fmt:message bundle="${loc}" key="header.cart" var="cart"/>
<fmt:message bundle="${loc}" key="header.active_orders" var="active_orders"/>
<fmt:message bundle="${loc}" key="header.add_product" var="add_product"/>
<fmt:message bundle="${loc}" key="header.logout" var="logout"/>
<fmt:message bundle="${loc}" key="header.view_clients" var="clients"/>
<fmt:message bundle="${loc}" key="header.view_banned_clients" var="banned_clients"/>
<fmt:message bundle="${loc}" key="delivery.title" var="delivery"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/css/header.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/> " type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<div class="header">
    <div class="logo-container">
        <a id="company_name" href="Controller?command=gotomainpage">Panenka</a>
        <span><img class="logo-image" id="logo-image" src="<c:url value="/img/about-logo.png"/>"></span>
    </div>
</div>
<nav class="navbar navbar-inverse">
    <div class="parent">
        <div class="container-fluid">
            <ul class="nav navbar-nav">
                <c:set var="user" value="${sessionScope.user}"/>
                <c:if test="${user==null}">
                    <li><a id="menu_item" type="button"
                           href="Controller?command=gotoregistrationpage">${signup_button}</a></li>
                    <li class="menu_item"><a type="button"
                                             href="Controller?command=gotoauthorizationpage">${login_button}</a></li>
                </c:if>
                <c:if test="${user!=null&&user.role==1}">
                    <li class="menu_item"><a type="button" href="Controller?command=gotocartpage">${cart}</a></li>
                    <li class="menu_item"><a type="button" href="Controller?command=gotoactiveorderspage">${active_orders}</a></li>
                </c:if>
                <c:if test="${user!=null&&user.role==2}">
                    <li class="menu_item"><a type="button" href="Controller?command=viewallclients">${clients}</a></li>
                    <li class="menu_item"><a type="button" href="Controller?command=viewbannedclients">${banned_clients}</a></li>
                    <li class="menu_item"><a type="button" href="Controller?command=viewordersbystatus&status=2">${active_orders}</a></li>
                    <li class="menu_item"><a type="button" href="Controller?command=gotoaddproductpage">${add_product}</a></li>
                    <li class="menu_item"><a type="button" href="Controller?command=gotodeliverynumberpage">${delivery}</a></li>
                </c:if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${user!=null}">
                    <li>
                        <a href="Controller?command=logout&url=${requestScope.fullUrl}">${logout}
                        </a>
                    </li>
                </c:if>
                <li class="menu_item">
                <div class="drop-menu">
                    <button class="dropbtn"><a id="change" class="dropdown-toggle" data-toggle="dropdown"
                                               href="#">${change_lang} <span class="caret"></span></a></button>
                    <div class="drop-content">
                        <a href="Controller?command=changelocale&locale=ru&url=${requestScope.fullUrl}">Русский</a>
                        <a href="Controller?command=changelocale&locale=en&url=${requestScope.fullUrl}">English</a>
                    </div>
                </div>
                </li>

            </ul>

        </div>
    </div>
</nav>
</html>
