package com.mygdx.eventos;

import java.util.EventListener;

import com.mygdx.entidades.npcs.dialogos.charlas.Respuesta;

public interface EventoSeleccionRespuesta extends EventListener{
		void respuestaSeleccionada(Respuesta respuesta);
}
