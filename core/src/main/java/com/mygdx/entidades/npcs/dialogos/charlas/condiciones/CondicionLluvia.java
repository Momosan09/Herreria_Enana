package com.mygdx.entidades.npcs.dialogos.charlas.condiciones;

import com.mygdx.entidades.Npc;
import com.mygdx.entidades.npcs.dialogos.charlas.Condicion;
import com.mygdx.utiles.EstadoMundo;

public class CondicionLluvia implements Condicion {

    private boolean debeEstarLloviendo;

    public CondicionLluvia(boolean debeEstarLloviendo) {
        this.debeEstarLloviendo = debeEstarLloviendo;
    }

    @Override
    public boolean seCumple(EstadoMundo mundo, Npc npc) {
        return mundo.isLloviendo() == debeEstarLloviendo;
    }

}
