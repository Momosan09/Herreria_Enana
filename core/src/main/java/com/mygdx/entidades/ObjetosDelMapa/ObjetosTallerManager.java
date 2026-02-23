package com.mygdx.entidades.ObjetosDelMapa;

import java.util.ArrayList;

import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.InteraccionManager;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetoDelMapa;

public class ObjetosTallerManager{

	private ArrayList<ObjetoDelMapa> objetos;
	private InteraccionManager interaccionManager;
	
	public ObjetosTallerManager(InteraccionManager interaccionManager) {
		objetos = new ArrayList<>();
		this.interaccionManager = interaccionManager;
	}
	
	public void agregarObjeto(ObjetoDelMapa entidad) {
		objetos.add(entidad);
		interaccionManager.registrar(entidad);
	}
	
    
    public void renderizar() {
        for (ObjetoDelMapa entidad: objetos) {
        	entidad.draw();
        	entidad.dibujarAreaDeInteraccion();
        }
    }

	
}
