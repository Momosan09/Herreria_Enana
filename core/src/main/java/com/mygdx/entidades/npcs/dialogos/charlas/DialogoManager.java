package com.mygdx.entidades.npcs.dialogos.charlas;

import com.mygdx.entidades.Npc;
import com.mygdx.utiles.EstadoMundo;
import com.mygdx.utiles.MundoConfig;

public class DialogoManager {

    private Npc npcActual;
    private EstadoMundo mundo;

    public DialogoManager(EstadoMundo mundo) {
        this.mundo = mundo;
    }

    public void iniciar(Npc npc) {
    	if(npc != null) {    		
        this.npcActual = npc;
        npc.iniciarCharla(mundo);
    	}
    }

    public void elegirRespuesta(int indice) {

        Charla charla = npcActual.getCharlaActual();

        if (charla == null) return;

        Respuesta respuesta = charla.respuestas().get(indice);

        respuesta.consecuencia().ejecutar(mundo, npcActual);
    }
}
