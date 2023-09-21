package com.mygdx.entidades.npcs.dialogos;

import com.mygdx.utiles.Recursos;

public enum Npc_dialogos_vendedor {

	SALUDO(Recursos.bundle.get("vendedor_Dialogo_1"));
	
	private final String mensaje;
	
	Npc_dialogos_vendedor(String mensaje){
		this.mensaje = mensaje;
	}
	
	
}
