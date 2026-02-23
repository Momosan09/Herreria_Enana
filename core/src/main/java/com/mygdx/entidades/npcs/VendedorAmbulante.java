package com.mygdx.entidades.npcs;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.NpcData;
import com.mygdx.entidades.Vendedor;
import com.mygdx.utiles.EstadoMundo;

public class VendedorAmbulante extends Vendedor{

		public VendedorAmbulante(float x, float y, World world, String ruta, NpcData data, EstadoMundo estadoM, String rutaDialogos) {
			super(x, y, world, ruta, data, VendedorData.AMBULANTE, estadoM, rutaDialogos);

		}

		@Override
		public NpcData getData() {
			return null;
		}


}
