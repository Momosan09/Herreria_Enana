package com.mygdx.entidades.npcs.dialogos;

public enum NpcData {

	VENDEDOR("Vendedor","Hola enum");
	
	private final String _nombre;
	private final String _dialogos;
	
	NpcData(String nombre, String dialogos) {
		this._nombre = nombre;
		this._dialogos = dialogos;
	}

	public String getNombre() {
		return _nombre;
	}
	
	public String getDialogos(){
		return _dialogos;
	}
	
}
	

