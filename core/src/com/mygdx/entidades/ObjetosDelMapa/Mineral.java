package com.mygdx.entidades.ObjetosDelMapa;

import com.badlogic.gdx.Gdx;
import com.mygdx.entidades.Entidad;
import com.mygdx.enums.Items;
import com.mygdx.utiles.HelpDebug;

public class Mineral extends Entidad{
	
	public int vida = 100;

	public Mineral(float x, float y, String rutaTextura) {
		super(x, y, rutaTextura);
		// TODO Auto-generated constructor stub
	}

	
	public void click() {
		if(Gdx.input.isTouched()) {
			if((Gdx.input.getX() >= this.posicion.x && Gdx.input.getX() <= (this.posicion.x + this.textura.getWidth())) && (Gdx.input.getY() >= this.posicion.y && Gdx.input.getY() <= (this.posicion.y + this.textura.getHeight())));
			//System.out.println("piedrita");
			this.vida -= 10;
			
		}
	}

	public void minar() {
		
		if((getJugadorEnRango() && buscarPorItemEnJugador(Items.PICO)) ) {
			click();
			if(this.vida < 0) {
				System.out.println("muerte");
			}

		}else {
			System.out.println("No tiene pico");
		}
	}
	

}
