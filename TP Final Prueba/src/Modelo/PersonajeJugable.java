package Modelo;

import Enumeradores.E_Clases;
import Interfaces.Jsonable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class PersonajeJugable extends Entidad implements Jsonable {
    E_Clases clases;
    Inventario inventario;

    public PersonajeJugable(String nombre, E_Clases clase) {
        super(nombre);
        this.clases = clase;
        this.inventario = new Inventario();
        setPuntosDeVidaMaxima(clase.getVidaBase());
    }
    public PersonajeJugable(String nombre, E_Clases clase, int puntosDeVidaActual) {
        super(nombre);
        this.inventario = new Inventario();
        this.clases= clase;
        setPuntosDeVidaMaxima(clase.getVidaBase());
        setPuntosDeVidaActualAux(puntosDeVidaActual);
    }
    public PersonajeJugable(String nombre, E_Clases clase, boolean vivoOMuerto) {
        super(nombre);
        this.clases = clase;
        this.inventario = new Inventario();
        setPuntosDeVidaMaxima(clase.getVidaBase());
        setPuntosDeVidaActualAux(0);
        setVivoOMuerto(false);
    }
    public E_Clases getClases() {
        return clases;
    }

    public int atacar(Enemigo enemigo) {
        if (getClases().equals(E_Clases.GUERRERO)) {
            if (inventario.inventario.containsKey("Espada Larga")&&inventario.inventario.get("Espada Larga").getCantidad() > 0) {
                enemigo.setPuntosDeVidaActual(-E_Clases.GUERRERO.getDano() * 2);
                return E_Clases.GUERRERO.getDano() * 2;
            } else {
                    enemigo.setPuntosDeVidaActual(-E_Clases.GUERRERO.getDano());
                    return E_Clases.GUERRERO.getDano();
                }
            }
         else if (getClases().equals(E_Clases.MAGO)) {
            if (inventario.inventario.containsKey("Baculo de Toth")&&inventario.inventario.get("Baculo de Toth").getCantidad() > 0) {
                enemigo.setPuntosDeVidaActual(-E_Clases.MAGO.getDano() * 5);
                return E_Clases.MAGO.getDano() * 5;
            } else {
                enemigo.setPuntosDeVidaActual(-E_Clases.MAGO.getDano());
                return E_Clases.MAGO.getDano();
            }
        } else if (getClases().equals(E_Clases.ARQUERO)) {
            if (inventario.inventario.containsKey("Arco Largo")&&inventario.inventario.get("Arco Largo").getCantidad() > 0) {
                enemigo.setPuntosDeVidaActual(-E_Clases.ARQUERO.getDano() * 3);
                return E_Clases.ARQUERO.getDano() * 3;
            }else {
                enemigo.setPuntosDeVidaActual(-E_Clases.ARQUERO.getDano());
                return E_Clases.ARQUERO.getDano();
            }
        } else if (getClases().equals(E_Clases.BARBARO)) {
            if (inventario.inventario.containsKey("Maza de Bridas")&&inventario.inventario.get("Maza de Bridas").getCantidad()>0) {
                    setPuntosDeVidaActual(20);
                    enemigo.setPuntosDeVidaActual(-E_Clases.BARBARO.getDano() * 2);
                    return E_Clases.BARBARO.getDano() * 2;
                } else {
                enemigo.setPuntosDeVidaActual(-E_Clases.BARBARO.getDano());
                return E_Clases.BARBARO.getDano();
            }
        }
        return 0;
    }

    public boolean agregarInventario(String nombre, Item item) {
        inventario.agregarItem(nombre, item);
        return true;
    }

    public boolean cambiarCantidad(String nombre) {
        inventario.aumentarCantidad(nombre);
        return true;
    }

    public StringBuilder mostrarInventario() {
        return inventario.mostrarInventario();
    }

    public void subirDeNivel(int nivel) {
        setPuntosDeVidaMaxima(((int) (getPuntosDeVidaMaxima() * (1 + 0.15 * nivel))));
        setVivoOMuerto(true);
    }
    public void agregarInventario(ArrayList<Item> inventarioAgregar) {
        for (Item item : inventarioAgregar) {
            agregarInventario(item.getNombre(), item);
        }
    }
    public void agregarInventario(Inventario inventarioAgregar){
        for(String clave:inventarioAgregar.inventario.keySet()){
            inventario.inventario.put(clave,inventarioAgregar.inventario.get(clave));
        }
    }

    public Inventario getInventario() {
        return inventario;
    }

    @Override
    public String toString() {
        return "PersonajeJugable{" + super.toString()
                + "clases=" + clases
                + "inventario=" + inventario.toString() + "}";

    }

    @Override
    public JSONObject toJson() {
        JSONObject personajeJugableJson = new JSONObject();
        personajeJugableJson.put("nombre", getNombre());
        personajeJugableJson.put("clases", getClases());
        personajeJugableJson.put("puntosDeVidaActual",getPuntosDeVidaActual());
        JSONObject inventarioJson = inventario.toJson();
        personajeJugableJson.put("inventario", inventarioJson);
        personajeJugableJson.put("vivoOMuerto",isVivoOMuerto());
        return personajeJugableJson;
    }
}
