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
	<link rel="shortcut icon" type="image/png" href="images/rm-16.png" />
	
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>

	<div class="container">
		<h1 class="text-colored text-center">Resale / Scambio</h1>
		<div class="panel panel-default">
			<div class="panel-body">
				<table class="table table-condensed" style="border-collapse: collapse">
					<thead>
						<tr>
							<th>
								<b></b>
							</th>
							<th>
								<b>Added</b>
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
						<c:forEach items="${reScope.pList}" var="re" varStatus="status">
						<tr>
							<td>
								<c:choose>
									<c:when test="${re.resaleType == 'SELL'}">
										<b>Selling</b>
									</c:when>
									<c:when test="${re.resaleType == 'LOOK'}">
										<i>Looking for</i>
									</c:when>
								</c:choose>
							</td>
							<td>
								<c:out value="${re.formattedCreationDate}" />
							</td>
							<td>
								<c:out value="${re.message}" />
							</td>
							<td>
								<img src="emailtoimage?username=${re.emailUsername}&domain=${re.emailDomain }" />
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>

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

