package charity.viewMain;

import charity.controller.ChuyenManHinh;
import charity.bean.DanhMuc;
import charity.component.ImageIconCustom;
import charity.controller.AdminUIController;
import java.util.ArrayList;
import java.util.List;

public class AdminUI extends javax.swing.JFrame {

    public AdminUI(int accountId) {
        initComponents();

        setTitle("QUẢN LÍ TỪ THIỆN");

        ChuyenManHinh controler = new ChuyenManHinh(jpnView);
        controler.setView(jpnMenu, jibTrangchu2);

        List<DanhMuc> listItem = new ArrayList<>();
        listItem.add(new DanhMuc("TrangChu", jpnTrangchu2, jibTrangchu2));
        listItem.add(new DanhMuc("QuanLyChuongTrinh", jpnQuanLyChuongTrinh, jibQuanLyChuongTrinh));
        listItem.add(new DanhMuc("QuanLyQuyenGop", jpnQuanLyQuyenGop, jibQuanLyQuyenGop));
        listItem.add(new DanhMuc("QuanLyToChuc", jpnQuanLyToChuc, jibQuanLyToChuc));
        listItem.add(new DanhMuc("QuanLyTaiKhoan", jpnTaiKhoan, jibTaiKhoan));

        controler.setEvent(listItem);
        
        AdminUIController adminUIController = new AdminUIController(accountId ,jlbLogout, txtName);
        
        init();
    }

