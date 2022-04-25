import processing.core.PApplet;

class BCell extends PButton {
    private boolean isMine = false;
    private boolean isFirstMine;
    private int positionInRow;
    private int positionInColumn;
    private int minesAround;
    private boolean flagged;
    private final Minesweeper minesweeper;

    BCell(PApplet applet, float x, float y, float width, float height, Minesweeper minesweeper) {
        super(applet, x, y, width, height);
        this.minesweeper = minesweeper;
        isFirstMine = false;
        flagged = false;
    }

    MouseClickListener clickListener = new MouseClickListener() {
        @Override
        public void mouseClicked() {
            setEnabled(false);
            if (isMine) {
                setFirstMine(true);
                minesweeper.setLost(true);
                minesweeper.getEmoji().setEmotion("SAD");
                minesweeper.getEmoji().setImage();
                minesweeper.uncoverAll();
            }
            setImage();
        }
    };

    void setImage() {
        if (isEnabled()) {
            setBackgroundImage(Images.images.get(0), getX(), getY(), getWidth(), getHeight());
            setBackgroundImageHover(Images.images.get(1), getX(), getY(), getWidth(), getHeight());
            setBackgroundImageActive(Images.images.get(2), getX(), getY(), getWidth(), getHeight());
            if(flagged){
                setFlag(Images.images.get(6));
            }
        } else if (!isEnabled() && (minesAround > 0) && (!isMine)) {
            setIcon(Images.numbers.get(minesAround - 1), getX(), getY(), getWidth(), getHeight());
        } else if (isMine && !isEnabled()) {
            if (isFirstMine) {
                setIcon(Images.images.get(5), getX(), getY(), getWidth(), getHeight());
            } else {
                setIcon(Images.images.get(4), getX(), getY(), getWidth(), getHeight());
            }
        }
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public int getMinesAround() {
        return minesAround;
    }

    public void setMinesAround(int minesAround) {
        this.minesAround = minesAround;
    }

    boolean isMine() {
        return isMine;
    }

    void setMine(boolean mine) {
        isMine = mine;
    }

    int getPositionInRow() {
        return positionInRow;
    }

    void setPositionInRow(int positionInRow) {
        this.positionInRow = positionInRow;
    }

    int getPositionInColumn() {
        return positionInColumn;
    }

    void setPositionInColumn(int positionInColumn) {
        this.positionInColumn = positionInColumn;
    }

    public boolean isFirstMine() {
        return isFirstMine;
    }

    public void setFirstMine(boolean firstMine) {
        isFirstMine = firstMine;
    }

}