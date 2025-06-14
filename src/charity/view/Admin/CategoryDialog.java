package charity.view.Admin;

import charity.component.ColorCustom;
import charity.component.FontCustom;
import charity.component.GButton;
import charity.controller.AdminController.CategoryDialogController;
import charity.model.Category;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author phaml
 */
public class CategoryDialog extends JDialog {

    private JFrame parent;
    private String title;
    private String action;
    private Category category;
    private CategoryDialogController controller;

    private JTextField txtName;
    private GButton btnSave, btnCancel;

    public CategoryDialog(JFrame owner, String title, String action) {
        super(owner, title, true);
        this.parent = owner;
        this.title = title;
        this.action = action;
        initComponent();

        this.controller = new CategoryDialogController(this);
    }

    public CategoryDialog(JFrame owner, String title, String action, Category category) {
        super(owner, title, true);
        this.parent = owner;
        this.title = title;
        this.action = action;
        this.category = category;
        initComponent();
        this.txtName.setText(category.getCategoryName());
        this.controller = new CategoryDialogController(this);

    }

    private void initComponent() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(420, 300);
        setLocationRelativeTo(parent);
        setResizable(false);

        //Main panel
        JPanel pnMain = new JPanel(new BorderLayout());
        pnMain.setBackground(Color.white);
        pnMain.setBorder(new EmptyBorder(0, 0, 0, 0));

        //header
        JLabel lbHeader = new JLabel(title);
        lbHeader.setOpaque(true);
        lbHeader.setBackground(ColorCustom.backroundHeaderTitle());
        lbHeader.setForeground(Color.WHITE);
        lbHeader.setFont(FontCustom.Arial18B());
        lbHeader.setPreferredSize(new Dimension(0, 50));
        lbHeader.setHorizontalAlignment((int) CENTER_ALIGNMENT);

        // Form
        JPanel pnForm = new JPanel(new GridBagLayout());
        pnForm.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;

        gbc.gridy = 0;
        pnForm.add(new JLabel("Tên danh mục"), gbc);

        gbc.gridy = 1;
        txtName = new JTextField();
        txtName.setFont(FontCustom.Arial13());
        txtName.setPreferredSize(new Dimension(350, 30));
        pnForm.add(txtName, gbc);

        //button
        JPanel pnButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        pnButton.setOpaque(false);
        pnButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        btnSave = createButton("Lưu", ColorCustom.colorBtnAdd());
        btnCancel = createButton("Hủy", ColorCustom.colorBtnDelete());

        pnButton.add(btnSave);
        pnButton.add(btnCancel);

        pnMain.add(lbHeader, BorderLayout.NORTH);
        pnMain.add(pnForm, BorderLayout.CENTER);
        pnMain.add(pnButton, BorderLayout.SOUTH);
        add(pnMain);
    }

    //dinh dang nut
    private GButton createButton(String text, Color color) {
        GButton btn = new GButton(text, color);
        btn.setForeground(Color.WHITE);
        btn.setPreferredSize(new Dimension(100, 30));
        btn.setFont(FontCustom.Arial13B());
        return btn;
    }

    public JTextField getTxtName() {
        return txtName;
    }

    public String getCategoryName() {
        return txtName.getText();
    }

    public Category getCategory() {
        return category;
    }

    public GButton getBtnSave() {
        return btnSave;
    }

    public GButton getBtnCancel() {
        return btnCancel;
    }

    public String getAction() {
        return action;
    }

}
