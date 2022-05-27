package Sample.Transition;

import Sample.View.Component.MiniBoss;
import Sample.View.Images;
import javafx.animation.Transition;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class MiniBossMovement extends Transition {
    private MiniBoss miniBoss;
    private int dx;

    public MiniBossMovement(MiniBoss miniBoss) {
        this.miniBoss = miniBoss;
        this.dx = 3;
        setCycleDuration(Duration.millis(2000));
        setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        miniBoss.setLayoutX(miniBoss.getLayoutX() - dx);
        int frame = (int) Math.floor(v * 15);
        miniBoss.setFill(new ImagePattern(Images.valueOf("MINI_BOSS_MOVEMENT_" + frame).getImage()));
        if (miniBoss.getLayoutX() < -1400) {
            stop();
            miniBoss.remove();
        }
        if (miniBoss.getHealth() == 0) stop();
    }
}