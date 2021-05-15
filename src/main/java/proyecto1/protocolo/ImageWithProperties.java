package proyecto1.protocolo;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    private int numberOfPlayers = 0;

    public ImageWithProperties(String id, String imageType, double positionX, double positionY) {
        this(id, imageType);
        this.positionX = positionX;
        this.positionY = positionY;
    }


    public ImageWithProperties(String id, String imageType) {
        this.image = new ImageView(Imagenes.getInstancia().cargarImagen(imageType));
        this.imageType = imageType;
        this.id = id;
        image.setId(id);
//        if (imageType.equals(Imagenes.IMG_NAVEUSUARIO)) {
//            numberOfPlayers ++;
//
//        }

        idLabel.setText("P" + String.valueOf(id));

        double fontSize = 15;
        FontWeight fontWeight = FontWeight.BOLD;
        idLabel.setFont(Font.font("Arial", fontWeight, fontSize));

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
        idLabel.setX(x + 45);
        idLabel.setY(y + 85);
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

//    public void addPlayer(){
//        numberOfPlayers ++;
//    }
}
