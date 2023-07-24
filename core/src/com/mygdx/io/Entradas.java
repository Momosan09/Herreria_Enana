package com.mygdx.io;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Texto;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class Entradas implements InputProcessor {

	private boolean arriba = false, abajo = false, izq = false, der = false, enter = false;
	public Sound efectoSonidoTeclas = Gdx.audio.newSound(Gdx.files.internal(Recursos.EFECTO_TECLA_MENU)); //lo hice public para poder disposearlo cuando en donde sea necesario (en Juego, cuando era llamado por PantallaMenu, se escuchaban los sonidos de las teclas)
	private boolean isSoundPlaying = false;
	private int cont = 0;

	private void reproducirEfectoSonido() {

		if (!isSoundPlaying) {
			efectoSonidoTeclas.play();
			isSoundPlaying = true;
		} else {
			isSoundPlaying = false;
		}
	}

	public boolean isArriba() {
		return arriba;
	}

	public boolean isAbajo() {
		return abajo;
	}

	public boolean isEnter() {
		return enter;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.W) {
			arriba = true;
			reproducirEfectoSonido();
		}
		if (keycode == Keys.S) {
			abajo = true;
			reproducirEfectoSonido();
		}
		if (keycode == Keys.ENTER) {
			enter = true;
			reproducirEfectoSonido();
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.W) {
			arriba = false;
			reproducirEfectoSonido();
		}
		if (keycode == Keys.S) {
			reproducirEfectoSonido();
			abajo = false;
		}
		if (keycode == Keys.ENTER) {
			reproducirEfectoSonido();
			enter = false;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
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
	
	public int seleccionarOpcion(Texto[] textos,int textoMinNro, int textoTopeNro) {// Tengo mis dudas sobre la eficiencia de este metodo, por el for que blanquea todos los textos
		if(cont < textoMinNro) cont = textoMinNro;
		
		if (isAbajo() && cont < textoTopeNro) {
			cont++;
		}
		if (isArriba() && cont > textoMinNro) {
			cont--;
		}

		for (int i = 0; i < textos.length; i++) {
			textos[i].setColor(Color.WHITE);
		} 
		textos[cont].setColor(Color.YELLOW);
		
		if(isEnter()) {
			return cont;	
		}
		
		return -1;
	}

}
