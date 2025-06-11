/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package charity.view.Admin;

import charity.component.ColorCustom;
import charity.component.FontCustom;
import charity.component.GButton;
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

    private JTextField txtName;
    private GButton btnSave, btnCancel;

    public CategoryDialog(JFrame owner, String title) {
        super(owner, title, true);
        this.parent = owner;
        this.title = title;
        initComponent();
    }

    private void initComponent() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(420, 250);
        setLocationRelativeTo(parent);
        setResizable(false);

        //Main panel
        JPanel pnMain = new JPanel(new BorderLayout());
        pnMain.setBackground(Color.white);
        pnMain.setBorder(new EmptyBorder(0, 0, 15, 0));

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
        gbc.insets = new Insets(10, 10,5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx =0;
        
        gbc.gridy=0;
        pnForm.add(new JLabel("Tên danh mục"), gbc);
        
        gbc.gridy=1;
        txtName = new JTextField();
        txtName.setFont(FontCustom.Arial13());
        txtName.setPreferredSize(new Dimension(350,30));
        pnForm.add(txtName, gbc);
        
        

        pnMain.add(lbHeader, BorderLayout.NORTH);
        pnMain.add(pnForm, BorderLayout.CENTER);
        add(pnMain);
    }

    public static void main(String[] args) {
        CategoryDialog dialog = new CategoryDialog(null, "Thêm danh mục");
        dialog.setVisible(true);
    }
}
