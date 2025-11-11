import Enumeradores.E_Clases;
import Enumeradores.E_TipoItem;
import GestoraJson.GestorJson;
import JsonUtiles.JsonUtiles;
import Modelo.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

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
        Enemigo goblin= new Enemigo("Pequeño Goblin",0);
        goblin.setPuntosDeVidaMaxima(500);
        System.out.println(goblin);
        Enemigo jefeGoblin= new Enemigo("Goblin",1);
        jefeGoblin.setPuntosDeVidaMaxima(1000);
        System.out.println(jefeGoblin);
        Enemigo goblin2 = new Enemigo("Goblin Luchador",2);
        goblin2.setPuntosDeVidaMaxima(2000);
        System.out.println(goblin2);
        Enemigo goblin3 = new Enemigo("Goblin Jefe",3);
        goblin3.setPuntosDeVidaMaxima(2500);
        System.out.println(goblin3);
        Enemigo enemigo = new Enemigo("Goblin Caudillo",4);
        enemigo.setPuntosDeVidaMaxima(3000);
        System.out.println(enemigo);






        //inventarios pjs
        Item itemPJ1 = new Item("Pocion de Vida", "Cura una cantidad de vida actual especifica",10, 1, true, 50, E_TipoItem.PUNTOSDEVIDA);
        Item itemPJ2 = new Item("Espada Larga", "Una gran mandoble que aumenta tu daño", 20, 1, false, 60,E_TipoItem.PUNTOSDEATAQUE);
        Item itemPJ3 = new Item("Arco Largo", "Un gran arco que aumenta la potencia de tus flechas", 300, 1, false, 0, E_TipoItem.PUNTOSDEATAQUE);
        Item itemPJ4 = new Item("Maza de Bridas", "Gran maza con puntas recurvada que te cura 20 por cada ataque", 500, 1, false, 0, E_TipoItem.PUNTOSDEATAQUE);
        Item itemPJ5 = new Item("Baculo de Toth", "Un poderoso baculo capaz de destruir a sus enemigos", 1000, 1, false, 0, E_TipoItem.PUNTOSDEATAQUE);
        Item itemPJ6 = new Item("Pocion de Revivir", "Una increible pocion que tiene la capacidad de traer del mundo de los muertos a su portador", 600, 1, true, 0, E_TipoItem.REVIVIR);

        personajeJugable.agregarInventario("Pocion de Vida", itemPJ1);
        personajeJugable.agregarInventario("Espada Larga", itemPJ2);
        personajeJugable.agregarInventario("Arco Largo", itemPJ3);
        personajeJugable.agregarInventario("Maza de Bridas", itemPJ4);
        personajeJugable.agregarInventario("Baculo de Toth", itemPJ5);
        personajeJugable.agregarInventario("Pocion de Revivir", itemPJ6);

        personajeJugable1.agregarInventario("Pocion de Vida", itemPJ1);
        personajeJugable1.agregarInventario("Espada Larga", itemPJ2);
        personajeJugable1.agregarInventario("Arco Largo", itemPJ3);
        personajeJugable1.agregarInventario("Maza de Bridas", itemPJ4);
        personajeJugable1.agregarInventario("Baculo de Toth", itemPJ5);
        personajeJugable1.agregarInventario("Pocion de Revivir", itemPJ6);


        personajeJugable2.agregarInventario("Pocion de Vida", itemPJ1);
        personajeJugable2.agregarInventario("Espada Larga", itemPJ2);
        personajeJugable2.agregarInventario("Arco Largo", itemPJ3);
        personajeJugable2.agregarInventario("Maza de Bridas", itemPJ4);
        personajeJugable2.agregarInventario("Baculo de Toth", itemPJ5);
        personajeJugable2.agregarInventario("Pocion de Revivir", itemPJ6);


        personajeJugable3.agregarInventario("Pocion de Vida", itemPJ1);
        personajeJugable3.agregarInventario("Espada Larga", itemPJ2);
        personajeJugable3.agregarInventario("Arco Largo", itemPJ3);
        personajeJugable3.agregarInventario("Maza de Bridas", itemPJ4);
        personajeJugable3.agregarInventario("Baculo de Toth", itemPJ5);
        personajeJugable3.agregarInventario("Pocion de Revivir", itemPJ6);

        //tienda
        Inventario inventarioTienda = new Inventario();
        Item item1 = new Item("Pocion de Vida", "Cura una cantidad de vida actual especifica",10, 2, true, 50,E_TipoItem.PUNTOSDEVIDA);

        inventarioTienda.agregarItem("Pocion de Vida",item1);
        Item item2 = new Item("Espada Larga", "Una gran mandoble que aumenta tu daño", 200, 2, false, 0,E_TipoItem.PUNTOSDEATAQUE);
        inventarioTienda.agregarItem("Espada Larga",item2);
        Item item3 = new Item("Arco Largo", "Un gran arco que aumenta la potencia de tus flechas", 300, 1, false, 0, E_TipoItem.PUNTOSDEATAQUE);
        inventarioTienda.agregarItem("Arco Largo",item3);
        Item item4 = new Item("Maza de Bridas", "Gran maza con puntas recurvada que te cura 20 por cada ataque", 500, 1, false, 0, E_TipoItem.PUNTOSDEATAQUE);
        inventarioTienda.agregarItem("Maza de Bridas",item4);
        Item item5 = new Item("Baculo de Toth", "Un poderoso baculo capaz de destruir a sus enemigos", 1000, 1, false, 0, E_TipoItem.PUNTOSDEATAQUE);
        inventarioTienda.agregarItem("Baculo de Toth", item5);
        Item item6 = new Item("Pocion de Revivir", "Una increible pocion que tiene la capacidad de traer del mundo de los muertos a su portador", 600, 1, true, 0, E_TipoItem.REVIVIR);
        inventarioTienda.agregarItem("Pocion de Revivir", item6);


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

        try{
            GestorJson gestorJson= new GestorJson();
            JSONArray todosAJson=gestorJson.pasarDeArrayAJson(partida.getEnemigos(),partida.getParty(),partida.getInventarioTienda());
            JsonUtiles.grabarUnJson(todosAJson,"Todos");
            JSONArray jsonArray= new JSONArray(JsonUtiles.leerUnJson("Todos"));
            System.out.println(gestorJson.pasarDeJsonAListaEnemigos(jsonArray));
            System.out.println(gestorJson.pasarDeJsonAParty(jsonArray));
            System.out.println();
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}