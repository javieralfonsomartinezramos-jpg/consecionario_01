package estructuras;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

/**
 * Pila LIFO basada en la lista doblemente enlazada del proyecto.
 * Se usa para el historial, mostrando primero lo mas reciente.
 */
public class Pila<T> implements Iterable<T> {

    private final ListaDobleEnlazada<T> datos = new ListaDobleEnlazada<>();

    public void push(T valor) {
        datos.agregarAlFinal(valor);
    }

    public T pop() {
        if (datos.isEmpty()) {
            throw new NoSuchElementException("La pila esta vacia");
        }

        return datos.removerUltimo();
    }

    public T peek() {
        if (datos.isEmpty()) {
            throw new NoSuchElementException("La pila esta vacia");
        }

        return datos.getUltimo();
    }

    public boolean removeIf(Predicate<T> condicion) {
        return datos.removeIf(condicion);
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

    public List<T> toListDesdeCima() {
        return datos.toListInvertida();
    }

    @Override
    public Iterator<T> iterator() {
        return datos.iterator();
    }
}
