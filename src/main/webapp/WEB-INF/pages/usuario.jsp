<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="usuario">
	<form action="confirmar" method="post" id="formUsuario">
		<label for="nome">Nome</label>
		<input type="text" class="form-control" name="nome" maxlength="200" required>
		<br>
		<label for="email">E-Mail</label>
		<input type="email" class="form-control" name="email" maxlength="200" required>
		<br>
		<button type="submit" class="btn btn-default">Confirmar</button>
	</form>
	
	<script src="<c:url value='/js/usuario.js' />"></script>
</div>