<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="login.title" var="title"/>
<fmt:message bundle="${loc}" key="login.welcome" var="welcome"/>
<fmt:message bundle="${loc}" key="login.button.login" var="login_buton"/>
<fmt:message bundle="${loc}" key="login.signup_hint.message" var="signup_hint_message"/>
<fmt:message bundle="${loc}" key="login.signup_hint.link" var="signup_hint_link"/>
<fmt:message bundle="${loc}" key="login.username_empty" var="username_empty"/>
<fmt:message bundle="${loc}" key="login.password_empty" var="password_empty"/>
<fmt:message bundle="${loc}" key="login.username" var="username"/>
<fmt:message bundle="${loc}" key="login.password" var="password"/>
<fmt:message bundle="${loc}" key="login.banned" var="banned"/>
<fmt:message bundle="${loc}" key="login.invalid_login_or_password" var="invalid_login_or_password"/>
<html lang="${sessionScope.locale}">
<head>
    <title>"${title}"</title>
    <link rel="stylesheet" href="<c:url value="/css/login.css"/>">
    <jsp:include page="template/header.jsp"/>
<body>
<div class="container">
    <div class="wrapper">
        <form id="login-form" class="form-signin" action="Controller" method="post" accept-charset="UTF-8">
            <h3 class="form-signin-heading"> ${welcome}</h3>
            <hr class="colorgraph">
            <br>
            <input type="hidden" name="command" value="authorization"/>
            <input type="text" class="form-control" id="username" name="login" placeholder="${username}" required
                   oninvalid="this.setCustomValidity('${username_empty}')"
                   oninput="this.setCustomValidity('')"
                   autofocus/>
            <input type="password" class="form-control" id="password" name="password" placeholder="${password}"
                   required placeholder="Username" required oninvalid="this.setCustomValidity('${password_empty}')"
                   oninput="this.setCustomValidity('')"/>
            <button class="btn btn-lg btn-primary btn-block" id="login-button" name="submit" value="Login"
                    type="submit">${login_buton} </button>
        </form>
        <div class="alert alert-success">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${signup_hint_message}
            <a href="<c:url value="Controller?command=gotoregistrationpage"/>">${signup_hint_link}</a>.
        </div>
        <c:if test="${param.message!=null|| param.banned!=null}">

        <div id="Bar" class="alert alert-danger"  >
            <div id="right">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            </div>

                <c:if test="${param.banned!=null}">
            <strong id="error-title">
                    ${banned}
            </strong>
                </c:if>
                <c:if test="${param.message!=null}">
            <strong id="error-title">
                    ${invalid_login_or_password}
            </strong>
                </c:if>
        </div>
        </c:if>
    </div>
</div>
<jsp:include page="template/footer.jsp"/>
</body>
</html>
