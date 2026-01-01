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

# 9 ENE (no tengo internet, esto se va a subir despues)
    - Cambie el debug del hud de pausa por un 9patch mas acorde
    - Arrgle un error que habia al volver a entrar al juego luego salir de el mediante el hud de pausa 

# 3 FEB pequeño descanso...
    - Nueva parte del mapa: interior de la montaña (falta mapa en el medio pero bue)
    - Cambios en el posicionamiento de los objetos
    - Agregados objetos interatuables en el taller

# 5 FEB
    - Preparando la interaccion con los objetos del taller

# 7 FEB

    - ¡Arregla el bug milenario!

    - Agrega el area de interaccion para los objetos del taller
    - Agrega area de interaccion para el jugador
    - Agrega algunos HUDS para los objetos del taller
    - Agrega un boton para silenciar la musica en el menu

# 9 FEB
Segunda vez que escribo esto, habia avanzado un monton pero me equivoque con algo de git y volvi al ultimo commit, espero no haber perdido nada

    - Agrega una clase que genera un informe de fallos
    - Agrega el panel de combinacion al hud del yunque
    - Modifica un poco las combinaciones

# 10 FEB

    - Agrega carbon
    - Agrega un nuevo tipo de hierro
    - agrega Maza y Cincel
    - Modifica las combinaciones, ahora herramientas y minerales se pueden combinar
    - Modificaciones en las clases hijas de Mineral
    - Modificaciones en la fundicion
    - Agrega enum de estados del mineral para mayor precision

# 19 FEB
    - Camino a la beta
    - Cambios menores en el mapa e iluminacion

# 20 FEB
    - Cambios en la colision del jugador
    - Cambios en las colisiones del mapa
    - Auto Sorting para los sprites

# 23 FEB
    - Cambios en el Auto Sorting
    - Auto Sorting para los npcs 

    Ahora con el autosorting tengo que acordarme que la clase OrganizadorSpritesIndiceZ es la unica que puede dibujar sobre el mapa

# 23 MAR ;(
    - Agrega nuevos assets para el mapa
    - Ahora se ve el nombre del item o mineral que se le ponga encima el cursor en las combinaciones
    - Agrega codigo para hacer pilas de minerales (no se esta usando)

# 24 ABR 
    bueno volvio a pasar mucho tiempo pero que se le va a hacer
    - Agrega la casa del carpintero
    - Agrega al carpintero
    - Modifica el sistema de las misiones
    - Agrega la primera mision de fabricar

# 27 ABR
    de ayer y hoy
    - Cambia un poco la forma de detectar las teclas que presiona y la logica para mostrar el inventario, etc.
    - Achica un poco el HUD general, reemplazando las tablas de la batalla por un "libro" que muestra las batallas.
    - Agrega una clase UI, la cual quiero usar como manager de todos los huds. De apoco voy modificando eso. Todos los huds los va a manejar esta clase
    - Agrega clase Item para poder crear las herramientas y objetos de mision mas facil y sin tener que hacerlos hijas de la clase mineral o lo que sea
    - Agrega la posibilidad de herramientas que se rompan con cierta cantidad de usos
    - Agrega algunas combinaciones para poder completar la primera mision

# 29 ABR
    - Ya se puede crear la sierra circular, y hacer combinaciones item con item
    - Se completa la mision cuando se crea la sierra

# 30 ABR
    - Mas dialogos para el carpintero, ahora se puede entregar la sierra y ya esta listo el dialogo general de venta

# 4 MAY
    - Progreso en el sistema de venta
    - Un cambio boludo en la clase Item

# 9 MAY
    - Mudando todo lo que tenga que ver con hud para la clase Ui

# 12 MAY
    - Los dialogos ahora funcionan correctamente con respuestas y todo.
    - Arregla un error en los dialgos
    - el hud venta ya se abre correctamente por medio del dialogo y se cierra con el boton.

# 17 MAY
    - Arregla unos errores menores

# 8 JUN
    - Arregla problemas con el dragAndDrop
    - Arregla problemas con los hud
    - Modifica el sistema de la carta incial
    - Cambios menores que me olvide de ir poniendo aca

# 9 JUN
    - Agrega dos items
     
# 7 SEP
    - Arregla problema con la combinacion y el diario de misiones

