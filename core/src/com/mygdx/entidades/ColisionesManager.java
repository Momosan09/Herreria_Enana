package com.mygdx.entidades;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.red.UtilesRed;
import com.mygdx.utiles.HelpDebug;

public class ColisionesManager {

	private Jugador actor;
	private ArrayList<Rectangle> colisiones;
	
	public ColisionesManager(Jugador actor) {
		colisiones = new ArrayList<Rectangle>();
		this.actor = actor;
	}
	
	public void agregarColision(Rectangle colision) {
		colisiones.add(colision);
		
	}
	
	public void agregarArrayDeColisiones(ArrayList<Rectangle> colisiones) {
		this.colisiones.addAll(colisiones);
	}
	
	
	public void eliminarColision(Rectangle colision) {
		colisiones.remove(colision);
	}
	
	public void checkearColisiones() {
		System.out.println("checking");
	    boolean hayColision = false;
	    for (Rectangle colision : colisiones) {
	        if (actor.colision.overlaps(colision)) {
	            actor.direccionDelChoque = actor.direccionActual;
	            hayColision = true;	     
	            System.out.println("pepin");
	        	}
	        }
	    

	    if (!hayColision) {
	        actor.direccionDelChoque = null;
	    }
	}

	public ArrayList<Rectangle> getColisiones(){
		return colisiones;
	}
	
}
