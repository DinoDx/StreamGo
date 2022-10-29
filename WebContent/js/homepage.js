var req = new XMLHttpRequest();

function ajaxCallFilm(nome, username) {
	console.log(nome);
	req.open("GET", "AjaxServlet?nome=" + nome + "&type=film", true);
	req.onreadystatechange = function(){processFilm(username)};
	req.send(null);
}

function processFilm(username) {
	console.log("Process " + req.readyState + " " + req.status);
	if (req.readyState == 4 && req.status == 200) {
		console.log(req.responseText);
		var obj = JSON.parse(req.responseText);
		$("#emptyWatchlist").hide();
		$("#fullWatchlist").show();
		$("#clear").show();

		var card = document.createElement("div");
		card.id = obj.nome;
		card.classList.add("card");

		$("<img>").attr("src", obj.img).addClass("card-img-top").attr("alt",
				"...").appendTo(card);

		var cardBody = document.createElement("div");
		cardBody.classList.add("card-body");

		$("<h5></h5>").addClass("card-title").text(obj.nome).appendTo(cardBody);
		$("<p></p>").addClass("card-text").html(
				obj.durata + "'<br>" + obj.genere).appendTo(cardBody);
		$("<a></a>")
				.addClass("btn btn-primary")
				.attr(
						'href',
						'ProductControl?action=deleteWatchlist&nome=' + obj.nome)
				.text("Rimuovi dalla watchlist").appendTo(cardBody);
		$("<a></a>")
				.addClass("btn btn-primary")
				.attr(
						'href',
						'Player?action=watch&nomeProdotto=' + obj.nome + '&username=' + username + '&type=film')
				.text("Guarda").appendTo(cardBody);

		card.append(cardBody);
		var fluid = document.createElement("div");
		fluid.classList.add("card-columns-fluid");
		fluid.append(card);

		var all = document.createElement("div");
		all.classList.add("col-xs-12", "col-md-6", "col-lg-4", "col-xl-3");
		all.append(fluid);
		console.log(all);

		$("#row").append(all);
	}
}

function ajaxCallSerie(nome, username) {
	console.log(nome);
	req.open("GET", "AjaxServlet?nome=" + nome + "&type=serie", true);
	req.onreadystatechange = function(){processSerie(username)};
	req.send(null);
}

function processSerie(username) {
	console.log("Process " + req.readyState + " " + req.status);
	if (req.readyState == 4 && req.status == 200) {
		console.log(req.responseText);
		var obj = JSON.parse(req.responseText);
		$("#emptyWatchlist").hide();
		$("#fullWatchlist").show();
		$("#clear").show();
		
		var card = document.createElement("div");
		card.id = obj.nome;
		card.classList.add("card");

		$("<img>").attr("src", obj.img).addClass("card-img-top").attr("alt",
				"...").appendTo(card);

		var cardBody = document.createElement("div");
		cardBody.classList.add("card-body");

		$("<h5></h5>").addClass("card-title").text(obj.nome).appendTo(cardBody);
		$("<p></p>").addClass("card-text").html(
				obj.durata + "'<br>" + obj.episodi + "episodi").appendTo(
				cardBody);
		$("<a></a>")
				.addClass("btn btn-primary")
				.attr(
						'href',
						'ProductControl?action=deleteWatchlist&nome=' + obj.nome)
				.text("Rimuovi dalla watchlist").appendTo(cardBody);
		$("<a></a>")
				.addClass("btn btn-primary")
				.attr(
						'href',
						'Player?action=watch&nomeProdotto=' + obj.nome + '&username=' + username + '&type=serie')
				.text("Guarda").appendTo(cardBody);

		card.append(cardBody);
		var fluid = document.createElement("div");
		fluid.classList.add("card-columns-fluid");
		fluid.append(card);

		var all = document.createElement("div");
		all.classList.add("col-xs-12", "col-md-6", "col-lg-4", "col-xl-3");
		all.append(fluid);
		console.log(all);

		$("#row").append(all);
	}
}