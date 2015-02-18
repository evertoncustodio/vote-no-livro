function votar(idLivro) {
	$(".votar").attr("disabled", true);
	
	$.ajax({
		  url: "votar.do?id=" + idLivro,
		  success: function(resultado){
			  $(".opcoes").remove();
			  $(".panel-body").append(resultado);
		  }
		});
}

$(".opcoes").ready(function(){
	$(".opcao").hover(
			function(){$(this).addClass("selecionado")},
			function(){$(this).removeClass("selecionado")}
	);
});

