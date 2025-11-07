package Modelo;

public class Entidad {

    private String nombre;
    private String apellido;
    private int puntosDeVidaActual;
    private int puntosDeVidaMaxima;


    public Entidad(String nombre) {
        this.nombre = nombre;
        this.puntosDeVidaMaxima = 0;
        this.puntosDeVidaActual = 0;
    }

    public int getPuntosDeVidaActual() { return puntosDeVidaActual; }

    public void setPuntosDeVidaActual(int puntosDeVida) { this.puntosDeVidaActual = puntosDeVida; }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntosDeVidaMaxima() {
        return puntosDeVidaMaxima;
    }

    public void setPuntosDeVidaMaxima(int puntosDeVidaMaxima) {
        this.puntosDeVidaMaxima = puntosDeVidaMaxima;
    }
}
