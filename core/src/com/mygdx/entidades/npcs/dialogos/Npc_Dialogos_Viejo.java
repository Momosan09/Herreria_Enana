package com.mygdx.entidades.npcs.dialogos;

import java.util.ArrayList;

import com.mygdx.utiles.Recursos;

public enum Npc_Dialogos_Viejo implements DialogosNPC{
	
	DIALOGO_1(Recursos.bundle.get("viejo_Dialogo_1")),
	DIALOGO_2(Recursos.bundle.get("viejo_Dialogo_2")),
	DIALOGO_3(Recursos.bundle.get("viejo_Dialogo_3"));
	
	private final String _mensaje;
	
	Npc_Dialogos_Viejo(String mensaje){
		this._mensaje = mensaje;
	}

	@Override
	public String getMensaje(int index) {
		// TODO Auto-generated method stub
		return _mensaje;
	}
	
	public static ArrayList<String> obtenerTodosLosMensajes() {
        ArrayList<String> mensajes = new ArrayList<>();
        for (Npc_Dialogos_Viejo dialogo : values()) {
            mensajes.add(dialogo._mensaje);
        }
        return mensajes;
    }



}
