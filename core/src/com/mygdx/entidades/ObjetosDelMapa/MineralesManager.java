package com.mygdx.entidades.ObjetosDelMapa;

import java.util.ArrayList;
import java.util.List;
import com.mygdx.entidades.Jugador;


public class MineralesManager {
	private List<Mineral> minerales;

    public MineralesManager() {
    	minerales = new ArrayList<Mineral>();
    }

    public void agregarMineral(Mineral mineral) {
    	minerales.add(mineral);
    }

//    public void eliminarMineral(Mineral mineral) {
//    	minerales.remove(mineral);
//    }
    
    public void eliminarMineral(float posX, float posY) {
    	boolean fin=false;
    	int i=0;
    	do {
    		if(minerales.get(i).getPosicion().x == posX && minerales.get(i).getPosicion().y == posY) {
    			minerales.get(i).vida=0;
    			minerales.remove(i);
    			fin = true;
    			System.out.println("Eliminadot");
    		}
    		
    		i++;
    	}while(!fin);
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

    public List<Mineral> getMinerales() {
        return minerales;
    }
    
}
