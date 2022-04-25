import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

public class Timer {
    ArrayList<PImage> digits = new ArrayList<>();
    PApplet applet;
    Minesweeper minesweeper;
    private int hundreds = 0;
    private int tenth = 0;
    private int minutes = 0;
    private long miliseconds;
    private long seconds;

    private boolean start,stop;

    public Timer(PApplet applet, Minesweeper minesweeper) {
        this.applet = applet;
        this.minesweeper = minesweeper;
        loadPics();
        setStart();
    }
    void draw() {

        if(!minesweeper.isLost() && !minesweeper.isWon()){
            getCurrentTime();
        }
        int hundreds = (int)seconds / 100;
        int tenth = (int)seconds % 100 / 10;
        int ones = (int)seconds % 10;
        applet.image(digits.get(hundreds), minesweeper.getShiftToCenterX() + minesweeper.getWidth() * minesweeper.getButtonWidth() - 3 * minesweeper.getButtonWidth(), minesweeper.getShiftToCenterY() - minesweeper.getButtonHeight());
        applet.image(digits.get(tenth), minesweeper.getShiftToCenterX() + minesweeper.getWidth() * minesweeper.getButtonWidth() - 2 * minesweeper.getButtonWidth(), minesweeper.getShiftToCenterY()- minesweeper.getButtonHeight());
        applet.image(digits.get(ones), minesweeper.getShiftToCenterX() + minesweeper.getWidth() * minesweeper.getButtonWidth() -  minesweeper.getButtonWidth(), minesweeper.getShiftToCenterY()- minesweeper.getButtonHeight());
    }

    int getHundreds() {
        return hundreds;
    }

    void setHundreds(int hundreds) {
        this.hundreds = hundreds;
    }

    int getTenth() {
        return tenth;
    }

    void setTenth(int tenth) {
        this.tenth = tenth;
    }

    int getMinutes() {
        return minutes;
    }

    void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    boolean isStart() {
        return start;
    }

    void setStart(boolean start) {
        this.start = start;
    }

    boolean isStop() {
        return stop;
    }

    void setStop(boolean stop) {
        this.stop = stop;
    }

    void setStart(){
        miliseconds = System.currentTimeMillis();
        if(minesweeper.isLost()){
            long stopTime = miliseconds;
        }
    }

    long getCurrentTime(){
        long current = System.currentTimeMillis();

        seconds = (current - miliseconds) / 1000;

        return seconds;
    }

    void loadPics(){
        for(int i = 0; i < 10; ++i){
            PImage img = applet.loadImage(i + ".jpeg");
            img.resize((int)(minesweeper.getButtonWidth()),(int)(minesweeper.getButtonHeight()));
            digits.add(img);
        }
    }


}
