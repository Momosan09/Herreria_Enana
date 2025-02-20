package com.mygdx.historia;

import java.util.ArrayList;

import com.mygdx.entidades.npcs.dialogos.Npc_Dialogos_Rey;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.eventos.Listeners;
import com.mygdx.hud.CartaHUD;
import com.mygdx.utiles.MundoConfig;

public abstract class CartasManager {

	private static CartaHUD[] cartas = {
			new CartaHUD(Npc_Dialogos_Rey.CARTA_0),
			new CartaHUD(Npc_Dialogos_Rey.CARTA_1, new Mision(MisionesDelJuego.RC1_FESP)),
			new CartaHUD(Npc_Dialogos_Rey.CARTA_2),
	};

	
	/**
	Contiene las condiciones de aparicion para cada carta del juego
	@return Devuelve la carta correspondiente basada en distintas condiciones
	*/
	public static CartaHUD determinarCarta() {
		if(MundoConfig.diasTranscurridos == 0 &&(MundoConfig.horaDelMundo == 4 && MundoConfig.minutoDelMundo > 6) ){
			return cartas[1];
		}
		if(MundoConfig.diasTranscurridos == 1 &&(MundoConfig.horaDelMundo == 16 && MundoConfig.minutoDelMundo > 0)){
			return cartas[2];
		}
		return null;
	}
	
	public static CartaHUD getPrimeraCarta() {
		return cartas[0];
	}
	
	
}
