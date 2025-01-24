package com.mygdx.utiles.idiomas;

public enum IdiomaCompleto {

	//poner con la traduccion propia del idioma
	ARGENTINO("Argentino"), ESPANIOL("Español"), INGLES("English");
	
	private String nombre;
	
	IdiomaCompleto(String nombre){
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
}
