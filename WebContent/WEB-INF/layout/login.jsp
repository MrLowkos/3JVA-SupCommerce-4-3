<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta content="IE=edge" http-equiv="X-UA-Compatible">
	<meta content="width=device-width, initial-scale=1.0" name="viewport">
	<title>Login | SupCommerce</title>
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
		<form class="form-signin" action="login" method="post">
			<div class="panel panel-default">
				<div class="panel-body">			
			<h2 class="form-signin-heading">Please login</h2>
			<input class="form-control" type="text" name="username" id="username" autofocus="autofocus" required="required" placeholder="Username">		
			<button class="btn btn-lg btn-primary btn-block" type="submit"><span class="glyphicon glyphicon-log-in"></span>&nbsp; LogIn</button>
			</div>
			</div>
		</form>		
	</section>
	
	<div id="push"></div>
	
</div>
						
<%@include file="/WEB-INF/template/footer.jsp" %>

</body>					
</html>