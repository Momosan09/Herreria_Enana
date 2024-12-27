package com.mygdx.eventos;

import java.util.EventListener;

import com.mygdx.entidades.Jugador;

public interface EventoMinar extends EventListener{

	void minar(Jugador j, int x, int y);
}
