package Modelo;

import Enumeradores.E_Clases;

import java.util.HashMap;

public class PersonajeJugable extends Entidad {
    E_Clases clases;
    Inventario inventario;
    public PersonajeJugable(String nombre, E_Clases clase) {
        super(nombre);
        this.clases = clase;
        this.inventario = new Inventario();
        setPuntosDeVidaMaxima(clase.getVidaBase());
    }

    public E_Clases getClases() {
        return clases;
    }

public int atacar(Enemigo enemigo) {
        if(getClases().equals(E_Clases.GUERRERO)){
            enemigo.setPuntosDeVidaActual(enemigo.getPuntosDeVidaActual()-100);
            return 100;
        }else if(getClases().equals(E_Clases.MAGO)){
            enemigo.setPuntosDeVidaActual(enemigo.getPuntosDeVidaActual()-50);
            return 50;
        }else if(getClases().equals(E_Clases.ARQUERO)){
            enemigo.setPuntosDeVidaActual(enemigo.getPuntosDeVidaActual()-125);
            return 125;
        }else if(getClases().equals(E_Clases.BARBARO)){
            enemigo.setPuntosDeVidaActual(enemigo.getPuntosDeVidaActual()-75);
            return 75;
        }
        return 0;
}

    @Override
    public String toString() {
        return "PersonajeJugable{" +super.toString()
                + "clases=" + clases
                + "inventario=" + inventario + "}";

    }
}
