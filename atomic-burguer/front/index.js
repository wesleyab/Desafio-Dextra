// Carregar os ingredientes adicionais e sua respectiva quantidade
$(document).ready(function() {
	$('#alface').change(function(){
        if($("#alface").is(":checked")){
            $("#child2").append("<select id='qtdeAlface'></select>");
            $("#qtdeAlface").append("<option value='selecione' selected='selected'>0</option>");
            $("#qtdeAlface").append("<option value='1'>1</option>");
            $("#qtdeAlface").append("<option value='2'>2</option>");
            $("#qtdeAlface").append("<option value='3'>3</option>");
            $("#qtdeAlface").append("<option value='4'>4</option>");
            $("#qtdeAlface").append("<option value='5'>5</option>");
            $("#qtdeAlface").append("<option value='6'>6</option>");
			$("#qtdeAlface").append("<option value='7'>7</option>");
			$("#qtdeAlface").append("<option value='8'>8</option>");
			$("#qtdeAlface").append("<option value='9'>9</option>");
			$("#qtdeAlface").append("<option value='10'>10</option>");
        } else {
            $("#qtdAlface").remove();
        }
    });
    $('#bacon').change(function(){
        if($("#bacon").is(":checked")){
            $("#child2").append("<select id='qtdeBacon'></select>");
            $("#qtdeBacon").append("<option value='selecione' selected='selected'>0</option>");
            $("#qtdeBacon").append("<option value='1'>1</option>");
            $("#qtdeBacon").append("<option value='2'>2</option>");
            $("#qtdeBacon").append("<option value='3'>3</option>");
            $("#qtdeBacon").append("<option value='4'>4</option>");
            $("#qtdeBacon").append("<option value='5'>5</option>");
            $("#qtdeBacon").append("<option value='6'>6</option>");
			$("#qtdeBacon").append("<option value='7'>7</option>");
			$("#qtdeBacon").append("<option value='8'>8</option>");
			$("#qtdeBacon").append("<option value='9'>9</option>");
			$("#qtdeBacon").append("<option value='10'>10</option>");
        } else {
            $("#qtdeBacon").remove();
        }
    });
    $('#burger').change(function(){
        if($("#burger").is(":checked")){
            $("#child2").append("<select id='qtdeBurger'></select>");
            $("#qtdeBurger").append("<option value='selecione' selected='selected'>0</option>");
            $("#qtdeBurger").append("<option value='1'>1</option>");
            $("#qtdeBurger").append("<option value='2'>2</option>");
            $("#qtdeBurger").append("<option value='3'>3</option>");
            $("#qtdeBurger").append("<option value='4'>4</option>");
            $("#qtdeBurger").append("<option value='5'>5</option>");
            $("#qtdeBurger").append("<option value='6'>6</option>");
			$("#qtdeBurger").append("<option value='7'>7</option>");
			$("#qtdeBurger").append("<option value='8'>8</option>");
			$("#qtdeBurger").append("<option value='9'>9</option>");
			$("#qtdeBurger").append("<option value='10'>10</option>");
        } else {
            $("#qtdeBurger").remove();
        }
    });
    $('#ovo').change(function(){
        if($("#ovo").is(":checked")){
            $("#child2").append("<select id='qtdeOvo'></select>");
            $("#qtdeOvo").append("<option value='selecione' selected='selected'>0</option>");
            $("#qtdeOvo").append("<option value='1'>1</option>");
            $("#qtdeOvo").append("<option value='2'>2</option>");
            $("#qtdeOvo").append("<option value='3'>3</option>");
            $("#qtdeOvo").append("<option value='4'>4</option>");
            $("#qtdeOvo").append("<option value='5'>5</option>");
            $("#qtdeOvo").append("<option value='6'>6</option>");
			$("#qtdeOvo").append("<option value='7'>7</option>");
			$("#qtdeOvo").append("<option value='8'>8</option>");
			$("#qtdeOvo").append("<option value='9'>9</option>");
			$("#qtdeOvo").append("<option value='10'>10</option>");
        } else {
            $("#qtdOvo").remove();
        }
    });
    $('#queijo').change(function(){
        if($("#queijo").is(":checked")){
            $("#child2").append("<select id='qtdeQueijo'></select>");
            $("#qtdeQueijo").append("<option value='selecione' selected='selected'>0</option>");
            $("#qtdeQueijo").append("<option value='1'>1</option>");
            $("#qtdeQueijo").append("<option value='2'>2</option>");
            $("#qtdeQueijo").append("<option value='3'>3</option>");
            $("#qtdeQueijo").append("<option value='4'>4</option>");
            $("#qtdeQueijo").append("<option value='5'>5</option>");
            $("#qtdeQueijo").append("<option value='6'>6</option>");
			$("#qtdeQueijo").append("<option value='7'>7</option>");
			$("#qtdeQueijo").append("<option value='8'>8</option>");
			$("#qtdeQueijo").append("<option value='9'>9</option>");
			$("#qtdeQueijo").append("<option value='10'>10</option>");
        } else {
            $("#qtdeQueijo").remove();
        }
    });
	
   // Declaração do lanche selecionado
   var lancheEscolhido;
   
   /**
   * Função responsável por calcular o valor do lanche escolhido
   */
   $("#sel1").change(function(){
      // obtendo o valor do atributo value da tag option
      lancheEscolhido = $("#sel1 option:selected").val();

    	$.ajax({ 
   			url: "http://localhost:8090/resources/calcular-lanche/" + lancheEscolhido,
			type: "GET",
			crossDomain: true,
   			dataType: 'json',
   			success: function(data){        
     			$("#totalPedido").text(data.valor);
   			},
         	error: function(jqXHR, status, errorThrown){
         		 console.log(jqXHR);
            	 alert("Ops... Não foi possível calcular o valor do lanche - " + status.code)
         	}
		});
   });

   /**
    * Função responsável por parsear o json para envio no corpo da requisição
	* @param body
    */
    calcularPedido = function (){
		var body = {};
    	var jsonObject = [];
		lancheEscolhido = $("#sel1 option:selected").val();
		body["lanche"] = lancheEscolhido;
    	if($("#alface").is(":checked")){
    		var item = {};
           	item["ingrediente"] = "ALFACE";
           	item["qtde"] = parseInt($("#qtdeAlface option:selected").val());
           	jsonObject.push(item);
    	}
    	if($("#bacon").is(":checked")){
    		var item = {};
           	item["ingrediente"] = "BACON";
           	item["qtde"] = parseInt($("#qtdeBacon option:selected").val());
           	jsonObject.push(item);
    	}
    	if($("#burger").is(":checked")){
    		var item = {};
           	item["ingrediente"] = "BURGER";
           	item["qtde"] = parseInt($("#qtdeBurger option:selected").val());
           	jsonObject.push(item);
    	}
    	if($("#ovo").is(":checked")){
    		var item = {};
           	item["ingrediente"] = "OVO";
           	item["qtde"] = parseInt($("#qtdeOvo option:selected").val());
           	jsonObject.push(item);
    	}
    	if($("#queijo").is(":checked")){
    		var item = {};
           	item["ingrediente"] = "QUEIJO";
           	item["qtde"] = parseInt($("#qtdeQueijo option:selected").val());
           	jsonObject.push(item);
    	}
		body["adicionais"] = jsonObject;
    	callService(body);
    }

	/**
    * Função responsável por calcular o valor total do pedido
	* @param body
    */
    callService = function(body){
    	$.ajax({
        	url: "http://localhost:8090/resources/calcular-pedido",
        	type: "PUT",
			crossDomain: true,
        	dataType: 'json',
			data: JSON.stringify(body),
        	contentType: "application/json",
         	success: function(data,status){
            	  $("#totalPedido").text(data.total);
         	},
         	error: function(jqXHR, status, errorThrown){
         		 console.log(jqXHR);
            	 alert("Ops... Não foi possível calcular o total do pedido - " + status.code)
         	}
    	});
    }
});