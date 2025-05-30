package Modelo;
public class Baraja {
    private Nodo cabeza; //nodo incial
    private int tamaño; 

    private class Nodo {
        Carta carta;
        Nodo siguiente;
        // constructor del nodo
        Nodo(Carta carta) {
            // iniciamos el nodo con la carta dada y el siguiente nodo como null
            this.carta = carta;
            this.siguiente = null;
        }
    }
    // constructor del baraja
    public Baraja() {
        cabeza = null;
        tamaño = 0;
    }
    // metodo para agregar una carta al final de la baraja
    public void agregarCarta(Carta carta) {
        Nodo nuevoNodo = new Nodo(carta); 
        // si la baraja está vacía, el nuevo nodo se convierte en la cabeza
        if (cabeza == null) {
            cabeza = nuevoNodo; 
        } else {
            Nodo actual = cabeza;
            // buscamos el ultimo nodo de la baraja
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamaño++;
    }
    // metodo par dar una carta de la baraja
    public Carta obtenerCartaAleatoria() {
        if (cabeza == null) 
            return null;
        int indice = (int) (Math.random() * tamaño);
        Nodo actual = cabeza;
        // recorremos la lista hasta el índice aleatorio
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        return actual.carta;
    }

    // Devuelve el número de cartas en la baraja
    public int getTamaño() {
        return tamaño;
    }
}