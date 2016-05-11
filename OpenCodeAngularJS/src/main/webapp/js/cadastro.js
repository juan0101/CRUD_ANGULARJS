$(document).ready(function () {
	  var $formCadastro = $('#formCadastro')
      var $inputLinguagem = $('#txtLinguagem');
	  var $inputTitulo = $('#txtTitulo');
	  console.log("************oi**********");
	  console.log(inputLinguagem+"linguagem");
	  console.log(inputTitulo+"titulo");
	  
	  $formCadastro.submit(function (evento) {
		  console.log("TO NO SUBMIT");
        if(inputLinguagem === ''){
			console.log("ta vazio! linguagem");
		}else{
			console.log("Linguaguem não esta vazio");
		}
		
		if(inputTitulo === ''){
			console.log("ta vazio! titulo");
		}else{
			console.log("Titulo não esta vazio");
		}
		
      });
     
});