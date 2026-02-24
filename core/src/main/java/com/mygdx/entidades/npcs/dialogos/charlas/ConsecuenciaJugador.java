package com.mygdx.entidades.npcs.dialogos.charlas;

import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.Npc;
import com.mygdx.utiles.EstadoMundo;

public interface ConsecuenciaJugador extends Consecuencia{

	   void ejecutar(Jugador jugador);
}
