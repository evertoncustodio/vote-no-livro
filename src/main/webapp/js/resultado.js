function mostrarLivro(idLivro) {
	$.ajax({
		  url: "mostrarLivro?id=" + idLivro,
		  success: function(resultado){
			  $(".teste").remove();
			  $(".livro").append(resultado);
			  $("#livroModal").modal('show');
		  }
		});
}

$("tbody tr").hover(
		function(){
			$(this).addClass("selecionado");
		},
		function() {
			$(this).removeClass("selecionado");
		}
);