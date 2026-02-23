package com.mygdx.utiles;

import java.util.ArrayList;
import java.util.function.Predicate;

import com.mygdx.entidades.ObjetosDelMapa.Items.Item;


/*
 * Clase generica que hereda ArrayList pero la potencia con metodos utiles
 */
public class InventarioList<G> extends ArrayList<G> {

    public G getUltimo() {
        if (isEmpty()) return null;
        return get(size() - 1);
    }

	public void agregar(G obj) {
		add(obj);
	}
    
    public void eliminar(G obj) {
        remove(obj);
    }
    
    public InventarioList<G> getTodos(){
    	return this;
    }
    
	public G getItem(int i) {
		if(!isEmpty()) {			
			return this.get(i);
		}
		
		return null;
	}

    public void eliminarSi(Predicate<G> condicion) {
        removeIf(condicion);
    }

    public G buscar(Predicate<G> condicion) {
        for (G obj : this) {
            if (condicion.test(obj)) {
                return obj;
            }
        }
        return null;
    }
    

}
