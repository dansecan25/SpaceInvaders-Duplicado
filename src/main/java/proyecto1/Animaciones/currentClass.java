package proyecto1.Animaciones;

import proyecto1.Enemigos.NaveEnemiga;
import proyecto1.ListasEnlazadas.ListFactory;
import proyecto1.ListasEnlazadas.Lista;
import proyecto1.ListasEnlazadas.doubleLinkedList;
import proyecto1.Trees.Tree;

/**
 * Clase currentClass.
 */
public class currentClass {
    private static String claseActual = "";
    private static final ListFactory<NaveEnemiga> listFactory = new ListFactory<>();
    private static Lista<NaveEnemiga> listaActual;
    private static int nivel=1;
    private static Tree treecito;
    private static int deathID = -1;
    private static final Lista PosList = new doubleLinkedList();
    private static boolean StopMovement;
    /**
     * Setter para indicar cual es la hilera actual.
     * @param claseActual the clase actual
     */
    public static void setClass(String claseActual,Lista<NaveEnemiga> lista, Tree tree) { System.out.println("Esto deberia correr primero");currentClass.claseActual = claseActual;listaActual = lista;treecito = tree; }
    /**
     * Retorna la lista actual
     * @return listaActual : Lista<NaveEnemiga>
     */

    public static Lista<NaveEnemiga> getLista(){
        return listaActual;
    }

    /**
     * Return the tree that is stored on the class for universal access
     * @return any Tree that is stored
     */
    public static Tree getTreecito() {return treecito;}

    /**
     * Retorna el tipo de hilera actual
     * @return claseActual : String
     */
    public static String getClase(){ return claseActual; }

    /**
     * Reordena la lista
     * @param indice: int
     */
    public static void reordenar(int indice){ while(indice < listaActual.tamanoLista()-1){ NaveEnemiga data = listaActual.obtenerDato(indice + 1);if (data != null){ data.setPosicionLista(indice); }indice++; } }

    /**
     * Set de la variable que indica el nivel actual
      * @param niv: int
     */
    public static void setNivel(int niv){ nivel = niv; }

    /**
     * Retorna el nivel actual
     * @return nivel: int
     */
    public int getNivel(){
        return nivel;
    }

    /**
     * Sets the id of the deceased ship
     * @param ID int
     */
    public static void setDeaths(int ID){
        deathID=ID;
    }

    /**
     * Return the id of the deceased ship
     * @return deathID an int with the ID of the deceased ship
     */
    public static int getDeathID(){ return deathID; }

    /**
     * Stores the x and y were the ship just died
     * @param x int
     * @param y int
     */
    public static void setPosDeceased(int x, int y){
        PosList.agregarUltimo(x);
        PosList.agregarUltimo(y);
    }

    /**
     * looks for the x and y coordinate that are stored in a double linked list
     * @param pos int
     * @return the position according the index value entered
     */
    public static int getPosDeceased(int pos){
        if(pos==0){
            return (int) PosList.obtenerDato(pos);
        }
        if(pos==1){
            return (int) PosList.obtenerDato(pos);
        } return 0;

    }

    /**
     * Sets the variable to indicate the animation to stop if dead
     * @param stop boolean, true if it should stop, false if it should move
     */
    public static void setStopMovement(boolean stop){StopMovement=stop;}

    /**
     * Gets the boolean for stoping the movement
     * @return true if the animation should stop, false if the animation execute
     */
    public static boolean getStopMovement(){return StopMovement;}
}