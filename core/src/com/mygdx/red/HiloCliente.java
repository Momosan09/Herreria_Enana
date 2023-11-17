package com.mygdx.red;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.mygdx.enums.Direcciones;
import com.mygdx.game.Principal;
import com.mygdx.pantallas.Juego;
import com.mygdx.red.Cliente;
import com.mygdx.utiles.Render;

public class HiloCliente extends Thread{

	private DatagramSocket socket;
	private boolean fin = false;
	private InetAddress ipServer;
	private int puerto = 35323;//El puerto del servidor siempre va a estar fijo
	private boolean conexionExitosa = false;
	private boolean servidorLleno = false;
	private boolean esperandoJugadores = true;
	public boolean comenzoJuego = false;
	private Juego game;
	private int idCliente;
	
	public HiloCliente(Juego game) {
		this.game = game;

		try {
			socket = new DatagramSocket();
			//socket.setReuseAddress(true); // Permite reutilizar el puerto
			ipServer = InetAddress.getByName("255.255.255.255");//Broadcast
			//enviarMensaje("conectar");
		} catch (SocketException | UnknownHostException e) {
			fin = true;
			e.printStackTrace();
		}
		//enviarMensaje("conectar");
		
	}
	
	@Override
	public void run() {
		do {
			byte[] datos = new byte[1024];
			DatagramPacket dp = new DatagramPacket(datos, datos.length);
			try {
				socket.receive(dp);
				procesarMensaje(dp);
			} catch (IOException e) {
				//e.printStackTrace();

			}
		}while(!fin);
		socket.close(); // Cierra el socket al salir del bucle
	}

	private void procesarMensaje(DatagramPacket dp) {
		String msg = new String(dp.getData()).trim();//trim() lo que hace es sacar los espacios
		String[] mensajeCompuesto = msg.split("#");
		
		if(mensajeCompuesto[0].equals("conexion_exitosa")) {
			conexionExitosa = true;
			ipServer = dp.getAddress();//deja de usar broadcast apenas el servidor le responde
			
			if(mensajeCompuesto[1].equals("0")) {
				idCliente = 0;
			}else if(mensajeCompuesto[1].equals("1")) {
				idCliente = 1;
			}
			
			System.out.println("-----Soy el cliente numero = " + idCliente);
		}
		
		if(mensajeCompuesto[0].equals("jugadores_listos")) {
			esperandoJugadores = false;
		}
		
		if(mensajeCompuesto[0].equals("servidor_lleno")) {
			servidorLleno = true;
		}	
		
		if(comenzoJuego) {
			if(this.idCliente==0) {
				game.idJugador=0;
			}else if(this.idCliente==1){
				game.idJugador=1;
			}
		}
		
		if(conexionExitosa) {
		if(mensajeCompuesto[0].equals("actualizar_posicion")) {
			float x = Float.valueOf(mensajeCompuesto[2]);
			float y = Float.valueOf(mensajeCompuesto[3]);
			if(mensajeCompuesto[1].equals("1")) {
				game.getJugador1().movimientoPorRed(x, y);
				game.getJugador1().movimientoCamara();			
			}else {
				game.getJugador2().movimientoPorRed(x, y);
				game.getJugador2().movimientoCamara();		
			}
		}
		
		if(mensajeCompuesto[0].equals("eliminar")) {
			if(mensajeCompuesto[1].equals("mineral")) {
				float posX = Float.valueOf(mensajeCompuesto[2]);
				float posY = Float.valueOf(mensajeCompuesto[3]);
				game.getMineralesManager().eliminarMineral(posX, posY);;
				System.out.println("elimnadororor");
			}
		}
		}
		//mensajeCompuesto(msg);
		
	}

	
	public void enviarMensaje(String msg) {
		byte[] mensaje = msg.getBytes();

		try {
			DatagramPacket dp = new DatagramPacket(mensaje, mensaje.length, ipServer, puerto);
			socket.send(dp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isConexionExitosa() {
		return conexionExitosa;
	}
	
	public boolean isServidorLleno() {
		return servidorLleno;
	}

	public boolean isEsperandojugadores() {
		return esperandoJugadores;
	}
	
	public int getIdCliente() {
		return idCliente;
	}
	
	public void fin() {
		fin = true;
		socket.close();
	}
	
	
	public void setGame(Juego game) {//Sirve para pasarle un Juego, porque en el constructor del static le estoy pasando uno nulo, entonces llamo a esta funcion desde la clase del juego y fue
		this.game = game;
	}
}