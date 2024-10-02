import java.util.function.Supplier;

public class Parametro<T> {
    private String nombre;
    private T valor;
    private boolean esValido;
    private Supplier<Void> accion;

    public Parametro(String nombre, T valor, boolean esValido, Supplier<Void> accion) {
        this.nombre = nombre;
        this.valor = valor;
        this.esValido = esValido;
        this.accion = accion;
    }

    public T validar() {
        if (esValido) {
            return valor;
        }
        accion.get();
        return null;
    }

    public T getValor() {
        return valor;
    }
}
