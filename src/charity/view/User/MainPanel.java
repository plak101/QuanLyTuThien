package charity.view.User;

import charity.component.ColorCustom;
import charity.component.FontCustom;
import charity.component.GButton;
import charity.component.ImageIconCustom;
import charity.component.MapHelper;
import charity.controller.UserController.MainPanelController;
import charity.model.Category;
import charity.model.CharityEvent;
import charity.service.CategoryService;
import charity.utils.MessageDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.net.URL;
import java.util.List;
import javax.swing.JButton;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainPanel extends JPanel {
    private JFrame frame;
    private int accountId, userId;
    private JPanel campaignsPanel;
    private JButton btnPrev;
    private JButton btnNext;
    private MainPanelController controller;
    private CategoryService categoryService = new CategoryService();

    public MainPanel(JFrame frame, int accountId, int userId){
        this.frame = frame;
        this.accountId= accountId;
        this.userId = userId;
        init();
        this.controller = new MainPanelController(this);
    }

    private void init() {
        setLayout(new BorderLayout(0, 10));
//        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(Color.white);

        // tieu de
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        //panel chua noi dung chinh
        JPanel contentPanel = new JPanel(new BorderLayout(0, 10));
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 40));
        contentPanel.setBackground(Color.white);

        //panel category
        JPanel categoryPanel = createCategoryPanel();
        contentPanel.add(categoryPanel, BorderLayout.NORTH);

        //panel chua noi dung va nut dieu hương
        JPanel centerPanel = new JPanel(new BorderLayout(0, 0));
        centerPanel.setBorder(new EmptyBorder(0, 30, 0, 20));
        centerPanel.setBackground(Color.white);

        btnPrev = createNavigationButton("←");
        btnNext = createNavigationButton("→");

        JPanel westPanel = new JPanel(new GridBagLayout());
        westPanel.setBackground(Color.white);
        westPanel.add(btnPrev);
        JPanel eastPanel = new JPanel(new GridBagLayout());
        eastPanel.setBackground(Color.white);
        eastPanel.add(btnNext);

        //panel noi dung
        campaignsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 0));
        campaignsPanel.setBorder(new EmptyBorder(70, 10, 10, 10));
        campaignsPanel.setBackground(Color.white);

        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.setBackground(Color.white);
        containerPanel.add(campaignsPanel, BorderLayout.CENTER);

        //them cac panel vao centerPanel
        centerPanel.add(westPanel, BorderLayout.WEST);
        centerPanel.add(eastPanel, BorderLayout.EAST);
        centerPanel.add(campaignsPanel, BorderLayout.CENTER);

        contentPanel.add(centerPanel, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);

    }

    //tao header   
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                //tao hinh nen
                URL imgURL = getClass().getResource("/charity/image/background.jpg");
                if (imgURL == null) {
                    MessageDialog.showError("Khong tim thay anh: /charity/image/background.jpg");
                }
                ImageIcon backgroundImage = new ImageIcon(imgURL);
                Image img = backgroundImage.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };

        //tao panel cho tieu de voi nen trong suot
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);

        //tao va tuy chinh label tieu de
        JLabel lblTitle = new JLabel("Trang Chủ");
        lblTitle.setFont(FontCustom.Arial40B());
        lblTitle.setForeground(Color.red);
        lblTitle.setBorder(new EmptyBorder(20, 0, 20, 0));//padding

        titlePanel.add(lblTitle);
        headerPanel.add(titlePanel, BorderLayout.CENTER);
        return headerPanel;
    }

    //tao panel danh muc
    private JPanel createCategoryPanel() {
        JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        categoryPanel.setBackground(Color.WHITE);

        List<Category> categorys = categoryService.getAllCategories();
        //tao nut "Tất cả"
        JButton btnCategory = new JButton("Tất cả");
        btnCategory.setFocusPainted(false);
        btnCategory.setBackground(Color.WHITE);
        btnCategory.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        //add event
        btnCategory.addActionListener(e -> controller.filterByCategory("Tất cả"));

        categoryPanel.add(btnCategory);

        for (Category c : categorys) {
            btnCategory = new JButton(c.getCategoryName());
            btnCategory.setFocusPainted(false);
            btnCategory.setBackground(Color.WHITE);
            btnCategory.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200)),
                    BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));

            //add event
            btnCategory.addActionListener(e -> controller.filterByCategory(c.getCategoryName()));

            categoryPanel.add(btnCategory);
        }
        return categoryPanel;
    }

    //tao nut dieu hương
    private JButton createNavigationButton(String text) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(40, 40));
        btn.setFont(FontCustom.Arial18B());
        btn.setFocusPainted(false);
        btn.setBackground(new Color(252, 252, 252));
