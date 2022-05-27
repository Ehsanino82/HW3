package Sample.View.Component;

import Sample.App;
import Sample.Transition.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Boss extends Rectangle {
    private static AnchorPane pane;
    private static Boss instance;
    private static int health;
    private BossMovement bossMovement;

    public static Boss getInstance(AnchorPane pane) {
        if (instance == null)
            instance = new Boss(pane);
        return instance;
    }

    public Boss(AnchorPane pane) {
        super(630, 0, 250, 250);
        this.pane = pane;
        this.health = 100;
        this.setFill(new ImagePattern(new Image(App.class.getResource("pictures/Boss/0.png").toExternalForm())));
    }

    public int getHealth() {
        return health;
    }

    public BossMovement getBossMovement() {
        return bossMovement;
    }


    public void setBossMovement(BossMovement bossMovement) {
        this.bossMovement = bossMovement;
    }

    public void setHealth(int health) {
        Boss.health = health;
    }

    public static void setPane(AnchorPane pane) {
        Boss.pane = pane;
    }

    public void bossShot() {
        this.bossMovement.stop();
        BossShootAnimation bossShootAnimation = new BossShootAnimation(pane, this);
        bossShootAnimation.play();
    }

    private void remove() {
        pane.getChildren().remove(Boss.getInstance(pane));
    }

    public void explode() {
        bossMovement.stop();
        BossDeathAnimation bossDeathAnimation = new BossDeathAnimation(this);
        bossDeathAnimation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                remove();
                Rectangle rectangle = new Rectangle(0, 0, 900, 600);
                pane.getChildren().add(rectangle);
                VictoryAnimation victoryAnimation = new VictoryAnimation(pane, rectangle);
                victoryAnimation.play();
            }
        });
        bossDeathAnimation.play();
    }
}
