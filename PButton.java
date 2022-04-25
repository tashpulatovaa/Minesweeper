import processing.core.PApplet;
import processing.core.PImage;

interface MouseClickListener {
    void mouseClicked();
}

public class PButton {
    enum State {
        NORMAL,
        HOVER,
        ACTIVE
    }

    private State currentState = State.NORMAL;
    private String label = "";
    private float x, y, width, height;
    final static int fontSize = 16;

    private int backgroundColor = 0xffbababa;
    private int backgroundColorHover = 0xffbababa;
    private int backgroundColorActive = 0xffbababa;

    private int labelColor = 0xffe0e0e0;
    private int labelColorHover = 0xffe0e0e0;
    private int labelColorActive = 0xff969696;

    private PImage icon = null;
    private PImage iconHover = null;
    private PImage iconActive = null;
    private PImage flag = null;

    private PImage backgroundImage = null;
    private PImage backgroundImageHover = null;
    private PImage backgroundImageActive = null;

    private float iconWidth;
    private float iconHeight;
    private float iconX;
    private float iconY;

    private float imageWidth;
    private float imageHeight;
    private float imageX;
    private float imageY;

    private float labelX =  x - width / 2;
    private float labelY =  y - height / 2;

    private boolean enabled = true;
    private MouseClickListener clickListener = () -> { };

    private final PApplet applet;

    PButton(PApplet applet, float x, float y, float width, float height) {

        this.applet = applet;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }



    String getLabel() {
        return label;
    }

    void setLabel(String label, float labelX, float labelY) {
        this.labelX = labelX;
        this.labelY = labelY;
        this.label = label;
    }

    float getX() {
        return x;
    }

    void setX(float x) {
        this.x = x;
    }

    float getY() {
        return y;
    }

    void setY(float y) {
        this.y = y;
    }

    float getWidth() {
        return width;
    }

    void setWidth(float width) {
        this.width = width;
    }

    float getHeight() {
        return height;
    }

    void setHeight(float height) {
        this.height = height;
    }

    static int getFontSize() {
        return fontSize;
    }

    int getBackgroundColor() {
        return backgroundColor;
    }

    void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    int getBackgroundColorHover() {
        return backgroundColorHover;
    }

    void setBackgroundColorHover(int backgroundColorHover) {
        this.backgroundColorHover = backgroundColorHover;
    }

    int getBackgroundColorActive() {
        return backgroundColorActive;
    }

    void setBackgroundColorActive(int backgroundColorActive) {
        this.backgroundColorActive = backgroundColorActive;
    }

    int getLabelColor() {
        return labelColor;
    }

    void setLabelColor(int labelColor) {
        this.labelColor = labelColor;
    }

    int getLabelColorHover() {
        return labelColorHover;
    }

    void setLabelColorHover(int labelColorHover) {
        this.labelColorHover = labelColorHover;
    }

    int getLabelColorActive() {
        return labelColorActive;
    }

    void setLabelColorActive(int labelColorActive) {
        this.labelColorActive = labelColorActive;
    }

    PImage getIcon() {
        return icon;
    }

    public PImage getFlag() { return flag; }

    void setFlag(PImage flag) {
        this.flag = flag;
    }

    void setIcon(PImage icon, float iconX, float iconY, float iconWidth, float iconHeight) {
        this.iconX = iconX;
        this.iconY = iconY;
        this.iconWidth = iconWidth;
        this.iconHeight = iconHeight;
        this.icon = icon;
    }

    PImage getIconHover() {
        return iconHover;
    }

    void setIconHover(PImage iconHover,float iconX,float iconY, float iconWidth, float iconHeight) {
        this.iconX = iconX;
        this.iconY = iconY;
        this.iconWidth = iconWidth;
        this.iconHeight = iconHeight;
        this.iconHover = iconHover;
    }

    PImage getIconActive() {
        return iconActive;
    }

    void setIconActive(PImage iconActive,float iconX,float iconY, float iconWidth, float iconHeight ) {
        this.iconX = iconX;
        this.iconY = iconY;
        this.iconWidth = iconWidth;
        this.iconHeight = iconHeight;
        this.iconActive = iconActive;
    }

    PImage getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(PImage backgroundImage, float imageX, float imageY, float imageWidth, float imageHeight) {
        this.imageX = imageX;
        this.imageY = imageY;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.backgroundImage = backgroundImage;
    }

    PImage getBackgroundImageHover() {
        return backgroundImageHover;
    }

    void setBackgroundImageHover(PImage backgroundImageHover,float imageX, float imageY, float imageWidth, float imageHeight) {
        this.imageX = imageX;
        this.imageY = imageY;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.backgroundImageHover = backgroundImageHover;
    }

    PImage getBackgroundImageActive() {
        return backgroundImageActive;
    }

    void setBackgroundImageActive(PImage backgroundImageActive,float imageX, float imageY, float imageWidth, float imageHeight) {
        this.imageX = imageX;
        this.imageY = imageY;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.backgroundImageActive = backgroundImageActive;
    }

