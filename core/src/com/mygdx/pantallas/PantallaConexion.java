package com.mygdx.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.Principal;
import com.mygdx.hud.ConexionHUD;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.Render;

import com.mygdx.red.Cliente;
import com.mygdx.red.UtilesRed;

public class PantallaConexion implements Screen{

	private Cliente cliente;
	private ConexionHUD hud;
	private Principal game;
	
	public PantallaConexion(final Principal game) {
		this.game = game;
	}

	@Override
	public void show() {
		hud = new ConexionHUD();
		cliente = new Cliente();
		
	}

	@Override
	public void render(float delta) {
		hud.render();
		if(Gdx.input.isTouched()) {
			System.out.println(HelpDebug.debub(getClass())+"cliente cerrado");
			UtilesRed.hc.enviarMensaje("desconectar");
			cliente.cerrarCliente();
		}
		
		if(UtilesRed.hc.isConexionExitosa() && UtilesRed.hc.isEsperandojugadores()) {
			//hud.agregarMensaje("Conexion Exitosa", false);
			//hud.agregarMensaje("Esperando jugadores", false);
			hud.conexionExitosa = true;
			
		}else if(UtilesRed.hc.isServidorLleno()) {
			hud.agregarMensaje("El servidor esta lleno", true);
			this.game.setScreen(new PantallaMenu(game));
		}
		
		if(UtilesRed.hc.isConexionExitosa() && !UtilesRed.hc.isEsperandojugadores()) {
			hud.agregarMensaje("Iniciando juego", false);
			this.game.setScreen(new Juego(game, true));
			UtilesRed.hc.comenzoJuego = true;//Esto es para setear la proyeccion de la camara
		}
	}

	@Override
	public void resize(int width, int height) {
		hud.reEscalar(width, height);
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
	}
}
