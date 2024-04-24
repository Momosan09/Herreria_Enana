package com.mygdx.entidades.npcs.dialogos;

import java.util.ArrayList;

import com.mygdx.utiles.Recursos;

public enum Npc_Dialogos_Carpintero implements DialogosNPC{

		//Charla 1 - Primer Dialogo - Saludo 
		SALUDO(Recursos.bundle.get("carpintero_saludo")),
		RESPUESTA_1_SALUDO(Recursos.bundle.get("carpintero_saludo_respuesta_1")),
		RESPUESTA_2_SALUDO(Recursos.bundle.get("carpintero_saludo_respuesta_2")),
	
		//Charla 2 - Sobre la guerra
		SOBRE_LA_GUERRA(Recursos.bundle.get("carpintero_sobre_la_guerra")),
		SOBRE_LA_GUERRA_RESPUESTA_1(Recursos.bundle.get("carpintero_sobre_la_guerra_respuesta_1")),
		SOBRE_LA_GUERRA_RESPUESTA_2(Recursos.bundle.get("carpintero_sobre_la_guerra_respuesta_2")),
		
		//Charla 3 - Mision: Sierra circular
		MISION_CARPINTERO_SIERRA_CIRCULAR(Recursos.bundle.get("carpintero_mision_sierra_circular")),
		MISION_CARPINTERO_SIERRA_CIRCULAR_RESPUESTA_1(Recursos.bundle.get("carpintero_mision_sierra_circular_respuesta_1")),
		MISION_CARPINTERO_SIERRA_CIRCULAR_RESPUESTA_2(Recursos.bundle.get("carpintero_mision_sierra_circular_respuesta_2"));
		
		private final String _mensaje;
		
		Npc_Dialogos_Carpintero(String mensaje){
			this._mensaje = mensaje;
		}

		@Override
		public String getMensaje(int index) {
			// TODO Auto-generated method stub
			return _mensaje;
		}

		
		public static ArrayList<String> obtenerTodosLosMensajes() {
	        ArrayList<String> mensajes = new ArrayList<>();
	        for (Npc_Dialogos_Carpintero dialogo : values()) {
	            mensajes.add(dialogo._mensaje);
	        }
	        return mensajes;
	    }

	}

