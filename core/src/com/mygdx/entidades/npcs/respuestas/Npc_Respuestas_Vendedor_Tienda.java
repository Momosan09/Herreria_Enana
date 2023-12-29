package com.mygdx.entidades.npcs.respuestas;

import java.util.ArrayList;

import com.mygdx.entidades.npcs.dialogos.DialogosNPC;
import com.mygdx.utiles.Recursos;

public enum Npc_Respuestas_Vendedor_Tienda implements DialogosNPC{
	
	RESPUESTA_1_DIALOGO_1(Recursos.bundle.get("vendedor_tienda_Respuesta_1_Dialogo_1")),
	RESPUESTA_2_DIALOGO_1(Recursos.bundle.get("vendedor_tienda_Respuesta_2_Dialogo_1"));
	
	private final String _mensaje;
	
	Npc_Respuestas_Vendedor_Tienda(String mensaje) {
		this._mensaje = mensaje;
	}
	
	@Override
	public String getMensaje(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static ArrayList<String> obtenerTodosLosMensajes() {
        ArrayList<String> mensajes = new ArrayList<>();
        for (Npc_Respuestas_Vendedor_Tienda dialogo : values()) {
            mensajes.add(dialogo._mensaje);
        }
        return mensajes;
    }


}
