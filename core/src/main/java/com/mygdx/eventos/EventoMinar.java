package com.mygdx.eventos;

import java.util.EventListener;

import com.mygdx.entidades.Jugador;

public interface EventoMinar extends EventListener {

    boolean contieneClick(Jugador jugador, int x, int y);

    void minar(Jugador jugador, int x, int y);
}