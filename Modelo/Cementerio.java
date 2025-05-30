package Modelo;
public class Cementerio {
    private Carta[] cartasDerrotadas; // arreglo para guardar las cartas derrotadas
    private int cantidad;

    public Cementerio() {
        cartasDerrotadas = new Carta[50]; // tamaño máximo arbitrario
        cantidad = 0;
    }

    // Agregamos una carta derrotada al cementerio
    public void agregarCartaDerrotada(Carta carta) {
        if (cantidad < cartasDerrotadas.length) {
            cartasDerrotadas[cantidad++] = carta;
        }
    }

    // Devuelve la última carta derrotada
    public Carta verUltimaDerrota() {
        if (cantidad == 0) return null;
        return cartasDerrotadas[cantidad - 1];
    }

    // Devuelve todas las cartas derrotadas como un arreglo
    public Carta[] getCartasDerrotadas() {
        Carta[] resultado = new Carta[cantidad];
        for (int i = 0; i < cantidad; i++) {
            resultado[i] = cartasDerrotadas[i];
        }
        return resultado;
    }
}