package Modelo;

import java.util.ArrayList;

public class GestorGenerico<T> {

    private ArrayList<T> coleccionGenerica;

    public GestorGenerico() {
        coleccionGenerica = new ArrayList<>();
    }

    public boolean agregar(T elemento) {
        if(coleccionGenerica.contains(elemento)) {
            return false;
        }else {
            return coleccionGenerica.add(elemento);
        }
    }

    public T obtener(int indice) {
        if (indice >= 0 && indice < coleccionGenerica.size()) {
            return coleccionGenerica.get(indice);
        } else {
            return null;
        }
    }


    public ArrayList<T> getLista() {
        return coleccionGenerica;
    }
}
