package com.mygdx.entidades.ObjetosDelMapa;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetoDelMapa;
import com.mygdx.hud.YunqueHUD;

public class Yunque extends Entidad{

	private YunqueHUD hud;

	public Yunque(float x, float y, World world, String rutaTextura, YunqueHUD hud) {
		super(x, y, world, rutaTextura);
		crearCuerpo(world, this.textura.getWidth(), this.textura.getHeight());
		this.hud = hud;
	}

	public void mostarHUD(Jugador jugador) {
		if (getJugadorEnRango() && apretoE) {
			hud.mostrar();
		} else if (hud != null) {
			hud.ocultar();
		}
	}

	public YunqueHUD getHUD() {
		return hud;
	}

}
