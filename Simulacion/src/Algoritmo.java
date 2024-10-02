import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Algoritmo {
    private String nombre;
    private String codigo;
    private List<Parametro<?>> parametros;
    private List<NumAleatorio> numeros;

    public Algoritmo(String nombre, String codigo, List<Parametro<?>> parametros) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.parametros = parametros;
        this.numeros = new ArrayList<>();
    }

    public BigInteger generarNumAleatorio(BigInteger seed) {
        BigInteger p = (BigInteger) parametros.get(0).validar();
        BigInteger q = (BigInteger) parametros.get(1).validar();
        BigInteger m = (BigInteger) parametros.get(3).validar();

        BigInteger x = seed.multiply(seed).mod(m);

        if (x.compareTo(BigInteger.ZERO) < 0) {
            x = x.abs();
        }

        double numeroAleatorio = x.doubleValue() / (m.subtract(BigInteger.ONE)).doubleValue();
        numeros.add(new NumAleatorio(x));

        return x;
    }

    public File exportarNumeros() {
        File archivo = new File("numeros.txt");
        try (FileWriter writer = new FileWriter(archivo)) {
            for (NumAleatorio num : numeros) {
                writer.write(num.getValor() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return archivo;
    }

    public void imprimirTabla(BigInteger initialSeed) {
        BigInteger p = (BigInteger) parametros.get(0).getValor();
        BigInteger q = (BigInteger) parametros.get(1).getValor();
        BigInteger m = (BigInteger) parametros.get(3).getValor();

        System.out.println("\nTabla de Resultados");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-10s %-10s %-10s %-20s %-20s %-20s %-20s %-20s%n",
                "Iteración", "p", "q", "Semilla", "xi", "xi^2", "xi mod m", "xi / (m - 1)");
        System.out.println("--------------------------------------------------------------------------------");

        BigInteger seed = initialSeed;

        for (int i = 0; i < 10; i++) {
            BigInteger x = generarNumAleatorio(seed);
            BigInteger xiCuadrado = x.multiply(x);
            BigInteger xiModM = x.mod(m);

            System.out.printf("%-10d %-10d %-10d %-20d %-20s %-20s %-20s %-20.6f%n",
                    (i + 1), p, q, seed, x, xiCuadrado, xiModM, numeros.get(i).getValorNormalizado(m));
            seed = x;
        }
    }
}
