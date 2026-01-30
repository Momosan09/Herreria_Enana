package com.mygdx.enums;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.utiles.Dinero;
import com.mygdx.utiles.recursos.Recursos;

public enum Items {
	//Aca van todos los items que el jugador puede tener y que sean necesarios para realizar alguna accion
	//Ejemplo: PICO, es necesario para minar
	//Ejemplo: AMULETO1, es necesario para x motivo
	
	
	VACIO(new Texture(Recursos.objMapa.VACIO),-1,-1,-1,-1),
	PICO(new Texture(Recursos.itemsYmision.PICO_DER),-1,0,0,13),
	CINCEL(new Texture(Recursos.itemsYmision.CINCEL),-1,0,0,9),
	MAZA(new Texture(Recursos.itemsYmision.MAZA),-1,0,0,24),
	LIMA_PLANA(new Texture(Recursos.itemsYmision.LIMA),-1,0,0,11),
	SIERRA(new Texture(Recursos.itemsYmision.SIERRA),60,0,0,14),
	
	//de forja
	HOJA_ESPADA_HIERRO_0(new Texture(Recursos.HOJA_HIERRO_0)),
	ESPADA_HIERRO_0(new Texture(Recursos.ESPADA_HIERRO_0)),
	
	//de compra
	MANGO_MADERA_0(new Texture(Recursos.itemsYmision.MANGO_MADERA_0),-1,0,0,5),
	
	
	//Esquemas
	ESQUEMA_SIERRA_CIRCULAR(new Texture(Recursos.itemsYmision.ESQUEMA_TEXT)),
	ESQUEMA_HOJA_ESPADA(new Texture(Recursos.itemsYmision.ESQUEMA_TEXT),-1,0,0,2),
	
	//MISION
	DISCO_HIERRO(new Texture(Recursos.itemsYmision.DISCO_HIERRO)),
	SIERRA_CIRCULAR(new Texture(Recursos.itemsYmision.SIERRA_CIRCULAR));

	private Texture textura;
	private int oro, plata, cobre;
	private Dinero valor;
	private int usos;

	Items(Texture textura, int usos, int oro, int plata, int cobre){
		this.textura = textura;
		valor = new Dinero(oro,plata,cobre);
		this.usos = usos;
	}
	
	Items(Texture textura){
		this.textura = textura;
		valor = new Dinero(oro,plata,cobre);
		this.usos = -1;
	}

	public Texture getTextura() {
		return textura;
	}
	
	public String getNombre() {
		String nombre = this.toString();
		return nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
	}
	
	public Dinero getValor() {
		return valor;
	}
	
	public int getUsos() {
		return usos;
	}
}
