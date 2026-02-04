package com.mygdx.entidades.jugador;

import java.util.ArrayList;
import java.util.HashMap;

import com.mygdx.eventos.Listeners;
import com.mygdx.historia.Mision;
import com.mygdx.historia.MisionHablar;
import com.mygdx.historia.MisionesDelJuego;
import com.mygdx.historia.TipoMision;
import com.mygdx.historia.misiones.MisionRecFab;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.sonidos.ListaSonidos;
import com.mygdx.utiles.sonidos.SonidosManager;

public class TareasInventarioManager {

	private HashMap<String,Mision> tareas = new HashMap<String,Mision>();
	
	public void agregarMision(MisionesDelJuego misionD) {
		Mision mision = null;
		switch (misionD.getTipo()) {
		case FABRICAR:
			mision = new MisionRecFab(misionD);
			break;
		case RECOLECTAR:
			mision = new MisionRecFab(misionD);
			break;
			
		case ENTREGAR:
			break;
			
		case HABLAR:
			mision = new MisionHablar(misionD);
			break;
		}
		
		if(mision == null) {
			System.out.println(HelpDebug.debub(getClass())+ "error en agregarMision()");
		}
		
		tareas.put(mision.getId(), mision);
		Listeners.misionAgregada(mision);
		SonidosManager.reproducirSonido(ListaSonidos.MISION_RECIBIDA);
	}
	
	public void agregarMision(Mision mision) {
		tareas.put(mision.getId(), mision);
		Listeners.misionAgregada(mision);
		SonidosManager.reproducirSonido(ListaSonidos.MISION_RECIBIDA);
	}
	
	public HashMap<String, Mision> getMisiones() {
		return tareas;
		
	}
	
	public boolean buscarMisionPorId(String id) {
		if (tareas.containsKey(id)) {
			return true;
		}else {
			return false;
		}
	}
	
	public Mision conseguirMisionPorId(MisionesDelJuego mision) {
			if(!tareas.isEmpty()) {
				return buscarMisionDevolverHijo(mision);
			}
		return null;
	}
	
	public ArrayList<MisionRecFab> conseguirMisionesPorTipo(TipoMision tipo) {

		ArrayList<MisionRecFab> misionesFiltradas = new ArrayList<>();

		for (Mision mision : tareas.values()) {
			if (mision.getTipo() == tipo && mision instanceof MisionRecFab) {
				misionesFiltradas.add((MisionRecFab) mision);
			}
		}

		return misionesFiltradas;
	}


	
	/**
	 * Se le pasa una MisionDelJuego y devuelve el tipo exacto de la mision
	 * Por ejemplo: return MisionRecFab
	 * @param mision
	 * @return
	 */
	private Mision buscarMisionDevolverHijo(MisionesDelJuego mision) {
		Mision m = tareas.get(mision.getId());
		switch (mision.getTipo()) {
		case FABRICAR:
			return (MisionRecFab)m;
			
		case RECOLECTAR:
			return (MisionRecFab)m;
			
		case HABLAR:
			return (MisionHablar)m;

		}
		return m;
	}
	
	public void avanzarMision(MisionesDelJuego n) {
		Mision mision = tareas.get(n.getId());
		switch (n.getTipo()) {
		case FABRICAR:
	    	MisionRecFab m = (MisionRecFab) mision;
	    	m.setCantidadConseguida(1);
	    	m.comprobarCondicion();
	    	
	    	break;
		case RECOLECTAR:
	    	MisionRecFab m1 = (MisionRecFab) mision;
	    	m1.setCantidadConseguida(1);
	    	m1.comprobarCondicion();
			break;
		case HABLAR:
			break;
		case ENTREGAR:
			break;
		default:
			break;


		}
		
	}
	
	public void avanzarMision(Mision n) {
		Mision mision = tareas.get(n.getId());
		switch (mision.getTipo()) {
		case FABRICAR:
	    	MisionRecFab m = (MisionRecFab) mision;
	    	m.setCantidadConseguida(1);
		case RECOLECTAR:
	    	MisionRecFab m1 = (MisionRecFab) mision;
	    	m1.setCantidadConseguida(1);
			
		case HABLAR:


		}
			
	}
	
	public void avanzarMision(Mision n, int cantidad) {
		Mision mision = tareas.get(n.getId());
		switch (mision.getTipo()) {
		case FABRICAR:
	    	MisionRecFab m = (MisionRecFab) mision;
	    	m.setCantidadConseguida(cantidad);
		case RECOLECTAR:
	    	MisionRecFab m1 = (MisionRecFab) mision;
	    	m1.setCantidadConseguida(cantidad);
			
		case HABLAR:


		}
			
	}
	
	public void avanzarMision(MisionesDelJuego n, int cantidad) {
		Mision mision = tareas.get(n.getId());
		switch (n.getTipo()) {
		case FABRICAR:
	    	MisionRecFab m = (MisionRecFab) mision;
	    	m.setCantidadConseguida(cantidad);
		case RECOLECTAR:
	    	MisionRecFab m1 = (MisionRecFab) mision;
	    	m1.setCantidadConseguida(cantidad);
			
		case HABLAR:


		}
	}
}
