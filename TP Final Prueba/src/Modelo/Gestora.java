package Modelo;

import java.util.Scanner;

public class Gestora {
    //Coleccion donde guardamos las diferentes partidas

    //Metodos para ejecutar el juego
    public void menu(){
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        int eleccion = 0;
        while (continuar) {
            switch (eleccion) {
                case 1:

                    eleccion = 0;
                    break;
                    default:
                        System.out.println("Bienvenido al juego ...");
                        break;
            }
        }
    }

}
