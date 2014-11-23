<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<script src="<c:url value='/js/jquery-1.11.1.min.js' />"></script>
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/bootstrap.min.css' />" />
		<script src="<c:url value='/js/bootstrap.js' />"></script>
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/default.css' />" />
	</head>

	<body>
		<div class="panel panel-default painelGeral centralizado">
			<div class="panel-heading">
				<h1 class="panel-title">Vote no Livro</h1>
			</div>
			<div class="panel-body">
				<jsp:include page="opcoes.jsp" />
			</div>
		</div>
	</body>
</html>
