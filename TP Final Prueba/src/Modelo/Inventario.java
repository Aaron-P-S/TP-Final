package Modelo;

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
    public void mostrarInventario(){
        for (Map.Entry<String, Item> entry : inventario.entrySet()) {
            System.out.println("Nombre del item:"+ entry.getKey()+" Descripcion: "+entry.getValue());
        }
    }
}
