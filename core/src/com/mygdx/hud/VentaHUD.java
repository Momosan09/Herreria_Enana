package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.Vendedor;
import com.mygdx.entidades.ObjetosDelMapa.Items.Item;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.enums.Items;
import com.mygdx.hud.actoresEspeciales.CuadraditoItem;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.Recursos;

public class VentaHUD extends HUD {

	private Vendedor vendedor;
	private NinePatchDrawable fondo;
	private Skin skin;
	private Button cerrarBoton;
	
	public VentaHUD(Jugador jugador, Vendedor vendedor) {
		super(jugador);
		this.vendedor = vendedor;
		construir();
	}
	
	public VentaHUD(Jugador jugador) {
		super(jugador);
		construir();
	}


	@Override
	public void crearActores() {
		skin = new Skin(Gdx.files.internal(Recursos.SKIN));
		tabla = new Table();
		tabla.setFillParent(true);
		tabla.setDebug(false);

		fondo = new NinePatchDrawable(new NinePatch(new Texture(Recursos.VENTA_HUD)));
		contenedor = new Table();
		//contenedor.setBackground(fondo);
		  // contenedor.defaults().size(100, 100); //Establece el tamaño deseado para cada casillero

		cerrarBoton = new Button(skin);
		cerrarBoton.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				ocultar();
				MundoConfig.estadoJuego = EstadosDelJuego.JUEGO;
			}
		});
		
		   }

	@Override
	public void poblarStage() {
//		contenedor.setBackground(new TextureRegionDrawable(new Texture(Recursos.HORNO_TEXTURA)));
		if(vendedor != null) {
			actualizarProductos();
		}
		tabla.add(contenedor);
		tabla.add(cerrarBoton).top();
		stage.addActor(tabla);
		}



	
    private void comprarItem(Items item) {
        int precioOro = item.getValor().getMonedasOro();
        int precioPlata = item.getValor().getMonedasPlata();
        int precioCobre = item.getValor().getMonedasCobre();

        if (jugador.monedero.tieneElDinero(precioOro, precioPlata, precioCobre)) {
            jugador.monedero.restarDinero(precioOro, precioPlata, precioCobre);
            jugador.getItems().add(new Item(item));
            vendedor.getInventario().remove(item);
            actualizarProductos();
        } else {
            System.out.println("No tienes suficiente dinero.");
        }
    }

    private void actualizarProductos() {
        contenedor.clear();
        int indiceFilas = 0;

        for (final Items item : vendedor.getInventario()) {
            CuadraditoItem cuadraditoItem = new CuadraditoItem(item);
            cuadraditoItem.setTouchable(Touchable.enabled);
            contenedor.add(cuadraditoItem).pad(10);

            // Listener para detectar clics
            cuadraditoItem.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                	comprarItem(item);
                }
            });

            indiceFilas++;
            if (indiceFilas == 4) {
                contenedor.row();
                indiceFilas = 0;
            }
        }
    }
	
	public Vendedor getVendedor() {
		return vendedor;
	}
	
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
		contenedor.clear();
		actualizarProductos();
	
	}

	public void dispose() {
		Recursos.muxJuego.removeProcessor(stage);//tengo que sacar el stage del inputprocesor porque el mux es estatico, entonces cuando entro y salgo del juego, el mux agrega el nuevo stage pero sigue guardando el anterior
		stage.dispose();
		skin.dispose();
	}

}