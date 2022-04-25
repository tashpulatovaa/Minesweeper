import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public abstract class Images {
    static final ArrayList<PImage> images = new ArrayList<>();
    static final ArrayList<PImage> numbers = new ArrayList<>();

    public static ArrayList<PImage> getImages() {
        return images;
    }

    public static ArrayList<PImage> getNumbers() {
        return numbers;
    }

    public static void loadImages(PApplet applet, Minesweeper minesweeper) {

        // this.minesweeper = minesweeper;
        BCell temp = new BCell(applet, 10, 10, 10, 10, minesweeper);
        PImage blockNormal = temp.loadImg("block.png");
        PImage blockHover = temp.loadImg("block2.png");
        PImage blockActive = temp.loadImg("blockActive.png");
        PImage unabled = temp.loadImg("b.png");
        PImage mine = temp.loadImg("bomb.png");
        PImage redMine = temp.loadImg("redBomb.png");
        PImage flag = temp.loadImg("flag.png");
        PImage smileNormal = temp.loadImg("smileNormal.jpg");
        PImage smileHover = temp.loadImg("s.jpg");
        PImage smileActive = temp.loadImg("smileActive.jpeg");
        PImage winner = temp.loadImg("winner.jpg");
        PImage upset = temp.loadImg("upset2.png");
        images.add(blockNormal);  //0
        images.add(blockHover);   //1
        images.add(blockActive);  //2
        images.add(unabled);      //3
        images.add(mine);         //4
        images.add(redMine);      //5
        images.add(flag);         //6
        images.add(smileNormal);  //7
        images.add(smileHover);   //8
        images.add(smileActive);  //9
        images.add(winner);       //10
        images.add(upset);        //11
        for (int i = 1; i < 9; ++i) {
            PImage num = temp.loadImg(i + ".png");
            numbers.add(num);
        }
    }

    public static void images(PImage pImage, float v, float v1) {
    }
}
