package Modelo;

import Enumeradores.E_TipoItem;
import Interfaces.Jsonable;
import org.json.JSONObject;

public class Item implements Jsonable {
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidad;
    private boolean esConsumible;
    private int estadistica;
    private E_TipoItem tipo;

    public Item(String nombre, String descripcion, double precio, int cantidad, boolean esConsumible, int estadistica, E_TipoItem tipo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.esConsumible = esConsumible;
        this.estadistica = estadistica;
        this.tipo = tipo;
    }

    public E_TipoItem getTipo() {
        return tipo;
    }

    public void setTipo(E_TipoItem tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isEsConsumible() {
        return esConsumible;
    }

    public void setEsConsumible(boolean esConsumible) {
        this.esConsumible = esConsumible;
    }

    public int getEstadistica() {
        return estadistica;
    }

    public void setEstadistica(int estadistica) {
        this.estadistica = estadistica;
    }

    //modificar para que diga el tipo de item
    @Override
    public String toString() {
        return "Item{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                '}';
    }

    @Override
    public JSONObject toJson() {
        JSONObject itemJson = new JSONObject();
        itemJson.put("nombre", nombre);
        itemJson.put("descripcion", descripcion);
        itemJson.put("precio", precio);
        itemJson.put("cantidad", cantidad);
        itemJson.put("esConsumible", esConsumible);
        itemJson.put("estadistica", estadistica);
        itemJson.put("tipo", tipo);
        return itemJson;
    }
}
