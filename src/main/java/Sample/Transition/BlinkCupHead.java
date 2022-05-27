package Sample.Transition;

import Sample.View.Component.CupHead;
import Sample.View.Images;
import javafx.animation.Transition;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class BlinkCupHead extends Transition {
    private CupHead cupHead;
    public BlinkCupHead(CupHead cupHead) {
        this.cupHead = cupHead;
        setCycleDuration(Duration.millis(2000));
    }
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 12);
        cupHead.setFill(new ImagePattern(Images.valueOf("CUP_HEAD_BLINK_" + frame).getImage()));
    }
}