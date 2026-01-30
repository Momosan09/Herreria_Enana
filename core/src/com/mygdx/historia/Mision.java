package com.mygdx.historia;

import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Jugador;
import com.mygdx.enums.EstadosMision;
import com.mygdx.utiles.HelpDebug;

public abstract class Mision {

	protected String requisor;//quien nos da la mision
	protected int diasParaCompletar;
	protected String descripcion;
	protected TipoMision tipo;
	protected boolean recompensaAdquirida = false;
	protected int cobre, plata, oro;
	protected String id;
	protected EstadosMision estado = EstadosMision.PENDIENTE;
	
	public Mision(MisionesDelJuego datosMision) {
		this.requisor = datosMision.getRequisor();
		this.diasParaCompletar = datosMision.getDiasParaCompletar();
		this.descripcion = datosMision.getDescripcion();
		this.tipo = datosMision.getTipo();
		this.oro = datosMision.getOro();
		this.plata = datosMision.getPlata();
		this.cobre = datosMision.getCobre();
		this.id = datosMision.getId();
		System.out.println(HelpDebug.debub(getClass())+"Creada correctamente la mision ["+id+"]");
		System.out.println(descripcion);
	}
	
	protected void comprobarCondicion() {
		if(diasParaCompletar > 0) {	
	
		}else if(diasParaCompletar == -1){

		}else {
			estado = EstadosMision.FALLADA;
		}
		

	}
	
	
	public void setEstado(EstadosMision e) {
		estado = e;
		//comprobarCondicion();
	}
	
	/**
	 * Cambia el estado de la mision a Fallada
	 * @param e
	 */
	public void setFallada() {
		estado = EstadosMision.FALLADA;
		comprobarCondicion();
	}
	
	public boolean isFallada() {
		return (estado == EstadosMision.FALLADA ? true : false);
	}
	
	public void darRecompensa(Jugador jugador) {
		if(	estado == EstadosMision.COMPLETADA && !recompensaAdquirida) {			
			jugador.monedero.agregarMonedasOro(oro);
			jugador.monedero.agregarMonedasPlata(plata);
			jugador.monedero.agregarMonedasCobre(cobre);
		recompensaAdquirida = true;
		}
	}
	
	public String getEntidad() {
		return requisor;
	}
	
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public int getDiasRestantes() {
		return diasParaCompletar;
	}
	
	/**
	 * resta uno cada vez que pasa un dia. Se llama en Tiempo.java
	 */
	public void restarDias() {
		if(diasParaCompletar != -1) {
			diasParaCompletar--;
		}
	}
	
	public TipoMision getTipo() {
		return tipo;
	}
	
	
	public EstadosMision getEstado() {
		return estado;
	}
	
	public int getOro() {
		return oro;
	}
	
	public int getPlata() {
		return plata;
	}
	
	public int getCobre() {
		return cobre;
	}
	
	public String getId() {
		return id;
	}
}
