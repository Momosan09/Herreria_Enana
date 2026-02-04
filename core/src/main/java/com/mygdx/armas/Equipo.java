package com.mygdx.armas;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Null;
import com.mygdx.armas.modificadores.AplicadorDeModificadores;
import com.mygdx.armas.modificadores.EfectosArma;
import com.mygdx.armas.modificadores.Modificador;
import com.mygdx.armas.modificadores.Modificadores;
import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.Items.Item;
import com.mygdx.enums.Items;
import com.mygdx.utiles.HelpDebug;

/**
 * 
 */
public abstract class Equipo {
	
	
	/*Existe una textura base la cual es el metal base + la forma del arma
	* overlays guarda todas las capas que se le tienen que aplicar a esa textura
	* combinarTexturas se encarga de la logica de apilacion
	* reconstruir se debe llamr cada vez que se le haga un cambio a la pila de texturas
	*/
	protected Texture texturaBase;
	protected ArrayList<Texture> overlays;
	protected Texture texturaFinal;

	protected Sprite sprite;
	
	protected float verde;
	protected float rojo;
	protected float azul;
	
	protected Mineral metalBase;
	protected EstadosArmas tipoArma;
	protected Modificadores modificador = null;
	protected EfectosArma efecto;
	
//	public Equipo(Mineral metalBase, EstadosArmas tipoArma) {
//		
//		this.tipoArma = tipoArma;
//		this.metalBase = metalBase;
//		
//		Texture t1 = new Texture(metalBase.tipo.ruta + tipoArma.ruta);
//
//
//	}
	
	public Equipo(Mineral metalBase, @Null ArrayList<Items> items, EstadosArmas tipoArma) {
		
		this.tipoArma = tipoArma;
		
		Texture t1 = new Texture(metalBase.tipo.ruta + tipoArma.ruta);
		Texture texturasItem[] = new Texture[items.size()];
		
		for (int i = 0; i<texturasItem.length;i++) {
			texturasItem[i] = items.get(i).getTextura();
		}
		
		
		this.metalBase = metalBase;
		
	    texturaBase = new Texture(metalBase.tipo.ruta + tipoArma.ruta);
	    overlays = new ArrayList<>();

	    if (items != null) {
	        for (Items item : items) {
	            overlays.add(item.getTextura());
	        }
	    }

	    reconstruirTextura();
		
	}
	
	public Texture getTextura() {
		return texturaFinal;
	}
	
	public void aplicarModificador(Modificadores mod) {
	    this.modificador = mod;
	    efecto = AplicadorDeModificadores.darEfecto(mod, metalBase, tipoArma);

	    overlays.add(new Texture(mod.ruta));//Agrega la nueva textura a las capas

	    reconstruirTextura();//Reconstruye la textura agregando la textura del modificador
	}

	
	public void atacar(Entidad objetivo/*, ContextoCombate ctx*/) {

	    // daño base
	    //objetivo.recibirDanio(calcularDanio());

	    // efecto especial
	    if (efecto != null) {
	       // efecto.aplicar(general, objetivo/*, ctx*/);
	    }
	}

	private void reconstruirTextura() {
	    ArrayList<Texture> todas = new ArrayList<>();
	    todas.addAll(overlays);
	    todas.add(texturaBase);

	    if (texturaFinal != null) {
	        texturaFinal.dispose();
	    }

	    texturaFinal = combinarTexturas(todas.toArray(new Texture[0]));
	}



	/*
	 * Combina x cantidad de texturas en una sola, compone de atras para adelante, es decir que la primera textura pasada por parametro va a ser la de mas atras
	 * FIXME me parece que aca va a haber un problema cuando el juego guarde los datos, no se si va a crear la textura.
	 */
	public static Texture combinarTexturas(Texture ...texturas) {
		Pixmap pixmaps[] = new Pixmap[texturas.length];

		//Preparar texturas y crear pixmaps
		for(int i = 0; i<texturas.length;i++) {
			
			if (!texturas[i].getTextureData().isPrepared()) {
				texturas[i].getTextureData().prepare();
			}
			
			pixmaps[i] = texturas[i].getTextureData().consumePixmap();
		}
		
		
		for(int i = pixmaps.length-1; i>=1;i--) {
			pixmaps[i-1].drawPixmap(pixmaps[i], 0, 0);
		}

	    Texture textureResult = new Texture(pixmaps[0]);
	    
	    //Disposear los pixmaps
		for(int i = 0; i<pixmaps.length;i++) {
			pixmaps[i].dispose();
			
		}
		
	    return textureResult;
	}
	
	public static Texture combinarTexturas(Texture[] texturas1, Texture ...texturas2) {
		Pixmap pixmaps[] = new Pixmap[texturas1.length + texturas2.length];
		
		Texture texturasTODAS[] = new Texture[pixmaps.length];
		
		//juntar todas las texturas en un solo array
		
		for (int i = 0; i<texturasTODAS.length;i++) {
			if(i<texturas1.length) {
				texturasTODAS[i] = texturas1[i];				
			}else {
				texturasTODAS[i] = texturas2[i-texturas1.length];				
			}
		}
		
		
		//Preparar texturas y crear pixmaps
		for(int i = 0; i<pixmaps.length;i++) {
			
			if (!texturasTODAS[i].getTextureData().isPrepared()) {
				texturasTODAS[i].getTextureData().prepare();
			}
			pixmaps[i] = texturasTODAS[i].getTextureData().consumePixmap();
		}
		
		
		for(int i = pixmaps.length-1; i>=1;i--) {
			pixmaps[i-1].drawPixmap(pixmaps[i], 0, 0);
		}

	    Texture textureResult = new Texture(pixmaps[0]);
	    
	    //Disposear los pixmaps
		for(int i = 0; i<pixmaps.length;i++) {
			pixmaps[i].dispose();
		}
		
	    return textureResult;
	}

}
