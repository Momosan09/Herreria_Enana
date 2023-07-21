package com.mygdx.io;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class Entradas implements InputProcessor{
	
	private boolean arriba = false, abajo = false, izq = false, der = false;

	
	public boolean isArriba(){
		return arriba;
	}
	
	public boolean isAbajo() {
		return abajo;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		

		if(keycode == Keys.W) {
			arriba = true;
		}
		if(keycode == Keys.S) {
			abajo = true;
		}
		return false;
	}



	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.W) {
			arriba = false;
		}
		if(keycode == Keys.S) {
			abajo = false;
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
