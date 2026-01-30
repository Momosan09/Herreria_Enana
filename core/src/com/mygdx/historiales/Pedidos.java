package com.mygdx.historiales;

import java.util.ArrayList;

public abstract class Pedidos {

	private static ArrayList<String> pedidos = new ArrayList<>();
	
	public static ArrayList<String> getPedidos() {
		return pedidos;
	}
}
