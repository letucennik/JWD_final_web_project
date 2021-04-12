<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="registration.title" var="title"/>
<fmt:message bundle="${loc}" key="registration.header" var="reg_header"/>
<fmt:message bundle="${loc}" key="registration.username.label" var="username_label"/>
<fmt:message bundle="${loc}" key="registration.username.hint" var="username_hint"/>
<fmt:message bundle="${loc}" key="registration.email.label" var="email_label"/>
<fmt:message bundle="${loc}" key="registration.password.label" var="password_label"/>
<fmt:message bundle="${loc}" key="registration.password.hint" var="password_hint"/>
<fmt:message bundle="${loc}" key="registration.password.confirm.label" var="password_confirm_label"/>
<fmt:message bundle="${loc}" key="registration.button.signup" var="signup"/>
<fmt:message bundle="${loc}" key="registration.login_hint.message" var="login_hint"/>
<fmt:message bundle="${loc}" key="registration.login_hint.link" var="login_link"/>
<fmt:message bundle="${loc}" key="registration.empty_username" var="empty_username"/>
<fmt:message bundle="${loc}" key="registration.empty.email" var="empty_email"/>
<fmt:message bundle="${loc}" key="registration.invalid.email" var="invalid_email"/>
<fmt:message bundle="${loc}" key="registration.empty.password" var="empty_password"/>
<fmt:message bundle="${loc}" key="registration.invalid.password_confirm" var="invalid_password_confirm"/>
<fmt:message bundle="${loc}" key="registration.email.exists" var="email_exists"/>
<fmt:message bundle="${loc}" key="registration.invalid.login" var="invalid_login"/>
<fmt:message bundle="${loc}" key="registration.invalid.password" var="invalid_password"/>
<fmt:message bundle="${loc}" key="registration.username.exists" var="username_exists"/>
<html lang="${sessionScope.locale}">
<head>
    <meta charset="utf-8">
    <title>"${title}"</title>
    <link rel="stylesheet" href="<c:url value="/css/registration.css"/>">
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.css" rel="stylesheet">
    <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
</head>
<body>
<jsp:include page="template/header.jsp"/>
<div class="container">
    <div class="wrapper">
        <h2 class="text-center">${reg_header} </h2>
    </div>
    <form id="form" class="form-horizontal">
        <input type="hidden" id="empty_username" value="${empty_username}">
        <input type="hidden" id="username_hint" value="${username_hint}">
        <input type="hidden" id="empty_email" value="${empty_email}">
        <input type="hidden" id="invalid_email" value="${invalid_email}">
        <input type="hidden" id="empty_password" value="${empty_password}">
        <input type="hidden" id="password_hint" value="${password_hint}">
        <input type="hidden" id="invalid_password_confirm" value="${invalid_password_confirm}">
        <input type="hidden" name="command" value="savenewuser"/>
        <div class="form-group">
            <label class="control-label col-sm-offset-2 col-sm-2 required-mark" for="username">${username_label}</label>
            <div class="col-sm-4">
                <input type="text" class="bg-danger form-control" id="username" name="username" title="${username_hint}"
                       value="" placeholder="User11"/>
                <i class="fas fa-check-circle"></i>
                <i class="fas fa-exclamation-circle"></i>
                <small>Error message</small>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-offset-2 col-sm-2 required-mark" for="email">${email_label}</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="email" name="email" value=""
                       placeholder="example@example.com"/>
                <i class="fas fa-check-circle"></i>
                <i class="fas fa-exclamation-circle"></i>
                <small>Error message</small>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-offset-2 col-sm-2 required-mark" for="password">${password_label}</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" id="password" name="password"
                       title="${password_hint}"
                       placeholder="${password_label}">
                <i class="fas fa-check-circle"></i>
                <i class="fas fa-exclamation-circle"></i>
                <small>Error message</small>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-offset-2 col-sm-2 required-mark"
                   for="password-confirm">${password_confirm_label}</label>

            <div class="col-sm-4">
                <input type="password" class="form-control" id="password-confirm"
                       title="${password_hint}"
                       placeholder="${password_confirm_label}">
                <i class="fas fa-check-circle"></i>
                <i class="fas fa-exclamation-circle"></i>
                <small>Error message</small>
            </div>
        </div>
        <div class="text-center">
            <button class="btn-register btn btn-lg btn-primary" id="register-button" name="submit"
                    type="submit">${signup} </button>
        </div>

    </form>
    <div class="login-hint alert alert-success">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        ${login_hint} <a
            href="Controller?command=gotoauthorizationpage">${login_link} </a>
    </div>
    <c:if test="${param.message!=null}">
        <div id="Bar" class="alert alert-danger">
            <div id="right">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            </div>
            <strong id="error-title">
                <c:if test="${param.message=='Email exists'}">
                    ${email_exists}
                </c:if>
                <c:if test="${param.message=='Username exists'}">
                    ${username_exists}
                </c:if>
                <c:if test="${param.message=='Invalid login'}">
                    ${invalid_login}
                </c:if>
                <c:if test="${param.message=='Invalid password'}">
                    ${invalid_password}
                </c:if>
                <c:if test="${param.message=='Invalid email'}">
                    ${invalid_email}
                </c:if>
                <a href="Controller?command=gotoauthorizationpage">${login_link} </a>
            </strong>
        </div>
    </c:if>
</div>
<jsp:include page="template/footer.jsp"/>
<script src="<c:url value="/js/registration.js"/>"></script>
</body>
</html>