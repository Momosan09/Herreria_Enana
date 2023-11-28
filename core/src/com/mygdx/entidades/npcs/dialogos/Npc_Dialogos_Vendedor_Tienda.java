package com.mygdx.entidades.npcs.dialogos;

import java.util.ArrayList;

import com.mygdx.utiles.Recursos;

public enum Npc_Dialogos_Vendedor_Tienda implements DialogosNPC{
	
	
	SALUDO(Recursos.bundle.get("vendedor_tienda_Dialogo_1")),
	DIALOGO1(Recursos.bundle.get("vendedor_tienda_Dialogo_2")),
	VENTA(Recursos.bundle.get("vendedor_tienda_Dialogo_3"));

	
	private final String _mensaje;
	
	Npc_Dialogos_Vendedor_Tienda(String mensaje){
		this._mensaje = mensaje;
	}

	
	@Override
	public String getMensaje(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static ArrayList<String> obtenerTodosLosMensajes() {
        ArrayList<String> mensajes = new ArrayList<>();
        for (Npc_Dialogos_Vendedor_Tienda dialogo : values()) {
            mensajes.add(dialogo._mensaje);
        }
        return mensajes;
    }


}
