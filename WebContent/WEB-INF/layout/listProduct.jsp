<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<c:set var="errorMessage" value="No product registered !"/>

<!DOCTYPE html>
<html lang="en">
 				
<head>
 	<meta charset="UTF-8">
 	<meta content="IE=edge" http-equiv="X-UA-Compatible">
 	<meta content="width=device-width, initial-scale=1.0" name="viewport">
 	<title>ListProduct | SupCommerce</title>
 	<%-- CSS --%> 
 	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">
 	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">
 	<link rel="stylesheet" href="<c:url value="/css/sticky-footer.css" />">
 	<link rel="stylesheet" href="<c:url value="/css/style.css" />">					
</head>
 					
<body>

<div id="wrap">	

	<jsp:include page="/WEB-INF/template/header.jsp"/>
	
	<section id="main-container" class="container">
		<div class="page-header">
			<h1>Product List</h1>
		</div>
		<c:choose>
			<c:when test="${empty products}">
				<p class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span>&nbsp; ${errorMessage}</p>
			</c:when>
			<c:otherwise>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th><span class="badge">Id</span></th>
							<th>Name</th>
							<th>Description</th>
							<th>Price</th>
							<th>Category</th>
							<c:if test="${not empty sessionScope.username}">
								<th><span class="glyphicon glyphicon-wrench"></span>&nbsp; Actions</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${products}" var="p">
							<tr>							
								<td><a href="<c:url value="/showProduct?id=${p.id}"/>"><span class="badge"><c:out value="${p.id}"/></span></a></td>
								<td class="text-muted"><c:out value="${p.name}"/></td>
								<td class="text-muted"><c:out value="${p.content}"/></td>
								<td class="text-muted"><fmt:formatNumber value="${p.price}" type="currency"/></td>
								<td class="text-muted"><c:out value="${p.category.name}"/></td>
								<c:if test="${not empty sessionScope.username}">
									<td>
										<form id="remove-button-${p.id}" action="<c:url value="/auth/removeProduct?id=${p.id}"/>" method="post">
											<button type="submit" class="close" form="remove-button-${p.id}"><span class="glyphicon glyphicon-remove"></span></button>
										</form>
										<a href="<c:url value="/showProduct?id=${p.id}"/>"><button type="button" class="close" form="show-button-${p.id}"><span class="glyphicon glyphicon-eye-open"></span></button></a>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</section>
	
	<div id="push"></div>
	
</div>	
	 
<jsp:include page="/WEB-INF/template/footer.jsp"/>

</body>
</html>