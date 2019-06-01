package com.atomic.burguer.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atomic.burguer.model.Lanche;
import com.atomic.burguer.model.Pedido;
import com.atomic.burguer.model.TipoLanche;
import com.atomic.burguer.service.ManagementService;

@RestController
@RequestMapping("/resources")
public class ResourceAPI {

	@Autowired
	private ManagementService managementService;
	
	@PutMapping("/calcular-pedido")
	public ResponseEntity<Pedido> criar(@Valid @RequestBody Pedido pedido) {
		Pedido newPedido = managementService.calcularPedido(pedido);
		return ResponseEntity.ok(newPedido);
	}
	
	@GetMapping("/calcular-lanche/{tipo}")
	public ResponseEntity<Lanche> calcularLanche(@PathVariable("tipo") TipoLanche tipo) {
		Lanche lanche = managementService.calcularLanche(tipo);
		return ResponseEntity.ok(lanche);
	}
}