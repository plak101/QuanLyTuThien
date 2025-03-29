package frontend_User;

import dao.*;
import entity.CharityEvent;
import entity.User;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author phaml
 */
public class UserUI extends javax.swing.JFrame {

    private List<CharityEvent> eventList = new ArrayList();
    DefaultTableModel eventModel;
    CharityEventDAO eventDAO = new CharityEventDAO();
    UserDAO userDAO = new UserDAO();
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
    }

    //1. load data
    public void loadData() {
        //hien thi ten user
        User user = userDAO.getUserById(userId);
        txtUserName.setText(user.getName());

        //hien thi du lieu len table
        showEventTable();
        showDonationListTable();
        showMyDonationTable();
    }

    //2. cai dat cardlayout
    public void setupCardLayout() {
        jpnRight.setLayout(new CardLayout());
        jpnRight.add(jpnMain, "main");
        jpnRight.add(jpnDonation, "donationList");
        jpnRight.add(jpnMyDonation, "myDonationList");
        jpnRight.add(jpnAccount, "account");
        ProfilePanel profilePanel = new ProfilePanel();
        jpnRight.add(profilePanel, "profilePanel");
    }

    //hien thi thong tin event
    public void showEventTable() {
        List<CharityEvent> events = new ArrayList<>();
        events = eventDAO.getEventList();
        DefaultTableModel eventModel = (DefaultTableModel) eventTable.getModel();
        Object[] obj = new Object[9];
        for (CharityEvent event : events) {
            obj[0] = event.getId();
            obj[1] = event.getName();
            obj[2] = event.getCategory();
            obj[3] = numberFormat.format(event.getCurrentAmount());
            obj[4] = numberFormat.format(event.getTargetAmount());
            obj[5] = String.format("%.2f%%", ((float) event.getCurrentAmount() / event.getTargetAmount()) * 100);
            obj[6] = dateFormat.format(event.getDateBegin());
            obj[7] = dateFormat.format(event.getDateEnd());
            obj[8] = event.getDescription();
            eventModel.addRow(obj);
        }
    }

    //#HIEN THI DONATION LIST
    public void showDonationListTable() {
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDB.getConnection();
            String cauLenhSQL = ""
                    + "SELECT donationId, eventName, userName, amount, donationDate\n"
                    + "FROM qltt.donation d\n"
                    + "join user u on d.userId= u.userId\n"
                    + "join event e on e.eventId = d.eventId\n"
                    + "order by donationId asc";

            rs = ConnectionDB.executeSelect(cauLenhSQL);

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
        } finally {
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
            conn = ConnectionDB.getConnection();
            String cauLenhSQL = "SELECT donationId, eventName, userName, amount, donationDate\n"
                    + "FROM qltt.donation d\n"
                    + "join user u on d.userId= u.userId\n"
                    + "join event e on e.eventId = d.eventId\n"
                    + "where d.userId =1\n"
                    + "order by donationId asc;";
            rs = ConnectionDB.executeSelect(cauLenhSQL);

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
        } finally {
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

        jpnLeft = new frontend_User.GradientPanel();
        jPanel2 = new javax.swing.JPanel();
        txtUserName = new javax.swing.JTextField();
        jpnMainOption = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jpnDonationOption = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jpnMyDonationOption = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jpnAccountOption = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jpnRight = new javax.swing.JPanel();
        jpnMain = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jbtReset = new javax.swing.JButton();
        jbtDonate = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        eventTable = new javax.swing.JTable();
        jpnMyDonation = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        myDonationTable = new javax.swing.JTable();
        jpnDonation = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        donationListTable = new javax.swing.JTable();
        jpnAccount = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setOpaque(false);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(txtUserName)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jpnMainOption.setBackground(new java.awt.Color(0, 102, 102));
        jpnMainOption.setOpaque(false);
        jpnMainOption.setVerifyInputWhenFocusTarget(false);
        jpnMainOption.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnMainOptionMouseClicked(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 204, 204));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Trang chủ");

        javax.swing.GroupLayout jpnMainOptionLayout = new javax.swing.GroupLayout(jpnMainOption);
        jpnMainOption.setLayout(jpnMainOptionLayout);
        jpnMainOptionLayout.setHorizontalGroup(
            jpnMainOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMainOptionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnMainOptionLayout.setVerticalGroup(
            jpnMainOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnMainOptionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnDonationOption.setOpaque(false);
        jpnDonationOption.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnDonationOptionMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Danh sách quyên góp");

        javax.swing.GroupLayout jpnDonationOptionLayout = new javax.swing.GroupLayout(jpnDonationOption);
        jpnDonationOption.setLayout(jpnDonationOptionLayout);
        jpnDonationOptionLayout.setHorizontalGroup(
            jpnDonationOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnDonationOptionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnDonationOptionLayout.setVerticalGroup(
            jpnDonationOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnDonationOptionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnMyDonationOption.setOpaque(false);
        jpnMyDonationOption.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnMyDonationOptionMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Lịch sử quyên góp");

        javax.swing.GroupLayout jpnMyDonationOptionLayout = new javax.swing.GroupLayout(jpnMyDonationOption);
        jpnMyDonationOption.setLayout(jpnMyDonationOptionLayout);
        jpnMyDonationOptionLayout.setHorizontalGroup(
            jpnMyDonationOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMyDonationOptionLayout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnMyDonationOptionLayout.setVerticalGroup(
            jpnMyDonationOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnMyDonationOptionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnAccountOption.setOpaque(false);
        jpnAccountOption.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnAccountOptionMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Thông tin tài khoản");

        javax.swing.GroupLayout jpnAccountOptionLayout = new javax.swing.GroupLayout(jpnAccountOption);
        jpnAccountOption.setLayout(jpnAccountOptionLayout);
        jpnAccountOptionLayout.setHorizontalGroup(
            jpnAccountOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnAccountOptionLayout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnAccountOptionLayout.setVerticalGroup(
            jpnAccountOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnAccountOptionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jpnLeftLayout = new javax.swing.GroupLayout(jpnLeft);
        jpnLeft.setLayout(jpnLeftLayout);
        jpnLeftLayout.setHorizontalGroup(
            jpnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnMainOption, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnMyDonationOption, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnDonationOption, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnAccountOption, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnLeftLayout.setVerticalGroup(
            jpnLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnLeftLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(jpnMainOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jpnDonationOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jpnMyDonationOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jpnAccountOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        jpnRight.setPreferredSize(new java.awt.Dimension(1300, 840));
        jpnRight.setLayout(new java.awt.CardLayout());

        jpnMain.setBackground(new java.awt.Color(255, 255, 255));
        jpnMain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnMainMouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jbtReset.setText("Làm Mới");
        jbtReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtResetActionPerformed(evt);
            }
        });

        jbtDonate.setText("Quyên góp");
        jbtDonate.setEnabled(false);
        jbtDonate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDonateActionPerformed(evt);
            }
        });

        jScrollPane2.setBackground(java.awt.Color.lightGray);

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
        eventTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eventTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(eventTable);
        if (eventTable.getColumnModel().getColumnCount() > 0) {
            eventTable.getColumnModel().getColumn(0).setPreferredWidth(7);
            eventTable.getColumnModel().getColumn(2).setPreferredWidth(40);
            eventTable.getColumnModel().getColumn(5).setPreferredWidth(30);
            eventTable.getColumnModel().getColumn(6).setPreferredWidth(60);
            eventTable.getColumnModel().getColumn(7).setPreferredWidth(60);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(333, 333, 333)
                .addComponent(jbtDonate)
                .addGap(18, 18, 18)
                .addComponent(jbtReset)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 986, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 343, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtReset)
                    .addComponent(jbtDonate))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jpnMainLayout = new javax.swing.GroupLayout(jpnMain);
        jpnMain.setLayout(jpnMainLayout);
        jpnMainLayout.setHorizontalGroup(
            jpnMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMainLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jpnMainLayout.setVerticalGroup(
            jpnMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMainLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnRight.add(jpnMain, "card2");

        jpnMyDonation.setBackground(new java.awt.Color(204, 204, 255));

        myDonationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên sự kiện", "Người quyên góp", "Số tiền", "Ngày quyên góp"
            }
        ));
        jScrollPane4.setViewportView(myDonationTable);

        javax.swing.GroupLayout jpnMyDonationLayout = new javax.swing.GroupLayout(jpnMyDonation);
        jpnMyDonation.setLayout(jpnMyDonationLayout);
        jpnMyDonationLayout.setHorizontalGroup(
            jpnMyDonationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMyDonationLayout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 863, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jpnMyDonationLayout.setVerticalGroup(
            jpnMyDonationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnMyDonationLayout.createSequentialGroup()
                .addGap(0, 73, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jpnRight.add(jpnMyDonation, "card4");

        jpnDonation.setBackground(new java.awt.Color(204, 255, 255));

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

        javax.swing.GroupLayout jpnDonationLayout = new javax.swing.GroupLayout(jpnDonation);
        jpnDonation.setLayout(jpnDonationLayout);
        jpnDonationLayout.setHorizontalGroup(
            jpnDonationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnDonationLayout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 925, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jpnDonationLayout.setVerticalGroup(
            jpnDonationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnDonationLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(433, 433, 433))
        );

        jpnRight.add(jpnDonation, "card3");

        javax.swing.GroupLayout jpnAccountLayout = new javax.swing.GroupLayout(jpnAccount);
        jpnAccount.setLayout(jpnAccountLayout);
        jpnAccountLayout.setHorizontalGroup(
            jpnAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1027, Short.MAX_VALUE)
        );
        jpnAccountLayout.setVerticalGroup(
            jpnAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        jpnRight.add(jpnAccount, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jpnLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnRight, javax.swing.GroupLayout.PREFERRED_SIZE, 1027, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnRight, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jpnLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtResetActionPerformed
        // TODO add your handling code here:
        eventModel.setRowCount(0);
        showEventTable();
        jbtDonate.setEnabled(false);
        pos = -1;
        selectedEventId = -1;
    }//GEN-LAST:event_jbtResetActionPerformed

    private void donationListTableAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_donationListTableAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_donationListTableAncestorAdded

//lay sự kiện click chuột vào bảng event-----------------
    private void eventTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eventTableMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            pos = eventTable.getSelectedRow();
            selectedEventId = -1;
            if (pos != -1) {
                selectedEventId = (int) eventTable.getValueAt(pos, 0);
                jbtDonate.setEnabled(true);//bat nut quyen gop
            }
        }

    }//GEN-LAST:event_eventTableMouseClicked

    private void jbtDonateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDonateActionPerformed
        // TODO add your handling code here:
        if (selectedEventId != -1) {
            CharityEvent event = eventDAO.getEventById(selectedEventId);

            if (event == null) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy sự kiện!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

            DonateJDialog dialog = new DonateJDialog(this, true, event, userId);
            dialog.setVisible(true);
        } else {

        }
    }//GEN-LAST:event_jbtDonateActionPerformed

    private void jpnMainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnMainMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jpnMainMouseClicked
    //click vao panel trong menu
    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
//        CardLayout cardLayout = (CardLayout) jpnRight.getLayout();
//        cardLayout.show(jpnRight, "donationList");
//        jbtTrangChu.setBackground(Color.WHITE);
//        jbtDonationList.setBackground(Color.LIGHT_GRAY);
//        jbtMyDonationList.setBackground(Color.WHITE);
    }//GEN-LAST:event_jPanel2MouseClicked

    //click chuot vào option Trang chủ
    private void jpnMainOptionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnMainOptionMouseClicked
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CardLayout cardLayout = (CardLayout) jpnRight.getLayout();
                cardLayout.show(jpnRight, "main");

                jpnMainOption.setOpaque(true);
                jpnDonationOption.setOpaque(false);
                jpnMyDonationOption.setOpaque(false);
                jpnAccountOption.setOpaque(false);

                jpnMainOption.setBackground(Color.decode("#006666"));
                jpnMainOption.repaint();
                jpnDonationOption.repaint();
                jpnMyDonationOption.repaint();
                jpnAccountOption.repaint();
            }
        });
    }//GEN-LAST:event_jpnMainOptionMouseClicked
    //click chuot vào option danh sách donate
    private void jpnDonationOptionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnDonationOptionMouseClicked
        CardLayout cardLayout = (CardLayout) jpnRight.getLayout();
        cardLayout.show(jpnRight, "donationList");

        jpnMainOption.setOpaque(false);
        jpnDonationOption.setOpaque(true);
        jpnMyDonationOption.setOpaque(false);
        jpnAccountOption.setOpaque(false);

        jpnDonationOption.setBackground(Color.decode("#006666"));
        //ve lai
        jpnMainOption.repaint();
        jpnDonationOption.repaint();
        jpnMyDonationOption.repaint();

    }//GEN-LAST:event_jpnDonationOptionMouseClicked

    //click chuot vào option lịch sử quyên góp
    private void jpnMyDonationOptionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnMyDonationOptionMouseClicked
        CardLayout cardLayout = (CardLayout) jpnRight.getLayout();
        cardLayout.show(jpnRight, "myDonationList");

        jpnMainOption.setOpaque(false);
        jpnDonationOption.setOpaque(false);
        jpnMyDonationOption.setOpaque(true);
        jpnAccountOption.setOpaque(false);

        jpnMyDonationOption.setBackground(Color.decode("#006666"));
        //ve lai
        jpnMainOption.repaint();
        jpnDonationOption.repaint();
        jpnMyDonationOption.repaint();
        jpnAccountOption.repaint();

    }//GEN-LAST:event_jpnMyDonationOptionMouseClicked
    //
    private void txtUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserNameActionPerformed

    private void jpnAccountOptionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnAccountOptionMouseClicked

        CardLayout cardLayout = (CardLayout) jpnRight.getLayout();
        cardLayout.show(jpnRight, "profilePanel");


        jpnMainOption.setOpaque(false);
        jpnDonationOption.setOpaque(false);
        jpnMyDonationOption.setOpaque(false);
        jpnAccountOption.setOpaque(true);

        jpnAccountOption.setBackground(Color.decode("#006666"));
        //ve lai
        jpnMainOption.repaint();
        jpnDonationOption.repaint();
        jpnMyDonationOption.repaint();
        jpnAccountOption.repaint();
    }//GEN-LAST:event_jpnAccountOptionMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable donationListTable;
    private javax.swing.JTable eventTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton jbtDonate;
    private javax.swing.JButton jbtReset;
    private javax.swing.JPanel jpnAccount;
    private javax.swing.JPanel jpnAccountOption;
    private javax.swing.JPanel jpnDonation;
    private javax.swing.JPanel jpnDonationOption;
    private frontend_User.GradientPanel jpnLeft;
    private javax.swing.JPanel jpnMain;
    private javax.swing.JPanel jpnMainOption;
    private javax.swing.JPanel jpnMyDonation;
    private javax.swing.JPanel jpnMyDonationOption;
    private javax.swing.JPanel jpnRight;
    private javax.swing.JTable myDonationTable;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
