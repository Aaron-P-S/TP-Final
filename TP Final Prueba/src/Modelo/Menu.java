package Modelo;

import Enumeradores.E_Clases;
import Excepciones.NumeroNoValidoException;
import Excepciones.TodosLosMiembrosMuertosException;
import GestoraJson.GestorJson;
import JsonUtiles.JsonUtiles;
import org.json.JSONArray;

import java.nio.channels.ScatteringByteChannel;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.SortedMap;

public class Menu {
    //Coleccion donde guardamos las diferentes partidas
    //cambiar a private
    //flag game over cuando se pierde la partida para no poder volver a continuar
    public Partida partida;
    public int nivel = 0;

    public Menu(Partida partida) {
        this.partida = partida;
    }

    //Metodos para ejecutar el juego
    public void menu() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        int eleccion = 0;
        boolean winCon = false;
        while (continuar) {
            switch (eleccion) {
                case 0:
                    System.out.println("""
                            Bienvenido al juego ...
                            Ingrese un numero para continuar:
                            1. Empezar pelea
                            2. Visitar la Plaza de los Gremios
                            3. Salir
                            """);
                    try {
                        eleccion = sc.nextInt();
                        sc.nextLine();
                        if (eleccion < 0) {
                            throw new NumeroNoValidoException("Se ingreso un numero negativo");
                        }
                        if (eleccion > 3) throw new NumeroNoValidoException("Se ingreso un numero no valido");
                    } catch (InputMismatchException e) {
                        System.out.println("No se ingreso un numero, intentelo de nuevo");
                        eleccion = 0;
                        sc.nextLine();
                    } catch (NumeroNoValidoException e) {
                        System.out.println(e.getMessage());
                        eleccion = 0;
                        sc.nextLine();
                    }
                    break;
                case 1:
                    try {
                        if (combate()) {
                            System.out.println("Felicidades, a ganado el combate numero " + (nivel + 1));
                            if (nivel + 1 == partida.enemigos.size()) {
                                System.out.println("Felicitaciones a ganado el juego");
                                winCon=true;
                                continuar=false;
                            }
                        } else {
                            throw new TodosLosMiembrosMuertosException("☠️ Han muerto todos los miembros de la party ☠️" +
                                    "GAME OVER");
                        }
                        nivel++;
                        if (nivel % 3 == 0) {
                            System.out.println("Felicitaciones! Tu party a subido de nivel");
                            partida.party.get(0).subirDeNivel(nivel);
                            partida.party.get(1).subirDeNivel(nivel);
                            partida.party.get(2).subirDeNivel(nivel);
                            partida.party.get(3).subirDeNivel(nivel);
                        }
                    } catch (TodosLosMiembrosMuertosException e) {
                        System.out.println(e.getMessage());
                        eleccion = 3;
                        break;
                    }
                    eleccion = 0;
                    break;
                case 2:
                    //tienda
                    /// menu tienda
                    String nombre = "";
                    String nombreObjeto = "";
                    int flag = 0;
                    int numeroDePj = 0;
                    int seleccion;
                    boolean seleccionValida = false;
                    boolean seleccionValida2 = false;
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
                                        System.out.println("1-> " + partida.party.get(0).getNombre() + " | 2->" + partida.party.get(1).getNombre() + "| 3-> " + partida.party.get(2).getNombre() + " | 4-> " + partida.party.get(3).getNombre());
                                        do {
                                            try {
                                                switch (sc.nextInt()) {
                                                    case 1:
                                                        nombre = partida.party.get(0).getNombre();
                                                        seleccionValida = true;
                                                        break;
                                                    case 2:
                                                        nombre = partida.party.get(1).getNombre();
                                                        seleccionValida = true;
                                                        break;
                                                    case 3:
                                                        nombre = partida.party.get(2).getNombre();
                                                        seleccionValida = true;
                                                        break;
                                                    case 4:
                                                        nombre = partida.party.get(3).getNombre();
                                                        seleccionValida = true;
                                                        break;
                                                    default:
                                                        System.out.println("Se ingreso un numero no valido, intentelo de nuevo");
                                                        sc.nextLine();
                                                        seleccionValida = false;
                                                        break;
                                                }
                                            } catch (InputMismatchException e) {
                                                System.out.println("No se escribio un numero, intentelo de nuevo");
                                                seleccionValida = false;
                                                sc.nextLine();
                                            }
                                        } while (!seleccionValida);
                                        System.out.println(" °º¤ø,¸Bienvenido a La tienda del maestro Emma¸,ø¤º° ");
                                        System.out.println("( ͡° ͜ʖ ͡°) -> estabas buscando un ALQUIMISTA!?");
                                        System.out.println(partida.inventarioTienda.mostrarInventarioAlquimia());
                                        System.out.println("DINERO DISPONIBLE = $$$ " + partida.getDineroDisponible());
                                        System.out.println("Ingrese el item que desea comprar: ");
                                        System.out.println("1. Pocion de Vida 2. Pocion de Revivir");
                                        try {
                                            do {
                                                switch (sc.nextInt()) {
                                                    case 1:
                                                        seleccionValida = true;
                                                        nombreObjeto = "Pocion de Vida";
                                                        break;
                                                    case 2:
                                                        seleccionValida = true;
                                                        nombreObjeto = "Pocion de Revivir";
                                                        break;
                                                    default:
                                                        seleccionValida = false;
                                                        System.out.println("Se ingreso un numero no valido, intentelo de nuevo");
                                                        sc.nextLine();
                                                        break;
                                                }
                                            } while (!seleccionValida);
                                        }catch (InputMismatchException e){
                                            System.out.println("No se ingreso un numero, intentelo de nuevo");
                                            seleccionValida = false;
                                            sc.nextLine();
                                        }

                                        break;
                                    case 2:
                                        seleccionValida = true;
                                        System.out.println("Ingrese quien desea entrar a la tienda, pues solo de a uno se debe de entrar...");
                                        System.out.println("1-> " + partida.party.get(0).getNombre() + " | 2->" + partida.party.get(1).getNombre() + "| 3-> " + partida.party.get(2).getNombre() + " | 4-> " + partida.party.get(3).getNombre());

                                        do {
                                            try {
                                                seleccion = sc.nextInt();
                                                sc.nextLine();

                                                switch (seleccion) {
                                                    case 1:
                                                        nombre = partida.party.get(0).getNombre();
                                                        numeroDePj = 0;
                                                        seleccionValida = true;
                                                        break;
                                                    case 2:
                                                        nombre = partida.party.get(1).getNombre();
                                                        numeroDePj = 1;
                                                        seleccionValida = true;
                                                        break;
                                                    case 3:
                                                        nombre = partida.party.get(2).getNombre();
                                                        numeroDePj = 2;
                                                        seleccionValida = true;
                                                        break;
                                                    case 4:
                                                        nombre = partida.party.get(3).getNombre();
                                                        numeroDePj = 3;
                                                        seleccionValida = true;
                                                        break;
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

                                        System.out.println(" +--[♦]==< Bienvenido a LA FORJA DE LOS NUEVE NICOS >==[♦]--+ ");
                                        System.out.println("( ͡° ͜ʖ ͡°) -> Tengo TODO seas un Mago, Arquero, Guerrero o Barbaro! Aqui esta lo que necesitas!");
                                        System.out.println("Asi que eres un " + partida.party.get(numeroDePj).getClases());
                                        System.out.println(partida.inventarioTienda.mostrarInventarioArmeria(partida.party.get(numeroDePj)));
                                        System.out.println("DINERO DISPONIBLE = $$$ " + partida.getDineroDisponible());
                                        System.out.println("Desea comprar ese item?: ");
                                        System.out.println("1. Si | 2. No");
                                        try {
                                            do {
                                                switch (sc.nextInt()) {
                                                    case 1:
                                                        seleccionValida = true;
                                                        if (partida.party.get(numeroDePj).getClases().equals(E_Clases.MAGO)) {
                                                            nombreObjeto = "Baculo de Toth";
                                                        } else if (partida.party.get(numeroDePj).getClases().equals(E_Clases.ARQUERO)) {
                                                            nombreObjeto = "Arco Largo";
                                                        } else if (partida.party.get(numeroDePj).getClases().equals(E_Clases.GUERRERO)) {
                                                            nombreObjeto = "Espada Larga";
                                                        } else if (partida.party.get(numeroDePj).getClases().equals(E_Clases.BARBARO)) {
                                                            nombreObjeto = "Maza de Bridas";
                                                        }
                                                        break;
                                                    case 2:
                                                        nombreObjeto = "null";
                                                        break;
                                                    default:
                                                        System.out.println("Se ingreso un numero no valido, intentelo de nuevo");
                                                        sc.nextLine();
                                                        seleccionValida = false;
                                                        break;
                                                }
                                            } while (!seleccionValida);
                                        }catch (InputMismatchException e){
                                            System.out.println("No se ingreso un numero, intentelo de nuevo");
                                            seleccionValida = false;
                                            sc.nextLine();
                                        }
                                        break;
                                    case 3:
                                        // guardar los datos al salir
                                        continuar = false;
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
                        partida.setDineroDisponible(partida.inventarioTienda.comprarItem(partida.getPersonajeJugable(nombre), nombreObjeto, partida.getDineroDisponible()));
                        System.out.println("DINERO DISPONIBLE = $$$ " + partida.getDineroDisponible());
                        System.out.println("+-🎒🎒🎒---------------------------------------------+");
                        System.out.println("Inventario de: " + nombre);
                        System.out.println(partida.getPersonajeJugable(nombre).mostrarInventario());
                        System.out.println("+----------------------------------------------------+");
                        System.out.println("Desea volver a la Plaza de los Gremios? ");
                        System.out.println("| Ingrese 1 para volver o ingrese cualquier numero para salir |");
                        do {
                            try {
                                flag = sc.nextInt();
                                sc.nextLine();
                                seleccionValida2 = true;
                            }catch (InputMismatchException e){
                                System.out.println("No se ingreso un numero, intentelo de nuevo");
                                seleccionValida2=false;
                                sc.nextLine();
                            }

                        }while (seleccionValida2==false);
                    } while (flag == 1);
                    eleccion = 0;
                    break;
                case 3:
                    continuar = false;
                    break;
            }

        }
    }

    public boolean combate() {
        int eleccionCombate = 0;
        int turno = 0;
        Scanner sc = new Scanner(System.in);
        while (partida.estadoParty() && partida.enemigos.get(nivel).isVivoOMuerto()) {
            switch (eleccionCombate) {
                case 0:
                    System.out.println("+-👾👾👾------------------------------------------------+");
                    System.out.println(partida.vidaParty(nivel));
                    System.out.println("+--------------------------------------------------------+");
                    System.out.println("Es el turno de " + partida.party.get(turno).getNombre());
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

                    if (!partida.party.get(turno).isVivoOMuerto()) {
                        System.out.println("+-----------------------☠️---------------------------+");
                        System.out.println(partida.party.get(turno).getNombre() + " esta muerto, se salteara su turno ");
                    } else {
                        int valorAtaque = partida.party.get(turno).atacar(partida.enemigos.get(nivel));
                        System.out.println(partida.party.get(turno).getNombre() + " atacara por " + valorAtaque + "☄️");
                        System.out.println(partida.enemigos.get(nivel).getNombre() + " tiene " + partida.enemigos.get(nivel).getPuntosDeVidaActual() + " ❤️ puntos de vida restantes ️");

                    }
                    turno++;
                    if (turno + 1 > partida.party.size()) {
                        int numeroRandom = (int) (Math.random() * 101);
                        int eleccionEnemigo = 0;
                        System.out.println(numeroRandom);
                        if (numeroRandom < 25) {
                            eleccionEnemigo = 0;
                        } else if (numeroRandom < 50) {
                            eleccionEnemigo = 1;
                        } else if (numeroRandom < 75) {
                            eleccionEnemigo = 2;
                        } else if (numeroRandom < 100) {
                            eleccionEnemigo = 3;
                        }
                        if (!partida.party.get(eleccionEnemigo).isVivoOMuerto()) {
                            while (!partida.party.get(eleccionEnemigo).isVivoOMuerto()) {
                                if (eleccionEnemigo > partida.party.size()) eleccionEnemigo++;
                                else eleccionEnemigo--;
                            }

                        }
                        int danoHecho = partida.enemigos.get(nivel).atacar(partida.party.get(eleccionEnemigo), nivel);
                        System.out.println("+-----------------------⚔️---------------------------+");
                        System.out.println(partida.enemigos.get(nivel).getNombre() + " ataco a " + partida.party.get(eleccionEnemigo).getNombre() + " por " + danoHecho + " de dano ☄️");
                        System.out.println("+----------------------------------------------------+");
                        System.out.println("La vida actual de " + partida.party.get(eleccionEnemigo).getNombre() + " es " + partida.party.get(eleccionEnemigo).getPuntosDeVidaActual() + " ❤️");
                        turno = 0;
                    }
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
                                if (partida.party.get(turno).inventario.usarItem(partida.party.get(turno).inventario.inventario.get("Pocion de Vida"), partida.party.get(turno))) {
                                    turno++;
                                } else {
                                    System.out.println();
                                }
                                break;
                            case 2:
                                if (partida.party.get(turno).inventario.usarItem(partida.party.get(turno).inventario.inventario.get("Pocion de Revivir"), partida.party.get(turno))) {
                                    turno++;
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
//        GestorJson gestorJson = new GestorJson();
//        JSONArray todos=gestorJson.pasarDeArrayAJson(partida.enemigos,partida.getParty(),partida.getInventarioTienda());
//        JsonUtiles.grabarUnJson(todos,"Juego1");
        return partida.estadoParty();
    }

    public void usarHabilidad(Enemigo enemigo) {
        if (partida.party.get(nivel).getClases().equals(E_Clases.GUERRERO)) {
            partida.party.get(nivel).atacar(enemigo);
        } else if (partida.party.get(nivel).getClases().equals(E_Clases.MAGO)) {
            partida.party.get(0).setPuntosDeVidaActual(partida.party.get(0).getPuntosDeVidaActual() + 25);
            partida.party.get(1).setPuntosDeVidaActual(partida.party.get(1).getPuntosDeVidaActual() + 25);
            partida.party.get(2).setPuntosDeVidaActual(partida.party.get(2).getPuntosDeVidaActual() + 25);
            partida.party.get(3).setPuntosDeVidaActual(partida.party.get(3).getPuntosDeVidaActual() + 25);
        } else if (partida.party.get(nivel).getClases().equals(E_Clases.ARQUERO)) {
            partida.setDineroDisponible(partida.getDineroDisponible() + ((int) (25 * (1 + 0.25 * nivel))));
        } else if (partida.party.get(nivel).getClases().equals(E_Clases.BARBARO)) {
//inmortalidad por dos turnos
        }
    }

}

