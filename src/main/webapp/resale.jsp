<%@page import="net.tarine.ibb.business.ResaleBusiness"%>
<%@page import="java.util.Date"%>
<%@page import="net.tarine.ibb.business.ConfigBusiness"%>
<%@page import="net.tarine.ibb.business.WizardBusiness"%>
<%@page import="net.tarine.ibb.business.ParticipantsBusiness"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="net.tarine.ibb.model.*"%>
<%@ page import="net.tarine.ibb.*"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
ResaleBusiness.saveParametersIfNotEmpty(session, request);
List<Resales> reList = ResaleBusiness.findNonExpired(new Date());
request.setAttribute("reList", reList);
%>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>IBB Resale</title>
		
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

<script language="javascript"> 
function toggle(showHideDiv, switchTextDiv) {
	var ele = document.getElementById(showHideDiv);
	var text = document.getElementById(switchTextDiv);
	if(ele.style.display == "block") {
		ele.style.display = "none";
		text.innerHTML = "Email";
	} else {
		ele.style.display = "block";
		text.innerHTML = "Hide/nascondi";
	}
}

function forwardForm0() {
	var valid = true;	
	// Validate Email
	var email = document.form0.email.value;
	if (!(/(.+)@(.+){2,}\.(.+){2,}/.test(email)) || email=="" || email==null) {
		alert("Please enter a valid email / Inserisci un'email valida per favore");
		valid = false;
	}
	// Validate date
	var expDate = document.form0.expDate.value;
	if (!(/\d{2}\/\d{2}\/\d{4}/.test(expDate)) || expDate=="" || expDate==null) {
		alert("Please enter a valid date / Inserisci una data valida per favore");
		valid = false;
	}
	if (valid) {
		document.form0.submit();
	}
}
</script>

	<div class="container">
		<h1 class="text-colored text-center">Resale / Scambio</h1>
		<div class="panel panel-default">
			<div class="panel-body">
				<table class="table table-condensed" style="border-collapse: collapse">
					<thead>
						<tr>
							<th>
								<b>Action</b>
							</th>
							<th>
								<b>Message</b>
							</th>
							<th>
								<b>Email</b>
							</th>
						</tr>
					</thead>
				
					<tbody>
						<c:forEach items="${requestScope.reList}" var="re" varStatus="status">
						<tr>
							<td>
								<c:choose>
									<c:when test="${re.resaleType == 'SELL'}">
										<span style="color: green; font-weight: bold;">Offering</span>
									</c:when>
									<c:when test="${re.resaleType == 'BUY'}">
										<span style="color: red; font-weight: bold;">Searching</span>
									</c:when>
								</c:choose>
							</td>
							<!--td>
								<c:out value="${re.formattedCreationDate}" />
							</td-->
							<td>
								<c:out value="${re.message}" />
							</td>
							<td>
								<a id="show_${re.id}" href="javascript:toggle('email_${re.id}','show_${re.id}');" >Email</a>
								<div id="email_${re.id}" style="display: none;">
									<img src="emailtoimage?username=${re.emailUsername}&domain=${re.emailDomain }" />
								</div>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<p>&nbsp;</p>
				<table class="table table-condensed" style="border-collapse: collapse">
					<thead>
						<tr>
							<th>
								<b>Add your listing / Aggiungi il tuo annuncio</b>
							</th>
						</tr>
					</thead>
					<tbody>
					<!--tr>
						<td><i>All fields are mandatory</i> / <b>Tutti i campi sono obbligatori</b></td>
					</tr-->
					</tbody>
				</table>
				<form action="resale.jsp" name="form0" method="post">
					<div class="row">
						<div class="col-sm-2"><i>Why are you here?</i><br /><b>Perch&egrave; sei qui?</b></div>
						<div class="col-sm-2">
							<select name="resaleType" class="selectpicker">
								<option value="SELL">I'm offering / Offro</option>
								<option value="BUY">I'm looking for / Cerco</option>
							</select>
						</div>
						<div class="col-sm-1"><i>Message</i><br /><b>Messaggio</b></div>
						<div class="col-sm-7">
							<input name="message" type="text" class="form-control" maxlength="256" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-2"><i>Email</i><br /><b>Email</b></div>
						<div class="col-sm-4">
							<input name="email" type="text" class="form-control" maxlength="64" />
						</div>
						<div class="col-sm-2"><i>Expiration date</i><br/><b>Scadenza annuncio</b></div>
						<div class="col-sm-2">
							<input name="expDate" type="text" class="form-control" maxlength="10" />
						</div>
						<div class="col-sm-2">
							dd/mm/yyyy 
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<div class="input-group">
								<input type="submit" value="Submit / Salva" name="submit"
									onclick="forwardForm0(); return true;"
    								class="btn btn-primary btn-lg" />
    						</div>
						</div>
					</div>
				</form>
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

