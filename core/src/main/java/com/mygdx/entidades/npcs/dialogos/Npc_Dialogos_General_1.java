package com.mygdx.entidades.npcs.dialogos;

import java.util.ArrayList;

import com.mygdx.utiles.recursos.Recursos;

public enum Npc_Dialogos_General_1 implements DialogosNPC{

	
	//Charla 1 - Primer Dialogo - Saludo 
	SALUDO(Recursos.bundle.get("general_saludo")),
	RESPUESTA_1_SALUDO(Recursos.bundle.get("general_saludo_respuesta_1")),
	RESPUESTA_2_SALUDO(Recursos.bundle.get("general_saludo_respuesta_2"));
	
	

	private final String _mensaje;
	
	Npc_Dialogos_General_1(String mensaje){
		this._mensaje = mensaje;
	}
	
	@Override
	public String getMensaje(int index) {
		return _mensaje;
	}

	
	public static ArrayList<String> obtenerTodosLosMensajes() {
        ArrayList<String> mensajes = new ArrayList<>();
        for (Npc_Dialogos_General_1 dialogo : values()) {
            mensajes.add(dialogo._mensaje);
        }
        return mensajes;
    }

}
