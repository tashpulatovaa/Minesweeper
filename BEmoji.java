import processing.core.PApplet;

class BEmoji extends PButton {
    enum EmotionalState {
        NORMAL,
        HAPPY,
        SAD
    }

    private EmotionalState emotion;

    BEmoji(PApplet applet, float x, float y, float width, float height) {
        super(applet, x, y, width, height);
        setEnabled(true);
        emotion = EmotionalState.NORMAL;
    }

    void setEmotion(String e) {
        switch (e) {
            case "NORMAL":
                this.emotion = EmotionalState.NORMAL;
                break;
            case "HAPPY":
                this.emotion = EmotionalState.HAPPY;
                break;
            case "SAD":
                this.emotion = EmotionalState.SAD;
                break;
        }
    }

    void setImage() {
        if (emotion == EmotionalState.NORMAL) {
            setIcon(Images.images.get(7), getX(), getY(), getWidth(), getHeight());
            setIconHover(Images.images.get(8), getX(), getY(), getWidth(), getHeight());
            setIconActive(Images.images.get(9), getX(), getY(), getWidth(), getHeight());
        } else if (emotion == EmotionalState.HAPPY) {
            setIcon(Images.images.get(10), getX(), getY(), getWidth(), getHeight());
            setIconHover(Images.images.get(10), getX(), getY(), getWidth(), getHeight());
            setIconActive(Images.images.get(10), getX(), getY(), getWidth(), getHeight());
        } else if (emotion == EmotionalState.SAD) {
            setIcon(Images.images.get(11), getX(), getY(), getWidth(), getHeight());
            setIconHover(Images.images.get(11), getX(), getY(), getWidth(), getHeight());
            setIconActive(Images.images.get(11), getX(), getY(), getWidth(), getHeight());
        }
    }

    boolean contains(float x, float y) {
        return (x >= getX() && x < getX() + getWidth() && y >= getY() && y < getY() + getHeight());
    }

    void setEmotion(EmotionalState emotion) {
        this.emotion = emotion;
    }

    EmotionalState getEmotion() {
        return emotion;
    }
}