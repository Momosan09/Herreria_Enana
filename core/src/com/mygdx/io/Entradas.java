package com.mygdx.io;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.utiles.Recursos;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class Entradas implements InputProcessor {

	private boolean arriba = false, abajo = false, izq = false, der = false, enter = false;
	private Sound efectoSonidoTeclas = Gdx.audio.newSound(Gdx.files.internal(Recursos.EFECTO_TECLA_MENU));
	private boolean isSoundPlaying = false;

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

}
