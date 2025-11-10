import Enumeradores.E_Clases;
import Enumeradores.E_TipoItem;
import Modelo.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //Hardcodeos se toman del archivo json
        PersonajeJugable personajeJugable= new PersonajeJugable("Pedro", E_Clases.BARBARO);
        System.out.println(personajeJugable);
        PersonajeJugable personajeJugable1= new PersonajeJugable("Aaron",E_Clases.GUERRERO);
        System.out.println(personajeJugable1);
        PersonajeJugable personajeJugable2= new PersonajeJugable("Jose",E_Clases.MAGO);
        PersonajeJugable personajeJugable3 = new PersonajeJugable("Juan",E_Clases.ARQUERO);
        Enemigo goblin= new Enemigo("Goblin",0);
        goblin.setPuntosDeVidaMaxima(500);
        System.out.println(goblin);
        Enemigo jefeGoblin= new Enemigo("Goblin Jefe",1);
        jefeGoblin.setPuntosDeVidaMaxima(1000);
        System.out.println(jefeGoblin);
        Enemigo goblin2 = new Enemigo("Goblin 2",2);
        goblin2.setPuntosDeVidaMaxima(750);
        System.out.println(goblin2);
        Enemigo goblin3 = new Enemigo("Goblin 3",3);
        goblin3.setPuntosDeVidaMaxima(1250);
        System.out.println(goblin3);





        //inventarios pjs
        Item itemPJ1 = new Item("Pocion de Vida", "Cura una cantidad de vida actual especifica",10, 0, true, 50, E_TipoItem.PUNTOSDEVIDA);
        Item itemPJ2 = new Item("Espada Larga", "Una gran mandoble que aumenta tu daño", 20, 0, false, 60,E_TipoItem.PUNTOSDEATAQUE);

        personajeJugable.agregarInventario("Pocion de Vida", itemPJ1);
        personajeJugable.agregarInventario("Espada Larga", itemPJ2);

        personajeJugable1.agregarInventario("Pocion de Vida", itemPJ1);
        personajeJugable1.agregarInventario("Espada Larga", itemPJ2);

        personajeJugable2.agregarInventario("Pocion de Vida", itemPJ2);
        personajeJugable2.agregarInventario("Espada Larga", itemPJ2);

        personajeJugable3.agregarInventario("Pocion de Vida", itemPJ2);
        personajeJugable3.agregarInventario("Espada Larga", itemPJ2);
        //tienda
        Inventario inventarioTienda = new Inventario();
        Item item1 = new Item("Pocion de Vida", "Cura una cantidad de vida actual especifica",10, 2, true, 50,E_TipoItem.PUNTOSDEVIDA);

        inventarioTienda.agregarItem("Pocion de Vida",item1);
        Item item2 = new Item("Espada Larga", "Una gran mandoble que aumenta tu daño", 200, 2, false, 60,E_TipoItem.PUNTOSDEATAQUE);
        inventarioTienda.agregarItem("Espada Larga",item2);

        //llamamos a las clases
        Partida partida =new Partida();
        partida.agregarTienda(inventarioTienda);
        partida.agregarPersonajeJugable(personajeJugable);
        partida.agregarPersonajeJugable(personajeJugable1);
        partida.agregarPersonajeJugable(personajeJugable2);
        partida.agregarPersonajeJugable(personajeJugable3);
        partida.agregarEnemigo(goblin);
        partida.agregarEnemigo(jefeGoblin);
        partida.agregarEnemigo(goblin2);
        partida.agregarEnemigo(goblin3);

        Menu menu = new Menu(partida);
        menu.menu();

    }
}