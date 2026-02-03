package com.mygdx.armas;

/**
 * Este enum sirve para definir las rutas de los equipos/armas en conjunto con TipoMinerales. Funciona igual que EstadosMinerales pero, para mantener el codigo limpio lo separe en otro enum.
 * @author  Momosan09
 *
 */

public enum EstadosArmas {

	MAZA_1("Equipo/Maza1.png");
	
	public String ruta;
	
	EstadosArmas(String ruta){
		this.ruta = ruta;
	}
}
