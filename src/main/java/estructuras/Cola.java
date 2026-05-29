package estructuras;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Cola FIFO para procesar compras en el mismo orden en que el usuario agrego
 * productos al carrito.
 */
public class Cola<T> implements Iterable<T> {

    private final ListaDobleEnlazada<T> datos = new ListaDobleEnlazada<>();

    public void offer(T valor) {
        datos.agregarAlFinal(valor);
    }

    public T poll() {
        if (datos.isEmpty()) {
            throw new NoSuchElementException("La cola esta vacia");
        }

        return datos.removerPrimero();
    }

    public T peek() {
        if (datos.isEmpty()) {
            throw new NoSuchElementException("La cola esta vacia");
        }

        return datos.getPrimero();
    }

    public boolean isEmpty() {
        return datos.isEmpty();
    }

    public int size() {
        return datos.size();
    }

    public void clear() {
        datos.clear();
    }

    public List<T> toList() {
        return datos.toList();
    }

    @Override
    public Iterator<T> iterator() {
        return datos.iterator();
    }
}
