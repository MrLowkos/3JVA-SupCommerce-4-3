<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta content="IE=edge" http-equiv="X-UA-Compatible">
	<meta content="width=device-width, initial-scale=1.0" name="viewport">
	<title>Welcome |SupCommerce</title>
	<!-- CSS bootstrap 3.0 --> 
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="<c:url value="/css/sticky-footer.css"/>">
	<link rel="stylesheet" href="<c:url value="/css/style.css"/>">					
</head>
					
<body>

<div id="wrap">	
			
	<%@include file="/WEB-INF/template/header.jsp" %>
	
	<section id="main-container" class="container">
	
			<div class="jumbotron">
				<h1>Welcome <c:choose>
								<c:when test="${not empty sessionScope.username}">
									<c:out value="${sessionScope.username},<br/>enjoy " escapeXml="false"/>
								</c:when>
								<c:otherwise>
									<c:out value="to "/>
								</c:otherwise>
							</c:choose>SupCommerce<br/>
				<small>A product manager site.</small></h1>
			</div>
			
	</section>
	
	<div id="push"></div>
	
</div>		
			
<%@include file="/WEB-INF/template/footer.jsp" %>

</body>			
</html>