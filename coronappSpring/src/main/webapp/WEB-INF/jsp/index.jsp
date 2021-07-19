<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CoronApp - Teste Seus Sintomas</title>
</head>
<body>
    <h1>${mensagem}</h1>
	<h2>Teste seus Sintomas - CoronApp</h2>
	<strong>Informe seu CPF para iniciar o teste:</strong>
	<br/> <br/>
	<form name="frmcpf" action="/buscar" method="post">
		<h2>${erro}</h2>
		<label for="cpf">CPF:</label>
		<input name="cpf" id="cpf" placeholder="CPF sem pontuação" type="number">
		<br/><br/>
		<button>Iniciar Teste</button>
	</form>
</body>
</html>