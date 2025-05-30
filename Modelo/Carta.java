package Modelo;
public class Carta {
    private String nombreCarta;
    private String tipoCarta;
    private int poderCarta;

    // constructor de la carta
    public Carta(String nombreCarta, String tipoCarta, int poderCarta) {
        this.nombreCarta = nombreCarta;
        this.tipoCarta = tipoCarta;
        this.poderCarta = poderCarta;
    }
    //getters de la carta
    public String getNombre() {
        return nombreCarta;
    }
    public String getTipo() {
        return tipoCarta;
    }
    public int getPoder() {
        return poderCarta;
    }

    // metodo para comparar el poder de dos cartas
    public boolean esDebilContra(Carta otra) {
        switch (this.tipoCarta) {
            case "Fuego":
                return otra.getTipo().equals("Agua");
            case "Agua":
                return otra.getTipo().equals("Tierra");
            case "Tierra":
                return otra.getTipo().equals("Aire");
            case "Aire":
                return otra.getTipo().equals("Fuego");
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        return nombreCarta + " (" + tipoCarta + ") - Poder: " + poderCarta;
    }
}