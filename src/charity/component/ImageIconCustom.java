package charity.component;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
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
//BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g2 = resized.createGraphics();
//
//        // Bật anti-aliasing + rendering quality
//        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
//        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//        g2.drawImage(image, 0, 0, width, height, null);
//        g2.dispose();
//
//        return new ImageIcon(resized);
    }
}
