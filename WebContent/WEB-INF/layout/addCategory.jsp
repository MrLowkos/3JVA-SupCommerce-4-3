<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
 	<meta charset="UTF-8">
 	<meta content="IE=edge" http-equiv="X-UA-Compatible">
 	<meta content="width=device-width, initial-scale=1.0" name="viewport">
 	<title>AddCategory | SupCommerce</title>
 	<%-- CSS bootstrap 3.0 --%> 
 	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">
 	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">
 	<link rel="stylesheet" href="<c:url value="/css/sticky-footer.css"/>">
 	<link rel="stylesheet" href="<c:url value="/css/style.css"/>">					
</head>
 					
<body>

<div id="wrap">
    
	<%@include file="/WEB-INF/template/header.jsp" %>
	
	<section id="main-container" class="container">
		<c:if test="${not empty newCategoryNeeded}">
			<p class="alert alert-warning fade in">
				<button class="close" data-dismiss="alert" type="button">×</button>
				<span class="glyphicon glyphicon-ok"></span>&nbsp; <c:out value="${newCategoryNeeded}"/>
			</p>
		</c:if>
		<c:if test="${not empty newCategoryName and empty nameError}">
			<p class="alert alert-success fade in">
				<button class="close" data-dismiss="alert" type="button">×</button>
				<span class="glyphicon glyphicon-ok"></span>&nbsp; <c:out value="${newCategoryName}  has been to add to the categories."/>
			</p>
		</c:if>
		<div class="page-header">
			<h1>Add Category <small>Fill category fields</small></h1>
		</div>
		<form class="form-horizontal" action="addCategory" method="post">
		
			<div class="row form-group ${not empty nameError ? 'has-error' : ''}">	
				<div class="form-group input-group col-xs-6">
					<span class="input-group-addon label-fix"><label for="category-name" class="control-label">Name</label></span>
					<input type="text" name="category-name" id="category-name" class="form-control" value="<c:out value="${name}"/>" autofocus="autofocus" required="required"/>
				</div>
				<div class="col-xs-6">
					<c:if test="${not empty nameError}">
						<span class="help-block"><span class="glyphicon glyphicon-warning-sign"></span>&nbsp; <c:out value="${nameError}"/></span>
					</c:if>
				</div>
			</div>
			
			<div class="form-group">
			     <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span>&nbsp; Add</button>
		  	</div>
		
		</form>
		
	</section>
	
	<div id="push"></div>

</div>
	
<jsp:include page="/WEB-INF/template/footer.jsp"/>

</body>
</html>