<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="add_product.title" var="title"/>
<fmt:message bundle="${loc}" key="add_product.category" var="category"/>
<fmt:message bundle="${loc}" key="add_product.nameEn" var="nameEn"/>
<fmt:message bundle="${loc}" key="add_product.nameRu" var="nameRu"/>
<fmt:message bundle="${loc}" key="add_product.image" var="image"/>
<fmt:message bundle="${loc}" key="add_product.price" var="price"/>
<fmt:message bundle="${loc}" key="url.directory" var="directory"/>
<fmt:message bundle="${loc}" key="add_product.amount" var="amount"/>
<fmt:message bundle="${loc}" key="add_product.nameEn.hint" var="nameEn_hint"/>
<fmt:message bundle="${loc}" key="add_product.nameRu.hint" var="nameRu_hint"/>
<fmt:message bundle="${loc}" key="add_product.empty" var="emptyName"/>
<fmt:message bundle="${loc}" key="add_product.empty_price" var="emptyPrice"/>
<fmt:message bundle="${loc}" key="add_product.file_not_chosen" var="fileNotChosen"/>
<fmt:message bundle="${loc}" key="add_product.invalid.name.en" var="invalidNameEn"/>
<fmt:message bundle="${loc}" key="add_product.invalid.name.ru" var="invalidNameRu"/>
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
<jsp:include page="template/navbar.jsp"/>
<div class="container">
    <div class="wrapper">
        <h2 class="text-center">${title} </h2>
    </div>
    <form action="Controller"  method="POST" id="form" class="form-horizontal" enctype="multipart/form-data">
        <input  type="hidden" name="command"  value="addproduct"/>
        <input type="hidden" id="empty" value="${emptyName}"/>
        <input type="hidden" id="invalidNameEn" value="${invalidNameEn}"/>
        <input type="hidden" id="invalidNameRu" value="${invalidNameRu}"/>
        <input type="hidden" id="hintNameRu" value="${nameRu_hint}"/>
        <input type="hidden" id="hintNameEn" value="${nameEn_hint}"/>
        <input type="hidden" id="emptyPrice" value="${emptyPrice}"/>
        <input type="hidden" id="fileNotChosen" value="${fileNotChosen}"/>
        <div class="form-group">
            <label class="control-label col-sm-offset-2 col-sm-2 required-mark" for="category">${category}</label>
            <div class="col-sm-4">
                <select name="category" id="category" form="form">
                    <c:forEach var="category" items="${requestScope.allCategories}">
                        <c:if test="${sessionScope.locale.toString()=='en'}">
                            <c:set value="${category.name}" var="product_name"/>
                        </c:if>
                        <c:if test="${sessionScope.locale.toString()=='ru'}">
                            <c:set value="${category.nameRu}" var="product_name"/>
                        </c:if>
                    <option value="${category.id}">${category.nameRu}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-offset-2 col-sm-2 required-mark" for="name">${nameEn}</label>
            <div class="col-sm-4">
                <input type="text" class="bg-danger form-control" id="name" name="name" title="${nameEn}"
                       value="" placeholder="${nameEn_hint}"/>
                <i class="fas fa-check-circle"></i>
                <i class="fas fa-exclamation-circle"></i>
                <small>Error message</small>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-offset-2 col-sm-2 required-mark" for="nameRu">${nameRu}</label>
            <div class="col-sm-4">
                <input type="text" class="bg-danger form-control" id="nameRu" name="nameRu" title="${nameRu}"
                        placeholder="${nameRu_hint}"/>
                <i class="fas fa-check-circle"></i>
                <i class="fas fa-exclamation-circle"></i>
                <small>Error message</small>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-offset-2 col-sm-2 required-mark" for="image">${image}</label>
            <div class="col-sm-4">
                <input type="file" class="bg-danger form-control" id="image" name="image"  accept="image/*"/>
                <i class="fas fa-check-circle"></i>
                <i class="fas fa-exclamation-circle"></i>
                <small>Error message</small>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-offset-2 col-sm-2 required-mark" for="price">${price}</label>
            <div class="col-sm-4">
                <input type="number"  min="0" step="any" class="bg-danger form-control" id="price" name="price"/>
                <i class="fas fa-check-circle"></i>
                <i class="fas fa-exclamation-circle"></i>
                <small>Error message</small>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-offset-2 col-sm-2 required-mark" for="price">${amount}</label>
            <div class="col-sm-4">
                <input name="amount" type="number" min="1" value="1"/>
            </div>
        </div>
        <div class="text-center">
            <button class="btn-register btn btn-lg btn-primary" id="register-button" name="submit"
                    type="submit">${title} </button>
        </div>
    </form>
</div>
<c:if test="${param.message!=null}">
<div id="Bar" class="alert alert-danger">
    <div id="right">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    </div>
    <strong id="error-title">
        <c:if test="${param.message=='invalidNameRu'}">
            ${invalidNameRu}
        </c:if>
        <c:if test="${param.message=='invalidNameEn'}">
            ${invalidNameEn}
        </c:if>
        <c:if test="${param.message=='invalidImage'}">
            ${fileNotChosen}
        </c:if>
    </strong>
</c:if>
</body>
<jsp:include page="template/footer.jsp"/>
<script src="<c:url value="/js/add_product.js"/>"></script>
</html>
