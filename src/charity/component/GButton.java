package charity.component;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

/**
 *
 * @author phaml
 */
public class GButton extends JButton {
    private Color startColor= Color.decode("#2d99ae");
    private Color endColor= Color.decode("#2d99ae");
    public GButton(String text) {
        super(text);
        setContentAreaFilled(false);   // Không vẽ nền mặc định
        setFocusPainted(false);        // Bỏ hiệu ứng viền focus
        setBorderPainted(false);       // Không vẽ border mặc định
        setOpaque(false);              // Quan trọng để bo góc có hiệu lực
    }
    
    public GButton() {
        super();
        setContentAreaFilled(false);   // Không vẽ nền mặc định
        setFocusPainted(false);        // Bỏ hiệu ứng viền focus
        setBorderPainted(false);       // Không vẽ border mặc định
        setOpaque(false);              // Quan trọng để bo góc có hiệu lực
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth(), h = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, startColor, w, h, endColor);
        g2d.setPaint(gp);
        g2d.fillRoundRect(0, 0, w, h, 20, 20);
        super.paintComponent(g);

    }
    public void changeColor( String color){
        try{
            this.startColor= Color.decode(color);
            this.endColor= Color.decode(color);
            repaint();
        }catch (NumberFormatException e){
            System.out.println("Mã màu không hợp lệ!");
        }
    }

    public void setBackgroundColor(Color color) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
