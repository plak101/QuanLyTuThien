package charity.UserController;

import charity.model.Account;
import charity.model.User;
import charity.service.AccountService;
import charity.service.UserService;
import charity.utils.ScannerUtils;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
//import java.sql.Date;
import javax.swing.*;

public class InforPanelController {

    private int userId, accountId;
    private JFrame parent;
    private JTextField txtName, txtAddress, txtPhone, txtUsername, txtEmail, txtPassword, txtNewPassword, txtPasswordConfirm;
    private JDateChooser jdcBirthday;
    private JRadioButton jrbtMale, jrbtFemale;
    private JButton jbtUserSave, jbtUserCancel, jbtAccountSave, jbtAccountCancel, jbtReset, jbtUserUpdate, jbtPasswordUpdate, jbtEmailUpdate;
    private JPanel jpnPasswordConfirm;

    private AccountService accountService;
    private UserService userService;

    private Account account;
    private User user;

    ScannerUtils scu = new ScannerUtils();
    private int choice;

    public InforPanelController(int accountId, int userId, JFrame parent, JTextField txtName, JTextField txtAddress, JTextField txtPhone, JTextField txtUsername, JTextField txtEmail, JTextField txtPassword, JTextField txtNewPassword, JTextField txtPasswordConfirm, JDateChooser jdcBirthday, JRadioButton jrbtMale, JRadioButton jrbtFemale, JButton jbtUserSave, JButton jbtUserCancel, JButton jbtAccountSave, JButton jbtAccountCancel, JButton jbtReset, JButton jbtUserUpdate, JButton jbtPasswordUpdate, JButton jbtEmailUpdate, JPanel jpnPasswordConfirm) {
        this.accountId = accountId;
        this.userId = userId;
        this.parent = parent;
        this.txtName = txtName;
        this.txtAddress = txtAddress;
        this.txtPhone = txtPhone;
        this.txtUsername = txtUsername;
        this.txtEmail = txtEmail;
        this.txtPassword = txtPassword;
        this.txtNewPassword = txtNewPassword;
        this.txtPasswordConfirm = txtPasswordConfirm;
        this.jdcBirthday = jdcBirthday;
        this.jrbtMale = jrbtMale;
        this.jrbtFemale = jrbtFemale;
        this.jbtUserSave = jbtUserSave;
        this.jbtUserCancel = jbtUserCancel;
        this.jbtAccountSave = jbtAccountSave;
        this.jbtAccountCancel = jbtAccountCancel;
        this.jbtReset = jbtReset;
        this.jbtUserUpdate = jbtUserUpdate;
        this.jbtPasswordUpdate = jbtPasswordUpdate;
        this.jbtEmailUpdate = jbtEmailUpdate;
        this.jpnPasswordConfirm = jpnPasswordConfirm;

        accountService = new AccountService();
        userService = new UserService();
//        lockComponents();
    }

    public void loadData() {
        clearData();

        //hien thong tin user
        user = userService.getUserById(userId);
        if (user != null) {
            txtName.setText(user.getName());
            txtAddress.setText(user.getAddress());
            jdcBirthday.setDate(user.getBirthday());
            txtPhone.setText(user.getPhone());
            if (user.getGender().equals("Nam")) {
                jrbtMale.setSelected(true);
            } else {
                jrbtFemale.setSelected(true);
            }
        }

        //hien thong tin account
        account = accountService.getAccountById(accountId);
        if (account != null) {
            txtUsername.setText(account.getUsername());
            txtEmail.setText(account.getEmail());
            txtPassword.setText(account.getPassword());
        } else {
            System.out.println(accountId);
        }

        //an nut save, cancel
        reset();
        int choice = 0;
    }

    public void showUserButton(boolean b) {
        jbtUserSave.setVisible(b);
        jbtUserCancel.setVisible(b);
    }

    public void showAccountButton(boolean b) {
        jbtAccountSave.setVisible(b);
        jbtAccountCancel.setVisible(b);
    }

    public void showPasswordConfirm(boolean b) {
        jpnPasswordConfirm.setVisible(b);
    }

    public void clearData() {
        txtName.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
        txtPhone.setText("");
        txtUsername.setText("");
        jdcBirthday.setDate(null);
        jrbtMale.setSelected(false);
        jrbtFemale.setSelected(false);
    }

    public void reset() {
        showUserButton(false);
        showAccountButton(false);
        showPasswordConfirm(false);
    }

