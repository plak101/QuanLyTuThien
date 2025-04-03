package charity.viewUser;

import charity.model.CharityEvent;
import charity.model.User;
import charity.repository.CharityEventRepository;
import charity.repository.UserRepository;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.*;

/**
 *
 * @author phaml
 */
public class UserUI extends javax.swing.JFrame {

    private List<CharityEvent> eventList = new ArrayList();
    DefaultTableModel eventModel;
    CharityEventRepository eventDAO = new CharityEventRepository();
    UserRepository userDAO = new UserRepository();
    private int pos = -1;
    private int userId = 1;
    private int selectedEventId = -1;
    //định dạng số và ngày
    DecimalFormat numberFormat = new DecimalFormat("#,###");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");

    public UserUI() {
        initComponents();
        loadData();
        setupCardLayout();
        setLocationRelativeTo(null);
    }

    //1. load data
    public void loadData() {
        //hien thi ten user
        User user = userDAO.getUserById(userId);
        txtUserName.setText(user.getName());

        //hien thi du lieu len table
//        showEventTable();
//        showDonationListTable();
//        showMyDonationTable();
    }

    //2. cai dat cardlayout
    public void setupCardLayout() {
        jpnRight.setLayout(new CardLayout());

        //1. Main 
        MainPanel mainPanel = new MainPanel(this, userId);
        jpnRight.add(mainPanel, "mainPanel");

        jpnRight.add(jpnDonation, "donationList");
        jpnRight.add(jpnMyDonation, "myDonationList");
//        jpnRight.add(jpnAccount, "account");

        //Profile
        ProfilePanel profilePanel = new ProfilePanel(userId);
        jpnRight.add(profilePanel, "profilePanel");
    }

//    //#HIEN THI DONATION LIST
//    public void showDonationListTable() {
//        Connection conn = null;
//        ResultSet rs = null;
//
//        try {
//            conn = ConnectionDB.getConnection();
//            String cauLenhSQL = ""
//                    + "SELECT donationId, eventName, userName, amount, donationDate\n"
//                    + "FROM qltt.donation d\n"
//                    + "join user u on d.userId= u.userId\n"
//                    + "join event e on e.eventId = d.eventId\n"
//                    + "order by donationId asc";
//
//            rs = ConnectionDB.executeSelect(cauLenhSQL);
//
//            DefaultTableModel donationListModel = (DefaultTableModel) donationListTable.getModel();
//            Object[] obj = new Object[9];
//            while (rs.next()) {
//
//                obj[0] = rs.getInt("donationId");
//                obj[1] = rs.getString("eventName");
//                obj[2] = rs.getString("userName");
//                obj[3] = numberFormat.format(rs.getLong("amount"));
//                obj[4] = dateFormat.format(rs.getDate("donationDate"));
//
//                donationListTable.setModel(donationListModel);
//                donationListModel.addRow(obj);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserUI.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(new JFrame(), "Lỗi truy xuất");
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(UserUI.class.getName()).log(Level.SEVERE, "Lỗi đóng kết nối!", ex);
//            }
//        }
//
//    }
//    public void showMyDonationTable() {
//        Connection conn = null;
//        ResultSet rs = null;
//
//        try {
//            conn = ConnectionDB.getConnection();
//            String cauLenhSQL = "SELECT donationId, eventName, userName, amount, donationDate\n"
//                    + "FROM qltt.donation d\n"
//                    + "join user u on d.userId= u.userId\n"
//                    + "join event e on e.eventId = d.eventId\n"
//                    + "where d.userId =1\n"
//                    + "order by donationId asc;";
//            rs = ConnectionDB.executeSelect(cauLenhSQL);
//
//            DefaultTableModel myDonationModel = (DefaultTableModel) myDonationTable.getModel();
//            Object[] obj = new Object[9];
//            while (rs.next()) {
//
//                obj[0] = rs.getInt("donationId");
//                obj[1] = rs.getString("eventName");
//                obj[2] = rs.getString("userName");
//                obj[3] = numberFormat.format(rs.getLong("amount"));
//                obj[4] = dateFormat.format(rs.getDate("donationDate"));
//
//                myDonationTable.setModel(myDonationModel);
//                myDonationModel.addRow(obj);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserUI.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(new JFrame(), "Lỗi truy xuất");
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(UserUI.class.getName()).log(Level.SEVERE, "Lỗi đóng kết nối!", ex);
//            }
//        }
//
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu1 = new java.awt.PopupMenu();
        jpnRight = new javax.swing.JPanel();
        jpnMyDonation = new javax.swing.JPanel();
        jpnDonation = new javax.swing.JPanel();
        jpnLeft = new charity.viewUser.GradientPanel();
        jPanel2 = new javax.swing.JPanel();
        txtUserName = new javax.swing.JTextField();
        jpnMainOption = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jpnDonationOption = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jpnMyDonationOption = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jpnInforOption = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        popupMenu1.setLabel("popupMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1300, 600));

        jpnRight.setPreferredSize(new java.awt.Dimension(1300, 840));
        jpnRight.setLayout(new java.awt.CardLayout());

        jpnMyDonation.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jpnMyDonationLayout = new javax.swing.GroupLayout(jpnMyDonation);
        jpnMyDonation.setLayout(jpnMyDonationLayout);
        jpnMyDonationLayout.setHorizontalGroup(
            jpnMyDonationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpnMyDonationLayout.setVerticalGroup(
            jpnMyDonationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jpnRight.add(jpnMyDonation, "card4");

        jpnDonation.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout jpnDonationLayout = new javax.swing.GroupLayout(jpnDonation);
        jpnDonation.setLayout(jpnDonationLayout);
        jpnDonationLayout.setHorizontalGroup(
            jpnDonationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpnDonationLayout.setVerticalGroup(
            jpnDonationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jpnRight.add(jpnDonation, "card3");

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(250, 40));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        txtUserName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUserName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUserName.setText("jTextField1");
        txtUserName.setFocusable(false);
        txtUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtUserName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnMainOption.setOpaque(false);
        jpnMainOption.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnMainOptionMouseClicked(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Trang chủ");

        javax.swing.GroupLayout jpnMainOptionLayout = new javax.swing.GroupLayout(jpnMainOption);
        jpnMainOption.setLayout(jpnMainOptionLayout);
        jpnMainOptionLayout.setHorizontalGroup(
            jpnMainOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnMainOptionLayout.setVerticalGroup(
            jpnMainOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jpnDonationOption.setOpaque(false);
        jpnDonationOption.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnDonationOptionMouseClicked(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Danh sách quyên góp");

        javax.swing.GroupLayout jpnDonationOptionLayout = new javax.swing.GroupLayout(jpnDonationOption);
        jpnDonationOption.setLayout(jpnDonationOptionLayout);
        jpnDonationOptionLayout.setHorizontalGroup(
            jpnDonationOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnDonationOptionLayout.setVerticalGroup(
            jpnDonationOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jpnMyDonationOption.setOpaque(false);
        jpnMyDonationOption.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnMyDonationOptionMouseClicked(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Lịch sử quyên góp");

        javax.swing.GroupLayout jpnMyDonationOptionLayout = new javax.swing.GroupLayout(jpnMyDonationOption);
        jpnMyDonationOption.setLayout(jpnMyDonationOptionLayout);
        jpnMyDonationOptionLayout.setHorizontalGroup(
            jpnMyDonationOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnMyDonationOptionLayout.setVerticalGroup(
            jpnMyDonationOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jpnInforOption.setOpaque(false);
        jpnInforOption.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnInforOptionMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Thông tin cá nhân");

        javax.swing.GroupLayout jpnInforOptionLayout = new javax.swing.GroupLayout(jpnInforOption);
        jpnInforOption.setLayout(jpnInforOptionLayout);
        jpnInforOptionLayout.setHorizontalGroup(
            jpnInforOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnInforOptionLayout.setVerticalGroup(
            jpnInforOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpnLeftLayout = new javax.swing.GroupLayout(jpnLeft);
        jpnLeft.setLayout(jpnLeftLayout);
        jpnLeftLayout.setHorizontalGroup(
            jpnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnLeftLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jpnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnDonationOption, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnMainOption, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jpnLeftLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jpnInforOption, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnLeftLayout.createSequentialGroup()
                .addComponent(jpnMyDonationOption, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jpnLeftLayout.setVerticalGroup(
            jpnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnLeftLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113)
                .addComponent(jpnMainOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jpnDonationOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnMyDonationOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnInforOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(231, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jpnRight, javax.swing.GroupLayout.PREFERRED_SIZE, 1027, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnRight, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jpnLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //click vao panel trong menu
    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        //        CardLayout cardLayout = (CardLayout) jpnRight.getLayout();
        //        cardLayout.show(jpnRight, "donationList");
        //        jbtTrangChu.setBackground(Color.WHITE);
        //        jbtDonationList.setBackground(Color.LIGHT_GRAY);
        //        jbtMyDonationList.setBackground(Color.WHITE);
    }//GEN-LAST:event_jPanel2MouseClicked

    //
    private void txtUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserNameActionPerformed

    private void jpnMainOptionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnMainOptionMouseClicked
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CardLayout cardLayout = (CardLayout) jpnRight.getLayout();
                cardLayout.show(jpnRight, "mainPanel");

                jpnMainOption.setOpaque(true);
                jpnDonationOption.setOpaque(false);
                jpnMyDonationOption.setOpaque(false);
                jpnInforOption.setOpaque(false);

                jpnMainOption.setBackground(Color.decode("#006666"));
                jpnMainOption.repaint();
                jpnDonationOption.repaint();
                jpnMyDonationOption.repaint();
                jpnInforOption.repaint();
            }
        });
    }//GEN-LAST:event_jpnMainOptionMouseClicked

    private void jpnDonationOptionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnDonationOptionMouseClicked
        CardLayout cardLayout = (CardLayout) jpnRight.getLayout();
        cardLayout.show(jpnRight, "donationList");

        jpnMainOption.setOpaque(false);
        jpnDonationOption.setOpaque(true);
        jpnMyDonationOption.setOpaque(false);
        jpnInforOption.setOpaque(false);

        jpnDonationOption.setBackground(Color.decode("#006666"));
        //ve lai
        jpnMainOption.repaint();
        jpnDonationOption.repaint();
        jpnMyDonationOption.repaint();
        jpnInforOption.repaint();
    }//GEN-LAST:event_jpnDonationOptionMouseClicked

    private void jpnMyDonationOptionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnMyDonationOptionMouseClicked
        CardLayout cardLayout = (CardLayout) jpnRight.getLayout();
        cardLayout.show(jpnRight, "myDonationList");

        jpnMainOption.setOpaque(false);
        jpnDonationOption.setOpaque(false);
        jpnMyDonationOption.setOpaque(true);
        jpnInforOption.setOpaque(false);

        jpnMyDonationOption.setBackground(Color.decode("#006666"));
        //ve lai
        jpnMainOption.repaint();
        jpnDonationOption.repaint();
        jpnMyDonationOption.repaint();
        jpnInforOption.repaint();
    }//GEN-LAST:event_jpnMyDonationOptionMouseClicked

    private void jpnInforOptionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnInforOptionMouseClicked
        CardLayout cardLayout = (CardLayout) jpnRight.getLayout();
        cardLayout.show(jpnRight, "profilePanel");

        jpnMainOption.setOpaque(false);
        jpnDonationOption.setOpaque(false);
        jpnMyDonationOption.setOpaque(false);
        jpnInforOption.setOpaque(true);

        jpnInforOption.setBackground(Color.decode("#006666"));
        //ve lai
        jpnMainOption.repaint();
        jpnDonationOption.repaint();
        jpnMyDonationOption.repaint();
        jpnInforOption.repaint();
    }//GEN-LAST:event_jpnInforOptionMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jpnDonation;
    private javax.swing.JPanel jpnDonationOption;
    private javax.swing.JPanel jpnInforOption;
    private charity.viewUser.GradientPanel jpnLeft;
    private javax.swing.JPanel jpnMainOption;
    private javax.swing.JPanel jpnMyDonation;
    private javax.swing.JPanel jpnMyDonationOption;
    private javax.swing.JPanel jpnRight;
    private java.awt.PopupMenu popupMenu1;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
