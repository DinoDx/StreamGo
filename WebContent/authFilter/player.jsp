<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*, model.*, control.Player"%>

<%
	ProdottoBean prodotto = (ProdottoBean) session.getAttribute("prodotto");
	String error = (String) request.getAttribute("error");

	if (prodotto == null && error == null) {
		response.sendRedirect(response.encodeRedirectURL("/ProgettoTSW/Player"));
		return;
	}

	String username = (String) session.getAttribute("username");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>StreamGO - Player</title>
<link rel="shrtcut icon" type="image.png" href="img/streamgo.png">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link href="<%=request.getContextPath()%>/css/default.css"
	type="text/css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/player.css"
	type="text/css" rel="stylesheet">
<script src="js/player.js"></script>
</head>
<body>
	<hr>

	<img src="img/streamgo.jpg" alt="impossibile caricare l'immagine"
		width="200" height="150" class="logo rotate">
	<a href="authFilter/homepage.jsp" class="navHomepage"><i
		class="glyphicon glyphicon-home"></i>Homepage</a>
	<a href="Logout" class="navLogout"><i
		class="glyphicon glyphicon-ban-circle"></i>Logout</a>
	<div class="row">
		<div class="col-sm-10">
			<h1><%=prodotto.getNome()%></h1>
		</div>
	</div>

	<div class="col-sm-12 col-md-3">
		<!--left col-->

		<ul class="list-group">
			<li class="list-group-item" style="background-color: #98878F"><i
				class="fa fa-dashboard fa-1x" style="background-color: #98878F">Informazioni</i></li>
			<li class="list-group-item text-right"><span class="pull-left"><strong>Recensioni
						ricevute</strong></span> <%=prodotto.countRecensioni()%></li>
			<li class="list-group-item text-right"><span class="pull-left"><strong>Media
						recensioni</strong></span> <%=String.format("%.2f", prodotto.mediaRecensioni())%></li>
			<li class="list-group-item text-right"><span class="pull-left"><strong>Durata
				</strong></span> <%=prodotto.getDurata()%>'</li>
			<%
				if (prodotto instanceof FilmBean) {
			%>
			<li class="list-group-item text-right"><span class="pull-left"><strong>Genere
				</strong></span> <%=((FilmBean) prodotto).getGenere()%></li>
			<%
				} else {
			%>
			<li class="list-group-item text-right"><span class="pull-left"><strong>Episodi
				</strong></span> <%=((SerieTvBean) prodotto).getNumEpisodi()%></li>
			<%
				}
			%>
		</ul>

	</div>
	<div class="col-sm-12 col-md-9 col-lg-9" id="player">
		<div id="frame">
			<iframe width="560" height="315"
				src="https://www.youtube.com/embed/<%=prodotto.getLink()%>"
				frameborder="0"
				allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
				allowfullscreen></iframe>
		</div>

		<ul class="nav nav-tabs">
			<li class="active"><a data-toggle="tab" href="#recensione">Aggiungi
					una recensione</a></li>
			<li><a data-toggle="tab" href="#recensioni">Tutte le
					recensioni</a></li>
		</ul>

		<div class="tab-content">

			<div class="tab-pane active" id="recensione">

				<h2></h2>
				<form class="form"
					action="<%=response.encodeURL("/ProgettoTSW/Player")%>"
					method="post" id="recensioneForm">
					<input type="hidden" name="action" value="modificaRecensione">
					<input type="hidden" name="nomeProdotto"
						value="<%=prodotto.getNome()%>"> <input type="hidden"
						name="type"
						value="<%=(prodotto instanceof FilmBean) ? "film" : "serie"%>">
					<div class="form-group">
						<div class="col-lg-2">
							<label for="number"><h4>Voto:</h4></label>
						</div>
						<div class="col-lg-2">
							<input type="number" name="number" min="1" max="5"
								class="form-control">
						</div>
					</div>
					<br> <br> <br> <br>

					<div class="form-group">
						<div class="col-lg-2">
							<label for="descrizione"><h4>Descrizione:</h4></label>
						</div>
						<div class="col-lg-10">
							<textarea name="descrizione" rows="4" cols="50"
								class="form-control"></textarea>
							<br>
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
				<hr>
			</div>

			<div class="tab-pane" id="recensioni">

				<%
					ArrayList<RecensioneBean> recensioni = prodotto.getRecensioni();
					if (recensioni.size() > 0) {
						for (RecensioneBean rec : recensioni) {
							//							if (!rec.getNomeUtente().equals(username)) {
				%>
				<br>
				<div class="row">
					<div class="card text-center col-lg-9 col-md-12"
						id="cardRecensioni">
						<br>
						<div class="card-header"><%=rec.getNomeUtente()%></div>
						<hr>
						<div class="card-body">
							<h5 class="card-title">
								<%
									for (int i = 1; i <= 5; i++) {
												if (i <= rec.getVoto()) {
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
								if (rec.getDescrizione() != null) {
							%>
							<p class="card-text"><%=rec.getDescrizione()%></p>
							<%
								}
							%>
						</div>
					</div>
				</div>
				<%
					//					}
						}
					} else {
				%>
				<%
					}
				%>
				<hr>

			</div>
		</div>

		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>