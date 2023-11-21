package com.mygdx.entidades.ObjetosDelMapa;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.entidades.ColisionesManager;
import com.mygdx.entidades.Jugador;
import com.mygdx.red.UtilesRed;


public class MineralesManager {
	private ArrayList<Mineral> minerales;
	private ArrayList<Rectangle> colisiones;

    public MineralesManager() {
    	minerales = new ArrayList<Mineral>();
    	colisiones = new ArrayList<Rectangle>();
    }

    public void agregarMineral(Mineral mineral) {
    	minerales.add(mineral);
    	colisiones.add(mineral.getColision());
    }

//    public void eliminarMineral(Mineral mineral) {
//    	minerales.remove(mineral);
//    }
    
    public void eliminarMineral(float posX, float posY, ColisionesManager colisionesManager1, ColisionesManager colisionesManager2) {
    	boolean fin=false;
    	for (int i = minerales.size() - 1; i >= 0; i--) {//Busqueda inversa
    	    if (minerales.get(i).getPosicion().x == posX && minerales.get(i).getPosicion().y == posY) {
    	        minerales.get(i).vida = 0;
    	        colisionesManager1.eliminarColision(colisiones.get(i));
    	        colisionesManager2.eliminarColision(colisiones.get(i));
    	        minerales.remove(i);
    	        colisiones.remove(i);
    	        fin = true;
    	    }
    	}

    }

    public void detectarJugador(Jugador jugador) {
        for (Mineral mineral : minerales) {
        	if(mineral.vida >0) {//Si el mineral esta vivo, detecta al jugador
        	mineral.detectarJugador(jugador);
        	}
        }
    }
    public void detectarJugador(Jugador jugador1, Jugador jugador2) {
        for (Mineral mineral : minerales) {
        	if(mineral.vida >0) {//Si el mineral esta vivo, detecta al jugador
        	mineral.detectarJugador(jugador1);
        	mineral.detectarJugador(jugador2);
        	}
        }
    }

    public void renderizar() {
        for (Mineral mineral : minerales) {
        	if(mineral.isComprable()) {
        		mineral.draw();
        	}
        	if(mineral.vida >0 && !mineral.isComprable()) {//Si el mineral esta vivo, se renderiza
        		mineral.draw();
        		//entidad.ejecutarAnimacion();
        	}
        }
    } 
    
    
    public void minar(Jugador jugador) { //necesito al jugador para saber a quien pasarle el mineral cuando es picado
    	for (Mineral mineral : minerales) {
    		if(mineral.vida > 0 && !mineral.isComprable()) {//Si el mineral esta vivo y no es comprable, se puede minar
			mineral.minar(jugador);
    		}
		}
    }
    
    public boolean comprar(Jugador jugador) { //necesito al jugador para saber a quien pasarle el mineral cuando es picado
    	for (Mineral mineral : minerales) {
    		if(mineral.isComprable()) {//Si el mineral es comprable, se puede comprar
    			if(mineral.getComprar()) {
    				mineral.comprar(jugador);
    			}
    		}
		}
    	return false;
    }
    public boolean dialogoCompra() {
    	for (Mineral mineral : minerales) {
			if(mineral.isComprable()) {
				if(mineral.getComprar()) {
					return true;
				}
			}
		}
		return false;
    }
    
    
    public void generarVetas() {
    	
    }

    public ArrayList<Mineral> getMinerales() {
        return minerales;
    }
    
    public ArrayList<Rectangle> getColisiones() {
    	return colisiones;
    }
    
}
