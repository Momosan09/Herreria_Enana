package com.mygdx.historia;

import java.util.ArrayList;

import com.mygdx.entidades.Jugador;
import com.mygdx.eventos.EventoCambioDeDia;
import com.mygdx.eventos.EventoRestarDiasDeMision;
import com.mygdx.eventos.Listeners;
import com.mygdx.historia.misiones.MisionRecFab;
import com.mygdx.utiles.HelpDebug;

public class MisionesManager implements EventoRestarDiasDeMision{
	
	private ArrayList<Mision> misiones = new ArrayList<>();
	private Jugador jugador;
	
	public MisionesManager(Jugador jugador) {
		this.jugador = jugador;
		misiones = new ArrayList<>(jugador.getMisiones().values());
        Listeners.agregarListener(this);
	}
	
	private void agregarMision() {
		misiones.clear();
		misiones = new ArrayList<>(jugador.getMisiones().values());

	}
	


	

	/**
	 * resta los dias en los que se puede completar la mision
	 * Se llama con el evento de cambio de dia (en Listeners dentro de otro metodo)
	 */
	@Override
	public void restarDias() {
		for (Mision mision : misiones) {
			if(mision.getDiasRestantes() != -1) {
			mision.restarDias();
			}
		}
		
	}
	
}
