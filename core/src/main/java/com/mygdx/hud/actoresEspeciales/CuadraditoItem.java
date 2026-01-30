package com.mygdx.hud.actoresEspeciales;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.combinaciones.IngredientesId;
import com.mygdx.entidades.ObjetosDelMapa.Items.Item;
import com.mygdx.enums.Items;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.recursos.Recursos;

public class CuadraditoItem extends Table {
    private Label nombre, valor;
    private Image imagenItem;
    private Stack pila;

    public CuadraditoItem(final IngredientesId item) {
        setDebug(true);
        setTouchable(Touchable.enabled); // Permitir interacción

        pila = new Stack();
        
        // Estilo de fuente
        Label.LabelStyle labelStyle = EstiloFuente.generarFuente(30, Colores.BLANCO, false);
        
        // Inicializar componentes
        nombre = new Label(item.tipoI.getNombre(), labelStyle);
        valor = new Label("" + item.tipoI.getValor().getMonedasCobre(), labelStyle);
        imagenItem = new Image(item.tipoI.getTextura());
        
        // Fondo del contenedor
        setBackground(new TextureRegionDrawable(new Texture(Recursos.hud.CASILLERO_VENTA_HUD)));
        
        // Organizar elementos

      pila.add(imagenItem);
      pila.add(nombre);
      pila.add(valor);
      add(pila).size(100);


    }
}