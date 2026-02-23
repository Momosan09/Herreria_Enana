package com.mygdx.armas.modificadores;

import com.mygdx.entidades.Entidad;

public interface EfectosArma {
    void aplicar(Entidad atacante, Entidad objetivo/*, ContextoCombate ctx*/);
}
