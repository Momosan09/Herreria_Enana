package com.mygdx.eventos;

import java.util.EventListener;

public interface EventoHudConexion extends EventListener{
	void agregarMensaje(String msg, boolean error);

}
