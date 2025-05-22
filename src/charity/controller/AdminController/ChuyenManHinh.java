package charity.controller.AdminController;

import charity.bean.DanhMuc;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import charity.view.Admin.AccountPanel;
import charity.view.Admin.CharityEventPanel;
import charity.view.Admin.DonationPanel;
import charity.view.Admin.OrganizationPanel;
import charity.view.Admin.StatisticsPanel;
import charity.view.Admin.TrangChu;


public class ChuyenManHinh {

    private final JPanel root;
    private String kindSelected = "";
    private List<DanhMuc> listItem;

    public ChuyenManHinh(JPanel jpnRoot) {
        this.root = jpnRoot;
    }

    public void setView(JPanel jpnItem, JLabel jibItem) {
        kindSelected = "TrangChu";
        jpnItem.setBackground(new Color(96, 100, 191));
        jibItem.setBackground(new Color(96, 100, 191));

        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new TrangChu());
        root.validate();
        root.repaint();
    }

    public void setEvent(List<DanhMuc> listItem) {
        this.listItem = listItem;
        for (DanhMuc item : listItem) {
            item.getJib().addMouseListener(new LabelEvent(
                    item.getKind(), item.getJpn(), item.getJib()));
        }
    }

    class LabelEvent implements MouseListener {

        private JPanel node;

        private String kind;
        private JPanel jpnItem;
        private JLabel jpbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jpbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jpbItem = jpbItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch (kind) {
                case "TrangChu":
                    node = new TrangChu();
                    break;
                case "QuanLyChuongTrinh":
                    node = new CharityEventPanel();
                    break;
                case "QuanLyQuyenGop":
//                    node = new DonationListPanel(null, 0, 0);
                    node = new DonationPanel(null, 0, 0);
                    break;
                case "QuanLyToChuc":
                    node = new OrganizationPanel();
                    break;
                case "QuanLyTaiKhoan":
                    node = new AccountPanel();
                    break;
                case "ThongKe":
                    node = new StatisticsPanel();
                    break;
                default:
                    node = new TrangChu();
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackground(kind);
            kindSelected = kind;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(new Color(96, 100, 191));
            jpbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(96, 100, 191));
            jpbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind)) {
                jpnItem.setBackground(new Color(76, 175, 80));
                jpbItem.setBackground(new Color(76, 175, 80));
            }
        }

        public void setChangeBackground(String kind) {
            for (DanhMuc item : listItem) {
                if (item.getKind().equalsIgnoreCase(kind)) {
                    item.getJpn().setBackground(new Color(96, 100, 191));
                    item.getJib().setBackground(new Color(96, 100, 191));
                } else {
                    item.getJpn().setBackground(new Color(76, 175, 80));
                    item.getJib().setBackground(new Color(76, 175, 80));
                }
            }
        }
    }
}
