package com.mygdx.entidades.ObjetosDelMapa;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetoDelMapa;
import com.mygdx.hud.Fundicion;

public class Horno extends Entidad {
    private Fundicion hud;

    public Horno(float x, float y, World world, String rutaTextura, Fundicion hud) {
        super(x, y, world, rutaTextura);
        crearCuerpo(world,16,16);
        this.hud = hud;
    }

    public void mostrarHUD(Jugador jugador) {
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

