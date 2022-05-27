package Sample.Transition;

import javafx.animation.Transition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BackgroundTransition extends Transition {
    private Rectangle rectangle1;
    private Rectangle rectangle2;
    public BackgroundTransition(Rectangle rectangle1, Rectangle rectangle2) {
        this.rectangle1 = rectangle1;
        this.rectangle2 = rectangle2;
        setCycleDuration(Duration.millis(9000000));
    }

    @Override
    protected void interpolate(double v) {
        rectangle1.setLayoutX(rectangle1.getLayoutX() - 5);
        rectangle2.setLayoutX(rectangle2.getLayoutX() - 5);
        if (rectangle1.getLayoutX() <= -900) rectangle1.setLayoutX(900);
        if (rectangle2.getLayoutX() <= -1800) rectangle2.setLayoutX(0);
    }
}