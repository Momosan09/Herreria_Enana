package com.mygdx.eventos;

import java.util.EventListener;
import com.mygdx.hud.CartaHUD;

public interface EventoRecibirCarta extends EventListener{
	void recibirCarta(CartaHUD carta);
}
