package charity.controller.UserController;

import charity.component.GButton;
import charity.model.Account;
import charity.model.User;
import charity.service.AccountService;
import charity.service.UserService;
import charity.utils.ScannerUtils;
import charity.view.Login.LoginFrame;
import charity.view.User.UserUI;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.sql.Date;
import javax.swing.*;

public class InforPanelController {

    private int userId, accountId;
    private JFrame parent;
    private JTextField txtName, txtAddress, txtPhone, txtUsername, txtEmail, txtPassword, txtNewPassword, txtPasswordConfirm;
    private JDateChooser jdcBirthday;
    private JRadioButton jrbtMale, jrbtFemale;
    private GButton gbtReset, gbtUserUpdate, gbtPasswordUpdate, gbtEmailUpdate, gbtUserSave, gbtUserCancel, gbtAccountSave, gbtAccountCancel;
    private JPanel jpnPasswordConfirm;

    private AccountService accountService;
    private UserService userService;

    private Account account;
    private User user;

    ScannerUtils scu = new ScannerUtils();
    private int choice;

    public InforPanelController(int accountId, int userId, JFrame parent, JTextField txtName, JTextField txtAddress, JTextField txtPhone, JTextField txtUsername, JTextField txtEmail, JTextField txtPassword, JTextField txtNewPassword, JTextField txtPasswordConfirm, JDateChooser jdcBirthday, JRadioButton jrbtMale, JRadioButton jrbtFemale, GButton gbtUserSave, GButton gbtUserCancel, GButton gbtAccountSave, GButton gbtAccountCancel, GButton gbtReset, GButton gbtUserUpdate, GButton gbtPasswordUpdate, GButton gbtEmailUpdate, JPanel jpnPasswordConfirm) {
        this.parent = parent;
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
        this.gbtUserSave = gbtUserSave;
        this.gbtUserCancel = gbtUserCancel;
        this.gbtAccountSave = gbtAccountSave;
        this.gbtAccountCancel = gbtAccountCancel;
        this.gbtReset = gbtReset;
        this.gbtUserUpdate = gbtUserUpdate;
        this.gbtPasswordUpdate = gbtPasswordUpdate;
        this.gbtEmailUpdate = gbtEmailUpdate;
        this.jpnPasswordConfirm = jpnPasswordConfirm;

        accountService = new AccountService();
        userService = new UserService();
//        lockComponents();
        ScannerUtils.setOnlyInputNumber(txtPhone);
        setHoverButtonEvent();
    }

    public void loadData() {
        clearData();
        user = userService.getUserByAccountId(accountId);

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
        gbtUserSave.setVisible(b);
        gbtUserCancel.setVisible(b);
    }

    public void showAccountButton(boolean b) {
        gbtAccountSave.setVisible(b);
        gbtAccountCancel.setVisible(b);
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
        setGbtResetEvent();
        setGbtEmailUpdate();
        setGbtPasswordUpdate();
        setGbtUserUpdate();
        setGbtAccountCancelEvent();
        setGbtAccountSaveEvent();
        setGbtUserSaveEvent();
        setGbtUserCancelEvent();
    }

    public void setGbtResetEvent() {
        gbtReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });
    }

    public void setGbtUserUpdate() {
        gbtUserUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
                showUserButton(true);
            }

        });

    }

    public void setGbtPasswordUpdate() {
        gbtPasswordUpdate.addActionListener(new ActionListener() {
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

    public void setGbtEmailUpdate() {
        gbtEmailUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
                showAccountButton(true);
                choice = 1;
            }
        });
    }

    public void setGbtUserSaveEvent() {
        gbtUserSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!validateInput()) {
                    return;
                }

                // Lấy dữ liệu từ giao diện người dùng
                String name = capitalizeFirstLetter(txtName.getText().trim());
                String address = capitalizeFirstLetter(txtAddress.getText().trim());
                String phone = txtPhone.getText().trim();
                java.util.Date utilDate = jdcBirthday.getDate();
                java.sql.Date birthday = new java.sql.Date(utilDate.getTime());
                String gender = jrbtMale.isSelected() ? "Nam" : "Nữ";

                User u = new User(accountId, userId, name, address, phone, gender, birthday);
                //test
                System.out.println(u);
                boolean success;

                if (user == null) {
                    // Thêm mới người dùng
                    success = userService.addUser(u);
                    if (success) {
                        //test
                        user = userService.getUserByAccountId(accountId);
                        System.out.println(user.getId());
                        if (user != null) {
                            userId = user.getId();
                        }
                        JOptionPane.showMessageDialog(null, "Cập nhật thông tin thành công!");
                        ((UserUI) parent).getController().loadDataUpdate();
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật thông tin thất bại!");
                    }
                } else {
                    // Cập nhật người dùng hiện tại
                    success = userService.updateUser(u);
                    System.out.println(user.getId());

                    if (success) {
                        JOptionPane.showMessageDialog(null, "Cập nhật thông tin thành công");
                        ((UserUI) parent).getController().loadDataUpdate();

                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật thông tin thất bại!");
                    }
                }

                // Ẩn nút sau khi lưu
                showUserButton(false);
            }
        });
    }

    public void setGbtUserCancelEvent() {
        gbtUserCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });
    }

    public void setGbtAccountCancelEvent() {
        gbtAccountCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });
    }

    public void setGbtAccountSaveEvent() {
        gbtAccountSave.addActionListener(new ActionListener() {
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
                    if (scu.isPasswordValid(newPassword)) {
                        if (!passwordConfirm.equals(newPassword)) {
                            JOptionPane.showMessageDialog(null, "Mật khẩu nhập lại không khớp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
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
        String phoneNumber = txtPhone.getText().trim();
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

    public void setHoverButtonEvent() {
        gbtEmailUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                gbtEmailUpdate.changeColor("#2d99ae");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtEmailUpdate.changeColor("#5dc1d3");
            }
        });
        gbtReset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                gbtReset.changeColor("#2d99ae");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtReset.changeColor("#5dc1d3");
            }
        });
        gbtPasswordUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                gbtPasswordUpdate.changeColor("#2d99ae");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtPasswordUpdate.changeColor("#5dc1d3");
            }
        });
        gbtUserUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                gbtUserUpdate.changeColor("#2d99ae");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtUserUpdate.changeColor("#5dc1d3");
            }
        });
        gbtAccountCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                gbtAccountCancel.changeColor("#D32F2F");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtAccountCancel.changeColor("#F44336");
            }
        });
        gbtUserCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                gbtUserCancel.changeColor("#D32F2F");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtUserCancel.changeColor("#F44336");
            }
        });

        gbtAccountSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                gbtAccountSave.changeColor("#43A047");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtAccountSave.changeColor("#66BB6A");
            }
        });
        gbtUserSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                gbtUserSave.changeColor("#43A047");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtUserSave.changeColor("#66BB6A");
            }
        });
    }

    public void reloadData() {
        loadData();
    }
}
