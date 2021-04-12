<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="cart.title" var="title"/>
<fmt:message bundle="${loc}" key="cart.price" var="price"/>
<fmt:message bundle="${loc}" key="cart.product" var="product"/>
<fmt:message bundle="${loc}" key="cart.quantity" var="quantity"/>
<fmt:message bundle="${loc}" key="cart.remove" var="remove"/>
<fmt:message bundle="${loc}" key="cart.confirm" var="confirm"/>
<fmt:message bundle="${loc}" key="cart.total" var="total"/>
<fmt:message bundle="${loc}" key="cart.overflow_amount" var="amount"/>
<fmt:message bundle="${loc}" key="cart.overflow_name" var="name"/>
<fmt:message bundle="${loc}" key="user_details.success" var="success"/>
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
<c:if test="${param.message=='insertedUserDetails'}">
    <div class="login-hint alert alert-success">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${success}
    </div>
</c:if>
<c:if test="${param.message=='overflow'}">
    <div class="login-hint alert alert-danger">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${amount} ${sessionScope.availableProductAmount} ${name} "${sessionScope.productWithOverflow.nameRu}"
    </div>
</c:if>

<div class="small-container cart-page">
    <c:set var="overallPrice" value="0"/>
    <c:set var="hasUserDetailsInserted" value="${sessionScope.user.userDetails!=null}"/>
    <table>
        <input id="hasUserDetails" type="hidden" value="${hasUserDetailsInserted}"/>
        <c:forEach var="n" items="${requestScope.userOrdersInCart}">
            <c:if test="${sessionScope.locale.toString()=='en'}">
                <c:set value="${n.product.nameEn}" var="product_name"/>
            </c:if>
            <c:if test="${sessionScope.locale.toString()=='ru'}">
                <c:set value="${n.product.nameRu}" var="product_name"/>
            </c:if>
            <c:set var="overallPrice" value="${overallPrice+n.product.price}"/>
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
                            <br>
                            <a href="Controller?command=deleteorderitem&id=${n.id}&url=${requestScope.fullUrl}">${remove}</a>
                        </div>
                    </div>

                </td>
                <div class="quantity">
                    <input class="allId" type="hidden" value="${n.id}"/>
                    <td><input id="quantity${n.id}" type="number" min="1" value="${n.numberOfProducts}"
                               onchange="updateQuantity(${n.id},this)"/></td>
                    <td id="${n.id}">${n.product.price} $</td>
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
        <c:if test="${requestScope.userOrdersInCart.size()>0}">
            <a href="Controller?command=confirmorder" style="color: aliceblue">
                <button id="confirmButton" class="btn-register btn btn-lg btn-primary" type="submit" name="submit">
                        ${confirm}</button>
            </a>
        </c:if>
    </div>
</div>
<jsp:include page="template/footer.jsp"/>
<script src="<c:url value="/js/cart.js"/>"></script>
</body>
</html>
