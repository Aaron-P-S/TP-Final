package Modelo;

import Interfaces.Jsonable;

import java.util.HashMap;

public class Enemigo extends Entidad implements Jsonable {

    int nivel;

    public Enemigo(String nombre, int nivel) {
        super(nombre);
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "Enemigo{" +
                "nivel=" + nivel +
                "} " + super.toString();
    }





}
