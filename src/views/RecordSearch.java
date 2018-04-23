package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import common.FileScanner;
import common.FileScanner.SortType;
import common.Student;

public class RecordSearch extends JDialog {

    private JPanel contentPane;
    private JTextField tfSearchBy;
    private final ButtonGroup bgSearchBy = new ButtonGroup();
    private JTable tableSearchResults;

    private FileScanner fileScan;
    private JButton btnSearchOk;
    private JRadioButton rdbtnFirstName;
    private JRadioButton rdbtnLastName;
    private JRadioButton rdbtnSid;

    private ArrayList<Student> studentsArrayList = new ArrayList<Student>();
    private int currentPosition;
    private JLabel lblByHowMany;
    private JTextField tfHowMany;
    private final ButtonGroup bgTravelList = new ButtonGroup();
    private JRadioButton rdbtnForward;
    private JRadioButton rdbtnBackward;
    private JButton btnTravelOk;

    /**
     * Create the frame.
     */
    public RecordSearch(JFrame parent) {
        setTitle("Record Search");
        initialize(parent);
        fileScan = new FileScanner("src/resources/class_roster.txt", "src/resources/students_grades.txt");
        studentsArrayList = fileScan.SortedStudentsArrayList();
        currentPosition = 0;
        addListener();
    }

    /*
     * Initialize the widow
     */
    public void initialize(JFrame parent) {
        contentPane = new JPanel();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 520);
        setResizable(false);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblSearchBy = new JLabel("Search by:");
        rdbtnFirstName = new JRadioButton("First name");
        rdbtnLastName = new JRadioButton("Last name");
        rdbtnSid = new JRadioButton("SID");

        bgSearchBy.add(rdbtnLastName);
        bgSearchBy.add(rdbtnFirstName);
        bgSearchBy.add(rdbtnSid);
        rdbtnFirstName.setSelected(true);

        tfSearchBy = new JTextField();
        tfSearchBy.setColumns(10);
        btnSearchOk = new JButton("OK");

        JScrollPane scpTable = new JScrollPane();
        JLabel lblTraverlingList = new JLabel("Traverling list:");

        rdbtnForward = new JRadioButton("Forward");
        rdbtnBackward = new JRadioButton("Backward");

        bgTravelList.add(rdbtnForward);
        bgTravelList.add(rdbtnBackward);
        rdbtnForward.setSelected(true);

        lblByHowMany = new JLabel("by how many?");
        tfHowMany = new JTextField();
        tfHowMany.setColumns(5);

        btnTravelOk = new JButton("OK");
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
                .createSequentialGroup().addContainerGap()
                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(scpTable, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
                                .createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_contentPane.createSequentialGroup().addComponent(lblSearchBy)
                                        .addPreferredGap(ComponentPlacement.UNRELATED).addComponent(rdbtnFirstName)
                                        .addPreferredGap(ComponentPlacement.RELATED).addComponent(rdbtnLastName)
                                        .addPreferredGap(ComponentPlacement.RELATED).addComponent(rdbtnSid))
                                .addGroup(gl_contentPane.createSequentialGroup()
                                        .addComponent(tfSearchBy, GroupLayout.PREFERRED_SIZE, 188,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addGap(18).addComponent(btnSearchOk)))
                                .addPreferredGap(ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED).addComponent(lblByHowMany)
                                                .addPreferredGap(ComponentPlacement.UNRELATED).addComponent(tfHowMany)
                                                .addGap(18).addComponent(btnTravelOk))
                                        .addGroup(gl_contentPane.createSequentialGroup().addComponent(lblTraverlingList)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(rdbtnForward)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(rdbtnBackward)))))
                .addContainerGap()));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_contentPane.createSequentialGroup().addContainerGap(18, Short.MAX_VALUE)
                        .addGroup(gl_contentPane
                                .createParallelGroup(Alignment.BASELINE).addComponent(lblSearchBy)
                                .addComponent(rdbtnFirstName).addComponent(rdbtnLastName).addComponent(rdbtnSid)
                                .addComponent(lblTraverlingList).addComponent(rdbtnForward).addComponent(rdbtnBackward))
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
                                .createSequentialGroup().addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(tfSearchBy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSearchOk)))
                                .addGroup(gl_contentPane.createSequentialGroup().addGap(18).addGroup(gl_contentPane
                                        .createParallelGroup(Alignment.BASELINE).addComponent(lblByHowMany)
                                        .addComponent(tfHowMany, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnTravelOk))))
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

    /*
     * Add event listener
     */
    public void addListener() {
        SearchEventHandler seh = new SearchEventHandler();
        TravelEventHandler teh = new TravelEventHandler();
        btnSearchOk.addActionListener(seh);
        tfSearchBy.addActionListener(seh);
        btnTravelOk.addActionListener(teh);

    }

    // -------------------------------------listeners-------------------------------------
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    /*
     * Search event listener
     * when one of radio button is selected
     * do the search
     */
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

    /*
     * Travel event
     * Generate an array list
     * travel forward or backward
     * stop when it hits the end
     * default position is the first record of student file
     */
    private class TravelEventHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int howMuch = Integer.valueOf(tfHowMany.getText());

                DefaultTableModel model = (DefaultTableModel) tableSearchResults.getModel();
                model.setRowCount(0);
                String[] row = new String[5];
                int i = 0;
                if (rdbtnForward.isSelected() && howMuch >= 0 || rdbtnBackward.isSelected() && howMuch < 0) {
                    if (currentPosition + howMuch >= studentsArrayList.size())
                        JOptionPane.showMessageDialog(contentPane, "Cannot travel that far", "Message", 1);
                    while (i <= Math.abs(howMuch) && currentPosition < studentsArrayList.size()) {
                        row[0] = Integer.toString(i + 1);
                        row[1] = studentsArrayList.get(currentPosition).getSID();
                        row[2] = studentsArrayList.get(currentPosition).getFirstName();
                        row[3] = studentsArrayList.get(currentPosition).getLastName();
                        row[4] = Integer.toString(studentsArrayList.get(currentPosition).getSiteNum());
                        model.addRow(row);
                        i++;
                        currentPosition++;
                    }
                    currentPosition--;

                } else {
                    if (currentPosition - howMuch < 0)
                        JOptionPane.showMessageDialog(contentPane, "Cannot travel that far", "Message", 1);
                    while (i <= Math.abs(howMuch) && currentPosition >= 0) {
                        row[0] = Integer.toString(i + 1);
                        row[1] = studentsArrayList.get(currentPosition).getSID();
                        row[2] = studentsArrayList.get(currentPosition).getFirstName();
                        row[3] = studentsArrayList.get(currentPosition).getLastName();
                        row[4] = Integer.toString(studentsArrayList.get(currentPosition).getSiteNum());
                        model.addRow(row);
                        i++;
                        currentPosition--;
                    }
                    currentPosition++;

                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(contentPane, "Please enter a number.", "Message", 0);
            }
        }
    }
    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
}
