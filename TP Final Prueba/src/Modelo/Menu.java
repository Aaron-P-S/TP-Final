package Modelo;

import Excepciones.NumeroNoValidoException;

import java.util.InputMismatchException;
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
                    System.out.println("Bienvenido al juego ..." +
                            "Ingrese un numero para continuar:" +
                            "1. Empezar pelea:" +
                            "2. Visitar la tienda" +
                            "3. Salir");
                    try {
                        eleccion = sc.nextInt();
                        sc.nextLine();
                    }catch (InputMismatchException e){
                        System.out.println("No se ingreso un numero, intentelo de nuevo");
                        eleccion = 0;
                        sc.nextLine();
                    }
                    break;
                case 1:
                    if (combate()) {
                        System.out.println("Felicidades, a ganade el combate numero " + (nivel + 1));
                    }
                    nivel++;
                    eleccion = 0;
                    break;
                case 2:
                    break;
                case 3:
                    //guardar los datos antes de salir
                    continuar = false;
                    break;
                default:
                    System.out.println("Se ingreso un numero no valido, intentelo de nuevo");
                    break;
            }
        }
    }

    public boolean combate() {
        int eleccionCombate = 0;
        int turno = 0;
        Scanner sc = new Scanner(System.in);
        while (eleccionCombate != 2 && partida.enemigos.get(nivel).isVivoOMuerto()) {
            switch (eleccionCombate) {
                case 0:
                    System.out.println("Ingrese un numero segun la accion que quiera realizar en el combate:" +
                            "1. atacar" +
                            "2. salir");
                    eleccionCombate = sc.nextInt();
                    sc.nextLine();
                    break;
                case 1:
                    //atacar
                    if (turno + 1 > partida.party.size()) {

                        turno = 0;
                    }
                    System.out.println(turno);
                    System.out.println(partida.party.get(turno).getNombre() + " atacara");
                    int valorAtaque=partida.party.get(turno).atacar(partida.enemigos.get(nivel));
                    System.out.println(partida.enemigos.get(nivel).getNombre() + " tiene " + partida.enemigos.get(nivel).getPuntosDeVidaActual() + " puntos de vida restantes");

                    turno++;
                    eleccionCombate = 0;
                    break;
                default:
                    throw new NumeroNoValidoException("Se escogio un numero no valido, intentelo de nuevo");
            }
        }
        return partida.estadoParty();
    }

}
