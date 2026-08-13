package com.mygdx.entidades.npcs.dialogos.charlas;

import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.Npc;
import com.mygdx.eventos.EventoSeleccionRespuesta;
import com.mygdx.eventos.Listeners;
import com.mygdx.utiles.EstadoMundo;
import com.mygdx.utiles.HelpDebug;


public class DialogoManager implements EventoSeleccionRespuesta{

	private Jugador jugador;
    private Npc npcActual;
    private EstadoMundo mundo;

    public DialogoManager(EstadoMundo mundo, Jugador jugador) {
        this.mundo = mundo;
        this.jugador = jugador;
        Listeners.agregarListener(this);
    }

    public void iniciar(Npc npc) {
    	if(npc != null) {    		
        this.npcActual = npc;
        //npc.iniciarCharla(mundo);
    	}
    }

    public void elegirRespuesta(int indice) {

        Charla charla = npcActual.getCharlaActual();

        if (charla == null) return;

        Respuesta respuesta = charla.respuestas().get(indice);

        respuesta.consecuencia().ejecutar(mundo, npcActual, jugador);

    }

	@Override
	public void respuestaSeleccionada(Respuesta respuesta) {
		respuesta.consecuencia().ejecutar(mundo, npcActual, jugador);
		
	}

}
