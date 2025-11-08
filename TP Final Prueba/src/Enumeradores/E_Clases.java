package Enumeradores;

public enum E_Clases {
    BARBARO(200),
    ARQUERO(100),
    MAGO(75),
    GUERRERO(150),;
    final int vidaBase;
E_Clases(int vidaBase){
    this.vidaBase = vidaBase;
}

    public int getVidaBase() {
        return vidaBase;
    }
}
