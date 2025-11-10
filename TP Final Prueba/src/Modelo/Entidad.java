package Modelo;

public class Entidad {

    private String nombre;
    private int puntosDeVidaActual;
    private int puntosDeVidaMaxima;
    private boolean vivoOMuerto;


    public Entidad(String nombre) {
        this.nombre = nombre;
        this.puntosDeVidaMaxima = 0;
        this.puntosDeVidaActual = 0;
        this.vivoOMuerto = true;
    }

    public int getPuntosDeVidaActual() { return puntosDeVidaActual; }

    public int setPuntosDeVidaActual(int puntosDeVida) {
        if(puntosDeVida > 0) {
            this.puntosDeVidaActual = puntosDeVida;
        }else {
            setVivoOMuerto(false);
            puntosDeVidaActual = 0;
        }
        return puntosDeVida;
    }

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
        setPuntosDeVidaActual(puntosDeVidaMaxima);
    }

    public boolean isVivoOMuerto() {
        return vivoOMuerto;
    }

    public void setVivoOMuerto(boolean vivoOMuerto) {
        this.vivoOMuerto = vivoOMuerto;
    }

    @Override
    public String toString() {
        return "Entidad{" +
                ", nombre='" + nombre + '\'' +
                ", puntosDeVidaActual=" + puntosDeVidaActual +
                ", puntosDeVidaMaxima=" + puntosDeVidaMaxima +
                ", vivoOMuerto=" + vivoOMuerto +
                '}';
    }
}
