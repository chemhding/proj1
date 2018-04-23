package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import common.FileScanner;

public class Statistics extends JDialog {

    private JPanel contentPane;
    private JButton btnStatistics;
    private JTextField textField;
    private JTable tbClasses;
    private JScrollPane scrollPane;
    private JButton btnGradePercentage;

    /*
     * Create window
     */
    public Statistics(JFrame parent) {
        setTitle("Statistics");
        initialize(parent);

        // -------------------------------------listeners-------------------------------------
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        /*
         * text field listener
         * Receive the class name from user input
         * If class exists, return detailed grades average by site
         */
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tbClasses = new JTable();
                tbClasses.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Site Name",
                        "Homework Average", "Project Average", "Exam Average", "Total Score Average" }) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                });
                tbClasses.setCellSelectionEnabled(true);
                scrollPane.setViewportView(tbClasses);
                DefaultTableModel model = (DefaultTableModel) tbClasses.getModel();
                model.setRowCount(0);
                Object[] row = new Object[5];
                FileScanner fileScan = new FileScanner("src/resources/class_roster.txt",
                        "src/resources/students_grades.txt");
                ArrayList<int[]> results = fileScan.classDetailedGrades(textField.getText().trim());
                for (int i = 0; i < results.size(); i++) {
                    row[0] = results.get(i)[0];
                    row[1] = results.get(i)[1];
                    row[2] = results.get(i)[2];
                    row[3] = results.get(i)[3];
                    row[4] = results.get(i)[4];
                    model.addRow(row);
                }
            }
        });

        /*
         * Statistics button listener
         * Show the students list ranked by class
         */
        btnStatistics.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tbClasses = new JTable();
                tbClasses.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Class Name",
                        "Homework Average", "Project Average", "Exam Average", "Total Score Average" }) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                });
                tbClasses.setCellSelectionEnabled(true);
                scrollPane.setViewportView(tbClasses);
                DefaultTableModel model = (DefaultTableModel) tbClasses.getModel();
                model.setRowCount(0);
                Object[] row = new Object[5];
                FileScanner fileScan = new FileScanner("src/resources/class_roster.txt",
                        "src/resources/students_grades.txt");
                ArrayList<String[]> results = fileScan.gradesPerClass();
                for (int i = 0; i < results.size(); i++) {
                    row[0] = results.get(i)[0];
                    row[1] = results.get(i)[1];
                    row[2] = results.get(i)[2];
                    row[3] = results.get(i)[3];
                    row[4] = results.get(i)[4];
                    model.addRow(row);
                }
            }
        });

        /*
         * Button ranked by grade percentage
         */
        btnGradePercentage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tbClasses = new JTable();
                tbClasses.setModel(new DefaultTableModel(new Object[][] {},
                        new String[] { "Class Name", "A%", "B%", "C%", "E%" }) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                });
                tbClasses.setCellSelectionEnabled(true);
                scrollPane.setViewportView(tbClasses);
                DefaultTableModel model = (DefaultTableModel) tbClasses.getModel();
                model.setRowCount(0);
                Object[] row = new Object[5];
                FileScanner fileScan = new FileScanner("src/resources/class_roster.txt",
                        "src/resources/students_grades.txt");
                ArrayList<String[]> results = fileScan.gradePercentage();
                for (int i = 0; i < results.size(); i++) {
                    row[0] = results.get(i)[0];
                    row[1] = results.get(i)[1];
                    row[2] = results.get(i)[2];
                    row[3] = results.get(i)[3];
                    row[4] = results.get(i)[4];
                    model.addRow(row);
                }

            }
        });
        // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    }

    /*
     * Initialize the window
     */
    public void initialize(JFrame parent) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 540);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        btnStatistics = new JButton("Statistics");

        JLabel lblShowDetailsOf = new JLabel("Show Details of class:");

        textField = new JTextField();
        textField.setColumns(10);

        scrollPane = new JScrollPane();

        btnGradePercentage = new JButton("Grade Percentage");
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_contentPane.createSequentialGroup().addComponent(btnStatistics).addGap(18)
                                        .addComponent(btnGradePercentage)
                                        .addPreferredGap(ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
                                        .addComponent(lblShowDetailsOf).addPreferredGap(ComponentPlacement.RELATED)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(ComponentPlacement.RELATED))
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE))
                        .addGap(8)));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnStatistics)
                                .addComponent(lblShowDetailsOf)
                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnGradePercentage))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE).addGap(4)));

        tbClasses = new JTable();
        tbClasses.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Class Name", "Homework Average",
                "Project Average", "Exam Average", "Total Score Average" }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tbClasses.setCellSelectionEnabled(true);
        scrollPane.setViewportView(tbClasses);
        DefaultTableModel model = (DefaultTableModel) tbClasses.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];
        FileScanner fileScan = new FileScanner("src/resources/class_roster.txt", "src/resources/students_grades.txt");
        ArrayList<String[]> results = fileScan.gradesPerClass();
        for (int i = 0; i < results.size(); i++) {
            row[0] = results.get(i)[0];
            row[1] = results.get(i)[1];
            row[2] = results.get(i)[2];
            row[3] = results.get(i)[3];
            row[4] = results.get(i)[4];
            model.addRow(row);
        }
        contentPane.setLayout(gl_contentPane);
        setLocationRelativeTo(parent);
    }
}
