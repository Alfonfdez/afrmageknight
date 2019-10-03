# Proyecto final Android - Mage Knight Dummy Player app
# Curso M1019 - Desarrollo de apps para Android

## 1.	Descripción del proyecto

Proyecto desarrollado para Android consistente en una aplicación capaz de gestionar un Jugador Virtual (*Dummy Player*) en una partida al juego de mesa *Mage Knight Edición Definitiva*.

La *app* creará una base de datos (BBDD) utilizando la librería SQLite e insertará toda la información necesaria del juego (cartas, fichas, etc.). A partir de esta BBDD se gestionará la partida, proporcionando una ayuda esencial para el jugador que quiera disfrutar *Mage Knight* en modo Solitario o los jugadores que se propongan jugar en modo Cooperativo.

Debido a la constante actualización de la información de la partida en las tablas correspondientes de la *database*, el jugador o jugadores podrán cerrar la aplicación y continuar su partida en cualquier momento otro momento sin miedo a perder sus datos.

Este proyecto realizará las siguientes acciones resumidas a continuación:

-	A partir de la selección del usuario, o usuarios, del tipo de partida a jugar (Solitario o Cooperativo) y del héroe, o héroes seleccionados (los 7 héroes disponibles: Arythea, Tovak, Norowas, Goldyx, Wolfhawk, Krang y Braevalar), el Jugador Virtual escogerá un héroe disponible al azar.

-	El héroe del Jugador Virtual dispondrá de inicio 3 cristales y 16 cartas en su mazo.

-	El juego dispondrá de 6 Rondas en total (3 de Día y 3 de Noche) y en cada una de éstas se irán desarrollando turnos donde el Dummy Player irá consumiendo su mazo de cartas, de manera aleatoria, hasta terminarlo. 

-	Al inicio de cada Ronda la aplicación de encargará de escoger una Táctica al azar.

-	Al final de cada Ronda se añadirá una carta y un cristal a su Inventario. Estos cristales acelerarán el proceso de descarte de cartas.

-	Esta gestión de la app ayudará al jugador humano a tener un tempo aleatorio de la partida que hará que pueda jugar en aquellos escenarios donde no se enfrente a otras personas.


## 2.	Descripción objetiva
### 2a. Objetivos alcanzados

Se ha logrado obtener un programa que permite a los jugadores poder enfocarse en su propia partida sin tener que molestarse a gestionar un Jugador Virtual de manera física.

El programa desarrolla todos los pasos de principio a fin, hasta la finalización de la partida a Mage Knight.

### 2b. Objetivos no alcanzados

Las siguientes versiones de la aplicación deberán tener ofrecer la posibilidad de multilenguaje, incluyendo el idioma inglés (aparte del castellano ya incluido en la versión actual).

Además ofrecer una guía auxiliar con toda la información de todas las cartas y fichas del juego de mesa, que ya se encuentra en la propia base de datos.
