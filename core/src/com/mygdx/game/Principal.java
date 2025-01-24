package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.pantallas.Juego;
import com.mygdx.pantallas.PantallaConfiguracion;
import com.mygdx.pantallas.PantallaMenu;
import com.mygdx.utiles.Config;
import com.mygdx.utiles.Render;
import com.mygdx.utiles.Tiempo;

public class Principal extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	
	@Override
	public void create() {

		Tiempo.setMomentoInicioJuego(TimeUtils.millis()); //#1
		Tiempo.contarSegundosJuegoAbierto();//#2
		/*
		* TimeUtils.millis() devuelve los milisegundos transcurridos desde el primero de junio de 1970. esa fecha es t=0
		* #1 guarda el valor que devuelve TimeUtils.millis() apenas se inicia el juego, entonces si pasaron 10 milisegundos desde el t0=0 el t0j=0 del juego es t0j= t0
		* #2 usando t0j podemos calcular los segundos que el juego estuvo abierto con una simple cuenta (ver definicion ed #2)
		*
		*/
		if(Config.prefs.getBoolean("pantallaCompleta")) {
			Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		}else {
			Gdx.graphics.setWindowedMode(Config.prefs.getInteger("pantallaAncho"), Config.prefs.getInteger("pantallaAlto"));
		}
		batch = new SpriteBatch();
		Render.batch = batch;
		font = new BitmapFont();
	    this.setScreen(new PantallaConfiguracion(this));
	}

	@Override
	public void render() {
	    ScreenUtils.clear(0, 0, 0, 1);
	    super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		Render.batch.dispose();
		font.dispose();
		Config.prefs.flush();//guarda los cambios de las preferences
	}
}
