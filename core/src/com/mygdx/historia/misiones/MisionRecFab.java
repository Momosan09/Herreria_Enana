package com.mygdx.historia.misiones;

import com.mygdx.enums.EstadosMision;
import com.mygdx.historia.Mision;
import com.mygdx.historia.MisionesDelJuego;

public class MisionRecFab extends Mision{

	protected int cantidadObjetivo;
	protected int cantidadConseguida;
	protected String objeto;
	protected boolean objetoFabricado = false; //Por ahora no esta comprobando si lo fabrico, o sea que lo puede comprar y que se complete igual
	
	public MisionRecFab(MisionesDelJuego datosMision) {
		super(datosMision);
		cantidadObjetivo = datosMision.getCantidadObjetivo();
		cantidadConseguida = 0;
		objeto = datosMision.getObjeto();
		
	}
	
	public void comprobarCondicion() {
		super.comprobarCondicion();
		if(cantidadObjetivo == cantidadConseguida) {
			setEstado(EstadosMision.COMPLETADA);
			setObjetoFabricado();
		}
	}
	
	public int getCantidadConseguida() {
		return cantidadConseguida;
	}
	
	public void setCantidadConseguida(int cant) {
		if(cantidadConseguida < cantidadObjetivo) {			
		cantidadConseguida += cant;
		}else if(cantidadConseguida == cantidadObjetivo) {
			estado = EstadosMision.COMPLETADA;
			setObjetoFabricado();
		}
	}
	
	public void setObjetoFabricado() {
		objetoFabricado = true;
	}
	
	public String getObjeto() {
		return objeto;
	}
	
	public int getCantidadObjetivo() {
		return cantidadObjetivo;
	}
	

}
