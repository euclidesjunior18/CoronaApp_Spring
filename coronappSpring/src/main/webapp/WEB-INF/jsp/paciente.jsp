<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CoronApp - Testes Rápido de Sintomas</title>
<style>
* {
	box-sizing: border-box
}

/* Add padding to containers */
.container {
	padding: 2px;
}

/* Full-width input fields */
input[type=text], input[type=date], input[type=number], select {
	width: 100%;
	padding: 15px;
	margin: 3px 0 10px 0;
	display: inline-block;
	border: none;
	background: #f1f1f1;
}
</style>
<script>
	function showPosition() {
		if (navigator.geolocation) {
			navigator.geolocation
					.getCurrentPosition(function(position) {
						document.getElementById("latitude").value = position.coords.latitude
								.toFixed(8);
						document.getElementById("longitude").value = position.coords.longitude
								.toFixed(8);
					});
		} else {
			alert("Navegador não tem suporte ou permite geolocalização!");
		}
	}
</script>
</head>
<body>
	<c:if test="${not empty sessionScope.erro}"><h3 style="color: red">${sessionScope.erro}</h3><c:remove var="erro" scope="session" /></c:if>
	<form action="/testar" method="post" enctype="multipart/form-data">
		<div class="container">
			<input type="hidden" name="version" value="${not empty paciente.version ? paciente.version : ''}"/>
			<h1>Teste Rápido</h1>
			<hr>
			<h2>${erro}</h2>
			<label for="nome"><b>CPF: </b></label> <input type="number"		    
				name="cpf" required="required" value="${sessionScope.cpf}" readonly="readonly"
				placeholder="CPF sem pontuações">
			<form:errors path="paciente.cpf" />
			
			<br /><label for="file">
			      <input type="file" name="file" />
			</label>
			
			<br /> <label for="nome"><b>Nome: </b></label> <input type="text"
				name="nome" required="required" autofocus="autofocus" maxlength="40"
				size="40" placeholder="Nome" value="${empty paciente.nome ? '' : paciente.nome}">
			<form:errors path="paciente.nome" />
			<br /> <label for="nome"><b>Data de Nascimento: </b></label> <input
				type="date" name="dataNascimento" required="required"
				placeholder="dd/mm/aaaa" value="${empty paciente.dataNascimentoFmt ? '' : paciente.dataNascimentoFmt}">
			<form:errors path="paciente.dataNascimento" />

			<label for="sexo"><b>Sexo: </b></label> Masculino <input type="radio"
				name="sexo" value="M" required="required" ${((not empty paciente.sexo) && (paciente.sexo eq 'M')) ? 'checked' : ''}> Feminino <input
				type="radio" name="sexo" value="F" required="required" ${((not empty paciente.sexo) && (paciente.sexo eq 'F')) ? "checked" : ""}>
			<form:errors path="paciente.sexo" />
			<br />
			<br /> <label for="latitude"><b>Latitude: </b></label> <input
				type="number" id="latitude" name="latitude" step="0.00000001"
				placeholder="Latitude" value="${empty paciente.latitude ? '' : paciente.latitude}">
			<form:errors path="paciente.latitude" />

			<label for="longitude"><b>Longitude: </b></label> <input
				type="number" id="longitude" name="longitude" required="required"
				step="0.00000001" placeholder="Longitude" value="${empty paciente.longitude ? '' : paciente.longitude}">
			<form:errors path="paciente.longitude" />

			<button name="capturar"
				onclick="javascript: showPosition(); return false;">Capturar</button>
			<br /> <br /> <label for="sintomas"><b>Selecione os
					sintomas: </b></label> <select name="sintomas" multiple="multiple" 
				required="required">
				<c:forEach items="${sintomas}" var="sintoma">
					<option value="${sintoma.percentual}">
						${sintoma.descricao}</option>
				</c:forEach>
			</select>

			<button type="Submit">Testar</button>
		</div>
	</form>
</body>
</html>