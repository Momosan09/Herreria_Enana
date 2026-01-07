package com.mygdx.entidades.ObjetosDelMapa.Items;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.combinaciones.Ingrediente;
import com.mygdx.combinaciones.IngredientesId;
import com.mygdx.entidades.ObjetosDelMapa.Minable.EstadosMinerales;
import com.mygdx.enums.Items;
import com.mygdx.utiles.Dinero;

public class Item implements Ingrediente{

	private Texture textura;
	private String nombre;
	private Items tipo;
	protected IngredientesId ingredienteId;
	private int usos;
	private Dinero valor;
	
	public Item(IngredientesId ingredienteId) {
		this.ingredienteId = ingredienteId;
		this.tipo = ingredienteId.tipoI;
		this.textura = tipo.getTextura();
		this.nombre = tipo.getNombre();
		this.usos = tipo.getUsos();
		this.valor = tipo.getValor();
		
	}
	
	public Item(IngredientesId ingredienteId, String nombre) {
		this.ingredienteId = ingredienteId;
		this.tipo = ingredienteId.tipoI;
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
	
	public Dinero getValor() {
		return valor;
	}

	@Override
	public IngredientesId getIngredienteId() {
		return ingredienteId;
	}
}
