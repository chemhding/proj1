package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import common.FileScanner;
import common.Student;

public class ShowList extends JDialog {

    private JPanel contentPane;

    private int hwWeight;
    private int projWeight;
    private int examWeight;

    private final ButtonGroup bgShowList = new ButtonGroup();
    private JTable tbShowList;
    private JRadioButton rdbtnSortedListBy;
    private JRadioButton rdbtnTotalScore;
    private JRadioButton rdbtnHwAvg;
    private JRadioButton rdbtnProjAvg;
    private JScrollPane scrollPane;
    private JRadioButton rdbtnGrade;

    public ShowList(JFrame parent) {
        setTitle("Show List");
        initialize(parent);
        ScoringWeight sw = new ScoringWeight(contentPane);
        sw.setModal(true);
        sw.setVisible(true);

        hwWeight = sw.getHwWgt();
        projWeight = sw.getProjWgt();
        examWeight = sw.getExamWgt();
        rdbtnSortedListBy.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (rdbtnSortedListBy.isSelected()) {
                    FileScanner fileScan = new FileScanner("src/resources/class_roster.txt",
                            "src/resources/fakedata.txt");
                    ArrayList<Student> results = fileScan.rankByClassSiteLastName();

                    tbShowList = new JTable();
                    tbShowList.setModel(new DefaultTableModel(new Object[][] {},
                            new String[] { "Class Name", "Last Name", "First Name", "Site" }) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    });
                    tbShowList.setCellSelectionEnabled(true);
                    scrollPane.setViewportView(tbShowList);
                    DefaultTableModel model = (DefaultTableModel) tbShowList.getModel();
                    model.setRowCount(0);
                    Object[] row = new Object[4];
                    for (int i = 0; i < results.size(); i++) {
                        row[0] = results.get(i).getCourse().getClassName();
                        row[1] = results.get(i).getLastName();
                        row[2] = results.get(i).getFirstName();
                        row[3] = results.get(i).getSiteNum();
                        model.addRow(row);
                    }
                }
            }
        });

        rdbtnTotalScore.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (rdbtnTotalScore.isSelected()) {
                    FileScanner fileScan = new FileScanner("src/resources/class_roster.txt",
                            "src/resources/fakedata.txt");
                    ArrayList<Student> results = fileScan.rankByTotalScore(hwWeight, projWeight, examWeight);

                    tbShowList = new JTable();
                    tbShowList.setModel(new DefaultTableModel(new Object[][] {},
                            new String[] { "Class Name", "Last Name", "First Name", "Site", "Totoal Score" }) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    });
                    tbShowList.setCellSelectionEnabled(true);
                    scrollPane.setViewportView(tbShowList);
                    DefaultTableModel model = (DefaultTableModel) tbShowList.getModel();
                    model.setRowCount(0);
                    Object[] row = new Object[5];
                    for (int i = 0; i < results.size(); i++) {
                        row[0] = results.get(i).getCourse().getClassName();
                        row[1] = results.get(i).getLastName();
                        row[2] = results.get(i).getFirstName();
                        row[3] = results.get(i).getSiteNum();
                        row[4] = results.get(i).getTotalScore(hwWeight, projWeight, examWeight);
                        model.addRow(row);
                    }
                }
            }
        });

        rdbtnHwAvg.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (rdbtnHwAvg.isSelected()) {
                    FileScanner fileScan = new FileScanner("src/resources/class_roster.txt",
                            "src/resources/fakedata.txt");
                    ArrayList<Student> results = fileScan.rankByHwAvg();

                    tbShowList = new JTable();
                    tbShowList.setModel(new DefaultTableModel(new Object[][] {},
                            new String[] { "Class Name", "Last Name", "First Name", "Site", "Homework Average" }) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    });
                    tbShowList.setCellSelectionEnabled(true);
                    scrollPane.setViewportView(tbShowList);
                    DefaultTableModel model = (DefaultTableModel) tbShowList.getModel();
                    model.setRowCount(0);
                    Object[] row = new Object[5];
                    for (int i = 0; i < results.size(); i++) {
                        row[0] = results.get(i).getCourse().getClassName();
                        row[1] = results.get(i).getLastName();
                        row[2] = results.get(i).getFirstName();
                        row[3] = results.get(i).getSiteNum();
                        row[4] = results.get(i).getAverageHomeWork();
                        model.addRow(row);
                    }
                }
            }
        });

        rdbtnProjAvg.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (rdbtnProjAvg.isSelected()) {
                    FileScanner fileScan = new FileScanner("src/resources/class_roster.txt",
                            "src/resources/fakedata.txt");
                    ArrayList<Student> results = fileScan.rankByProjAvg();

                    tbShowList = new JTable();
                    tbShowList.setModel(new DefaultTableModel(new Object[][] {},
                            new String[] { "Class Name", "Last Name", "First Name", "Site", "Project Average" }) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    });
                    tbShowList.setCellSelectionEnabled(true);
                    scrollPane.setViewportView(tbShowList);
                    DefaultTableModel model = (DefaultTableModel) tbShowList.getModel();
                    model.setRowCount(0);
                    Object[] row = new Object[5];
                    for (int i = 0; i < results.size(); i++) {
                        row[0] = results.get(i).getCourse().getClassName();
                        row[1] = results.get(i).getLastName();
                        row[2] = results.get(i).getFirstName();
                        row[3] = results.get(i).getSiteNum();
                        row[4] = results.get(i).getAveragePorjet();
                        model.addRow(row);
                    }
                }
            }
        });

        rdbtnGrade.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (rdbtnGrade.isSelected()) {
                    FileScanner fileScan = new FileScanner("src/resources/class_roster.txt",
                            "src/resources/fakedata.txt");
                    ArrayList<Student> results = fileScan.rankByGrade(hwWeight, projWeight, examWeight);

                    tbShowList = new JTable();
                    tbShowList.setModel(new DefaultTableModel(new Object[][] {},
                            new String[] { "Class Name", "Last Name", "First Name", "Site", "Grade" }) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    });
                    tbShowList.setCellSelectionEnabled(true);
                    scrollPane.setViewportView(tbShowList);
                    DefaultTableModel model = (DefaultTableModel) tbShowList.getModel();
                    model.setRowCount(0);
                    Object[] row = new Object[5];
                    for (int i = 0; i < results.size(); i++) {
                        row[0] = results.get(i).getCourse().getClassName();
                        row[1] = results.get(i).getLastName();
                        row[2] = results.get(i).getFirstName();
                        row[3] = results.get(i).getSiteNum();
                        row[4] = results.get(i).getGrade(hwWeight, projWeight, examWeight);
                        model.addRow(row);
                    }
                }
            }
        });
    }

    public void initialize(JFrame parent) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 520);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        rdbtnSortedListBy = new JRadioButton("Sorted list by last name");
        bgShowList.add(rdbtnSortedListBy);

        rdbtnTotalScore = new JRadioButton("Total score");
        bgShowList.add(rdbtnTotalScore);

        rdbtnHwAvg = new JRadioButton("Homework averyage");
        bgShowList.add(rdbtnHwAvg);

        rdbtnProjAvg = new JRadioButton("Project average");
        bgShowList.add(rdbtnProjAvg);

        scrollPane = new JScrollPane();

        rdbtnGrade = new JRadioButton("Grade");
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane
                .setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
                                .addGroup(gl_contentPane
                                        .createParallelGroup(
                                                Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(rdbtnSortedListBy, GroupLayout.DEFAULT_SIZE, 146,
                                                        Short.MAX_VALUE)
                                                .addGap(18)
                                                .addComponent(rdbtnTotalScore, GroupLayout.DEFAULT_SIZE, 108,
                                                        Short.MAX_VALUE)
                                                .addGap(16)
                                                .addComponent(rdbtnHwAvg, GroupLayout.DEFAULT_SIZE, 153,
                                                        Short.MAX_VALUE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(rdbtnProjAvg, GroupLayout.DEFAULT_SIZE, 130,
                                                        Short.MAX_VALUE)
                                                .addGap(26).addComponent(rdbtnGrade))
                                        .addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 654,
                                                Short.MAX_VALUE))
                                .addContainerGap()));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnSortedListBy)
                                .addComponent(rdbtnTotalScore).addComponent(rdbtnGrade).addComponent(rdbtnProjAvg)
                                .addComponent(rdbtnHwAvg))
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)));

        // tbShowList = new JTable();
        // tbShowList.setModel(new DefaultTableModel(new Object[][] {}, columnName) {
        // @Override
        // public boolean isCellEditable(int row, int column) {
        // return false;
        // }
        // });
        // tbShowList.setCellSelectionEnabled(true);
        // scrollPane.setViewportView(tbShowList);
        contentPane.setLayout(gl_contentPane);
        setLocationRelativeTo(parent);

    }
}
