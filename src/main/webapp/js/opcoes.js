function votar(idLivro) {
	$(".votar").unbind("click");
	
	$.ajax({
		  url: "votar?id=" + idLivro,
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

