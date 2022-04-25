import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

public class FCounter {
    private final PApplet applet;
    private final Minesweeper minesweeper;
    private float x, y;
    private int nOfFlags;
    private final ArrayList<PImage> digits = new ArrayList<>();

    public FCounter(PApplet applet, Minesweeper minesweeper) {
        nOfFlags = minesweeper.getFlags();
        this.applet = applet;
        this.minesweeper = minesweeper;
        loadPics();
    }

    void draw() {
        int tenth = nOfFlags / 10;
        int ones = nOfFlags % 10;
        applet.image(digits.get(tenth), minesweeper.getShiftToCenterX(), minesweeper.getShiftToCenterY() - minesweeper.getButtonHeight());
        applet.image(digits.get(ones), minesweeper.getShiftToCenterX() + minesweeper.getButtonWidth(), minesweeper.getShiftToCenterY() - minesweeper.getButtonHeight());

    }

    void loadPics() {
        for (int i = 0; i < 10; ++i) {
            PImage img = applet.loadImage(i + ".jpeg");
            img.resize((int) (minesweeper.getButtonWidth()), (int) (minesweeper.getButtonHeight()));
            digits.add(img);
        }
    }

    void settings(Minesweeper minesweeper, float mouseX, float mouseY) {
        int positionInRow = (int) ((mouseX - minesweeper.getShiftToCenterX()) / minesweeper.getButtonWidth());
        int positionInColumn = (int) ((mouseY - minesweeper.getShiftToCenterY()) / minesweeper.getButtonHeight());
        if (minesweeper.getCells()[positionInColumn][positionInRow].isFlagged() && minesweeper.getCells()[positionInColumn][positionInRow].isEnabled()) {
            minesweeper.setFlags(minesweeper.getFlags()+1);
            setnOfFlags(minesweeper.getFlags());
            minesweeper.getCells()[positionInColumn][positionInRow].setFlag(null);
            minesweeper.getCells()[positionInColumn][positionInRow].setFlagged(false);
            minesweeper.getCells()[positionInColumn][positionInRow].setImage();
        } else if (!minesweeper.getCells()[positionInColumn][positionInRow].isFlagged() && minesweeper.getCells()[positionInColumn][positionInRow].isEnabled() && minesweeper.getFlags() > 0) {
            minesweeper.setFlags(minesweeper.getFlags()-1);
            setnOfFlags(minesweeper.getFlags());
            minesweeper.getCells()[positionInColumn][positionInRow].setFlag(Images.images.get(6));
            minesweeper.getCells()[positionInColumn][positionInRow].setFlagged(true);
            minesweeper.getCells()[positionInColumn][positionInRow].setImage();
        }
    }

    public int getnOfFlags() {
        return nOfFlags;
    }

    public void setnOfFlags(int nOfFlags) {
        this.nOfFlags = nOfFlags;
    }
}
