package charity.component;

import charity.controller.LoginController.LoginController;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Window;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class GPanel extends JPanel {
    private Color starColor = Color.decode("#abc6e5");
    private Color endColor = Color.decode("#86fde8");
    
    
    private int a=0, b=0;
    public GPanel() {
    }

 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth(), h = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, starColor, w, h, endColor);
        g2d.setPaint(gp);
        g2d.fillRoundRect(0, 0, w, h, a, b);
    }
    public void changeColor( String color1, String color2){
        try{
            this.starColor= Color.decode(color1);
            this.endColor= Color.decode(color2);
            repaint();
        }catch (NumberFormatException e){
            System.out.println("Mã màu không hợp lệ!");
        }
    }
    public void changeRoundRect(int a, int b){
        this.a = a;
        this.b = b;
        repaint();
    }
}
