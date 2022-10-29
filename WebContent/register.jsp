<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>StreamGO - Registrazione</title>
<link rel="shrtcut icon" type="image.png" href="img/streamgo.png">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="<%=request.getContextPath()%>/css/default.css"
	type="text/css" rel="stylesheet">
<link href="css/register.css" type="text/css" rel="stylesheet">

</head>
<body>
	<img src="img/streamgo.jpg" alt="impossibile caricare l'immagine"
		width="200" height="150" class="logo">
	<nav id="navigationBar">
		<ul>
			<li><a href="login.jsp"><i
					class='fas fa-sign-in-alt'></i>Torna al login</a></li> |
			<li><a href="register.jsp"><i class='far fa-address-card'></i>Registrati</a></li>
		</ul>
	</nav>
	<div id="errorsList"></div>
	<form class="form" action="<%=response.encodeURL("Register")%>"
		method="post" id="registerForm"
		onsubmit="event.preventDefault(); validate(this)">
		<input type="hidden" name="action" value="update">
		<div class="form-group">

			<div class="col-sm-12 col-md-6">
				<label for="username"><h4>Username</h4></label> <input type="text"
					class="form-control" name="username" id="username"
					placeholder="username" title="inserisci il tuo username" required>
			</div>
		</div>
		<div class="form-group">

			<div class="col-sm-12 col-md-6">
				<label for="password"><h4>Password</h4></label> <input
					type="password" class="form-control" name="password" id="password"
					placeholder="password" title="inserisci la tua password" required>
			</div>
		</div>

		<div class="form-group">

			<div class="col-sm-12 col-md-6">
				<label for="password"><h4>Conferma Password</h4></label> <input
					type="password" class="form-control" name="confermaPassword"
					id="confermaPassword" placeholder="conferma la password"
					title="conferma la tua nuova password" required>
			</div>
		</div>

		<div class="form-group">

			<div class="col-sm-12 col-md-6">
				<label for="nome"><h4>Nome</h4></label> <input type="text"
					class="form-control" name="nome" id="nome" placeholder="nome"
					title="inserisci il tuo nome" required>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-12 col-md-6">
				<label for="nome"><h4>Cognome</h4></label> <input type="text"
					class="form-control" name="cognome" id="cognome"
					placeholder="cognome" title="inserisci il tuo cognome" required>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-12 col-md-6">
				<div id="phones">
					<label for="number"><h4>Numero di telefono</h4></label> <input
						class="form-control" type="text" name="number"
						placeholder="0123456789"
						title="inserisci il tuo numero di telefono"> <input
						type="button" value="+" onclick="addPhone()">
				</div>
			</div>
		</div>

		<div class="form-group">
			<div class="col-xs-12">
				<br>
				<button class="btn btn-lg btn-success" type="submit">
					<i class="glyphicon glyphicon-ok-sign"></i> Registrati
				</button>
				<button class="btn btn-lg" type="reset">
					<i class="glyphicon glyphicon-repeat"></i> Reset
				</button>
			</div>
		</div>
	</form>
	
	<script src='https://kit.fontawesome.com/a076d05399.js'></script>
	<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="js/register.js"></script>
</body>
</html>