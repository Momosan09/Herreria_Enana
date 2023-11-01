package com.mygdx.entidades.ObjetosDelMapa;

import com.badlogic.gdx.Gdx;
import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Jugador;
import com.mygdx.enums.Items;
import com.mygdx.utiles.HelpDebug;

public class Mineral extends Entidad{
	
	public String nombre;
	public int vida = 100;
	public int valor=5;
	private boolean comprar = false, cerrar = false;

	public Mineral(float x, float y, boolean comprable, String rutaTextura) {
		super(x, y, comprable,rutaTextura);
	}

	
	public void click() {
		if(Gdx.input.isTouched()) {
			if((Gdx.input.getX() >= this.posicion.x && Gdx.input.getX() <= (this.posicion.x + this.textura.getWidth())) && (Gdx.input.getY() >= this.posicion.y && Gdx.input.getY() <= (this.posicion.y + this.textura.getHeight())));
			//System.out.println("piedrita");
			this.vida -= 10;
			
		}
	}

	public void minar(Jugador jugador) {
		if((getJugadorEnRango() && buscarPorItemEnJugador(Items.PICO)) ) {
			click();
			if(this.vida <= 0) {
				System.out.println(HelpDebug.debub(getClass())+"muerte");
				jugador.getMinerales().add(this);
			}

		}else {
			//System.out.println("No tiene pico");
		}
	}
	
	public void comprar(Jugador jugador) {
		if((((getJugadorEnRango()) && this.isComprable()) && Gdx.input.isTouched()) && !cerrar) {
			System.out.println("click compra");
			comprar = true;
		}else {
			//System.out.println("No tiene pico");
		}
	}
	
	public boolean getComprar() {
		return comprar;
	}
	
	public void dejarDeComprar() {
		cerrar = true;
	}

}
