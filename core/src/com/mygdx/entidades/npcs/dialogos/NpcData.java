package com.mygdx.entidades.npcs.dialogos;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.utiles.Recursos;

public enum NpcData implements DialogosNPC{

	VENDEDOR("Vendedor",  Recursos.VENDEDOR_PORTRAIT,Npc_Dialogos_Vendedor.obtenerTodosLosMensajes()),
	VIEJO("Viejin", Recursos.VENDEDOR_PORTRAIT, Npc_Dialogos_Viejo.obtenerTodosLosMensajes());
	
	private final String _nombre;
	private final Texture _retrato;
	private ArrayList<String> _dialogos;

	
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
	

