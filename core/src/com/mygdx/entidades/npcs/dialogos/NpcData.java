package com.mygdx.entidades.npcs.dialogos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.entidades.npcs.respuestas.Npc_Respuestas_Vendedor_Tienda;
import com.mygdx.entidades.npcs.respuestas.Npc_Respuestas_Viejo;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.Recursos;

public enum NpcData implements DialogosNPC{

	VENDEDOR_AMBULANTE("Vendedor Ambulante",  Recursos.VENDEDOR_AMBULANTE_PORTRAIT, Npc_Dialogos_Vendedor_Ambulante.obtenerTodosLosMensajes()),
	VIEJO("Viejin", Recursos.VENDEDOR_AMBULANTE_PORTRAIT, Npc_Dialogos_Viejo.obtenerTodosLosMensajes()),
	VENDEDOR_TIENDA("Vendedor Tienda", Recursos.VENDEDOR_TIENDA_PORTRAIT, Npc_Dialogos_Vendedor_Tienda.obtenerTodosLosMensajes()),
	REY("Rey", Recursos.VENDEDOR_AMBULANTE_PORTRAIT, Npc_Dialogos_Rey.obtenerTodosLosMensajes());
	
	private final String _nombre;
	private final Texture _retrato;
	private ArrayList<String> _dialogos;
	private ArrayList<String[]> paqueteDeCharlas;

	NpcData(String nombre, String retratoRuta, ArrayList<String> _dialogos) {
		this._nombre = nombre;
		this._retrato = new Texture(retratoRuta);
		this._dialogos = _dialogos;
		paqueteDeCharlas = new ArrayList<String[]>();
		
		empaquetarDialogos(); // prepara los dialogos en arrays de 3 posiciones, 0 = mensaje, 1 y 2 = respuestas
	}

	public String getNombre() {
		return _nombre;
	}
	
	public Texture getTextura() {
		return _retrato;
	}
	
    public ArrayList<String[]> getBloquesDeCharla() {
        return paqueteDeCharlas;
    }

	@Override
	public String getMensaje(int index) {
		// TODO Auto-generated method stub
		return _dialogos.get(index);
	}
	
	public void empaquetarDialogos() {
	    int bloqueTamano = 3; // Tamaño de cada bloque
	    for (int i = 0; i < _dialogos.size() / bloqueTamano; i++) {
	        String[] datos = new String[bloqueTamano];
	        for (int j = 0; j < bloqueTamano; j++) {
	            datos[j] = _dialogos.get(i * bloqueTamano + j);//multiplico "i" por 3 pq asi me lo da como quiero
	        }
	        paqueteDeCharlas.add(datos);

	        // Imprime para depuración
	        System.out.println(HelpDebug.debub(getDeclaringClass())+"Bloque " + i + ": " + Arrays.toString(datos));
	    }
	}


}
	

