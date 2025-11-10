package Modelo;

import Excepciones.NumeroNoValidoException;
import Excepciones.TodosLosMiembrosMuertosException;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Menu {
    //Coleccion donde guardamos las diferentes partidas
    //cambiar a private
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
        while (continuar) {
            switch (eleccion) {
                case 0:
                    System.out.println("Bienvenido al juego ...\n" +
                            "Ingrese un numero para continuar:\n" +
                            "1. Empezar pelea:\n" +
                            "2. Visitar la tienda\n" +
                            "3. Salir\n");
                    try {
                        eleccion = sc.nextInt();
                        sc.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("No se ingreso un numero, intentelo de nuevo");
                        eleccion = 0;
                        sc.nextLine();
                    }
                    break;
                case 1:
                    try {
                        if (combate()) {
                            System.out.println("Felicidades, a ganado el combate numero " + (nivel + 1));
                        } else {
                            throw new TodosLosMiembrosMuertosException("☠️ Han muerto todos los miembros de la party ☠️");
                        }
                        nivel++;
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
                    int flag=0;
                    do {

                        System.out.println("+-🛍️🛍️🛍️------------------------------------------------+");
                        System.out.println("Bienvenido a la tienda!");
                        System.out.println(partida.inventarioTienda.mostrarInventario().toString());
                        System.out.println("DINERO DISPONIBLE = $$$" + partida.getDineroDisponible());
                        System.out.println("+----------------------------------------------------+");
                        System.out.println("Ingrese a quien desea comprar el item");
                        System.out.println("1-> " + partida.party.get(0).getNombre()+" | 2->" + partida.party.get(1).getNombre() + "| 3-> "+ partida.party.get(2).getNombre() + " | 4-> " + partida.party.get(3).getNombre() );
                        switch (sc.nextInt()){
                            case 1:
                                    nombre = partida.party.get(0).getNombre();
                                break;
                            case 2:
                                    nombre = partida.party.get(1).getNombre();
                                break;
                            case 3:
                                    nombre = partida.party.get(2).getNombre();
                                break;
                            case 4:
                                    nombre = partida.party.get(3).getNombre();
                                break;
                            default:
                                System.out.println("Se ingreso un numero no valido, intentelo de nuevo");
                                sc.nextLine();
                                break;
                        }
                        System.out.println("+----------------------------------------------------+");
                        System.out.println("Ingrese el nombre del item que desea comprar");
                        System.out.println("1- ❤️‍🩹 Pocion de vida | 2- ⚔️ Espada Larga");
                        switch (sc.nextInt()){
                            case 1:
                                nombreObjeto = "Pocion de Vida";
                                break;
                            case 2:
                                nombreObjeto = "Espada Larga";
                                break;
                            default:
                                System.out.println("⭕ Se ingreso un numero no valido, intentelo de nuevo ⭕");
                                sc.nextLine();
                                break;
                        }

                        System.out.println("+-💰💰💰---------------------------------------------+");
                        partida.setDineroDisponible(partida.inventarioTienda.comprarItem(partida.getPersonajeJugable(nombre), nombreObjeto, partida.getDineroDisponible()));
                        System.out.println("DINERO DISPONIBLE = $$$ " + partida.getDineroDisponible());
                        System.out.println("+-🎒🎒🎒---------------------------------------------+");
                        System.out.println("Inventario de: "+nombre);
                        System.out.println(partida.getPersonajeJugable(nombre).mostrarInventario());
                        System.out.println("+----------------------------------------------------+");
                        System.out.println("Desea volver a la tienda? ");
                        System.out.println("| 1. Si | | 2. No |");
                        flag= sc.nextInt();
                   }while(flag == 1);
                    eleccion = 0;
                    break;
                case 3:
                    //guardar los datos antes de salir
                    continuar = false;
                    break;
                default:
                    System.out.println("⭕ Se ingreso un numero no valido, intentelo de nuevo ⭕");
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
                    System.out.println("Es el turno de "+partida.party.get(turno).getNombre());
                    System.out.println("Ingrese un numero segun la accion que quiera realizar en el combate:\n" +
                            "| 1. atacar |  | 2. Inventario  | 3. Salir |\n");
                    try {
                        eleccionCombate = sc.nextInt();
                        sc.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("⭕ No se ingreso un numero, intentelo de nuevo ⭕");
                        eleccionCombate = 0;
                        sc.nextLine();
                    }
                    break;
                case 1:
                    //atacar

                    if (!partida.party.get(turno).isVivoOMuerto()) {
                        System.out.println("+-----------------------☠️---------------------------+");
                        System.out.println(partida.party.get(turno).getNombre() + " esta muerto, se salteara su turno ");
                        turno++;
                    } else {
                        System.out.println("+-----------------------🤺---------------------------+");
                        System.out.println(partida.vidaParty());
                        int valorAtaque = partida.party.get(turno).atacar(partida.enemigos.get(nivel));
                        System.out.println(partida.party.get(turno).getNombre() + " atacara por " + valorAtaque + "☄️");
                        System.out.println(partida.enemigos.get(nivel).getNombre() + " tiene " + partida.enemigos.get(nivel).getPuntosDeVidaActual() + " ❤️ puntos de vida restantes ️");

                        turno++;

                        }
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
                            while(!partida.party.get(eleccionEnemigo).isVivoOMuerto()) {
                                if (eleccionEnemigo > partida.party.size()) eleccionEnemigo++;
                                else eleccionEnemigo--;
                            }

                        }
                        int danoHecho = partida.enemigos.get(nivel).atacar(partida.party.get(eleccionEnemigo), nivel);
                        System.out.println("+-----------------------⚔️---------------------------+");
                        System.out.println(partida.enemigos.get(nivel).getNombre() + " ataco a " + partida.party.get(eleccionEnemigo).getNombre() + " por " + danoHecho + " de dano ☄️");
                        System.out.println("+----------------------------------------------------+");
                        System.out.println( "La vida actual de "+ partida.party.get(eleccionEnemigo).getNombre() +" es "+ partida.party.get(eleccionEnemigo).getPuntosDeVidaActual() + " ❤️");
                        turno = 0;
                    }
                    eleccionCombate = 0;
                    break;
                case 2:
                    System.out.println("Ingrese un numero segun el item que quiera utilizar");
                    if(partida.party.get(turno).inventario.usarItem(partida.party.get(turno).inventario.inventario.get("Pocion de Vida"),partida.party.get(turno))) {
                        turno++;
                    }
                    eleccionCombate=0;
                    break;
                default:
                    throw new NumeroNoValidoException("⭕ Se escogio un numero no valido, intentelo de nuevo ⭕");
            }
        }
        return partida.estadoParty();
    }

}
