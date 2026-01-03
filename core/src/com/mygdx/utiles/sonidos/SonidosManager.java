package com.mygdx.utiles.sonidos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.utiles.Config;
import com.mygdx.utiles.particulas.ListaDeParticulas;
import com.mygdx.utiles.recursos.Recursos;

public class SonidosManager {
	
    private static final ObjectMap<ListaSonidos, Sound> sonidos = new ObjectMap<>();
    private static float volumenFX = Config.volumenEfectos;
    private static long ultimoSonido = 0;
    
    public static void cargar() {
    	agregar(ListaSonidos.MINAR);
    	agregar(ListaSonidos.MISION_RECIBIDA);

    }
    
    private static void agregar(ListaSonidos tipo) {
    	sonidos.put(tipo, Gdx.audio.newSound(Gdx.files.internal(tipo.ruta)));
    }

    public static void reproducirSonido(ListaSonidos tipo) {
    	long ahora = TimeUtils.millis();
        if (sonidos.get(tipo) != null) {
            if (ahora - ultimoSonido > 120) {//Evita el spam de sonidso
            	sonidos.get(tipo).play(volumenFX);
            	ultimoSonido = ahora;
            }
        }
    }
    



//    public static void setVolumenFX(float v) {
//        volumenFX = MathUtils.clamp(v, 0f, 1f);
//    }

    public static void dispose() {
        sonidos.clear();
    }
}
