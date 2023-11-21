package com.mygdx.entidades.ObjetosDelMapa;

import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetoDelMapa;
import com.mygdx.hud.Fundicion;

public class Horno extends Entidad {
    private Fundicion hud;

    public Horno(float x, float y, String rutaTextura, Fundicion hud) {
        super(x, y, rutaTextura);
        this.hud = hud;
    }

    public void mostarHUD(Jugador jugador) {
        if (getJugadorEnRango() && apretoE) {
            hud.mostrar();
            hud.tieneHierro(jugador);
        } else if (hud != null) {
            hud.ocultar();
        }
    }

    public Fundicion getHUD() {
        return hud;
    }
}

