$("#formUsuario").submit(function(event){
	event.preventDefault();
	
	$("button").attr("disabled", true);
	
	$.ajax({
		  type: "POST",
		  url: $("#formUsuario").attr("action"),
		  data: $("#formUsuario").serialize(),
		  success: function(resultado){
			  $(".usuario").remove();
			  $(".panel-body").append(resultado);
		  }
		});
});