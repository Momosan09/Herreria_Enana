package com.mygdx.entidades.npcs.dialogos;

import java.util.ArrayList;

import com.mygdx.utiles.Recursos;

public enum Npc_Dialogos_Vendedor implements DialogosNPC{

	SALUDO(Recursos.bundle.get("vendedor_Dialogo_1")),
	DIALOGO1(Recursos.bundle.get("vendedor_Dialogo_2")),
	VENTA(Recursos.bundle.get("vendedor_Frase_Venta"));
	
	private final String _mensaje;
	
	Npc_Dialogos_Vendedor(String mensaje){
		this._mensaje = mensaje;
	}

	@Override
	public String getMensaje(int index) {
		// TODO Auto-generated method stub
		return _mensaje;
	}

	
	public static ArrayList<String> obtenerTodosLosMensajes() {
        ArrayList<String> mensajes = new ArrayList<>();
        for (Npc_Dialogos_Vendedor dialogo : values()) {
            mensajes.add(dialogo._mensaje);
        }
        return mensajes;
    }

}
