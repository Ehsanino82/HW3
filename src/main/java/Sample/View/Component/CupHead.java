package Sample.View.Component;

import Sample.App;
import Sample.Transition.BulletTransition;
import Sample.Transition.CupHeadMovement;
import Sample.Transition.CupHeadShootAnimation;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class CupHead extends Rectangle {

    private static CupHead instance;
    private int health;
    private boolean blink;
    private CupHeadMovement cupHeadMovement;
    public static CupHead getInstance() {
        if (instance == null)
            instance = new CupHead();
        return instance;
    }

    public CupHead() {
        super(30, 90, 65, 65);
        this.health = 4;
        this.blink = false;
        this.setFill(new ImagePattern(new Image(App.class.getResource("pictures/CupHead/1.png").toExternalForm())));
    }

    public int getHealth() {
        return health;
    }

    public CupHeadMovement getCupHeadMovement() {
        return cupHeadMovement;
    }

    public boolean isBlink() {
        return blink;
    }

    public void setBlink(boolean blink) {
        this.blink = blink;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setCupHeadMovement(CupHeadMovement cupHeadMovement) {
        this.cupHeadMovement = cupHeadMovement;
    }

    public void moveRight() {
        if (!hitRightWall())
            this.setX(this.getX() + 10);
    }

    public void moveLeft() {
        if (!hitLeftWall())
            this.setX(this.getX() - 10);
    }

    public void moveUp() {
        if (!hitUpWall())
            this.setY(this.getY() + 10);
    }

    public void moveDown() {
        if (!hitDownWall())
            this.setY(this.getY() - 10);
    }

    public boolean hitRightWall() {
        return this.getX() + this.getWidth() >= 900;
    }

    public boolean hitLeftWall() {
        return this.getX() <= 0;
    }

    public boolean hitUpWall() {
        return this.getY() + this.getWidth() >= 600;
    }

    public boolean hitDownWall() {
        return this.getY() <= 0;
    }

    public void shot(AnchorPane pane) {
        Rectangle bullet = new Rectangle(instance.getX() + 35, instance.getY() + 30, 50, 15);
        bullet.setFill(new ImagePattern(new Image(App.class.getResource("pictures/CupHead/bullet.png").toExternalForm())));
        pane.getChildren().add(bullet);
        Rectangle rectangle = new Rectangle(instance.getX() + 50, instance.getY() + 16, 50, 50);
        pane.getChildren().add(rectangle);
        CupHeadShootAnimation cupHeadShootAnimation = new CupHeadShootAnimation(pane, rectangle);
        BulletTransition transition = new BulletTransition(pane, bullet);
        transition.play();
        cupHeadShootAnimation.play();
    }
}
