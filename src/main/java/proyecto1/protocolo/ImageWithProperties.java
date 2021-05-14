package proyecto1.protocolo;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import proyecto1.Imagenes.Imagenes;
import proyecto1.Ventanas.ClientWindow;

public class ImageWithProperties {

    private String imageType;
    private String id;
    private Text idLabel = new Text();
    private ImageView image;
    private double positionX;
    private double positionY;


    public ImageWithProperties(String id, String imageType) {
        this.image = new ImageView(Imagenes.getInstancia().cargarImagen(imageType));
        this.imageType = imageType;
        this.id = id;
        image.setId(id);
        idLabel.setText(id);

    }

    public void addToGameWindow(Group window){
        window.getChildren().add(image);
        window.getChildren().add(idLabel);

    }

    public void removeFromGameWindow(){
        image.setVisible(false);
        idLabel.setVisible(false);

    }

    public void move(double x, double y) {
        image.setX(x);
        image.setY(y);
        idLabel.setX(x);
        idLabel.setY(y);
        positionX = x;
        positionY = y;
    }

    public String getId() {
        return id;
    }

    public Text getIdLabel() {
        return idLabel;
    }

    public ImageView getImage() {
        return image;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public String getImageType() {
        return imageType;
    }
}
