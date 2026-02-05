package com.mygdx.entidades;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Null;
import com.mygdx.armas.Arma;
import com.mygdx.armas.EstadosArmas;
import com.mygdx.armas.modificadores.Modificadores;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.npcs.dialogos.NpcData;

public abstract class General extends Npc{

	private float vida;
	//private float fuerza;
	
	private Arma arma;
	
	//private Armadura armadura;
	
	@Null private Modificadores modificadorFavorito;
	
	@Null private Mineral metalFavorito;
	
	@Null private EstadosArmas formaFavorita;
	
	public General(float x, float y, World world, String ruta, NpcData data, int ancho, int alto) {
		super(x, y, world, ruta, data, ancho, alto);
	}

	
}
