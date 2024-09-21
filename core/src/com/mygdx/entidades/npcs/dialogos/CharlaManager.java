package com.mygdx.entidades.npcs.dialogos;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.Npc;
import com.mygdx.entidades.Vendedor;
import com.mygdx.entidades.ObjetosDelMapa.Items.Esquema;
import com.mygdx.entidades.npcs.Carpintero;
import com.mygdx.enums.CaracterMensajes;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.enums.Items;
import com.mygdx.enums.Respuestas;
import com.mygdx.historia.FabricablesMision;
import com.mygdx.historia.MisionesDelJuego;
import com.mygdx.historia.TipoMision;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.MundoConfig;

public class CharlaManager {

	// Aca van todas las charlas
	public ArrayList<Charla> vendedorTiendaCharlas;
	private Jugador jugador;


	public CharlaManager(Jugador jugador, Npc vendedorTienda, Npc vendedorAmbulante, Npc viejo, Npc carpintero) {
		crearCharlasVendedorTienda(vendedorTienda);
		crearCharlasVendedorAmbulante(vendedorAmbulante);
		crearCharlasViejo(viejo);
		crearCharlasCarpintero(carpintero);
		viejo.setCharlaActual("saludo");
		carpintero.setCharlaActual("saludo");
		vendedorAmbulante.setCharlaActual("saludo");
		vendedorTienda.setCharlaActual("saludo");
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
		carpintero.charlas.add(new Charla("carpintero_entrega_primera_mision", carpintero.getPaqueteDeDialogosNro(3)));
		carpintero.charlas.add(new Charla("carpintero_venta", carpintero.getPaqueteDeDialogosNro(4)));

	}

	public void npcVendedorTienda(Npc vendedorTienda) {// Aca va toda la logica en donde, dependiendo del npc, se evalua
														// que charla es la que sigue. Ejemplo: aca poner que si es
														// viernes el npc tenga un dialogo y si es lunes que tenga uno
														// distinto
		if (vendedorTienda.getJugadorEnRango()) {

			switch (vendedorTienda.getNombreCharlaActual()) {
			case "saludo":
				if (jugador.respuesta1 == Respuestas.VERDADERO) {
					Vendedor vendedor = (Vendedor) vendedorTienda;
					abrirVenta(vendedor);
				} else if(jugador.respuesta2 == Respuestas.VERDADERO){
					cerrarDialogo(vendedorTienda);

				}
				break;

			default:
				break;
			}

		}

	}

	public void npcVendedorAmbulante(Npc vendedorAmbulante) {// Aca va toda la logica en donde, dependiendo del npc, se
																// evalua que charla es la que sigue. Ejemplo: aca poner
																// que si es viernes el npc tenga un dialogo y si es
																// lunes que tenga uno distinto
		if (vendedorAmbulante.getJugadorEnRango()) {
			
			switch (vendedorAmbulante.getNombreCharlaActual()) {
			case "saludo":
				if (jugador.respuesta1 == Respuestas.VERDADERO ) {
					Vendedor vendedor = (Vendedor) vendedorAmbulante;
					abrirVenta(vendedor);
				} else if(jugador.respuesta2 == Respuestas.VERDADERO){
					cerrarDialogo(vendedorAmbulante);

				}
				break;

			default:
				break;
			}
			
			vendedorAmbulante.setCharlaActual("saludo");


		}

	}

	public void npcViejo(Npc viejo) {// Aca va toda la logica en donde, dependiendo del npc, se evalua que charla es
										// la que sigue. Ejemplo: aca poner que si es viernes el npc tenga un dialogo y
										// si es lunes que tenga uno distinto
		if (viejo.getJugadorEnRango()) {

			switch (viejo.getNombreCharlaActual()) {
			case "saludo":
				if (jugador.respuesta1 == Respuestas.VERDADERO) {
					viejo.setCharlaActual("montanas_minerales");

				} else if (jugador.respuesta2 == Respuestas.VERDADERO) {
					cerrarDialogo(viejo);
				}
				break;

			case "montanas_minerales":
				if (jugador.respuesta1 == Respuestas.VERDADERO) {
					cerrarDialogo(viejo);

				} else if (jugador.respuesta2 == Respuestas.VERDADERO) {
					cerrarDialogo(viejo);
				}
				break;
			}
		}

	}

	public void npcCarpintero(Npc carpintero) {

		if (carpintero.getJugadorEnRango()) {

			switch (carpintero.getNombreCharlaActual()) {

			case "saludo":
				if (jugador.respuesta1 == Respuestas.VERDADERO) {
					carpintero.setCharlaActual("sobre_la_guerra");
					
				} else if (jugador.respuesta2 == Respuestas.VERDADERO) {
					cerrarDialogo(carpintero);
				}
				
				break;
			case "sobre_la_guerra":
				if (jugador.respuesta1 == Respuestas.VERDADERO || jugador.respuesta2 == Respuestas.VERDADERO) {
					carpintero.setCharlaActual("mision_sierra_circular");
					
					
				}
				break;

			case "mision_sierra_circular":
				if (jugador.respuesta1 == Respuestas.VERDADERO) {
					if (!jugador.buscarMisionPorId("CARP_00")) {
						jugador.agregarMision(MisionesDelJuego.CARP_00);
						jugador.agregarItem(new Esquema(Items.ESQUEMA_SIERRA_CIRCULAR));

					}
				} else if (jugador.respuesta2 == Respuestas.VERDADERO) {
					cerrarDialogo(carpintero);
					

				}
				break;

			case "carpintero_entrega_primera_mision":
				if (jugador.respuesta1 == Respuestas.VERDADERO) {
					cerrarDialogo(carpintero);
				} else if (jugador.respuesta2 == Respuestas.VERDADERO
						&& jugador.conseguirMisionPorId(MisionesDelJuego.CARP_00).getCompletada()) {
					jugador.getItems().remove(jugador.getItem(Items.SIERRA_CIRCULAR));
					carpintero.setCharlaActual("carpintero_venta");
				}
				break;

			case "carpintero_venta":
				if (jugador.respuesta1 == Respuestas.VERDADERO) {
					Vendedor vendedor = (Vendedor) carpintero;
					abrirVenta(vendedor);
				} else if (jugador.respuesta2 == Respuestas.VERDADERO) {
					cerrarDialogo(carpintero);
				}
				break;
			}
			
			

			if (jugador.buscarMisionPorId(MisionesDelJuego.CARP_00.getId())) {//Esto esta aca aproposito, es para las condiciones de misiones
				if (!jugador.conseguirMisionPorId(MisionesDelJuego.CARP_00).getCompletada()) {
					carpintero.setCharlaActual("carpintero_entrega_primera_mision");
				}
			}
			
		}

	}

	private void cerrarDialogo(Npc npc) {
		MundoConfig.estadoJuego = EstadosDelJuego.JUEGO;
		jugador.resetearRespuestas();
	}
	
	private void abrirVenta(Vendedor vendedor) {
		MundoConfig.estadoJuego = EstadosDelJuego.VENTA;
		MundoConfig.vendedor = vendedor;
		jugador.resetearRespuestas();
	}
}
