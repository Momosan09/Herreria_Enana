package com.mygdx.entidades.npcs.dialogos;

import java.util.ArrayList;

import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.Npc;
import com.mygdx.historia.FabricablesMision;
import com.mygdx.historia.MisionesDelJuego;
import com.mygdx.historia.TipoMision;

public class CharlaManager {

	//Aca van todas las charlas
	public ArrayList<Charla> vendedorTiendaCharlas;
	private Jugador jugador;
	
	
	public CharlaManager(Jugador jugador, Npc vendedorTienda, Npc vendedorAmbulante, Npc viejo, Npc carpintero) {
		crearCharlasVendedorTienda(vendedorTienda);
		crearCharlasVendedorAmbulante(vendedorAmbulante);
		crearCharlasViejo(viejo);
		crearCharlasCarpintero(carpintero);
		viejo.setCharlaActual("saludo");
		carpintero.setCharlaActual("saludo");
		checkearCharlas(vendedorTienda, vendedorAmbulante, viejo, carpintero);
		
		this.jugador = jugador;
	}
	
	public void checkearCharlas(Npc vendedorTienda, Npc vendedorAmbulante, Npc viejo, Npc carpintero) {
		npcVendedorTienda(vendedorTienda);
		npcVendedorAmbulante(vendedorAmbulante);
		npcViejo(viejo);
		npcCarpintero(carpintero);
		
	}
	
	
	public void crearCharlasVendedorTienda(Npc vendedorTienda) {
		vendedorTienda.charlas.add(new Charla("saludo", vendedorTienda.getPaqueteDeDialogosNro(0)));
	}
	
	public void crearCharlasVendedorAmbulante(Npc vendedorAmbulante) {
		vendedorAmbulante.charlas.add(new Charla("saludo", vendedorAmbulante.getPaqueteDeDialogosNro(0)));
	}
	
	public void crearCharlasViejo(Npc viejo) {
		viejo.charlas.add(new Charla("saludo", viejo.getPaqueteDeDialogosNro(0)));
		viejo.charlas.add(new Charla("montanas_minerales", viejo.getPaqueteDeDialogosNro(1)));
	}
	
	public void crearCharlasCarpintero(Npc carpintero) {
		carpintero.charlas.add(new Charla("saludo", carpintero.getPaqueteDeDialogosNro(0)));
		carpintero.charlas.add(new Charla("sobre_la_guerra", carpintero.getPaqueteDeDialogosNro(1)));
		carpintero.charlas.add(new Charla("mision_sierra_circular", carpintero.getPaqueteDeDialogosNro(2)));
		
	}
	
	
	public void npcVendedorTienda(Npc vendedorTienda) {//Aca va toda la logica en donde, dependiendo del npc, se evalua que charla es la que sigue. Ejemplo: aca poner que si es viernes el npc tenga un dialogo y si es lunes que tenga uno distinto
		if(vendedorTienda.getJugadorEnRango()) {			
		vendedorTienda.setCharlaActual("saludo");
		vendedorTienda.resetearRespuestas();
		}
		
	}
	
	public void npcVendedorAmbulante(Npc vendedorAmbulante) {//Aca va toda la logica en donde, dependiendo del npc, se evalua que charla es la que sigue. Ejemplo: aca poner que si es viernes el npc tenga un dialogo y si es lunes que tenga uno distinto
		if(vendedorAmbulante.getJugadorEnRango()) {	
		vendedorAmbulante.setCharlaActual("saludo");
		vendedorAmbulante.resetearRespuestas();
		}
	}
	
	public void npcViejo(Npc viejo) {//Aca va toda la logica en donde, dependiendo del npc, se evalua que charla es la que sigue. Ejemplo: aca poner que si es viernes el npc tenga un dialogo y si es lunes que tenga uno distinto
		if(viejo.getJugadorEnRango()) {
			
		if(viejo.getNombreCharlaActual().equals("saludo") && viejo.respuesta1) {
			viejo.setCharlaActual("montanas_minerales");
			viejo.resetearRespuestas();
		}else {

			
		}
		}

	}
	
	public void npcCarpintero(Npc carpintero) {
		if (carpintero.getJugadorEnRango()) {

			if (carpintero.getNombreCharlaActual().equals("saludo") && carpintero.respuesta1) {
				carpintero.setCharlaActual("sobre_la_guerra");
			} else if (carpintero.getNombreCharlaActual().equals("saludo") && carpintero.respuesta2) {
				carpintero.ocultarDialogo();
			}

			if (carpintero.getNombreCharlaActual().equals("sobre_la_guerra")
					&& (carpintero.respuesta1 || carpintero.respuesta2)) {
				carpintero.setCharlaActual("mision_sierra_circular");
			}

			if (carpintero.getNombreCharlaActual().equals("mision_sierra_circular") && carpintero.respuesta1) {
				if (!jugador.buscarMisionPorId("CARP_00")) {
					jugador.agregarMision(MisionesDelJuego.CARP_00);
					carpintero.resetearRespuestas();//Tengo que resetearle las respuestas aca pq por ahora es el ultimo dialogo de este npc, y se resetean desde la clase npc pero solo cuando se llama a un nuevo dialogo
				}
			} else {
				carpintero.ocultarDialogo();
			}
		}
	}
	

	
	
	
}
