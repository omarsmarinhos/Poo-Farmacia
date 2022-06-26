package clases;

import java.util.ArrayList;

public interface IAcciones {
    
    public abstract void agregar(Object o);
    public abstract void remover(int posicion);
    public ArrayList<Object> recorrer();
    
     
}
