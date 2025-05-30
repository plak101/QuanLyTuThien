package UI;

import UI.controller.CampaignController;
import charity.model.CharityEvent;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.border.EmptyBorder;

public class CampaignPanel extends JFrame {
    private JPanel campaignsPanel;
    private CampaignController controller;
    private JButton prevButton;
    private JButton nextButton;
    
    public CampaignPanel() {
        initComponents();
        controller = new CampaignController(this);
    }
    
    private void initComponents() {
        setTitle("Chiến dịch từ thiện");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Panel chính sử dụng BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
        
        // Thêm header panel
        JPanel headerPanel = createHeaderPanel();
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Panel chứa nội dung chính
        JPanel contentPanel = new JPanel(new BorderLayout(0, 10));
        contentPanel.setBorder(new EmptyBorder(0, 10, 10, 10));

        // Panel danh mục
        JPanel categoryPanel = createCategoryPanel();
        contentPanel.add(categoryPanel, BorderLayout.NORTH);

        // Panel chứa nút điều hướng và campaigns
        JPanel centerPanel = new JPanel(new BorderLayout(10, 0));
        
        // Nút Previous - làm hình vuông
        prevButton = createNavigationButton("←");
        
        // Nút Next - làm hình vuông
        nextButton = createNavigationButton("→");

        // Panel chứa các chiến dịch
        campaignsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        campaignsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Tạo một panel trung gian để giữ campaignsPanel
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.setBackground(Color.WHITE);
        containerPanel.add(campaignsPanel, BorderLayout.CENTER);

        // Panel cho nút điều hướng
        JPanel westPanel = new JPanel(new GridBagLayout());
        westPanel.add(prevButton);
        JPanel eastPanel = new JPanel(new GridBagLayout());
        eastPanel.add(nextButton);
        
        // Thêm các thành phần vào centerPanel
        centerPanel.add(westPanel, BorderLayout.WEST);
        centerPanel.add(containerPanel, BorderLayout.CENTER);
        centerPanel.add(eastPanel, BorderLayout.EAST);
        
        contentPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private JButton createNavigationButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(40, 40));
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setFocusPainted(false);
        button.setBackground(new Color(240, 240, 240));
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        
        if (text.equals("←")) {
            button.addActionListener(e -> controller.showPreviousPage());
        } else {
            button.addActionListener(e -> controller.showNextPage());
        }
        
