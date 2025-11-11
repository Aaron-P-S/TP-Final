package GestoraJson;

import Enumeradores.E_Clases;
import Modelo.Enemigo;
import Modelo.Inventario;
import Modelo.PersonajeJugable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GestorJson {

    public JSONArray pasarDeArrayAJson(ArrayList<Enemigo> enemigos, ArrayList<PersonajeJugable> jugadores, Inventario inventario) {
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
        return jsonArrayTodos;
    }

    public ArrayList<Enemigo> pasarDeJsonAListaEnemigos(JSONArray listaTodos) {
        ArrayList<Enemigo> enemigos = new ArrayList<>();
        for (int i = 0; i < listaTodos.length(); i++) {
            if (listaTodos.getJSONObject(i).has("nivel")) {
                Enemigo enemigo = new Enemigo(listaTodos.getJSONObject(i).getString("nombre"), listaTodos.getJSONObject(i).getInt("nivel"));
                enemigos.add(enemigo);
            }
        }
        return enemigos;
    }
    public ArrayList<PersonajeJugable> pasarDeJsonAParty(JSONArray listaTodos){
        ArrayList<PersonajeJugable> jugadores = new ArrayList<>();
        for (int i = 0; i < listaTodos.length(); i++) {
            if(listaTodos.getJSONObject(i).has("clases")){
                PersonajeJugable personajeJugable= new PersonajeJugable(listaTodos.getJSONObject(i).getString("nombre"),(E_Clases.valueOf(listaTodos.getJSONObject(i).getString("clases")) ));
                jugadores.add(personajeJugable);
            }
        }
        return jugadores;
    }
}
