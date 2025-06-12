package charity.controller.AdminController;

import charity.component.GButton;
import charity.component.ClassTableModel;
import charity.component.ColorCustom;
import charity.model.Account;
import charity.model.Role;
import charity.model.User;
import charity.service.AccountService;
import charity.service.UserService;
import charity.utils.ScannerUtils;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author phaml
 */
public class AccountPanelController {

    private JTextField txtAddress;
    private JTextField txtEmail;
    private JTextField txtFullName;
    private JTextField txtId;
    private JTextField txtPassword;
    private JTextField txtPhoneNumber;
    private JTextField txtSearch;
    private JTextField txtUsername;
    private JComboBox<Role> jcbRole;
    private JDateChooser jdcBirthDate;
    private JRadioButton jrbFemale;
    private JRadioButton jrbMale;
    private JTable tableAccount;
    private GButton gbtAdd;
    private GButton gbtDelete;
    private GButton gbtReset;
    private GButton gbtUpdate;
    private GButton gbtSave;
    private GButton gbtCancel;

    private ClassTableModel classTableModel = null;
    private AccountService accountService = null;
    private UserService userService = null;
    private TableRowSorter<TableModel> rowSorter = null;

    private int selectedAction = -1;

    public AccountPanelController(JTextField txtAddress, JTextField txtEmail, JTextField txtFullName, JTextField txtId, JTextField txtPassword, JTextField txtPhoneNumber, JTextField txtSearch, JTextField txtUsername, JComboBox<Role> jcbRole, JDateChooser jdcBirthDate, JRadioButton jrbFemale, JRadioButton jrbMale, JTable tableAccount, GButton gbtAdd, GButton gbtDelete, GButton gbtReset, GButton gbtUpdate, GButton gbtSave, GButton gbtCancel) {
        this.txtAddress = txtAddress;
        this.txtEmail = txtEmail;
        this.txtFullName = txtFullName;
        this.txtId = txtId;
        this.txtPassword = txtPassword;
        this.txtPhoneNumber = txtPhoneNumber;
        this.txtSearch = txtSearch;
        this.txtUsername = txtUsername;
        this.jcbRole = jcbRole;
        this.jdcBirthDate = jdcBirthDate;
        this.jrbFemale = jrbFemale;
        this.jrbMale = jrbMale;
        this.tableAccount = tableAccount;
        this.gbtAdd = gbtAdd;
        this.gbtDelete = gbtDelete;
        this.gbtReset = gbtReset;
        this.gbtUpdate = gbtUpdate;
        this.gbtSave = gbtSave;
        this.gbtCancel = gbtCancel;

        init();
        initEvent();
    }

    private void init() {
        classTableModel = new ClassTableModel();
        accountService = new AccountService();
        userService = new UserService();
        gbtCancel.setVisible(false);
        gbtSave.setVisible(false);

        jcbRole.addItem(Role.User);
        jcbRole.addItem(Role.Admin);
        setTableAccount();
        clearForm();
        jdcBirthDate.setDateFormatString("dd/MM/yyyy");
    }

    private void initEvent() {
//        setGbtAddMouseEvent();
        setGbtAddEvent();
        setGbtUpdateEvent();
        setGbtSaveEvent();
        setGbtCancelEvent();
        setGbtDeleteEvent();
        setGbtResetEvent();
        ScannerUtils.setOnlyInputNumber(txtPhoneNumber);
        setHoverButton();
    }

    private void setTableAccount() {
        List<Account> accounts = accountService.getAllAccount();
        DefaultTableModel model = classTableModel.getAccountTable(accounts);
        tableAccount.setModel(model);
        tableAccount.setBackground(Color.white);

        rowSorter = new TableRowSorter<>(tableAccount.getModel());
        tableAccount.setRowSorter(rowSorter);
        setSearchTable();

        designTable(tableAccount);
//        tableAccount.setPreferredSize(new Dimension(tableAccount.getWidth(), 250));
        setTableAccountMouseEvent();
    }

