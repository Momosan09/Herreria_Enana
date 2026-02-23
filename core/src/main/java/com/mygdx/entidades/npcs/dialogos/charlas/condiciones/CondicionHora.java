package com.mygdx.entidades.npcs.dialogos.charlas.condiciones;

import com.mygdx.entidades.Npc;
import com.mygdx.entidades.npcs.dialogos.charlas.Condicion;
import com.mygdx.utiles.EstadoMundo;

public class CondicionHora implements Condicion {

    private int horaMin;
    private int horaMax;

    public CondicionHora(int horaMin, int horaMax) {
        this.horaMin = horaMin;
        this.horaMax = horaMax;
    }

    @Override
    public boolean seCumple(EstadoMundo mundo, Npc npc) {
        int horaActual = mundo.getHora();
        return horaActual >= horaMin && horaActual <= horaMax;
    }
}
