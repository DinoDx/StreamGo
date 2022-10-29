$(document)
		.ready(
				function() {
					$('#film')
							.click(
									function() {
										if ($(this).is(':checked')) {
											$('#variabile')
													.html(
															'<label for="genere"><h4>Genere</h4></label> <input type="text" class="form-control" name="genere" id="genere" placeholder="genere del film" title="inserisci il genere del film" required>');
										}
									});

					$('#serie')
							.click(
									function() {
										if ($(this).is(':checked')) {
											$('#variabile')
													.html(
															'<label for="numEpisodi"><h4>Numero di episodi</h4></label> <input type="text" class="form-control" name="numEpisodi" id="numEpisodi" placeholder="numero di episodi" title="inserisci il numero di episodi della serie" required>');
										}
									});
				});

function checkNomeProdotto(nome) {
	var letters = /^[0-9a-zA-Z\s]+$/;
	if (nome.val().match(letters))
		return true;

	return false;
}

function checkGenere(inputtxt) {
	var name = /^[A-Za-z\s]+$/;
	if (inputtxt.val().match(name))
		return true;

	return false;
}

function validate(obj) {
	var valid = true;

	if (!checkNomeProdotto($("#nome"))) {
		valid = false;
		$("#nome").addClass("error");
		$("#nomeProdottoError").remove();
		$("<h4>Il nome del prodotto puo contenere solo lettere e numeri</h4>")
				.attr("id", "nomeProdottoError").css("color", "red").appendTo(
						$("#errorsList"));
	} else {
		$("#nome").removeClass("error");
		$("#nomeProdottoError").remove();
	}

	$("#genereError").remove();
	if ($('#film').is(':checked')) {
		if (!checkGenere($("#genere"))) {
			valid = false;
			$("#genere").addClass("error");
			$("<h4>Il genere puo contenere solo lettere</h4>").attr("id",
					"genereError").css("color", "red").appendTo(
					$("#errorsList"));
		} else {
			$("#nome").removeClass("error");
			$("#genereError").remove();
		}
	}
	if (valid)
		obj.submit();
}