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
import com.mygdx.enums.EstadosMision;
import com.mygdx.enums.Items;
import com.mygdx.enums.Respuestas;
import com.mygdx.historia.FabricablesMision;
import com.mygdx.historia.MisionesDelJuego;
import com.mygdx.historia.TipoMision;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.Recursos;

public class CharlaManager {

	// Aca van todas las charlas
	public ArrayList<Charla> vendedorTiendaCharlas;
	private Jugador jugador;


	public CharlaManager(Jugador jugador, Npc vendedorTienda, Npc vendedorAmbulante, Npc viejo, Npc carpintero) {
		crearCharlasVendedorTienda(vendedorTienda);
		crearCharlasVendedorAmbulante(vendedorAmbulante);
		crearCharlasViejo(viejo);
		crearCharlasCarpintero(carpintero);
		vendedorTienda.setCharlaActual(Recursos.bundle.get("vendedor_tienda.charla.saludo"));
		vendedorAmbulante.setCharlaActual(Recursos.bundle.get("vendedor_ambulante.charla.saludo"));
		viejo.setCharlaActual(Recursos.bundle.get("viejo.charla.nombre.saludo"));
		carpintero.setCharlaActual(Recursos.bundle.get("carpintero.charla.nombre.saludo"));
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
		vendedorTienda.charlas.add(new Charla(Recursos.bundle.get("vendedor_tienda.charla.saludo"), vendedorTienda.getPaqueteDeDialogosNro(0)));
	}

	public void crearCharlasVendedorAmbulante(Npc vendedorAmbulante) {
		vendedorAmbulante.charlas.add(new Charla(Recursos.bundle.get("vendedor_ambulante.charla.saludo"), vendedorAmbulante.getPaqueteDeDialogosNro(0)));
	}

	public void crearCharlasViejo(Npc viejo) {
		viejo.charlas.add(new Charla(Recursos.bundle.get("viejo.charla.nombre.saludo"), viejo.getPaqueteDeDialogosNro(0)));
		viejo.charlas.add(new Charla(Recursos.bundle.get("viejo.charla.nombre.montanas_minerales"), viejo.getPaqueteDeDialogosNro(1)));
		viejo.charlas.add(new Charla(Recursos.bundle.get("viejo_RC2_VIE_0"), viejo.getPaqueteDeDialogosNro(2)));
		viejo.charlas.add(new Charla(Recursos.bundle.get("viejo_RC2_VIE_1"), viejo.getPaqueteDeDialogosNro(3)));
		viejo.charlas.add(new Charla(Recursos.bundle.get("viejo_RC2_VIE_2"), viejo.getPaqueteDeDialogosNro(4)));
		viejo.charlas.add(new Charla(Recursos.bundle.get("viejo_RC2_VIE_FALLADA"), viejo.getPaqueteDeDialogosNro(5)));
//		viejo.charlas.get(Recursos.bundle.get("viejo.charla.nombre.montanas_minerales"), new Charla(Recursos.bundle.get("viejo.charla.nombre.montanas_minerales"), viejo.getPaqueteDeDialogosNro(1)));
	}

	public void crearCharlasCarpintero(Npc carpintero) {
		carpintero.charlas.add(new Charla(Recursos.bundle.get("carpintero.charla.nombre.saludo"), carpintero.getPaqueteDeDialogosNro(0)));
		carpintero.charlas.add(new Charla(Recursos.bundle.get("carpintero.charla.nombre.sobre_la_guerra"), carpintero.getPaqueteDeDialogosNro(1)));
		carpintero.charlas.add(new Charla(Recursos.bundle.get("carpintero.charla.nombre.mision.sierra_circular"), carpintero.getPaqueteDeDialogosNro(2)));
		carpintero.charlas.add(new Charla(Recursos.bundle.get("carpintero.charla.nombre.mision.sierra_circular.completada"), carpintero.getPaqueteDeDialogosNro(3)));
		carpintero.charlas.add(new Charla(Recursos.bundle.get("carpintero.charla.nombre.venta"), carpintero.getPaqueteDeDialogosNro(4)));

	}

