<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RESULTADO DAS ATIVIDADES</title>
</head>
<body>
    <h1>${mensagemcpf}</h1>
    <h3>${dadospessoa}</h3>
	<br/> <br/>
	<form name="frmatv" action="/" method="post">
	
	<table border='1'>
	    <tr>
        	<td>Nome</td>
        	<td>CPF</td>
        	<td>RESULTADO</td>
        </tr>
 		<c:forEach items="${listapaciente}" var="paciente">
   		<tr>
        	<td>${paciente.getNome()}</td>
        	<td>${paciente.getCpf()}</td>
        	<td>${paciente.getResultado()}</td>
         </tr>
		</c:forEach>
	</table>
	    <br>
		<button >V O L T A R</button>
	</form>
</body>
</html>