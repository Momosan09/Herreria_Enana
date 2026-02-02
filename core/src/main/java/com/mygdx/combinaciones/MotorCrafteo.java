package com.mygdx.combinaciones;

import com.mygdx.armas.Equipo;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.enums.EstadosMision;
import com.mygdx.historia.Mision;
import com.mygdx.historia.TipoMision;
import com.mygdx.historia.misiones.MisionRecFab;
import com.mygdx.utiles.sonidos.ListaSonidos;
import com.mygdx.utiles.sonidos.SonidosManager;

public class MotorCrafteo {

	private InventarioCrafteo inventario;
	private static Jugador j;
	
	public MotorCrafteo(Jugador j) {
		setInventario(new InventarioJugador(j));
		this.j = j;
	}
	
    /**
     * Verifica si se puede fabricar una receta
     */
    public static boolean puedeFabricar(Receta receta, InventarioCrafteo inventario, Medios medioActual) {
    	if(receta!= null) { //El metodo buscar de Cargador de recetas devuelve una receta nula si no encontro, y por lo tanto, si no encontro no puede fabricar
        
    		// Medio correcto
        if (receta.medio() != medioActual) {
            return false;
        }



        if (receta.herramienta() != null) {
            if (!inventario.tieneItem(receta.herramienta())) {
                return false;
            }
        }
        // Entradas
        for (IngredienteReceta ing : receta.entradas()) {
            if (inventario.getCantidad(ing.ingrediente()) < ing.cantidad()) {
                return false;
            }
        }

        return true;
        
    	}else {
    		
    		return false;
    	}
    }

    /**
     * Fabrica la receta (ASUME que ya fue validada)
     */
    public static void fabricar(Receta receta, InventarioCrafteo inventario) {

        // Consumir entradas
        for (IngredienteReceta ing : receta.entradas()) {
            inventario.consumir(ing.ingrediente(), ing.cantidad());
            sonidosDeFabricar(receta.herramienta());
        }

        // Caso 1: receta normal (ingredientes)
        if (receta.salidas() != null) {
            for (IngredienteReceta out : receta.salidas()) {
                inventario.agregar(out.ingrediente(), out.cantidad());
                comprobarMisionesDeFabricar(out, out.cantidad());
            }
            return;
        }

        // Caso 2: receta de resultado final (arma/equipo)
        if (receta.salidaFinal() != null) {

            Mineral metalBase = obtenerMetalBase(receta);
            Equipo equipo = ResultadoFinalFactory.crear(receta.salidaFinal(), metalBase);

            j.getInventarioArmas().add(equipo);
        }
    }


	private static Mineral obtenerMetalBase(Receta receta) {
		for (IngredienteReceta ing : receta.entradas()) {
			if (ing.ingrediente().tipoM != null) {
				return CreadorDeMinerales.crear(ing.ingrediente());
			}
		}
		return null;
	}

        
        

        
    

	public InventarioCrafteo getInventario() {
		return inventario;
	}

	public void setInventario(InventarioCrafteo inventario) {
		this.inventario = inventario;
	}
	
	private static void comprobarMisionesDeFabricar(IngredienteReceta out, int cantidad) {
        for(MisionRecFab m : j.conseguirMisionesPorTipo(TipoMision.FABRICAR)) {
        	if(m.getObjeto() == out.ingrediente() && (m.getEstado() == EstadosMision.PENDIENTE)) {
        		j.avanzarMision(m, cantidad);
        	}
        }
	}
	
	private static void sonidosDeFabricar(IngredientesId herramienta) {
		
	    if (herramienta == null) {
	        return; // no hay sonido de herramienta
	    }
		
		switch (herramienta) {
		case SIERRA: {
			SonidosManager.reproducirSonido(ListaSonidos.SIERRA_METAL);
			break;
		}
		case ESQUEMA_HOJA_ESPADA: {
			SonidosManager.reproducirSonido(ListaSonidos.SONIDO_ESQUEMA);
			break;
		}
		case MAZA: {
			SonidosManager.reproducirSonido(ListaSonidos.GOLPE_MARTILLO_METAL);
			break;
		}
		default:
			//SonidosManager.reproducirSonido(ListaSonidos.FALLA);
		}
	}
}