    boolean isEnabled() {
        return enabled;
    }

    void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public float getIconWidth() {
        return iconWidth;
    }

    public void setIconWidth(float iconWidth) {
        this.iconWidth = iconWidth;
    }

    public float getIconHeight() {
        return iconHeight;
    }

    public void setIconHeight(float iconHeight) {
        this.iconHeight = iconHeight;
    }

    public float getIconX() {
        return iconX;
    }

    public void setIconX(float iconX) {
        this.iconX = iconX;
    }

    public float getIconY() {
        return iconY;
    }

    public void setIconY(float iconY) {
        this.iconY = iconY;
    }

    public float getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(float imageWidth) {
        this.imageWidth = imageWidth;
    }

    public float getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(float imageHeight) {
        this.imageHeight = imageHeight;
    }

    public float getImageX() {
        return imageX;
    }

    public void setImageX(float imageX) {
        this.imageX = imageX;
    }

    public float getImageY() {
        return imageY;
    }

    public void setImageY(float imageY) {
        this.imageY = imageY;
    }

    MouseClickListener getClickListener() {
        return clickListener;
    }

    void setClickListener(MouseClickListener clickListener) {
        this.clickListener = clickListener;
    }

    void mousePressed() {
        if (!enabled) return;
        if (areCoordsInside(applet.mouseX, applet.mouseY)) {
            currentState = State.ACTIVE;
        }
    }

    void mouseReleased() {
        if (!enabled) return;
        if (areCoordsInside(applet.mouseX, applet.mouseY)) {
            currentState = State.HOVER;
        } else {
            currentState = State.NORMAL;
        }
    }

    void mouseClicked() {
        if (!enabled) return;
        if (areCoordsInside(applet.mouseX, applet.mouseY)) {
            clickListener.mouseClicked();
        }
    }

    void mouseMoved() {
        if (!enabled) return;

        if (areCoordsInside(applet.mouseX, applet.mouseY)) {
            currentState = State.HOVER;
        }else {
            currentState = State.NORMAL;
        }
    }

    void draw() {
        if (currentState == State.NORMAL) {
            applet.fill(backgroundColor);
            applet.rect(x, y, width, height);
            if (backgroundImage != null && isEnabled()) {
                applet.image(backgroundImage, imageX, imageY, imageWidth, imageHeight);
            }
            if (icon != null) {
                applet.image(icon, iconX, iconY, iconWidth, iconHeight);
            }
            if (flag != null && isEnabled()) {
                applet.image(flag, x, y, width, height);
            }

            if (!label.isEmpty()) {
                applet.fill(labelColor);
                applet.textAlign(applet.CENTER, applet.CENTER);
                applet.text(label, labelX, labelY);
            }
        } else if (currentState == State.HOVER) {
            applet.fill(backgroundColorHover);
            applet.rect(x, y, width, height);
            if (backgroundImageHover != null && isEnabled()) {
                applet.image(backgroundImageHover, imageX, imageY, imageWidth, imageHeight);
            } else if (backgroundImage != null && isEnabled()) {
                applet.image(backgroundImage, imageX, imageY, imageWidth, imageHeight);
            }
            if (iconHover != null) {
                applet.image(iconHover, iconX, iconY, iconWidth, iconHeight);
            } else if (icon != null ) {
                applet.image(icon, iconX, iconY, iconWidth, iconHeight);
            }
            if (flag != null && isEnabled()) {
                applet.image(flag, x, y, width, height);
            }

            if (!label.isEmpty()) {
                applet.fill(labelColorHover);
                applet.textAlign(applet.CENTER, applet.CENTER);
                applet.text(label, labelX, labelY);
            }

        } else if (currentState == State.ACTIVE) {
            applet.fill(backgroundColorActive);
            applet.rect(x, y, width, height);
            if (backgroundImageActive != null && isEnabled()) {
                applet.image(backgroundImageActive, imageX, imageY, imageWidth, imageHeight);
            } else if (backgroundImage != null && isEnabled()) {
                applet.image(backgroundImage, imageX, imageY, imageWidth, imageHeight);
            }
            if (iconActive != null ) {
                applet.image(iconActive, iconX, iconY, iconWidth, iconHeight);
            } else if (icon != null ) {
                applet.image(icon, iconX, iconY, iconWidth, iconHeight);
            }
            if (flag != null && isEnabled()) {
                applet.image(flag, x, y, width, height);
            }

            if (!label.isEmpty()) {
                applet.fill(labelColorActive);
                applet.textAlign(applet.CENTER, applet.CENTER);
                applet.text(label, labelX,labelY);
            }
        }
    }

    private boolean areCoordsInside(int mouseX, int mouseY) {
        return mouseX >= x && mouseX < x + width &&
                mouseY >= y && mouseY < y + height;
    }

    PImage loadImg(String filename){
        return  applet.loadImage(filename);
    }
}
