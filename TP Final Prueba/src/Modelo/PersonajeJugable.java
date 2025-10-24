package Modelo;

import Enumeradores.E_Clases;

import java.util.HashMap;

public class PersonajeJugable extends Entidad {
    E_Clases clases;
    Inventario inventario;
    public PersonajeJugable(String nombre, String apellido, E_Clases clase) {
        super(nombre, apellido);
        this.clases = clase;
        this.inventario = new Inventario();
    }

    public E_Clases getClases() {
        return clases;
    }
}
