import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.IOException;

public class Building {

    private Image image;

    public void draw(Graphics g) throws IOException {

        image = new ImageIcon(getClass().getResource("drawing.jpg")).getImage();

        g.drawImage(image, 850, 65, new ImageObserver() {
            @Override
            public boolean imageUpdate(Image image, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        });
    }
}