//        btn.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        //bo goc
        btn.putClientProperty("JButton.arc", 15);
        //event
        if (text.equals("←")) {
            btn.addActionListener(e -> controller.showPreviousPage());
        } else {
            btn.addActionListener(e -> controller.showNextPage());
        }
        return btn;
    }

    //tao card event
    public JPanel createCampaignCard(CharityEvent event) {
        JPanel card = createMainCardPanel();
        card.add(createImagePanel(event));
        card.add(createInforPanel(event));
        card.add(createViewButton(event));
        return card;
    }

    // panel chinh cardevent
    private JPanel createMainCardPanel() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(250, 300));
        card.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        card.setBackground(Color.white);
        return card;
    }

    //panel anh
    private JPanel createImagePanel(CharityEvent event) {
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setPreferredSize(new Dimension(250, 160));
        imagePanel.setBackground(Color.white);

        ImageIcon image = ImageIconCustom.getSmoothIcon(event.getImageUrl(), 250, 160);
        JLabel imageLabel = new JLabel(image);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.TOP); // căn trên
        imagePanel.setMaximumSize(new Dimension(250, 160)); // cố định kích thước
        imagePanel.setAlignmentY(Component.TOP_ALIGNMENT);
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        return imagePanel;
    }

    private JPanel createInforPanel(CharityEvent event) {
        JPanel inforPanel = new JPanel();
        inforPanel.setLayout(new BoxLayout(inforPanel, BoxLayout.Y_AXIS));
        inforPanel.setBorder(new EmptyBorder(10, 5, 5, 10));
        inforPanel.setBackground(Color.white);

        //them cac thanh phhan
        inforPanel.add(createCategoryLabel(event));
        inforPanel.add(Box.createVerticalStrut(5));
        inforPanel.add(createTitleLabel(event));
        inforPanel.add(Box.createVerticalStrut(5));
        inforPanel.add(createProgressBar(event));
        inforPanel.add(Box.createVerticalStrut(5));
        inforPanel.add(createAmountLabel(event));

        return inforPanel;
    }

    //tao panel ten su kién
    private JPanel createCategoryLabel(CharityEvent event) {
        JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        categoryPanel.setBackground(Color.white);
        categoryPanel.setMaximumSize(new Dimension(200, 15));

        JLabel lbl = new JLabel("Danh mục: " + MapHelper.getCategoryName(event.getCategoryId()));
        lbl.setFont(FontCustom.Arial12());
        lbl.setForeground(new Color(100, 100, 100));
        categoryPanel.add(lbl);

        return categoryPanel;
    }

    //CardEvent: tieu de event
    private JPanel createTitleLabel(CharityEvent event) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.setBackground(Color.white);
        panel.setMaximumSize(new Dimension(200, 35));

        JLabel lbl = new JLabel(event.getName());
        lbl.setFont(FontCustom.Arial13B());
        panel.add(lbl);

        return panel;
    }

    //tao thanh tien do 
    private JPanel createProgressBar(CharityEvent event) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.setBackground(Color.white);
        panel.setMaximumSize(new Dimension(200, 15));

        JProgressBar bar = new JProgressBar();
        bar.setPreferredSize(new Dimension(200, 12));

        double progress = (double) event.getCurrentAmount() / event.getTargetAmount() * 100;
        bar.setMaximum(100);
        bar.setValue((int) progress);
        bar.setStringPainted(true);
        bar.setString(String.format("%.0f%%", progress));

        bar.setForeground(new Color(46, 139, 87));
        bar.setBackground(new Color(240, 240, 240));

        panel.add(bar);
        return panel;
    }

    private JPanel createAmountLabel(CharityEvent event) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.setBackground(Color.white);
        panel.setMaximumSize(new Dimension(200, 15));

        //format so tien
        String amount = String.format("%,d / %,d VNĐ",
                event.getCurrentAmount(),
                event.getTargetAmount()
        );
        JLabel lbl = new JLabel(amount);
        lbl.setFont(FontCustom.Arial11());

        panel.add(lbl);
        return panel;
    }

    //tao btn xem thong tin
    private JPanel createViewButton(CharityEvent event) {
        JPanel viewPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        viewPanel.setBackground(Color.white);
        viewPanel.setBorder(new EmptyBorder(5, 0, 0, 0));
        GButton viewButton = new GButton("Xem thông tin");
        viewButton.setPreferredSize(new Dimension(200, 25));
        viewButton.setHover(ColorCustom.defaultBtn(), ColorCustom.defaultBtnHover());
        //event
        viewButton.addActionListener(e->controller.showEventDialog(frame, event, accountId, userId));
        
        viewPanel.add(viewButton);
        return viewPanel;
    }

    public void updateCampaignCards(List<CharityEvent> events) {
        campaignsPanel.removeAll();
        for (CharityEvent event : events) {
            campaignsPanel.add(createCampaignCard(event));
        }

        campaignsPanel.revalidate();
        campaignsPanel.repaint();
    }

    //kiem tra khi chuyen trang
    public void updateNavigationButton(boolean canGoPrevious, boolean canGoNext) {
        if (btnPrev != null) {
            btnPrev.setEnabled(canGoPrevious);
        }
        if (btnNext != null) {
            btnNext.setEnabled(canGoNext);
        }
    }

    //hien thi khi danh muc khong co event
    public void showNoEventMessage() {
        JPanel messagePanel = new JPanel(new GridBagLayout());
        messagePanel.setPreferredSize(new Dimension(campaignsPanel.getWidth(), 300));
        messagePanel.setBackground(Color.white);

        JLabel messageLabel = new JLabel("Không có sự kiện nào trong danh mục này");
        messageLabel.setFont(FontCustom.Arial14B());
        messageLabel.setForeground(Color.gray);

        messagePanel.add(messageLabel);

        campaignsPanel.removeAll();
        campaignsPanel.add(messagePanel);
        campaignsPanel.revalidate();
        campaignsPanel.repaint();
    }

    public MainPanelController getController() {
        return controller;
    }
    
}