# 19 SEP
    - Agrega clase Tiempo. Se usa para medir el tiempo de sesion y el tiempo dentro del juego (gameplay).
    - Aprovecha clase Tiempo para corregir el sistema de dias, horas y minutos introducido hace un monton. ¡Ahora el tiempo dentro del juego funciona bien!
    - Modificaciones en el reloj del hud (aun no anda)

# 20 SEP
    - ¡El reloj funciona! (eso es basicamente todo lo de hoy (esta mier me llevo horas))
    - Agrego base para eventos (no se porque no los estuve usando todo este tiempo)
    - Cambios en las interacciones tanto para NPCS y Objetos del mapa
    - Ahora hay una carta fisica que aparece en el mapa la cual muestra el hud de la carta a leer
    
# 25 SEP
    - Cambios en la carta fisica
    - La carta ahora muestra un icono y luz si no fue leida.
    - La carta de inicio NO es parte de las cartas y el estado del juego es INICIO

# 26 SEP
    - Ahora las cartas pueden dar misiones

# 28 SEP
    - Muestra mensaje al recibir misiones
    - Reproduce sonido al recibir misiones

# 21 NOV
    - Arregla un error con el autosorting que no se porque surgio 
    - cambia el inventario de minerales, ahora uso EnumMap. Esto me obliga a cambiar muchas cosas del codigo.
#### ¡Importante!
Cambio en las rutas de los minerales, la estructura es la siguiente:
 ```
├── assets
│   ├── objetosDelMundo
│   │   ├── inanimados
│   │   │   ├── minerales
│   │   │   │   ├── carbon
│   │   │   │   │  ├── carbonMena.png
│   │   │   │   │  ├── carbonPuro.png
│   │   │   │   │  ├── ...
│   │   │   │   ├── hierro
│   │   │   │   │  ├── hierroMena.png
│   │   │   │   │  ├── hierroPuro.png
│   │   │   │   │  ├── hierroLingote.png
│   │   │   │   │  ├── ...
│   │   │   │   ├── piedra
│   │   │   │   │  ├── piedraMena.png 
│   │   │   │   │  ├── ... 
│   │   │   │   ├── ... 
```

Esta estructura es muy importante primero por que organiza mejor las cosas y segundo para los enum.
El enum ```TipoMineral``` ahora tiene un pedazo de la ruta y ```EstadosMinerales``` tiene otro, esto me componer las texturas entonces simplemente con el enum puedo hacer:
```java
TipoMinerales.HIERRO.ruta + EstadosMinerales.PURO.ruta; //Me construye la ruta completa
```

#### Importante
Los nombres de las imagenes tienen que tener SI o SI como ultima palabra el estado en el que estan, si es la imagen del lingote de hierro la ultima palabra del nombre del archivo tiene que ser Lingote.png (con mayuscula la primera letra)
ademas de que el nombre tienen que ser el ```tipo``` + ```estado```
por ejemplo:
>    hierroMena.png
    carbonPuro.png
    ...

# 25 DIC
    - Arregla bug en combinaciones que surgio del enumMap
    - Prepara para cambio de tamaño en el spritesheet del jugador
    - el jugador ahora puede llevar un item en la mano

# 27 DIC
    - Ahora se como funciona el Inputprocessor y lo estoy usando bien despues de mas de un año
    - Mejora en minado de minerales
    - Ahora los minerales se agregan de manera aleatoria en un sitio de minado predefinido

# 2025

# 13 ENE
    - Se pueden guardar cambios de configuracion con "Preferences"
    - Empiezo cambios en pantalla configuracion

# 16 ENE
    - Agrega configuraciones que persisten al cerrar y abrir el juego

# 24 ENE
    - agrega configuraciones
    - Agrega Configuracion para cambiar el idioma

# 27 ENE
    - Optimiza constructor de minerales

# 14 FEB
    - Agrega algunos items
    - Cambios en el sistema de dinero
    - Funciona la compra de items
    - Ahora se puede completar la primera mision, fabricar espadas

# 20 FEB
    - Agrega tiempo limite para completar misiones
    - Agrega descripcion para las misiones

# 21 FEB
    - Cambios en el sistema de misiones

# 22 FEB
    - Mas cambios a las misiones
    - Agrega dialogos

# 24 FEB
    - Modifica estructura de misiones    

# DICIEMBRE 2025
    (me olvide de escribir aca)
    - Modifica la fundicion
    - Modifica la estructura de los asstes y la forma de acceder a ellos

# 2026

# 1 ENE
    - Arregla unos problemas con la fundicion
    - Arregla unos problemas con las combinaciones