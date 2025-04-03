package charity.viewUser;

import charity.controller.ClassTableModel;
import charity.model.CharityEvent;
import charity.repository.CharityEventRepository;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class MainPanel extends javax.swing.JPanel {
    private JFrame parent;
    private int userId;

    private CharityEventRepository eventDAO = new CharityEventRepository();

    private ClassTableModel classTableModel = new ClassTableModel();
    private TableRowSorter<TableModel> rowSorter = null;
    private DefaultTableModel eventModel = null;
    private JTable eventTable = null;
    private int selectedEventId = -1;

//    private 
    public MainPanel(JFrame parent, int userId) {
        eventModel = classTableModel.getActiveEventTable();
        
        this.userId = userId;
        this.parent = parent;

        initComponents();
        header.changeColor("#74ebd5", "#ACB6E5");
        

        showEventTable();
        loadButton();
        tableMouseClick();

    }

    public void loadButton() {
        //background color
//        jbtDonate.setBackground( Color.decode("#ACB6E5"));
//        jbtDonate.setForeground(Color.BLACK);
//        jbtReset.setBackground( Color.decode("#ACB6E5"));
//        jbtActive.setBackground(Color.white);
//        jbtExpired.setBackground(Color.white);

        //set enabled
        jbtDonate.setEnabled(false);
        jbtActive.setEnabled(false);
    }

    public void designTable(JTable table) {

        //table header
        table.getTableHeader().setBackground(Color.decode("#FFFFFF"));
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 40));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        //table body
        table.setRowHeight(40);
        table.setShowHorizontalLines(true);
        table.setGridColor(new Color(230, 230, 230));

        //size column
        table.getColumnModel().getColumn(0).setMaxWidth(400);
        table.getColumnModel().getColumn(0).setPreferredWidth(40);

        table.getColumnModel().getColumn(1).setMaxWidth(500);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);

        table.getColumnModel().getColumn(2).setMaxWidth(500);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);

        table.getColumnModel().getColumn(5).setMaxWidth(500);
        table.getColumnModel().getColumn(5).setPreferredWidth(90);

        //show
        table.validate();
        table.repaint();
    }

    public void showEventTable() {
        //setup event table


        System.out.println(eventModel.getRowCount());
        eventTable = new JTable(eventModel);

        //setup rowsorter
        rowSorter = new TableRowSorter<>(eventTable.getModel());
        eventTable.setRowSorter(rowSorter);
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {

            //"(?i)" khong phan biet chu hoa chu thuong
            //khi nhap vao txtSearch
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    if (jrbtId.isSelected()) {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0));
                    } else if (jrbtName.isSelected()) {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 1));
                    } else {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 2));
                    }

                }
            }

            //khi xoa noi dung cua txtSearch
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtSearch.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    if (jrbtId.isSelected()) {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0));
                    } else if (jrbtName.isSelected()) {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 1));
                    } else {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 2));
                    }
                }
            }

            //khi co thay doi thuoc tinh van ban
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        designTable(eventTable);

        //hien thi ra jpnTable
        JScrollPane scroll = new JScrollPane(eventTable);
        scroll.setViewportView(eventTable);
        jpnTable.removeAll();
        jpnTable.setLayout(new CardLayout());
        jpnTable.add(scroll);
        jpnTable.revalidate();
        jpnTable.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchType = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        jrbtId = new javax.swing.JRadioButton();
        jrbtName = new javax.swing.JRadioButton();
        jrbtCategory = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jbtReset = new javax.swing.JButton();
        jbtDonate = new javax.swing.JButton();
        jpnTable = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jbtExpired = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jbtActive = new javax.swing.JButton();
        header = new charity.viewUser.GradientPanel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jrbtId.setBackground(new java.awt.Color(255, 255, 255));
        searchType.add(jrbtId);
        jrbtId.setSelected(true);
        jrbtId.setText("ID");

        jrbtName.setBackground(new java.awt.Color(255, 255, 255));
        searchType.add(jrbtName);
        jrbtName.setText("Tên sự kiện");

        jrbtCategory.setBackground(new java.awt.Color(255, 255, 255));
        searchType.add(jrbtCategory);
        jrbtCategory.setText("Loại");

        jLabel2.setText("Tìm kiếm");

        jbtReset.setBackground(new java.awt.Color(204, 255, 255));
        jbtReset.setText("Làm mới");
        jbtReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtResetActionPerformed(evt);
            }
        });

        jbtDonate.setBackground(new java.awt.Color(204, 255, 255));
        jbtDonate.setText("Quyên góp");
        jbtDonate.setOpaque(true);
        jbtDonate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDonateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrbtId, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbtName)
                .addGap(18, 18, 18)
                .addComponent(jrbtCategory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtDonate)
                .addGap(18, 18, 18)
                .addComponent(jbtReset)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrbtId, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrbtName)
                    .addComponent(jrbtCategory)
                    .addComponent(jLabel2))
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbtReset, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtDonate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jpnTable.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpnTableLayout = new javax.swing.GroupLayout(jpnTable);
        jpnTable.setLayout(jpnTableLayout);
        jpnTableLayout.setHorizontalGroup(
            jpnTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpnTableLayout.setVerticalGroup(
            jpnTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jbtExpired.setBackground(new java.awt.Color(204, 255, 255));
        jbtExpired.setText("Hết hạn");
        jbtExpired.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtExpiredActionPerformed(evt);
            }
        });

        jButton5.setText("jButton3");

        jbtActive.setBackground(new java.awt.Color(204, 255, 255));
        jbtActive.setText("Đang hoạt động");
        jbtActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtActiveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jbtActive)
                        .addGap(0, 0, 0)
                        .addComponent(jbtExpired))
                    .addComponent(jButton5))
                .addContainerGap(790, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtExpired)
                    .addComponent(jbtActive))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Sự kiện");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addContainerGap(933, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jpnTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbtResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtResetActionPerformed
        eventModel.setRowCount(0);
        if (jbtActive.isEnabled()) {
            eventModel = classTableModel.getExpiredEventTable();
        } else {
            eventModel = classTableModel.getActiveEventTable();
        }
        showEventTable();

        jbtDonate.setEnabled(false);
        selectedEventId = -1;
        tableMouseClick();
        txtSearch.setText("");
        jrbtId.setSelected(true);
    }//GEN-LAST:event_jbtResetActionPerformed

    private void jbtDonateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDonateActionPerformed
        if (selectedEventId != -1) {
            CharityEvent event = eventDAO.getEventById(selectedEventId);

            if (event == null) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy sự kiện!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

            DonateJDialog dialog = new DonateJDialog(parent,true, event, userId);
            dialog.setVisible(true);
        }
    }//GEN-LAST:event_jbtDonateActionPerformed

    private void jbtActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtActiveActionPerformed
        eventModel = classTableModel.getActiveEventTable();
        showEventTable();
        tableMouseClick();

        jbtExpired.setEnabled(true);
        jbtActive.setEnabled(false);
    }//GEN-LAST:event_jbtActiveActionPerformed

    private void jbtExpiredActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtExpiredActionPerformed
        eventModel = classTableModel.getExpiredEventTable();
        showEventTable();

        jbtExpired.setEnabled(false);
        jbtDonate.setEnabled(false);
        jbtActive.setEnabled(true);
    }//GEN-LAST:event_jbtExpiredActionPerformed

    //bat su kien table mouse click
    public void tableMouseClick() {
        eventTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!jbtActive.isEnabled()) {
                    if (!e.getValueIsAdjusting()) {//dam bao dong khong bi chon nhieu lan
                        int selectedRow = eventTable.getSelectedRow();
                        if (selectedRow != -1) {
                            selectedEventId = (int) eventTable.getValueAt(selectedRow, 0);
                            jbtDonate.setEnabled(true);
                        }
                    }
                }
            }

        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private charity.viewUser.GradientPanel header;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton jbtActive;
    private javax.swing.JButton jbtDonate;
    private javax.swing.JButton jbtExpired;
    private javax.swing.JButton jbtReset;
    private javax.swing.JPanel jpnTable;
    private javax.swing.JRadioButton jrbtCategory;
    private javax.swing.JRadioButton jrbtId;
    private javax.swing.JRadioButton jrbtName;
    private javax.swing.ButtonGroup searchType;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
