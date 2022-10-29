<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*, control.*, model.*"%>
<%
	Collection<?> movies = (Collection<?>) request.getAttribute("movies");
	Collection<?> shows = (Collection<?>) request.getAttribute("shows");

	String error = (String) request.getAttribute("error");

	if ((movies == null || shows == null) && error == null) {
		response.sendRedirect(response.encodeRedirectURL("/ProgettoTSW/Catalogo"));
		return;
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>StreamGO - Catalogo</title>
<link rel="shrtcut icon" type="image.png" href="img/streamgo.png">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link href="<%=request.getContextPath()%>/css/default.css"
	type="text/css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/homepage.css"
	type="text/css" rel="stylesheet">
</head>
<body>

	<img src="img/streamgo.jpg" alt="impossibile caricare l'immagine"
		width="200" height="150" class="logo rotate">
	<br>
	<nav id="navigationBar">
		<ul>
			<li><a href="login.jsp"><i class='fas fa-sign-in-alt'></i>Torna
					al login</a></li> |
			<li><a href="register.jsp"><i class='far fa-address-card'></i>Registrati</a></li>
		</ul>
	</nav>

	<div class="pos-f-t">
		<div class="collapse" id="navbarToggleExternalContent">
			<nav id="navigationMenu">
				<ul>
					<li><a href="login.jsp"><i class='fas fa-sign-in-alt'></i>Torna
							al login</a></li>
					<li><a href="register.jsp"><i class='far fa-address-card'></i>Registrati</a></li>
				</ul>
			</nav>
		</div>
		<nav class="navbar navbar-dark">
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarToggleExternalContent"
				aria-controls="navbarToggleExternalContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
		</nav>
	</div>
	<div class="container">
		<br> <br>
		<h2 id="film">Catalogo Film</h2>
		<br>
		<form action="<%=response.encodeURL("Catalogo")%>" method="get">
			<label for="sortFilm">Seleziona l'ordine:</label> <select
				name="sortFilm" id="sortFilm">
				<option value="nome">Nome</option>
				<option value="durata">Durata</option>
				<option value="genere">Genere</option>
			</select> <input type="radio" name="orderFilm" id="filmASC" value="ASC"
				checked>Ascendente <input type="radio" name="orderFilm"
				id="filmDESC" value="DESC">Discendente <input type="submit"
				value="Ordina">
		</form>
		<br>
		<div class="row">
			<%
				if (movies != null && movies.size() > 0) {

					Iterator<?> it = movies.iterator();
					while (it.hasNext()) {
						FilmBean bean = (FilmBean) it.next();
			%>
			<div class="col-xs-12 col-md-6 col-lg-4 col-xl-3">
				<div class="card-columns-fluid">
					<div class="card">
						<div class="animationContainer">
							<div class="image">
								<img src="<%=bean.getImg()%>" class="card-img-top" alt="...">
							</div>
							<div class="content">
								Recensioni ricevute:
								<%=bean.countRecensioni()%><br> <br> Media recensioni:
								<%=String.format("%.2f", bean.mediaRecensioni())%><br>
								<%
									for (int i = 1; i <= 5; i++) {
												if (i <= bean.mediaRecensioni()) {
								%>
								<i class='fas fa-star'></i>
								<%
									} else if (i <= bean.mediaRecensioni() + 0.5) {
								%>
								<i class='fas fa-star-half-alt'></i>
								<%
									} else {
								%>
								<i class='far fa-star'></i>
								<%
									}
											}
								%>
							</div>
						</div>
						<div class="card-body">
							<h5 class="card-title"><%=bean.getNome()%></h5>
							<p class="card-text"><%=bean.getDurata()%>'<br>
								<%=bean.getGenere()%></p>
						</div>
					</div>
				</div>
			</div>

			<%
				}
				} else {
			%>
			<div class="card text-center" id="emptyFilm">
				<div class="card-header">Catalogo vuoto</div>
				<div class="card-body">
					<h5 class="card-title">Non sono attualmente disponibili film</h5>
					<p class="card-text">Vai direttamente al catalogo delle Serie
						Tv</p>
					<a href="#serieTv" class="btn btn-primary">Serie TV</a>
				</div>
				<div class="card-footer">Oppure seleziona uno dei prodotti
					consigliati!</div>
			</div>
			<%
				}
			%>
		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>


	<div class="container">
		<br> <br>
		<h2 id="serieTv">Catalogo Serie TV</h2>
		<br>
		<form action="<%=response.encodeURL("Catalogo")%>" method="get">
			<label for="sortSerie">Seleziona l'ordine:</label> <select
				name="sortSerie" id="sortSerie">
				<option value="nome">Nome</option>
				<option value="durata">Durata</option>
				<option value="numepisodi">Episodi</option>
			</select> <input type="radio" name="orderSerie" id="serieASC" value="ASC"
				checked>Ascendente <input type="radio" name="orderSerie"
				id="serieDESC" value="DESC">Discendente <input type="submit"
				value="Ordina">
		</form>
		<br>
		<div class="row">
			<%
				if (shows != null && shows.size() > 0) {

					Iterator<?> it = shows.iterator();
					while (it.hasNext()) {
						SerieTvBean bean = (SerieTvBean) it.next();
			%>
			<div class="col-xs-12 col-md-6 col-lg-4 col-xl-3">
				<div class="card-columns-fluid">
					<div class="card">
						<div class="animationContainer">
							<div class="image">
								<img src="<%=bean.getImg()%>" class="card-img-top" alt="...">
							</div>
							<div class="content">
								Recensioni ricevute:
								<%=bean.countRecensioni()%><br> <br> Media recensioni:
								<%=String.format("%.2f", bean.mediaRecensioni())%><br>
								<%
									for (int i = 1; i <= 5; i++) {
												if (i <= bean.mediaRecensioni()) {
								%>
								<i class='fas fa-star'></i>
								<%
									} else if (i <= bean.mediaRecensioni() + 0.5) {
								%>
								<i class='fas fa-star-half-alt'></i>
								<%
									} else {
								%>
								<i class='far fa-star'></i>
								<%
									}
											}
								%>
							</div>
						</div>
						<div class="card-body">
							<h5 class="card-title"><%=bean.getNome()%></h5>
							<p class="card-text"><%=bean.getDurata()%>'<br>
								<%=bean.getNumEpisodi()%>
								episodi
							</p>
						</div>
					</div>
				</div>
			</div>
			<%
				}
				} else {
			%>
			<div class="card text-center" id="emptySerieTv">
				<div class="card-header">Catalogo vuoto</div>
				<div class="card-body">
					<h5 class="card-title">Non sono attualmente disponibili Serie
						TV</h5>
					<p class="card-text">Vai direttamente al catalogo dei Film</p>
					<a href="#film" class="btn btn-primary">Film</a>
				</div>
				<div class="card-footer">Oppure seleziona uno dei prodotti
					consigliati!</div>
			</div>
			<%
				}
			%>
		</div>
	</div>

	<script src='https://kit.fontawesome.com/a076d05399.js'></script>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
		integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
		crossorigin="anonymous"></script>
	<script src="js/homepage.js"></script>
</body>
</html>