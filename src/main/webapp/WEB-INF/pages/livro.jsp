<div class="teste">
	<div class="modal fade" id="livroModal" tabindex="-1" role="dialog" aria-labelledby="livroModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Fechar</span></button>
					<h4 class="modal-title" id="livroModalLabel">${livro.titulo}</h4>
				</div>
				<div class="modal-body">
					<img alt="${livro}" src="${pageContext.request.contextPath}/img/${livro.imagem}">
				</div>
			</div>
		</div>
	</div>
</div>