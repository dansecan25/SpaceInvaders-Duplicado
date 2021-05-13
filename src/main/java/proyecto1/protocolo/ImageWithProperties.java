package proyecto1.protocolo;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import proyecto1.Ventanas.ClientWindow;

public class ImageWithProperties {

    private String id;
    private Text idLabel = new Text();
    private ImageView image;

    public ImageWithProperties(String id, ImageView image) {

        this.id = id;
        this.image = image;
        image.setId(id);
        idLabel.setText(id);
    }

    public void addToGameWindow(Group window){
        window.getChildren().add(image);
        window.getChildren().add(idLabel);

    }

    public void move(double x, double y) {
        image.setX(x);
        image.setY(y);
        idLabel.setX(x);
        idLabel.setY(y);
    }
}
