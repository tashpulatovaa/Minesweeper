import processing.core.PApplet;

public class BLevel extends PButton {
    BLevel(PApplet applet, float x, float y, float width, float height) {
        super(applet, x, y, width, height);
    }

    boolean contains(float x, float y) {
        return (x >= getX() && x < getX() + getWidth() && y >= getY() && y < getY() + getHeight());
    }
}
