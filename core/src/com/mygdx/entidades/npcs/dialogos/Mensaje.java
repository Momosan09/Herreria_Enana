package com.mygdx.entidades.npcs.dialogos;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.enums.CaracterMensajes;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;

public class Mensaje {

    private Label mensaje;
    private Label.LabelStyle anadido, quitado;

    public Mensaje() {
        mensaje = new Label("", EstiloFuente.generarFuente(16, Colores.BLANCO, false));
    }

    public Mensaje(CaracterMensajes caracter) {
    	crearEstilos();
    	switch (caracter) {
		case ANADIDO:
			mensaje = new Label("", anadido);
			
			break;
		case QUITADO:
			mensaje = new Label("", quitado);
			break;
		}
    }

    public void mostrarMensajeTemporal(String msg, float duracion) {
    	mensaje.setPosition(duracion, duracion);
        mensaje.setText(msg); // Establecer el mensaje
        mensaje.setVisible(true); // Hacer visible la etiqueta
        Timer.schedule(new Task(){
            @Override
            public void run() {
                mensaje.setVisible(false); // Hacer invisible la etiqueta después de la duración especificada
            }
        }, duracion); // Especificar la duración del mensaje antes de ejecutar la tarea
    }

    private void crearEstilos() {
    	anadido = EstiloFuente.generarFuente(16, Colores.VERDE, false);
    	quitado = EstiloFuente.generarFuente(16, Colores.ROJO, false);
    }
}
