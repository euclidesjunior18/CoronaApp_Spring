<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CoronApp</title>
<style type="text/css">
body {
	font-family: Arial, Helvetica, sans-serif;
	margin: 0;
}

html {
	box-sizing: border-box;
}

*, *:before, *:after {
	box-sizing: inherit;
}

/*atividade 2*/

.resultado {
	padding: 50px;
	text-align: center;
	background-color: #474e5d;
	color: white;
}

.progress-bar{
  --progress: ${paciente.resultado};
  height: 50px;
  padding: 5px;
  background-color: #ccc;
  display: flex;
}

.progress-bar::before{
   content: "";
  width: calc(var(--progress) * 1%);
  background-color: hsl(calc(var(--progress) * 3.6), 80%, 50%);
}

</style>
</head>
<body>
	<div class="resultado">
		<h1>Sr.: ${paciente.nome}</h1>		
	    <p>Seu resultado é: ${paciente.resultado}%</p>
		<!-- atividade 2 --> 
		<div class="progress-bar" ></div>
	</div>
	<div style="margin: auto; padding: 10px; text-align: center;"><button onclick="location.href='/'" type="button">
         Novo Teste</button></div>
</body>
</html>