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