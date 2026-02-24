package com.mygdx.entidades.npcs.dialogos.charlas.consecuencias;

import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.Npc;
import com.mygdx.entidades.npcs.dialogos.charlas.ConcecuenciaNpc;
import com.mygdx.entidades.npcs.dialogos.charlas.Consecuencia;
import com.mygdx.utiles.EstadoMundo;

public class ConsecuenciaIniciarCharla implements Consecuencia {

    private String idSiguienteCharla;

    public ConsecuenciaIniciarCharla(String id) {
        this.idSiguienteCharla = id;
    }

    @Override
    public void ejecutar(EstadoMundo mundo, Npc npc, Jugador jugador) {
        npc.setCharlaActual(idSiguienteCharla);
    }
}
