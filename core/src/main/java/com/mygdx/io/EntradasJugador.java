package com.mygdx.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.enums.Items;
import com.mygdx.eventos.Listeners;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.entidades.Jugador;

public class EntradasJugador implements InputProcessor{
	private int ultimoNumeroApretado;
	private Jugador jugador;
	
	public EntradasJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		
		if(keycode == Keys.NUM_1) {
			if(ultimoNumeroApretado == 1 && jugador.getItemEnMano() == Items.PICO) {
				jugador.desequipar();
			}else {
				jugador.equipar(1);				
			}
			ultimoNumeroApretado = 1;
		} if(keycode == Keys.NUM_2) {
			if(ultimoNumeroApretado == 2 && jugador.getItemEnMano() == Items.MAZA) {
				jugador.desequipar();
			}else {
				jugador.equipar(2);				
			}
			ultimoNumeroApretado = 2;
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
		if(MundoConfig.estadoJuego == EstadosDelJuego.JUEGO) {	
			if(jugador.getItemEnMano() == Items.PICO) {
				Listeners.minar(jugador, screenX, screenY);
			}
		}
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