    public void setEvent() {
        setJbtResetEvent();
        setJbtEmailUpdate();
        setJbtPasswordUpdate();
        setJbtUserUpdate();
        setJbtUserSaveEvent();
        setJbtAccountCancelEvent();
        setJbtAccountSaveEvent();
        setJbtUserSaveEvent();
        setJbtUserCancelEvent();
    }

    public void setJbtResetEvent() {
        jbtReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });
    }

    public void setJbtUserUpdate() {
        jbtUserUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
                showUserButton(true);
            }

        });

    }

    public void setJbtPasswordUpdate() {
        jbtPasswordUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
                showPasswordConfirm(true);
                showAccountButton(true);
                choice = 2;
                txtNewPassword.setText("");
                txtPasswordConfirm.setText("");
            }

        });

    }

    public void setJbtEmailUpdate() {
        jbtEmailUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
                showAccountButton(true);
                choice = 1;
            }
        });
    }

    public void setJbtUserSaveEvent() {
        jbtUserSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!validateInput()) {
                    return;
                }

                String name = capitalizeFirstLetter(txtName.getText().trim());
                String address = capitalizeFirstLetter(txtAddress.getText().trim());
                String phone = txtPhone.getText().trim();

                // Lấy ngày sinh
                java.util.Date utilDate = jdcBirthday.getDate();
                java.sql.Date birthday = new java.sql.Date(utilDate.getTime());

                // Giới tính
                String gender = jrbtMale.isSelected() ? "Nam" : "Nữ";

                User u = new User(accountId, userId, name, address, phone, gender, birthday);
                if (user == null) {
                    //neu user chua co thi them moi
                    if (userService.addUser(u)) {
                        JOptionPane.showMessageDialog(null, "Cập nhật thông tin thành công");
//                       cap nhat user va userid
                        user = userService.getUserByAccountId(accountId);
                        if (user != null) {
                            userId = user.getId();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật thông tin thất bại!");
                    }
                } else {
                    if (userService.updateUser(u)) {
                        JOptionPane.showMessageDialog(null, "Cập nhật thông tin thành công");
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật thông tin thất bại!");
                    }
                }

                //an nut
                showUserButton(false);
            }
        }
        );
    }

    public void setJbtUserCancelEvent() {
        jbtUserCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });
    }

    public void setJbtAccountCancelEvent() {
        jbtAccountCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });
    }

    public void setJbtAccountSaveEvent() {
        jbtAccountSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (choice == 1) {
                    if (scu.isEmailVaid(txtEmail)) {
                        String email = txtEmail.getText().trim();
                        account.setEmail(email);
                        if (accountService.updateAccount(account)) {
                            JOptionPane.showMessageDialog(null, "Cập nhật Email thành công!");
                            loadData();
                        } else {
                            JOptionPane.showMessageDialog(null, "Cập nhật Email thất bại!");
                        }
                    }
                } else if (choice == 2) {
                    String newPassword = txtNewPassword.getText().trim();
                    String passwordConfirm = txtPasswordConfirm.getText().trim();
                    if (scu.isPasswordValid(newPassword, passwordConfirm)) {
                        account.setPassword(newPassword);

                        if (accountService.updateAccount(account)) {
                            JOptionPane.showMessageDialog(null, "Cập nhật mật khẩu thành công!");
                            loadData();
                        } else {
                            JOptionPane.showMessageDialog(null, "Cập nhật mật khẩu thất bại!");
                        }
                    }
                }
            }
        });
    }

    private boolean validateInput() {
        if (scu.isEmpty(txtName, "Vui lòng nhập họ và tên!")) {
            return false;
        }
        if (scu.isEmpty(txtAddress, "Vui lòng nhập địa chỉ!")) {
            return false;
        }
        if (scu.isDateNull(jdcBirthday, "Vui lòng chọn ngày sinh!")) {
            return false;
        }
        if (scu.isDateAfterToday(jdcBirthday, "Ngày sinh không hợp lệ!")) {
            return false;
        }
        if (!scu.isGenderSelected(jrbtMale, jrbtFemale, "Vui lòng chọn giới tính!")) {
            return false;
        }
        if (!scu.isPhoneValid(txtPhone, "Vui lòng nhập số điện thoại hợp lệ (10 chữ số)!")) {
            return false;
        }
        return true;
    }

    private String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        String[] words = input.trim().toLowerCase().split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
            }
        }
        return result.toString().trim();
    }
}
