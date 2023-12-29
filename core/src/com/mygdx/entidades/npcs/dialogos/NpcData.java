package com.mygdx.entidades.npcs.dialogos;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.entidades.npcs.respuestas.Npc_Respuestas_Vendedor_Tienda;
import com.mygdx.entidades.npcs.respuestas.Npc_Respuestas_Viejo;
import com.mygdx.utiles.Recursos;

public enum NpcData implements DialogosNPC{

	VENDEDOR_AMBULANTE("Vendedor Ambulante",  Recursos.VENDEDOR_AMBULANTE_PORTRAIT, Npc_Dialogos_Vendedor_Ambulante.obtenerTodosLosMensajes(), Npc_Respuestas_Viejo.obtenerTodosLosMensajes()),
	VIEJO("Viejin", Recursos.VENDEDOR_AMBULANTE_PORTRAIT, Npc_Dialogos_Viejo.obtenerTodosLosMensajes(), Npc_Respuestas_Viejo.obtenerTodosLosMensajes()),
	VENDEDOR_TIENDA("Vendedor Tienda", Recursos.VENDEDOR_TIENDA_PORTRAIT, Npc_Dialogos_Vendedor_Tienda.obtenerTodosLosMensajes(), Npc_Respuestas_Viejo.obtenerTodosLosMensajes()),
	REY("Rey", Recursos.VENDEDOR_AMBULANTE_PORTRAIT, Npc_Dialogos_Rey.obtenerTodosLosMensajes(), Npc_Respuestas_Viejo.obtenerTodosLosMensajes());
	
	private final String _nombre;
	private final Texture _retrato;
	private ArrayList<String> _dialogos;
	private ArrayList<String> _respuestas;

	
	NpcData(String nombre, String retratoRuta, ArrayList<String> _dialogos, ArrayList<String> _respuestas) {
		this._nombre = nombre;
		this._retrato = new Texture(retratoRuta);
		this._dialogos = _dialogos;
		this._respuestas = _respuestas;
	}

	public String getNombre() {
		return _nombre;
	}
	
	public Texture getTextura() {
		return _retrato;
	}
	
    public ArrayList<String> getDialogos() {
        return _dialogos;
    }

    public ArrayList<String> getRespuestas() {
        return _respuestas;
    }
    
	@Override
	public String getMensaje(int index) {
		// TODO Auto-generated method stub
		return _dialogos.get(index);
	}
	

	public String getRespuesta(int index) {
		// TODO Auto-generated method stub
		return _respuestas.get(index);
	}




	
}
	

