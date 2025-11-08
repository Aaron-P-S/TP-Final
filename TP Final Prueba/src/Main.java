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
        Enemigo goblin= new Enemigo("Goblin",0);
        goblin.setPuntosDeVidaMaxima(500);
        System.out.println(goblin);
        Enemigo jefeGoblin= new Enemigo("Goblin Jefe",1);
        System.out.println(jefeGoblin);
        Partida partida =new Partida();
        partida.agregarPersonajeJugable(personajeJugable);
        partida.agregarPersonajeJugable(personajeJugable1);
        partida.agregarEnemigo(goblin);
        partida.agregarEnemigo(jefeGoblin);
        Menu menu = new Menu(partida);
        menu.menu();

        //llamamos a las clases


    }
}