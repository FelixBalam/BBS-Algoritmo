import java.math.BigInteger;

public class NumAleatorio {
    private BigInteger valor;

    public NumAleatorio(BigInteger valor) {
        this.valor = valor;
    }

    public BigInteger getValor() {
        return valor;
    }

    public double getValorNormalizado(BigInteger m) {
        return valor.doubleValue() / (m.subtract(BigInteger.ONE)).doubleValue();
    }
}
