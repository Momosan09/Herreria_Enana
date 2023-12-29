package com.mygdx.entidades.npcs;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Npc;
import com.mygdx.entidades.npcs.dialogos.Charla;
import com.mygdx.entidades.npcs.dialogos.NpcData;


public class VendedorDeTienda extends Npc{
	


	public VendedorDeTienda(float x, float y, World world, String ruta, NpcData data) {
		super(x, y, world, ruta, data,8,5);
	}
	
	@Override
	public NpcData getData() {
		return null;
	}
	
	

}
