package com.mygdx.entidades.ObjetosDelMapa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.entidades.ColisionesManager;
import com.mygdx.entidades.Jugador;


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
    
    
    public void limpiarMinerales(ColisionesManager colisionesManager, boolean red) {
    	for(int i = 0; i<minerales.size();i++) {
    		if(minerales.get(i).vida <= 0) {
    			if(!red) {
    				colisionesManager.eliminarColision(colisiones.get(i));    				
    			}
    			minerales.remove(i);
    			colisiones.remove(i);
    		}
    	}
    }
    
    public void eliminarMineral(float posX, float posY, ColisionesManager colisionesManager) {
    	boolean fin=false;
    	int i=0;
    	
    	do {
    		if(minerales.get(i).getPosicion().x == posX && minerales.get(i).getPosicion().y == posY) {
    			minerales.get(i).vida=0;
    			colisionesManager.eliminarColision(minerales.get(i).getColision());
    			minerales.remove(i);
    			colisiones.remove(i);
    			fin = true;
    		}
    		
    		i++;
    	}while(!fin && i<minerales.size());
    }

    public void detectarJugador(Jugador jugador) {
        for (Mineral mineral : minerales) {
        	if(mineral.vida >0) {//Si el mineral esta vivo, detecta al jugador
        	mineral.detectarJugador(jugador);
        	}
        }
    }

    public void renderizar() {
        for (Mineral mineral : minerales) {
        	if(mineral.isComprable()) mineral.draw();
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
