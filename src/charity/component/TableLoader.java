package charity.component;

import charity.model.CharityEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

public class TableLoader extends SwingWorker<DefaultTableModel, Void> {
    private final JPanel targetPanel;
    private final List<CharityEvent> events;
    private final DefaultTableModel tableModel;
    private final JTable table;
    
    public TableLoader(JPanel targetPanel, List<CharityEvent> events, 
                      DefaultTableModel tableModel, JTable table) {
        this.targetPanel = targetPanel;
        this.events = events;
        this.tableModel = tableModel;
        this.table = table;
    }
    
    @Override
    protected DefaultTableModel doInBackground() {
        return tableModel; // Process data in background
    }
    
    @Override
    protected void done() {
        try {
            // Setup UI components in EDT
            JScrollPane scroll = new JScrollPane(table);
            table.setFillsViewportHeight(true);
            table.setBackground(Color.white);
            scroll.getViewport().setBackground(Color.white);
            scroll.setPreferredSize(new Dimension(targetPanel.getWidth(), 400));
            
            targetPanel.removeAll();
            targetPanel.setBackground(Color.white);
            targetPanel.setLayout(new CardLayout());
            targetPanel.add(scroll);
            targetPanel.revalidate();
            targetPanel.repaint();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 