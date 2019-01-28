# Arkanoid
Arkanoid **V1.15.0**

***
*Clasico Juego Arkanoid.*

Consiste en rompero todos lo bloque sin que la bola caiga al vacio.
 
El objetivo del proyecto es la puesta en practica de los conocimientos aprendidos en actividades anteriores
desarrolladas en la 

FP.Superior **Desarrollo De aplicaciones Multiplataforma.**
***

### News

### **V1.15.0**

Implementados los sonidos del juego.

### **V1.5.0**

Implementación radianes para el calculo de las direcciones y el control de la bola

![](http://centros5.pntic.mec.es/ies.de.melilla/img_2/trrig_graf_02.gif)

    private void checkCollision() {
         if (ball.colisionan(bar)) {
             initSound(loadMedia.getSoundFileBar());
             soundClip.start();
             double centerDistance = (ball.getPosX() + ball.getAncho() / 2d) - (bar.getPosX() + LoadMedia.WIDTH_BAR_SHIP / 2d);
             double impactCof = centerDistance / (LoadMedia.WIDTH_BAR_SHIP / 2d);
             double vT = ball.getTotalSpeed();
             double maxAngle = Math.toRadians(60);
             double angle = Math.PI / 2 - maxAngle * Math.abs(impactCof);
             double newVX = vT * Math.cos(angle) * ((centerDistance > 0) ? 1 : -1);
             double newVY = vT * Math.sin(angle);
             ball.setVelocidadX(newVX);
             ball.setVelocidadY(-newVY);
         }
     }

***


### Change Log
**v1.15.0**

* -Implementados los sonidos del juego.

  -Rellenado el README, posibles modificaciones futuras.

  -Cambio de formato con el audio
  
  -Refactorizado superficial.

  -Subida a produccion

**v1.10.0**
* Parcheado el cheque de la barra con las paredes.
  Suavizado el aumento de velocidad y la dificultad.
  Falta:
  
  -Terminar de pulir el segundo nivel.
  
  -Sonido y video(No tengo muchas esperanzas u.u).
  
  -Añadir contador de vidas, con sus respetivas imagenes y contador(back).

**v1.5.0**
* Rediseñada la pantalla iniacial,
  centrado todos los texto tanto de la pantalla de inicio como la cabecera

* Implementado el tope de la barra, tiene bug a resolver.

* Arreglos en la Clase GameScreen.
  Ajuste de todos los bloques al nuevo margen.
  Añadida la nueva fuente de texto.
  Refactorizado parte del codigo

* Implementado funcionalidad con el teclado.
  Arreglo del background del ScoreHeader.

* Se añanden nuevos recursos

* Implementado el algoritmo para el calculo del angulo del rebote de la bola, mejora de la colision con los bloques.

**v.1.1.0**
* Añadidos los bloques y las colisiones

* Se ha adaptado la clase LoadImage a el resto del programa,
  Falta adaptar los sprites para que el collider no pille las sombras como colision

* Arreglos en la integraciónd e la clase LoadImage

* Terminada la clase para cargas las imagenes

* Inicializa la carga del sprite principal con la imagen,
  tengo posiciones y tamaños de bloques bolas, y nave.
  Falta rellenar los metodos.

**v1.0.0**

* Proyecto volcado
* Preparativos previos al subida del proyecto al repositorio



