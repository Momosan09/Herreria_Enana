package com.mygdx.entidades.npcs.dialogos.charlas;

import com.mygdx.entidades.Npc;
import com.mygdx.utiles.EstadoMundo;

public interface Consecuencia {
    void ejecutar(EstadoMundo mundo, Npc npc);
}
