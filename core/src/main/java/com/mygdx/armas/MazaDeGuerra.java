package com.mygdx.armas;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.utiles.HelpDebug;

public class MazaDeGuerra extends Arma{

	public MazaDeGuerra(Mineral metalBase) {
		super(new Texture("objetosDelMundo/inanimados/items/Mazas/hierroMaza.png"), metalBase);//poner atributos propios por ser maza de guerra
		System.out.println(HelpDebug.debub(getClass()) + "---- ATENCION: El sprite de la maza esta hardcodeado");
		//TODO: hacer un sistema flexible igual que el de los minerales, aprovechando ya el enum TipoMinerales tengo que crear otro FiguraArma para poder completar rutas de textura como con los minerales. Solo que en este caso tengo que ver como superponer las texturas en un solo sprite

	}
	
}
