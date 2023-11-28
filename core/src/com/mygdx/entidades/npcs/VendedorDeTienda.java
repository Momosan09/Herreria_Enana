package com.mygdx.entidades.npcs;

import com.mygdx.entidades.Npc;
import com.mygdx.entidades.npcs.dialogos.NpcData;

public class VendedorDeTienda extends Npc{

	public VendedorDeTienda(float x, float y, String ruta, NpcData data) {
		super(x, y, ruta, data);
	}
	
	@Override
	public NpcData getData() {
		return null;
	}

}
