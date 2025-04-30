package UI;

import charity.viewMain.OrganizationPanel;
import javax.swing.*;
import java.awt.*;

public class testPanel extends javax.swing.JFrame {

    private OrganizationPanel orgPanel;
    
    public testPanel() {
        initComponents();
        setupOrganizationPanel();
    }
    
    private void setupOrganizationPanel() {
        // Tạo và thêm OrganizationPanel vào JFrame
        orgPanel = new OrganizationPanel();
        
        // Thêm vào content pane của JFrame với BorderLayout
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(orgPanel, BorderLayout.CENTER);
        
        // Điều chỉnh kích thước cửa sổ phù hợp với nội dung
        setTitle("Quản lý tổ chức từ thiện");
        setSize(1200, 700); // Kích thước hợp lý hơn để hiển thị đầy đủ panel
        setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new Color(255, 255, 255));
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        try {
            // Cài đặt Nimbus Look and Feel nếu có
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(testPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Tạo và hiển thị form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                testPanel panel = new testPanel();
                panel.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}