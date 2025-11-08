package Modelo;

public class Item {
    private String nombre;
    private String descripcion;
    //precio dentro del item?
    private double precio;
    private int cantidad;
    private boolean esConsumible;
    private int estadistica;

    public Item(String nombre, String descripcion, double precio, int cantidad, boolean esConsumible, int estadistica) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.esConsumible = esConsumible;
        this.estadistica = estadistica;
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

    @Override
    public String toString() {
        return "Item{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                ", esConsumible=" + esConsumible +
                ", estadistica=" + estadistica +
                '}';
    }
}
