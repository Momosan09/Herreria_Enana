package com.mygdx.historia;

import java.util.ArrayList;

import com.mygdx.entidades.npcs.dialogos.Npc_Dialogos_Rey;
import com.mygdx.eventos.Listeners;
import com.mygdx.hud.CartaHUD;
import com.mygdx.utiles.MundoConfig;

public abstract class CartasManager {

	private static CartaHUD[] cartas = {
			new CartaHUD(Npc_Dialogos_Rey.CARTA_0),
			new CartaHUD(Npc_Dialogos_Rey.CARTA_1),
			new CartaHUD(Npc_Dialogos_Rey.CARTA_2),
	};

	
	/**
	Contiene las condiciones de aparicion para cada carta del juego
	@return Devuelve la carta correspondiente basada en distintas condiciones
	*/
	public static CartaHUD determinarCarta() {
		if(MundoConfig.diasTranscurridos == 0) {
			return cartas[0];
		}else {
			return null;
		}
	}
	
	
}
