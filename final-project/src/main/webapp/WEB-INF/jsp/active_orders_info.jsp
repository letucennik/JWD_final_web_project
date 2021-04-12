<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="active_orders.info.title" var="title"/>
<fmt:message bundle="${loc}" key="cart.price" var="price"/>
<fmt:message bundle="${loc}" key="cart.product" var="product"/>
<fmt:message bundle="${loc}" key="cart.quantity" var="quantity"/>
<fmt:message bundle="${loc}" key="cart.total" var="total"/>
<html>
<head>
    <title>${title}</title>
    <jsp:include page="template/header.jsp"/>
    <link rel="stylesheet" href="<c:url value="/css/cart.css"/>" type="text/css">
    <script src="<c:url value="/js/lib/jquery-3.1.1.js"/>"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
</head>
<body>
<jsp:include page="template/navbar.jsp"/>
<div class="small-container cart-page">
<c:set var="overallPrice" value="0"/>
<table>
<c:forEach var="n" items="${requestScope.activeOrders}">
    <c:if test="${sessionScope.locale.toString()=='en'}">
        <c:set value="${n.product.nameEn}" var="product_name"/>
    </c:if>
    <c:if test="${sessionScope.locale.toString()=='ru'}">
        <c:set value="${n.product.nameRu}" var="product_name"/>
    </c:if>
    <c:set var="overallPrice" value="${overallPrice+n.product.price*n.numberOfProducts}"/>
    <tr>
        <th>${product}</th>
        <th>${quantity}</th>
        <th>${price}</th>
    </tr>
    <tr>
        <td>
            <div class="cart-info">
                    <span>
                         <img src="<c:url value="${n.product.picturePath}" />" alt="product-image">
                    </span>
                <div>
                    <p> ${product_name}</p>
                    <small id="fixed_price${n.id}">${n.product.price} $</small>
                </div>
            </div>
        </td>
        <div class="quantity">
            <input class="allId" type="hidden" value="${n.id}"/>
            <td><input type="number" value="${n.numberOfProducts}"
                       readonly></td>
            <td id="${n.id}">${n.product.price*n.numberOfProducts} $</td>
        </div>
    </tr>
</c:forEach>
</table>
    <div class="total-price">
        <table>
            <tr>
                <td>${total}</td>
                <td id="overall_price">${overallPrice} $</td>
            </tr>
        </table>
</div>
</body>
</html>
