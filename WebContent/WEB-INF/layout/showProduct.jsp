<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<!DOCTYPE html>
<html lang="en">
 				
<head>
 	<meta charset="UTF-8">
 	<meta content="IE=edge" http-equiv="X-UA-Compatible">
 	<meta content="width=device-width, initial-scale=1.0" name="viewport">
 	<title>ShowProduct | SupCommerce</title>
 	<%-- CSS --%> 
 	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">
 	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">
 	<link rel="stylesheet" href="<c:url value="/css/sticky-footer.css"/>">
 	<link rel="stylesheet" href="<c:url value="/css/style.css"/>">				
</head>
 					
<body>

<div id="wrap">	

	<%@include file="/WEB-INF/template/header.jsp" %>
	
	<section id="main-container" class="container">
			<div class="page-header">
				<h1>Product Details</h1>
			</div>
			<c:choose>
				<c:when test="${not empty error}">
					<p class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span>&nbsp; <c:out value="${error}"/></p>
				</c:when>
				<c:otherwise>			
					<ul class="list-group panel panel-primary">
						<li class="list-group-item panel-heading">
							<c:if test="${not empty sessionScope.username}">
								<form action="<c:url value="/auth/removeProduct?id=${product.id}"/>" method="post">
									<button type="submit" class="close" >&times;</button>
								</form>
							</c:if>
							<span class="glyphicon glyphicon-tag"></span>&nbsp; <c:out value="${product.id}"/>
						</li>
						<li class="list-group-item">Name: <span class="text-muted"><c:out value="${product.name}"/></span></li>
						<li class="list-group-item">Description: <span class="text-muted"><c:out value="${product.content}"/></span></li>
						<li class="list-group-item">Price: <span class="text-muted"><fmt:formatNumber value="${product.price}" /></span></li>
						<li class="list-group-item">Category: <span class="text-muted"><c:out value="${product.category.name}" /></span></li>
					</ul>			    
			</c:otherwise>
		</c:choose>	
	</section>
	
	<div id="push"></div>
	
</div>
	 
<%@include file="/WEB-INF/template/footer.jsp" %>

</body>
</html>