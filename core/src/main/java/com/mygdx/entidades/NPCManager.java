package com.mygdx.entidades;

import java.util.ArrayList;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.npcs.Carpintero;
import com.mygdx.entidades.npcs.VendedorAmbulante;
import com.mygdx.entidades.npcs.VendedorDeTienda;
import com.mygdx.entidades.npcs.Viejo;
import com.mygdx.entidades.npcs.dialogos.charlas.DialogoManager;
import com.mygdx.entidades.npcs.generales.General_1;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.utiles.EstadoMundo;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.Render;
import com.mygdx.utiles.recursos.Recursos;

public class NPCManager {

	private EstadoMundo estadoM;
	private Npc viejo, vendedorAmbulate, vendedorTienda, carpintero, general, rey;
	private ArrayList<Npc> npcs;
	private World world;
	//private InteraccionManager interaccionManager;
	private DialogoManager dM;
	
	public NPCManager(EstadoMundo estadoM, World world, DialogoManager dM) {
		this.estadoM = estadoM;
		this.world = world;
		this.dM = dM;
		
		npcs = new ArrayList<>();
		
		crearNpcs();
		
	}
	
	public void agregarNpc(Npc npc) {
		npcs.add(npc);

	}
	
	public void dibujarNpcs() {
		for (Npc npc : npcs) {
			npc.ejecutarAnimacion();
			npc.dibujarAreaDeInteraccion();
		}
	}
	


	private void crearNpcs() {
		viejo = new Viejo(19,34, world,Recursos.npc.enanos.VIEJO, NpcData.VIEJO, estadoM, Recursos.bundle.get("dialogos.npc.viejo_todosLosDialogos"));
		agregarNpc(viejo);
		
		vendedorTienda = new VendedorDeTienda(12,33.5f, world,Recursos.npc.enanos.VENDEDOR_TIENDA, NpcData.VENDEDOR_TIENDA, estadoM, Recursos.bundle.get("dialogos.npc.vendedorTienda_todosLosDialogos"));
		agregarNpc(vendedorTienda);
		
		vendedorAmbulate = new VendedorAmbulante(22,40, world,Recursos.npc.enanos.VENDEDOR_AMBULANTE, NpcData.VENDEDOR_AMBULANTE, estadoM, Recursos.bundle.get("dialogos.npc.vendedorAmbulante_todosLosDialogos"));
		agregarNpc(vendedorAmbulate);
		
		carpintero = new Carpintero(6,5, world, Recursos.npc.enanos.CARPINTERO, NpcData.CARPINTERO, estadoM, Recursos.bundle.get("dialogos.npc.carpintero_todosLosDialogos"));
		agregarNpc(carpintero);
	
		general = new General_1(20,36, world, estadoM, Recursos.bundle.get("dialogos.npc.general_todosLosDialogos"));
		agregarNpc(general);
		
	}
	

	public void resolverInteracciones(Jugador jugador) {

	    if (!jugador.quiereInteractuar()) return;
	    Npc masCercano = null;
	    float menorDistancia = Float.MAX_VALUE;

	    Circle areaJugador = jugador.getAreaInteraccion();

	    for (Npc npc : npcs) {

	        Circle areaNpc = npc.getAreaInteraccion();

	        if (areaNpc.overlaps(areaJugador)) {

	            float distancia = Vector2.dst(areaNpc.x, areaNpc.y,areaJugador.x, areaJugador.y);

	            if (distancia < menorDistancia) {
	                menorDistancia = distancia;
	                masCercano = npc;
	            }
	        }
	    }

	    if (masCercano != null) {
	    	//Listeners.ejecutarInteraccion(masCercano);
	    	dM.iniciar(masCercano);
	        //masCercano.interactuar(jugador);
	        MundoConfig.locutor = masCercano;
	        MundoConfig.estadoJuego = EstadosDelJuego.DIALOGO;
	        MundoConfig.acutualizarCharla = true;

	        
	    }else {
	        MundoConfig.estadoJuego =EstadosDelJuego.JUEGO;
	        MundoConfig.acutualizarCharla = false;
	        jugador.resetInteraccion();
	    }

	    //jugador.resetInteraccion();
	}

	
}
