package com.mygdx.hud.actoresEspeciales;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.entidades.ObjetosDelMapa.Items.Item;
import com.mygdx.enums.Items;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.Recursos;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class CuadraditoItem extends Table{
    
    private Label nombre, valor;
    private Table contenedor;
    private Stack pila;
    private Label.LabelStyle labelStyle;
    
    public CuadraditoItem(Items item) {
    	this.setDebug(true);
        contenedor = new Table();
        labelStyle = EstiloFuente.generarFuente(30, Colores.BLANCO, false);
        nombre = new Label(item.getNombre(), labelStyle);
        valor  = new Label(""+item.getValor(), labelStyle);
        
        contenedor.setBackground(new TextureRegionDrawable(new Texture(Recursos.CASILLERO_VENTA_HUD)));
        contenedor.add(nombre);
        contenedor.row();
        contenedor.add(new Image(item.getTextura()));
        contenedor.add(valor);

        contenedor.add(pila);
        add(contenedor);
        
        
        //agregarListener();
        
    }
    
    public void agregarListener() {
    	this.addListener(new InputListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                System.out.println("El cursor está sobre "+ nombre);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
                System.out.println("El cursor ha salido de " + nombre);
            }
            
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            	System.out.println("auch");
            	return true;
            }
        });
    }
}
