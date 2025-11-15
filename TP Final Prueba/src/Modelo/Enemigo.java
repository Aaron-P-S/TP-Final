package Modelo;

import Interfaces.Jsonable;
import org.json.JSONObject;

import java.util.HashMap;

public class Enemigo extends Entidad implements Jsonable {

    private int nivel;

    public Enemigo(String nombre, int nivel, int puntosDeVidaMaxima) {
        super(nombre);
        setNivel(nivel);
        setPuntosDeVidaMaxima(puntosDeVidaMaxima);
    }

    public Enemigo(String nombre, int nivel, int puntosDeVidaMaxima,int puntosDeVidaActual) {
        super(nombre);
        this.nivel = nivel;
        setPuntosDeVidaMaxima(puntosDeVidaMaxima);
        setPuntosDeVidaActual(puntosDeVidaActual);
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
        setPuntosDeVidaMaxima(((int) (500 * (1 + (0.5 * nivel)))));
    }

    @Override
    public String toString() {
        return "Enemigo{" +
                "nivel=" + nivel +
                "} " + super.toString();
    }

public int atacar(PersonajeJugable pj, int nivel){
        int danoHecho=((int) (50 * (1 + 0.25 * nivel)));
        pj.setPuntosDeVidaActual( - danoHecho);
        return danoHecho;
}


    @Override
    public JSONObject toJson() {
        JSONObject enemigoJson= new JSONObject();
        enemigoJson.put("nivel", getNivel());
        enemigoJson.put("nombre", getNombre());
        enemigoJson.put("puntosDeVidaMaxima", getPuntosDeVidaMaxima());
        enemigoJson.put("puntosDeVidaActual",getPuntosDeVidaActual());
        return enemigoJson;
    }
}
