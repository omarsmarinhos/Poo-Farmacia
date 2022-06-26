package clases;

import java.util.ArrayList;

public class Comprobantes implements IAcciones{
    
    ArrayList<ComprobantePago> comprobantes;

    public Comprobantes() {
        comprobantes = new ArrayList<>();
    }

    @Override
    public void agregar(Object o) {
        comprobantes.add((ComprobantePago) o);
    }

    @Override
    public void remover(int posicion) {
        comprobantes.remove(posicion);
    }

    @Override
    public ArrayList<Object> recorrer() {
        return (ArrayList<Object>)(Object)comprobantes;
    }

}