    public void init() {
        jlbImage.setIcon(ImageIconCustom.getSmoothIcon("/charity/icon/AdminTrans.png", 80, 80));
        jibTrangchu2.setIcon(ImageIconCustom.getSmoothIcon("/charity/icon/home.png", 24, 24));
        jibQuanLyChuongTrinh.setIcon(ImageIconCustom.getSmoothIcon("/charity/icon/calendar.png", 24, 24));
        jibQuanLyQuyenGop.setIcon(ImageIconCustom.getSmoothIcon("/charity/icon/list2.png", 24,24));
        jibQuanLyToChuc.setIcon(ImageIconCustom.getSmoothIcon("/charity/icon/non-profit-organization (2).png", 30, 30));
        jibTaiKhoan.setIcon(ImageIconCustom.getSmoothIcon("/charity/icon/people.png", 30, 30));


        jlbLogout.setIcon(ImageIconCustom.getSmoothIcon("/charity/icon/logout2.png", 20, 20));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnView = new javax.swing.JPanel();
        jpnMenu = new charity.viewMain.GradientPanel();
        jpnTrangchu2 = new javax.swing.JPanel();
        jibTrangchu2 = new javax.swing.JLabel();
        jpnQuanLyChuongTrinh = new javax.swing.JPanel();
        jibQuanLyChuongTrinh = new javax.swing.JLabel();
        jpnQuanLyQuyenGop = new javax.swing.JPanel();
        jibQuanLyQuyenGop = new javax.swing.JLabel();
        jpnQuanLyToChuc = new javax.swing.JPanel();
        jibQuanLyToChuc = new javax.swing.JLabel();
        jlbImage = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jpn = new javax.swing.JPanel();
        txtName = new javax.swing.JTextField();
        jlbLogout = new javax.swing.JLabel();
        jpnTaiKhoan = new javax.swing.JPanel();
        jibTaiKhoan = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpnView.setBackground(new java.awt.Color(255, 255, 255));
        jpnView.setAutoscrolls(true);
        jpnView.setPreferredSize(new java.awt.Dimension(800, 500));

        javax.swing.GroupLayout jpnViewLayout = new javax.swing.GroupLayout(jpnView);
        jpnView.setLayout(jpnViewLayout);
        jpnViewLayout.setHorizontalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1045, Short.MAX_VALUE)
        );
        jpnViewLayout.setVerticalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jpnMenu.setPreferredSize(new java.awt.Dimension(250, 650));

        jpnTrangchu2.setBackground(new java.awt.Color(76, 175, 80));

        jibTrangchu2.setBackground(new java.awt.Color(255, 255, 255));
        jibTrangchu2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jibTrangchu2.setForeground(new java.awt.Color(255, 255, 255));
        jibTrangchu2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jibTrangchu2.setText("Trang chủ");
        jibTrangchu2.setMinimumSize(new java.awt.Dimension(90, 35));
        jibTrangchu2.setPreferredSize(new java.awt.Dimension(90, 35));

        javax.swing.GroupLayout jpnTrangchu2Layout = new javax.swing.GroupLayout(jpnTrangchu2);
        jpnTrangchu2.setLayout(jpnTrangchu2Layout);
        jpnTrangchu2Layout.setHorizontalGroup(
            jpnTrangchu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnTrangchu2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jibTrangchu2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpnTrangchu2Layout.setVerticalGroup(
            jpnTrangchu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnTrangchu2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jibTrangchu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jpnQuanLyChuongTrinh.setBackground(new java.awt.Color(76, 175, 80));

        jibQuanLyChuongTrinh.setBackground(new java.awt.Color(255, 255, 255));
        jibQuanLyChuongTrinh.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jibQuanLyChuongTrinh.setForeground(new java.awt.Color(255, 255, 255));
        jibQuanLyChuongTrinh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jibQuanLyChuongTrinh.setText("Quản lý chương trình");
        jibQuanLyChuongTrinh.setPreferredSize(new java.awt.Dimension(189, 36));

        jpnQuanLyQuyenGop.setBackground(new java.awt.Color(76, 175, 80));

        jibQuanLyQuyenGop.setBackground(new java.awt.Color(255, 255, 255));
        jibQuanLyQuyenGop.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jibQuanLyQuyenGop.setForeground(new java.awt.Color(255, 255, 255));
        jibQuanLyQuyenGop.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jibQuanLyQuyenGop.setText("Quản lý quyên góp");
        jibQuanLyQuyenGop.setPreferredSize(new java.awt.Dimension(166, 36));

        javax.swing.GroupLayout jpnQuanLyQuyenGopLayout = new javax.swing.GroupLayout(jpnQuanLyQuyenGop);
        jpnQuanLyQuyenGop.setLayout(jpnQuanLyQuyenGopLayout);
        jpnQuanLyQuyenGopLayout.setHorizontalGroup(
            jpnQuanLyQuyenGopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnQuanLyQuyenGopLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jibQuanLyQuyenGop, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpnQuanLyQuyenGopLayout.setVerticalGroup(
            jpnQuanLyQuyenGopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnQuanLyQuyenGopLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jibQuanLyQuyenGop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jpnQuanLyChuongTrinhLayout = new javax.swing.GroupLayout(jpnQuanLyChuongTrinh);
        jpnQuanLyChuongTrinh.setLayout(jpnQuanLyChuongTrinhLayout);
        jpnQuanLyChuongTrinhLayout.setHorizontalGroup(
            jpnQuanLyChuongTrinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQuanLyChuongTrinhLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jibQuanLyChuongTrinh, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jpnQuanLyQuyenGop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnQuanLyChuongTrinhLayout.setVerticalGroup(
            jpnQuanLyChuongTrinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnQuanLyChuongTrinhLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jibQuanLyChuongTrinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpnQuanLyQuyenGop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jpnQuanLyToChuc.setBackground(new java.awt.Color(76, 175, 80));

        jibQuanLyToChuc.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jibQuanLyToChuc.setForeground(new java.awt.Color(255, 255, 255));
        jibQuanLyToChuc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jibQuanLyToChuc.setText("Quản lý tổ chức");
        jibQuanLyToChuc.setPreferredSize(new java.awt.Dimension(71, 36));

        javax.swing.GroupLayout jpnQuanLyToChucLayout = new javax.swing.GroupLayout(jpnQuanLyToChuc);
        jpnQuanLyToChuc.setLayout(jpnQuanLyToChucLayout);
        jpnQuanLyToChucLayout.setHorizontalGroup(
            jpnQuanLyToChucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnQuanLyToChucLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jibQuanLyToChuc, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpnQuanLyToChucLayout.setVerticalGroup(
            jpnQuanLyToChucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnQuanLyToChucLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jibQuanLyToChuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ADMIN");

        txtName.setEditable(false);
        txtName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtName.setText("txtName");
        txtName.setFocusable(false);

        javax.swing.GroupLayout jpnLayout = new javax.swing.GroupLayout(jpn);
        jpn.setLayout(jpnLayout);
        jpnLayout.setHorizontalGroup(
            jpnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jpnLayout.setVerticalGroup(
            jpnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jlbLogout.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlbLogout.setForeground(new java.awt.Color(255, 255, 255));
        jlbLogout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbLogout.setText("Đăng xuất");

        jpnTaiKhoan.setBackground(new java.awt.Color(76, 175, 80));

        jibTaiKhoan.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jibTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        jibTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jibTaiKhoan.setText("Quản lý tài khoản");
        jibTaiKhoan.setPreferredSize(new java.awt.Dimension(71, 36));

        javax.swing.GroupLayout jpnTaiKhoanLayout = new javax.swing.GroupLayout(jpnTaiKhoan);
        jpnTaiKhoan.setLayout(jpnTaiKhoanLayout);
        jpnTaiKhoanLayout.setHorizontalGroup(
            jpnTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnTaiKhoanLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jibTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpnTaiKhoanLayout.setVerticalGroup(
            jpnTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnTaiKhoanLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jibTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jpnMenuLayout = new javax.swing.GroupLayout(jpnMenu);
        jpnMenu.setLayout(jpnMenuLayout);
        jpnMenuLayout.setHorizontalGroup(
            jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnQuanLyChuongTrinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnQuanLyToChuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnMenuLayout.createSequentialGroup()
                .addGroup(jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jpnMenuLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jlbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jpnTrangchu2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnMenuLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jlbLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
            .addComponent(jpnTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnMenuLayout.setVerticalGroup(
            jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMenuLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jlbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jpnTrangchu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpnQuanLyChuongTrinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpnQuanLyToChuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpnTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                .addComponent(jlbLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jpnView, javax.swing.GroupLayout.DEFAULT_SIZE, 1045, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
            .addComponent(jpnView, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    public static void main(String args[]) {
//    
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(AdminUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AdminUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AdminUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AdminUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
    ////        java.awt.EventQueue.invokeLater(new Runnable() {
////            public void run() {
////                new AdminUI().setVisible(true);
////            }
////        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jibQuanLyChuongTrinh;
    private javax.swing.JLabel jibQuanLyQuyenGop;
    private javax.swing.JLabel jibQuanLyToChuc;
    private javax.swing.JLabel jibTaiKhoan;
    private javax.swing.JLabel jibTrangchu2;
    private javax.swing.JLabel jlbImage;
    private javax.swing.JLabel jlbLogout;
    private javax.swing.JPanel jpn;
    private charity.viewMain.GradientPanel jpnMenu;
    private javax.swing.JPanel jpnQuanLyChuongTrinh;
    private javax.swing.JPanel jpnQuanLyQuyenGop;
    private javax.swing.JPanel jpnQuanLyToChuc;
    private javax.swing.JPanel jpnTaiKhoan;
    private javax.swing.JPanel jpnTrangchu2;
    private javax.swing.JPanel jpnView;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
