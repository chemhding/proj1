package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import common.*;
import common.FileScanner.SortType;

public class RecordSearch extends JFrame {

    private JPanel contentPane;
    private JTextField tfSearchBy;
    private final ButtonGroup bgSearchBy = new ButtonGroup();
    private JTable tableSearchResults;

    private FileScanner fileScan;
    private JButton btnSearchOk;
    private JRadioButton rdbtnFirstName;
    private JRadioButton rdbtnLastName;
    private JRadioButton rdbtnSid;

    /**
     * Create the frame.
     */
    public RecordSearch(JFrame parent) {

        initialize(parent);
        fileScan = new FileScanner("src/resources/class_roster.txt", "src/resources/fakedata.txt");
        // System.out.println(fileScan.getStudents().get(2).getFirstName() + " line
        // 56");
        addListener();
    }

    public void initialize(JFrame parent) {
        contentPane = new JPanel();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 520);
        setResizable(false);

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblSearchBy = new JLabel("Search by:");

        rdbtnFirstName = new JRadioButton("First name");
        rdbtnFirstName.setSelected(true);
        bgSearchBy.add(rdbtnFirstName);

        rdbtnLastName = new JRadioButton("Last name");
        bgSearchBy.add(rdbtnLastName);

        rdbtnSid = new JRadioButton("SID");
        bgSearchBy.add(rdbtnSid);

        tfSearchBy = new JTextField();
        tfSearchBy.setColumns(10);

        btnSearchOk = new JButton("OK");

        JScrollPane scpTable = new JScrollPane();
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
                .createSequentialGroup().addContainerGap()
                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(scpTable, GroupLayout.DEFAULT_SIZE, 934, Short.MAX_VALUE)
                        .addGroup(gl_contentPane.createSequentialGroup().addComponent(lblSearchBy)
                                .addPreferredGap(ComponentPlacement.UNRELATED).addComponent(rdbtnFirstName)
                                .addPreferredGap(ComponentPlacement.RELATED).addComponent(rdbtnLastName)
                                .addPreferredGap(ComponentPlacement.RELATED).addComponent(rdbtnSid))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addComponent(tfSearchBy, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
                                .addGap(18).addComponent(btnSearchOk)))
                .addContainerGap()));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_contentPane.createSequentialGroup().addContainerGap(48, Short.MAX_VALUE)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblSearchBy)
                                .addComponent(rdbtnFirstName).addComponent(rdbtnLastName).addComponent(rdbtnSid))
                        .addGap(18)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                .addComponent(tfSearchBy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSearchOk))
                        .addGap(19).addComponent(scpTable, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap()));

        tableSearchResults = new JTable();
        tableSearchResults.setCellSelectionEnabled(true);
        tableSearchResults.setModel(new DefaultTableModel(new Object[][] {},
                new String[] { "Number", "Student ID", "First Name", "Last Name", "Site Number" }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        scpTable.setViewportView(tableSearchResults);
        contentPane.setLayout(gl_contentPane);
        setLocationRelativeTo(parent);
    }

    public void addListener() {
        SearchEventHandler seh = new SearchEventHandler();
        btnSearchOk.addActionListener(seh);
        tfSearchBy.addActionListener(seh);

    }

    private class SearchEventHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String keyword = tfSearchBy.getText().trim();
            ArrayList<Student> students = new ArrayList<Student>();
            if (rdbtnFirstName.isSelected()) {
                students = fileScan.searchStudents(keyword, SortType.FirstName);
            }
            if (rdbtnLastName.isSelected()) {
                students = fileScan.searchStudents(keyword, SortType.LastName);
            }
            if (rdbtnSid.isSelected()) {
                students = fileScan.searchStudents(keyword, SortType.SID);
            }
            if (students.isEmpty())
                JOptionPane.showMessageDialog(contentPane, "No result found", "Message", 1);
            DefaultTableModel model = (DefaultTableModel) tableSearchResults.getModel();
            model.setRowCount(0);
            String[] row = new String[5];
            for (int i = 0; i < students.size(); i++) {
                row[0] = Integer.toString(i + 1);
                row[1] = students.get(i).getSID();
                row[2] = students.get(i).getFirstName();
                row[3] = students.get(i).getLastName();
                row[4] = Integer.toString(students.get(i).getSiteNum());
                model.addRow(row);
            }
        }

    }
}
