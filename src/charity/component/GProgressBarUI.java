/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package charity.component;

import java.awt.*;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicProgressBarUI;

/**
 *
 * @author phaml
 */
public class GProgressBarUI extends BasicProgressBarUI{

    @Override
    protected void paintDeterminate(Graphics g, JComponent c) {
        Graphics2D g2 = (Graphics2D) g.create();
        Insets b =progressBar.getInsets();// border
        int w= progressBar.getWidth();
        int h= progressBar.getHeight();
        int barRectWidth = w-(b.right+b.left);
        int barRectHeight = h- (b.top+b.bottom);
        
        //Tăng chất lượng vẽ
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        //Bckground
        g2.setColor(progressBar.getBackground());
        g2.fillRoundRect(b.left, b.top, barRectWidth, barRectHeight, barRectHeight, barRectHeight);
        // Vẽ viền bo tròn custom
        g2.setColor(Color.GRAY); // Màu viền, bạn đổi tuỳ ý
        g2.setStroke(new BasicStroke(2)); // Độ dày viền
        g2.drawRoundRect(b.left, b.top, barRectWidth, barRectHeight, barRectHeight, barRectHeight);
        //foreground
        int amountFull = getAmountFull(b, barRectWidth, barRectHeight);
        g2.setColor(progressBar.getForeground());
        g2.fillRoundRect(b.left, b.top, amountFull, barRectHeight, barRectHeight, barRectHeight);
        
        //ve text % tren progress bar
        if (progressBar.isStringPainted()){
            paintString(g2, b.left, b.top, barRectWidth, barRectHeight, amountFull, b);
            
        }
        
        g2.dispose();
    }
    
}
