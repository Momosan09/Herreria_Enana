## 18 JUL
    - Creacion inicial
    - Movimiento basico jugador
    - Cambio de textura dependiendo de la direccion en la que el jugador se mueva
    - Camara que sigue al jugador

## 19 JUL
    - Primer intento de multiples pantallas
    - La pantalla del menu tiene un Viewport el cual permite redimensionar la ventana manteniendo la relacion de aspecto
    - Creada una clase "Render.java" para tener mas flexibilidad con el batch
    - Creada una clase "Recursos.java" donde las rutas se almacenan en constantes
    - Creada una clase "Config.java" a la que no le doy ningun uso
    - Creada una clase "Texto.java" para imprimir texto en la pantalla del juego con mayor facilidad
    - Se cambio la fuente "Arial" por defecto de Libgdx por [Cardinal](https://www.dafont.com/es/cardinal.font) 
    - Se agrego un archivo "Pendiente.md" donde voy a poner las cosas que dejo para despues

## 20 JUL
    - ¡Maldita sea! despues de tanto incordio pude solucionar el tema de los textos del menu, ¡Y era una pavada!
    - Cuando pasas del menu a la pantalla del juego ahora aparece el jugador.
    - Agregada una melodia temporal para le menu principal (El audio debe ser reemplazado por uno creado por YO)
    - Se agrego un efecto de sondio al apretar cualquier tecla (la idea es despues usar ese efecto para los sonidos al navegar por el menu).

## 21 JUL 
    - Cambie el codigo de los textos en el menu
    - Agrege las opciones "jugar" y "configuracion" al menu
    - El ahora en el maldito menu se puede seleccionar una de las opciones "jugar" o "configuraciones", la seleccion se muestra con otro color y al presionar la tecla Enter cambia la pantalla (diferenciada)
    - Viewport y un texto para la pantalla de Configuracion

## 22 JUL 
    - Hice mi musiquita para el menu!

## 24 JUL 
    - Mapa cargado!
    - Ya se puede volver del menu de configuracion a el menu principal
    - Creado un metodo para poder implementar una navegacion por los textos mas facilmente (Entradas.java seleccionarOpcion(Texto[],int indiceMin,int indiceMax))

## 5 AGO
    - Imagen para el menu principal
    - Animacion para la imagen del menu principal!
    - Mapa nuevo

## 8 AGO
    - ANIMACIONES (era una tonteria y no me estaba dando cuenta)
    - Enum para las direcciones del jugador ¡No mas Strings!
    - Dada una textura de NxN dimensiones ahora puedo elegir que fila se va a renderizar sin la necesidad de separarlo en distintas imagenes

## 9 AGO 
    - Ya anda el Fat Jar
    - Creada una carpeta "comandos" con archivos ."bat" para ejecutar comandos que necesite

## 10 AGO
    - Mejoras en el movimiento del jugador

## 18 AGO
    - Hace un mes que empeze el juego!
    - Mejoras en el movimiento del jugador (La logica que cree yo funcionaba exactamemente como lo planeaba, el problema se daba en las animaciones verticales, las cuales eran reemplazadas por al animacion de estar quieto. Esta nueva logica, la cual yo no hice, hace que las animaciones funcionen correctamente pero me obliga a sacrificar el movimiento que me gustaba).
    (Ademas ya me harto este maldito movimiento del demonio)(Ver commit inmediatamante anterior para la logica de movimiento mia)

## 23 AGO
    - Segunda consigna
    - Hice la base del HUD
    - Minimas modificaciones al HUD

## 24 AGO
    - Logre hacer que el HUD se reescale pero ahora el problema es su posicion. Cuando se reescala la ventana, la imagen tambien lo hace, pero al parecer las posciones del viewport no y estas no se reacomodan
    - AAAAAAA lo logre! por fin despues de tanto incordio, el HUD se reescala y se mantiene en la poscion relativa a la ventana!! [dios](https://stackoom.com/en/question/3rhx7)
    - Viste todos los commits sobre el HUD, en especial el que decia algo asi como "Iaaajuuu"? Bueno ni bien termine con eso descubri algo llamado "Table" de scene2d.ui lo cual era justo lo que necesitaba, asique borre todo lo anterior y ahora de nuevo con el HUD

## 25 AGO
    - Un poco de HUD
    - Un poco mas de HUD, creo que este codigo esta creciendo mucho...
    - Mas HUD, ya casi esta terminado el esqueleto

## 26 AGO
    - "Terminado" el HUD del juego (Faltan los botones y las imagenes pero despues lo hago)
    - Creada una clase "EstiloFuente" que devuelve un LabelStyle para poder tener distintos estilos de fuente en las interfaces que haga, reemplaza la clase "Texto" y esta sera eliminada cuando todas las interfaces que hay hasta el momento dejen de usar esta clase
    - Esqueleto del menu principal
    - La pantalla configuraciones ahora esta bajo las fuerzas Table. ¡Larga vida Table!
    - Chau clase Texto! Cumpliste tu funcion, pero a la larga me ibas a dar problemas

## 27 AGO
    - Internacionalizacion y localizacion del juego (posibilidad de multiples lenguajes y variantes del mismo lenguaje por region).
    - Mira te explico, me di cuenta que es muy tedioso editar todos los archivos .properties para agregar los distintos idiomas ya que tenian que estar todas las keys iguales y era molesto copiar todo y ademas que coincida con la key que le puse en el codigo. Entonces hice un programita para que me escriba las keys automaticamente y solo tener que ponerle los valores nada mas, funciona pero deberia modificarlo un poco mas y ahora para usarlo tengo que modificarle la ruta en la que lee el archivo y volver a compilar, ademas que sobreescribe todo lo que ya haya escrito en el archivo. 
    Ademas lo hice ahora porque me parecio que cuando ya tenga los dialogos de todos los npc y lo que falte iba a ser mucho mas complicado.
    - Bueno ya termine el programa (masomenos me faltan pulir un poco) ahora puedo usarlo desde "comandos" y crear todos los .properties que quiera

## 9 SEP
    - Primer acercamiento a los npc
    - Un poco mas de Npcs

## 10 SEP
    - POR UN DEMONIO! ya puedo poner todos los npc que quiera, con animaciones y cada uno con su distinto set de dialogos. Hice un NPC manger para manegear a todos los npc. Sobre los dialogos: por ahora lo hice que los cargo en la clase Juego, tengo que hacer que se puedan traducir (en el proximo commit)
    - Ahora si, los dialogos estan traducidos

# 11 SEP
    - No es final pero le cambie el spritesheet del jugador por uno mas acorde

# 21 SEP
    - Bueno, estoy reestructurando los npcs y sus dialogos para usarlos con enum e interfaces
    - Ok la estructura creo que ya masomenos esta, tengo que cambiar el tema del NPCManager y hacer que cada npc diga sus mensajes de forma independiente (ahora le estoy pasando un indice y todos los npcs dan el mensaje de que este en ese indice)

# 22 SEP
    - Hice un retrato para el vendedor dentro del dialogo, voy a tener que cambiarle la ropa en el juego para que se vea como en el retrato
    - Cambie la forma en al que se pasan los retratos, ahora por npcData 

# 23 SEP
    - Empeze a hacer el tema de las combinaciones
    
# 30 SEP
    - OMG asi es, ya hice masomenos que se puede combinar (ponele). Dado un "inventario"(un ArrayList<Image>, lo resolvi asi para probar ahora, pero tengo que ver como es la mejor forma, ademas el "inventario" esta en la misma clase, la idea es sea traido desde el jugador) se muestran las imagenes sobre la pantalla y son todas arrastrables, por ahora, cuando son superpuestas dice que son validas con todas, tengo que ver como hacer para discriminar eso.
    - Hice masomenos la animacion que queria de respiracion del menu principal
    - Todo masomenos hoy...

# 1 OCT
    - Aplique un filtro al generador de fuentes, asi cuando se reescala la ventana, los textos no se ven tan mal
    - Algunos cambios al reescalado

# 8 OCT
    - Empezando a hacer una demo para el juego, estoy haciendo la carta aparece apenas empieza el juego

# 11 OCT
    - Estoy haciendo el boton de cerrar de la carta
    - Hice clase "HelpDebug" que tiene un metodo el cual te devuelve el nombre de la clase, lo hice porque en un momento estaba imprimiendo mensajes en la consola que no sabia de donde venian, es una boludez pero util! 

# 12 OCT
    - Hice unas eskines para los botones
    - no me andan los botones
    - Vamos a ver si se arreglo magicamente o no... SI || ACTUALIZACION -> El problema era el inputProcessor, faltaba un inputMultiplexer el cual se encarga de manejar a los distintos processors NO FUE MAGIA
    - OMG los HUDs se reescalan re piola, queda re bien (CartaHUD todavia no se puede reescalar bien)
    - Ahora se puede cerrar la carta del comienzo

# 13 OCT
    - CartaHUD ya se reescala correctamente (era una boludez, no estaba llamando al metodo para reescalar en el juego)
    - CartaHUD ahora se cierra completamente cuando es cerrado (se quitan los actores, el listener de eventos, etc)
    - InputMultiplexer yaajuuuu, ahora todo anda... TODO
    - Unos cambios a la interfaz Combinacion

# 15 OCT
    - El HUD principal tiene ahora las Labels clickeables
    - La base del funcionamiento de la label de batallas anteriores ya funciona
    - Cambie algunas clases a clases abstractas 

# 16 OCT
    - Modificando y agregando nuevos HUDs
    
# 28 OCT
    - Agregando Mineria

# 1 NOV
    - Cambios en mineria
    - Agrega Inventario

# 3 NOV
    - Cambios menores que me olvide de poner

# 4 NOV
    - Empezando el tema de red

# 12 NOV
    - Esto es todo lo que pude hacer de redes, queria hacer un commit grande con el tema ya mas que nada terminado pero no pude
    - Ahora el inventario del jugador se refleja en la pantalla de combinaciones
    - Dejando a medias el tema de la fundicion

# 13 NOV
    - Redes... la parte de que los dos clientes sean independientes entre si ya esta, varios dias de intentos y despues con la ayuda del profe. El problema era que tenia 3 hilos por cliente cuando solo deberia tener 1, entonces llegaba un punto en que era un re quilombo ahi adentro de la red nadie se entendia nada y bueno...
    - Ahora el hilo del cliente efectivamente se cierra cuando cerras el programa 

# 15 NOV
    - OMG LO LOGRE, los dos jugadores se pueden mover por el mundo libremente, la camara es especifica de cada uno y, recien empiezo con esto, pero por lo menos los minerales del inventario se muestran de manera independiente. Podria limpiar un poco el codigo de la clase Juego... 

# 16 NOV
    - Cambios menores

# 17 NOV
    - Los minerales ahora se manejan en red. Si un jugador mina un hierro, ese hierro desaparece para ambos clientes

# 18 NOV
    - Ahora hay animaciones cuando se juega en red
    - Ahora las animaciones en red funcionan correctamente

# 19 NOV
    - Colisiones omg
    - Agrege sprites para un segundo jugador asi es facil de diferenciar uno del otro

# 20 NOV
    - Modificando el comportamiento del servidor frente a la desconexion de un jugador
    - Puliendo la desconexion
    - Horno de fundicion
    - Dejando casi para entregar

# 27 NOV
    - El proyecto fue aprobado el 21 de noviembre, este commit es la version que fue aprobada.
    - Desde aca sigo el proyecto para terminarlo
    - Eliminada toda la parte de red (Para recuperar ver commit anterior)

# 28 NOV
    - Minimos cambios en el mapa
    - Nuevo NPC

# 5 DIC
    - BOX2D
    - BOX2D Lights
    - Sistema de dia y noche (masomenos)
    - Colisiones que vienen desde Tiled
    - El vendedor de tienda ahora tiene una colision coherente
    - Ahora la clase HelpMapa lee tambien rectangulos de Tiled. Puse un rectangulo "puntoAparicion" que es donde el jugador empieza (para modificarlo hay que cambiarlo desde Tiled). Ahora puedo discriminar por nombre de rectangulo, esto me va a servir mas adelante.

# 7 DIC
    - Cambios en el orden de renderizacion de las capas del mapa
    - Algunos cambios en colisiones
    - El sistema de minado ahora funciona correctamente!

# 17 DIC
    - AHORA MISIONES!

# 20 DIC
    - Recompensas para las misiones
    - Scrollpane que le da un toque como si fuese un pergamino largo que estas viendo (en el diarioHUD)
    
# 21 DIC
    - Modificada la clase del mapa, ahora lee los objetos de las tiles que vienen por cada capa (puedo poner los arboles y las colisiones se ponen automaticamente)
    - Arreglado un error que habia en la fundicion

# 31 DIC
    - Me olvide de escribir lo que hice dias anteriores, estoy cambiando el sistema de dialogo para que haya respuestas y distintas charlas con los npcs

# 2024

# 5 ENE
    - DIALOGOSSS HIPER FLEXIBLESSSS CON RESPUESTA!!!

# 7 ENE
    - Saque todo el debug feo del dialogo y lo reemplaze por un NinePatch (¿tendre que hacerlo TenPatch?)
    - Color para la label con el cursor encima