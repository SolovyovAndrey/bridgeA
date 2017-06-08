import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.IOException;


public class Bridge {

    private Image image;
    protected boolean dawn;
    protected Image[] images;
    protected int i = 0;


    public Bridge() {

        dawn = false;
    }


    public void draw(Graphics g) throws IOException {

        image = new ImageIcon(getClass().getResource("bridge4.png")).getImage();

        g.drawImage(images[getImage(i)], 500, 265, new ImageObserver() {
            @Override
            public boolean imageUpdate(Image image, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        });
    }


    public int getImage(int i) {

        this.i = i;
        int idx = i;

        if (i < 4) {
            images = new Image[4];
            image = images[i];

            images[0] = new ImageIcon(getClass().getResource("bridge3.png")).getImage();
            images[1] = new ImageIcon(getClass().getResource("bridge2.png")).getImage();
            images[2] = new ImageIcon(getClass().getResource("bridge1.png")).getImage();
            images[3] = new ImageIcon(getClass().getResource("bridge0.png")).getImage();
        }
        if (i == 4) {
            dawn = true;
            idx = 3;

        }
        return idx;
    }
}