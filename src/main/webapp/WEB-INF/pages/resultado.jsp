<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="v" %>

<div class="resultado">

	<v:tabela titulo="Resultado Geral" scores="${rankingGeral.scores}" />
	<v:tabela titulo="Resultado do usu�rio ${usuario.nome}" scores="${rankingUsuario.scores}" />
	
	<a class="btn btn-default" href="${pageContext.request.contextPath}">Nova Vota��o</a>
	
	<div class="livro">
		
	</div>
	
	<script src="${pageContext.request.contextPath}/js/resultado.js"></script>
</div>