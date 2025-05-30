package Controlador;

import Modelo.Carta;
import Modelo.MazoJugador;

public class EstrategiaCPU  {
    
    public Carta decidirCarta(Carta cartaJugador, MazoJugador mazoCPU) {
        if (mazoCPU.estaVacio()) return null;
        
        // Simulamos un árbol de decisión básico
        if (cartaJugador.getPoder() > 80) {
            if (cartaJugador.esDebilContra(new Carta("", "Agua", 0))) {
                return buscarCartaMedia(mazoCPU); // Rama izquierda
            } else {
                return buscarCartaFuerte(mazoCPU); // Rama derecha
            }
        } else {
            return buscarCartaFuerte(mazoCPU); // Rama derecha
        }
    }

    private Carta buscarCartaFuerte(MazoJugador mazo) {
        // En una implementación real, buscaríamos la carta más fuerte
        // Aquí simplemente devolvemos la primera carta
        return mazo.verPrimeraCarta();
    }

    private Carta buscarCartaMedia(MazoJugador mazo) {
        // En una implementación real, buscaríamos una carta con poder medio
        // Aquí simplemente devolvemos la primera carta
        return mazo.verPrimeraCarta();
    }
}