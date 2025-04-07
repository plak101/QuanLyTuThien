
package charity.viewMain;

import charity.controller.ChuyenManHinh;
import charity.bean.DanhMuc;
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
        listItem.add(new DanhMuc("QuanLyNhaTaiTro", jpnQuanNhaTaiTro, jibQuanNhaTaiTro));
        listItem.add(new DanhMuc("QuanLyChuongTrinh", jpnQuanLyChuongTrinh, jibQuanLyChuongTrinh));
        listItem.add(new DanhMuc("QuanLyQuyenGop", jpnQuanLyQuyenGop, jibQuanLyQuyenGop));
        listItem.add(new DanhMuc("BaoCao", jpnBaoCao, jibBaoCao));
        
        
        controler.setEvent(listItem);
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
        jpnBaoCao = new javax.swing.JPanel();
        jibBaoCao = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpnView.setBackground(new java.awt.Color(255, 255, 255));
        jpnView.setAutoscrolls(true);
        jpnView.setPreferredSize(new java.awt.Dimension(800, 500));

        javax.swing.GroupLayout jpnViewLayout = new javax.swing.GroupLayout(jpnView);
        jpnView.setLayout(jpnViewLayout);
        jpnViewLayout.setHorizontalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 812, Short.MAX_VALUE)
        );
        jpnViewLayout.setVerticalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jpnTrangchu2.setBackground(new java.awt.Color(76, 175, 80));

        jibTrangchu2.setBackground(new java.awt.Color(255, 255, 255));
        jibTrangchu2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jibTrangchu2.setForeground(new java.awt.Color(255, 255, 255));
        jibTrangchu2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jibTrangchu2.setText("Trang chủ");

        javax.swing.GroupLayout jpnTrangchu2Layout = new javax.swing.GroupLayout(jpnTrangchu2);
        jpnTrangchu2.setLayout(jpnTrangchu2Layout);
        jpnTrangchu2Layout.setHorizontalGroup(
            jpnTrangchu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jibTrangchu2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnTrangchu2Layout.setVerticalGroup(
            jpnTrangchu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jibTrangchu2, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        jpnQuanNhaTaiTro.setBackground(new java.awt.Color(76, 175, 80));

        jibQuanNhaTaiTro.setBackground(new java.awt.Color(255, 255, 255));
        jibQuanNhaTaiTro.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jibQuanNhaTaiTro.setForeground(new java.awt.Color(255, 255, 255));
        jibQuanNhaTaiTro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jibQuanNhaTaiTro.setText("Quản lý nhà tài trợ");

        javax.swing.GroupLayout jpnQuanNhaTaiTroLayout = new javax.swing.GroupLayout(jpnQuanNhaTaiTro);
        jpnQuanNhaTaiTro.setLayout(jpnQuanNhaTaiTroLayout);
        jpnQuanNhaTaiTroLayout.setHorizontalGroup(
            jpnQuanNhaTaiTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jibQuanNhaTaiTro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnQuanNhaTaiTroLayout.setVerticalGroup(
            jpnQuanNhaTaiTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jibQuanNhaTaiTro, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        jpnQuanLyChuongTrinh.setBackground(new java.awt.Color(76, 175, 80));

        jibQuanLyChuongTrinh.setBackground(new java.awt.Color(255, 255, 255));
        jibQuanLyChuongTrinh.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jibQuanLyChuongTrinh.setForeground(new java.awt.Color(255, 255, 255));
        jibQuanLyChuongTrinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jibQuanLyChuongTrinh.setText("Quản lý chương trình");

        javax.swing.GroupLayout jpnQuanLyChuongTrinhLayout = new javax.swing.GroupLayout(jpnQuanLyChuongTrinh);
        jpnQuanLyChuongTrinh.setLayout(jpnQuanLyChuongTrinhLayout);
        jpnQuanLyChuongTrinhLayout.setHorizontalGroup(
            jpnQuanLyChuongTrinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jibQuanLyChuongTrinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnQuanLyChuongTrinhLayout.setVerticalGroup(
            jpnQuanLyChuongTrinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jibQuanLyChuongTrinh, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        jpnQuanLyQuyenGop.setBackground(new java.awt.Color(76, 175, 80));

        jibQuanLyQuyenGop.setBackground(new java.awt.Color(255, 255, 255));
        jibQuanLyQuyenGop.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jibQuanLyQuyenGop.setForeground(new java.awt.Color(255, 255, 255));
        jibQuanLyQuyenGop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jibQuanLyQuyenGop.setText("Quản lý quyên góp");

        javax.swing.GroupLayout jpnQuanLyQuyenGopLayout = new javax.swing.GroupLayout(jpnQuanLyQuyenGop);
        jpnQuanLyQuyenGop.setLayout(jpnQuanLyQuyenGopLayout);
        jpnQuanLyQuyenGopLayout.setHorizontalGroup(
            jpnQuanLyQuyenGopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jibQuanLyQuyenGop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnQuanLyQuyenGopLayout.setVerticalGroup(
            jpnQuanLyQuyenGopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jibQuanLyQuyenGop, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        jpnBaoCao.setBackground(new java.awt.Color(76, 175, 80));

        jibBaoCao.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jibBaoCao.setForeground(new java.awt.Color(255, 255, 255));
        jibBaoCao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jibBaoCao.setText("Báo cáo");

        javax.swing.GroupLayout jpnBaoCaoLayout = new javax.swing.GroupLayout(jpnBaoCao);
        jpnBaoCao.setLayout(jpnBaoCaoLayout);
        jpnBaoCaoLayout.setHorizontalGroup(
            jpnBaoCaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jibBaoCao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnBaoCaoLayout.setVerticalGroup(
            jpnBaoCaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jibBaoCao, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/charity/icon/AdminTrans.png"))); // NOI18N
        jLabel1.setText("ADMIN");
        jLabel1.setMaximumSize(new java.awt.Dimension(576, 512));

        javax.swing.GroupLayout jpnMenuLayout = new javax.swing.GroupLayout(jpnMenu);
        jpnMenu.setLayout(jpnMenuLayout);
        jpnMenuLayout.setHorizontalGroup(
            jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMenuLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jpnBaoCao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnQuanLyQuyenGop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnQuanLyChuongTrinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnTrangchu2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnQuanNhaTaiTro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                .addGap(0, 60, Short.MAX_VALUE))
        );
        jpnMenuLayout.setVerticalGroup(
            jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMenuLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jpnTrangchu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jpnQuanNhaTaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jpnQuanLyChuongTrinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jpnQuanLyQuyenGop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jpnBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnView, javax.swing.GroupLayout.PREFERRED_SIZE, 812, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnView, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jibBaoCao;
    private javax.swing.JLabel jibQuanLyChuongTrinh;
    private javax.swing.JLabel jibQuanLyQuyenGop;
    private javax.swing.JLabel jibQuanNhaTaiTro;
    private javax.swing.JLabel jibTrangchu;
    private javax.swing.JLabel jibTrangchu1;
    private javax.swing.JLabel jibTrangchu2;
    private javax.swing.JPanel jpnBaoCao;
    private charity.viewMain.GradientPanel jpnMenu;
    private javax.swing.JPanel jpnQuanLyChuongTrinh;
    private javax.swing.JPanel jpnQuanLyQuyenGop;
    private javax.swing.JPanel jpnQuanNhaTaiTro;
    private javax.swing.JPanel jpnTrangchu;
    private javax.swing.JPanel jpnTrangchu1;
    private javax.swing.JPanel jpnTrangchu2;
    private javax.swing.JPanel jpnView;
    // End of variables declaration//GEN-END:variables
}
