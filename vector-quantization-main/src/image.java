import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

public class image {
    String directory;
    BufferedImage img;
    float[][] pixels;
    int w, h;

    public image() {

    }

    public image(String str) {
        try {
            img = ImageIO.read(new File(str));
            directory = str;
        } catch (IOException e) {
            e.printStackTrace();
        }
        w = img.getWidth();
        h = img.getHeight();
        pixels = new float[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                Color c = new Color(img.getRGB(i, j));
                int red = (int) (c.getRed() * 0.299);
                int green = (int) (c.getGreen() * 0.587);
                int blue = (int) (c.getBlue() * 0.114);
                pixels[i][j] = red + green + blue;
            }
        }
    }

    @Override
    public String toString() {
        String tmp = new String();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                tmp += pixels[i][j] + " ";
            }
            tmp += '\n';
        }
        return tmp;
    }


    public void toimage(String s) {
        BufferedImage img = new BufferedImage(
                pixels.length, pixels[0].length, BufferedImage.TYPE_BYTE_GRAY);
        for (int x = 0; x < pixels.length; x++) {
            for (int y = 0; y < pixels[x].length; y++) {
                img.setRGB(x, y, new Color((int) pixels[x][y], (int) pixels[x][y], (int) pixels[x][y]).getRGB());
            }
        }
        File imageFile = new File(s);
        try {
            ImageIO.write(img, "png", imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
