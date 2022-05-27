package Sample.Transition;

import Sample.View.Images;
import javafx.animation.Transition;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BulletDustAnimation extends Transition {
    private Rectangle bullet;
    public BulletDustAnimation(Rectangle bullet) {
        this.bullet = bullet;
        setCycleDuration(Duration.millis(1000));
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 11);
        bullet.setFill(new ImagePattern(Images.valueOf("HIT_DUST_" + frame).getImage()));
    }
}