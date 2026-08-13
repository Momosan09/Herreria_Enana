package com.mygdx.eventos;

import java.util.EventListener;

import com.mygdx.entidades.Npc;

public interface EventoInteraccion extends EventListener{
	void interactuar(Npc npc);
}
