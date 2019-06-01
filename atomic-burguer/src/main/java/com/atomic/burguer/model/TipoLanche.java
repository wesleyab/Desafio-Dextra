package com.atomic.burguer.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Enumeração dos tipos de lanches.
 * 
 * @author Wesley de Araújo Barros
 */
public enum TipoLanche {
	
	XBACON,
	
	XBURGER,
	
	XEGG,
	
	XEGGBACON;
	
	public static String descricao(TipoLanche tipo) {
		switch (tipo) {
		case XBACON:
			return "X-Bacon (Bacon, Hambúrguer de carne e queijo)";
		case XBURGER:
			return "X-Burger (Hambúrguer de carne e queijo)";
		case XEGG:
			return "X-Egg (Ovo, Hambúrguer de carne e queijo)";
		case XEGGBACON:
			return "X-EGG Bacon (Ovo, bancon, Hambúrguer de carne e queijo)";
		default:
			break;
		}
		return null;
	}
	
	public static List<Ingrediente> ingredientes(TipoLanche tipo) {
		switch (tipo) {
		case XBACON:
			return Arrays.asList(Ingrediente.BACON, Ingrediente.BURGER, Ingrediente.QUEIJO);
		case XBURGER:
			return Arrays.asList(Ingrediente.BURGER, Ingrediente.QUEIJO);
		case XEGG:
			return Arrays.asList(Ingrediente.OVO, Ingrediente.BURGER, Ingrediente.QUEIJO);
		case XEGGBACON:
			return Arrays.asList(Ingrediente.OVO, Ingrediente.BACON, Ingrediente.BURGER, Ingrediente.QUEIJO);
		default:
			break;
		}
		return new ArrayList<Ingrediente>();
	}

}
