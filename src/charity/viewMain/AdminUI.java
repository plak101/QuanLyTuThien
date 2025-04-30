package charity.viewMain;

import charity.controller.ChuyenManHinh;
import charity.bean.DanhMuc;
import charity.component.ImageIconCustom;
import charity.controller.AdminUIController;
import java.util.ArrayList;
import java.util.List;

public class AdminUI extends javax.swing.JFrame {

    public AdminUI() {
        initComponents();

        setTitle("QUẢN LÍ TỪ THIỆN");

        ChuyenManHinh controler = new ChuyenManHinh(jpnView);
        controler.setView(jpnMenu, jibTrangchu2);

        List<DanhMuc> listItem = new ArrayList<>();
        listItem.add(new DanhMuc("TrangChu", jpnTrangchu2, jibTrangchu2));
        listItem.add(new DanhMuc("QuanLyNhaTaiTro", jpnQuanNhaTaiTro, jibQuanNhaTaiTro));
        listItem.add(new DanhMuc("QuanLyChuongTrinh", jpnQuanLyChuongTrinh, jibQuanLyChuongTrinh));
        listItem.add(new DanhMuc("QuanLyQuyenGop", jpnQuanLyQuyenGop, jibQuanLyQuyenGop));
        listItem.add(new DanhMuc("QuanLyToChuc", jpnQuanLyToChuc, jibQuanLyToChuc));
        listItem.add(new DanhMuc("BaoCao", jpnBaoCao, jibBaoCao));

        controler.setEvent(listItem);
        
        AdminUIController adminUIController = new AdminUIController(jlbLogout);
        init();
    }

