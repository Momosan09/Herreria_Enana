package com.mygdx.entidades.ObjetosDelMapa;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetoDelMapa;
import com.mygdx.hud.Fundicion;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.Recursos;

public class Horno extends ObjetoDelMapa {
    private Fundicion hud;

    public Horno(float x, float y, World world, String rutaTextura, Fundicion hud, Jugador jugador) {
        super(x, y, world, rutaTextura, jugador);
        crearCuerpo(world,16,16);
        this.hud = hud;
    }

	public void mostrarHUD(Jugador jugador) {
		if (getJugadorEnRango() && MundoConfig.apretoE) {
			hud.mostrar();
			hud.tieneHierro(jugador);
		} else if (hud != null) {
			Gdx.input.setInputProcessor(Recursos.muxJuego);//No estoy seguro de que sea la mejor opcion pero bue
			MundoConfig.mostrarHUD=true;
			MundoConfig.apretoE = false;
			hud.ocultar();
		}
	}

    public Fundicion getHUD() {
        return hud;
    }
}

