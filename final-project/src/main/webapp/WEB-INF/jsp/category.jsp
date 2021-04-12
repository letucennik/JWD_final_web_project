<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="category.add_to_cart" var="addToCart"/>
<fmt:message bundle="${loc}" key="category.at_store" var="atStore"/>
<fmt:message bundle="${loc}" key="category.delete_product" var="deleteProduct"/>
<html>
<head>
    <c:if test="${sessionScope.locale.toString()=='en'}">
        <c:set value="${requestScope.category.name}" var="category_name"/>
    </c:if>
    <c:if test="${sessionScope.locale.toString()=='ru'}">
        <c:set value="${requestScope.category.nameRu}" var="category_name"/>
    </c:if>
    <title>${category_name}</title>
    <jsp:include page="template/header.jsp"/>
    <jsp:include page="template/navbar.jsp"/>
    <script src="https://kit.fontawesome.com/dbed6b6114.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<c:url value="/css/category.css"/>" type="text/css">
</head>
<body>
<div class="products">
    <div class="container">
        <h1 class="lg-title"> ${category_name} </h1>
        <div class="product-items">
<c:forEach var="n" items="${requestScope.productsByCategory}">

    <c:if test="${sessionScope.locale.toString()=='en'}">
        <c:set value="${n.nameEn}" var="product_name"/>
    </c:if>
    <c:if test="${sessionScope.locale.toString()=='ru'}">
        <c:set value="${n.nameRu}" var="product_name"/>
    </c:if>
    <c:set var="user" value="${sessionScope.user}"/>
            <div class="product">
                <div class="product-content">
                    <div class="product-img">
                        <span>
                        <img src="<c:url value="${n.picturePath}" />" alt="product-image">
                            </span>
                    </div>
                    <div class="product-btns">
                        <c:if test="${user!=null&&user.role==1}">
                        <button type="button" class="btn-cart">
                                <a href="Controller?command=saveneworder&url=${requestScope.fullUrl}&product=${n.id}">
                                ${addToCart}
                                </a>
                            <span><i class="fas fa-plus"></i> </span>
                        </button>
                        </c:if>
                        <c:if test="${user!=null&&user.role==2}">
                            <button id="delete" type="button" class="btn-cart">
                                <a id="link" href="Controller?command=deleteproduct&id=${n.id}&url=${requestScope.fullUrl}">
                                ${deleteProduct}
                                </a>
                                <span><i class="fas fa-trash"></i> </span>
                            </button>
                            <button type="button" class="btn-cart">
                                <h3>${atStore} ${n.numberOfProducts}</h3>
                            </button>
                        </c:if>
                    </div>
                </div>
                <div class="product-info">
                    <a href="#" class="product-name"> ${product_name}</a>
                    <p class="product-price">${n.price} $</p>
                </div>
            </div>
</c:forEach>
    </div>
</div>
</div>

<jsp:include page="template/footer.jsp"/>
<script src="<c:url value="/js/category.js"/>"></script>
</body>
</html>
