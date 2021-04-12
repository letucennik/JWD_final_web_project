<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="category.skincare" var="scincare"/>
<fmt:message bundle="${loc}" key="category.makeup" var="makeup"/>
<fmt:message bundle="${loc}" key="category.men" var="men"/>
<fmt:message bundle="${loc}" key="category.perfumery" var="perfumery"/>
<fmt:message bundle="${loc}" key="category.sunscreen" var="sunscreen"/>
<fmt:message bundle="${loc}" key="category.skincare.basic.eye_lip" var="eye_lip_care"/>
<fmt:message bundle="${loc}" key="category.skincare.basic.masks" var="masks"/>
<fmt:message bundle="${loc}" key="category.skincare.basic.cleanser" var="cleanser"/>
<fmt:bundle basename="url">
    <fmt:message key="url.eye_lip_care" var="eye_lip_url"/>
    <fmt:message key="url.masks" var="masks_url"/>
    <fmt:message key="url.cleanser" var="cleanser_url"/>
    <fmt:message key="url.wrinkles" var="wrinkles_url"/>
    <fmt:message key="url.dark_spots" var="dark_spots_url"/>
    <fmt:message key="url.flabbiness" var="flabbiness_url"/>
    <fmt:message key="url.acne_rashes" var="acne_url"/>
    <fmt:message key="url.dark_circles" var="dark_circles_url"/>
    <fmt:message key="url.dryness" var="dryness_url"/>
    <fmt:message key="url.oil" var="oil_url"/>
</fmt:bundle>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/navbar.css"/>" type="text/css">
</head>
<nav>
    <div class="wrapper">
        <ul class="nav-links">
            <li>
                <a>${scincare}</a>
                <div class="mega-box">
                    <div class="content">
                        <div class="row">
                            <header>
                                <fmt:message bundle="${loc}" key="category.skincare.basic"/>
                            </header>
                            <ul class="mega-links">
                                <li><a href="Controller?command=gotocategorypage&nameEn=${eye_lip_url}">${eye_lip_care}</a></li>
                                <li><a href="Controller?command=gotocategorypage&nameEn=${masks_url}">${masks}</a></li>
                                <li><a href="Controller?command=gotocategorypage&nameEn=${cleanser_url}">${cleanser}</a>
                                </li>
                            </ul>
                        </div>
                        <div class="row">
                            <header>
                                <fmt:message bundle="${loc}" key="category.scincare.antiage"/>
                            </header>
                            <ul class="mega-links">
                                <li><a href="Controller?command=gotocategorypage&nameEn=${wrinkles_url}"> <fmt:message bundle="${loc}" key="category.scincare.antiage.deep_wrinles"/></a>
                                </li>
                                <li><a href="Controller?command=gotocategorypage&nameEn=${dark_spots_url}"> <fmt:message bundle="${loc}" key="category.scincare.antiage.dark_spots"/></a>
                                </li>
                                <li><a href="Controller?command=gotocategorypage&nameEn=${flabbiness_url}"> <fmt:message bundle="${loc}" key="category.scincare.antiage.flabiness"/></a>
                                </li>
                            </ul>
                        </div>
                        <div class="row">
                            <header>
                                <fmt:message bundle="${loc}" key="category.skincare.concerns"/>
                            </header>
                            <ul class="mega-links">
                                <li><a href="Controller?command=gotocategorypage&nameEn=${acne_url}"> <fmt:message bundle="${loc}" key="category.skincare.concerns.acne"/></a></li>
                                <li><a href="Controller?command=gotocategorypage&nameEn=${dark_circles_url}"> <fmt:message bundle="${loc}" key="category.skincare.concerns.dark_circles"/></a>
                                </li>
                                <li><a href="Controller?command=gotocategorypage&nameEn=${dryness_url}"> <fmt:message bundle="${loc}" key="category.skincare.concerns.dryness"/></a></li>
                                <li><a href="Controller?command=gotocategorypage&nameEn=${oil_url}"> <fmt:message bundle="${loc}" key="category.skincare.concerns.oil"/></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </li>
            <li><a>${sunscreen}</a></li>
            <li><a>${makeup}</a></li>
            <li><a>${perfumery}</a></li>
        </ul>
    </div>
</nav>
</html>


