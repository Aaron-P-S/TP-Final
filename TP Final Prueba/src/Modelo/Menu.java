package Modelo;

import java.util.Scanner;

public class Menu {
    //Coleccion donde guardamos las diferentes partidas
    Partida partida;
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
                    eleccion = sc.nextInt();
                    sc.nextLine();
                    break;
                case 1:
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

}
