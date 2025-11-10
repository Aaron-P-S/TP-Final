package Modelo;

import java.util.ArrayList;

public class Partida {
    // clase para guardar los datos de las distintas partidas
    Inventario inventarioTienda;
    ArrayList<PersonajeJugable> party;
    ArrayList<Enemigo> enemigos;
    private int dineroDisponible;

    public Partida(Inventario inventarioTienda, ArrayList<PersonajeJugable> party, ArrayList<Enemigo> enemigos) {
        this.inventarioTienda = inventarioTienda;
        this.party = party;
        this.enemigos = enemigos;
        this.dineroDisponible=200;

    }

    public Partida(){
this.inventarioTienda = new Inventario();
this.party = new ArrayList<>();
this.enemigos = new ArrayList<>();
this.dineroDisponible=200;
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

    public boolean estadoParty(){
        boolean estado = false;
        for (PersonajeJugable pj : party) {
            if(pj.isVivoOMuerto()){
                return true;
            }
        }
        return estado;
    }

    public PersonajeJugable getPersonajeJugable(String nombre){
        for (PersonajeJugable pj : party){
            if(pj.getNombre().equals(nombre)){
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

    public boolean agregarTienda(Inventario inventario){
        this.inventarioTienda = inventario;
        return true;
    }

    public String vidaParty(){

        String S = "         "+party.get(0).getNombre() +"                   "+ party.get(1).getNombre() +"                   "+ party.get(2).getNombre() +"                  "+ party.get(3).getNombre()+"\n" +
                "  | "+party.get(0).getPuntosDeVidaActual() + " ❤️ de " + party.get(0).getPuntosDeVidaMaxima() +" 💖  |  | "+ party.get(1).getPuntosDeVidaActual() + " ❤️ de " + party.get(1).getPuntosDeVidaMaxima() + " 💖  |  | "+ party.get(2).getPuntosDeVidaActual()  + " ❤️ de " + party.get(2).getPuntosDeVidaMaxima() +" 💖  |  | "+ party.get(3).getPuntosDeVidaActual() + " ❤️ de " + party.get(3).getPuntosDeVidaMaxima() + " 💖  |";


        return S;
    }
}

