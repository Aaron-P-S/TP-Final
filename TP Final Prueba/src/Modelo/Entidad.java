package Modelo;

public abstract class Entidad {

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

    public int getPuntosDeVidaActual() {
        return puntosDeVidaActual;
    }

    public void setPuntosDeVidaActual(int puntosDeVida) {
        int puntosDeVidaAux = getPuntosDeVidaActual() + puntosDeVida;
        if (puntosDeVidaAux < puntosDeVidaMaxima) {
            if (puntosDeVidaAux > 0) {
                this.puntosDeVidaActual = puntosDeVidaAux;
            } else {
                setVivoOMuerto(false);
                puntosDeVidaActual = 0;
            }
        } else puntosDeVidaActual = puntosDeVidaMaxima;
    }
    public void setPuntosDeVidaActualAux(int puntosDeVida) {
        this.puntosDeVidaActual = puntosDeVida;
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
        if(getPuntosDeVidaActual()==0&&isVivoOMuerto()) {
            setPuntosDeVidaActual(puntosDeVidaMaxima);
        }
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
