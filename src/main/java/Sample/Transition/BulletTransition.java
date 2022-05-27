package Sample.Transition;

import Sample.View.Component.Boss;
import Sample.View.Component.CupHead;
import Sample.View.Component.MiniBoss;
import Sample.View.GamePageController;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BulletTransition extends Transition {
    private AnchorPane pane;
    private Rectangle bullet;
    private int dx;

    public BulletTransition(AnchorPane pane, Rectangle bullet) {
        this.pane = pane;
        this.bullet = bullet;
        this.dx = 5;
        setCycleDuration(Duration.millis(500));
        setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        bullet.setLayoutX(bullet.getLayoutX() + dx);
        for (int i = 6; i < pane.getChildren().size(); i++) {
            if (hasCollision(i)) {
                dx = 0;
                stop();
                bullet.setWidth(70);
                bullet.setHeight(70);
                bullet.setLayoutY(bullet.getLayoutY() - 30);
                BulletDustAnimation bulletDustAnimation = new BulletDustAnimation(bullet);
                bulletDustAnimation.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        pane.getChildren().remove(bullet);
                    }
                });
                bulletDustAnimation.play();
                damage((Rectangle) pane.getChildren().get(i));
            }
        }
        if (bullet.getLayoutX() > 900) {
            stop();
            pane.getChildren().remove(bullet);
        }
    }

    private boolean hasCollision(int i) {
        if (bullet.getLayoutX() <= 650
                && pane.getChildren().get(i).getBoundsInParent().intersects(bullet.getBoundsInParent())) {
            if ((pane.getChildren().get(i) instanceof MiniBoss)) {
                MiniBoss miniBoss = (MiniBoss) pane.getChildren().get(i);
                return miniBoss.getHealth() > 0;
            } else if ((pane.getChildren().get(i) instanceof Boss)) {
                Boss boss = (Boss) pane.getChildren().get(i);
                return boss.getHealth() > 0;
            }
        }
        return false;
    }

    private void damage(Rectangle rectangle) {
        if (rectangle instanceof Boss) {
            Boss.getInstance(pane).setHealth(Boss.getInstance(pane).getHealth() - 1);
            GamePageController.getProgressBar().setWidth(Boss.getInstance(pane).getHealth() * 2);
            GamePageController.getHealth().setText(String.valueOf(Boss.getInstance(pane).getHealth()));
            if (Boss.getInstance(pane).getHealth() == 0) {
                GamePageController.setGameScore(GamePageController.getGameScore() + 100 + (CupHead.getInstance().getHealth() * 5));
                GamePageController.getScore().setText("Score: " + GamePageController.getGameScore());
                Boss.getInstance(pane).explode();
            }
        } else if (rectangle instanceof MiniBoss) {
            MiniBoss miniBoss = (MiniBoss) rectangle;
            miniBoss.setHealth(miniBoss.getHealth() - 1);
            if (miniBoss.getHealth() == 0) {
                GamePageController.setGameScore(GamePageController.getGameScore() + 2);
                GamePageController.getScore().setText("Score: " + GamePageController.getGameScore());
                miniBoss.explode();
            }
        }
    }
}