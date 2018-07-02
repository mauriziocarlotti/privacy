<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />

<c:set var="contextRoot" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="Maurizio Carlotti">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">


<title>PrivacyTrack - ${title}</title>

<script>
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}'
</script>
<!-- Bootstrap Core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="${css}/shop-homepage.css" rel="stylesheet">
<link href="${css}/myapp.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

<!-- Custom CSS -->

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<div class="se-pre-con"></div>
	<div class="wrapper">

		<!-- Navigation -->
		<%@include file="./shared/navbar.jsp"%>

		<!-- Page Content -->
		<div class="content">
			<c:if test="${userClickHome == true }">
				<%@include file="home.jsp"%>
			</c:if>

			<c:if test="${userClickAbout == true }">
				<%@include file="about.jsp"%>
			</c:if>

			<c:if test="${userClickContact == true }">
				<%@include file="contact.jsp"%>
			</c:if>

			<c:if
				test="${userClickAllCustomers == true or userClickCategoryCustomers == true}">
				<%@include file="listCustomers.jsp"%>
			</c:if>

			<c:if
				test="${userClickShowCustomer == true}">
				<%@include file="singleCustomer.jsp"%>
			</c:if>

			<c:if
				test="${userClickManageCustomers == true}">
				<%@include file="manageCustomers.jsp"%>
			</c:if>

		</div>
		<!-- /.container -->

		<!-- Footer bar -->
		<%@include file="./shared/footer.jsp"%>

		<!-- jQuery -->
		<script src="${js}/jquery.js"></script>

		<!-- jQuery Validate -->
		<script src="${js}/jquery.validate.js"></script>
		<script src="${js}/jquery.validate.min.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script src="${js}/bootstrap.min.js"></script>

		<!-- DataTable plugins -->
		<script src="${js}/jquery.dataTables.js"></script>
		
		<!-- DataTable Bootstrap plugins -->
		<script src="${js}/dataTables.bootstrap.js"></script>
		
		<!-- Bootbox plugins -->
		<script src="${js}/bootbox.min.js"></script>
		
		<!-- Self coded javascript -->
		<script src="${js}/myapp.js"></script>

	</div>
</body>

</html>
