package com.mygdx.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public abstract class Config {

    public static Preferences prefs = Gdx.app.getPreferences("HerreriaEnanaPreferencias");

    // Valores con default
    public static float volumenMusica   = prefs.getFloat("nivelVolumenMusica", 70f);
    public static float volumenMenues   = prefs.getFloat("nivelVolumenMenues", 70f);
    public static float volumenEfectos  = prefs.getFloat("nivelVolumenEfectos", 70f);

    public static boolean permitirParticulas = prefs.getBoolean("permitirParticulas", true);

    public static float ancho = 1280;
    public static float alto = 768;

    public static boolean pantallaCompleta = Gdx.graphics.isFullscreen();

    public static String[] resolucionesString = {"1280 x 720", "1920 x 1080"};
    public static String[] idiomasString = {"Argentino", "Español", "English"};
    public static String idiomaElegido = "Español";

    public static void guardar() {
        prefs.putFloat("nivelVolumenMusica", volumenMusica);
        prefs.putFloat("nivelVolumenMenues", volumenMenues);
        prefs.putFloat("nivelVolumenEfectos", volumenEfectos);
        prefs.putBoolean("permitirParticulas", permitirParticulas);
        prefs.flush();
    }
}
