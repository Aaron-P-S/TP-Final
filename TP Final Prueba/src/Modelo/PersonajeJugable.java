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
            if(inventario.inventario.containsKey("Espada Larga")){
                enemigo.setPuntosDeVidaActual(enemigo.getPuntosDeVidaActual()-E_Clases.GUERRERO.getDano()*2);
                return E_Clases.GUERRERO.getDano()*2;
            }else {
                enemigo.setPuntosDeVidaActual(enemigo.getPuntosDeVidaActual() - E_Clases.GUERRERO.getDano());
                return E_Clases.GUERRERO.getDano();
            }
        }else if(getClases().equals(E_Clases.MAGO)){
            if(inventario.inventario.containsKey("Baculo de Toth")){
                enemigo.setPuntosDeVidaActual(enemigo.getPuntosDeVidaActual()-E_Clases.MAGO.getDano()*5);
                return E_Clases.MAGO.getDano()*5;
            }else {
                enemigo.setPuntosDeVidaActual(enemigo.getPuntosDeVidaActual() - E_Clases.MAGO.getDano());
                return E_Clases.MAGO.getDano();
            }
        }else if(getClases().equals(E_Clases.ARQUERO)){
            if(inventario.inventario.containsKey("Arco Largo")){
                enemigo.setPuntosDeVidaActual(enemigo.getPuntosDeVidaActual()-E_Clases.ARQUERO.getDano()*3);
                return E_Clases.ARQUERO.getDano()*3;
            }
            enemigo.setPuntosDeVidaActual(enemigo.getPuntosDeVidaActual()- E_Clases.ARQUERO.getDano());
            return E_Clases.ARQUERO.getDano();
        }else if(getClases().equals(E_Clases.BARBARO)){
            if(inventario.inventario.containsKey("Maza de Bridas")){
                setPuntosDeVidaActual(getPuntosDeVidaActual()+20);
                enemigo.setPuntosDeVidaActual(enemigo.getPuntosDeVidaActual()-E_Clases.BARBARO.getDano()*2);
                return E_Clases.BARBARO.getDano()*2;
            }else {
            enemigo.setPuntosDeVidaActual(enemigo.getPuntosDeVidaActual()-E_Clases.BARBARO.getDano());
            return E_Clases.BARBARO.getDano();
            }
        }
        return 0;
}

    public boolean agregarInventario(String nombre, Item item){
        inventario.agregarItem(nombre,item);
        return true;
    }

    public boolean cambiarCantidad(String nombre){
        inventario.aumentarCantidad(nombre);
        return true;
    }

    public StringBuilder mostrarInventario(){
        return inventario.mostrarInventario();
    }
    public void subirDeNivel(int nivel){
        setPuntosDeVidaMaxima(((int) (getPuntosDeVidaMaxima() * (1 + 0.15 * nivel))));
        setVivoOMuerto(true);
    }

    @Override
    public String toString() {
        return "PersonajeJugable{" +super.toString()
                + "clases=" + clases
                + "inventario=" + inventario + "}";

    }
}
