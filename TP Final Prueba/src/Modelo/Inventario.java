package Modelo;

import Excepciones.NoHayStockEnTiendaException;
import Excepciones.NoTienesDineroSuficienteException;

import java.util.HashMap;
import java.util.Map;

public class Inventario {
    HashMap<String,Item> inventario;

    public Inventario() {
        inventario=new HashMap<>();
    }

    public boolean agregarItem(String nombre, Item item){
        if(!inventario.containsKey(nombre)){
            inventario.put(nombre,item);
            return true;
        }else{
            return false;
        }
    }

    public boolean aumentarCantidad(String nombre){
        if(inventario.containsKey(nombre)){
            inventario.get(nombre).setCantidad((inventario.get(nombre).getCantidad())+1);
            return true;
        }else{
            return false;
        }
    }

    public int comprarItem(PersonajeJugable personajeJugable, String nombre, int dineroDisponible){
        try {
            if (inventario.containsKey(nombre)) {
                if (dineroDisponible >= inventario.get(nombre).getPrecio()) {
                    if (inventario.get(nombre).getCantidad() > 0) {
                        dineroDisponible -= (int) inventario.get(nombre).getPrecio();
                        inventario.get(nombre).setCantidad(inventario.get(nombre).getCantidad() - 1);
                        personajeJugable.cambiarCantidad(nombre);
                        return dineroDisponible;
                    } else{
                        throw new NoHayStockEnTiendaException("No hay stock de ese item");
                    }
                }else{
                    throw new NoTienesDineroSuficienteException("No tienes dinero suficiente para comprar");
                }
            }
        }catch (NoHayStockEnTiendaException e){
            System.out.println(e.getMessage());
        }catch (NoTienesDineroSuficienteException e){
            System.out.println(e.getMessage());
        }
        return dineroDisponible;
    }

    public StringBuilder mostrarInventario(){
        StringBuilder inventarioString = new StringBuilder();
        for (Map.Entry<String, Item> entry : inventario.entrySet()) {
            inventarioString.append(entry.getKey()).append(" ").append(entry.getValue().toString()).append("\n");
        }
        return inventarioString;
    }
}
