package Modelo;
import java.util.Scanner;

public class Jugador {
    private String nombreJugador;
    private MazoJugador mazo;
    private Cementerio cementerio;// Cementerio para cartas derrotadas

    // Constructor del jugador
    public Jugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
        this.mazo = new MazoJugador();
        this.cementerio = new Cementerio();
    }

    //getters de los atributos
    public String getNombre() {
        return nombreJugador;
    }
    public MazoJugador getMazo() {
        return mazo;
    }
    public Cementerio getCementerio() {
        return cementerio;
    }

    // metodo para jugar una carta
    public Carta jugarCarta(Scanner scanner, boolean esHumano) {
        if (!esHumano) {
            // Si no es humano (es la CPU), simplemente saca la primera carta del mazo
            return mazo.sacarCarta();
        }
        int cantidadCartas = mazo.tamaño();
        if (cantidadCartas == 0) return null;

        // Copiamos las cartas a un arreglo simple
        Carta[] cartas = new Carta[cantidadCartas];
        for (int i = 0; i < cantidadCartas; i++) {
            cartas[i] = mazo.sacarCarta();
        }

        int opcion = -1;
        while (opcion < 1 || opcion > cantidadCartas) {
            System.out.print("\nIngresa el número de la carta: ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }
        }
        Carta seleccionada = cartas[opcion - 1];

        // Devolvemos las cartas no seleccionadas al mazo
        for (int i = 0; i < cantidadCartas; i++) {
            if (i != (opcion - 1)) {
                mazo.agregarCarta(cartas[i]);
            }
        }
        return seleccionada;
    }

    // metodo para agregar una carta al mazo del jugador
    public void agregarCartaAMazo(Carta carta) {
        mazo.agregarCarta(carta);
    }

    // metodo para agregar una carta al cementerio
    public void agregarCartaACementerio(Carta carta) {
        cementerio.agregarCartaDerrotada(carta);
    }

    // mostramos el mazo del jugador ac
    public void mostrarMazo() {
        System.out.println("Mazo de " + nombreJugador + ":");
        MazoJugador temp = new MazoJugador();
        while (!mazo.estaVacio()) {
            Carta carta = mazo.sacarCarta();
            System.out.println("  " + carta);
            temp.agregarCarta(carta);
        }
        this.mazo = temp;
    }
}