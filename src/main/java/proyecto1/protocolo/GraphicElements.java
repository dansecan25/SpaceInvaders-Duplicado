package proyecto1.protocolo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GraphicElements {
    private static List<ImageWithProperties> elements = new ArrayList<>();
    private static int size = 0;

    public static ImageWithProperties createElement(String id, String imageType) {
        ImageWithProperties currentImage = new ImageWithProperties(id, imageType);
        return currentImage;
    }


    public static void addElement(ImageWithProperties element) {
        elements.add(element);
        size++;
    }

    public static void clearElements() {
            for (int i = 0; i < size; i++){
                elements.get(i).removeFromGameWindow();
                elements.set(i, null);
            }

            size = 0;
    }

    public static void removeElement(String ID) {
        int i = findElementIndex(ID);
        elements.get(i).removeFromGameWindow();
        elements.set(i, null);

    }

    public static ImageWithProperties findElement(String ID) {
        for (int i = 0; i < size; i++){
            String elementId = elements.get(i).getId();
            if (ID.equals(elementId)) {
                return elements.get(i);
            }
        }
        return null;
    }

    public static int findElementIndex(String ID) {
        int i = 0;
        while (i < size) {
            String elementId = elements.get(i).getId();
            if (ID.equals(elementId)) {
                return i;
            }
            i++;
        }

        return -1;
    }

}