	public void npcVendedorTienda(Npc vendedorTienda) {// Aca va toda la logica en donde, dependiendo del npc, se evalua
														// que charla es la que sigue. Ejemplo: aca poner que si es
														// viernes el npc tenga un dialogo y si es lunes que tenga uno
														// distinto
		if (vendedorTienda.getJugadorEnRango()) {

			if (vendedorTienda.getNombreCharlaActual().equals(Recursos.bundle.get("vendedor_tienda.charla.saludo"))) {

				if (jugador.respuesta1 == Respuestas.VERDADERO) {
					Vendedor vendedor = (Vendedor) vendedorTienda;
					abrirVenta(vendedor);
				} else if(jugador.respuesta2 == Respuestas.VERDADERO){
					cerrarDialogo(vendedorTienda);

				}

	}
	}
	}

	public void npcVendedorAmbulante(Npc vendedorAmbulante) {// Aca va toda la logica en donde, dependiendo del npc, se
																// evalua que charla es la que sigue. Ejemplo: aca poner
																// que si es viernes el npc tenga un dialogo y si es
																// lunes que tenga uno distinto
		if (vendedorAmbulante.getJugadorEnRango()) {
			
			if (vendedorAmbulante.getNombreCharlaActual().equals(Recursos.bundle.get("vendedor_ambulante.charla.saludo"))) {
	
				if (jugador.respuesta1 == Respuestas.VERDADERO ) {
					Vendedor vendedor = (Vendedor) vendedorAmbulante;
					abrirVenta(vendedor);
				} else if(jugador.respuesta2 == Respuestas.VERDADERO){
					cerrarDialogo(vendedorAmbulante);

				}

			}
			
			vendedorAmbulante.setCharlaActual(Recursos.bundle.get("vendedor_ambulante.charla.saludo"));


		}

	}

	public void npcViejo(Npc viejo) {// Aca va toda la logica en donde, dependiendo del npc, se evalua que charla es
										// la que sigue. Ejemplo: aca poner que si es viernes el npc tenga un dialogo y
										// si es lunes que tenga uno distinto
		if (viejo.getJugadorEnRango()) {

			if (viejo.getNombreCharlaActual().equals(Recursos.bundle.get("viejo.charla.nombre.saludo"))) {
				if (jugador.respuesta1 == Respuestas.VERDADERO) {
					viejo.setCharlaActual(Recursos.bundle.get("viejo.charla.nombre.montanas_minerales"));

				} else if (jugador.respuesta2 == Respuestas.VERDADERO) {
					cerrarDialogo(viejo);
				}

			}
			if (viejo.getNombreCharlaActual().equals(Recursos.bundle.get("viejo.charla.nombre.montanas_minerales"))) {
				if (jugador.respuesta1 == Respuestas.VERDADERO) {
					if(!jugador.getMisiones().get(MisionesDelJuego.RC2_VIE.getId()).isFallada()) {						
					viejo.setCharlaActual(Recursos.bundle.get("viejo_RC2_VIE_0"));
					}else {
						cerrarDialogo(viejo); 
						viejo.setCharlaActual(Recursos.bundle.get("viejo.charla.nombre.montanas_minerales"));
					}

				} else if (jugador.respuesta2 == Respuestas.VERDADERO) {
					cerrarDialogo(viejo);
				}

			}
			
			if (viejo.getNombreCharlaActual().equals(Recursos.bundle.get("viejo_RC2_VIE_0"))) {
				if (jugador.respuesta1 == Respuestas.VERDADERO) {
					viejo.setCharlaActual(Recursos.bundle.get("viejo_RC2_VIE_1"));

				} else if (jugador.respuesta2 == Respuestas.VERDADERO) {
					viejo.setCharlaActual(Recursos.bundle.get("viejo_RC2_VIE_2"));
				}

			}
			
			if (viejo.getNombreCharlaActual().equals(Recursos.bundle.get("viejo_RC2_VIE_1"))) {
				if (jugador.respuesta1 == Respuestas.VERDADERO) {
					jugador.getMisiones().get(MisionesDelJuego.RC2_VIE.getId()).setFallada();
					viejo.setCharlaActual(Recursos.bundle.get("viejo.charla.nombre.saludo"));

				} else if (jugador.respuesta2 == Respuestas.VERDADERO) {//Si el jugador fallo la mision de hablar con el viejo
					viejo.setCharlaActual(Recursos.bundle.get("viejo_RC2_VIE_FALLADA"));
				}

			}
			
			if (viejo.getNombreCharlaActual().equals(Recursos.bundle.get("viejo_RC2_VIE_2"))) {
				if (jugador.respuesta1 == Respuestas.VERDADERO) {
//					jugador.agregarMision(MisionesDelJuego.);
						//Aca va la mision del sastre
					cerrarDialogo(viejo);
				} else if (jugador.respuesta2 == Respuestas.VERDADERO) {
					cerrarDialogo(viejo);
				}

			}
			
			if (viejo.getNombreCharlaActual().equals(Recursos.bundle.get("viejo_RC2_VIE_FALLADA"))) {
				if (jugador.respuesta1 == Respuestas.VERDADERO) {	
					cerrarDialogo(viejo);
					viejo.setCharlaActual(Recursos.bundle.get("viejo.charla.nombre.saludo"));

				} else if (jugador.respuesta2 == Respuestas.VERDADERO) {
					cerrarDialogo(viejo);
					viejo.setCharlaActual(Recursos.bundle.get("viejo.charla.nombre.saludo"));
				}

			}
			
			
		}

	}

