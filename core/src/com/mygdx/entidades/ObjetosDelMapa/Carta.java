package com.mygdx.entidades.ObjetosDelMapa;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetoDelMapa;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.eventos.EventoInteraccionObj;
import com.mygdx.eventos.Listeners;
import com.mygdx.historia.CartasManager;
import com.mygdx.hud.CartaHUD;
import com.mygdx.hud.YunqueHUD;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;

public class Carta extends ObjetoDelMapa implements EventoInteraccionObj{
	
	private boolean dibujar = false;
	private boolean primeraVez = true; // primera vez que lee la carta
	private Sprite sinLeer;
	private PointLight luzExclamacion;
	
	public Carta(float x, float y, World world, String rutaTextura, Jugador jugador) {
		super(x, y, world, rutaTextura, jugador);
		sinLeer = new Sprite(new Texture(Recursos.EXCLAMACION));
		sinLeer.setPosition(this.posicion.x-8, this.posicion.y+24);
		luzExclamacion = new PointLight(Render.rayHandler, 128, new Color(Color.valueOf("#ea8e0e")), 30, this.posicion.x+10, this.posicion.y+30);
		 Listeners.agregarListener(this);
	}

	@Override
	public void draw() {
        //Ver si hay carta para ese dia
        CartaHUD cartaDelDia = CartasManager.determinarCarta();
        if(cartaDelDia != null) {
        	if(primeraVez) {
        		luzExclamacion.setActive(true);
        		sinLeer.draw(Render.batch);        		
        	}else {
        		luzExclamacion.setActive(false);
        	}
    		sprite.draw(Render.batch);    	//No muestra la carta si no hay carta
        }else {
        	luzExclamacion.setActive(false);
        	primeraVez = true;
        	//System.out.println("No hay carta para hoy \n");
        }		

		
	}
	
	
	@Override
	public void interaccionObj() {
		if(getJugadorEnRango()) {
				primeraVez = false;
				MundoConfig.estadoJuego = EstadosDelJuego.CARTA;
			}
		}
	}
	

