package charity.view.User;

import charity.controller.UserController.UserUIController;
import charity.component.ImageIconCustom;
import charity.component.IFormatData;
import charity.service.UserService;

/**
 *
 * @author phaml
 */
public class UserUI extends javax.swing.JFrame implements IFormatData {

    private int accountId;
    private UserService userService;
    private UserUIController controller;

    public UserUI(int accountId) {
        this.accountId = accountId;
        initComponents();

        controller = new UserUIController(this, accountId,jpnMainOption, jpnEventOption, jpnOrganizationOption, jpnDonationOption, jpnMyDonationOption, jpnInforOption, jpnRight, txtUserName, jlbLogout);
        controller.loadData();
        controller.setMouseEvent();

        init();
        setLocationRelativeTo(null);
    }

    public void init() {
        jLabel6.setIcon(ImageIconCustom.getSmoothIcon("/charity/icon/user.png", 70, 70));
        
        jLabel9.setIcon(ImageIconCustom.getSmoothIcon("/charity/icon/home.png", 24, 24));
        jLabel1.setIcon(ImageIconCustom.getSmoothIcon("/charity/icon/home.png", 24, 24));
        jLabel3.setIcon(ImageIconCustom.getSmoothIcon("/charity/icon/list2.png", 24, 24));
        jLabel5.setIcon(ImageIconCustom.getSmoothIcon("/charity/icon/non-profit-organization (2).png", 24, 24));
        jLabel2.setIcon(ImageIconCustom.getSmoothIcon("/charity/icon/user2.png", 24, 24));
        jLabel4.setIcon(ImageIconCustom.getSmoothIcon("/charity/icon/recent.png", 24, 24));

        jlbLogout.setIcon(ImageIconCustom.getSmoothIcon("/charity/icon/logout2.png", 20, 20));
    }

    public UserUIController getController() {
        return controller;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu1 = new java.awt.PopupMenu();
        jpnRight = new javax.swing.JPanel();
        jpnLeft = new charity.view.User.GradientPanel();
        jPanel2 = new javax.swing.JPanel();
        txtUserName = new javax.swing.JTextField();
        jpnDonationOption = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jpnMyDonationOption = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jpnInforOption = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jlbLogout = new javax.swing.JLabel();
        jpnOrganizationOption = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jpnEventOption = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jpnMainOption = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();

        popupMenu1.setLabel("popupMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpnRight.setBackground(new java.awt.Color(255, 255, 255));
        jpnRight.setPreferredSize(new java.awt.Dimension(0, 650));
        jpnRight.setLayout(new java.awt.CardLayout());

        jpnLeft.setName(""); // NOI18N
        jpnLeft.setOpaque(false);

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(250, 40));

        txtUserName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtUserName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUserName.setText("jTextField1");
        txtUserName.setBorder(null);
        txtUserName.setFocusable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtUserName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jpnDonationOption.setOpaque(false);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Danh sách quyên góp");

        javax.swing.GroupLayout jpnDonationOptionLayout = new javax.swing.GroupLayout(jpnDonationOption);
        jpnDonationOption.setLayout(jpnDonationOptionLayout);
        jpnDonationOptionLayout.setHorizontalGroup(
            jpnDonationOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnDonationOptionLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpnDonationOptionLayout.setVerticalGroup(
            jpnDonationOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jpnMyDonationOption.setOpaque(false);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Lịch sử quyên góp");

        javax.swing.GroupLayout jpnMyDonationOptionLayout = new javax.swing.GroupLayout(jpnMyDonationOption);
        jpnMyDonationOption.setLayout(jpnMyDonationOptionLayout);
        jpnMyDonationOptionLayout.setHorizontalGroup(
            jpnMyDonationOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnMyDonationOptionLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpnMyDonationOptionLayout.setVerticalGroup(
            jpnMyDonationOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jpnInforOption.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Thông tin cá nhân");

        javax.swing.GroupLayout jpnInforOptionLayout = new javax.swing.GroupLayout(jpnInforOption);
        jpnInforOption.setLayout(jpnInforOptionLayout);
        jpnInforOptionLayout.setHorizontalGroup(
            jpnInforOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnInforOptionLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpnInforOptionLayout.setVerticalGroup(
            jpnInforOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jlbLogout.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlbLogout.setForeground(new java.awt.Color(255, 255, 255));
        jlbLogout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbLogout.setText("Đăng xuất");

        jpnOrganizationOption.setOpaque(false);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Tổ chức từ thiện");

        jpnEventOption.setOpaque(false);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Sự kiện");

        javax.swing.GroupLayout jpnEventOptionLayout = new javax.swing.GroupLayout(jpnEventOption);
        jpnEventOption.setLayout(jpnEventOptionLayout);
        jpnEventOptionLayout.setHorizontalGroup(
            jpnEventOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnEventOptionLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpnEventOptionLayout.setVerticalGroup(
            jpnEventOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpnOrganizationOptionLayout = new javax.swing.GroupLayout(jpnOrganizationOption);
        jpnOrganizationOption.setLayout(jpnOrganizationOptionLayout);
        jpnOrganizationOptionLayout.setHorizontalGroup(
            jpnOrganizationOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnOrganizationOptionLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jpnEventOption, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnOrganizationOptionLayout.setVerticalGroup(
            jpnOrganizationOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnOrganizationOptionLayout.createSequentialGroup()
                .addComponent(jpnEventOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("WELCOME");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jpnMainOption.setOpaque(false);

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Trang chủ");

        javax.swing.GroupLayout jpnMainOptionLayout = new javax.swing.GroupLayout(jpnMainOption);
        jpnMainOption.setLayout(jpnMainOptionLayout);
        jpnMainOptionLayout.setHorizontalGroup(
            jpnMainOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnMainOptionLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpnMainOptionLayout.setVerticalGroup(
            jpnMainOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpnLeftLayout = new javax.swing.GroupLayout(jpnLeft);
        jpnLeft.setLayout(jpnLeftLayout);
        jpnLeftLayout.setHorizontalGroup(
            jpnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnInforOption, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnMyDonationOption, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnOrganizationOption, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnDonationOption, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnLeftLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jpnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnLeftLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jpnLeftLayout.createSequentialGroup()
                .addGroup(jpnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnLeftLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jlbLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnLeftLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
            .addGroup(jpnLeftLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jpnMainOption, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnLeftLayout.setVerticalGroup(
            jpnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnLeftLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jpnMainOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpnOrganizationOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpnDonationOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpnMyDonationOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpnInforOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141)
                .addComponent(jlbLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jpnRight, javax.swing.GroupLayout.PREFERRED_SIZE, 1057, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpnLeft.getAccessibleContext().setAccessibleName("");
        jpnLeft.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jlbLogout;
    private javax.swing.JPanel jpnDonationOption;
    private javax.swing.JPanel jpnEventOption;
    private javax.swing.JPanel jpnInforOption;
    private charity.view.User.GradientPanel jpnLeft;
    private javax.swing.JPanel jpnMainOption;
    private javax.swing.JPanel jpnMyDonationOption;
    private javax.swing.JPanel jpnOrganizationOption;
    private javax.swing.JPanel jpnRight;
    private java.awt.PopupMenu popupMenu1;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
