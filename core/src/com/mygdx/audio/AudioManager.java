package com.mygdx.audio;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.utiles.Recursos;

public abstract class AudioManager {

	private static Sound misionRecibida = Gdx.audio.newSound(Gdx.files.internal(Recursos.SONIDO_MISION_RECIBIDA));
	
	
	public static void reproducirSonidoMisionRecibida() {
		misionRecibida.play(50);
	}
	
	
}
