package clases;

import java.util.ArrayList;

public class Listas implements IAcciones {

    ArrayList<Object> lista;

    public Listas() {
        lista = new ArrayList<>();
    }

    @Override
    public void agregar(Object o) {
        lista.add((o));
    }

    @Override
    public void remover(int posicion) {
        lista.remove(posicion);
    }

    @Override
    public ArrayList<Object> recorrer() {
        return lista;
    }

}
