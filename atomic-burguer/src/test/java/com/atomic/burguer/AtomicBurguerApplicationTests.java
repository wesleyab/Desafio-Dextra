package com.atomic.burguer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.atomic.burguer.model.Adicional;
import com.atomic.burguer.model.Lanche;
import com.atomic.burguer.model.Pedido;
import com.atomic.burguer.model.Ingrediente;
import com.atomic.burguer.model.TipoLanche;
import com.atomic.burguer.service.ManagementService;

public class AtomicBurguerApplicationTests {
	
	@Mock
	private ManagementService managementService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockValoresIngredientes();
	}
	
	/**
	 * Mock do valor dos ingredientes, para que a inflação não influencie nos testes automatizados.
	 */
	private void mockValoresIngredientes() {
		Mockito.when(managementService.getValor(Ingrediente.ALFACE)).thenReturn(new BigDecimal(0.40));
		Mockito.when(managementService.getValor(Ingrediente.BACON)).thenReturn(new BigDecimal(2.00));
		Mockito.when(managementService.getValor(Ingrediente.BURGER)).thenReturn(new BigDecimal(3.00));
		Mockito.when(managementService.getValor(Ingrediente.OVO)).thenReturn(new BigDecimal(0.80));
		Mockito.when(managementService.getValor(Ingrediente.QUEIJO)).thenReturn(new BigDecimal(1.50));
	}
	
	// Teste para calcular o valor do lanche solicitado
	@Test
	public void calcularLanche() {
		TipoLanche xBacon = TipoLanche.XBACON;
		TipoLanche xBurger = TipoLanche.XBURGER;
		TipoLanche xEgg = TipoLanche.XEGG;
		TipoLanche xEggBacon = TipoLanche.XEGGBACON;
		
		ManagementService mgtService = new ManagementService();
		
		BigDecimal xBaconValor = new BigDecimal(6.50).setScale(2, BigDecimal.ROUND_HALF_UP);
		Lanche actual = mgtService.calcularLanche(xBacon);
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(formatValor(xBaconValor), actual.getValor());
		
		BigDecimal xBurgerValor = new BigDecimal(4.50).setScale(2, BigDecimal.ROUND_HALF_UP);
		actual = mgtService.calcularLanche(xBurger);
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(formatValor(xBurgerValor), actual.getValor());
		
		BigDecimal xEggValor = new BigDecimal(5.30).setScale(2, BigDecimal.ROUND_HALF_UP);
		actual = mgtService.calcularLanche(xEgg);
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(formatValor(xEggValor), actual.getValor());
		
		BigDecimal xEggBaconValor = new BigDecimal(7.30).setScale(2, BigDecimal.ROUND_HALF_UP);
		actual = mgtService.calcularLanche(xEggBacon);
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(formatValor(xEggBaconValor), actual.getValor());
	}

	// Teste para calcular valor do pedido sem promoções
	@Test
	public void calcularPedido() {
		Pedido pedido = new Pedido();
		pedido.setLanche(TipoLanche.XBURGER);

		Adicional ad1 = new Adicional();
		ad1.setIngrediente(Ingrediente.OVO);
		ad1.setQtde(1);
		Adicional ad2 = new Adicional();
		ad2.setIngrediente(Ingrediente.BACON);
		ad2.setQtde(2);
		pedido.setAdicionais(Arrays.asList(ad1, ad2));

		BigDecimal expected = new BigDecimal(9.30).setScale(2, BigDecimal.ROUND_HALF_UP);

		ManagementService mgtService = new ManagementService();

		Pedido actual = mgtService.calcularPedido(pedido);
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(formatValor(expected), actual.getTotal());
		
		// Sem adicionais
		pedido.setAdicionais(null);
		expected = new BigDecimal(4.50).setScale(2, BigDecimal.ROUND_HALF_UP);

		actual = mgtService.calcularPedido(pedido);
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(formatValor(expected), actual.getTotal());
	}

	// Testes para o calculo da promoção: Light
	@Test
	public void calcularPedido_light() {
		Pedido pedido = new Pedido();
		pedido.setLanche(TipoLanche.XBURGER);

		Adicional ad = new Adicional();
		ad.setIngrediente(Ingrediente.ALFACE);
		ad.setQtde(2);
		pedido.setAdicionais(Arrays.asList(ad));

		BigDecimal expected = new BigDecimal(4.77).setScale(2, RoundingMode.HALF_EVEN);
		
		ManagementService mgtService = new ManagementService();

		Pedido actual = mgtService.calcularPedido(pedido);
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(formatValor(expected), actual.getTotal());
	}

	// Testes para o calculo da promoção: Muita carne
	@Test
	public void calcularPedido_MuitaCarne() {
		Pedido pedido = new Pedido();
		pedido.setLanche(TipoLanche.XBACON);

		Adicional ad = new Adicional();
		ad.setIngrediente(Ingrediente.BURGER);
		ad.setQtde(2);
		pedido.setAdicionais(Arrays.asList(ad));

		BigDecimal expected = new BigDecimal(9.50).setScale(2, BigDecimal.ROUND_HALF_UP);
		
		ManagementService mgtService = new ManagementService();

		Pedido actual = mgtService.calcularPedido(pedido);
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(formatValor(expected), actual.getTotal());

		ad.setQtde(6);
		pedido.setAdicionais(Arrays.asList(ad));
		expected = new BigDecimal(18.50).setScale(2, BigDecimal.ROUND_HALF_UP);
		
		actual = mgtService.calcularPedido(pedido);
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(formatValor(expected), actual.getTotal());
	}

	// Testes para o calculo da promoção: Muito queijo
	@Test
	public void calcularPedido_MuitoQueijo() {
		Pedido pedido = new Pedido();
		pedido.setLanche(TipoLanche.XEGG);

		Adicional ad = new Adicional();
		ad.setIngrediente(Ingrediente.QUEIJO);
		ad.setQtde(2);
		pedido.setAdicionais(Arrays.asList(ad));

		BigDecimal expected = new BigDecimal(6.80).setScale(2, BigDecimal.ROUND_HALF_UP);
		
		ManagementService mgtService = new ManagementService();

		Pedido actual = mgtService.calcularPedido(pedido);
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(formatValor(expected), actual.getTotal());

		ad.setQtde(6);
		pedido.setAdicionais(Arrays.asList(ad));
		expected = new BigDecimal(11.30).setScale(2, BigDecimal.ROUND_HALF_UP);

		actual = mgtService.calcularPedido(pedido);
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(formatValor(expected), actual.getTotal());
	}

	// Testes para o calculo de todas as promoções
	@Test
	public void calcularPedido_TodasPromocoes() {
		Pedido pedido = new Pedido();
		pedido.setLanche(TipoLanche.XEGG);

		Adicional ad1 = new Adicional();
		ad1.setIngrediente(Ingrediente.BURGER);
		ad1.setQtde(3);
		Adicional ad2 = new Adicional();
		ad2.setIngrediente(Ingrediente.ALFACE);
		ad2.setQtde(2);
		Adicional ad3 = new Adicional();
		ad3.setIngrediente(Ingrediente.QUEIJO);
		ad3.setQtde(5);
		pedido.setAdicionais(Arrays.asList(ad1, ad2, ad3));

		BigDecimal expected = new BigDecimal(14.94).setScale(2, RoundingMode.HALF_EVEN);

		ManagementService mgtService = new ManagementService();
		
		Pedido actual = mgtService.calcularPedido(pedido);
		Assertions.assertNotNull(actual);
		Assertions.assertEquals(formatValor(expected), actual.getTotal());
	}
	
	private String formatValor(BigDecimal bd) {
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(2);
		return df.format(bd);
	}

}
