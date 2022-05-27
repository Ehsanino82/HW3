package Sample.Transition;

import Sample.View.Images;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class CupHeadShootAnimation extends Transition {
    AnchorPane pane;
    private Rectangle rectangle;

    public CupHeadShootAnimation(AnchorPane pane, Rectangle rectangle) {
        this.pane = pane;
        this.rectangle = rectangle;
        setCycleDuration(Duration.millis(500));
        setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane.getChildren().remove(rectangle);
            }
        });
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 3);
        rectangle.setFill(new ImagePattern(Images.valueOf("CUP_HEAD_SHOOT_" + frame).getImage()));
    }
}