<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="net.tarine.ibb.AppConstants"%>
<%@ page import="net.tarine.ibb.business.ConfigBusiness"%>
<%@ page import="net.tarine.ibb.business.WizardBusiness"%>
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
String code = request.getParameter(AppConstants.PARAMS_CODE);
if (code == null) code = "Error!!";
session.setAttribute(AppConstants.PARAMS_CODE, code);
%>
	<div class="container">
	<h1 class="text-colored text-center">Italian Burning Boots</h1>
		<div class="panel panel-default">
			<div class="panel-body">
				<h3>Thank you</h3>

				<div>
					<i>We're happy to have you with us!<br/>
					You will receive important information about the meeting point
					and about the event at the email address you provided.</i>
				</div>
				<div>
					<i>Please take note of your registration code: </i><b><span style="font-size: 1.3em"><%=session.getAttribute("code") %></span></b><br />
					<i><span class="text-warning">You will not be able to join the event without it!</span></i>
				</div>
				<div>
					<i>If you have questions please write to </i><b><%=AppConstants.EVENT_EMAIL %></b>
				</div>
				<p>&nbsp;</p>
				<p>&nbsp;</p>
				
				<h3>Grazie</h3>
				
				<div>
					<b>Siamo felici di averti tra noi!<br />
					Riceverai informazioni importanti sul luogo di incontro e sull'evento
					all'indirizzo email che hai fornito.</b>
				</div>
				<div>
					<b>Prendi nota del tuo codice di registrazione: </b><i><span style="font-size: 1.3em"><%=session.getAttribute("code") %></span></i><br />
					<b><span class="text-warning">Senza il codice non potrai partecipare all'evento!</span></b>
				</div>
				<div>
					<b>Per domande scrivi a </b><i><%=AppConstants.EVENT_EMAIL %></i>
				</div>
				<p>&nbsp;</p>
				<p>&nbsp;</p>
				<div class="row">
					<div class="col-sm-2">
				 		<button type="button" class="btn btn-primary" disabled="true">&nbsp;<i class="glyphicon glyphicon-chevron-left"></i><i class="glyphicon glyphicon-chevron-left"></i> Prev</button>
					</div>
					<div class="col-sm-8"></div>
					<div class="col-sm-2">
						<a href="<%=AppConstants.EVENT_URL %>"><button type="button" class="btn btn-primary">Next <i class="glyphicon glyphicon-chevron-right"></i><i class="glyphicon glyphicon-chevron-right"></i>&nbsp;</button></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /container -->

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="jquery/1.11.1/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap-select.min.js"></script>
</body>
</html>

