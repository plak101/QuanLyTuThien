import javax.swing.*;
import java.awt.*;

public class DonationForm extends JFrame {

    public DonationForm() {
        setTitle("Quyên góp");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel chính
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Tiêu đề
        JLabel titleLabel = new JLabel("Quyên góp", SwingConstants.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(255, 204, 204));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Panel nhập liệu
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[] labels = {"ID:", "Tên sự kiện:", "Loại:", "Mục tiêu:", "Số tiền hiện tại:", "Tiến độ:", "Ngày bắt đầu:", "Ngày kết thúc:"};
        JTextField[] textFields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            gbc.gridx = (i % 2 == 0) ? 0 : 2;
            gbc.gridy = i / 2;
            formPanel.add(label, gbc);

            textFields[i] = new JTextField("jTextField1");
            gbc.gridx = (i % 2 == 0) ? 1 : 3;
            formPanel.add(textFields[i], gbc);
        }

        // Ghi chú
        JLabel noteLabel = new JLabel("Ghi chú:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(noteLabel, gbc);

        JTextArea noteArea = new JTextArea(3, 20);
        JScrollPane scrollPane = new JScrollPane(noteArea);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        formPanel.add(scrollPane, gbc);

        // Thêm formPanel vào giữa
        panel.add(formPanel, BorderLayout.CENTER);

        // Panel dưới cùng
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcBottom = new GridBagConstraints();
        gbcBottom.insets = new Insets(5, 5, 5, 5);
        gbcBottom.fill = GridBagConstraints.HORIZONTAL;

        JLabel donationLabel = new JLabel("Số tiền quyên góp");
        gbcBottom.gridx = 0;
        gbcBottom.gridy = 0;
        bottomPanel.add(donationLabel, gbcBottom);

        JTextField donationField = new JTextField("0000");
        gbcBottom.gridx = 1;
        gbcBottom.gridy = 0;
        gbcBottom.weightx = 1.0;
        bottomPanel.add(donationField, gbcBottom);

        JButton donateButton = new JButton("Quyên góp");
        gbcBottom.gridx = 0;
        gbcBottom.gridy = 1;
        gbcBottom.gridwidth = 2;
        bottomPanel.add(donateButton, gbcBottom);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Thêm panel chính vào frame
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DonationForm().setVisible(true);
        });
    }
}
