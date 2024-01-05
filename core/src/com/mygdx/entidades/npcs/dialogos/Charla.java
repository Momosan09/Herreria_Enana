package com.mygdx.entidades.npcs.dialogos;

import java.util.ArrayList;

import com.mygdx.entidades.Npc;

public class Charla {

	private Npc locutor;
	
	//Requisitos para que se de la charla
	private int diaEspecifico = -1;
	
	//Texto
	public String nombreCharla; //Un identificador de la charla, ej: "viejo_saludo", "vendedor_venta_1"
	private String mensaje, respuesta1, respuesta2;
	
	public ArrayList<ArrayList<String>> bloques;
	
	public Charla(String nombreCharla, String[] valores) {
		this.nombreCharla = nombreCharla;
		this.mensaje = valores[0];
		this.respuesta1 = valores[1];
		this.respuesta2 = valores[2];
		bloques = new ArrayList<ArrayList<String>>();
		
		bloqueUno();
	}

	public void bloqueUno() {
		ArrayList<String> bloqueUno = new ArrayList();
		
		bloqueUno.add(mensaje);
		bloqueUno.add(respuesta1);
		bloqueUno.add(respuesta2);
		
		bloques.add(bloqueUno);

	}
	/*
	public void bloqueDos() {
		ArrayList<String> bloqueDos = new ArrayList();
		
		bloqueDos.add(_dialogos.get(1));
		bloqueDos.add(_respuestas.get(2));
		bloqueDos.add(_respuestas.get(3));
		
		bloques.add(bloqueDos);
	}
	*/
	
	public String getMensaje() {
		return mensaje;
	}
	
	public String getRespuesta1() {
		return respuesta1;
	}
	
	public String getRespuesta2() {
		return respuesta2;
	}
}
