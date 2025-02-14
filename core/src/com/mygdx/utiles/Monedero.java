package com.mygdx.utiles;

/**
 * Esta clase es para todo lo que tenga que manejar el dinero
 * @author  Momosan09
 *
 */
public class Monedero extends Dinero{
	
	public Monedero() {
		super(-1,-1,-1);
	}
	
	public Monedero(int oro, int plata, int cobre) {
		super(oro,plata,cobre);
	}
	
	/**
	 * Cambia el valor de las monedas al pasado por parametro. Si tiene 100 monedas y se pasa 1, entonces el jugador pasa a tener solo una moneda de este tipo
	 */
	public void setMonedasCobre(int cantidad) {
		dinero[2] = cantidad;
	}
	
	/**
	 * Cambia el valor de las monedas al pasado por parametro. Si tiene 100 monedas y se pasa 1, entonces el jugador pasa a tener solo una moneda de este tipo
	 */
	public void setMonedasPlata(int cantidad) {
		dinero[1] = cantidad;
	}
	
	/**
	 * Cambia el valor de las monedas al pasado por parametro. Si tiene 100 monedas y se pasa 1, entonces el jugador pasa a tener solo una moneda de este tipo
	 */
	public void setMonedasOro(int cantidad) {
		dinero[0] = cantidad;
	}
	
	/**
	 * Suma la cantidad enviada por parametro al total de monedas
	 */
	public void agregarMonedasCobre(int cantidad) {
		dinero[2] += cantidad;
	}
	/**
	 * Suma la cantidad enviada por parametro al total de monedas
	 */
	public void agregarMonedasPlata(int cantidad) {
		dinero[1] += cantidad;
	}
	/**
	 * Suma la cantidad enviada por parametro al total de monedas
	 */
	public void agregarMonedasOro(int cantidad) {
		dinero[0] += cantidad;
	}
	
	/**
	 * Resta la cantidad enviada por parametro al total de monedas
	 */
	public void restarMonedasCobre(int cantidad) {
		dinero[2] -= cantidad;
	}
	/**
	 * Resta la cantidad enviada por parametro al total de monedas
	 */
	public void restarMonedasPlata(int cantidad) {
		dinero[1] -= cantidad;
	}
	/**
	 * Resta la cantidad enviada por parametro al total de monedas
	 */
	public void restarMonedasOro(int cantidad) {
		dinero[0] -= cantidad;
	}
	
	
	public boolean estaPobre() {
		if(dinero[0] == 0 && dinero[1] == 0 && dinero[2] == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Resta todas las monedas juntas
	 */
	public void restarDinero(int oro, int plata, int cobre) {
		restarMonedasOro(oro);
		restarMonedasPlata(plata);
		restarMonedasCobre(cobre);
	}
	
	/**
	 * suma todas las monedas a la vez
	 * @param oro
	 * @param plata
	 * @param cobre
	 */
	public void sumarDinero(int oro, int plata, int cobre) {
		agregarMonedasOro(oro);
		agregarMonedasPlata(plata);
		agregarMonedasCobre(cobre);
	}
	
	public boolean tieneElDinero(int valorOro, int valorPlata, int valorCobre) {
		if(dinero[0] >= valorOro && dinero[1] >= valorPlata && dinero[2] >= valorCobre) {
			return true;
		}else {
			return false;
		}
	}
}
