<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="titulo" required="true" %>
<%@ attribute name="scores" required="true" type="java.util.List" %>

<div class="panel panel-default">
	<div class="panel-heading">${titulo}</div>
	
	<table class="table">
		<thead>
			<tr>
				<th>Livro</th>
				<th>Votos</th>
			<tr> 
		</thead>
		<tbody>
			<c:forEach var="score" items="${scores}"> 
				<tr onclick="mostrarLivro(${score.livro.id})">
					<td>${score.livro}</td>
					<td>${score.votos}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
