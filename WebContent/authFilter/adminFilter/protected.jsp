<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*, model.*, control.Protected"%>

<%
	ArrayList<UtenteBean> utenti = (ArrayList<UtenteBean>) request.getAttribute("utenti");
	String error = (String) request.getAttribute("error");

	System.out.println("UTENTI = " + utenti);
	if (utenti == null && error == null) {
		response.sendRedirect(response.encodeRedirectURL("/ProgettoTSW/Protected"));
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>StreamGO - Protected</title>
<link rel="shrtcut icon" type="image.png" href="img/streamgo.png">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link href="<%=request.getContextPath()%>/css/default.css"
	type="text/css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/protected.css"
	type="text/css" rel="stylesheet">
</head>
<body>
	<hr>
	<img src="<%=request.getContextPath()%>/img/streamgo.jpg"
		alt="impossibile caricare l'immagine" width="200" height="150"
		class="logo rotate">
	<a href="authFilter/homepage.jsp" class="navHomepage"><i
		class="glyphicon glyphicon-home"></i>Homepage</a>
	<a href="Logout" class="navLogout"><i
		class="glyphicon glyphicon-ban-circle"></i>Logout</a>

	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#prodotti">Gestione
				Prodotti</a></li>
		<li><a data-toggle="tab" href="#utenti">Gestione Utenti</a></li>
	</ul>

	<div class="tab-content">

		<div class="tab-pane active" id="prodotti">
			<br>
			<div id="errorsList"></div>
			<form class="form"
				action="<%=response.encodeURL("/ProgettoTSW/Protected")%>"
				method="post" id="registerForm"
				onsubmit="event.preventDefault(); validate(this)">
				<fieldset>
				<legend>Aggiungi un nuovo prodotto</legend>
				<input type="hidden" name="action" value="aggiungi">
				<div class="form-group">

					<div class="col-sm-12 col-md-6">
						<label for="nome"><h4>Nome del prodotto</h4></label> <input
							type="text" class="form-control" name="nome" id="nome"
							placeholder="nome prodotto"
							title="inserisci il nome del prodotto" required>
					</div>
				</div>
				<div class="form-group">

					<div class="col-sm-12 col-md-6">
						<label for="durata"><h4>Durata</h4></label> <input type="number"
							min="" class="form-control" name="durata" id="durata"
							placeholder="durata" title="inserisci la durata" required>
					</div>
				</div>

				<div class="form-group">

					<div class="col-sm-12 col-md-6">
						<label for="copertina"><h4>Copertina</h4></label> <input
							type="text" class="form-control" name="copertina" id="copertina"
							placeholder="nome dell'immagine"
							title="inserisci il nome dell'immagine di copertina" required>
					</div>
				</div>

				<div class="form-group">

					<div class="col-sm-12 col-md-6">
						<label for="link"><h4>Link</h4></label> <input type="text"
							class="form-control" name="link" id="link" placeholder="link"
							title="inserisci il link del video" required>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-2 col-md-2">
						<div class="radio">
							<label> <input type="radio" name="type" id="film"
								value="film" checked>
								<h4>Film</h4>
							</label>
						</div>
						<div class="radio">
							<label> <input type="radio" name="type" id="serie"
								value="serie">
								<h4>SerieTV</h4>
							</label>
						</div>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-10 col-md-4" id="variabile">
						<label for="genere"><h4>Genere</h4></label> <input type="text"
							class="form-control" name="genere" id="genere"
							placeholder="genere del film"
							title="inserisci il genere del film" required>
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
				</fieldset>
			</form>

			<form class="form"
				action="<%=response.encodeURL("/ProgettoTSW/Protected")%>"
				method="post" id="registerForm">
				<br><br><br>
				<fieldset>
				<legend>Rimuovi un prodotto</legend>
				<input type="hidden" name="action" value="elimina">
				<div class="form-group">

					<div class="col-sm-12 col-md-6">
						<label for="nome"><h4>Nome del prodotto</h4></label> <input
							type="text" class="form-control" name="nomeDelete"
							id="nomeDelete" placeholder="nome prodotto"
							title="inserisci il nome del prodotto" required>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-4 col-md-4">
						<div class="radio">
							<label> <input type="radio" name="deleteType" id="film"
								value="film" checked>
								<h4>Film</h4>
							</label>
						</div>
						<div class="radio">
							<label> <input type="radio" name="deleteType" id="serie"
								value="serie">
								<h4>SerieTV</h4>
							</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-12">
						<br>
						<button class="btn btn-lg btn-success" type="submit">
							<i class="glyphicon glyphicon-ok-sign"></i> Elimina
						</button>
						<button class="btn btn-lg" type="reset">
							<i class="glyphicon glyphicon-repeat"></i> Reset
						</button>
					</div>
				</div>
				</fieldset>
			</form>
		</div>

		<div class="tab-pane" id="utenti">
			<br> <br>
			<table>
				<tr>
					<th>Username</th>
					<th colspan="2">Operazione</th>
				</tr>
				<%
					for (UtenteBean bean : utenti) {
						if (!bean.isAdmin()) {
				%>
				<tr>
					<td><%=bean.getUsername()%></td>
					<td><a
						href="<%=response.encodeURL("Protected?action=promuovi&username=" + bean.getUsername())%>">Promuovi</a></td>
					<td><a
						href="<%=response.encodeURL("Protected?action=rimuovi&username=" + bean.getUsername())%>">Rimuovi
							account</a></td>
				</tr>
				<%
					}
					}
				%>
			</table>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="js/protected.js"></script>
</body>
</html>