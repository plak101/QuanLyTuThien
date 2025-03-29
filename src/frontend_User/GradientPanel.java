package frontend_User;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author phaml
 */
public class GradientPanel extends javax.swing.JPanel {

    public GradientPanel() {
        initComponents();
//        GradientPaint g = new GradientPaint(0, 0, Color.decode("#758EB7"), 0, getHeight(), Color.decode("#A5CAD2"));
        new GradientPaint(0, 0, Color.decode("#141E30"), 0, getHeight(), Color.decode("#243B55"));
    }
    @Override
    protected void paintChildren(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradientPaint = new GradientPaint(0, 0, Color.decode("#2c6975"), 0, getHeight(), Color.decode("#68b2a0"));
        g2d.setPaint(gradientPaint);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintChildren(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody


    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 142, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 342, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
