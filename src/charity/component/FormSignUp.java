/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package charity.component;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Admin
 */
public class FormSignUp extends JPanel{
    public FormSignUp(){
        
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth(), h = getHeight();
        GradientPaint gp = new GradientPaint (0, 0, Color.decode("#E0EAFC"), w, h, Color.decode("#CFDEF3"));
        g2d.setPaint(gp);
        g2d.fillRoundRect(0, 0, w, h, 40, 40);
    }
}
