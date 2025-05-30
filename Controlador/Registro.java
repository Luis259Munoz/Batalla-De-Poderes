package Controlador;
import java.util.HashMap;

import Modelo.Carta;

public class Registro {
    private HashMap<String, JugadorInfo> registro;

    // constructor del registro
    public Registro() {
        registro = new HashMap<>();
    }

    // metodo para registrar un nuevo jugador
    public void registrarJugador(String nombre) {
        registro.put(nombre, new JugadorInfo());
    }

    // semams una carta jugada al jugador
    public void agregarCartaJugada(String nombreJugador, Carta carta) {
        registro.get(nombreJugador).cartasJugadas++;
    }

    // metodo para agregar una carta ganada
    public void agregarCartaGanada(String nombreJugador, Carta carta) {
        JugadorInfo info = registro.get(nombreJugador);
        info.cartasGanadas++;
        info.poderTotal += carta.getPoder();
    }

    // sumamos una ronda ganada al jugador
    public void agregarRondaGanada(String nombreJugador) {
        registro.get(nombreJugador).rondasGanadas++;
    }

    // meotod para mostrar las estadisticas de los jugadores
    public void mostrarEstadisticas() {
        System.out.println("\n=== ESTADÍSTICAS DEL JUEGO ===");
        registro.forEach((nombre, info) -> {
            System.out.println("Jugador: " + nombre);
            System.out.println("  Cartas jugadas: " + info.cartasJugadas);
            System.out.println("  Cartas ganadas: " + info.cartasGanadas);
            System.out.println("  Poder total acumulado: " + info.poderTotal);
            System.out.println("  Rondas ganadas: " + info.rondasGanadas);
            System.out.println();
        });
    }

    // clase interna para guardar la información de cada jugador
    private class JugadorInfo {
        int cartasJugadas;
        int cartasGanadas;
        int poderTotal;
        int rondasGanadas;

        JugadorInfo() {
            cartasJugadas = 0;
            cartasGanadas = 0;
            poderTotal = 0;
            rondasGanadas = 0;
        }
    }
}