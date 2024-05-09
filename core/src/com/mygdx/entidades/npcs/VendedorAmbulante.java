package com.mygdx.entidades.npcs;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Npc;
import com.mygdx.entidades.NpcInterface;
import com.mygdx.entidades.Vendedor;
import com.mygdx.entidades.npcs.dialogos.NpcData;

public class VendedorAmbulante extends Vendedor{

		public VendedorAmbulante(float x, float y, World world, String ruta, NpcData data) {
			super(x, y, world, ruta, data, VendedorData.AMBULANTE);

		}

		@Override
		public NpcData getData() {
			// TODO Auto-generated method stub
			return null;
		}


}
