<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="delivery.title" var="title"/>
<fmt:message bundle="${loc}" key="delivery.product" var="product"/>
<fmt:message bundle="${loc}" key="delivery.amount" var="amount"/>
<fmt:message bundle="${loc}" key="delivery.row" var="row"/>
<fmt:message bundle="${loc}" key="delivery.products" var="delivered"/>
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
    <c:set var="counter" value="0"/>
    <div class="row">
        <form id="form" class="form-horizontal">
            <input type="hidden" name="command" value="gotomainpage">
            <input type="hidden" name="message" value="saved">
        <table id="table" class="table text-center mt-1 table-bordered">
            <thead>
            <tr>
                <th class="text-center">${product}</th>
                <th class="text-center">${amount}</th>
            </tr>
            </thead>
            <tbody>
            <input id="numberOfProductTypes" type="hidden" value="${requestScope.numberOfProductTypes}"/>
            <input id="deliveryId" type="hidden" value="${requestScope.delivery.id}"/>
                <c:forEach begin="1" end="${requestScope.numberOfProductTypes}" varStatus="loop">
                    <tr>
                        <td>
                            <select class="allProducts"  id="product">
                                <c:forEach var="product" items="${requestScope.allProducts}">
                                    <c:if test="${sessionScope.locale.toString()=='en'}">
                                        <c:set value="${product.nameEn}" var="product_name"/>
                                    </c:if>
                                    <c:if test="${sessionScope.locale.toString()=='ru'}">
                                        <c:set value="${product.nameRu}" var="product_name"/>
                                    </c:if>
                                    <option value="${product.id}" >${product_name}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input class="allAmounts" id="amount${product.id}" type="number" min="1" value="1" required/>
                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
        <div class="text-center">
            <button class="btn-register btn btn-lg btn-primary" id="register-button" name="submit"
                    type="submit">${title} </button>
        </div>
        </form>
    </div>
</div>
<jsp:include page="template/footer.jsp"/>
<script src="<c:url value="/js/delivery.js"/>"></script>
</body>
</html>
