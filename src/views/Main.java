package views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import common.FileScanner;
import common.Student;
import common.Writer;

public class Main implements ActionListener {

    private JFrame frame;
    private JButton btnExit;
    private JButton btnGradeInput;
    private JButton btnRecordSearch;
    private JButton btnRosterCreation;
    private JButton btnChangeRecord;
    private JButton btnStatistics;
    private JButton btnShowList;

    private static Logger logger = LogManager.getLogger(Main.class);

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        logger.info("Program start");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Throwable e) {
            logger.error("Error while set look and feel. Message: " + e.getMessage());
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Main window = new Main();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Main() {
        initialize();
        addListener();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setResizable(false);

        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblInstruction = new JLabel("Grade Percentage Average Management System");
        lblInstruction.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblInstruction.setHorizontalAlignment(SwingConstants.CENTER);

        btnRosterCreation = new JButton("Roster Creation");
        btnGradeInput = new JButton("Grade Input");
        btnRecordSearch = new JButton("Record Search");
        btnShowList = new JButton("Show List");
        btnChangeRecord = new JButton("Change Record");
        btnStatistics = new JButton("Statistics");
        btnExit = new JButton("Exit");

        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(groupLayout.createSequentialGroup().addGap(94).addGroup(groupLayout
                        .createParallelGroup(
                                Alignment.TRAILING)
                        .addGroup(Alignment.LEADING,
                                groupLayout.createSequentialGroup().addGap(73)
                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                .addComponent(btnGradeInput, GroupLayout.DEFAULT_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnRecordSearch, GroupLayout.DEFAULT_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnRosterCreation, GroupLayout.DEFAULT_SIZE, 184,
                                                        Short.MAX_VALUE))
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                .addComponent(btnChangeRecord, GroupLayout.DEFAULT_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnStatistics, GroupLayout.DEFAULT_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnShowList, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
                                                        184, Short.MAX_VALUE))
                                        .addGap(67))
                        .addComponent(lblInstruction, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup().addGap(163)
                                .addComponent(btnExit, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE).addGap(145)))
                        .addGap(81)));
        groupLayout
                .setVerticalGroup(
                        groupLayout.createParallelGroup(Alignment.LEADING)
                                .addGroup(
                                        groupLayout.createSequentialGroup().addGap(66).addComponent(lblInstruction)
                                                .addGap(53)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(btnRosterCreation).addComponent(btnShowList))
                                                .addGap(49)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(btnGradeInput).addComponent(btnChangeRecord))
                                                .addGap(50)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(btnRecordSearch).addComponent(btnStatistics))
                                                .addGap(43).addComponent(btnExit)
                                                .addContainerGap(79, Short.MAX_VALUE)));
        frame.getContentPane().setLayout(groupLayout);
    }

    /*
     * initialize the listener of components
     */
    public void addListener() {
        btnRosterCreation.addActionListener(this);
        btnExit.addActionListener(this);
        btnGradeInput.addActionListener(this);
        btnRecordSearch.addActionListener(this);
        btnShowList.addActionListener(this);
        btnChangeRecord.addActionListener(this);
        btnStatistics.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnRosterCreation) {
            RosterCreation rc = new RosterCreation(frame);
            rc.setModal(true);
            rc.setVisible(true);
        } else if (ae.getSource() == btnGradeInput) {
            JFileChooser jf = new JFileChooser();
            jf.setCurrentDirectory(new File(System.getProperty("user.home")));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.text", "txt");
            jf.addChoosableFileFilter(filter);
            int result = jf.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File gradeFile = jf.getSelectedFile();
                String path = gradeFile.getAbsolutePath();
                FileScanner fileScan = new FileScanner("src/resources/class_roster.txt", path);
                Writer write = new Writer("src/resources/students_grades.txt");
                ArrayList<Student> students = fileScan.getStudents();
                for (int i = 0; i < students.size(); i++)
                    write.addString(students.get(i).toString());
                write.close();
                JOptionPane.showMessageDialog(jf, "Grade input completed!", "Congratulations", 1);
            }
        } else if (ae.getSource() == btnRecordSearch) {
            logger.info("Entering record search");
            RecordSearch rs = new RecordSearch(frame);
            rs.setModal(true);
            rs.setVisible(true);
        } else if (ae.getSource() == btnShowList) {
            logger.info("Entering show list");
            ShowList sl = new ShowList(frame);
            sl.setModal(true);
            sl.setVisible(true);

        } else if (ae.getSource() == btnChangeRecord) {
            logger.info("Entering change record");
            ChangeRecord cr = new ChangeRecord(frame);
            cr.setModal(true);
            cr.setVisible(true);
        } else if (ae.getSource() == btnStatistics) {
            logger.info("Entering statistics");
            Statistics sts = new Statistics(frame);
            sts.setModal(true);
            sts.setVisible(true);
        } else if (ae.getSource() == btnExit) {
            logger.info("Program Exits");
            System.exit(0);
        }
    }
}
