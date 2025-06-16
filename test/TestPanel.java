package test;

import charity.view.Admin.DonationProcessingPanel;
import javax.swing.*;
import java.awt.*;

public class TestPanel extends JFrame {
    
    public TestPanel() {
        super("Test Panel Xử Lý Tiền Quyên Góp");
        
        // Tạo panel xử lý tiền quyên góp
        DonationProcessingPanel processingPanel = new DonationProcessingPanel();
        
        // Thêm vào frame
        getContentPane().add(processingPanel);
        
        // Thiết lập frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            TestPanel frame = new TestPanel();
            frame.setVisible(true);
        });
    }
}
