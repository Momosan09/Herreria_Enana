package com.mygdx.entidades.npcs.dialogos.charlas.consecuencias;

import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.Npc;
import com.mygdx.entidades.npcs.dialogos.charlas.Consecuencia;
import com.mygdx.entidades.npcs.dialogos.charlas.ConsecuenciaJugador;
import com.mygdx.utiles.EstadoMundo;

public class ConsecuenciaAsignarMision implements Consecuencia{

    private String idMision;
    
    public ConsecuenciaAsignarMision(String id) {
        this.idMision = id;
    }
	

	@Override
	public void ejecutar(EstadoMundo mundo, Npc npc, Jugador jugador) {
		//jugador.getInventarios().tareas.agregarMision(idMision);
		
	}

}
