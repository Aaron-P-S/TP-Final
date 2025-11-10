package Enumeradores;

public enum E_Clases {
    BARBARO(200,75),
    ARQUERO(100,125),
    MAGO(75,150),
    GUERRERO(150,100),;
    final int vidaBase;
    final int dano;
E_Clases(int vidaBase, int dano){
    this.vidaBase = vidaBase;
    this.dano = dano;
}

    public int getVidaBase() {
        return vidaBase;
    }

    public int getDano() {
        return dano;
    }
}
