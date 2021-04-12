<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="user_details.title" var="title"/>
<fmt:message bundle="${loc}" key="user_details.header" var="u_header"/>
<fmt:message bundle="${loc}" key="user_details.first_name" var="first_name"/>
<fmt:message bundle="${loc}" key="user_details.name.hint" var="name_hint"/>
<fmt:message bundle="${loc}" key="user_details.second_name" var="second_name"/>
<fmt:message bundle="${loc}" key="user_details.city" var="city"/>
<fmt:message bundle="${loc}" key="user_details.minsk" var="Minsk"/>
<fmt:message bundle="${loc}" key="user_details.hrodna" var="Hrodna"/>
<fmt:message bundle="${loc}" key="user_details.homel" var="Homel"/>
<fmt:message bundle="${loc}" key="user_details.brest" var="Brest"/>
<fmt:message bundle="${loc}" key="user_details.mahileu" var="Mahileu"/>
<fmt:message bundle="${loc}" key="user_details.vitsebsk" var="Vitsebsk"/>
<fmt:message bundle="${loc}" key="user_details.phone" var="phone"/>
<fmt:message bundle="${loc}" key="user_details.phone.hint" var="phone_hint"/>
<fmt:message bundle="${loc}" key="user_details.address" var="address"/>
<fmt:message bundle="${loc}" key="user_details.submit" var="submit"/>
<fmt:message bundle="${loc}" key="user_details.empty_first_name" var="empty_first_name"/>
<fmt:message bundle="${loc}" key="user_details.empty_second_name" var="empty_second_name"/>
<fmt:message bundle="${loc}" key="user_details.empty_phone" var="empty_phone"/>
<fmt:message bundle="${loc}" key="user_details.empty_address" var="empty_address"/>
<fmt:message bundle="${loc}" key="user_details.invalid_first_name" var="invalid_first_name"/>
<fmt:message bundle="${loc}" key="user_details.empty_second_name" var="empty_second_name"/>
<fmt:message bundle="${loc}" key="user_details.invalid_phone" var="invalid_phone"/>
<fmt:message bundle="${loc}" key="user_details.invalid_name" var="invalid_name"/>
<fmt:message bundle="${loc}" key="user_details.invalid_second_name" var="invalid_second_name"/>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="<c:url value="/css/registration.css"/>">
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.css" rel="stylesheet">
    <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
</head>
<body>
<jsp:include page="template/header.jsp"/>
<div class="container">
    <div class="wrapper">
        <h2 class="text-center">${u_header} </h2>
    </div>
    <form id="form" class="form-horizontal">
        <input type="hidden" id="empty_first_name" value="${empty_first_name}"/>
        <input type="hidden" id="empty_second_name" value="${empty_second_name}"/>
        <input type="hidden" id="empty_phone" value="${empty_phone}"/>
        <input type="hidden" id="empty_address" value="${empty_address}"/>
        <input type="hidden" id="invalid_first_name" value="${name_hint}"/>
        <input type="hidden" id="invalid_second_name" value="${name_hint}"/>
        <input type="hidden" id="invalid_phone" value="${phone_hint}"/>
        <input type="hidden" name="command" value="saveuserdetails"/>
        <div class="form-group">
            <label class="control-label col-sm-offset-2 col-sm-2 required-mark" for="first_name">${first_name}</label>
            <div class="col-sm-4">
                <input type="text" class="bg-danger form-control" id="first_name" name="firstName" title="${name_hint}"
                       value="" placeholder="Ваня"/>
                <i class="fas fa-check-circle"></i>
                <i class="fas fa-exclamation-circle"></i>
                <small>Error message</small>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-offset-2 col-sm-2 required-mark" for="second_name">${second_name}</label>
            <div class="col-sm-4">
                <input type="text" class="bg-danger form-control" id="second_name" name="secondName" title="${name_hint}"
                       value="" placeholder="Петров"/>
                <i class="fas fa-check-circle"></i>
                <i class="fas fa-exclamation-circle"></i>
                <small>Error message</small>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-offset-2 col-sm-2 required-mark" for="city">${city}</label>
            <div class="col-sm-4">
                <select name="city" id="city" form="form">
                    <option value="Минск">${Minsk}</option>
                    <option value="Гродно">${Hrodna}</option>
                    <option value="Брест">${Brest}</option>
                    <option value="Гомель">${Homel}</option>
                    <option value="Витебск">${Vitsebsk}</option>
                    <option value="Могилев">${Mahileu}</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-offset-2 col-sm-2 required-mark" for="phone">${phone}</label>
            <div class="col-sm-4">
                <input type="text" class="bg-danger form-control" id="phone" name="phone" title="${phone_hint}"
                       value="" placeholder="${phone_hint}"/>
                <i class="fas fa-check-circle"></i>
                <i class="fas fa-exclamation-circle"></i>
                <small>Error message</small>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-offset-2 col-sm-2 required-mark" for="address">${address}</label>
            <div class="col-sm-4">
                <input type="text" class="bg-danger form-control" id="address" name="address"
                       value=""/>
                <i class="fas fa-check-circle"></i>
                <i class="fas fa-exclamation-circle"></i>
                <small>Error message</small>
            </div>
        </div>
        <div class="text-center">
            <button class="btn-register btn btn-lg btn-primary" id="register-button" name="submit"
                    type="submit">${submit} </button>
        </div>

    </form>
<c:if test="${param.message!=null}">
    <div id="Bar" class="alert alert-danger">
        <div id="right">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        </div>
        <strong id="error-title">
            <c:if test="${param.message=='Invalid name'}">
                ${invalid_name}
            </c:if>
            <c:if test="${param.message=='Invalid address'}">
                ${empty_address}
            </c:if>
            <c:if test="${param.message=='Invalid phone'}">
                ${invalid_phone}
            </c:if>
        </strong>
</c:if>
</div>
<jsp:include page="template/footer.jsp"/>
<script src="<c:url value="/js/user_details.js"/>"></script>
</body>
</html>
