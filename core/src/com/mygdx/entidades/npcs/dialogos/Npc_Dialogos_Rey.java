package com.mygdx.entidades.npcs.dialogos;

import java.util.ArrayList;
import com.mygdx.utiles.Recursos;

public enum Npc_Dialogos_Rey implements DialogosNPC{
	
	CARTA_0(Recursos.bundle.get("rey_Carta_0")),
	CARTA_1(Recursos.bundle.get("rey_Carta_0")),
	CARTA_2(Recursos.bundle.get("rey_Carta_0"));

	
	private final String _mensaje;
	
	Npc_Dialogos_Rey(String mensaje){
		this._mensaje = mensaje;
	}

	@Override
	public String getMensaje(int index) {
		// TODO Auto-generated method stub
		return _mensaje;
	}

	
	public static ArrayList<String> obtenerTodosLosMensajes() {
        ArrayList<String> mensajes = new ArrayList<>();
        for (Npc_Dialogos_Rey dialogo : values()) {
            mensajes.add(dialogo._mensaje);
        }
        return mensajes;
    }
}
