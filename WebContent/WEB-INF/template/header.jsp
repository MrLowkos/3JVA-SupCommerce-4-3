<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<div id="header" class="navbar navbar-fixed-top navbar-inverse">
	<div class="container">
		<header class="navbar-header">
			<button class="navbar-toggle collapsed" data-target=".navbar-collapse" data-toggle="collapse" type="button">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value="/"/>">SupCommerce 4.3</a>
		</header>
		<nav class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<c:choose>
					<c:when test="${not empty sessionScope.username}">
						<li class="active"><a href="<c:url value="listProduct"/>"><span class="glyphicon glyphicon-user"></span>&nbsp; <c:out value="${sessionScope.username}"/></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home"></span></a></li>
					</c:otherwise>
				</c:choose>
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">
					<span class="glyphicon glyphicon-wrench"></span>&nbsp; Actions
					<b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value="/listProduct"/>"><span class="glyphicon glyphicon-list"></span>&nbsp; Products</a></li>
						<li><a href="<c:url value="/cheaperProducts"/>"><span class="glyphicon glyphicon-stats"></span>&nbsp; Cheapest</a></li>				
						<c:if test="${not empty sessionScope.username}">
							<li><a href="<c:url value="/auth/basicInsert"/>"><span class="glyphicon glyphicon-random"></span>&nbsp; Rand Product</a></li>
							<li><a href="<c:url value="/auth/addProduct"/>"><span class="glyphicon glyphicon-plus"></span>&nbsp; Product</a></li>
							<li><a href="<c:url value="/auth/addCategory"/>"><span class="glyphicon glyphicon-plus"></span>&nbsp; Category</a></li>
						</c:if>
					</ul>
				</li>				
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${not empty sessionScope.username}">
						<li class="rigth"><a href="<c:url value="/logout"/>"><span class="glyphicon glyphicon-log-out"></span>&nbsp; Logout</a></li>
					</c:when>
					<c:otherwise>
						<li class="rigth"><a href="<c:url value="/login"/>"><span class="glyphicon glyphicon-log-in"></span>&nbsp; Login</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>
	</div>
</div>