	public void npcCarpintero(Npc carpintero) {

		if (carpintero.getJugadorEnRango()) {

			if  (carpintero.getNombreCharlaActual().equals(Recursos.bundle.get("carpintero.charla.nombre.saludo"))) {

				if (jugador.respuesta1 == Respuestas.VERDADERO) {
					carpintero.setCharlaActual(Recursos.bundle.get("carpintero.charla.nombre.sobre_la_guerra"));
					
				} else if (jugador.respuesta2 == Respuestas.VERDADERO) {
					cerrarDialogo(carpintero);
				}
				
			}
			if(carpintero.getNombreCharlaActual().equals(Recursos.bundle.get("carpintero.charla.nombre.sobre_la_guerra"))) {
				
				if (jugador.respuesta1 == Respuestas.VERDADERO || jugador.respuesta2 == Respuestas.VERDADERO) {
					carpintero.setCharlaActual(Recursos.bundle.get("carpintero.charla.nombre.mision.sierra_circular"));
					
				}
					
				}

			if(carpintero.getNombreCharlaActual().equals(Recursos.bundle.get("carpintero.charla.nombre.mision.sierra_circular"))) {
				if (jugador.respuesta1 == Respuestas.VERDADERO) {
					if (!jugador.buscarMisionPorId("CARP_00")) {
						jugador.agregarMision(MisionesDelJuego.CARP_00);
						jugador.agregarItem(new Esquema(Items.ESQUEMA_SIERRA_CIRCULAR));

					}
				} else if (jugador.respuesta2 == Respuestas.VERDADERO) {
					cerrarDialogo(carpintero);
					

				}
			}

				if(carpintero.getNombreCharlaActual().equals(Recursos.bundle.get("carpintero.charla.nombre.mision.sierra_circular.completada"))) {
				if (jugador.respuesta1 == Respuestas.VERDADERO) {
					cerrarDialogo(carpintero);
				} else if (jugador.respuesta2 == Respuestas.VERDADERO
						&& jugador.conseguirMisionPorId(MisionesDelJuego.CARP_00).getEstado() == EstadosMision.COMPLETADA) {
					jugador.getItems().remove(jugador.getItem(Items.SIERRA_CIRCULAR));
					carpintero.setCharlaActual(Recursos.bundle.get("carpintero.charla.nombre.venta"));
				}
				}

				if(carpintero.getNombreCharlaActual().equals(Recursos.bundle.get("carpintero.charla.nombre.venta"))) {
				if (jugador.respuesta1 == Respuestas.VERDADERO) {
					Vendedor vendedor = (Vendedor) carpintero;
					abrirVenta(vendedor);
				} else if (jugador.respuesta2 == Respuestas.VERDADERO) {
					cerrarDialogo(carpintero);
				}
			}
			
			

			if (jugador.buscarMisionPorId(MisionesDelJuego.CARP_00.getId())) {//Esto esta aca aproposito, es para las condiciones de misiones
				if (jugador.conseguirMisionPorId(MisionesDelJuego.CARP_00).getEstado() == EstadosMision.PENDIENTE) {
					carpintero.setCharlaActual(Recursos.bundle.get("carpintero.charla.nombre.mision.sierra_circular.completada"));
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
