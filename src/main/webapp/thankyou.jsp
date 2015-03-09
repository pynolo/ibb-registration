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
String code = request.getParameter(AppConstants.PARAMS_CODE);
if (code == null) { 
	code = "Error: registration code is missing!";
} else {
	session.setAttribute(AppConstants.PARAMS_CODE, code);
}
%>
	<div class="container">
		<h1 class="text-colored text-center">Italian Burning Boots</h1>
		<div class="panel panel-default">
			<div class="panel-body">
				<h3>We're happy to have you with us!</h3>

				<div class="text-center">
					<i>Your registration code is CONFIRMED. Print it or write it down!</i>
					<div class="row">
						<div class="col-md-2 col-md-offset-5"
							style="border: 2px black dotted; padding: 0.5em; font-size: 1.9em; font-weight: bold;">
							<%=code %>
						</div>
					</div>
				</div>
				<p>
					<i><span class="text-warning">SUPER IMPORTANT - How to use the code:</span><br/>
					&bull; Keep it private and use it <span class="text-warning">to enter the event</span>,it works like a ticket!<br />
					&bull; Give it to someone else <span class="text-warning">to let her/him replace your registration data</span> to take your place
					(<a href="transfer.jsp">link</a>)</i>
				</p>
				<p>
					<i>You will receive important information 
					about the event location on the email address you provided. If you have 
					questions please write to </i><b><%=AppConstants.EVENT_EMAIL %></b>
				</p>
				<p>&nbsp;</p>
				
				<h3>Siamo felici di averti tra noi!</h3>
				
				<div class="text-center">
					<b>IMPORTANTE: Il tuo codice &egrave; CONFERMATO. Stampalo o prendi nota!</b>
					<div class="row">
						<div class="col-md-2 col-md-offset-5"
							style="border: 2px black dotted; padding: 0.5em; font-size: 1.9em; font-weight: bold;">
							<%=code %>
						</div>
					</div>
				</div>
				<p>
					<b><span class="text-warning">IMPORTANTISSIMO - come usare il codice:</span><br />
					&bull; Non comunicarlo ad altri e <span class="text-warning">portalo all'evento per entrare</span>, funziona come un biglietto!<br />
					&bull; Dallo a un'altra persona, <span class="text-warning">se vuoi che sostituisca i suoi dati ai tuoi</span> per prendere il tuo posto
					(<a href="transfer.jsp">link</a>)</b>
				</p>
				<p>
					<b>Riceverai informazioni importanti sul luogo 
					dell'evento all'indirizzo email che hai fornito. Per domande scrivi
					a </b><i><%=AppConstants.EVENT_EMAIL %></i>
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

