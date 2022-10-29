<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*, model.*, control.Profilo"%>
<%
	UtenteBean utente = (UtenteBean) request.getAttribute("utente");
	
	String error = (String) request.getAttribute("error");

	if (utente == null && error == null) {
		response.sendRedirect(response.encodeRedirectURL("/ProgettoTSW/Profilo"));
		return;
	}
	System.out.println(utente.getTelefoni());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>StreamGO - Profilo</title>
<link rel="shrtcut icon" type="image.png" href="img/streamgo.png">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link href="<%=request.getContextPath()%>/css/default.css"
	type="text/css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/profilo.css"
	type="text/css" rel="stylesheet">

</head>
<body>
	<hr>
	<img src="img/streamgo.jpg" alt="impossibile caricare l'immagine"
		width="200" height="150" class="logo rotate">
	<center>
	<a href="authFilter/homepage.jsp" class="navHomepage"><i
			class="glyphicon glyphicon-home"></i>Homepage</a><a href="Logout"
			class="navLogout"><i class="glyphicon glyphicon-ban-circle"></i>Logout</a>
	</center>
	<div class="container">
		<div class="row">
			<div class="col-sm-10">
				<h1><%=utente.getUsername()%></h1>
			</div>

		</div>
		<div class="row">
			<div class="col-sm-3">

				<ul class="list-group">
					<li class="list-group-item" style="background-color: #98878F"><i
						class="fa fa-dashboard fa-1x" style="background-color: #98878F">Attività</i></li>
					<li class="list-group-item text-right"><span class="pull-left"><strong>Recensioni
								rilasciate</strong></span> <%=utente.countRecensioni()%></li>
					<li class="list-group-item text-right"><span class="pull-left"><strong>Media
								recensioni</strong></span> <%=String.format("%.2f", utente.mediaRecensioni())%></li>
				</ul>

			</div>
			<div class="col-sm-9">
				<ul class="nav nav-tabs">
					<li class="active"><a data-toggle="tab" href="#info">Informazioni</a></li>
					<li><a data-toggle="tab" href="#edit">Modifica
							informazioni</a></li>
					<li><a data-toggle="tab" href="#recensioni">Recensioni</a></li>
				</ul>

				<div class="tab-content">
					<div class="tab-pane active" id="info">
						<hr>
						<div class="col-md-12">
							<table>
								<tr>
									<td><b>Username</b></td>
									<td><%=utente.getUsername()%></td>
								</tr>
								<tr>
									<td><b>Nome</b></td>
									<td><%=utente.getNome()%></td>
								</tr>
								<tr>
									<td><b>Cognome</b></td>
									<td><%=utente.getCognome()%></td>
								</tr>
								<tr>
									<td><b>Ruolo</b></td>
									<td><%=utente.isAdmin() ? "Amministratore" : "Utente"%></td>
								</tr>
								<%
									ArrayList<String> telefoni = utente.getTelefoni();
									for (int i = 0; i < telefoni.size(); i++) {
								%>
								<tr>
									<td><b>Telefono <%=i+1%> <a style="float: right"
											href="<%=response.encodeURL("Profilo?action=deleteNumber&numero=" + telefoni.get(i))%>">Rimuovi</a></b></td>
									<td><%=telefoni.get(i)%></td>
								</tr>
								<%
									}
								%>
							</table>
						</div>
						<hr>

					</div>

					<div class="tab-pane" id="edit">

						<h2></h2>

						<hr>
						<div id="errorsList"></div>
						<form class="form"
							action="<%=response.encodeURL("/ProgettoTSW/Profilo")%>"
							method="post" id="editForm"
							onsubmit="event.preventDefault(); validate(this);">
							<input type="hidden" name="action" value="update">
							<div class="form-group">

								<div class="col-sm-12 col-md-6">
									<label for="username"><h4>Nuovo Username</h4></label> <input
										type="text" class="form-control" name="username" id="username"
										placeholder="username"
										title="inserisci lil tuo nuova username"
										value="<%=utente.getUsername()%>">
								</div>
							</div>
							<div class="form-group">

								<div class="col-sm-12 col-md-6">
									<label for="password"><h4>Nuova Password</h4></label> <input
										type="password" class="form-control" name="password"
										id="password" placeholder="password"
										title="inserisci la tua nuova password">
								</div>
							</div>

							<div class="form-group">

								<div class="col-sm-12 col-md-6">
									<label for="password"><h4>Conferma Password</h4></label> <input
										type="password" class="form-control" name="confermaPassword"
										id="confermaPassword" placeholder="conferma la password"
										title="conferma la tua nuova password">
								</div>
							</div>

							<div class="form-group">

								<div class="col-sm-12 col-md-6">
									<label for="nome"><h4>Nome</h4></label> <input type="text"
										class="form-control" name="nome" id="nome" placeholder="nome"
										title="inserisci il tuo nome" value="<%=utente.getNome()%>">
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-12 col-md-6">
									<label for="nome"><h4>Cognome</h4></label> <input type="text"
										class="form-control" name="cognome" id="cognome"
										placeholder="cognome" title="inserisci il tuo cognome"
										value="<%=utente.getCognome()%>">
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
										<i class="glyphicon glyphicon-ok-sign"></i> Salva
									</button>
									<button class="btn btn-lg" type="reset">
										<i class="glyphicon glyphicon-repeat"></i> Reset
									</button>
								</div>
							</div>
						</form>
					</div>

					<div class="tab-pane" id="recensioni">
						<%
							ArrayList<RecensioneBean> recensioni = utente.getRecensioni();
							if (recensioni.size() > 0) {
								for (RecensioneBean r : recensioni) {
						%>
						<br>
						<div class="card text-center" id="cardRecensioni">
							<br>
							<div class="card-header"><%=r.getNomeProdotto()%></div>
							<hr>
							<div class="card-body">
								<h5 class="card-title">
									<%
										for (int i = 1; i <= 5; i++) {
													if (i <= r.getVoto()) {
									%>
									<i class="glyphicon glyphicon-star"></i>
									<%
										} else {
									%>
									<i class="glyphicon glyphicon-star-empty"></i>
									<%
										}
												}
									%>
								</h5>
								<%
									if (r.getDescrizione() != null) {
								%>
								<p class="card-text"><%=r.getDescrizione()%></p>
								<%
									}
								%>
							</div>
						</div>
						<%
							}
							} else {
						%>
						<%
							}
						%>
						<hr>

					</div>

				</div>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="js/profilo.js"></script>
</body>
</html>