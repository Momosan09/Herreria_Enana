package com.mygdx.eventos;

import java.util.ArrayList;
import java.util.EventListener;

import com.mygdx.entidades.Jugador;
import com.mygdx.historia.Mision;
import com.mygdx.hud.CartaHUD;
import com.mygdx.utiles.HelpDebug;

public abstract class Listeners {

		public static ArrayList<EventListener> listeners = new ArrayList<>();
		private static final ArrayList<EventListener> pendientesDeEliminar = new ArrayList<>();

		public static void agregarListener(EventListener listener) { 	//Anade las clases que tengan eventos. te lo re robe Facu
			if (!listeners.contains(listener)) {
				 System.out.println("Listener agregado: " + listener.getClass().getSimpleName());
				listeners.add(listener);
				
			}
			
		}
		
		public static void misionAgregada(Mision mision) {
			for (EventListener listener : listeners) {
				if((listener instanceof EventoMisionAgregada)) {
					((EventoMisionAgregada)listener).misionAgregada(mision);
				}
			}
		}
		
		
		public static void recibirCarta(CartaHUD carta) {
			for (EventListener listener : listeners) {
				if((listener instanceof EventoRecibirCarta)) {
					((EventoRecibirCarta)listener).recibirCarta(carta);
				}
			}
		}
		

		
		public static void cambioDeDia() {
		    for (EventListener listener : listeners) {
		        if (listener instanceof EventoCambioDeDia) {
		            ((EventoCambioDeDia) listener).cambioDeDia();
		        }
		        if (listener instanceof EventoRestarDiasDeMision) {
		            ((EventoRestarDiasDeMision) listener).restarDias();
		        }
		    }
		}

		
		public static void interaccionObjetoMapa() {
			for (EventListener listener : listeners) {
				if((listener instanceof EventoInteraccionObj)) {
					((EventoInteraccionObj)listener).interaccionObj();
				}
			}
		}
		
		public static void interaccionNPC() {
			for (EventListener listener : listeners) {
				if((listener instanceof EventoInteraccionNPC)) {
					((EventoInteraccionNPC)listener).interaccionNPC();
				}
			}
		}
		
		
		/**
		 * Metodo general de interaccion, contiene interaccionNPC e interaccionObj
		 */
		public static void interaccion() {
			//System.out.println("interaccion");
			for (EventListener listener : listeners) {
				if((listener instanceof EventoInteraccionNPC)) {
					((EventoInteraccionNPC)listener).interaccionNPC();

				}
				if((listener instanceof EventoInteraccionObj)) {
					((EventoInteraccionObj)listener).interaccionObj();

				}
			}
		}
		/**
		 * 
		 * @param j el jugador
		 * @param x la posicion x del click
		 * @param y la posicion y del click
		 */
		public static void minar(Jugador j, int x, int y) {
			for (EventListener listener : listeners) {
				if((listener instanceof EventoMinar)) {
					((EventoMinar)listener).minar(j, x, y);
				}
			}
		}
		
		public static void quitarListener(EventListener listener) {
		    if (!pendientesDeEliminar.contains(listener)) {
		        pendientesDeEliminar.add(listener);
		    }
		}

		
		public static void flush() {
		    listeners.removeAll(pendientesDeEliminar);
		    pendientesDeEliminar.clear();
		}


}
