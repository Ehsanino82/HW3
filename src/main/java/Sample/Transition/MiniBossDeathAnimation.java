package Sample.Transition;

import Sample.View.Component.MiniBoss;
import Sample.View.Images;
import javafx.animation.Transition;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class MiniBossDeathAnimation extends Transition {
    private MiniBoss miniBoss;
    public MiniBossDeathAnimation(MiniBoss miniBoss) {
        this.miniBoss = miniBoss;
        setCycleDuration(Duration.millis(2000));
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 10);
        miniBoss.setFill(new ImagePattern(Images.valueOf("MINI_BOSS_DEATH_" + frame).getImage()));
    }
}