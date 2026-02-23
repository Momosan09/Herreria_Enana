package com.mygdx.entidades.npcs;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.NpcData;
import com.mygdx.entidades.Vendedor;
import com.mygdx.utiles.EstadoMundo;


public class VendedorDeTienda extends Vendedor{
	


	public VendedorDeTienda(float x, float y, World world, String ruta, NpcData data, EstadoMundo estadoM, String rutaDialgos) {
		super(x, y, world, ruta, data,VendedorData.TIENDA, estadoM, rutaDialgos);
	}
	
	@Override
	public NpcData getData() {
		return null;
	}
	
	

}
