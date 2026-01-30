package com.mygdx.utiles;


/**
 * Esta clase es para las cosas que tienen un valor, como los items etc.
 * @author  Momosan09
 *
 */
public class Dinero {

	protected int[] dinero = new int[3];
	
	public Dinero() {
		dinero[0] = 0;
		dinero[1] = 0;
		dinero[2] = 0;
	}
	
	public Dinero(int cantInicialOro, int cantInicalPlata, int cantInicialCobre) {
		dinero[0] = cantInicialOro;
		dinero[1] = cantInicalPlata;
		dinero[2] = cantInicialCobre;
	}
	
	public int getMonedasCobre() {
		return dinero[2];
	}
	
	public int getMonedasPlata() {
		return dinero[1];
	}
	
	public int getMonedasOro() {
		return dinero[0];
	}
	

	
	
}
