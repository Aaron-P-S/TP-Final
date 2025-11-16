package UI;

import Enumeradores.E_Clases;
import Excepciones.NumeroNoValidoException;
import Excepciones.PartidaGanadaException;
import Excepciones.TodosLosMiembrosMuertosException;
import GestoraJson.GestorJson;
import JsonUtiles.JsonUtiles;
import Modelo.*;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    //Coleccion donde guardamos las diferentes partidas
    //cambiar a private
    //flag game over cuando se pierde la partida para no poder volver a continuar
    public Partida partida;
    public int nivel = 0;

    public Menu(Partida partida) {
        this.partida = partida;
    }

    public Menu() {

    }

    //Metodos para ejecutar el juego
    public void menu() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        int eleccion = 0;
        boolean winCon = false; //ponerlo en partida
        System.out.println("Ingrese 0 para iniciar una nueva partida, ingrese otro numero para cargar partida");
        int eleccionPartida;
        try {
            eleccionPartida = sc.nextInt();
            sc.nextLine();
            cargarpartida(eleccionPartida);
        } catch (InputMismatchException e) {
            System.out.println("Ingrese un numero para cargar partida");
            sc.nextLine();
            continuar = false;

        } catch (PartidaGanadaException e) {
            System.out.println(e.getMessage());
            continuar = false;

        }

        while (continuar) {
            switch (eleccion) {
                case 0:
                    System.out.println("""
                            Bienvenido al juego ...
                            Ingrese un numero para continuar:
                            1. Empezar pelea
                            2. Visitar la Plaza de los Gremios
                            3. Mostrar Party
                            4. Salir
                            """);
                    try {
                        eleccion = sc.nextInt();
                        sc.nextLine();
                        if (eleccion < 0) {
                            throw new NumeroNoValidoException("Se ingreso un numero negativo");
                        }
                        if (eleccion > 4) throw new NumeroNoValidoException("Se ingreso un numero no valido");
                    } catch (InputMismatchException e) {
                        System.out.println("No se ingreso un numero, intentelo de nuevo");
                        eleccion = 0;
                        sc.nextLine();
                    } catch (NumeroNoValidoException e) {
                        System.out.println(e.getMessage());
                        eleccion = 0;

                    }
                    break;
                case 1:
                    try {
                        if (combate()) {
                            System.out.println("Felicidades, a ganado el combate numero " + (nivel + 1));
                            if (nivel + 1 == partida.getEnemigos().size()) {
                                System.out.println("Felicitaciones a ganado el juego");
                                winCon = true;
                                continuar = false;
                                break;
                            }
                            int cantidadDeOroRecompensa = partida.recompensa(nivel);
                            System.out.println("Por derrotar al jefe se le otorgara una recompensa de " + cantidadDeOroRecompensa + " de oro");

                        } else {
                            throw new TodosLosMiembrosMuertosException("☠️ Han muerto todos los miembros de la party ☠️" +
                                    "GAME OVER");
                        }
                        nivel++;
                        if (nivel % 3 == 0) {
                            System.out.println("Felicitaciones! Tu party a subido de nivel");
                            partida.getParty().get(0).subirDeNivel(nivel);
                            partida.getParty().get(1).subirDeNivel(nivel);
                            partida.getParty().get(2).subirDeNivel(nivel);
                            partida.getParty().get(3).subirDeNivel(nivel);
                        }
                    } catch (TodosLosMiembrosMuertosException e) {
                        System.out.println(e.getMessage());
                        eleccion = 3;
                        break;
                    }
                    eleccion = 0;
                    break;
                case 2:
                    /// menu tienda
                    tienda();
                    break;
                case 3:
                    System.out.println(partida.mostrarParty());
                    eleccion = 0;
                    break;
                case 4:
                    System.out.println("Guardando y saliendo de la partida");
                    if (guardarPartida()) {
                        System.out.println("La partida se a guardado exitosamente");
                    } else {
                        System.out.println("Hubo un problema al guardar la partida");
                    }
                    continuar = false;
                    break;
            }

        }
    }

    public boolean combate() {
        int eleccionCombate = 0;
        int turno = 0;
        Scanner sc = new Scanner(System.in);
        while (partida.estadoParty() && partida.getEnemigos().get(nivel).isVivoOMuerto()) {
            switch (eleccionCombate) {
                case 0:
                    if (turno + 1 > partida.getParty().size()) {
                        int eleccionEnemigo = 0;
                        do {
                            int numeroRandom = (int) (Math.random() * 101);

                            if (numeroRandom < 25) {
                                eleccionEnemigo = 0;
                            } else if (numeroRandom < 50) {
                                eleccionEnemigo = 1;
                            } else if (numeroRandom < 75) {
                                eleccionEnemigo = 2;
                            } else if (numeroRandom < 100) {
                                eleccionEnemigo = 3;
                            }
                        } while (!partida.getParty().get(eleccionEnemigo).isVivoOMuerto());
                        int danoHecho = partida.getEnemigos().get(nivel).atacar(partida.getParty().get(eleccionEnemigo), nivel);
                        System.out.println("+-----------------------⚔️---------------------------+");
                        System.out.println(partida.getEnemigos().get(nivel).getNombre() + " ataco a " + partida.getParty().get(eleccionEnemigo).getNombre() + " por " + danoHecho + " de dano ☄️");
                        System.out.println("+----------------------------------------------------+");
                        System.out.println("La vida actual de " + partida.getParty().get(eleccionEnemigo).getNombre() + " es " + partida.getParty().get(eleccionEnemigo).getPuntosDeVidaActual() + " ❤️");
                        turno = 0;
                    }
                    System.out.println("+-👾👾👾------------------------------------------------+");
                    System.out.println(partida.vidaParty(nivel));
                    System.out.println("+--------------------------------------------------------+");
                    System.out.println("Es el turno de " + partida.getParty().get(turno).getNombre());
                    System.out.println("""
                            Ingrese un numero segun la accion que quiera realizar en el combate:
                            | 1. atacar |  | 2. Inventario  |
                            """);
                    try {
                        eleccionCombate = sc.nextInt();
                        if (eleccionCombate < 0 || eleccionCombate > 2) {
                            throw new NumeroNoValidoException("Se ingreso un numero no valido");
                        }
                        sc.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("⭕ No se ingreso un numero, intentelo de nuevo ⭕");
                        sc.nextLine();
                        eleccionCombate = 0;
                        break;
                    } catch (NumeroNoValidoException e) {
                        System.out.println(e.getMessage());
                        sc.nextLine();
                        eleccionCombate = 0;
                        break;
                    }
                    break;
                case 1:
                    //atacar

                    if (!partida.getParty().get(turno).isVivoOMuerto()) {
                        System.out.println("+-----------------------☠️---------------------------+");
                        System.out.println(partida.getParty().get(turno).getNombre() + " esta muerto, se salteara su turno ");
                    } else {
                        int valorAtaque = partida.getParty().get(turno).atacar(partida.getEnemigos().get(nivel));
                        System.out.println(partida.getParty().get(turno).getNombre() + " atacara por " + valorAtaque + "☄️");
                        System.out.println(partida.getEnemigos().get(nivel).getNombre() + " tiene " + partida.getEnemigos().get(nivel).getPuntosDeVidaActual() + " ❤️ puntos de vida restantes ️");

                    }
                    turno++;
                    //eleccionTurnoEnemigo();

                    eleccionCombate = 0;
                    break;
                case 2:
                    System.out.println(partida.vidaParty(nivel));
                    System.out.println("+----------------------------------------------------+");
                    System.out.println("Ingrese un numero segun el item que quiera utilizar");
                    System.out.println("1-> Pocion de vida " + "2-> Pocion de Resurreccion " + "3-> Pocion de dano");
                    try {
                        switch (sc.nextInt()) {
                            case 1:
                                if (partida.getParty().get(turno).getInventario().usarItem(partida.getParty().get(turno).getInventario().getInventario().get("Pocion de Vida"), partida.getParty().get(turno))) {
                                    turno++;
                                } else {
                                    System.out.println();
                                }
                                break;
                            case 2:
                                if (partida.getParty().get(turno).getInventario().usarItem(partida.getParty().get(turno).getInventario().getInventario().get("Pocion de Revivir"), partida.getParty().get(turno))) {
                                    turno++; //salta index out of bound cuando es el ultimo turno en el menu de combate
                                } else {
                                    System.out.println("El personaje que se quiere revivir esta vivo");
                                }
                                break;
                            case 3:
                                System.out.println("Trabajo en proceso");
                                break;
                            default:
                                throw new NumeroNoValidoException("⭕ Se escogio un numero no valido, intentelo de nuevo ⭕");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("No se ingreso un numero valido");
                        sc.nextLine();
                        break;
                    } catch (NumeroNoValidoException e) {
                        System.out.println(e.getMessage());
                        sc.nextLine();
                        break;
                    }
                    eleccionCombate = 0;
                    break;
                default:
                    throw new NumeroNoValidoException("No se ingreso un numero valido");
            }
        }
        guardarPartida();
        return partida.estadoParty();
    }

    public void usarHabilidad(Enemigo enemigo) {
        if (partida.getParty().get(nivel).getClases().equals(E_Clases.GUERRERO)) {
            partida.getParty().get(nivel).atacar(enemigo);
        } else if (partida.getParty().get(nivel).getClases().equals(E_Clases.MAGO)) {
            for (PersonajeJugable p : partida.getParty()) {
                p.setPuntosDeVidaActual(25);
            }
        } else if (partida.getParty().get(nivel).getClases().equals(E_Clases.ARQUERO)) {
            partida.setDineroDisponible(partida.getDineroDisponible() + ((int) (25 * (1 + 0.25 * nivel))));
        } else if (partida.getParty().get(nivel).getClases().equals(E_Clases.BARBARO)) {
//inmortalidad por dos turnos
        }
    }

    public void eleccionTurnoEnemigo() {

    }

    public boolean guardarPartida() {
        try {
            GestorJson gestorJson = new GestorJson();
            JSONArray todosAJson = gestorJson.pasarDeArrayAJson(partida.getEnemigos(), partida.getParty(), partida.getInventarioTienda(), partida.getDineroDisponible(), getNivel());
            JsonUtiles.grabarUnJson(todosAJson, "Juego1");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public boolean cargarpartida(int eleccion) {
        try {
            JSONArray jsonArray = null;
            if (eleccion == 0) {
                jsonArray = new JSONArray(JsonUtiles.leerUnJson("Todos"));
            } else {
                jsonArray = new JSONArray(JsonUtiles.leerUnJson("Juego1"));
            }
            GestorJson gestorJson = new GestorJson();
            ArrayList<Enemigo> enemigosArray = gestorJson.pasarDeJsonAListaEnemigos(jsonArray);
            ArrayList<PersonajeJugable> personajesArray = gestorJson.pasarDeJsonAParty(jsonArray);
            GestorGenerico<PersonajeJugable> personajesGestorGenerico = new GestorGenerico<>();
            for (int i = 0; i < personajesArray.size(); i++) {
                personajesGestorGenerico.agregar(personajesArray.get(i));
            }
            GestorGenerico<Enemigo> enemigosGenerico = new GestorGenerico<>();
            for (Enemigo e : enemigosArray) {
                enemigosGenerico.agregar(e);
            }
            Inventario inventariotienda = gestorJson.pasarDeJsonAInventario(jsonArray);
            int dinero = gestorJson.pasarDeJsonAdinero(jsonArray);
            setNivel(gestorJson.pasarDeJsonAnivel(jsonArray));
            this.partida = new Partida(personajesGestorGenerico, enemigosGenerico, inventariotienda, dinero);
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        if (nivel < 4) {
            this.nivel = nivel;
        } else {
            throw new PartidaGanadaException("La partida a la que se quizo acceder ya esta ganada");
        }
    }

    public void tienda() {
        String nombreObjeto = "";
        int flag = 0;
        int numeroDePj = 0;
        boolean seleccionValida = false;
        boolean seleccionValida2 = false;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("+-🛍️🛍️🛍️------------------------------------------------+");
            System.out.println("Bienvenido a la Plaza de los Gremios!");
            System.out.println("+----------------------------------------------------+");
            System.out.println("A que tienda quieres ir?");
            System.out.println("1- ❤️‍🩹 La tienda del maestro Emma | 2- ⚔️ La Forja de los Nueve Nicos ");
            do {
                try {
                    switch (sc.nextInt()) {
                        case 1:
                            seleccionValida = true;
                            System.out.println("Ingrese quien desea entrar a la tienda, pues solo de a uno se debe de entrar...");
                            System.out.println(partida.mostrarNombres());

                            sc.nextLine();
                            numeroDePj = eleccionPersonaje();

                            System.out.println(" °º¤ø,¸Bienvenido a La tienda del maestro Emma¸,ø¤º° ");
                            System.out.println("( ͡° ͜ʖ ͡°) -> estabas buscando un ALQUIMISTA!?");
                            System.out.println(partida.getInventarioTienda().mostrarInventarioAlquimia());
                            System.out.println("DINERO DISPONIBLE = $$$ " + partida.getDineroDisponible());
                            System.out.println("Ingrese el item que desea comprar: ");
                            System.out.println("1. Pocion de Vida 2. Pocion de Revivir 3. No comprar nada");

                            nombreObjeto = eleccionItemAlquimia();
                            break;
                        case 2:
                            seleccionValida = true;
                            System.out.println("Ingrese quien desea entrar a la tienda, pues solo de a uno se debe de entrar...");
                            System.out.println(partida.mostrarNombres());

                            sc.nextLine();
                            numeroDePj = eleccionPersonaje();

                            System.out.println(" +--[♦]==< Bienvenido a LA FORJA DE LOS NUEVE NICOS >==[♦]--+ ");
                            System.out.println("( ͡° ͜ʖ ͡°) -> Tengo TODO seas un Mago, Arquero, Guerrero o Barbaro! Aqui esta lo que necesitas!");
                            System.out.println("Asi que eres un " + partida.getParty().get(numeroDePj).getClases());
                            System.out.println(partida.getInventarioTienda().mostrarInventarioArmeria(partida.getParty().get(numeroDePj)));
                            System.out.println("DINERO DISPONIBLE = $$$ " + partida.getDineroDisponible());
                            System.out.println("Desea comprar ese item?: ");
                            System.out.println("1. Si | 2. No");

                            nombreObjeto = eleccionItemArmeria(numeroDePj);
                            break;
                        default:
                            System.out.println("⭕ Se ingreso un numero no valido, intentelo de nuevo ⭕");
                            sc.nextLine();
                            seleccionValida = false;
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("No se ingreso un numero, intentelo de nuevo");
                    seleccionValida = false;
                    sc.nextLine();
                }
            } while (!seleccionValida);
            System.out.println("+-💰💰💰---------------------------------------------+");
            partida.setDineroDisponible(partida.getInventarioTienda().comprarItem(partida.getPersonajeJugablePoscision(numeroDePj), nombreObjeto, partida.getDineroDisponible()));
            if(nombreObjeto == ""){
                System.out.println("Te vas sin comprar nada! :c");
            }
            System.out.println("DINERO DISPONIBLE = $$$ " + partida.getDineroDisponible());
            System.out.println("+-🎒🎒🎒---------------------------------------------+");
            System.out.println("Inventario de: " + partida.getPersonajeJugablePoscision(numeroDePj).getNombre());
            System.out.println(partida.getPersonajeJugablePoscision(numeroDePj).getInventario().mostrarInventario());
            System.out.println("+----------------------------------------------------+");
            System.out.println("Desea volver a la Plaza de los Gremios? ");
            System.out.println("| Ingrese 1 para volver o ingrese cualquier numero para salir |");
            do {
                try {
                    flag = sc.nextInt();
                    sc.nextLine();
                    seleccionValida2 = true;
                } catch (InputMismatchException e) {
                    System.out.println("No se ingreso un numero, intentelo de nuevo");
                    seleccionValida2 = false;
                    sc.nextLine();
                }

            } while (seleccionValida2 == false);
        } while (flag == 1);
    }

    public int eleccionPersonaje() {
        boolean seleccionValida;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                switch (sc.nextInt()) {
                    case 1:
                        return 0;
                    case 2:
                        return 1;
                    case 3:
                        return 2;
                    case 4:
                        return 3;
                    default:
                        System.out.println("Se ingreso un numero incorrecto, intentelo de nuevo");
                        seleccionValida = false;
                        break;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada no válida. ");
                seleccionValida = false;
                sc.nextLine();
            }
        } while (!seleccionValida);
        return -1;
    }

    public String eleccionItemAlquimia() {
        Scanner sc = new Scanner(System.in);
        boolean seleccionValida;

        do {
            try {
                switch (sc.nextInt()) {
                    case 1:
                        return "Pocion de Vida";
                    case 2:
                        return "Pocion de Revivir";
                    case 3:
                        return "";

                    default:
                        seleccionValida = false;
                        System.out.println("Se ingreso un numero no valido, intentelo de nuevo");
                        sc.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("No se ingreso un numero, intentelo de nuevo");
                seleccionValida = false;
                sc.nextLine();
            }
        } while (!seleccionValida);

        return null;
    }

    public String eleccionItemArmeria(int numeroDePj) {
        Scanner sc = new Scanner(System.in);
        boolean seleccionValida = false;
        try {
            do {
                switch (sc.nextInt()) {
                    case 1:
                        if (partida.getParty().get(numeroDePj).getClases().equals(E_Clases.MAGO)) {
                            return "Baculo de Toth";
                        } else if (partida.getParty().get(numeroDePj).getClases().equals(E_Clases.ARQUERO)) {
                            return "Arco Largo";
                        } else if (partida.getParty().get(numeroDePj).getClases().equals(E_Clases.GUERRERO)) {
                            return "Espada Larga";
                        } else if (partida.getParty().get(numeroDePj).getClases().equals(E_Clases.BARBARO)) {
                            return "Maza de Bridas";
                        }
                    case 2:
                        return "";
                    default:
                        System.out.println("Se ingreso un numero no valido, intentelo de nuevo");
                        sc.nextLine();
                        seleccionValida = false;
                        break;
                }
            } while (!seleccionValida);
        } catch (InputMismatchException e) {
            System.out.println("No se ingreso un numero, intentelo de nuevo");
            seleccionValida = false;
            sc.nextLine();
        }
        return "";
    }

}


