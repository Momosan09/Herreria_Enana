package com.mygdx.entidades.npcs.dialogos;

import java.util.ArrayList;

public enum NpcData implements DialogosNPC{

	VENDEDOR("Vendedor", Npc_Dialogos_Vendedor.obtenerTodosLosMensajes()),
	VIEJO("Viejin", Npc_Dialogos_Viejo.obtenerTodosLosMensajes());
	
	private final String _nombre;
	private ArrayList<String> _dialogos;

	
	NpcData(String nombre, ArrayList<String> arrayList) {
		this._nombre = nombre;
		_dialogos = arrayList;
		
		
	}

	public String getNombre() {
		return _nombre;
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
	

