package Modelo;

public class Entidad {

    private String nombre;
    private String apellido;
    private int puntosDeVida;



    public Entidad(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.puntosDeVida = 0;
    }

    public int getPuntosDeVida() {return puntosDeVida;}

    public void setPuntosDeVida(int puntosDeVida) {this.puntosDeVida = puntosDeVida;}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


}
