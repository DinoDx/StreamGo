<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*, model.UtenteBean,control.Login"%>
<%
	String error = (String) session.getAttribute("loginError");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>StreamGO - Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shrtcut icon" type="image.png" href="img/streamgo.png">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="css/login.css" type="text/css" rel="stylesheet">

</head>
<body>
	<img src="img/streamgo.jpg" alt="impossibile caricare l'immagine"
		width="300" height="200" class="rotate">
	<form class="form" action="<%=response.encodeURL("Login")%>"
		method="post" id="grad">
	
		<label for="accedi"><h3>Accedi:</h3></label><br> <input
			type="hidden" name="action" value="login">

		<% if(error != null) {%>
			<div id="errorsList" style="color: red; font-weight: bold"><%=error %></div>
			<%session.removeAttribute("loginError"); %>
		<%} %>

		<div class="form-group">

			<div class="col-sm-12 col-md-12">
				<label for="username"><h4>Username</h4></label> <input type="text"
					class="form-control" name="username" id="username" maxlength="15"
					placeholder="username" title="inserisci il tuo username" required>
			</div>
		</div>

		<div class="form-group">

			<div class="col-sm-12 col-md-12">
				<label for="password"><h4>Password</h4></label> <input
					type="password" class="form-control" name="password" id="password"
					maxlength="15" placeholder="password"
					title="inserisci la tua password" required>
			</div>
		</div>
		<div class="form-group">
			<div class="col-xs-12">
				<br>
				<button class="btn btn-md btn-success" type="submit">
					<i class="glyphicon glyphicon-ok-sign"></i> Accedi
				</button>
				<button class="btn btn-md" type="reset">
					<i class="glyphicon glyphicon-repeat"></i> Reset
				</button>
			</div>
		</div>
		<div class="card-footer">
			<div class="d-flex justify-content-center links">
				<p><br>
					Prima volta su StreamGo? <br><a href="register.jsp">Registrati</a><br>oppure<br>
					<a href="catalogo.jsp">Sfoglia il catalogo</a>
				</p>
			</div>
		</div>
		<br>
		<p class="mt-5 mb-3 text-muted text-center">&copy; 2019-2020</p>

	</form>
</body>
</html>