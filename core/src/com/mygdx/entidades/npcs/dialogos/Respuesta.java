package com.mygdx.entidades.npcs.dialogos;

import java.util.ArrayList;

import com.mygdx.enums.Caracter;
import com.mygdx.enums.Respuestas;

public class Respuesta {

	public String contenido;
	public Respuestas respuesta; //Este es el valor que elije el jugador, es decir, lo que responde
	public Caracter caracter;//Este es el carcter de la respuesta, si es para negarse, para aceptar, etc
	
	
	public Respuesta(String crudo) {
		
	}
	
	/**
	 * Extrae el string crudo (con etiquetas)
	 * @param crudo
	 */
	private void separarCrudo(String crudo) {
		String[] valores = crudo.split("&%");
		contenido = valores[0];
		determinarEtiqueta(valores[1]);
		
	}
	
	private void determinarEtiqueta(String etiqueta) {
		if(etiqueta.equals("&%FINALIZAR")) {
			caracter = Caracter.FINALIZAR;
		}else if(etiqueta.equals("&%ACEPTAR")) {
			caracter = Caracter.ACEPTAR;
		}else if(etiqueta.equals("&%NEGARSE")) {
			caracter = Caracter.NEGARSE;
		}else {
			System.err.println("NO EXISTE ESTA ETIQUETA");
		}
	}
}
