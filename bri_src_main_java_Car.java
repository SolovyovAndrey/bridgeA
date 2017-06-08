
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.IOException;

public class Car {

    private int x;
    private int y;
    private Image image;


    public Car (int x, int y){
        this.x = x;
        this.y = y;

    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }


    public void setY(int y) {
        this.y = y;
    }


    public void draw(Graphics g) throws IOException {

        image = new ImageIcon(getClass().getResource("Ajaguar.jpg")).getImage();

        g.drawImage(image,getX(),getY(),new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        });

    }
}
