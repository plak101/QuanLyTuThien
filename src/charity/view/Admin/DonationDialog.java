/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package charity.view.Admin;

import charity.component.ColorCustom;
import charity.component.FontCustom;
import charity.component.GButton;
import charity.component.ImageIconCustom;
import charity.controller.AdminController.DonationDialogController;
import charity.model.Donation;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DonationDialog extends JDialog {

    private JTextField txtDonor, txtEvent, txtAmount;
    private DateTimePicker dtpk;
    private JTextArea txtContent;
    private JButton btnUserChoice, btnEventChoice;
    private GButton btnSave, btnCancel;
    private String title;
    private String type;
    private DonationDialogController controller;
    private Donation donation;

    public DonationDialog(JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.title = title;
        this.type = type;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(450, 550));
        setResizable(false);
        setLocationRelativeTo(owner);
        initComponent();
        controller = new DonationDialogController(this, type);

    }

    public DonationDialog(JFrame owner, String title, boolean modal, String type, Donation donation) {
        super(owner, title, modal);
        this.title = title;
        this.type = type;
        this.donation = donation;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(450, 550));
        setResizable(false);
        setLocationRelativeTo(owner);
        initComponent();
        controller = new DonationDialogController(this, type);

    }

    private void initComponent() {
        JPanel pnMain = new JPanel(new BorderLayout());
        pnMain.setBackground(Color.white);
        pnMain.setBorder(new EmptyBorder(0, 0, 0, 0));

        // Header
        pnMain.add(createHeaderPanel(), BorderLayout.NORTH);

        // Center form
        JPanel pnForm = new JPanel(new GridBagLayout());
        pnForm.setBackground(Color.white);
        pnForm.setBorder(new EmptyBorder(5, 10, 5, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Tên người quyên góp
        txtDonor = new JTextField();
        btnUserChoice = new JButton(ImageIconCustom.getSmoothIcon("/charity/icon/more.png", 20, 20));
        pnForm.add(createPanelInputChoice("Người quyên góp", txtDonor, btnUserChoice), gbc);
        gbc.gridy++;

        // Sự kiện
        txtEvent = new JTextField();
        btnEventChoice = new JButton(ImageIconCustom.getSmoothIcon("/charity/icon/more.png", 20, 20));
        pnForm.add(createPanelInputChoice("Sự kiện", txtEvent, btnEventChoice), gbc);
        gbc.gridy++;

        // Số tiền
        txtAmount = new JTextField();
        pnForm.add(createPanelInput("Số tiền", txtAmount), gbc);
        gbc.gridy++;

        // Ngày quyên góp
        DatePickerSettings dateSetting = new DatePickerSettings();
        dateSetting.setFormatForDatesCommonEra("dd/MM/yyyy");

        TimePickerSettings timeSetting = new TimePickerSettings();
        timeSetting.setFormatForDisplayTime("HH:mm:ss");

        //tao datepicker
        dtpk = new DateTimePicker(dateSetting, timeSetting);
        pnForm.add(createPanelDateChoose("Ngày giờ quyên góp", dtpk), gbc);
        gbc.gridy++;

        // Nội dung
        JLabel lbContent = new JLabel("Nội dung");
        lbContent.setFont(FontCustom.Arial13B());
        txtContent = new JTextArea(2, 20);
        txtContent.setFont(FontCustom.Arial13());
        txtContent.setLineWrap(true);
        txtContent.setWrapStyleWord(true);
        JScrollPane scrollContent = new JScrollPane(txtContent);
        scrollContent.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        pnForm.add(lbContent, gbc);
        gbc.gridy++;
        pnForm.add(scrollContent, gbc);

        pnMain.add(pnForm, BorderLayout.CENTER);

        // Buttons
        JPanel pnButton = new JPanel();
        pnButton.setBackground(Color.white);
        pnButton.setBorder(new EmptyBorder(5, 10, 5, 10));
        btnSave = new GButton("Lưu");
        btnSave.setColor(ColorCustom.colorBtnAdd());
        btnSave.setForeground(Color.white);
        btnSave.setFont(FontCustom.Arial13B());
        btnSave.setPreferredSize(new Dimension(120, 30));
        btnSave.setHover(ColorCustom.colorBtnAdd(), ColorCustom.colorBtnAddHover());

        btnCancel = new GButton("Hủy");
        btnCancel.setColor(ColorCustom.colorBtnDelete());
        btnCancel.setForeground(Color.white);
        btnCancel.setFont(FontCustom.Arial13B());
        btnCancel.setPreferredSize(new Dimension(120, 30));
        btnCancel.setHover(ColorCustom.colorBtnDelete(), ColorCustom.colorBtnDeleteHover());

        pnButton.add(btnSave);
        pnButton.add(Box.createHorizontalStrut(20));
        pnButton.add(btnCancel);

        pnMain.add(pnButton, BorderLayout.SOUTH);

        setContentPane(pnMain);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel lbHeader = new JLabel(title, SwingConstants.CENTER);
        lbHeader.setOpaque(true);
        lbHeader.setBackground(ColorCustom.backroundHeaderTitle());
        lbHeader.setForeground(Color.WHITE);
        lbHeader.setFont(FontCustom.Arial18B());
        lbHeader.setPreferredSize(new Dimension(0, 50));
        panel.add(lbHeader, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createPanelInputChoice(String text, JTextField input, JButton btn) {
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 0));
        panel.setPreferredSize(new Dimension(350, 65));
        panel.setOpaque(false);
        //label
        JLabel lb = new JLabel(text);
        lb.setFont(FontCustom.Arial13B());
        panel.add(lb);
        //textField
        input.setPreferredSize(new Dimension(300, 30));
        btn.setPreferredSize(new Dimension(30, 30));
        btn.setFocusPainted(false);

        JPanel pnSub = new JPanel(new BorderLayout());
        pnSub.setOpaque(false);
        pnSub.add(input, BorderLayout.WEST);
        pnSub.add(btn, BorderLayout.EAST);
        panel.add(pnSub);
        return panel;
    }

    private JPanel createPanelInput(String text, JTextField input) {
        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 5));
        panel.setPreferredSize(new Dimension(350, 65));
        panel.setOpaque(false);
        //label
        JLabel lb = new JLabel(text);
        lb.setFont(FontCustom.Arial13B());
        panel.add(lb);
        //textField
        input.setPreferredSize(new Dimension(0, 50));

        panel.add(input);
        return panel;
    }

    private JPanel createPanelDateChoose(String text, DateTimePicker dtpk) {
        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 5));
        panel.setPreferredSize(new Dimension(350, 65));
        panel.setOpaque(false);
        //label
        JLabel lb = new JLabel(text);
        lb.setFont(FontCustom.Arial13B());
        panel.add(lb);
        //textField

        dtpk.setPreferredSize(new Dimension(300, 20));
        panel.add(dtpk);
        return panel;
    }

    public JTextField getTxtDonor() {
        return txtDonor;
    }

    public JTextField getTxtEvent() {
        return txtEvent;
    }

    public JTextField getTxtAmount() {
        return txtAmount;
    }

    public DateTimePicker getDateTimePicker() {
        return dtpk;
    }

    public JTextArea getTxtContent() {
        return txtContent;
    }

    public JButton getBtnUserChoice() {
        return btnUserChoice;
    }

    public JButton getBtnEventChoice() {
        return btnEventChoice;
    }

    public GButton getBtnSave() {
        return btnSave;
    }

    public GButton getBtnCancel() {
        return btnCancel;
    }

    public Donation getDonation() {
        return donation;
    }
    
}
