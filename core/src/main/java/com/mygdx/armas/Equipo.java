package com.mygdx.armas;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Null;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.Items.Item;
import com.mygdx.enums.Items;
import com.mygdx.utiles.HelpDebug;

/**
 * 
 */
public abstract class Equipo {
	
	protected Texture textura;
	protected Sprite sprite;
	
	protected float verde;
	protected float rojo;
	protected float azul;
	
	protected Mineral metalBase;
	protected EstadosArmas tipoArma;
	protected int modificador;//cambiar
	
	public Equipo(Mineral metalBase, EstadosArmas tipoArma) {
		
		this.tipoArma = tipoArma;
		this.metalBase = metalBase;
		
		Texture t1 = new Texture(metalBase.tipo.ruta + tipoArma.ruta);
		Texture t2 = Items.MANGO_MADERA_MAZA.getTextura();
		Texture t3 = Items.SIERRA_CIRCULAR.getTextura();
		this.textura = combinarTexturas(t3,t2,t1);
//TODO DESHARCODEARLO
	}
	
	public Equipo(Mineral metalBase, @Null ArrayList<Items> items, EstadosArmas tipoArma) {
		
		this.tipoArma = tipoArma;
		
		Texture t1 = new Texture(metalBase.tipo.ruta + tipoArma.ruta);
		Texture texturasItem[] = new Texture[items.size()];
		
		for (int i = 0; i<texturasItem.length;i++) {
			texturasItem[i] = items.get(i).getTextura();
			System.out.println(HelpDebug.debub(getClass())+"--------"+items.get(i).getNombre());
		}
		
		
		this.textura = combinarTexturas(texturasItem, t1);
		this.metalBase = metalBase;
	}
	
	public Texture getTextura() {
		return textura;
	}
	


	/*
	 * Combina x cantidad de texturas en una sola, compone de atras para adelante, es decir que la primera textura pasada por parametro va a ser la de mas atras
	 * FIXME me parece que aca va a haber un problema cuando el juego guarde los datos, no se si va a crear la textura.
	 */
	public static Texture combinarTexturas(Texture ...texturas) {
		Pixmap pixmaps[] = new Pixmap[texturas.length];
		
		//Preparar texturas y crear pixmaps
		for(int i = 0; i<texturas.length;i++) {
			texturas[i].getTextureData().prepare();
			pixmaps[i] = texturas[i].getTextureData().consumePixmap();
		}
		
		
		for(int i = pixmaps.length-1; i>=1;i--) {
			System.out.println(i);
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
			texturasTODAS[i].getTextureData().prepare();
			pixmaps[i] = texturasTODAS[i].getTextureData().consumePixmap();
		}
		
		
		for(int i = pixmaps.length-1; i>=1;i--) {
			System.out.println(i);
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
