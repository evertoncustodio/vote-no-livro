$("#formUsuario").submit(function(event){
	event.preventDefault();
	
	$("#formUsuario").submit(function(event){
		return false;
	});
	
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