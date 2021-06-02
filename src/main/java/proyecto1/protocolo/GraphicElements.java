package proyecto1.protocolo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GraphicElements {
    private List<ImageWithProperties> elements = new ArrayList<>();

    public static final GraphicElements SINGLETON = new GraphicElements();

    public ImageWithProperties createElement(String id, String imageType) {
        ImageWithProperties currentImage = new ImageWithProperties(id, imageType);
        return currentImage;
    }

    public List<ImageWithProperties> getElements() {
        return elements;
    }




    public void addElement(ImageWithProperties element) {
        elements.add(element);
    }

    public void clearElements() {
            for (int i = 0; i < elements.size(); i++){
                elements.get(i).removeFromGameWindow();
                elements.set(i, null);
            }
    }

    public void removeElement(String ID) {
        int i = findElementIndex(ID);
        if ( i >= 0) {
            elements.get(i).removeFromGameWindow();
            elements.remove(i);
        }


    }

    public ImageWithProperties findElement(String ID) {
        for (int i = 0; i < elements.size(); i++){
            String elementId = elements.get(i).getId();
            if (ID.equals(elementId)) {
                return elements.get(i);
            }
        }
        return null;
    }

    public int findElementIndex(String ID) {
        int i = 0;
        while (i < elements.size()) {
            String elementId = elements.get(i).getId();
            if (ID.equals(elementId)) {
                return i;
            }
            i++;
        }

        return -1;
    }

}
