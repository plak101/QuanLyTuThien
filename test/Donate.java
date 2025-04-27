

import javax.swing.*;
import java.awt.*;

public final class Donate extends JDialog {
    private JPanel header;
    private JProgressBar progress;

    public Donate(Frame parent, boolean modal) {
        super(parent, modal);
        init();
    }

    private void init() {
        setTitle("Donate Progress");
        setSize(600, 600);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null); // Giữ nguyên null layout như bạn mong muốn
        setLocationRelativeTo(null); // Center

        // Header
        header = new JPanel();
        header.setBackground(new Color(203, 45, 62));
        header.setBounds(0, 0, 600, 60);
        JLabel headerLabel = new JLabel("Donation Progress");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        header.add(headerLabel);
        add(header);

        // Progress Bar
        progress = new JProgressBar(0, 100);
        progress.setBounds(50, 100, 500, 30);
        progress.setValue(50);
        progress.setStringPainted(true);
        add(progress);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Donate dialog = new Donate(null, true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }
}