        return button;
    }

    public void updateCampaignCards(List<CharityEvent> events) {
        campaignsPanel.removeAll();
        
        for (CharityEvent event : events) {
            campaignsPanel.add(createCampaignCard(event));
        }
        
        campaignsPanel.revalidate();
        campaignsPanel.repaint();
    }

    private JPanel createCampaignCard(CharityEvent event) {
        // Tạo card chính
        JPanel card = createMainCardPanel();
        
        // Thêm ảnh và thông tin
        card.add(createImagePanel(event));
        card.add(createInfoPanel(event));
        
        return card;
    }

    private JPanel createMainCardPanel() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(200, 280));
        card.setMinimumSize(new Dimension(200, 280));
        card.setMaximumSize(new Dimension(200, 280));
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        card.setBackground(Color.WHITE);
        return card;
    }

    private JPanel createImagePanel(CharityEvent event) {
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setPreferredSize(new Dimension(200, 140));
        imagePanel.setMaximumSize(new Dimension(200, 140));
        imagePanel.setBackground(Color.WHITE);
        
        // Xử lý ảnh
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(event.getImageUrl()));
        Image image = imageIcon.getImage().getScaledInstance(200, 140, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        
        return imagePanel;
    }

    private JPanel createInfoPanel(CharityEvent event) {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        infoPanel.setBackground(Color.WHITE);
//        infoPanel.setPreferredSize(new Dimension(200, 140));
//        infoPanel.setMaximumSize(new Dimension(200, 140));

        // Thêm các thành phần
        infoPanel.add(createCategoryLabel(event));
        infoPanel.add(Box.createVerticalStrut(3));
        infoPanel.add(createTitleLabel(event));
        infoPanel.add(Box.createVerticalStrut(3));
        infoPanel.add(createProgressBar(event));
        infoPanel.add(Box.createVerticalStrut(3));
        infoPanel.add(createAmountLabel(event));

        return infoPanel;
    }

    private JPanel createCategoryLabel(CharityEvent event) {
        JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        categoryPanel.setBackground(Color.WHITE);
        categoryPanel.setMaximumSize(new Dimension(200, 15));
        
        JLabel categoryLabel = new JLabel("Danh mục: " + event.getCategoryId());
        categoryLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        categoryLabel.setForeground(new Color(100, 100, 100));
        categoryPanel.add(categoryLabel);
        
        return categoryPanel;
    }

    private JPanel createTitleLabel(CharityEvent event) {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setMaximumSize(new Dimension(200, 35));
        
        JLabel titleLabel = new JLabel("<html><div style='width: 180px;'>" + event.getName() + "</div></html>");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        titlePanel.add(titleLabel);
        
        return titlePanel;
    }

    private JPanel createProgressBar(CharityEvent event) {
        JPanel progressPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        progressPanel.setBackground(Color.WHITE);
        progressPanel.setMaximumSize(new Dimension(200, 15));
        
        JProgressBar progressBar = new JProgressBar();
        progressBar.setPreferredSize(new Dimension(180, 12));
        
        // Tính toán phần trăm tiến độ
        double progress = (double) event.getCurrentAmount() / event.getTargetAmount() * 100;
        progressBar.setMaximum(100);
        progressBar.setValue((int) progress);
        progressBar.setStringPainted(true);
        progressBar.setString(String.format("%.0f%%", progress));
        
        // Tùy chỉnh màu sắc
        progressBar.setForeground(new Color(46, 139, 87)); // Màu xanh lá cây đậm
        progressBar.setBackground(new Color(240, 240, 240));
        
        progressPanel.add(progressBar);
        return progressPanel;
    }

    private JPanel createAmountLabel(CharityEvent event) {
        JPanel amountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        amountPanel.setBackground(Color.WHITE);
        amountPanel.setMaximumSize(new Dimension(200, 15));
        
        // Format số tiền với dấu phẩy ngăn cách
        String amount = String.format("%,d₫ / %,d₫", 
            event.getCurrentAmount(), 
            event.getTargetAmount());
        
        JLabel amountLabel = new JLabel(amount);
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        amountPanel.add(amountLabel);
        
        return amountPanel;
    }

    private JPanel createCategoryPanel() {
        JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        categoryPanel.setBackground(new Color(245, 245, 245));
        
        String[] categories = {"Trẻ em", "Cộng đồng", "Giáo dục", 
                             "Hoàn cảnh khó khăn", "Môi trường", 
                             "Người già neo đơn", "Thiên tai", "Y tế"};
        
        for (String category : categories) {
            JButton categoryBtn = new JButton(category);
            categoryBtn.setFocusPainted(false);
            categoryBtn.setBackground(Color.WHITE);
            categoryBtn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));
            
            categoryBtn.addActionListener(e -> controller.filterByCategory(category));
            
            categoryPanel.add(categoryBtn);
        }
        
        return categoryPanel;
    }

    public void showNoEventsMessage() {
        campaignsPanel.removeAll();
        
        JPanel messagePanel = new JPanel(new GridBagLayout());
        messagePanel.setBackground(Color.WHITE);
        
        JLabel messageLabel = new JLabel("Không có sự kiện nào trong danh mục này");
        messageLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        messageLabel.setForeground(Color.GRAY);
        
        messagePanel.add(messageLabel);
        campaignsPanel.add(messagePanel);
        
        campaignsPanel.revalidate();
        campaignsPanel.repaint();
    }

    public void updateNavigationButtons(boolean canGoPrevious, boolean canGoNext) {
        if (prevButton != null) prevButton.setEnabled(canGoPrevious);
        if (nextButton != null) nextButton.setEnabled(canGoNext);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Tạo hình nền
                ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/charity/image/background.jpg"));
                Image img = backgroundImage.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        // Đặt kích thước cho header
//        headerPanel.setPreferredSize(new Dimension(800, 100));
        
        // Tạo panel cho tiêu đề với nền trong suốt
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        
        // Tạo và tùy chỉnh label tiêu đề
        JLabel titleLabel = new JLabel("TRANG CHỦ");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE); // Màu chữ trắng
        titleLabel.setBorder(new EmptyBorder(20, 0, 20, 0)); // Padding
        
        // Thêm hiệu ứng bóng cho chữ
        titleLabel.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(20, 0, 20, 0),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        
        titlePanel.add(titleLabel);
        headerPanel.add(titlePanel, BorderLayout.CENTER);
        
        return headerPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CampaignPanel panel = new CampaignPanel();
            panel.setVisible(true);
        });
    }
}