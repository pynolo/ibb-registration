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
	<title>Thank you!</title>
	
	<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet" />
	<link href="css/bootstrap-select.min.css" rel="stylesheet" />
	<link href="css/custom.css" rel="stylesheet" />
	<link rel="shortcut icon" type="image/png" href="images/favicon.png" />
	
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
<%
String email = request.getParameter(AppConstants.PARAMS_EMAIL);
String code = request.getParameter(AppConstants.PARAMS_CODE);
if (code == null || email == null) { 
	code = "Error in registration code or email!!";
} else {
	session.setAttribute(AppConstants.PARAMS_CODE, code);
	//MailBusiness.sendConfirmEmail(email, code);
}
%>
	<div class="container">
		<h1 class="text-colored text-center">Italian Burning Boots</h1>
		<div class="panel panel-default">
			<div class="panel-body">
				<h3>Thank you</h3>

				<div class="text-center">
					<i>IMPORTANT: Please take note of your registration code: </i>
					<div class="row">
						<div class="col-md-2 col-md-offset-5"
							style="border: 2px black dotted; padding: 0.5em; font-size: 1.9em; font-weight: bold;">
							<%=code %>
						</div>
					</div>
					<span class="text-warning">Print this page: the code is necessary to <b>join the event</b> or to <b>change</b> the participant's name!</span><br />
				</div>
				
				<p>
					<i>We're happy to have you with us!<br/>
					You will receive important information about the event location 
					on the email address you provided.</i>
				</p>
				<p>
					<i>If you have questions please write to </i><b><%=AppConstants.EVENT_EMAIL %></b>
				</p>
				<p>&nbsp;</p>
				
				<h3>Grazie</h3>
				
				
				<div class="text-center">
					<b>IMPORTANTE: prendi nota del tuo codice di registrazione: </b>
					<div class="row">
						<div class="col-md-2 col-md-offset-5"
							style="border: 2px black dotted; padding: 0.5em; font-size: 1.9em; font-weight: bold;">
							<%=code %>
						</div>
					</div>
					<span class="text-warning">Stampa la pagina: senza il codice non potrai <b>partecipare</b> o <b>cambiare</b> il nominativo del partecipante!</span><br />
				</div>
				<p>
					<b>Siamo felici di averti tra noi!<br />
					Riceverai informazioni importanti sul luogo dell'evento
					all'indirizzo email che hai fornito.</b>
				</p>
				<p>
					<b>Per domande scrivi a </b><i><%=AppConstants.EVENT_EMAIL %></i>
				</p>
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
	
	<%=AppConstants.TRACKER %>
</body>
</html>

