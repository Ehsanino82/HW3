package Sample.Transition;

import Sample.View.Component.Boss;
import Sample.View.Images;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BossShootAnimation extends Transition {
    private AnchorPane pane;
    private Boss boss;
    private boolean shoot;

    public BossShootAnimation(AnchorPane pane, Boss boss) {
        this.pane = pane;
        this.boss = boss;
        this.shoot = true;
        setCycleDuration(Duration.millis(2000));
        setCycleCount(1);
        setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                boss.getBossMovement().play();
            }
        });
    }

    @Override
    protected void interpolate(double v) {
        if (boss.getHealth() == 0) return;
        int frame = (int) Math.floor(v * 11);
        boss.setFill(new ImagePattern(Images.valueOf("BOSS_SHOOT_" + frame).getImage()));
        if (frame == 4 && shoot) {
            Rectangle egg = new Rectangle(boss.getX(), boss.getLayoutY() + 85, 60, 60);
            egg.setFill(new ImagePattern(Images.EGG.getImage()));
            egg.setId("egg");
            pane.getChildren().add(egg);
            EggTransition eggTransition = new EggTransition(pane, egg);
            eggTransition.play();
            shoot = false;
        }
    }
}