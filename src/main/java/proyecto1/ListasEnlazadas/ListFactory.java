package proyecto1.ListasEnlazadas;

import proyecto1.AbstractFactory.Factory;
import proyecto1.Enemigos.NaveEnemiga;

public class ListFactory<T> implements Factory<Lista<T>> {
    public Lista<T> create(String tipoLista){
        if (tipoLista == null){
            return null;
        }else if ("Simple".equalsIgnoreCase(tipoLista)){
            return new simpleLinkedList<>();
        }else if ("Doble".equalsIgnoreCase(tipoLista)){
            return new doubleLinkedList<>();
        }else if ("Circular".equalsIgnoreCase(tipoLista)){
            return new ListaCircular<>();
        }
        return null;
    }
}
