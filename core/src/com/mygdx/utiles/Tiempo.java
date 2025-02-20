package com.mygdx.utiles;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.enums.EstadosDelJuego;

public abstract class Tiempo {

	//LA CLASE Iluminacion.java ES BASTANTE IMPORTANTE CON RESPECTO AL TIEMPO Y ESO, QUIZAS LO QUE BUSQUES ESTA AHI
	
    private static long momentoDeInicioJuego;
    private static long tiempoDelJuego; // Este es el tiempo total en juego
    private static long empiezaGameplay = 0;
    private static long segundos;
    private static long tiempoPausado = 0;
    private static long inicioPausa = 0;
    private static boolean enPausa = false;

    // Metodo para inicializar el momento de inicio del juego
    public static void setMomentoInicioJuego(long milis) {
        momentoDeInicioJuego = milis;
    }

    // Devuelve los segundos totales desde que se abrio el juego
    public static void contarSegundosJuegoAbierto() {
        segundos = (TimeUtils.millis() - momentoDeInicioJuego) / 1000;
    }

    // Devuelve los segundos en los que el juego esta en estado de "no pausa"
    public static void contarSegundosEnEstadoJuego() {
        if (MundoConfig.estadoJuego != null) {
            if (empiezaGameplay == 0) empiezaGameplay = TimeUtils.millis(); // Inicializa el inicio del gameplay

            // Si el estado del juego no es PAUSA
            if (!MundoConfig.pausarTiempo) {
                if (enPausa) {
                    // Si esta saliendo de la pausa, sumar el tiempo que estuvo en pausa
                    tiempoPausado += TimeUtils.millis() - inicioPausa;
                    enPausa = false;
                }
                // Calcular el tiempo del juego activo, sin contar el tiempo pausado
                tiempoDelJuego = ((TimeUtils.millis() - tiempoPausado) - empiezaGameplay) / 1000;
                //System.out.println(tiempoDelJuego + " segundos en el juego activo");

            } else {
                // Si esta en pausa, marca el tiempo de inicio de la pausa solo una vez
                if (!enPausa) {
                    inicioPausa = TimeUtils.millis();
                    enPausa = true;
                }
            }
        } else {
            System.err.println("estadoJuego es nulo \n esta linea sale de la clase Tiempo");
        }
    }
    
    public static long getSegundosEnEstadoJuego() {
    	return tiempoDelJuego;
    }
    
    public static void actorEsperar(final Actor actor, int tiempo) {
	    // Añade una secuencia de acciones:
	    // - Espera 2 segundos (Actions.delay(2))
	    // - Luego oculta el label (Actions.visible(false))
    	actor.addAction(Actions.sequence(
	        Actions.delay(tiempo),        // Espera 
	        Actions.fadeOut(.5f)

	   ));
	    actor.getColor().a = 1;
    }

}
