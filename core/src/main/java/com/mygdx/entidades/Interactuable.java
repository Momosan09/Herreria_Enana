package com.mygdx.entidades;

import com.badlogic.gdx.math.Circle;
import com.mygdx.eventos.EventoInteraccion;

public interface Interactuable extends EventoInteraccion {
    Circle getAreaInteraccion();

}
