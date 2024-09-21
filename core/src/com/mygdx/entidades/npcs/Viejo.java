package com.mygdx.entidades.npcs;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Npc;
import com.mygdx.entidades.npcs.dialogos.NpcData;
import com.mygdx.eventos.EventoInteraccionNPC;
import com.mygdx.eventos.Listeners;

public class Viejo extends Npc{

	public Viejo(float x, float y, World world, String ruta, NpcData data) {
		super(x, y, world, ruta, data,8,8);

	}
	
	@Override
	public NpcData getData() {
		// TODO Auto-generated method stub
		return null;
	}


}
