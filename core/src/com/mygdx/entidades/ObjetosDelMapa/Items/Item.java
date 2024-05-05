package com.mygdx.entidades.ObjetosDelMapa.Items;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.entidades.ObjetosDelMapa.Minable.EstadosMinerales;
import com.mygdx.enums.Items;

public class Item {

	private Texture textura;
	private String nombre;
	private Items tipo;
	private int usos;
	private int valor = 0;
	
	public Item(Items tipo) {
		this.tipo = tipo;
		this.textura = tipo.getTextura();
		this.nombre = tipo.getNombre();
		this.usos = tipo.getUsos();
		this.valor = tipo.getValor();
		
	}
	
	public Item(Items tipo, String nombre) {
		this.tipo = tipo;
		this.nombre = nombre;
		this.textura = tipo.getTextura();
		this.usos = -1;
	}
	
	public Items getTipo() {
		return tipo;
	}
	
	public Texture getTextura() {
		return textura;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getUsos() {
		return usos;
	}
	
	public void restarUsos() {
		usos--;
	}
	
	public int getValor() {
		return valor;
	}
}
