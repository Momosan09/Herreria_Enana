package com.mygdx.historia;

import com.mygdx.utiles.Recursos;

public enum FabricablesMision {

	
	SIERRA_CIRCULAR(Recursos.bundle.get("mision.fabricable.sierra_circular"));
	
	private String nombre;

	FabricablesMision(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
}
