package Sample.Transition;

import Sample.View.Component.CupHead;
import Sample.View.Component.MiniBoss;
import Sample.View.EndGamePageController;
import Sample.View.GamePageController;
import Sample.View.Images;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class CupHeadMovement extends Transition {
    private AnchorPane pane;
    private CupHead cupHead;
    public CupHeadMovement(CupHead cupHead, AnchorPane pane) {
        this.cupHead = cupHead;
        this.pane = pane;
        setCycleDuration(Duration.millis(500));
        setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 3);
        cupHead.setFill(new ImagePattern(Images.valueOf("CUP_HEAD_MOVEMENT_" + frame).getImage()));
        for (int i = 6; i < pane.getChildren().size(); i++) {
            if (hasCollision(i)) {
                cupHead.setBlink(true);
                stop();
                BlinkCupHead blinkCupHead = new BlinkCupHead(cupHead);
                damage(cupHead);
                blinkCupHead.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        play();
                        cupHead.setBlink(false);
                    }
                });
                blinkCupHead.play();
            }
        }
    }

    private void damage(CupHead cupHead) {
        cupHead.setHealth(cupHead.getHealth() - 1);
        GamePageController.getHp().setText("HP: " + CupHead.getInstance().getHealth());
        if (cupHead.getHealth() == 0) {
            EndGamePageController.setIsWin(false);
            GamePageController.endGame(pane);
        }
    }

    private boolean hasCollision(int i) {
        if (!cupHead.isBlink() && pane.getChildren().get(i).getBoundsInParent().intersects(cupHead.getBoundsInParent())) {
            if ((pane.getChildren().get(i) instanceof MiniBoss)) {
                MiniBoss miniBoss = (MiniBoss) pane.getChildren().get(i);
                if (!cupHead.isBlink()) miniBoss.explode();
                return miniBoss.getHealth() >= 0;
            } else if (pane.getChildren().get(i).getId() != null && pane.getChildren().get(i).getId().equals("egg")) return true;
        }
        return false;
    }
}