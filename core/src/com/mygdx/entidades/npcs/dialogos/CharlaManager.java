package com.mygdx.entidades.npcs.dialogos;

import java.util.ArrayList;

import com.mygdx.entidades.Npc;

public class CharlaManager {

	//Aca van todas las charlas
	public ArrayList<Charla> vendedorTiendaCharlas;
	
	
	public CharlaManager(Npc vendedorTienda, Npc vendedorAmbulante, Npc viejo) {
		crearCharlasVendedorTienda(vendedorTienda);
		crearCharlasVendedorAmbulante(vendedorAmbulante);
		crearCharlasViejo(viejo);
		viejo.setCharlaActual("saludo");
		checkearCharlas(vendedorTienda, vendedorAmbulante, viejo);
	}
	
	public void checkearCharlas(Npc vendedorTienda, Npc vendedorAmbulante, Npc viejo) {
		npcVendedorTienda(vendedorTienda);
		npcVendedorAmbulante(vendedorAmbulante);
		npcViejo(viejo);
		
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
	
	
	public void npcVendedorTienda(Npc vendedorTienda) {//Aca va toda la logica en donde, dependiendo del npc, se evalua que charla es la que sigue. Ejemplo: aca poner que si es viernes el npc tenga un dialogo y si es lunes que tenga uno distinto
		vendedorTienda.setCharlaActual("saludo");
		
	}
	
	public void npcVendedorAmbulante(Npc vendedorAmbulante) {//Aca va toda la logica en donde, dependiendo del npc, se evalua que charla es la que sigue. Ejemplo: aca poner que si es viernes el npc tenga un dialogo y si es lunes que tenga uno distinto
		vendedorAmbulante.setCharlaActual("saludo");
	}
	
	public void npcViejo(Npc viejo) {//Aca va toda la logica en donde, dependiendo del npc, se evalua que charla es la que sigue. Ejemplo: aca poner que si es viernes el npc tenga un dialogo y si es lunes que tenga uno distinto

		if(viejo.getNombreCharlaActual().equals("saludo") && viejo.respuesta1) {
			viejo.setCharlaActual("montanas_minerales");
		}else {

			
		}

	}
	
	
	
}
