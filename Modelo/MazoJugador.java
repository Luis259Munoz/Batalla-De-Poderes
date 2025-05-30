package Modelo;
public class MazoJugador {
    private Carta[] mazo; // arreglo para guardar las cartas del mazo
    private int cantidad;

    // constructor del mazo
    public MazoJugador() {
        mazo = new Carta[50]; // tamaño máximo arbitrario
        cantidad = 0;
    }

    // metodo para agregar una carta al final del mazo
    public void agregarCarta(Carta carta) {
        if (cantidad < mazo.length) {
            mazo[cantidad++] = carta;
        }
    }

    // metodo para sacar una carta del mazo (primera carta)
    public Carta sacarCarta() {
        if (cantidad == 0) return null;
        Carta carta = mazo[0];
        // desplazamos todas las cartas una posición a la izquierda
        for (int i = 1; i < cantidad; i++) {
            mazo[i - 1] = mazo[i];
        }
        cantidad--;
        return carta;
    }

    // metodo para ver la primera carta del mazo
    public Carta verPrimeraCarta() {
        if (cantidad == 0) return null;
        return mazo[0];
    }

    // metodo para obtener el tamaño del mazo
    public int tamaño() {
        return cantidad;
    }

    // Indica si el mazo está vacío
    public boolean estaVacio() {
        return cantidad == 0;
    }
}
