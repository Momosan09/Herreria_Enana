package com.mygdx.entidades.npcs.dialogos.charlas.consecuencias;

import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.Npc;
import com.mygdx.entidades.npcs.dialogos.charlas.Consecuencia;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.utiles.EstadoMundo;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.MundoConfig;

public class ConsecuenciaFinalizarCharla implements Consecuencia{

	@Override
	public void ejecutar(EstadoMundo mundo, Npc npc, Jugador jugador) {
        jugador.resetInteraccion();
		System.out.println(HelpDebug.debub(getClass())+"ADIRO");
		
	}

}
