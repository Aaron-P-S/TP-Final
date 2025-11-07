package Modelo;

import java.util.ArrayList;

public class Partida {
    // clase para guardar los datos de las distintas partidas
    Inventario inventarioTienda;
    ArrayList<PersonajeJugable> party;
    ArrayList<Enemigo> enemigos;

    public Partida(Inventario inventarioTienda, ArrayList<PersonajeJugable> party, ArrayList<Enemigo> enemigos) {
        this.inventarioTienda = inventarioTienda;
        this.party = party;
        this.enemigos = enemigos;
    }

    public Partida(){

    }

    public boolean agregarPersonajeJugable(PersonajeJugable personajeJugable){
        party.add(personajeJugable);
        return true;

    }

    public boolean agregarEnemigo(Enemigo enemigo){
        enemigos.add(enemigo);
        return true;
    }

    public StringBuilder mostrarParty(){
        StringBuilder partyString = new StringBuilder();
        for (PersonajeJugable pj : party) {
            partyString.append(pj.toString()).append("\n");
        }
        return partyString;

    }

    public StringBuilder mostrarEnemigos() {
        StringBuilder enemigosString = new StringBuilder();
        for (Enemigo e: enemigos){
            enemigosString.append(e.toString()).append("\n");
        }
        return enemigosString;
    }


}

