package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;

import common.Course;
import common.Writer;

public class RosterCreation extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JLabel lblClassName;
    private JTextField tfClassName;
    private JLabel lblSelectNum;
    private JLabel lblHomework;
    private JLabel lblProjects;
    private JLabel lblSites;
    private JLabel lblExams;
    private JComboBox cbHomework;
    private JComboBox cbProjects;
    private JComboBox cbExams;
    private JComboBox cbSites;
    private JButton okButton;
    private JButton cancelButton;

    /**
     * Create the dialog.
     */
    public RosterCreation(JFrame parent) {
        initialize(parent);
        addListener();
    }

    /*
     * create event listener
     */
    public void addListener() {

        // -------------------------------------listeners-------------------------------------
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        /*
         * cancel button
         * close on click
         */
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        /*
         * okay button
         * promotes user to input course information
         */
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = "src/resources/class_roster.txt";
                Writer writer = new Writer(path);
                String className = null;
                try {
                    className = tfClassName.getText(0, 5).toUpperCase();
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
                if (className == null)
                    JOptionPane.showMessageDialog(contentPanel, "Please enter class name", "Wrong", 0);
                else {
                    int homeworkNumber = Integer.valueOf((String) cbHomework.getSelectedItem());
                    int projectsNumber = Integer.valueOf((String) cbProjects.getSelectedItem());
                    int examNumber = Integer.valueOf((String) cbExams.getSelectedItem());
                    int sitesNumber = Integer.valueOf((String) cbSites.getSelectedItem());
                    Course course = new Course(className, homeworkNumber, projectsNumber, examNumber, sitesNumber);

                    int[] studentsNumberPerSite = new int[sitesNumber];
                    for (int i = 0; i < sitesNumber; i++) {
                        int j = i + 1;
                        studentsNumberPerSite[i] = Integer
                                .parseInt(JOptionPane.showInputDialog("Please enter Students number of site " + j));
                    }
                    course.setStudents(studentsNumberPerSite);
                    writer.addString(course.toString());
                    writer.close();

                    dispose();
                }
            }
        });
        // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    }

    /*
     * Initialize the window
     */
    private void initialize(JFrame parent) {
        setTitle("Create roster");
        setSize(600, 520);
        setResizable(false);

        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel);

        lblClassName = new JLabel("Enter class name: (Max 5 chars)");
        lblClassName.setToolTipText("For example: CS401");
        tfClassName = new JTextField();

        lblSelectNum = new JLabel("Select numbers of each:");

        lblHomework = new JLabel("Homework:");
        lblProjects = new JLabel("Projects:");
        lblExams = new JLabel("Exams:");
        lblSites = new JLabel("Sites:");

        cbHomework = new JComboBox<String>();
        cbHomework.setModel(new DefaultComboBoxModel(
                new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" }));
        cbHomework.setSelectedIndex(3);

        cbProjects = new JComboBox();
        cbProjects.setModel(new DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5" }));
        cbProjects.setSelectedIndex(1);

        cbExams = new JComboBox();
        cbExams.setModel(new DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5" }));
        cbExams.setSelectedIndex(2);

        cbSites = new JComboBox();
        cbSites.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
        cbSites.setSelectedIndex(2);

        GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
        gl_contentPanel
                .setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPanel.createSequentialGroup().addGap(26)
                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(
                                                gl_contentPanel
                                                        .createSequentialGroup().addComponent(lblClassName)
                                                        .addPreferredGap(ComponentPlacement.UNRELATED).addComponent(
                                                                tfClassName, GroupLayout.PREFERRED_SIZE, 101,
                                                                GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lblSelectNum)
                                        .addGroup(gl_contentPanel.createSequentialGroup()
                                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
                                                        .addComponent(lblProjects).addComponent(lblHomework))
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(cbProjects, 0, GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE)
                                                        .addComponent(cbHomework, 0, 66, Short.MAX_VALUE))
                                                .addGap(104)
                                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
                                                        .addComponent(lblExams).addComponent(lblSites))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(cbSites, 0, GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE)
                                                        .addComponent(cbExams, 0, 66, Short.MAX_VALUE))))
                                .addContainerGap(141, Short.MAX_VALUE)));
        gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
                        .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                                .addComponent(tfClassName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblClassName))
                        .addGap(22).addComponent(lblSelectNum).addGap(18)
                        .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addComponent(lblHomework)
                                .addComponent(cbHomework, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblExams).addComponent(cbExams, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(46)
                        .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addComponent(lblSites)
                                .addComponent(lblProjects)
                                .addComponent(cbProjects, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbSites, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(63, Short.MAX_VALUE)));
        contentPanel.setLayout(gl_contentPanel);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                okButton = new JButton("OK");
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }

        setLocationRelativeTo(parent);
    }
}
