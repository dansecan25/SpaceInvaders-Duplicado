package proyecto1.AbstractFactory;

import proyecto1.Hileras.EnemyFactory;
import proyecto1.ListasEnlazadas.ListFactory;
import proyecto1.Trees.TreeFactory;

public class FactoryProvider {
    public static Factory getFactory(String type){
        if("List".equalsIgnoreCase(type)){
            return new ListFactory();
        }
        else if ("Tree".equalsIgnoreCase(type)){
            return new TreeFactory();
        }
        else if ("Enemy".equalsIgnoreCase(type)){
            return new EnemyFactory();
        }
        return null;
    }
}
