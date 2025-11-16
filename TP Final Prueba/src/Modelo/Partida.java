package Modelo;

import GestoraJson.GestorJson;

import java.util.ArrayList;
import java.util.HashMap;

public class Partida {
    // clase para guardar los datos de las distintas partidas

    // INVENTARIO TIENDA
    Inventario inventarioTienda;
    //GENERICOS
    GestorGenerico<PersonajeJugable> party;
    GestorGenerico<Enemigo> enemigos;

    private int dineroDisponible;

    public Partida() {
        this.inventarioTienda = new Inventario();
        this.party = new GestorGenerico<>();
        this.enemigos = new GestorGenerico<>();
        this.dineroDisponible = 20000;
    }
    public Partida(GestorGenerico<PersonajeJugable> personajes, GestorGenerico<Enemigo> enemigos, Inventario inventarioTienda, int dineroDisponible) {
        this.inventarioTienda = inventarioTienda;
        this.party = personajes;
        this.enemigos = enemigos;
        this.dineroDisponible = dineroDisponible;
    }

    public boolean agregarPersonajeJugable(PersonajeJugable personajeJugable) {
        party.agregar(personajeJugable);
        return true;

    }

    public boolean agregarEnemigo(Enemigo enemigo) {
        enemigos.agregar(enemigo);
        return true;
    }

    public StringBuilder mostrarParty() {
        StringBuilder partyString = new StringBuilder();
        for (PersonajeJugable pj : party.getLista()) {
            partyString.append(pj.toString()).append("\n");
        }
        return partyString;

    }

    public StringBuilder mostrarEnemigos() {
        StringBuilder enemigosString = new StringBuilder();
        for (Enemigo e : enemigos.getLista()) {
            enemigosString.append(e.toString()).append("\n");
        }
        return enemigosString;
    }

    public boolean estadoParty() {
        boolean estado = false;
        for (PersonajeJugable pj : party.getLista()) {
            if (pj.isVivoOMuerto()) {
                return true;
            }
        }
        return estado;
    }

    public PersonajeJugable getPersonajeJugable(String nombre) {
        for (PersonajeJugable pj : party.getLista()) {
            if (pj.getNombre().equals(nombre)) {
                return pj;
            }
        }
        return null;
    }

    public int getDineroDisponible() {
        return dineroDisponible;
    }

    public void setDineroDisponible(int dineroDisponible) {
        this.dineroDisponible = dineroDisponible;
    }

    public boolean agregarTienda(Inventario inventario) {
        this.inventarioTienda = inventario;
        return true;
    }

    public String vidaParty(int nivel) {

        nivel = (int) nivel / 3;

        ArrayList<PersonajeJugable> partyLista = party.getLista();

        String S = "         " + partyLista.get(0).getNombre() + " lvl." + nivel + "         " + partyLista.get(1).getNombre() + " lvl." + nivel + "            " + partyLista.get(2).getNombre() + " lvl." + nivel + "                " + partyLista.get(3).getNombre() + " lvl." + nivel + "\n" +
                "  | " + partyLista.get(0).getPuntosDeVidaActual() + " ❤️ de " + partyLista.get(0).getPuntosDeVidaMaxima() + " 💖  |  | " + partyLista.get(1).getPuntosDeVidaActual() + " ❤️ de " + partyLista.get(1).getPuntosDeVidaMaxima() + " 💖  |  | " + partyLista.get(2).getPuntosDeVidaActual() + " ❤️ de " + partyLista.get(2).getPuntosDeVidaMaxima() + " 💖  |  | " + partyLista.get(3).getPuntosDeVidaActual() + " ❤️ de " + partyLista.get(3).getPuntosDeVidaMaxima() + " 💖  |";
        return S;
    }

    public ArrayList<Enemigo> getEnemigos() {
        return enemigos.getLista();
    }

    public ArrayList<PersonajeJugable> getParty() {
        return party.getLista();
    }

    public Inventario getInventarioTienda() {
        return inventarioTienda;
    }
    public int recompensa(int nivel){
        setDineroDisponible(300*(1+nivel));
        return 300*(1+nivel);
    }
}

