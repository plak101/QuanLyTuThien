package charity.viewUser;

import charity.model.CharityEvent;
import charity.model.Donation;
import charity.repository.CharityEventRepository;
import charity.repository.DonationRepository;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.*;
import java.sql.Date;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DonateJDialog extends javax.swing.JDialog {

    private CharityEvent event;
    private int userId;
    private CharityEventRepository eventDAO = new CharityEventRepository();
    private DonationRepository donationDAO = new DonationRepository();

    //Định dạng số và ngày
    DecimalFormat moneyFormat = new DecimalFormat("#,###");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public DonateJDialog(JFrame parent, boolean modal, CharityEvent event, int userId) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);// set vi tri giua phan tu cha
        
        this.event = event;
        this.userId = userId;
        loadEventData();
        settingTxtMoney();
    }

    public void loadEventData() {
        CharityEvent event2 = eventDAO.getEventById(event.getId());
        event=event2;
        txtEventId.setText(String.valueOf(event2.getId()));
        txtEventName.setText((event2.getName()));
        txtCategory.setText((event2.getCategory()));
        txtCurrentAmount.setText((String.valueOf(moneyFormat.format(event2.getCurrentAmount()))));
        txtTargetAmount.setText((String.valueOf(moneyFormat.format(event2.getTargetAmount()))));
        txtDateBegin.setText(dateFormat.format(event2.getDateBegin()));
        txtDateEnd.setText(dateFormat.format(event2.getDateEnd()));
        txtProcess.setText(String.format("%.2f%%", (float) event2.getCurrentAmount() / event2.getTargetAmount() * 100));
        txtDescription.setText(event2.getDescription());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtEventId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtCategory = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCurrentAmount = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDateBegin = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEventName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTargetAmount = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtProcess = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtDateEnd = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        jbtDonate = new javax.swing.JButton();
        txtMoney = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFocusTraversalPolicyProvider(true);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        txtEventId.setEditable(false);
        txtEventId.setBackground(new java.awt.Color(255, 255, 255));
        txtEventId.setText("jTextField1");
        txtEventId.setName("txtEventId"); // NOI18N
        txtEventId.setPreferredSize(new java.awt.Dimension(100, 22));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("ID");

        txtCategory.setEditable(false);
        txtCategory.setBackground(new java.awt.Color(255, 255, 255));
        txtCategory.setText("jTextField1");
        txtCategory.setPreferredSize(new java.awt.Dimension(100, 22));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Loại");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Số tiền hiện tại");

        txtCurrentAmount.setEditable(false);
        txtCurrentAmount.setBackground(new java.awt.Color(255, 255, 255));
        txtCurrentAmount.setText("jTextField1");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Ngày bắt đầu");

        txtDateBegin.setEditable(false);
        txtDateBegin.setBackground(new java.awt.Color(255, 255, 255));
        txtDateBegin.setText("jTextField1");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Ghi chú");

        txtEventName.setEditable(false);
        txtEventName.setBackground(new java.awt.Color(255, 255, 255));
        txtEventName.setText("jTextField1");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Tên sự kiện");

        txtTargetAmount.setEditable(false);
        txtTargetAmount.setBackground(new java.awt.Color(255, 255, 255));
        txtTargetAmount.setText("jTextField1");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Mục tiêu");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Tiến độ");

        txtProcess.setEditable(false);
        txtProcess.setBackground(new java.awt.Color(255, 255, 255));
        txtProcess.setText("jTextField1");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Ngày kết thúc");

        txtDateEnd.setEditable(false);
        txtDateEnd.setBackground(new java.awt.Color(255, 255, 255));
        txtDateEnd.setText("jTextField1");

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setText("Quyên góp");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtDescription.setEditable(false);
        txtDescription.setBackground(new java.awt.Color(255, 255, 255));
        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane1.setViewportView(txtDescription);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Số tiền quyên góp");

        jbtDonate.setText("Quyên góp");
        jbtDonate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDonateActionPerformed(evt);
            }
        });

        txtMoney.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMoney.setForeground(new java.awt.Color(153, 153, 153));
        txtMoney.setText("0000");
        txtMoney.setToolTipText("");
        txtMoney.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMoneyFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMoneyFocusLost(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(15, 15, 15))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtEventId, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtCurrentAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDateBegin, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTargetAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtEventName, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1)))
                .addGap(16, 16, 16))
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel14)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtDonate, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEventName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEventId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCurrentAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDateBegin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTargetAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProcess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addComponent(jbtDonate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        txtCategory.getAccessibleContext().setAccessibleName("");
        txtMoney.getAccessibleContext().setAccessibleDescription("0");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //setting txt money
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
        {

        };
    }

    private void jbtDonateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDonateActionPerformed
        // TODO add your handling code here:

        String moneyStr = txtMoney.getText().trim();
        //kiem tra chuoi rong
        if (moneyStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số tiền quyên góp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //kiem tra so tien hop le
        if (moneyStr.matches(".*[a-zA-Z].*")) {
            JOptionPane.showMessageDialog(this, "Số tiền nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Long money = Long.parseLong(moneyStr);

        if (money < 2000) {
            JOptionPane.showMessageDialog(this, "Số tiền quyên góp không bé hơn 2 000 !", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //hien thi thong bao xac nhan
        int accept = JOptionPane.showConfirmDialog(null, "Xác nhận quyên góp!", "Xác nhận quyên góp!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (accept == JOptionPane.OK_OPTION) { //Dong y
            //lấy thời gian hiện tại
            long currentTimeMillis = Calendar.getInstance().getTimeInMillis();
            //Tạo date của sql
            Date currentDate = new Date(currentTimeMillis);
            
            Donation donation = new Donation(event.getId(), userId, money, currentDate);
            //them vao danh sach quyen gop
            if (donationDAO.addDonation(donation)) {

                // cap nhap so tien hien tai cua event
                CharityEvent event2 = eventDAO.getEventById(event.getId());
                event2.setCurrentAmount(event2.getCurrentAmount() + money);
                if (eventDAO.updateEvent(event2)) {
                    JOptionPane.showMessageDialog(this, "Quyên góp thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadEventData();//cập nhật giao diện
                }else{
                    JOptionPane.showMessageDialog(this, "Cập nhật số tiền thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(this, "Thêm quyên góp thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }

        System.out.print(moneyStr);
    }//GEN-LAST:event_jbtDonateActionPerformed


    private void txtMoneyFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMoneyFocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_txtMoneyFocusGained

    private void txtMoneyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMoneyFocusLost
        // TODO add your handling code here:
        if (txtMoney.getText().equals("2000")) {
            txtMoney.setText("");
            txtMoney.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_txtMoneyFocusLost

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        this.requestFocus();
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtDonate;
    private javax.swing.JTextField txtCategory;
    private javax.swing.JTextField txtCurrentAmount;
    private javax.swing.JTextField txtDateBegin;
    private javax.swing.JTextField txtDateEnd;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtEventId;
    private javax.swing.JTextField txtEventName;
    private javax.swing.JTextField txtMoney;
    private javax.swing.JTextField txtProcess;
    private javax.swing.JTextField txtTargetAmount;
    // End of variables declaration//GEN-END:variables
}
