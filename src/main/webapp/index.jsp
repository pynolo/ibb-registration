<%@page import="net.tarine.ibb.business.WizardBusiness"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	WizardBusiness.getParameters(session, request);
%>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Italian Burning Boots</title>
	
	<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet" />
	<link href="css/custom.css" rel="stylesheet" />
	<link rel="shortcut icon" type="image/png" href="images/rm-16.png" />
	
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
	<script type="text/JavaScript">
	function forwardForm0() {
		// Validate Name
		var title = $("#name").val();
		if (title=="" || title==null) { } else {
			alert("Please enter only alphanumeric values for your name / Inserisci solo numeri e lettere per favore");
			return false;
		}
		
		// Validate Email
		var email = $("#email").val();
		if ((/(.+)@(.+){2,}\.(.+){2,}/.test(email)) || email=="" || email==null) { } else {
			alert("Please enter a valid email / Inserisci un'email valida per favore");
			return false;
		}
	
		form.step.value = "1";
		return true;
	}
	</script>
</head>
<body>

	<div class="container">
	<h1 class="text-colored">Italian Burning Boots</h1>
		<div class="panel panel-default">
			<div class="panel-body">
				
				<c:choose>
					<c:when test="${sessionScope.step == 0}">
		    			<%@ include file="jspf/step0.jspf" %>
		    		</c:when>
		    		<c:when test="${sessionScope.step == 1}">
		    			<%@ include file="jspf/step1.jspf" %>
		    		</c:when>
		    		<c:when test="${sessionScope.step == 2}">
		    			<%@ include file="jspf/step2.jspf" %>
		    		</c:when>
		    	</c:choose>
			</div>
		</div>
	</div>
	<!-- /container -->

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="jquery/1.11.1/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>

