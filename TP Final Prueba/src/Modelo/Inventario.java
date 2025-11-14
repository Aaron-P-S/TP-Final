package Modelo;

import Enumeradores.E_Clases;
import Enumeradores.E_TipoItem;
import Excepciones.NoHayStockEnTiendaException;
import Excepciones.NoTieneElItemException;
import Excepciones.NoTienesDineroSuficienteException;
import Excepciones.PersonajeMuertoException;
import Interfaces.Jsonable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Inventario implements Jsonable {
    HashMap<String, Item> inventario;

    public Inventario() {
        inventario = new HashMap<>();
    }

    public boolean agregarItem(String nombre, Item item) {
        if (!inventario.containsKey(nombre)) {
            inventario.put(nombre, item);
            return true;
        } else {
            return false;
        }
    }

    public boolean aumentarCantidad(String nombre) {
        if (inventario.containsKey(nombre)) {
            inventario.get(nombre).setCantidad((inventario.get(nombre).getCantidad()) + 1);
            return true;
        } else {
            return false;
        }
    }

    public int comprarItem(PersonajeJugable personajeJugable, String nombre, int dineroDisponible) {
        try {
            if (inventario.containsKey(nombre)) {
                if (dineroDisponible >= inventario.get(nombre).getPrecio()) {
                    if (inventario.get(nombre).getCantidad() > 0) {
                        dineroDisponible -= (int) inventario.get(nombre).getPrecio();
                        inventario.get(nombre).setCantidad(inventario.get(nombre).getCantidad() - 1);
                        personajeJugable.cambiarCantidad(nombre);
                        System.out.println("💸 ¡¡Compra Realizada!! 💸");
                        return dineroDisponible;
                    } else {
                        throw new NoHayStockEnTiendaException("📦 No hay stock de ese item 📦");
                    }
                } else {
                    throw new NoTienesDineroSuficienteException("🪰 No tienes dinero suficiente para comprar 🪰");
                }
            }
        } catch (NoHayStockEnTiendaException e) {
            System.out.println(e.getMessage());
        } catch (NoTienesDineroSuficienteException e) {
            System.out.println(e.getMessage());
        }
        return dineroDisponible;
    }

    public StringBuilder mostrarInventario() {
        StringBuilder inventarioString = new StringBuilder();
        for (Map.Entry<String, Item> entry : inventario.entrySet()) {
            if (entry.getValue().getCantidad() > 0) {
                inventarioString.append(entry.getKey()).append(" ").append(entry.getValue().toString()).append("\n");
            }
        }
        return inventarioString;
    }

    public StringBuilder mostrarInventarioArmeria(PersonajeJugable pj) {
        StringBuilder invetarioArmeria = new StringBuilder();
        for (Map.Entry<String, Item> entry : inventario.entrySet()) {
            if (entry.getValue().getNombre().equals("Espada Larga") && (pj.getClases().equals(E_Clases.GUERRERO))) {
                invetarioArmeria.append(entry.getKey()).append(" ").append(entry.getValue().toString()).append("\n");
            } else if (entry.getValue().getNombre().equals("Baculo de Toth") && (pj.getClases().equals(E_Clases.MAGO))) {
                invetarioArmeria.append(entry.getKey()).append(" ").append(entry.getValue().toString()).append("\n");
            } else if (entry.getValue().getNombre().equals("Arco Largo") && (pj.getClases().equals(E_Clases.ARQUERO))) {
                invetarioArmeria.append(entry.getKey()).append(" ").append(entry.getValue().toString()).append("\n");
            } else if (entry.getValue().getNombre().equals("Maza de Bridas") && (pj.getClases().equals(E_Clases.BARBARO))) {
                invetarioArmeria.append(entry.getKey()).append(" ").append(entry.getValue().toString()).append("\n");
            }
        }
        return invetarioArmeria;
    }

    public StringBuilder mostrarInventarioAlquimia() {
        StringBuilder invetarioAlquimia = new StringBuilder();
        for (Map.Entry<String, Item> entry : inventario.entrySet()) {
            if (entry.getValue().getTipo().equals(E_TipoItem.PUNTOSDEVIDA) || (entry.getValue().getTipo().equals(E_TipoItem.REVIVIR))) {
                invetarioAlquimia.append(entry.getKey()).append(" ").append(entry.getValue().toString()).append("\n");
            }
        }
        return invetarioAlquimia;
    }

    public boolean usarItem(Item item, PersonajeJugable personajeJugable) {
        if (inventario.containsKey(item.getNombre())) {
            if (item.isEsConsumible()) {
                try {
                    if (inventario.get(item.getNombre()).getTipo().equals(E_TipoItem.PUNTOSDEVIDA)) {
                        if (personajeJugable.isVivoOMuerto()) {
                            if (item.getCantidad() > 0) {
                                personajeJugable.setPuntosDeVidaActual(personajeJugable.getPuntosDeVidaMaxima());
                                item.setCantidad(item.getCantidad() - 1);
                                return true;
                            } else throw new NoTieneElItemException("No tiene pociones de vida restantes");
                        } else {
                            throw new PersonajeMuertoException("No puede utilizar una pocion de vida en un personaje que esta muerto");
                        }
                    } else if (inventario.get(item.getNombre()).getTipo().equals(E_TipoItem.REVIVIR)) {
                        if (personajeJugable.isVivoOMuerto()) {
                            return false;
                        } else {
                            if (item.getCantidad() > 0) {
                                personajeJugable.setVivoOMuerto(true);
                                personajeJugable.setPuntosDeVidaActual(personajeJugable.getPuntosDeVidaMaxima());
                                return true;

                            } else throw new NoTieneElItemException("No tiene pociones de resurreccion restantes");
                        }
                    } else if (inventario.get(item.getNombre()).getTipo().equals(E_TipoItem.PUNTOSDEATAQUE)) {
                        if (personajeJugable.isVivoOMuerto()) {

                        } else {
                            throw new PersonajeMuertoException("No se puede utilizar una pocion de poder en un personaje que esta muerto");
                        }
                        return false;
                    }
                } catch (PersonajeMuertoException e) {
                    System.out.println(e.getMessage());
                    return false;
                } catch (NoTieneElItemException e) {
                    System.out.println(e.getMessage());
                    return false;
                }
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return "Inventario{" +
                "inventario=" + mostrarInventario() +
                '}';
    }

    @Override
    public JSONObject toJson() {
        JSONObject inventarioJson = new JSONObject();
        JSONArray contenidoJSon = new JSONArray();
        for (Map.Entry<String, Item> entry : inventario.entrySet()) {
            contenidoJSon.put(entry.getKey());
            contenidoJSon.put(entry.getValue().toJson());
        }
        inventarioJson.put("inventario", contenidoJSon);
        return inventarioJson;
    }

}