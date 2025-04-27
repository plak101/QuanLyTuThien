package charity.viewUser;

import charity.component.GPanel;
import charity.component.GProgressBarUI;
import charity.model.CharityEvent;
import charity.service.CharityEventService;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public final class DonateDialog extends JDialog {

    private int eventId, accountId, userId;
    private GPanel gpanel;
    private JTextField txtId, txtEventName, txtOrganizationName, txtCategory, txtCurrentAmount, txtTargetAmount, txtDateEnd, txtDateBegin, txtMoney;
    private JTextArea txtDescription, txtDonateDescription;
    private JLabel jlbProgress;
    private float progress;
    private CharityEvent event;
    private CharityEventService eventService = new CharityEventService();

    public DonateDialog(JFrame frame, boolean modal, int eventId, int accountId, int userId) {
        super(frame, modal);
        this.eventId = eventId;
        this.accountId = accountId;
        this.userId = userId;
        init();
    }

    public void init() {
        Font font28 = new Font("Segoe UI", Font.BOLD, 28);
        Font font22B = new Font("Segoe UI", Font.BOLD, 22);
        Font font14B = new Font("Segoe UI", Font.BOLD, 14);
        Font font12B = new Font("Segoe UI", Font.BOLD, 12);
        Font font14 = new Font("Segoe UI", Font.PLAIN, 14);

        event = eventService.getEventById(eventId);

        setSize(660, 600);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        gpanel = new GPanel();
        gpanel.changeColor("#ffffff", "#ffffff");
        gpanel.setLayout(null);

        GPanel headPanel = new GPanel();
        headPanel.changeColor("#cb2d3e", "#ef473a");
        headPanel.changeRoundRect(10, 10);
        headPanel.setBounds(0, 0, 646, 60);
        headPanel.setLayout(null);

        JLabel jlbHeader = new JLabel("HOẠT ĐỘNG QUYÊN GÓP");
        jlbHeader.setBounds(0, 0, 660, 60);
        jlbHeader.setFont(font22B);
        jlbHeader.setHorizontalAlignment(JLabel.CENTER);
        jlbHeader.setForeground(Color.WHITE);
        headPanel.add(jlbHeader);

        //tính tiến độ
        progress = (float) event.getCurrentAmount() / event.getTargetAmount() * 100;
        //label tiến độ
        String text = String.format("%.2f%%", progress);
        jlbProgress = new JLabel(text);
        jlbProgress.setBounds(60, 70, 50, 20);
        jlbProgress.setFont(font14B);
        gpanel.add(jlbProgress);
        //thanh tiến độ
        JProgressBar jpbProgress = new JProgressBar();
        jpbProgress.setUI(new GProgressBarUI());
        jpbProgress.setBounds(110, 70, 600 - 60 * 2, 20);

        jpbProgress.setValue((int) progress);
        jpbProgress.setStringPainted(false);//tat chu
        jpbProgress.setForeground(Color.decode("#006666"));
        jpbProgress.setBackground(Color.decode("#ffffff"));
        UIManager.put("ProgressBar.selectionForeground", Color.BLACK);//set màu chữ phần trăm
        UIManager.put("ProgressBar.selectionBackground", Color.LIGHT_GRAY);
        jpbProgress.setBorderPainted(false);
        gpanel.add(jpbProgress);

        //withd label
        int wl = 60, wt = 200;

        JLabel jlbId = new JLabel("ID");
        jlbId.setFont(font14);
        jlbId.setBounds(40, 100, wl, 25);
        gpanel.add(jlbId);

        txtId = new JTextField();
        txtId.setFont(font14);
        txtId.setBounds(40 + wl + 10, 100, wt, 25);
        gpanel.add(txtId);

        //to chuc
        JLabel jlbOrganizationName = new JLabel("Tổ chức");
        jlbOrganizationName.setFont(font14);
        jlbOrganizationName.setBounds(40, 140, wl, 25);
        gpanel.add(jlbOrganizationName);

        txtOrganizationName = new JTextField();
        txtOrganizationName.setFont(font14);
        txtOrganizationName.setBounds(40 + wl + 10, 140, wt, 25);
        gpanel.add(txtOrganizationName);

        //su kien
        JLabel jlbEventName = new JLabel("Sự kiện");
        jlbEventName.setFont(font14);
        jlbEventName.setBounds(40, 180, wl, 25);
        gpanel.add(jlbEventName);

        txtEventName = new JTextField();
        txtEventName.setFont(font14);
        txtEventName.setBounds(40 + wl + 10, 180, wt, 25);
        gpanel.add(txtEventName);

        //loai
        JLabel jlbCategory = new JLabel("Loại");
        jlbCategory.setFont(font14);
        jlbCategory.setBounds(40, 220, wl, 25);
        gpanel.add(jlbCategory);

        txtCategory = new JTextField();
        txtCategory.setFont(font14);
        txtCategory.setBounds(40 + wl + 10, 220, wt, 25);
        gpanel.add(txtCategory);
        
        //Muc tieu
        JLabel jlbTargetAmount = new JLabel("Mục tiêu");
        jlbTargetAmount.setFont(font14);
        jlbTargetAmount.setBounds(40, 260, wl, 25);
        gpanel.add(jlbTargetAmount);

        txtTargetAmount = new JTextField();
        txtTargetAmount.setFont(font14);
        txtTargetAmount.setBounds(40 + wl + 10, 260, wt, 25);
        gpanel.add(txtTargetAmount);
        
        //hien tai
        JLabel jlbCurrentAmount = new JLabel("Hiện tại");
        jlbCurrentAmount.setFont(font14);
        jlbCurrentAmount.setBounds(350, 260, wl, 25);
        gpanel.add(jlbCurrentAmount);

        txtCurrentAmount = new JTextField();
        txtCurrentAmount.setFont(font14);
        txtCurrentAmount.setBounds(350 + wl + 10, 260, wt, 25);
        gpanel.add(txtCurrentAmount);
        
        //bat dau
        JLabel jlbDateBegin = new JLabel("Bắt đầu");
        jlbDateBegin.setFont(font14);
        jlbDateBegin.setBounds(40, 300, wl, 25);
        gpanel.add(jlbDateBegin);

        txtDateBegin = new JTextField();
        txtDateBegin.setFont(font14);
        txtDateBegin.setBounds(40 + wl + 10, 300, wt, 25);
        gpanel.add(txtDateBegin);
        
        //ket thuc
        JLabel jlbDateEnd = new JLabel("Kết thúc");
        jlbDateEnd.setFont(font14);
        jlbDateEnd.setBounds(350, 300, wl, 25);
        gpanel.add(jlbDateEnd);

        txtDateEnd = new JTextField();
        txtDateEnd.setFont(font14);
        txtDateEnd.setBounds(350 + wl + 10, 300, wt, 25);
        gpanel.add(txtDateEnd);
        
        //ghi chú
        JLabel jlbDescription = new JLabel("Mô tả");
        jlbDescription.setFont(font14);
        jlbDescription.setBounds(40, 340, 600, 25);
        gpanel.add(jlbDescription);

        txtDescription = new JTextArea();
        txtDescription.setFont(font14);
        txtDescription.setLineWrap(true);//tự động xuống dòng
        txtDescription.setWrapStyleWord(true);//xuống dòng tại khoảng trắng
        JScrollPane scrollPane= new JScrollPane(txtDescription);
        scrollPane.setBounds(40 + wl + 10, 340, 510, 50);
        gpanel.add(scrollPane);
        
        //nhap so tien
        JLabel jlbMoney = new JLabel("Số tiền");
        jlbDateBegin.setFont(font14);
        jlbDateBegin.setBounds(40, 300, wl, 25);
        gpanel.add(jlbDateBegin);

        txtDateBegin = new JTextField();
        txtDateBegin.setFont(font14);
        txtDateBegin.setBounds(40 + wl + 10, 300, wt, 25);
        gpanel.add(txtDateBegin);
        //end
        gpanel.add(headPanel);
        add(gpanel);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new DonateDialog(null, true, 2, 3, 3).setVisible(true);
    }

}
