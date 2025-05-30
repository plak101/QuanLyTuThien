package charity.component;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;


public class ImageIconCustom {
    public static ImageIcon getSmoothIcon(String url, int width, int height){
        URL imgUrl = ImageIconCustom.class.getResource(url);
        if (imgUrl ==null){
            System.err.println("Không tìm thấy ảnh: "+ url);
            return null;
        }
        ImageIcon icon = new ImageIcon(imgUrl);
        Image image = icon.getImage();
        Image scaled = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);

    }
}
