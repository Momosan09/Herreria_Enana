package com.mygdx.entidades.npcs.generales;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.General;
import com.mygdx.entidades.npcs.dialogos.NpcData;
import com.mygdx.utiles.recursos.Recursos;

public class General_1 extends General{

	public General_1(float x, float y, World world) {
		super(x, y, world, Recursos.npc.enanos.VIEJO, NpcData.GENERAL_1, 32, 64);

	}


}
