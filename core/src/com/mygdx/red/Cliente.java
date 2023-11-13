package com.mygdx.red;

import com.mygdx.utiles.HelpDebug;

public class Cliente {


	private boolean empiezaChat=false;
	
	public Cliente() {
		UtilesRed.hc = new HiloCliente(UtilesRed.game);
		UtilesRed.hc.start();			
		UtilesRed.hc.enviarMensaje("conectar");		
	}

	public void empezarChat() {
		this.empiezaChat = true;
	}
	
	public void cerrarCliente() {
		System.out.println(HelpDebug.debub(getClass())+"cerrando");
		UtilesRed.hc.fin();
		UtilesRed.hc.cerrarSocket();
		UtilesRed.hc.interrupt();
	}
}
