import java.util.Scanner;

import Controlador.Registro;
import Modelo.Baraja;
import Modelo.Carta;
import Modelo.Jugador;
import Modelo.MazoJugador;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   🃏 Bienvenido a Batalla de Poderes 🃏    ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        System.out.print("👤 Ingrese su nombre: ");
        String nombreJugador = scanner.nextLine();

        // creamos la baraja principal
        Baraja barajaPrincipal = crearBaraja();
                                            
        // creamos los jugadores
        Jugador jugador = new Jugador(nombreJugador);
        Jugador cpu = new Jugador("MAQUINA DE GUERRA");

        // repartimos cartas a cada jugador
        System.out.println("\n🔄 Se reparten cartas...");
        repartirCartas(barajaPrincipal, jugador, cpu, 5);

        System.out.println("🗃️ Tu mazo tiene 10 cartas.");
        mostrarMazoNumerado(jugador);
        System.out.println("\n🤖 El oponente CPU también tiene 10 cartas.");

        Registro registro = new Registro();
        registro.registrarJugador(jugador.getNombre());
        registro.registrarJugador(cpu.getNombre());

        // variables para contar rondas ganadas
        int rondasGanadasJugador = 0;
        int rondasGanadasCPU = 0;

        // bucle de juego por 5 rondas
        for (int ronda = 1; ronda <= 5; ronda++) {
            System.out.println("\n═══════════════ 🏁 Ronda " + ronda + " 🏁 ═══════════════");

            // mostramos el mazo actualizado del jugador antes de elegir carta
            mostrarMazoNumerado(jugador);

            // El jugador elige que carta jugar
            Carta cartaJugador = jugador.jugarCarta(scanner, true);
            System.out.println("🧑‍🎤 " + jugador.getNombre() + " juega: \"" + cartaJugador.getNombre() + "\" = Poder: " + cartaJugador.getPoder());
            registro.agregarCartaJugada(jugador.getNombre(), cartaJugador);

            // La CPU juega una carta autoaicamente
            Carta cartaCPU = cpu.jugarCarta(scanner, false);
            System.out.println("🤖 " + cpu.getNombre() + " juega: \"" + cartaCPU.getNombre() + "\" = Poder: " + cartaCPU.getPoder());
            registro.agregarCartaJugada(cpu.getNombre(), cartaCPU);

            // determinamos el ganador de la ronda
            int resultado = determinarGanador(cartaJugador, cartaCPU);

            // procesa el resultado de la ronda
            if (resultado > 0) {
                // El jugador gana la ronda
                System.out.println("\n🏆 Ganador(a) de la ronda: " + jugador.getNombre() + " 🏆");
                jugador.agregarCartaAMazo(cartaJugador); // Recupera su carta
                jugador.agregarCartaAMazo(cartaCPU);      // Gana la carta de la CPU
                cpu.agregarCartaACementerio(cartaCPU);    // CPU pierde su carta
                registro.agregarCartaGanada(jugador.getNombre(), cartaCPU);
                registro.agregarRondaGanada(jugador.getNombre());
                rondasGanadasJugador++;
            } else if (resultado < 0) {
                // La CPU gana la ronda
                System.out.println("\n🏆 Ganador(a) de la ronda: " + cpu.getNombre() + " 🏆");
                cpu.agregarCartaAMazo(cartaCPU);          // Recupera su carta
                cpu.agregarCartaAMazo(cartaJugador);      // Gana la carta del jugador
                jugador.agregarCartaACementerio(cartaJugador); // Jugador pierde su carta
                registro.agregarCartaGanada(cpu.getNombre(), cartaJugador);
                registro.agregarRondaGanada(cpu.getNombre());
                rondasGanadasCPU++;
            } else {
                // Empateambos recuperan sus cartas
                System.out.println("\n🤝 ¡Empate! Ambas cartas vuelven a sus mazos.");
                jugador.agregarCartaAMazo(cartaJugador);
                cpu.agregarCartaAMazo(cartaCPU);
            }
        }

        // mostramos las cartas derrotadas del jugador humano al final del juego
        System.out.println("\n💀 --- Cartas derrotadas de " + jugador.getNombre() + " --- 💀");
        Carta[] derrotadas = jugador.getCementerio().getCartasDerrotadas();
        for (int i = 0; i < derrotadas.length; i++) {
            System.out.println("   🗑️ \"" + derrotadas[i].getNombre() + "\"");
        }

        // mostramos las estadísticas finales del juego
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║        📊 Estadísticas Finales      ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("🧑‍🎤 " + jugador.getNombre() + ": " + rondasGanadasJugador + " rondas ganadas");
        System.out.println("🤖 " + cpu.getNombre() + ": " + rondasGanadasCPU + " rondas ganadas");

        // idnicamos quién gano la partida
        if (rondasGanadasJugador > rondasGanadasCPU) {
            System.out.println("\n🎉 ¡" + jugador.getNombre() + " gana la partida! 🎉");
        } else if (rondasGanadasCPU > rondasGanadasJugador) {
            System.out.println("\n💀 ¡" + cpu.getNombre() + " gana la partida! 💀");
        } else {
            System.out.println("\n🤝 ¡Empate en la partida!");
        }

        // mostramos las estadísticas de juego
        registro.mostrarEstadisticas();
    }

    // creamos la baraja con cartas de diferentes tipos y poderes
    public static Baraja crearBaraja() {
        Baraja baraja = new Baraja();
        
        // Cartas de Fuego (10)
        baraja.agregarCarta(new Carta("🐉 Dragón Infernal", "Fuego", 90));
        baraja.agregarCarta(new Carta("🔥 Fénix Renaciente", "Fuego", 78));
        baraja.agregarCarta(new Carta("🦎 Salamandra de Lava", "Fuego", 80));
        baraja.agregarCarta(new Carta("👹 Demonio Flamígero", "Fuego", 85));
        baraja.agregarCarta(new Carta("🦂 Escorpión Ígneo", "Fuego", 65));
        baraja.agregarCarta(new Carta("🗡️ Espadachín Ardiente", "Fuego", 70));
        baraja.agregarCarta(new Carta("🔥 Espíritu del Volcán", "Fuego", 75));
        baraja.agregarCarta(new Carta("🦁 León de Fuego", "Fuego", 72));
        baraja.agregarCarta(new Carta("🧞‍♂️ Efrit del Desierto", "Fuego", 82));
        baraja.agregarCarta(new Carta("🎆 Fuego Fatuo", "Fuego", 40));

        // Cartas de Agua (10)
        baraja.agregarCarta(new Carta("🦑 Kraken Abisal", "Agua", 85));
        baraja.agregarCarta(new Carta("🧜‍♀️ Sirena Encantadora", "Agua", 62));
        baraja.agregarCarta(new Carta("🧜‍♂️ Tritón del Océano", "Agua", 60));
        baraja.agregarCarta(new Carta("🐋 Leviatán", "Agua", 88));
        baraja.agregarCarta(new Carta("🐬 Delfín Mágico", "Agua", 55));
        baraja.agregarCarta(new Carta("🧊 Yeti Polar", "Agua", 68));
        baraja.agregarCarta(new Carta("🌊 Nereida", "Agua", 70));
        baraja.agregarCarta(new Carta("🐚 Ninfa del Mar", "Agua", 58));
        baraja.agregarCarta(new Carta("🧊 Serpiente Marina", "Agua", 50));
        baraja.agregarCarta(new Carta("💧 Gota de Tormenta", "Agua", 35));

        // Cartas de Tierra (10)
        baraja.agregarCarta(new Carta("🗿 Gólem de Granito", "Tierra", 66));
        baraja.agregarCarta(new Carta("🌳 Ent Ancestral", "Tierra", 55));
        baraja.agregarCarta(new Carta("🧙‍♂️ Gnomo de las Rocas", "Tierra", 30));
        baraja.agregarCarta(new Carta("🦏 Troll de Montaña", "Tierra", 82));
        baraja.agregarCarta(new Carta("🦡 Tejón Excavador", "Tierra", 45));
        baraja.agregarCarta(new Carta("🏺 Guardián de Cristal", "Tierra", 58));
        baraja.agregarCarta(new Carta("🕳️ Gusano de Arena", "Tierra", 50));
        baraja.agregarCarta(new Carta("🌵 Cactro Gigante", "Tierra", 42));
        baraja.agregarCarta(new Carta("🪨 Basilisco Petrificante", "Tierra", 75));
        baraja.agregarCarta(new Carta("🍄 Hongo Venenoso", "Tierra", 38));

        // Cartas de Aire (10)
        baraja.agregarCarta(new Carta("🦄 Pegaso Alado", "Aire", 70));
        baraja.agregarCarta(new Carta("🦅 Harpía Tempestuosa", "Aire", 50));
        baraja.agregarCarta(new Carta("🌬️ Silfo del Viento", "Aire", 25));
        baraja.agregarCarta(new Carta("☁️ Dragón de Tormenta", "Aire", 88));
        baraja.agregarCarta(new Carta("👼 Ángel Celestial", "Aire", 95));
        baraja.agregarCarta(new Carta("🦉 Búho Sabio", "Aire", 60));
        baraja.agregarCarta(new Carta("🌪️ Espíritu del Torbellino", "Aire", 72));
        baraja.agregarCarta(new Carta("🪶 Ave Fénix Blanca", "Aire", 78));
        baraja.agregarCarta(new Carta("🦋 Mariposa de Cristal", "Aire", 40));
        baraja.agregarCarta(new Carta("⚡ Rayo Volador", "Aire", 65));

        return baraja;
    }
    
    // repartimso las cartas a los jugadores
    private static void repartirCartas(Baraja baraja, Jugador jugador1, Jugador jugador2, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            Carta carta1 = baraja.obtenerCartaAleatoria();
            Carta carta2 = baraja.obtenerCartaAleatoria();
            if (carta1 != null) jugador1.agregarCartaAMazo(carta1);
            if (carta2 != null) jugador2.agregarCartaAMazo(carta2);
        }
    }

    // metoo para determinar el ganador de una ronda
    private static int determinarGanador(Carta carta1, Carta carta2) {
        if (carta1.esDebilContra(carta2)) {
            return -1; // carta1 pierde
        } else if (carta2.esDebilContra(carta1)) {
            return 1; // carta1 gana
        }
        return Integer.compare(carta1.getPoder(), carta2.getPoder()); // compara por poder
    }

    //metodo para mostrar el mazo de cartas del jugador
    private static void mostrarMazoNumerado(Jugador jugador) {
        System.out.println("\n📦 Mazo de " + jugador.getNombre() + ":");
        MazoJugador temp = new MazoJugador();
        int i = 1;
        while (!jugador.getMazo().estaVacio()) {
            Carta carta = jugador.getMazo().sacarCarta();
            System.out.println("   " + i + "️⃣  " + carta);
            temp.agregarCarta(carta);
            i++;
        }
        // Restaura el mazo original después de mostrarlo
        while (!temp.estaVacio()) {
            jugador.getMazo().agregarCarta(temp.sacarCarta());
        }
    }
}