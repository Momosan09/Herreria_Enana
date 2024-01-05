package com.mygdx.entidades.npcs.dialogos;

import java.util.ArrayList;

import com.mygdx.utiles.Recursos;

public enum Npc_Dialogos_Viejo implements DialogosNPC{
	
	//Charla 1 - Primer Dialogo - Saludo      
	SALUDO(Recursos.bundle.get("viejo_saludo_dialogo")),
	SALUDO_RESPUESTA_1(Recursos.bundle.get("viejo_saludo_respuesta_1")),
	SALUDO_RESPUESTA_2(Recursos.bundle.get("viejo_saludo_respuesta_2")),
	
	//Charla 2 - Se dispara con la respuesta 1 de la charla 1
	MONTANAS_MINERALES(Recursos.bundle.get("viejo_montanas_minerales")),
	MONTANAS_MINERALES_RESPUESTA_1(Recursos.bundle.get("viejo_montanas_minerales_respuesta_1")),
	MONTANAS_MINERALES_RESPUESTA_2(Recursos.bundle.get("viejo_montanas_minerales_respuesta_2"));
	
	//DIALOGO_3(Recursos.bundle.get("viejo_Dialogo_3"));
	
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
