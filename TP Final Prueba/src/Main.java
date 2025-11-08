import Enumeradores.E_Clases;
import Modelo.Enemigo;
import Modelo.Menu;
import Modelo.Partida;
import Modelo.PersonajeJugable;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //Hardcodeos se toman del archivo json
        PersonajeJugable personajeJugable= new PersonajeJugable("Pedro", E_Clases.BARBARO);
        System.out.println(personajeJugable);
        PersonajeJugable personajeJugable1= new PersonajeJugable("Aaron",E_Clases.GUERRERO);
        System.out.println(personajeJugable1);
        PersonajeJugable personajeJugable2= new PersonajeJugable("A",E_Clases.ARQUERO);
        System.out.println(personajeJugable2);
        PersonajeJugable personajeJugable3= new PersonajeJugable("B",E_Clases.MAGO);
        System.out.println(personajeJugable3);
        Enemigo goblin= new Enemigo("Goblin",0);
        goblin.setPuntosDeVidaMaxima(500);
        System.out.println(goblin);
        Enemigo jefeGoblin= new Enemigo("Goblin Jefe",1);
        System.out.println(jefeGoblin);
        Enemigo enemigo= new Enemigo("Enemigo",2);
        enemigo.setPuntosDeVidaMaxima(1500);
        System.out.println(enemigo);
        Enemigo enemigo1= new Enemigo("Enemigo1",3);
        enemigo1.setPuntosDeVidaMaxima(2000);
        System.out.println(enemigo1);
        Enemigo enemigo2= new Enemigo("Enemigo2",4);
        System.out.println(enemigo2);
        jefeGoblin.setPuntosDeVidaMaxima(1000);
        enemigo.setPuntosDeVidaMaxima(1200);
        Partida partida =new Partida();
        partida.agregarPersonajeJugable(personajeJugable);
        partida.agregarPersonajeJugable(personajeJugable1);
        partida.agregarPersonajeJugable(personajeJugable2);
        partida.agregarPersonajeJugable(personajeJugable3);
        partida.agregarEnemigo(goblin);
        partida.agregarEnemigo(jefeGoblin);
        partida.agregarEnemigo(enemigo);
        partida.agregarEnemigo(enemigo1);
        partida.agregarEnemigo(enemigo2);
        Menu menu = new Menu(partida);
        menu.menu();

        //llamamos a las clases


    }
}