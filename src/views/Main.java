package views;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileNameExtensionFilter;

import common.FileScanner;
import common.Student;
import common.Writer;

public class Main implements ActionListener {

    private JFrame frmGpams;
    private JButton btnExit;
    private JButton btnGradeInput;
    private JButton btnRecordSearch;
    private JButton btnRosterCreation;
    private JButton btnChangeRecord;
    private JButton btnStatistics;
    private JButton btnShowList;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Main window = new Main();
                    window.frmGpams.setVisible(true);
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
        frmGpams = new JFrame();
        frmGpams.setTitle("GPAMS");
        frmGpams.setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frmGpams.setSize(screenSize.width / 2, screenSize.height / 2);
        frmGpams.setLocationRelativeTo(null);
        frmGpams.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblInstruction = new JLabel("Grade Point Average Management System");
        lblInstruction.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblInstruction.setHorizontalAlignment(SwingConstants.CENTER);

        btnRosterCreation = new JButton("Roster Creation");
        btnGradeInput = new JButton("Grade Input");
        btnRecordSearch = new JButton("Record Search");
        btnShowList = new JButton("Show List");
        btnChangeRecord = new JButton("Change Record");
        btnStatistics = new JButton("Statistics");
        btnExit = new JButton("Exit");

        GroupLayout groupLayout = new GroupLayout(frmGpams.getContentPane());
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup().addGap(383)
                        .addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(387, Short.MAX_VALUE))
                .addGroup(groupLayout.createSequentialGroup().addGap(210)
                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(btnGradeInput, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addComponent(btnRecordSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addComponent(btnRosterCreation, GroupLayout.PREFERRED_SIZE, 184,
                                        GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(btnChangeRecord, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addComponent(btnStatistics, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addComponent(btnShowList, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 184,
                                        GroupLayout.PREFERRED_SIZE))
                        .addGap(208))
                .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup().addGap(311)
                        .addComponent(lblInstruction).addContainerGap(307, Short.MAX_VALUE)));
        groupLayout
                .setVerticalGroup(
                        groupLayout.createParallelGroup(Alignment.LEADING)
                                .addGroup(
                                        groupLayout.createSequentialGroup().addGap(37).addComponent(lblInstruction)
                                                .addGap(79)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(btnRosterCreation).addComponent(btnShowList))
                                                .addGap(49)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(btnGradeInput).addComponent(btnChangeRecord))
                                                .addGap(50)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(btnRecordSearch).addComponent(btnStatistics))
                                                .addGap(82).addComponent(btnExit)
                                                .addContainerGap(97, Short.MAX_VALUE)));
        frmGpams.getContentPane().setLayout(groupLayout);
    }

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
            RosterCreation rc = new RosterCreation(frmGpams);
            rc.setModal(true);
            rc.setVisible(true);
        } else if (ae.getSource() == btnGradeInput) {
            JFileChooser jf = new JFileChooser();
            jf.setCurrentDirectory(new File(System.getProperty("user.home")));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.text", "txt");
            jf.addChoosableFileFilter(filter);
            int result = jf.showOpenDialog(frmGpams);
            if (result == JFileChooser.APPROVE_OPTION) {
                File gradeFile = jf.getSelectedFile();
                String path = gradeFile.getAbsolutePath();
                // System.out.println(path);
                FileScanner fileScan = new FileScanner("src/resources/class_roster.txt", path);
                Writer write = new Writer("src/resources/students_grades.txt");
                ArrayList<Student> students = fileScan.getStudents();
                for (int i = 0; i < students.size(); i++)
                    write.addString(students.get(i).toString());
                write.close();
                // try {
                // Scanner scan = new Scanner(gradeFile);
                // while (scan.hasNextLine()) {
                // String line = scan.nextLine();
                // Writer writer = new Writer("src/resources/students_grades.txt");
                // writer.addString(line);
                // }
                JOptionPane.showMessageDialog(jf, "Grade input completed!", "Congratulations", 1);
                // } catch (FileNotFoundException e) {
                // // TODO Auto-generated catch block
                // e.printStackTrace();
                // }

            }
        } else if (ae.getSource() == btnRecordSearch) {
            RecordSearch rs = new RecordSearch(frmGpams);
            rs.setModal(true);
            rs.setVisible(true);
        } else if (ae.getSource() == btnShowList) {
            ShowList sl = new ShowList(frmGpams);
            sl.setModal(true);
            sl.setVisible(true);

        } else if (ae.getSource() == btnChangeRecord) {
            ChangeRecord cr = new ChangeRecord(frmGpams);
            cr.setModal(true);
            cr.setVisible(true);
        } else if (ae.getSource() == btnStatistics) {
            Statistics sts = new Statistics(frmGpams);
            sts.setModal(true);
            sts.setVisible(true);
        } else if (ae.getSource() == btnExit) {
            System.exit(0);
        }
    }
}
