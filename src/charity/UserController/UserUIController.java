package charity.UserController;

import charity.model.User;
import charity.service.AccountService;
import charity.service.UserService;
import charity.viewLogin.LoginFrame;
import charity.viewUser.DonationListPanel;
import charity.viewUser.InforPanel;
import charity.viewUser.MainPanel;
import charity.viewUser.MyDonationPanel;
import charity.viewUser.OrganizationPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author phaml
 */
public class UserUIController {

    private JPanel jpnMainOption, jpnOrganizationOption, jpnDonationOption, jpnMyDonationOption, jpnInforOption, jpnRight;
    private JTextField txtUsername;
    private JLabel jlbLogout;
    private int accountId;
    private int userId;

    private User user;
    private UserService userService;
    private AccountService accountService;
    private JFrame parent;
    private JPanel activePanel;

    public UserUIController(JFrame parent, int accountId, JPanel jpnMainOption, JPanel jpnOrganizationOption, JPanel jpnDonationOption, JPanel jpnMyDonationOption, JPanel jpnInforOption, JPanel jpnRight, JTextField txtUsername, JLabel jlbLogout) {
        this.jpnMainOption = jpnMainOption;
        this.jpnOrganizationOption = jpnOrganizationOption;
        this.jpnDonationOption = jpnDonationOption;
        this.jpnMyDonationOption = jpnMyDonationOption;
        this.jpnInforOption = jpnInforOption;
        this.jpnRight = jpnRight;
        this.txtUsername = txtUsername;
        this.jlbLogout = jlbLogout;
        this.accountId = accountId;
        this.parent = parent;

        userService = new UserService();
        accountService = new AccountService();

        user = userService.getUserByAccountId(accountId);
        if (user != null) {
            userId = user.getId();
        }
    }

    public void loadData() {
        //hien thi ten user

        if (user == null) {
            txtUsername.setText("USER00" + accountId);
        } else {
            txtUsername.setText(user.getName());
        }

        //set acctive panel
        setActivePanel(jpnMainOption);

        //set card lay out
        setupCardLayout();
    }

    //    2. cai dat cardlayout
    public void setupCardLayout() {
        jpnRight.setLayout(new CardLayout());
        //1. Main 
        MainPanel mainPanel = new MainPanel(parent, accountId, userId);
        jpnRight.add(mainPanel, "mainPanel");

        //2 organization
        OrganizationPanel organizationPanel = new OrganizationPanel();
        jpnRight.add(organizationPanel, "organizationPanel");

        //3.Donation List
        DonationListPanel donationListPanel = new DonationListPanel(parent, accountId, userId);
        jpnRight.add(donationListPanel, "donationListPanel");

        //4.MyDonation
        MyDonationPanel myDonationPanel = new MyDonationPanel(parent, accountId, userId);
        jpnRight.add(myDonationPanel, "myDonationPanel");

        //5 profile
        InforPanel inforPanel = new InforPanel(parent, accountId, userId);
        jpnRight.add(inforPanel, "inforPanel");
    }

    public void setMouseEvent() {
        setMouseEvent(jpnMainOption);
        setMouseEvent(jpnDonationOption);
        setMouseEvent(jpnMyDonationOption);
        setMouseEvent(jpnOrganizationOption);
        setMouseEvent(jpnInforOption);
        setJlbLogoutEvent();
    }

    private void setActivePanel(JPanel selectedPanel) {
        activePanel = selectedPanel;

        JPanel[] panels = {jpnMainOption, jpnOrganizationOption, jpnDonationOption, jpnMyDonationOption, jpnInforOption};
        for (JPanel panel : panels) {
            if (panel == selectedPanel) {
                panel.setOpaque(true);
                panel.setBackground(Color.decode("#006666")); // Màu active
            } else {
                panel.setOpaque(false);
                panel.repaint();
            }
        }
    }

    private void setMouseEvent(JPanel panel) {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                if (panel != activePanel) {
                    panel.setOpaque(false);
                    panel.repaint();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (panel != activePanel) {
                    panel.setOpaque(true);
                    panel.setBackground(Color.decode("#006666"));
                    panel.repaint();
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                setActivePanel(panel);
                CardLayout cardLayout = (CardLayout) jpnRight.getLayout();
                if (panel == jpnMainOption) {
                    cardLayout.show(jpnRight, "mainPanel");
                } else if (panel == jpnOrganizationOption) {
                    cardLayout.show(jpnRight, "organizationPanel");
                } else if (panel == jpnDonationOption) {
                    cardLayout.show(jpnRight, "donationListPanel");
                } else if (panel == jpnMyDonationOption) {
                    cardLayout.show(jpnRight, "myDonationPanel");
                } else if (panel == jpnInforOption) {
                    cardLayout.show(jpnRight, "inforPanel");
                }
            }

        });
    }

    public void setJlbLogoutEvent() {
        jlbLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                jlbLogout.setOpaque(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jlbLogout.setOpaque(true);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                parent.dispose();
                SwingUtilities.invokeLater(() -> {
                    LoginFrame login = new LoginFrame();
                    login.setVisible(true);
                });
            }

        });
    }
}
