<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="opcoes">
	<div class="opcao">
		<div class="votar" onclick="votar(${opcao1.id})">
			<img alt="${opcao1}" title="${opcao1}" src="${pageContext.request.contextPath}/img/${opcao1.imagem}"/>
		</div> 
	</div>
	<div class="opcao">
		<div class="votar" onclick="votar(${opcao2.id})">
			<img alt="${opcao1}" title="${opcao2}" src="${pageContext.request.contextPath}/img/${opcao2.imagem}">
		</div>
	</div>
	
	<script src="${pageContext.request.contextPath}/js/opcoes.js"></script>
</div>
