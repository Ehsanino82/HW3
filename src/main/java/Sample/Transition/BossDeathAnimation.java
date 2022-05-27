package Sample.Transition;

import Sample.View.Component.Boss;
import Sample.View.Images;
import javafx.animation.Transition;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class BossDeathAnimation extends Transition {
    private Boss boss;
    public BossDeathAnimation(Boss boss) {
        this.boss = boss;
        setCycleDuration(Duration.millis(6000));
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 25);
        boss.setFill(new ImagePattern(Images.valueOf("BOSS_DEATH_" + frame).getImage()));
    }
}