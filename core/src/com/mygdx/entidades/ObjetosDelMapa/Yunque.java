package com.mygdx.entidades.ObjetosDelMapa;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetoDelMapa;
import com.mygdx.enums.TipoCombinacion;
import com.mygdx.hud.YunqueHUD;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.MyDragAndDrop;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;

public class Yunque extends ObjetoDelMapa{

	private YunqueHUD hud;
	private MyDragAndDrop dragDrop;
	private boolean entro = false;
	
	public Yunque(float x, float y, World world, String rutaTextura, YunqueHUD hud, Jugador jugador) {
		super(x, y, world, rutaTextura);
		this.hud = hud;
		dragDrop = new MyDragAndDrop(jugador);
	}
	
	
	private void dragAndDropUnaVez() {//necesito que este metodo se llame una sola vez cada vez que se muestre el interfaz
		if(!entro) {
			dragDrop.refrescar();
			dragDrop.create();
			entro = true;
		}
	}
	
	public void mostarHUD(Jugador jugador) {
		if (getJugadorEnRango() && apretoE) {
			dragAndDropUnaVez();
			Gdx.input.setInputProcessor(dragDrop.getStage());
			hud.mostrar();
			dragDrop.render();
			MundoConfig.mostrarHUD=false;
		} else{
			Gdx.input.setInputProcessor(Recursos.muxJuego);//No estoy seguro de que sea la mejor opcion pero bue
			MundoConfig.mostrarHUD=true;
			hud.ocultar();
			entro = false;
		}
	}
	

	public YunqueHUD getHUD() {
		return hud;
	}
	
	public Stage getDragAndDrop() {
		return dragDrop.getStage();
	}

}
