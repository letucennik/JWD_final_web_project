<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="active_orders.title" var="title"/>
<fmt:message bundle="${loc}" key="main.confirmed" var="confirmed"/>
<fmt:message bundle="${loc}" key="active_orders.confirmation_date" var="confirmation_date"/>
<fmt:message bundle="${loc}" key="active_orders.ready_date" var="ready_date"/>
<fmt:message bundle="${loc}" key="active_orders.status" var="status"/>
<fmt:message bundle="${loc}" key="active_orders.action" var="action"/>
<fmt:message bundle="${loc}" key="active_orders.inprocess" var="in_process"/>
<fmt:message bundle="${loc}" key="active_orders.view" var="view"/>
<fmt:message bundle="${loc}" key="active_orders.cancel" var="cancel"/>
<fmt:message bundle="${loc}" key="user_details.success" var="success"/>
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
<c:if test="${param.message=='insertedUserDetails'}">
    <div class="login-hint alert alert-success">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${success}
    </div>
</c:if>
<c:if test="${param.confirmed!=null}">
    <div class="login-hint alert alert-success">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${confirmed}
    </div>
</c:if>
<div class="container mt-5">
    <c:set var="user" value="${sessionScope.user}"/>
    <div class="row">
        <table class="table text-center mt-1 table-bordered">
            <thead>
            <tr>
                <th class="text-center">ID</th>
                <th class="text-center">${confirmation_date}</th>
                <th class="text-center">${ready_date}</th>
                <th class="text-center">${status}</th>
                <th class="text-center">${action}</th>
                <c:if test="${user!=null&&user.role==1}">
                    <th class="text-center">${action}</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${requestScope.userOrdersActive}">
                <tr>
                    <td id="order${order.id}">${order.id}</td>
                    <td>${order.confirmationDate.toString().substring(0,10)}</td>
                    <td>
                        <c:if test="${user!=null&&user.role==2}">
                            <form>
                                <input id="ready_date${order.id}" type="date" name="calendar" onchange="updateDate(${order.id},this)"
                                       value="${order.readyDate.toString().substring(0,10)}"/>
                            </form>
                        </c:if>
                        <c:if test="${user!=null&&user.role==1}">
                    ${order.readyDate.toString().substring(0,10)}
                        </c:if>
                    </td>
                    <td>${in_process}</td>
                    <td><a href="Controller?command=gotoactiveordersinfopage&orderId=${order.id}">${view}</a></td>
                    <c:if test="${user!=null&&user.role==1}">
                        <td>
                            <a href="Controller?command=deleteorder&id=${order.id}&url=${requestScope.fullUrl}">${cancel}</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="template/footer.jsp"/>
<script src="<c:url value="/js/active_orders.js"/>"></script>
</body>
</html>
