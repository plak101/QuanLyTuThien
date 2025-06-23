package charity.controller.UserController;

import charity.model.User;
import charity.service.AccountService;
import charity.service.UserService;
import charity.view.Login.LoginFrame;
import charity.view.Login.NewLogin;
import charity.view.User.DonationPanel;
import charity.view.User.InforPanel;
import charity.view.User.EventPanel;
import charity.view.User.MainPanel;
import charity.view.User.MyDonationPanel;
import charity.view.User.OrganizationPanel;
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

    private JPanel jpnMainOption,jpnEventOption, jpnOrganizationOption, jpnDonationOption, jpnMyDonationOption, jpnInforOption, jpnRight;
    private JTextField txtUsername;
    private JLabel jlbLogout;
    private int accountId;
    private int userId;

    private User user;
    private UserService userService;
    private AccountService accountService;
    private JFrame parent;
    private JPanel activePanel;
    private MainPanel mainPanel;
    private EventPanel eventPanel;
    private OrganizationPanel organizationPanel;
    private DonationPanel donationListPanel;
    private MyDonationPanel myDonationPanel;
    private InforPanel inforPanel;

    public UserUIController(JFrame parent, int accountId, JPanel jpnMainOption, JPanel jpnEventOption, JPanel jpnOrganizationOption, JPanel jpnDonationOption, JPanel jpnMyDonationOption, JPanel jpnInforOption, JPanel jpnRight, JTextField txtUsername, JLabel jlbLogout) {
        this.jpnMainOption = jpnMainOption;
        this.jpnEventOption = jpnEventOption;
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
        
        //Main
        mainPanel = new MainPanel(parent, accountId, userId);
        jpnRight.add(mainPanel, "mainPanel");
        
        //1. Event
        eventPanel = new EventPanel(parent, accountId, userId);
        jpnRight.add(eventPanel, "eventPanel");

        //2 organization
        organizationPanel = new OrganizationPanel();
        jpnRight.add(organizationPanel, "organizationPanel");

        //3.Donation List
        donationListPanel = new DonationPanel(parent, accountId, userId);
        jpnRight.add(donationListPanel, "donationListPanel");

        //4.MyDonation
        myDonationPanel = new MyDonationPanel(parent, accountId, userId);
        jpnRight.add(myDonationPanel, "myDonationPanel");

        //5 profile
        inforPanel = new InforPanel(parent, accountId, userId);
        jpnRight.add(inforPanel, "inforPanel");
    }

    public void setMouseEvent() {
        setMouseEvent(jpnMainOption);
        setMouseEvent(jpnEventOption);
        setMouseEvent(jpnDonationOption);
        setMouseEvent(jpnMyDonationOption);
        setMouseEvent(jpnOrganizationOption);
        setMouseEvent(jpnInforOption);
        setJlbLogoutEvent();
    }

    private void setActivePanel(JPanel selectedPanel) {
        activePanel = selectedPanel;

        JPanel[] panels = {jpnMainOption,jpnEventOption, jpnOrganizationOption, jpnDonationOption, jpnMyDonationOption, jpnInforOption};
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
                if (panel == jpnMainOption){
                    if (mainPanel instanceof MainPanel) {
//                        eventPanel.getController().reloadData();
                        cardLayout.show(jpnRight, "mainPanel");
                    }
                }
                else if (panel == jpnEventOption) {
                    if (eventPanel instanceof EventPanel) {
                        eventPanel.getController().reloadData();
                        cardLayout.show(jpnRight, "eventPanel");
                    }
                } else if (panel == jpnOrganizationOption) {
                    if (organizationPanel instanceof OrganizationPanel) {
                        organizationPanel.getController().reloadData();
                        cardLayout.show(jpnRight, "organizationPanel");
                    }
                } else if (panel == jpnDonationOption) {
                    if (donationListPanel instanceof DonationPanel) {
                        donationListPanel.getController().reloadData();
                        cardLayout.show(jpnRight, "donationListPanel");
                    }
                } else if (panel == jpnMyDonationOption) {
                    if (myDonationPanel instanceof MyDonationPanel) {
                        myDonationPanel.getController().reloadData();
                        cardLayout.show(jpnRight, "myDonationPanel");
                    }
                } else if (panel == jpnInforOption) {
                    if (inforPanel instanceof InforPanel) {
                        inforPanel.getController().reloadData();
                        cardLayout.show(jpnRight, "inforPanel");
                    }
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
                    NewLogin login = new NewLogin();
                    login.setVisible(true);
                });
            }

        });
    }

    public void loadDataUpdate() {
        user = userService.getUserByAccountId(accountId);
        if (user != null) {
            userId = user.getId();
            txtUsername.setText(user.getName());
        } else {
            System.err.println("Không tìm thấy user");
        }
    }
    public void reloadEventPanel(){
        eventPanel.getController().reloadData();
    }
    public void reloadMainPanel(){
        MainPanelController controller = mainPanel.getController();
        controller.loadEvent();
        
    }
}
