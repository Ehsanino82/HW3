package Sample.Transition;

import javafx.animation.Transition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class EggTransition extends Transition {
    private AnchorPane pane;
    private Rectangle rectangle;

    public EggTransition(AnchorPane pane, Rectangle rectangle) {
        this.pane = pane;
        this.rectangle = rectangle;
        setCycleDuration(Duration.millis(500));
        setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        rectangle.setLayoutX(rectangle.getLayoutX() - 3);
        if (rectangle.getLayoutX() < -900) {
            stop();
            pane.getChildren().remove(rectangle);
        }
    }
}