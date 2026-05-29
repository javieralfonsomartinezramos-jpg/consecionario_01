package estructuras;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Lista doblemente enlazada generica usada para datos vivos del usuario.
 * Permite recorrer, insertar y eliminar sin depender de ArrayList.
 */
public class ListaDobleEnlazada<T> implements Iterable<T> {

    private Nodo<T> primero;
    private Nodo<T> ultimo;
    private int tamano;

    public void add(T valor) {
        agregarAlFinal(valor);
    }

    public void addAll(Iterable<T> valores) {
        for (T valor : valores) {
            agregarAlFinal(valor);
        }
    }

    public void agregarAlFinal(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);

        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.siguiente = nuevo;
            nuevo.anterior = ultimo;
            ultimo = nuevo;
        }

        tamano++;
    }

    public void agregarAlInicio(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);

        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            nuevo.siguiente = primero;
            primero.anterior = nuevo;
            primero = nuevo;
        }

        tamano++;
    }

    public T removerPrimero() {
        if (primero == null) {
            throw new NoSuchElementException("La lista esta vacia");
        }

        T valor = primero.valor;
        desvincular(primero);
        return valor;
    }

    public T removerUltimo() {
        if (ultimo == null) {
            throw new NoSuchElementException("La lista esta vacia");
        }

        T valor = ultimo.valor;
        desvincular(ultimo);
        return valor;
    }

    public boolean remove(T valor) {
        Nodo<T> actual = primero;

        while (actual != null) {
            if (Objects.equals(actual.valor, valor)) {
                desvincular(actual);
                return true;
            }

            actual = actual.siguiente;
        }

        return false;
    }

    public boolean removeIf(Predicate<T> condicion) {
        boolean eliminado = false;
        Nodo<T> actual = primero;

        while (actual != null) {
            Nodo<T> siguiente = actual.siguiente;

            if (condicion.test(actual.valor)) {
                desvincular(actual);
                eliminado = true;
            }

            actual = siguiente;
        }

        return eliminado;
    }

    public boolean anyMatch(Predicate<T> condicion) {
        for (T valor : this) {
            if (condicion.test(valor)) {
                return true;
            }
        }

        return false;
    }

    public int count(Predicate<T> condicion) {
        int contador = 0;

        for (T valor : this) {
            if (condicion.test(valor)) {
                contador++;
            }
        }

        return contador;
    }

    public T get(int indice) {
        if (indice < 0 || indice >= tamano) {
            throw new IndexOutOfBoundsException("Indice fuera de rango: " + indice);
        }

        Nodo<T> actual;

        if (indice < tamano / 2) {
            actual = primero;
            for (int i = 0; i < indice; i++) {
                actual = actual.siguiente;
            }
        } else {
            actual = ultimo;
            for (int i = tamano - 1; i > indice; i--) {
                actual = actual.anterior;
            }
        }

        return actual.valor;
    }

    public T getPrimero() {
        if (primero == null) {
            throw new NoSuchElementException("La lista esta vacia");
        }

        return primero.valor;
    }

    public T getUltimo() {
        if (ultimo == null) {
            throw new NoSuchElementException("La lista esta vacia");
        }

        return ultimo.valor;
    }

    public int size() {
        return tamano;
    }

    public boolean isEmpty() {
        return tamano == 0;
    }

    public void clear() {
        primero = null;
        ultimo = null;
        tamano = 0;
    }

    public List<T> toList() {
        List<T> valores = new ArrayList<>();

        for (T valor : this) {
            valores.add(valor);
        }

        return valores;
    }

    public List<T> toListInvertida() {
        List<T> valores = new ArrayList<>();
        Nodo<T> actual = ultimo;

        while (actual != null) {
            valores.add(actual.valor);
            actual = actual.anterior;
        }

        return valores;
    }

    private void desvincular(Nodo<T> nodo) {
        Nodo<T> anterior = nodo.anterior;
        Nodo<T> siguiente = nodo.siguiente;

        if (anterior == null) {
            primero = siguiente;
        } else {
            anterior.siguiente = siguiente;
        }

        if (siguiente == null) {
            ultimo = anterior;
        } else {
            siguiente.anterior = anterior;
        }

        tamano--;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private Nodo<T> actual = primero;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                if (actual == null) {
                    throw new NoSuchElementException();
                }

                T valor = actual.valor;
                actual = actual.siguiente;
                return valor;
            }
        };
    }

    private static final class Nodo<T> {

        private final T valor;
        private Nodo<T> anterior;
        private Nodo<T> siguiente;

        private Nodo(T valor) {
            this.valor = valor;
        }
    }
}
