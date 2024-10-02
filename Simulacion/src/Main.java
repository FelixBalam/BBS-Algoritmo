import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el valor de p (número primo): ");
        BigInteger pValor = scanner.nextBigInteger();
        System.out.print("Ingrese el valor de q (número primo): ");
        BigInteger qValor = scanner.nextBigInteger();
        System.out.print("Ingrese la semilla inicial (menor que M): ");
        BigInteger seedValor = scanner.nextBigInteger();
        BigInteger mValor = pValor.multiply(qValor);

        if (seedValor.compareTo(BigInteger.ZERO) < 0 || seedValor.compareTo(mValor) >= 0) {
            System.out.println("Error: La semilla debe ser mayor o igual a 0 y menor que M.");
            return;
        }

        Parametro<BigInteger> p = new Parametro<>("p", pValor, true, () -> null);
        Parametro<BigInteger> q = new Parametro<>("q", qValor, true, () -> null);
        Parametro<BigInteger> seed = new Parametro<>("seed", seedValor, true, () -> null);
        Parametro<BigInteger> m = new Parametro<>("M", mValor, true, () -> null);

        List<Parametro<?>> parametros = new ArrayList<>();
        parametros.add(p);
        parametros.add(q);
        parametros.add(seed);
        parametros.add(m);

        Algoritmo algoritmo = new Algoritmo("Blum Blum Shub", "BBS01", parametros);

        algoritmo.generarNumAleatorio(seedValor);
        algoritmo.imprimirTabla(seedValor);
        algoritmo.exportarNumeros();

        System.out.println("\nLos números generados se han exportado al archivo 'numeros.txt'.");
    }
}
