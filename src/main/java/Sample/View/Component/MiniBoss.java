package Sample.View.Component;

import Sample.App;
import Sample.Transition.MiniBossDeathAnimation;
import Sample.Transition.MiniBossMovement;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class MiniBoss extends Rectangle {
    private AnchorPane pane;
    private int health;
    private MiniBossMovement miniBossMovement;

    public MiniBoss(AnchorPane pane, double x, double y) {
        super(x, y, 100, 100);
        this.pane = pane;
        this.health = 2;
        this.setFill(new ImagePattern(new Image(App.class.getResource("pictures/MiniBoss/Fly/0.png").toExternalForm())));
        this.miniBossMovement = new MiniBossMovement(this);
        this.miniBossMovement.play();
    }

    public int getHealth() {
        return health;
    }

    public MiniBossMovement getMiniBossMovement() {
        return miniBossMovement;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void remove() {
        pane.getChildren().remove(this);
    }

    public void explode() {
        MiniBossDeathAnimation miniBossDeathAnimation = new MiniBossDeathAnimation(this);
        miniBossDeathAnimation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                remove();
            }
        });
        miniBossDeathAnimation.play();
    }
}