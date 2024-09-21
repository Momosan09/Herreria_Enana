package com.mygdx.eventos;

import java.util.ArrayList;
import java.util.EventListener;

import com.mygdx.hud.CartaHUD;
import com.mygdx.utiles.HelpDebug;

public class Listeners {

		public static ArrayList<EventListener> listeners = new ArrayList<>();
		
		public static void agregarListener(EventListener listener) { 	//Anade las clases que tengan eventos. te lo re robe Facu
			if (!listeners.contains(listener)) {
				 System.out.println("Listener agregado: " + listener.getClass().getSimpleName());
				listeners.add(listener);
				
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
				if((listener instanceof EventoCambioDeDia)) {
					((EventoCambioDeDia)listener).cambioDeDia();
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
			System.out.println("interaccion");
			for (EventListener listener : listeners) {
				if((listener instanceof EventoInteraccionNPC)) {
					((EventoInteraccionNPC)listener).interaccionNPC();

				}
				if((listener instanceof EventoInteraccionObj)) {
					((EventoInteraccionObj)listener).interaccionObj();

				}
			}
		}

}
