import processing.core.PApplet;
import java.util.ArrayList;

public class Minesweeper extends Images {
    private static final int WIDTH_FOR_BEGINNER = 8;
    private static final int HEIGHT_FOR_BEGINNER = 8;
    private static final int WIDTH_FOR_INTERMEDIATE = 16;
    private static final int HEIGHT_FOR_INTERMEDIATE = 12;
    private static final int WIDTH_FOR_EXPERT = 24;
    private static final int HEIGHT_FOR_EXPERT = 16;

    private static final int MINES_FOR_BEGINNER = 10;
    private static final int MINES_FOR_INTERMEDIATE = 40;
    private static final int MINES_FOR_EXPERT = 99;

    static final int FLAGS_FOR_BEGINNER = 10;
    static final int FLAGS_FOR_INTERMEDIATE = 40;
    static final int FLAGS_FOR_EXPERT = 99;

    static final int[][] SHIFTS = {
            {-1, -1, -1, 0, 0, 0, 1, 1, 1},
            {-1, 0, 1, -1, 0, 1, -1, 0, 1}
    };

    private final PApplet applet;
    private final int width;
    private final int height;
    private final BCell[][] cells;
    private final int mines;
    private int flags;
    private int cellsToUncover;
    private final boolean[][] uncoveredField;
    private float shiftToCenterX, shiftToCenterY;
    private final float buttonWidth;
    private final float buttonHeight;
    private BLevel beginner, intermediate, expert;
    private boolean isFirstLeftClick = true;
    private FCounter flagCounter;
    private BEmoji emoji;
    private boolean lost, won;
    private final String message;

    Minesweeper(int difficulty, PApplet applet) {
        this.applet = applet;
        switch (difficulty) {
            case 1:
                mines = MINES_FOR_BEGINNER;
                width = WIDTH_FOR_BEGINNER;
                height = HEIGHT_FOR_BEGINNER;
                flags = FLAGS_FOR_BEGINNER;
                cellsToUncover = width * height - mines;
                break;
            case 2:
                mines = MINES_FOR_INTERMEDIATE;
                width = WIDTH_FOR_INTERMEDIATE;
                height = HEIGHT_FOR_INTERMEDIATE;
                flags = FLAGS_FOR_INTERMEDIATE;
                cellsToUncover = width * height - mines;
                break;
            case 3:
            default:
                mines = MINES_FOR_EXPERT;
                width = WIDTH_FOR_EXPERT;
                height = HEIGHT_FOR_EXPERT;
                flags = FLAGS_FOR_EXPERT;
                cellsToUncover = width * height - mines;
                break;
        }
        cells = new BCell[height][width];
        buttonWidth = (applet.width - 5 * (applet.width / (width * 1.0f))) / (width * 1.0f);
        buttonHeight = buttonWidth;
        Images.loadImages(applet, this);
        initCells(applet);
        uncoveredField = new boolean[height][width];
        lost = false;
        won = (cellsToUncover == flags);
        message = "Clone of Minesweeper (with Processing library) by Aikokul Tashpulatova for OOP 2021";
    }

    public boolean isLost() {
        return lost;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        uncoverAll();
        emoji.setEmotion("HAPPY");
        emoji.setImage();
        this.won = won;
    }
    void mousePressedFirst(float mouseX, float mouseY) {
        BCell selectedCell;

        int positionInRow = (int) ((mouseX - shiftToCenterX) / buttonWidth);
        int positionInColumn = (int) ((mouseY - shiftToCenterY) / buttonHeight);
        float buttonX = shiftToCenterX + (positionInRow * buttonHeight);
        float buttonY = shiftToCenterY + (positionInColumn * buttonHeight);

        if (areCoordsInside(positionInRow, positionInColumn)) {
            isFirstLeftClick = false;
            selectedCell = new BCell(applet, buttonX, buttonY, buttonWidth, buttonHeight, this);
            selectedCell.setPositionInRow(positionInRow);
            selectedCell.setPositionInColumn(positionInColumn);

            ArrayList<BCell> potentialMinePlaces = new ArrayList<>();
            for (int y = 0; y < height; ++y) {
                for (int x = 0; x < width; ++x) {
                    if (selectedCell.getPositionInRow() == x && selectedCell.getPositionInColumn() == y)
                        continue;
                    BCell cell = new BCell(applet, shiftToCenterX + (x * buttonWidth), shiftToCenterY + (y * buttonHeight), buttonWidth, buttonHeight, this);
                    cell.setPositionInRow(x);
                    cell.setPositionInColumn(y);
                    potentialMinePlaces.add(cell);
                }
            }
            putMines(potentialMinePlaces);
            cellsToUncover -= floodUncover(selectedCell);
        }
    }

