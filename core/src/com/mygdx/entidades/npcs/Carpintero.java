package com.mygdx.entidades.npcs;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Npc;
import com.mygdx.entidades.npcs.dialogos.NpcData;

public class Carpintero extends Npc{

	public Carpintero(float x, float y, World world, String ruta, NpcData data) {
		super(x, y, world, ruta, data);

	}

	@Override
	public NpcData getData() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