    public void designTable(JTable table) {

        //table header
        table.getTableHeader().setBackground(Color.decode("#B4EBE6"));
        table.getTableHeader().setBorder(null);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 40));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        //table body
        table.setRowHeight(40);
        table.setShowVerticalLines(false);
        table.setShowGrid(false);
        table.setShowHorizontalLines(true);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));

//        table.setGridColor(new Color(230, 230, 230));
//        //size column
        table.getColumnModel().getColumn(0).setMaxWidth(400);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);

        table.getColumnModel().getColumn(1).setMaxWidth(500);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);

        table.getColumnModel().getColumn(2).setMaxWidth(500);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
//
//        table.getColumnModel().getColumn(5).setMaxWidth(500);
//        table.getColumnModel().getColumn(5).setPreferredWidth(90);

//        chu can giua
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa chữ

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        //show
        table.validate();
        table.repaint();
    }

    private void setSearchTable() {
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {

            //"(?i)" khong phan biet chu hoa chu thuong
            //khi nhap vao txtSearch
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 1,2));
                }
            }

            //khi xoa noi dung cua txtSearch
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtSearch.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 1, 2));
                }
            }

            //khi co thay doi thuoc tinh van ban
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    private void setGbtAddMouseEvent() {
        gbtAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gbtCancel.setVisible(true);
                gbtSave.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }

        });
    }

    private void setTableAccountMouseEvent() {
        tableAccount.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tableAccount.getSelectedRow();
                if (selectedRow != -1) {
                    selectedRow = tableAccount.convertRowIndexToModel(selectedRow);
                    DefaultTableModel model = (DefaultTableModel) tableAccount.getModel();

                    txtId.setText(model.getValueAt(selectedRow, 0).toString());
                    txtUsername.setText(model.getValueAt(selectedRow, 1).toString());
                    txtPassword.setText(model.getValueAt(selectedRow, 3).toString());
                    txtEmail.setText(model.getValueAt(selectedRow, 2).toString());
                    String roleStr = model.getValueAt(selectedRow, 4).toString();
                    jcbRole.setSelectedItem(Role.valueOf(roleStr));
                    // Load thêm các thông tin User (FullName, Address, Phone, Gender, Birthday)
                    int accountId = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
                    User user = userService.getUserByAccountId(accountId);
                    if (user != null) {
                        txtFullName.setText(user.getName());
                        txtAddress.setText(user.getAddress());
                        txtPhoneNumber.setText(user.getPhone());
                        jdcBirthDate.setDate(user.getBirthday());
                        if ("Nam".equalsIgnoreCase(user.getGender())) {
                            jrbMale.setSelected(true);
                        } else {
                            jrbFemale.setSelected(true);
                        }
                    }
                }
            }
        });
    }

    private void clearForm() {
        txtId.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtEmail.setText("");
        txtFullName.setText("");
        txtAddress.setText("");
        txtPhoneNumber.setText("");
        txtSearch.setText("");
        jcbRole.setSelectedIndex(0);
        jdcBirthDate.setDate(null);
        jrbMale.setSelected(true);
    }

    private Account getAccountFromForm() {
        Account acc = new Account();
        if (!txtId.getText().isEmpty()) {
            acc.setId(Integer.parseInt(txtId.getText()));
        }
        acc.setUsername(txtUsername.getText());
        acc.setPassword(txtPassword.getText());
        acc.setEmail(txtEmail.getText());
        acc.setRole((Role) jcbRole.getSelectedItem());
        return acc;
    }

    private User getUserFromForm(int accountId) {
        User user = new User();
        user.setAccountId(accountId);
        user.setName(txtFullName.getText());
        user.setAddress(txtAddress.getText());
        user.setPhone(txtPhoneNumber.getText());
        user.setGender(jrbMale.isSelected() ? "Nam" : "Nữ");
        java.util.Date utilBirthDate = jdcBirthDate.getDate();
        java.sql.Date sqlBirthDate = new java.sql.Date(utilBirthDate.getTime());
        user.setBirthday(sqlBirthDate);
        return user;
    }

    private void setGbtAddEvent() {
        gbtAdd.addActionListener(e -> {
            clearForm();
            selectedAction = 1;
            gbtSave.setVisible(true);
            gbtCancel.setVisible(true);
        });
    }

    private void setGbtUpdateEvent() {
        gbtUpdate.addActionListener(e -> {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một tài khoản để cập nhật!");
                return;
            }
            selectedAction = 2;
            gbtSave.setVisible(true);
            gbtCancel.setVisible(true);
        });
    }

    private void setGbtCancelEvent() {
        gbtCancel.addActionListener(e -> {
            clearForm();
            selectedAction = -1;
            gbtSave.setVisible(false);
            gbtCancel.setVisible(false);
        });
    }

    private void setGbtSaveEvent() {
        gbtSave.addActionListener(e -> {
            if (!validateFormInput()) {
                return;
            }

            Account acc = getAccountFromForm();
            User user = getUserFromForm(acc.getId());
            if (selectedAction == 1) {
                // thêm mới
                boolean result = accountService.addAccountWithUser(acc, user);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Thêm tài khoản thành công!");
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm tài khoản thất bại!");
                }
            } else if (selectedAction == 2) {
                // cập nhật
                boolean result = accountService.updateAccountWithUser(acc, user);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Cập nhật tài khoản thành công!");
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật tài khoản thất bại!");
                }
            }

            // reset form và reload
            clearForm();
            setTableAccount();
            gbtSave.setVisible(false);
            gbtCancel.setVisible(false);
            selectedAction = -1;
        });
    }

    private void setGbtDeleteEvent() {
        gbtDelete.addActionListener(e -> {
            int selectedRow = tableAccount.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản để xóa!");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa tài khoản này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int accountId = Integer.parseInt(tableAccount.getValueAt(selectedRow, 0).toString());
                boolean result = accountService.deleteAccount(accountId);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Xóa tài khoản thành công!");
                    setTableAccount();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Xóa tài khoản thất bại!");
                }
            }
        });
    }

    private void setGbtResetEvent() {
        gbtReset.addActionListener(e -> {
            clearForm();
            gbtSave.setVisible(false);
            gbtCancel.setVisible(false);
            selectedAction = -1;
        });
    }

    private boolean validateFormInput() {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        String email = txtEmail.getText().trim();
        String fullName = txtFullName.getText().trim();
        String phone = txtPhoneNumber.getText().trim();

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tên đăng nhập không được để trống!");
            return false;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mật khẩu không được để trống!");
            return false;
        }
        if (!ScannerUtils.isPasswordValid(password)){
            return false;
        }

        if (!email.matches("^\\S+@\\S+\\.\\S+$")) {
            JOptionPane.showMessageDialog(null, "Email không hợp lệ!");
            return false;
        }

        if (fullName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Họ tên không được để trống!");
            return false;
        }

        if (!phone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải là 10 chữ số!");
            return false;
        }

        if (jdcBirthDate.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày sinh!");
            return false;
        }

        return true;
    }

    public void setHoverButton() {
        gbtAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                gbtAdd.setColor(ColorCustom.colorBtnAdd());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtAdd.setColor(ColorCustom.colorBtnAddHover());
            }
        });

        gbtDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                gbtDelete.setColor(ColorCustom.colorBtnDelete());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtDelete.setColor(ColorCustom.colorBtnDeleteHover());
            }
        });

        gbtUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                gbtUpdate.setColor(ColorCustom.colorBtnUpdate());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtUpdate.setColor(ColorCustom.colorBtnUpdateHover());
            }
        });

        gbtReset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                gbtReset.setColor(ColorCustom.colorBtnReset());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtReset.setColor(ColorCustom.colorBtnResetHover());
            }
        });

        gbtCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                gbtCancel.changeColor("#D32F2F");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtCancel.changeColor("#F44336");
            }
        });

        gbtSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                gbtSave.changeColor("#43A047");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtSave.changeColor("#66BB6A");
            }
        });

    }
}
