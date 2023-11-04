package com.mygdx.io;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.Recursos;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class Entradas implements InputProcessor {

	private boolean arriba = false, abajo = false, izq = false, der = false, enter = false;
	public Sound efectoSonidoTeclas = Gdx.audio.newSound(Gdx.files.internal(Recursos.EFECTO_TECLA_MENU)); //lo hice public para poder disposearlo cuando en donde sea necesario (en Juego, cuando era llamado por PantallaMenu, se escuchaban los sonidos de las teclas)
	private boolean isSoundPlaying = false;
	private int cont = 0;

	private void reproducirEfectoSonido() {// ademas de reproducir el sonido, lo que hace es que no se toman las teclas que no tienen sonido, es decir que si deja el la tecla apretada no se va a escuchar miles de veces, solo cuando se levante y se vuelva a presionar va a volver a sonar
		if (!isSoundPlaying) {
			efectoSonidoTeclas.play();
			isSoundPlaying = true;
			//System.out.println(HelpDebug.debub(getClass())+"Sonido= " + isSoundPlaying);
		} else {
			isSoundPlaying = false;
			//System.out.println(HelpDebug.debub(getClass())+ "Sonido= "+ isSoundPlaying);
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
	public boolean keyUp(int keycode) { // si no llamo a reproducirEfectoSonido() cuando se levanta la tecla, isSoundPlaying queda en true y hay que presionar otra vez para que recien a la siguiente se reproduzca			
		if (keycode == Keys.W) {
			arriba = false;
			reproducirEfectoSonido();	//llamar aca tambien a este metodo lo que hace es setear la variable en false cuando la tecla se levanta
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
		return true;
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
	
	public int seleccionarOpcion(Label[] labels, int textoMinNro, int textoTopeNro) {// Tengo mis dudas sobre la eficiencia de este metodo, por el for que blanquea todos los textos
		if(cont < textoMinNro) cont = textoMinNro;//setea la posicion inicial en la primera label
		
		if (Gdx.input.isKeyJustPressed(Keys.S) && cont < textoTopeNro) {//para que no se pase a un label inferior no seleccionable
			cont++;
		}
		if (Gdx.input.isKeyJustPressed(Keys.W) && cont > textoMinNro) {//para que no se pase a un label superior no seleccionable
			cont--;
		}

		for (int i = 0; i < labels.length; i++) {
			labels[i].setColor(Color.WHITE);
		} 
		labels[cont].setColor(Color.YELLOW);
		
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			return cont;	
		}
		
		return -1;
	}

}
