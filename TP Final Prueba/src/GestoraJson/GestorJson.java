package GestoraJson;

import Enumeradores.E_Clases;
import Enumeradores.E_TipoItem;
import Modelo.Enemigo;
import Modelo.Inventario;
import Modelo.Item;
import Modelo.PersonajeJugable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GestorJson {

    public JSONArray pasarDeArrayAJson(ArrayList<Enemigo> enemigos, ArrayList<PersonajeJugable> jugadores, Inventario inventario, int dinero, int nivel) {
        JSONArray jsonArrayTodos = new JSONArray();
        for (Enemigo enemigo : enemigos) {
            JSONObject enemigoJson = enemigo.toJson();
            jsonArrayTodos.put(enemigoJson);
        }
        for (PersonajeJugable personajeJugable : jugadores) {
            JSONObject personajeJson = personajeJugable.toJson();
            jsonArrayTodos.put(personajeJson);
        }
        JSONObject inventarioJson = inventario.toJson();
        jsonArrayTodos.put(inventarioJson);
        JSONObject dineroJson = new JSONObject();
        dineroJson.put("dinero",dinero);
        jsonArrayTodos.put(dineroJson);
        JSONObject nivelJson = new JSONObject();
        nivelJson.put("nivelPartida",nivel);
        jsonArrayTodos.put(nivelJson);
        return jsonArrayTodos;
    }

    public ArrayList<Enemigo> pasarDeJsonAListaEnemigos(JSONArray listaTodos) {
        ArrayList<Enemigo> enemigos = new ArrayList<>();
        for (int i = 0; i < listaTodos.length(); i++) {
            if (listaTodos.getJSONObject(i).has("nivel")) {
                Enemigo enemigo = new Enemigo(listaTodos.getJSONObject(i).getString("nombre"), listaTodos.getJSONObject(i).getInt("nivel"), listaTodos.getJSONObject(i).getInt("puntosDeVidaMaxima"), listaTodos.getJSONObject(i).getInt("puntosDeVidaActual"));
                enemigos.add(enemigo);
            }
        }
        return enemigos;
    }

    public ArrayList<PersonajeJugable> pasarDeJsonAParty(JSONArray listaTodos) {
        ArrayList<PersonajeJugable> jugadores = new ArrayList<>();
        for (int i = 0; i < listaTodos.length(); i++) {
            if (listaTodos.getJSONObject(i).has("clases")) {
                PersonajeJugable personajeJugable = new PersonajeJugable(listaTodos.getJSONObject(i).getString("nombre"), (E_Clases.valueOf(listaTodos.getJSONObject(i).getString("clases"))));
                Inventario inventarioPj=pasarDeJsonAInventario(listaTodos.getJSONObject(i).getJSONObject("inventario"));
                personajeJugable.agregarInventario(inventarioPj);
                jugadores.add(personajeJugable);
            }
        }
        return jugadores;
    }

    public Inventario pasarDeJsonAInventario(JSONObject inventarioJson) {
        Inventario inv = new Inventario();

        JSONObject contenido = inventarioJson.getJSONObject("inventario");

        for (String key : contenido.keySet()) {
            JSONObject itemJson = contenido.getJSONObject(key);
            Item item = new Item(itemJson.getString("nombre"),itemJson.getString("descripcion"),itemJson.getDouble("precio"),itemJson.getInt("cantidad"),itemJson.getBoolean("esConsumible"),itemJson.getInt("estadistica"),E_TipoItem.valueOf(itemJson.getString("tipo")));
            inv.agregarItem(key, item);
        }

        return inv;
    }
    public Inventario pasarDeJsonAInventario(JSONArray listaTodos) {
        for (int i = 0; i < listaTodos.length(); i++) {
            JSONObject obj = listaTodos.getJSONObject(i);
            if (obj.has("inventario")&&!obj.has("nombre")) {
                return pasarDeJsonAInventario(obj); // llama al método existente
            }
        }
        return new Inventario(); // vacío si no se encontró
    }

    public int pasarDeJsonAdinero(JSONArray listatodos){
        int dinero=0;
        for (int i = 0; i < listatodos.length(); i++) {
            if(listatodos.getJSONObject(i).has("dinero")){
                dinero=listatodos.getJSONObject(i).getInt("dinero");
            }
        }
        return dinero;
    }
    public int pasarDeJsonAnivel(JSONArray listatodos){
        int nivel=0;
        for (int i = 0; i < listatodos.length(); i++) {
            if(listatodos.getJSONObject(i).has("nivelPartida")){
                nivel=listatodos.getJSONObject(i).getInt("nivelPartida");
            }
        }
        return nivel;
    }
    }

