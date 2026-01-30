package com.mygdx.entidades.ObjetosDelMapa;

import java.util.ArrayList;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetosDelMapa.Minable.CarbonMena;
import com.mygdx.entidades.ObjetosDelMapa.Minable.HierroMena;
import com.mygdx.entidades.ObjetosDelMapa.Minable.PiedraMena;
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;
import com.mygdx.utiles.MisUtiles;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.OrganizadorSpritesIndiceZ;


public class MineralesManager {
	private ArrayList<Mineral> minerales;
	private World world;
	
    public MineralesManager(World world) {
    	minerales = new ArrayList<Mineral>();
    	this.world = world;
    	minerales.add(new HierroMena(200/32, 200/32, world, false));
    }

    public void agregarMineral(Mineral mineral) {
    	minerales.add(mineral);
    }

//    public void eliminarMineral(Mineral mineral) {
//    	minerales.remove(mineral);
//    }
    
    
    public void limpiarMinerales() {
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
    
    
    public void generarVetas(Rectangle area) {
    	float tilesAncho = area.width / MundoConfig.tamanoTile;
    	float tilesAlto = area.height / MundoConfig.tamanoTile;
    	System.out.println((area.x - area.width)+ " " + area.x);
    	int[][] zonas = new int[(int)tilesAncho][(int)tilesAlto];
    	
    	for(int i = 0; i<zonas.length;i++) {
    		for(int j=0;j<zonas[i].length;j++) {
    			zonas[i][j] = MisUtiles.generarAleatorio(0, 1);
    		}
    	}
    	
//    	for(int i = 0; i<zonas.length;i++) {
//    		System.out.println("\n");
//    		for(int j=0;j<zonas[i].length;j++) {
//    			System.out.print("["+ zonas[i][j] +"]");
//    		}
//    	}
//    	
    	for(int i = 0; i<zonas.length;i++) {
    		//System.out.println("\n");
    		for(int j=0;j<zonas[i].length;j++) {
    			if(zonas[i][j] == 1) {
    				float posX = ((area.x-area.width)/32)+(area.x+(j*32));//No termino de entender por que esto va invertido pero bueh
    				float posY = ((area.y-area.height)/32)+(area.y+(i*32));//No termino de entender por que esto va invertido pero bueh
    				
    				//posX y posY hay que convertirlo a tiles
    				float tileX = Math.round((posX/32));
    				float tileY = MundoConfig.altoMundo - Math.round((posY/32));
    				//System.out.println(tileX+" " + tileY+"  culo");
    				agregarMineral(mineralAleatorio(tileX, tileY));
    			}
    		}
    	}
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
    
    public Mineral mineralAleatorio(float x, float y) {
    	switch (MisUtiles.generarAleatorio(0, 2)) {
		case 0:{
			return new CarbonMena(x, y, world, false);			
		}
		case 1:{
			return new HierroMena(x, y, world, false);		
		}
		case 2:{
			return new PiedraMena(x, y, world, false);		
		}

		}
    	return null;
    }
    
    
}
