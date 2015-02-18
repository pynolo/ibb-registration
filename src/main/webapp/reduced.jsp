<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="net.tarine.ibb.business.WizardBusiness"%>
<%@ page import="net.tarine.ibb.business.ConfigBusiness"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Italian Burning Boots</title>
	
	<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet" />
	<link href="css/bootstrap-select.min.css" rel="stylesheet" />
	<link href="css/custom.css" rel="stylesheet" />
	<link rel="shortcut icon" type="image/png" href="images/rm-16.png" />
	
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
<%
WizardBusiness.getParameters(session, request);
String amount = ConfigBusiness.findValueByName(AppConstants.CONFIG_PRICE_REDUCED_TICKET);
if (amount != null) {
	session.setAttribute(AppConstants.PARAMS_AMOUNT, amount);
}
%>
	<div class="container">
	<h1 class="text-colored text-center">Italian Burning Boots</h1>
		<div class="panel panel-default">
			<div class="panel-body">
				<%@ include file="jspf/step0.jspf" %>
			</div>
		</div>
	</div>
	<!-- /container -->
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="jquery/1.11.1/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap-select.min.js"></script>
	
	<%=AppConstants.TRACKER %>
</body>
</html>