    void initCells(PApplet applet) {
        shiftToCenterX = (applet.width - width * buttonWidth) / 2;
        shiftToCenterY = (applet.height - height * buttonHeight) / 2;

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                float cellX = shiftToCenterX + x * buttonWidth;
                float cellY = shiftToCenterY + y * buttonHeight;
                cells[y][x] = new BCell(applet, cellX, cellY, buttonWidth, buttonHeight, this);
                cells[y][x].setPositionInColumn(y);
                cells[y][x].setPositionInRow(x);
                cells[y][x].setImage();
                cells[y][x].setClickListener(cells[y][x].clickListener);
            }
        }
        beginner = new BLevel(applet, applet.width - 110, applet.height / (2.5f) - 50, 100, 30);
        intermediate = new BLevel(applet, applet.width - 110, applet.height / (2.5f), 100, 30);
        expert = new BLevel(applet, applet.width - 110, applet.height / (2.5f) + 50, 100, 30);
        setLabels();
        flagCounter = new FCounter(applet, this);
        emoji = new BEmoji(applet, (shiftToCenterX + width * buttonWidth / 2 - buttonWidth / 2), shiftToCenterY - buttonHeight - 2, buttonWidth, buttonHeight);
        emoji.setEmotion("NORMAl");
        emoji.setImage();
    }

    void setLabels() {
        beginner.setLabel("BEGINNER", beginner.getX() + 50, beginner.getY() + 10);
        beginner.setLabelColor(0xff00ff00);
        beginner.setLabelColorHover(0xff009000);
        intermediate.setLabel("INTERMEDIATE", intermediate.getX() + 50, intermediate.getY() + 10);
        intermediate.setLabelColor(0xff00ff00);
        intermediate.setLabelColorHover(0xff009000);
        expert.setLabel("EXPERT", expert.getX() + 50, expert.getY() + 10);
        expert.setLabelColor(0xff00ff00);
        expert.setLabelColorHover(0xff009000);
    }

    int floodUncover(BCell selectedCell) {
        cells[selectedCell.getPositionInColumn()][selectedCell.getPositionInRow()].setEnabled(false);
        cells[selectedCell.getPositionInColumn()][selectedCell.getPositionInRow()].setImage();
        int uncoveredCells = 0;

        for (int i = 0; i < SHIFTS[0].length; ++i) {
            int ny = selectedCell.getPositionInColumn() + SHIFTS[0][i];
            int nx = selectedCell.getPositionInRow() + SHIFTS[1][i];

            if (areCoordsInside(nx, ny) && (cells[ny][nx].isEnabled()) && (!(cells[ny][nx].isMine()))) {
                cells[ny][nx].setEnabled(false);
                cells[ny][nx].setImage();
                ++uncoveredCells;

                if ((cells[ny][nx].getMinesAround() == 0) && (!(cells[ny][nx].isMine()))) {
                    BCell emptyCell = new BCell(applet, shiftToCenterY + ny * buttonHeight * 1.0f, shiftToCenterX + ny * buttonWidth * 1.0f, buttonWidth, buttonHeight, this);
                    emptyCell.setPositionInColumn(ny);
                    emptyCell.setPositionInRow(nx);
                    uncoveredCells += floodUncover(emptyCell);
                }
            }
        }
        return uncoveredCells;
    }

    void uncoverAll() {
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                if (cells[y][x].isEnabled()) {
                    cells[y][x].setEnabled(false);
                }
            }
        }
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                (cells[i][j]).setImage();
            }
        }
    }

    void putMines(ArrayList<BCell> potentialMinePlaces) {
        for (int i = 0; i < mines; ++i) {
            int randomIndex = (int) (Math.random() * potentialMinePlaces.size() - 1);
            BCell mine = potentialMinePlaces.get(randomIndex);
            potentialMinePlaces.remove(randomIndex);

            cells[mine.getPositionInColumn()][mine.getPositionInRow()].setMine(true);

            for (int j = 0; j < SHIFTS[0].length; ++j) {
                int ny = mine.getPositionInColumn() + SHIFTS[0][j];
                int nx = mine.getPositionInRow() + SHIFTS[1][j];
                if (areCoordsInside(nx, ny) && (!(cells[ny][nx].isMine()))) {
                    cells[ny][nx].setMinesAround(cells[ny][nx].getMinesAround() + 1);
                }
            }
        }
    }
    boolean areCoordsInside(float x, float y) {
        int fieldWidth = width;
        int fieldHeight = height;

        return x >= shiftToCenterX && x < shiftToCenterX + fieldWidth * buttonWidth && y >= shiftToCenterY && y < shiftToCenterY + fieldHeight * buttonHeight;
    }
    private boolean areCoordsInside(int x, int y) {
        int fieldWidth = width;
        int fieldHeight = height;

        return x >= 0 && x < fieldWidth && y >= 0 && y < fieldHeight;
    }
    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    BCell[][] getCells() {
        return cells;
    }

    int getFlags() {
        return flags;
    }

    void setFlags(int flags) {
        this.flags = flags;
    }

    int getCellsToUncover() {
        return cellsToUncover;
    }

    boolean[][] getUncoveredField() {
        return uncoveredField;
    }

    float getShiftToCenterX() {
        return shiftToCenterX;
    }

    float getShiftToCenterY() {
        return shiftToCenterY;
    }

    float getButtonWidth() {
        return buttonWidth;
    }

    float getButtonHeight() {
        return buttonHeight;
    }

    BLevel getBeginner() {
        return beginner;
    }

    BLevel getIntermediate() {
        return intermediate;
    }

    BLevel getExpert() {
        return expert;
    }

    boolean isFirstLeftClick() {
        return isFirstLeftClick;
    }

    FCounter getFlagCounter() {
        return flagCounter;
    }

    BEmoji getEmoji() {
        return emoji;
    }

    void setEmoji(BEmoji emoji) {
        this.emoji = emoji;
    }

    public String getMessage() {
        return message;
    }
}
