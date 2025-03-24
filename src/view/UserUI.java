package view;


import dao.DataConnection;
import model.CharityEvent;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author phaml
 */
public class UserUI extends javax.swing.JFrame {
    private List<CharityEvent> eventList = new ArrayList();
    DefaultTableModel eventModel;
    
    //định dạng số và ngày
    DecimalFormat numberFormat = new DecimalFormat("#,###");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
    
    public UserUI() {
        initComponents();
        showEventTable();
        showDonationListTable();
        showMyDonationTable();
//        jpnMyDonationList = new MyDonationTest();
        //#Dao----------
        eventModel = (DefaultTableModel) eventTable.getModel();

        
        //#Khoi tao mau jbutton
        jbtTrangChu.setBackground(Color.white);
        jbtDonationList.setBackground(Color.white);
        jbtMyDonationList.setBackground(Color.white);
        jButton4.setBackground(Color.white);
        jButton5.setBackground(Color.white);
        
        
        //Them Card Layout------------------------------------------------
        jpnRight.setLayout(new CardLayout());
        jpnRight.add(jpnTrangChu, "trangChu");
        jpnRight.add(jpnDonationList, "donationList");
        jpnRight.add(jpnMyDonationList, "myDonationList");
        
    }
    //load
    
    //hien thi thong tin event
    public void showEventTable(){
        Connection conn = null;
        ResultSet rs = null;
      
        try {
            conn = DataConnection.getConnection();
            String cauLenhSQL = "SELECT * FROM event;";
            rs = (ResultSet) DataConnection.thucThiLenhSelect(cauLenhSQL);

            //Tạo 1 DefaultTableModle lấy từ eventTable 
            DefaultTableModel eventModel = (DefaultTableModel) eventTable.getModel();

            //Tạo 1 mảng đồi tượng 9 phần tử để chứa giá trị các cột
            Object[] obj = new Object[9];
            while(rs.next()){
                obj[0]= rs.getInt("eventId");
                obj[1]= rs.getString("eventName");
                obj[2]= rs.getString("category");
                obj[3]= numberFormat.format(rs.getLong("currentAmount"));
                obj[4]= numberFormat.format(rs.getLong("targetAmount"));
                obj[5]= String.format("%.2f%%", ((float)rs.getLong("currentAmount")/rs.getLong("targetAmount"))*100);
                obj[6]= dateFormat.format(rs.getDate("dateBegin"));
                obj[7]= dateFormat.format(rs.getDate("dateEnd"));
                obj[8]= rs.getString("description");

                eventModel.addRow(obj);//Thêm vào eventTable
                eventTable.setModel(eventModel);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(UserUI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(new JFrame(), "Lỗi truy xuất");
        }finally{
            try {
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserUI.class.getName()).log(Level.SEVERE, "Lỗi đóng kết nối!", ex);
            }
        }
    }
    
    //#HIEN THI DONATION LIST
    public void showDonationListTable() {
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = DataConnection.getConnection();
            String cauLenhSQL = ""
                    + "SELECT donationId, eventName, userName, amount, donationDate\n"
                    + "FROM qltt.donation d\n"
                    + "join user u on d.userId= u.userId\n"
                    + "join event e on e.eventId = d.eventId\n"
                    + "order by donationId asc";

            rs = DataConnection.thucThiLenhSelect(cauLenhSQL);

            DefaultTableModel donationListModel = (DefaultTableModel) donationListTable.getModel();
            Object[] obj = new Object[9];
            while (rs.next()) {

                obj[0] = rs.getInt("donationId");
                obj[1] = rs.getString("eventName");
                obj[2] = rs.getString("userName");
                obj[3] = numberFormat.format(rs.getLong("amount"));
                obj[4] = dateFormat.format(rs.getDate("donationDate"));

                donationListTable.setModel(donationListModel);
                donationListModel.addRow(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserUI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(new JFrame(), "Lỗi truy xuất");
        }finally{
            try {
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserUI.class.getName()).log(Level.SEVERE, "Lỗi đóng kết nối!", ex);
            }
        }

    }
    public void showMyDonationTable() {
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = DataConnection.getConnection();
            String cauLenhSQL = "SELECT donationId, eventName, userName, amount, donationDate\n"
                    + "FROM qltt.donation d\n"
                    + "join user u on d.userId= u.userId\n"
                    + "join event e on e.eventId = d.eventId\n"
                    + "where d.userId =1\n"
                    + "order by donationId asc;";
            rs = DataConnection.thucThiLenhSelect(cauLenhSQL);

            DefaultTableModel myDonationModel = (DefaultTableModel) myDonationTable.getModel();
            Object[] obj = new Object[9];
            while (rs.next()) {

                obj[0] = rs.getInt("donationId");
                obj[1] = rs.getString("eventName");
                obj[2] = rs.getString("userName");
                obj[3] = numberFormat.format(rs.getLong("amount"));
                obj[4] = dateFormat.format(rs.getDate("donationDate"));

                myDonationTable.setModel(myDonationModel);
                myDonationModel.addRow(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserUI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(new JFrame(), "Lỗi truy xuất");
        }finally{
            try {
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserUI.class.getName()).log(Level.SEVERE, "Lỗi đóng kết nối!", ex);
            }
        }

    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnLeft = new javax.swing.JPanel();
        jbtTrangChu = new javax.swing.JButton();
        jbtDonationList = new javax.swing.JButton();
        jbtMyDonationList = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jpnRight = new javax.swing.JPanel();
        jpnMyDonationList = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        myDonationTable = new javax.swing.JTable();
        jpnDonationList = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        donationListTable = new javax.swing.JTable();
        jpnTrangChu = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jbtReset = new javax.swing.JButton();
        jbtDonate = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        eventTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpnLeft.setBackground(new java.awt.Color(255, 102, 102));
        jpnLeft.setForeground(new java.awt.Color(255, 255, 255));

        jbtTrangChu.setText("Trang chủ");
        jbtTrangChu.setBorder(null);
        jbtTrangChu.setFocusPainted(false);
        jbtTrangChu.setFocusable(false);
        jbtTrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtTrangChuActionPerformed(evt);
            }
        });

        jbtDonationList.setText("Danh sách quyên góp");
        jbtDonationList.setFocusPainted(false);
        jbtDonationList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDonationListActionPerformed(evt);
            }
        });

        jbtMyDonationList.setText("Lịch sử quyên góp");
        jbtMyDonationList.setFocusPainted(false);
        jbtMyDonationList.setFocusable(false);
        jbtMyDonationList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtMyDonationListActionPerformed(evt);
            }
        });

        jButton4.setText("jButton1");
        jButton4.setFocusPainted(false);
        jButton4.setFocusable(false);

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jpnLeftLayout = new javax.swing.GroupLayout(jpnLeft);
        jpnLeft.setLayout(jpnLeftLayout);
        jpnLeftLayout.setHorizontalGroup(
            jpnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtDonationList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtTrangChu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtMyDonationList, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpnLeftLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpnLeftLayout.setVerticalGroup(
            jpnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnLeftLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jpnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addComponent(jbtTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtDonationList, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtMyDonationList, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnRight.setLayout(new java.awt.CardLayout());

        jpnMyDonationList.setBackground(new java.awt.Color(204, 204, 255));

        myDonationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên sự kiện", "Người quyên góp", "Số tiền", "Ngày quyên góp"
            }
        ));
        jScrollPane4.setViewportView(myDonationTable);

        javax.swing.GroupLayout jpnMyDonationListLayout = new javax.swing.GroupLayout(jpnMyDonationList);
        jpnMyDonationList.setLayout(jpnMyDonationListLayout);
        jpnMyDonationListLayout.setHorizontalGroup(
            jpnMyDonationListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMyDonationListLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 863, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jpnMyDonationListLayout.setVerticalGroup(
            jpnMyDonationListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnMyDonationListLayout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jpnRight.add(jpnMyDonationList, "card4");

        jpnDonationList.setBackground(new java.awt.Color(204, 255, 255));

        jScrollPane5.setName(""); // NOI18N

        donationListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên sự kiện", "Người quyên góp", "Số tiền", "Ngày quyên góp"
            }
        ));
        donationListTable.setRowHeight(22);
        donationListTable.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                donationListTableAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane5.setViewportView(donationListTable);

        javax.swing.GroupLayout jpnDonationListLayout = new javax.swing.GroupLayout(jpnDonationList);
        jpnDonationList.setLayout(jpnDonationListLayout);
        jpnDonationListLayout.setHorizontalGroup(
            jpnDonationListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnDonationListLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 925, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jpnDonationListLayout.setVerticalGroup(
            jpnDonationListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnDonationListLayout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jpnRight.add(jpnDonationList, "card3");

        jpnTrangChu.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jbtReset.setText("Làm Mới");
        jbtReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtResetActionPerformed(evt);
            }
        });

        jbtDonate.setText("Quyên góp");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtDonate)
                .addGap(26, 26, 26)
                .addComponent(jbtReset)
                .addGap(34, 34, 34))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtReset)
                    .addComponent(jbtDonate))
                .addGap(37, 37, 37))
        );

        eventTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên sự kiện", "Loại", "Số tiền hiện tại", "Mục tiêu", "Tiến độ", "Ngày bắt đầu", "Ngày kết thúc", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        eventTable.setRowHeight(22);
        jScrollPane2.setViewportView(eventTable);
        if (eventTable.getColumnModel().getColumnCount() > 0) {
            eventTable.getColumnModel().getColumn(0).setPreferredWidth(7);
            eventTable.getColumnModel().getColumn(2).setPreferredWidth(40);
            eventTable.getColumnModel().getColumn(5).setPreferredWidth(30);
            eventTable.getColumnModel().getColumn(6).setPreferredWidth(60);
            eventTable.getColumnModel().getColumn(7).setPreferredWidth(60);
        }

        javax.swing.GroupLayout jpnTrangChuLayout = new javax.swing.GroupLayout(jpnTrangChu);
        jpnTrangChu.setLayout(jpnTrangChuLayout);
        jpnTrangChuLayout.setHorizontalGroup(
            jpnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnTrangChuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 945, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnTrangChuLayout.setVerticalGroup(
            jpnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTrangChuLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnRight.add(jpnTrangChu, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtTrangChuActionPerformed
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) jpnRight.getLayout();
        cardLayout.show(jpnRight, "trangChu");
        jbtTrangChu.setBackground(Color.LIGHT_GRAY);
        jbtDonationList.setBackground(Color.WHITE);
        jbtMyDonationList.setBackground(Color.WHITE);
    }//GEN-LAST:event_jbtTrangChuActionPerformed

    private void jbtDonationListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDonationListActionPerformed
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) jpnRight.getLayout();
        cardLayout.show(jpnRight, "donationList");
        jbtTrangChu.setBackground(Color.WHITE);
        jbtDonationList.setBackground(Color.LIGHT_GRAY);
        jbtMyDonationList.setBackground(Color.WHITE);
    }//GEN-LAST:event_jbtDonationListActionPerformed

    private void jbtMyDonationListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtMyDonationListActionPerformed
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) jpnRight.getLayout();
        cardLayout.show(jpnRight, "myDonationList");
        jbtTrangChu.setBackground(Color.WHITE);
        jbtDonationList.setBackground(Color.WHITE);
        jbtMyDonationList.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_jbtMyDonationListActionPerformed

    private void jbtResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtResetActionPerformed
        // TODO add your handling code here:
        eventModel.setRowCount(0);
        showEventTable();
    }//GEN-LAST:event_jbtResetActionPerformed

    private void donationListTableAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_donationListTableAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_donationListTableAncestorAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable donationListTable;
    private javax.swing.JTable eventTable;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton jbtDonate;
    private javax.swing.JButton jbtDonationList;
    private javax.swing.JButton jbtMyDonationList;
    private javax.swing.JButton jbtReset;
    private javax.swing.JButton jbtTrangChu;
    private javax.swing.JPanel jpnDonationList;
    private javax.swing.JPanel jpnLeft;
    private javax.swing.JPanel jpnMyDonationList;
    private javax.swing.JPanel jpnRight;
    private javax.swing.JPanel jpnTrangChu;
    private javax.swing.JTable myDonationTable;
    // End of variables declaration//GEN-END:variables
}
