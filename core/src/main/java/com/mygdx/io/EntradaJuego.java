package com.mygdx.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.eventos.Listeners;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.MundoConfig;

public class EntradaJuego implements InputProcessor{

	@Override
	public boolean keyDown(int keycode) {
		
		if(keycode == Keys.E) {
			Listeners.interaccion();
		}
		
		if(keycode == Keys.TAB) {
			MundoConfig.estadoJuego = (MundoConfig.estadoJuego == EstadosDelJuego.JUEGO ? EstadosDelJuego.INVENTARIO : EstadosDelJuego.JUEGO);
			System.out.println(HelpDebug.debub(getClass())+ "Inventario");
		}
		if(keycode == Keys.CONTROL_LEFT) {
			MundoConfig.estadoJuego = (MundoConfig.estadoJuego == EstadosDelJuego.JUEGO ? EstadosDelJuego.INVENTARIO_BATALLAS : EstadosDelJuego.JUEGO);
		}
		if(keycode == Keys.ESCAPE) {
			MundoConfig.estadoJuego = (MundoConfig.estadoJuego == EstadosDelJuego.JUEGO? EstadosDelJuego.PAUSA : EstadosDelJuego.JUEGO);
			System.out.println(HelpDebug.debub(getClass())+ "Pausa");
		}
		if(keycode == Keys.SHIFT_LEFT) {
			MundoConfig.estadoJuego = (MundoConfig.estadoJuego == EstadosDelJuego.JUEGO ? EstadosDelJuego.COMBINACION: EstadosDelJuego.JUEGO);
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

}
