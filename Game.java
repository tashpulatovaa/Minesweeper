import processing.core.PApplet;
import processing.event.MouseEvent;

public class Game extends PApplet {
    Minesweeper minesweeper;
    Timer timer;
    int level = 1;

    public void settings() {
        fullScreen();
    }

    public void setup() {
        background(0);
        surface.setTitle("Minesweeper");
        minesweeper = newField(level);
        timer = new Timer(this, minesweeper);
        textAlign(CENTER);
        text(minesweeper.getMessage(), 650, height - 80);
    }

    Minesweeper newField(int level) {
        minesweeper = new Minesweeper(level, this);
        return minesweeper;
    }

    public void draw() {
        for (int i = 0; i < minesweeper.getHeight(); ++i) {       // DRAW CELLS
            for (int j = 0; j < minesweeper.getWidth(); ++j) {
                minesweeper.getCells()[i][j].draw();
            }
        }
        minesweeper.getBeginner().draw();                        // DRAW ADDITIONAL COMPONENTS
        minesweeper.getIntermediate().draw();
        minesweeper.getExpert().draw();
        minesweeper.getFlagCounter().draw();
        minesweeper.getEmoji().draw();
        timer.draw();
    }

    public void mousePressed() {
        if (minesweeper.getBeginner().contains(mouseX, mouseY)) {
            minesweeper = null;
            level = 1;
            setup();
        } else if (minesweeper.getIntermediate().contains(mouseX, mouseY)) {
            minesweeper = null;
            level = 2;
            setup();
        } else if (minesweeper.getExpert().contains(mouseX, mouseY)) {
            minesweeper = null;
            level = 3;
            setup();
        } else if (minesweeper.getEmoji().contains(mouseX, mouseY)) {
            setup();
        } else if (mouseButton == RIGHT && minesweeper.areCoordsInside(mouseX, mouseY)) {
            minesweeper.getFlagCounter().settings(minesweeper, mouseX, mouseY);
        } else if (mouseButton == LEFT) {
            if (minesweeper.areCoordsInside(mouseX, mouseY)) {
                if (minesweeper.isFirstLeftClick()) {
                    minesweeper.mousePressedFirst(mouseX, mouseY);
                } else {
                    for (int i = 0; i < minesweeper.getHeight(); ++i) {
                        for (int j = 0; j < minesweeper.getWidth(); ++j) {
                            minesweeper.getCells()[i][j].mousePressed();
                            if (minesweeper.getCellsToUncover() == minesweeper.getFlags()) {
                                minesweeper.setWon(true);
                            }
                        }
                    }
                }
            }
            minesweeper.getBeginner().mousePressed();
            minesweeper.getIntermediate().mousePressed();
            minesweeper.getExpert().mousePressed();
            minesweeper.getEmoji().mousePressed();
        }
    }

    public void mouseReleased() {
        for (int i = 0; i < minesweeper.getHeight(); ++i) {
            for (int j = 0; j < minesweeper.getWidth(); ++j) {
                minesweeper.getCells()[i][j].mouseReleased();
            }
        }
        minesweeper.getBeginner().mouseReleased();
        minesweeper.getIntermediate().mouseReleased();
        minesweeper.getExpert().mouseReleased();
        minesweeper.getEmoji().mouseReleased();
    }

    public void mouseClicked() {
        if (mouseButton == LEFT) {
            if (minesweeper.areCoordsInside(mouseX, mouseY)) {
                for (int i = 0; i < minesweeper.getHeight(); ++i) {
                    for (int j = 0; j < minesweeper.getWidth(); ++j) {
                        ((minesweeper.getCells()[i][j])).mouseClicked();
                    }
                }
            }
        }
        minesweeper.getBeginner().mouseClicked();
        minesweeper.getIntermediate().mouseClicked();
        minesweeper.getExpert().mouseClicked();
        minesweeper.getEmoji().mouseClicked();
    }

    public void mouseMoved(MouseEvent event) {
        for (int i = 0; i < minesweeper.getHeight(); ++i) {
            for (int j = 0; j < minesweeper.getWidth(); ++j) {
                minesweeper.getCells()[i][j].mouseMoved();
            }
        }
        minesweeper.getBeginner().mouseMoved();
        minesweeper.getIntermediate().mouseMoved();
        minesweeper.getExpert().mouseMoved();
        minesweeper.getEmoji().mouseMoved();
    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
