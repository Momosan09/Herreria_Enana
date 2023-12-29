package com.mygdx.entidades.npcs.dialogos;

import java.util.ArrayList;

import com.mygdx.entidades.Npc;

public class Charla {

	private Npc locutor;
	
	//Requisitos para que se de la charla
	private int diaEspecifico = -1;
	
	//Texto
	private ArrayList<String> _dialogos, _respuestas;
	
	public ArrayList<ArrayList<String>> bloques;
	
	public Charla( int diaEspecifico, ArrayList<String> _dialogos, ArrayList<String> _respuestas) {
		this.diaEspecifico = diaEspecifico;
		this._dialogos = _dialogos;
		this._respuestas = _respuestas;
		bloques = new ArrayList<ArrayList<String>>();
		
		bloqueUno();
	}

	public void bloqueUno() {
		ArrayList<String> bloqueUno = new ArrayList();
		
		bloqueUno.add(_dialogos.get(0));
		bloqueUno.add(_respuestas.get(0));
		bloqueUno.add(_respuestas.get(1));
		
		bloques.add(bloqueUno);
		System.out.println(bloques.get(0).size());

	}
	
	public void bloqueDos() {
		ArrayList<String> bloqueDos = new ArrayList();
		
		bloqueDos.add(_dialogos.get(1));
		bloqueDos.add(_respuestas.get(2));
		bloqueDos.add(_respuestas.get(3));
		
		bloques.add(bloqueDos);
	}
	
}
