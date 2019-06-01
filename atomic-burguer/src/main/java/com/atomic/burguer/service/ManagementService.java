package com.atomic.burguer.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.atomic.burguer.model.Adicional;
import com.atomic.burguer.model.Lanche;
import com.atomic.burguer.model.Pedido;
import com.atomic.burguer.model.Ingrediente;
import com.atomic.burguer.model.TipoLanche;


/**
 * Classe responsável pelos serviços de inteligência para gerir as regras de
 * negócio da aplicação.
 */
@Service
public class ManagementService {
	
	/**
	 * Calcula o valor do lanche perante seus respectivos ingredientes.
	 * 
	 * @param tipoLanche {@link TipoLanche} Tipo do lanche pedido.
	 * @return {@Lanche} Contendo o valor calculado por seus ingredientes.
	 */
	public Lanche calcularLanche(TipoLanche tipoLanche) {
		Lanche lanche = new Lanche();

		List<Ingrediente> ingredientesLanche = new ArrayList<>();
		ingredientesLanche.addAll(TipoLanche.ingredientes(tipoLanche));

		lanche.setValor(formatValor(calcularTotal(ingredientesLanche, false)));

		return lanche;
	}

	/**
	 * Serviço responsável por preparar os ingredientes do pedido e o valor final
	 * 
	 * @param {@link Pedido} Objeto com as informações do pedido.
	 * @return {@link Pedido} Objeto com o valor total do pedido.
	 */
	public Pedido calcularPedido(Pedido pedido) {
		Pedido resultado = new Pedido();
		boolean adicional = false;

		if (pedido == null) {
			return resultado;
		}

		List<Ingrediente> ingredientesLanche = new ArrayList<>();
		// Deve conter um lanche para efetuar o pedido.
		if (pedido.getLanche() != null) {
			ingredientesLanche.addAll(TipoLanche.ingredientes(pedido.getLanche()));

			// Adiciona os adicionais ao lanche pedido.
			if (pedido.getAdicionais() != null && !pedido.getAdicionais().isEmpty()) {
				adicional = true;
				for (Adicional extra : pedido.getAdicionais()) {
					for (int i = 0; i < extra.getQtde(); i++) {
						ingredientesLanche.add(extra.getIngrediente());
					}
				}
			}
			resultado.setTotal(formatValor(calcularTotal(ingredientesLanche, adicional)));
		}

		return resultado;
	}

	/**
	 * Calculo do valor total do pedido.
	 * 
	 * @param ingredientesLanche Lista de {@link Ingrediente} ingredientes do lanche pedido.
	 * @param adicional Indicativo se o pedido possui adicionais.
	 * @return {@link BigDecimal} Valor calculado.
	 */
	public BigDecimal calcularTotal(List<Ingrediente> ingredientesLanche, boolean adicional) {
		BigDecimal total = BigDecimal.ZERO;

		for (Ingrediente ingrediente : ingredientesLanche) {
			total = total.add(this.getValor(ingrediente));
		}

		// Caso possua adicionais, verificar e calcular as promoções
		if (adicional) {
			// Calcular promoções: MUITA CARNE e MUITO QUEIJO
			total = muitaCarneOuQueijo(ingredientesLanche, total);
			// Calcular promoção:LIGHT
			total = light(ingredientesLanche, total);
		}
		return total;
	}

	/**
	 * Calculo da promoção muita carne ou queijo
	 * 
	 * @param ingredientes Lista de {@link Ingrediente} ingredientes do lanche.
	 * @param total Valor total.
	 * @return {@link BigDecimal} Valor calculado.
	 */
	private BigDecimal muitaCarneOuQueijo(List<Ingrediente> ingredientes, BigDecimal total) {
		int carne = 0;
		int queijo = 0;

		for (Ingrediente ingrediente : ingredientes) {
			if (ingrediente == Ingrediente.BURGER) {
				++carne;
			}
			if (ingrediente == Ingrediente.QUEIJO) {
				++queijo;
			}
		}

		// A cada 3 ingredientes de carne ou queijo um é grátis
		BigDecimal descCarne = new BigDecimal(carne / 3).setScale(1, RoundingMode.DOWN);
		BigDecimal descQueijo = new BigDecimal(queijo / 3).setScale(1, RoundingMode.DOWN);
		BigDecimal desconto = this.getValor(Ingrediente.BURGER).multiply(descCarne)
				.add(this.getValor(Ingrediente.QUEIJO).multiply(descQueijo));

		return total.subtract(desconto);
	}

	/**
	 * Calculo da promoção light
	 * 
	 * @param ingredientesLanche Lista de {@link Ingrediente} ingredientes do lanche pedido.
	 * @param total Valor total do pedido.
	 * @return {@link BigDecimal} Valor calculado pela promoção.
	 */
	private BigDecimal light(List<Ingrediente> ingredientesLanche, BigDecimal total) {
		BigDecimal desconto = BigDecimal.ZERO;
		boolean alface = false;
		boolean bacon = false;

		for (Ingrediente ingrediente : ingredientesLanche) {
			if (ingrediente == Ingrediente.ALFACE) {
				alface = true;
			}
			if (ingrediente == Ingrediente.BACON) {
				bacon = true;
			}
		}

		// Caso o pedido possui o ingrediente alface e não tem bacon, aplica-se
		// o desconto de 10% sobre o valor
		if (alface && !bacon) {
			desconto = total.multiply(new BigDecimal(10)).divide(new BigDecimal(100));
		}
		return total.subtract(desconto);
	}
	
	public BigDecimal getValor(Ingrediente tipo) {
		switch (tipo) {
		case ALFACE:
			return new BigDecimal(0.40);
		case BACON:
			return new BigDecimal(2.00);
		case BURGER:
			return new BigDecimal(3.00);
		case OVO:
			return new BigDecimal(0.80);
		case QUEIJO:
			return new BigDecimal(1.50);
		default:
			break;
		}
		return BigDecimal.ZERO;
	}
	
	private String formatValor(BigDecimal bd) {
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(2);
		return df.format(bd);
	}
}
