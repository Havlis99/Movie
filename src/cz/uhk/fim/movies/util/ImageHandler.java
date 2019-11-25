package cz.uhk.fim.movies.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageHandler {

    public static Image getImageFromUrl(String imageUrl){
        Image img = null;
        URL url = null;
        try {
            url = new URL(imageUrl);
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
