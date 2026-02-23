package com.mygdx.entidades;

import com.badlogic.gdx.math.Circle;

public interface Interactuable {
    Circle getAreaInteraccion();
    void interactuar(Jugador jugador);
}
