package com.mygdx.entidades.ObjetosDelMapa;

import java.util.ArrayList;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Jugador;
import com.mygdx.utiles.OrganizadorSpritesIndiceZ;


public class MineralesManager {
	private ArrayList<Mineral> minerales;

    public MineralesManager() {
    	minerales = new ArrayList<Mineral>();
    }

    public void agregarMineral(Mineral mineral) {
    	minerales.add(mineral);
    }

//    public void eliminarMineral(Mineral mineral) {
//    	minerales.remove(mineral);
//    }
    
    
    public void limpiarMinerales(World world) {
    	for(int i = 0; i<minerales.size();i++) {
    		if(minerales.get(i).vida <= 0) {
    			world.destroyBody(minerales.get(i).getBody());
    			minerales.remove(i);

    		}
    	}
    }
    
    public void eliminarMineral(float posX, float posY, World world) {
    	boolean fin=false;
    	int i=0;
    	
    	do {
    		if(minerales.get(i).getPosicion().x == posX && minerales.get(i).getPosicion().y == posY) {
    			minerales.get(i).vida=0;
    			world.destroyBody(minerales.get(i).getBody());
    			minerales.remove(i);
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
			//mineral.minar(jugador);
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
    
    public void dibujarAreaInteraccion() {
    	for (Mineral mineral : minerales) {
    			mineral.dibujarAreasInteraccion();
    	}
	}
    
    public void dibujarAreaMinado() {
    	for (Mineral mineral : minerales) {
    			mineral.dibujarAreaDeMinado();
    	}
	}
    

    public ArrayList<Mineral> getMinerales() {
        return minerales;
    }
    
    
}
