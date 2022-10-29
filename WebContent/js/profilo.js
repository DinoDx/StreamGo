var count = 2;

function addPhone() {

	var divv = document.createElement("div");
	divv.id = "id" + count;
	count++;

	$("<input>").attr("type", "text").attr("name", "number").attr(
			"placeholder", "0123456789").addClass("form-control")
			.appendTo(divv);
	$("<input>").attr("type", "button").attr("value", "-").click(function() {
		removePhone(divv.id);
	}).appendTo(divv);
	$("#phones").append(divv);
}

function removePhone(idd) {
	$("#" + idd).remove();
}

function checkUsername(username) {
	var letters = /^[0-9a-zA-Z]+$/;
	if (username.val().match(letters))
		return true;

	return false;
}

function checkNomeCognome(inputtxt) {
	var name = /^[A-Za-z\s]+$/;
	if (inputtxt.val().match(name))
		return true;

	return false;
}

function checkPhonenumber(inputtxt) {
	var phoneno = /^\d{10}$/;
	if (inputtxt.value.match(phoneno))
		return true;

	return false;
}

function validate(obj) {
	var valid = true;

	if ($("#username").val() != "" && !checkUsername($("#username"))) {
		valid = false;
		$("#username").addClass("error");
		$("#usernameError").remove();
		$("<h4>L' user name puo contenere solo lettere e numeri</h4>").attr(
				"id", "usernameError").css("color", "red").appendTo(
				$("#errorsList"));
	} else {
		$("#username").removeClass("error");
		$("#usernameError").remove();
	}

	if ($("#password").val() != $("#confermaPassword").val()) {
		valid = false;
		$("#password").addClass("error");
		$("#confermaPassword").addClass("error");
		$("#passwordError").remove();
		$("<h4>Le due password non coincidono</h4>")
				.attr("id", "passwordError").css("color", "red").appendTo(
						$("#errorsList"));
	} else {
		$("#password").removeClass("error");
		$("#confermaPassword").removeClass("error");
		$("#passwordError").remove();
	}

	if (!checkNomeCognome($("#nome"))) {
		valid = false;
		$("#nome").addClass("error");
		$("#nameError").remove();
		$("<h4>Il nome puo contenere solo lettere</h4>")
				.attr("id", "nameError").css("color", "red").appendTo(
						$("#errorsList"));
	} else {
		$("#nome").removeClass("error");
		$("#nameError").remove();
	}

	if (!checkNomeCognome($("#cognome"))) {
		valid = false;
		$("#cognome").addClass("error");
		$("#surnameError").remove();
		$("<h4>Il cognome puo contenere solo lettere</h4>").attr("id",
				"surnameError").css("color", "red").appendTo($("#errorsList"));
	} else {
		$("#cognome").removeClass("error");
		$("#surnameError").remove();
	}

	var numbers = document.getElementsByName("number");
	for (var i = 0; i < numbers.length; i++) {
		if (numbers[i].value != "" && !checkPhonenumber(numbers[i])) {
			valid = false;
			numbers[i].classList.add("error");
			$("#phoneError").remove();
			$("<h4>I numeri di telefono devono contenere 10 cifre</h4>").attr(
					"id", "phoneError").css("color", "red").appendTo(
					$("#errorsList"));
		} else {
			numbers[i].classList.remove("error");
			$("#phoneError").remove();
		}
	}

	if (valid)
		obj.submit();
}