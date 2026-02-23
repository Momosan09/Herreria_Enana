package com.mygdx.entidades.npcs;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Npc;
import com.mygdx.entidades.NpcData;
import com.mygdx.utiles.EstadoMundo;

public class Viejo extends Npc{

	public Viejo(float x, float y, World world, String ruta, NpcData data, EstadoMundo estadoM, String rutaDialogos) {
		super(x, y, world, ruta, data,8,8, estadoM, rutaDialogos);

	}
	
	@Override
	public NpcData getData() {
		return null;
	}


}
