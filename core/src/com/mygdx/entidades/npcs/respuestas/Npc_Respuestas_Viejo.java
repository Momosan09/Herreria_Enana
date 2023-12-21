package com.mygdx.entidades.npcs.respuestas;

import java.util.ArrayList;

import com.mygdx.entidades.npcs.dialogos.DialogosNPC;

import com.mygdx.utiles.Recursos;

public enum Npc_Respuestas_Viejo implements DialogosNPC{
	
	RESPUESTA_1_DIALOGO_3(Recursos.bundle.get("viejo_Respuesta_1_Dialogo_3")),
	RESPUESTA_2_DIALOGO_3(Recursos.bundle.get("viejo_Respuesta_2_Dialogo_3"))
	;

	private final String _mensaje;
	
	Npc_Respuestas_Viejo(String mensaje) {
		this._mensaje = mensaje;
	}
	
	@Override
	public String getMensaje(int index) {
		// TODO Auto-generated method stub
		return _mensaje;
	}
	
	public static ArrayList<String> obtenerTodosLosMensajes() {
        ArrayList<String> mensajes = new ArrayList<>();
        for (Npc_Respuestas_Viejo dialogo : values()) {
            mensajes.add(dialogo._mensaje);
        }
        return mensajes;
    }

	
}
