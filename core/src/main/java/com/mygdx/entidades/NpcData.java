package com.mygdx.entidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.recursos.Recursos;

public enum NpcData{

	VENDEDOR_AMBULANTE("Vendedor Ambulante",  Recursos.npc.enanos.portraits.VENDEDOR_AMBULANTE_PORTRAIT),
	VIEJO("Viejin", Recursos.npc.enanos.portraits.VENDEDOR_AMBULANTE_PORTRAIT),
	VENDEDOR_TIENDA("Vendedor Tienda", Recursos.npc.enanos.portraits.VENDEDOR_TIENDA_PORTRAIT),
	CARPINTERO("Carpintero", Recursos.npc.enanos.portraits.VENDEDOR_AMBULANTE_PORTRAIT),
	GENERAL_1("General_1", Recursos.npc.enanos.portraits.VENDEDOR_AMBULANTE_PORTRAIT),
	REY("Rey", Recursos.npc.enanos.portraits.VENDEDOR_AMBULANTE_PORTRAIT);
	
	private final String _nombre;
	private final Texture _retrato;
	private ArrayList<String> _dialogos;
	private ArrayList<String[]> paqueteDeCharlas;

	NpcData(String nombre, String retratoRuta) {
		this._nombre = nombre;
		this._retrato = new Texture(retratoRuta);
		
	}

	public String getNombre() {
		return _nombre;
	}
	
	public Texture getTextura() {
		return _retrato;
	}
	



}