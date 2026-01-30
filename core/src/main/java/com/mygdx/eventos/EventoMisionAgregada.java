package com.mygdx.eventos;

import java.util.EventListener;

import com.mygdx.historia.Mision;

public interface EventoMisionAgregada extends EventListener{

	void misionAgregada(Mision mision);
}
