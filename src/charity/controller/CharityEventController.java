package charity.controller;

import charity.controller.ClassTableModel;
import charity.model.CharityEvent;
import charity.service.CharityEventService;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CharityEventController {

    private JPanel jpnTable;

    private ClassTableModel classTableModel = null;
    private CharityEventService eventService = null;

    private TableRowSorter<TableModel> rowSorter = null;
    private JTable eventTable = null;

    private int selectedEventId = -1;

    public CharityEventController(JPanel jpnTable) {
        this.jpnTable = jpnTable;
        eventService = new CharityEventService();
        classTableModel = new ClassTableModel();
        
        showEventTable();
    }
    
    
    public void showEventTable() {
        //setup event table
        List<CharityEvent> events = new ArrayList<>();
        
        events = eventService.getActiveEventList();

        DefaultTableModel model = classTableModel.getEventTable(events);
        eventTable = new JTable(model);

        //setup rowsorter
        rowSorter = new TableRowSorter<>(eventTable.getModel());
        eventTable.setRowSorter(rowSorter);
//        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
//
//            //"(?i)" khong phan biet chu hoa chu thuong
//            //khi nhap vao txtSearch
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                String text = txtSearch.getText();
//                if (text.trim().length() == 0) {
//                    rowSorter.setRowFilter(null);
//                } else {
//                    if (jrbtId.isSelected()) {
//                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0));
//                    } else if (jrbtEvent.isSelected()) {
//                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 1));
//                    } else {
//                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 2));
//                    }
//
//                }
//            }
//
//            //khi xoa noi dung cua txtSearch
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                String text = txtSearch.getText();
//
//                if (text.trim().length() == 0) {
//                    rowSorter.setRowFilter(null);
//                } else {
//                    if (jrbtId.isSelected()) {
//                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0));
//                    } else if (jrbtEvent.isSelected()) {
//                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 1));
//                    } else {
//                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 2));
//                    }
//                }
//            }
//
//            //khi co thay doi thuoc tinh van ban
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//            }
//        });

        designTable(eventTable);

        //hien thi ra jpnTable
        JScrollPane scroll = new JScrollPane(eventTable);
        eventTable.setFillsViewportHeight(true);
        eventTable.setBackground(Color.white);
        scroll.getViewport().setBackground(Color.white);
        scroll.setPreferredSize(new Dimension(jpnTable.getWidth(), 250));
        jpnTable.removeAll();
        jpnTable.setBackground(Color.white);

        jpnTable.setLayout(new CardLayout());
        jpnTable.add(scroll);
        jpnTable.revalidate();
        jpnTable.repaint();
//        setTableClickEvent();
    }

    public void designTable(JTable table) {

        //table header
        table.getTableHeader().setBackground(Color.decode("#B4EBE6"));
//        table.getTableHeader().setBackground(Color.decode("#b8e7ea"));
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 40));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        //table body
        table.setRowHeight(40);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
        table.setShowGrid(false);

        //chu can giua
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        //size column
        table.getColumnModel().getColumn(0).setMaxWidth(400);
        table.getColumnModel().getColumn(0).setPreferredWidth(40);

        table.getColumnModel().getColumn(1).setMaxWidth(500);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);

        table.getColumnModel().getColumn(2).setMaxWidth(500);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);

        table.getColumnModel().getColumn(3).setMaxWidth(500);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);

        table.getColumnModel().getColumn(5).setMaxWidth(500);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);

        table.getColumnModel().getColumn(6).setMaxWidth(500);
        table.getColumnModel().getColumn(6).setPreferredWidth(70);

        //show
        table.validate();
        table.repaint();
    }
}
