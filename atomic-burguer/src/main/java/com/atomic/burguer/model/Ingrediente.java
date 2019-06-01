package com.atomic.burguer.model;

/**
 * Enumeração dos tipos de ingredientes.
 */
public enum Ingrediente {
	
	ALFACE,
	
	BACON,
	
	BURGER,
	
	OVO,
	
	QUEIJO;
	
	public static String nome(Ingrediente tipo) {
		switch (tipo) {
		case ALFACE:
			return "Alface";
		case BACON:
			return "Bacon";
		case BURGER:
			return "Hambúrguer de carne";
		case OVO:
			return "Ovo";
		case QUEIJO:
			return "Queijo";
		default:
			break;
		}
		return null;
	}
}
