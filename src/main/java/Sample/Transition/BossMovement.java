package Sample.Transition;

import Sample.View.Component.Boss;
import Sample.View.Images;
import javafx.animation.Transition;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class BossMovement extends Transition {
    private Boss boss;
    private boolean hitUp;
    private boolean hitDown;
    public BossMovement(Boss boss) {
        this.boss = boss;
        setCycleDuration(Duration.millis(2000));
        setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        if (boss.getHealth() == 0) return;
        if (hitDownWall() || hitDown) {
            hitDown = true;
            hitUp = false;
            boss.setLayoutY(boss.getLayoutY() - 1);
        }
        if (hitUpWall() || hitUp) {
            hitDown = false;
            hitUp = true;
            boss.setLayoutY(boss.getLayoutY() + 1);
        }
        int frame = (int) Math.floor(v * 10);
        boss.setFill(new ImagePattern(Images.valueOf("BOSS_MOVEMENT_" + frame).getImage()));
    }

    private boolean hitUpWall() {
        return boss.getLayoutY() <= 0;
    }

    private boolean hitDownWall() {
        return boss.getLayoutY() >= 320;
    }
}