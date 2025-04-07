/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package charity.UserController;

import charity.model.CharityEvent;
import charity.model.Donation;
import charity.service.AccountService;
import charity.service.CharityEventService;
import charity.service.DonationService;
import charity.utils.MessageDialog;
import com.utc2.charity.controller.IFormatData;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;
import java.util.Calendar;
import javax.swing.*;

/**
 *
 * @author phaml
 */
public class DonationDialogController implements IFormatData {

    private int accountId, userId;
    private CharityEvent event;
    private CharityEventService eventService = new CharityEventService();
    private DonationService donationService = new DonationService();
    private AccountService accountService = new AccountService();

    private JTextField txtEventId;
    private JTextField txtEventName;
    private JTextField txtCategory;
    private JTextField txtTargetAmount;
    private JTextField txtCurrentAmount;
    private JTextField txtProgress;
    private JTextField txtDateBegin;
    private JTextField txtDateEnd;
    private JTextField txtDescription;
    private JTextField txtMoney;
    private JButton jbtDonate;

    public DonationDialogController(int accountId, int userId, CharityEvent event, JTextField txtEventId, JTextField txtEventName, JTextField txtCategory, JTextField txtTargetAmount, JTextField txtCurrentAmount, JTextField txtProgress, JTextField txtDateBegin, JTextField txtDateEnd, JTextField txtDescription, JTextField txtMoney, JButton jbtDonate) {
        this.accountId = accountId;
        this.userId = userId;
        this.event = event;
        this.txtEventId = txtEventId;
        this.txtEventName = txtEventName;
        this.txtCategory = txtCategory;
        this.txtTargetAmount = txtTargetAmount;
        this.txtCurrentAmount = txtCurrentAmount;
        this.txtProgress = txtProgress;
        this.txtDateBegin = txtDateBegin;
        this.txtDateEnd = txtDateEnd;
        this.txtDescription = txtDescription;
        this.txtMoney = txtMoney;
        this.jbtDonate = jbtDonate;
    }

    public void loadEventData() {
        CharityEvent event2 = eventService.getEventById(event.getId());
        event = event2;
        txtEventId.setText(String.valueOf(event2.getId()));
        txtEventName.setText((event2.getName()));
        txtCategory.setText((event2.getCategory()));
        txtCurrentAmount.setText((String.valueOf(moneyFormat.format(event2.getCurrentAmount()))));
        txtTargetAmount.setText((String.valueOf(moneyFormat.format(event2.getTargetAmount()))));
        txtDateBegin.setText(dateFormat.format(event2.getDateBegin()));
        txtDateEnd.setText(dateFormat.format(event2.getDateEnd()));
        txtProgress.setText(String.format("%.2f%%", (float) event2.getCurrentAmount() / event2.getTargetAmount() * 100));
        txtDescription.setText(event2.getDescription());
    }

    public void settingTxtMoney() {
        txtMoney.setText("0");
        txtMoney.setForeground(Color.GRAY);

        txtMoney.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtMoney.getText().equals("0")) {
                    txtMoney.setText("");
                    txtMoney.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtMoney.getText().equals("")) {
                    txtMoney.setText("0");
                    txtMoney.setForeground(Color.GRAY);
                }
            }
        });
    }

    public void setJbtDonateEvent() {
        jbtDonate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!accountService.isUserExist(accountId)) {
                    MessageDialog.showError("Bạn chưa xác thực thông tin");
                    return;
                }

                String moneyStr = txtMoney.getText().trim();
                //kiem tra chuoi rong
                if (moneyStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số tiền quyên góp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //kiem tra so tien hop le
                if (moneyStr.matches(".*[a-zA-Z].*")) {
                    JOptionPane.showMessageDialog(null, "Số tiền nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Long money = Long.parseLong(moneyStr);

                if (money < 2000) {
                    JOptionPane.showMessageDialog(null, "Số tiền quyên góp không bé hơn 2 000 !", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //hien thi thong bao xac nhan
                int accept = JOptionPane.showConfirmDialog(null, "Xác nhận quyên góp!", "Xác nhận quyên góp!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (accept == JOptionPane.OK_OPTION) { //Dong y
                    //lấy thời gian hiện tại
                    long currentTimeMillis = Calendar.getInstance().getTimeInMillis();
                    
                    // Tạo timestamp của SQL
                    Timestamp currentTimestamp = new Timestamp(currentTimeMillis);
                    Donation donation = new Donation(event.getId(), userId, money, currentTimestamp);
                    //them vao danh sach quyen gop
                    if (donationService.addDonation(donation)) {

                        // cap nhap so tien hien tai cua event
                        CharityEvent event2 = eventService.getEventById(event.getId());
                        event2.setCurrentAmount(event2.getCurrentAmount() + money);
                        if (eventService.updateEvent(event2)) {

                            loadEventData();//cập nhật giao diện
                        } else {
                            JOptionPane.showMessageDialog(null, "Cập nhật số tiền thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm quyên góp thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}
