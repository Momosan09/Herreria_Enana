package com.mygdx.entidades.ObjetosDelMapa;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Jugador;
import com.mygdx.enums.Items;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.DibujarFiguras;
import com.mygdx.utiles.HelpDebug;

public class Mineral extends Entidad{
	
	public String nombre;
	public int vida = 100;
	public int valor=5;
	private boolean comprar = false, cerrar = false, comprable = false;
	private boolean dialogoAbierto = false;


	
	public Mineral(float x, float y, World world, boolean comprable, String rutaTextura, String nombre) {
		super(x, y, world, rutaTextura);
		this.comprable = comprable;
		this.nombre = nombre;
	}
	
	public Mineral(float x, float y, boolean comprable, String rutaTextura, String nombre) {
		super(x, y,comprable, rutaTextura);
		this.comprable = comprable;
		this.nombre = nombre;
	}

	
	public void click(Jugador jugador) {
		DibujarFiguras.dibujarRectanguloLleno(this.posicion.x,this.posicion.y, this.textura.getWidth(), this.textura.getHeight(), Color.valueOf(Colores.ROJO));
		System.out.println(jugador.getPosicion().x);
		if(Gdx.input.isTouched()) {
			System.out.println("click");
	        if (Gdx.input.getX() >= (this.posicion.x-textura.getWidth()) && Gdx.input.getX() <= (this.posicion.x+textura.getWidth())){
	        		System.out.println("puntero x =" + Gdx.input.getX());
	        		System.out.println(posicion.x);
	                // El toque está dentro del rango del mineral
	                this.vida -= 10;
	            }
	        }
	}

	public void minar(Jugador jugador) {	
		if((getJugadorEnRango() && buscarPorItemEnJugador(Items.PICO)) ) {
			click(jugador);
			if(this.vida <= 0) {
				System.out.println(HelpDebug.debub(getClass())+"muerte");
				jugador.getMinerales().add(this);
			}

		}else {
			//System.out.println("No tiene pico");
		}
	}
	
	public boolean comprar(Jugador jugador) {
	    if ((getJugadorEnRango() && isComprable()) && Gdx.input.isTouched()) {
	        // Cambiar el estado del diálogo
	        comprar = true;
	        return comprar;
	    }
	    return comprar;
	}
	
	public boolean getComprar() {
		return comprar;
	}

	 public boolean isComprable() {
	        return comprable;
	    }
	 public void abrirDialogo() {
		    dialogoAbierto = true;
		    // Muestra el diálogo de compra aquí
		}

		public void cerrarDialogo() {
		    dialogoAbierto = false;
		    // Cierra el diálogo de compra aquí
		}
		

	
}
