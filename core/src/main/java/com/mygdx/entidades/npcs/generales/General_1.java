package com.mygdx.entidades.npcs.generales;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.General;
import com.mygdx.entidades.NpcData;
import com.mygdx.utiles.EstadoMundo;
import com.mygdx.utiles.recursos.Recursos;

public class General_1 extends General{

	public General_1(float x, float y, World world, EstadoMundo estadoM, String rutaDialogos) {
		super(x, y, world, Recursos.npc.enanos.VIEJO, NpcData.GENERAL_1, 32, 64, estadoM, rutaDialogos);

	}


}
