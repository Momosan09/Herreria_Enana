package com.mygdx.entidades.npcs.dialogos;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.utiles.Recursos;

public enum NpcData implements DialogosNPC{

	VENDEDOR_AMBULANTE("Vendedor Ambulante",  Recursos.VENDEDOR_AMBULANTE_PORTRAIT, Npc_Dialogos_Vendedor_Ambulante.obtenerTodosLosMensajes()),
	VIEJO("Viejin", Recursos.VENDEDOR_AMBULANTE_PORTRAIT, Npc_Dialogos_Viejo.obtenerTodosLosMensajes()),
	VENDEDOR_TIENDA("Vendedor Tienda", Recursos.VENDEDOR_TIENDA_PORTRAIT, Npc_Dialogos_Vendedor_Tienda.obtenerTodosLosMensajes()),
	REY("Rey", Recursos.VENDEDOR_AMBULANTE_PORTRAIT, Npc_Dialogos_Rey.obtenerTodosLosMensajes());
	
	private final String _nombre;
	private final Texture _retrato;
	private ArrayList<String> _dialogos;
	private ArrayList<String> _respuestas;

	
	NpcData(String nombre, String retratoRuta, ArrayList<String> arrayList) {
		this._nombre = nombre;
		this._retrato = new Texture(retratoRuta);
		_dialogos = arrayList;
	}

	public String getNombre() {
		return _nombre;
	}
	
	public Texture getTextura() {
		return _retrato;
	}
	
    public ArrayList<String> getDialogos() {
        return _dialogos;
    }

	@Override
	public String getMensaje(int index) {
		// TODO Auto-generated method stub
		return _dialogos.get(index);
	}




	
}
	

