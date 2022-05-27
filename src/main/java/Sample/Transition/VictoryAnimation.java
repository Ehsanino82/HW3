package Sample.Transition;

import Sample.App;
import Sample.View.EndGamePageController;
import Sample.View.GamePageController;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class VictoryAnimation extends Transition {
    AnchorPane pane;
    Rectangle rectangle;

    public VictoryAnimation(AnchorPane pane, Rectangle rectangle) {
        this.pane = pane;
        this.rectangle = rectangle;
        setCycleDuration(Duration.millis(4000));
        setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                EndGamePageController.setIsWin(true);
                GamePageController.endGame(pane);
            }
        });
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 26);
        rectangle.setFill(new ImagePattern(new Image(App.class.getResource("pictures/Victory/" + frame + ".png").toExternalForm())));
    }
}