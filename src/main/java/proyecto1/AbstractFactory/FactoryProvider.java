package proyecto1.AbstractFactory;

import proyecto1.Enemigos.NaveEnemiga;
import proyecto1.ListasEnlazadas.ListFactory;
import proyecto1.Trees.TreeFactory;

public class FactoryProvider {
    public static Factory getFactory(String type){
        if("List".equalsIgnoreCase(type)){
            return new ListFactory<NaveEnemiga>();
        }
        else if ("Tree".equalsIgnoreCase(type)){
            return new TreeFactory();
        }
        return null;
    }
}
