package com.mygdx.entidades.npcs.dialogos;

import java.util.ArrayList;

import com.mygdx.utiles.Recursos;

public enum Npc_Dialogos_Vendedor_Ambulante implements DialogosNPC{

	SALUDO(Recursos.bundle.get("vendedorAmbulante_saludo_dialogo")),
	RESPUESTA_1_SALUDO(Recursos.bundle.get("vendedorAmbulante_saludo_respuesta_1")),
	RESPUESTA_2_SALUDO(Recursos.bundle.get("vendedorAmbulante_saludo_respuesta_2"));
	
	private final String _mensaje;
	
	Npc_Dialogos_Vendedor_Ambulante(String mensaje){
		this._mensaje = mensaje;
	}

	@Override
	public String getMensaje(int index) {
		// TODO Auto-generated method stub
		return _mensaje;
	}

	
	public static ArrayList<String> obtenerTodosLosMensajes() {
        ArrayList<String> mensajes = new ArrayList<>();
        for (Npc_Dialogos_Vendedor_Ambulante dialogo : values()) {
            mensajes.add(dialogo._mensaje);
        }
        return mensajes;
    }

}