    public void init() {
        jlbImage.setIcon(ImageIconCustom.getSmoothIcon("/charity/icon/AdminTrans.png", 80, 80));
        jibTrangchu2.setIcon(ImageIconCustom.getSmoothIcon("/charity/icon/home.png", 24, 24));
//        jLabel3.setIcon(ImageIconCustom.getSmoothIcon("/charity/icon/list2.png", 24, 24));
//        jLabel5.setIcon(ImageIconCustom.getSmoothIcon("/charity/icon/non-profit-organization (2).png", 24, 24));
//        jLabel2.setIcon(ImageIconCustom.getSmoothIcon("/charity/icon/user2.png", 24, 24));
//        jLabel4.setIcon(ImageIconCustom.getSmoothIcon("/charity/icon/recent.png", 24, 24));

        jlbLogout.setIcon(ImageIconCustom.getSmoothIcon("/charity/icon/logout2.png", 20, 20));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnView = new javax.swing.JPanel();
        jpnMenu = new charity.viewMain.GradientPanel();
        jpnTrangchu2 = new javax.swing.JPanel();
        jibTrangchu2 = new javax.swing.JLabel();
        jpnQuanNhaTaiTro = new javax.swing.JPanel();
        jibQuanNhaTaiTro = new javax.swing.JLabel();
        jpnQuanLyChuongTrinh = new javax.swing.JPanel();
        jibQuanLyChuongTrinh = new javax.swing.JLabel();
        jpnQuanLyQuyenGop = new javax.swing.JPanel();
        jibQuanLyQuyenGop = new javax.swing.JLabel();
        jpnQuanLyToChuc = new javax.swing.JPanel();
        jibQuanLyToChuc = new javax.swing.JLabel();
        jlbImage = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtName = new javax.swing.JPanel();
        jlbLogout = new javax.swing.JLabel();
        jpnBaoCao = new javax.swing.JPanel();
        jibBaoCao = new javax.swing.JLabel();

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

        jpnQuanNhaTaiTro.setBackground(new java.awt.Color(76, 175, 80));

        jibQuanNhaTaiTro.setBackground(new java.awt.Color(255, 255, 255));
        jibQuanNhaTaiTro.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jibQuanNhaTaiTro.setForeground(new java.awt.Color(255, 255, 255));
        jibQuanNhaTaiTro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jibQuanNhaTaiTro.setText("Quản lý nhà tài trợ");
        jibQuanNhaTaiTro.setPreferredSize(new java.awt.Dimension(163, 36));

        javax.swing.GroupLayout jpnQuanNhaTaiTroLayout = new javax.swing.GroupLayout(jpnQuanNhaTaiTro);
        jpnQuanNhaTaiTro.setLayout(jpnQuanNhaTaiTroLayout);
        jpnQuanNhaTaiTroLayout.setHorizontalGroup(
            jpnQuanNhaTaiTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnQuanNhaTaiTroLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jibQuanNhaTaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpnQuanNhaTaiTroLayout.setVerticalGroup(
            jpnQuanNhaTaiTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnQuanNhaTaiTroLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jibQuanNhaTaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jpnQuanLyChuongTrinh.setBackground(new java.awt.Color(76, 175, 80));

        jibQuanLyChuongTrinh.setBackground(new java.awt.Color(255, 255, 255));
        jibQuanLyChuongTrinh.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jibQuanLyChuongTrinh.setForeground(new java.awt.Color(255, 255, 255));
        jibQuanLyChuongTrinh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jibQuanLyChuongTrinh.setText("Quản lý chương trình");
        jibQuanLyChuongTrinh.setPreferredSize(new java.awt.Dimension(189, 36));

        javax.swing.GroupLayout jpnQuanLyChuongTrinhLayout = new javax.swing.GroupLayout(jpnQuanLyChuongTrinh);
        jpnQuanLyChuongTrinh.setLayout(jpnQuanLyChuongTrinhLayout);
        jpnQuanLyChuongTrinhLayout.setHorizontalGroup(
            jpnQuanLyChuongTrinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnQuanLyChuongTrinhLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jibQuanLyChuongTrinh, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpnQuanLyChuongTrinhLayout.setVerticalGroup(
            jpnQuanLyChuongTrinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnQuanLyChuongTrinhLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jibQuanLyChuongTrinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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
                .addComponent(jibQuanLyToChuc, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpnQuanLyToChucLayout.setVerticalGroup(
            jpnQuanLyToChucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnQuanLyToChucLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jibQuanLyToChuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ADMIN");

        javax.swing.GroupLayout txtNameLayout = new javax.swing.GroupLayout(txtName);
        txtName.setLayout(txtNameLayout);
        txtNameLayout.setHorizontalGroup(
            txtNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        txtNameLayout.setVerticalGroup(
            txtNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        jlbLogout.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlbLogout.setForeground(new java.awt.Color(255, 255, 255));
        jlbLogout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbLogout.setText("Đăng xuất");

        jpnBaoCao.setBackground(new java.awt.Color(76, 175, 80));

        jibBaoCao.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jibBaoCao.setForeground(new java.awt.Color(255, 255, 255));
        jibBaoCao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jibBaoCao.setText("Báo cáo");
        jibBaoCao.setPreferredSize(new java.awt.Dimension(71, 36));

        javax.swing.GroupLayout jpnBaoCaoLayout = new javax.swing.GroupLayout(jpnBaoCao);
        jpnBaoCao.setLayout(jpnBaoCaoLayout);
        jpnBaoCaoLayout.setHorizontalGroup(
            jpnBaoCaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnBaoCaoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jibBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpnBaoCaoLayout.setVerticalGroup(
            jpnBaoCaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnBaoCaoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jibBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jpnMenuLayout = new javax.swing.GroupLayout(jpnMenu);
        jpnMenu.setLayout(jpnMenuLayout);
        jpnMenuLayout.setHorizontalGroup(
            jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnQuanNhaTaiTro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnQuanLyChuongTrinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnQuanLyQuyenGop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnQuanLyToChuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnMenuLayout.createSequentialGroup()
                .addGroup(jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
            .addComponent(jpnBaoCao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnMenuLayout.setVerticalGroup(
            jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMenuLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jlbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jpnTrangchu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpnQuanNhaTaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpnQuanLyChuongTrinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpnQuanLyQuyenGop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpnQuanLyToChuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpnBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(jlbLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
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
    private javax.swing.JLabel jibBaoCao;
    private javax.swing.JLabel jibQuanLyChuongTrinh;
    private javax.swing.JLabel jibQuanLyQuyenGop;
    private javax.swing.JLabel jibQuanLyToChuc;
    private javax.swing.JLabel jibQuanNhaTaiTro;
    private javax.swing.JLabel jibTrangchu2;
    private javax.swing.JLabel jlbImage;
    private javax.swing.JLabel jlbLogout;
    private javax.swing.JPanel jpnBaoCao;
    private charity.viewMain.GradientPanel jpnMenu;
    private javax.swing.JPanel jpnQuanLyChuongTrinh;
    private javax.swing.JPanel jpnQuanLyQuyenGop;
    private javax.swing.JPanel jpnQuanLyToChuc;
    private javax.swing.JPanel jpnQuanNhaTaiTro;
    private javax.swing.JPanel jpnTrangchu2;
    private javax.swing.JPanel jpnView;
    private javax.swing.JPanel txtName;
    // End of variables declaration//GEN-END:variables
}
