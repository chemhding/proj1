package views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import common.Course;
import common.FileScanner;
import common.Student;
import common.Writer;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ChangeRecord extends JDialog {

    private JPanel contentPane;
    private JButton btnAddRecord;
    private JButton btnRemoveRecord;
    private JButton btnChangeRecord;
    private JButton btnClose;

    public ChangeRecord(JFrame parent) {
        setTitle("Change Record");
        initialize(parent);
        btnAddRecord.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String input1 = JOptionPane.showInputDialog(contentPane,
                        "Please enter first name, last name, and SID, use white spacce to seperate", "Input", 1);
                String input2 = JOptionPane.showInputDialog(contentPane,
                        "Please enter class name, homework grades, project grads, exam grads, use white space to seperate",
                        "Input", 1);
                Writer write = new Writer("src/resources/students_grades.txt");
                if (input1 != null && input2 != null) {
                    write.addString(input1 + " " + input2);
                    write.close();
                }
            }
        });

        btnRemoveRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(contentPane, "Enter SID to remove", "Input", 1).trim();
                FileScanner fileScan = new FileScanner("src/resources/class_roster.txt",
                        "src/resources/students_grades.txt");
                ArrayList<Student> students = fileScan.getStudents();
                Writer write = new Writer("src/resources/temp.txt");
                for (int i = 0; i < students.size(); i++) {
                    if (!students.get(i).getSID().equals(input))
                        write.addString(students.get(i).toString());
                }
                write.close();

                File old = new File("src/resources/students_grades.txt");
                File temp = new File("src/resources/temp.txt");
                temp.renameTo(old);
            }
        });

        btnChangeRecord.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(contentPane, "Enter SID to change", "Input", 1).trim();
                FileScanner fileScan = new FileScanner("src/resources/class_roster.txt",
                        "src/resources/students_grades.txt");
                ArrayList<Student> students = fileScan.getStudents();
                ArrayList<Course> courses = fileScan.getCourses();
                boolean sidFound = false;
                int i = 0;
                while (i < students.size() && !sidFound) {
                    if (students.get(i).getSID().equals(input))
                        sidFound = true;
                    i++;
                }
                if (!sidFound)
                    JOptionPane.showMessageDialog(contentPane, "SID not found", "Message", 1);
                else {
                    i--;
                    String firstName = JOptionPane.showInputDialog(contentPane, "Enter Frist Name", "Input", 1).trim();
                    String lastName = JOptionPane.showInputDialog(contentPane, "Enter Last Name", "Input", 1).trim();
                    String SID = JOptionPane.showInputDialog(contentPane, "Enter SID", "Input", 1).trim();
                    int siteNum = Integer
                            .valueOf(JOptionPane.showInputDialog(contentPane, "Enter site number", "Input", 1).trim());
                    String className = JOptionPane.showInputDialog(contentPane, "Enter class name", "Input", 1).trim();
                    boolean classFound = false;
                    int j = 0;

                    System.out.println(className + "111");
                    System.out.println(courses.size());
                    System.out.println(courses.get(7));
                    System.out.println(courses.get(7).getClassName().equalsIgnoreCase(className));
                    do {
                        while (j < courses.size() && !classFound) {
                            if (courses.get(j).getClassName().equalsIgnoreCase(className))
                                classFound = true;
                            j++;
                        }
                        System.out.println(classFound);
                        if (!classFound) {
                            className = JOptionPane.showInputDialog(contentPane, "Enter class name", "Input", 1).trim();
                            j = 0;
                        }
                    } while (!classFound);
                    j--;
                    int[] hwGrades = new int[courses.get(j).getHomework()];
                    int[] projGrades = new int[courses.get(j).getProjects()];
                    int[] examGrades = new int[courses.get(j).getExams()];
                    for (int k = 0; k < courses.get(j).getHomework(); k++)
                        hwGrades[k] = Integer
                                .valueOf(JOptionPane.showInputDialog(contentPane, "Enter homework grade", "Input", 1));
                    for (int p = 0; p < courses.get(j).getProjects(); p++)
                        projGrades[p] = Integer
                                .valueOf(JOptionPane.showInputDialog(contentPane, "Enter project grade", "Input", 1));
                    for (int q = 0; q < courses.get(j).getExams(); q++)
                        examGrades[q] = Integer
                                .valueOf(JOptionPane.showInputDialog(contentPane, "Enter exam grade", "Input", 1));
                    Student std = new Student(firstName, lastName, SID, siteNum, courses.get(j));
                    std.setHwGrades(hwGrades);
                    std.setProjGrades(projGrades);
                    std.setExamGrades(examGrades);
                    students.set(i, std);

                    Writer write = new Writer("src/resources/temp.txt");
                    for (int h = 0; h < students.size(); h++) {
                        write.addString(students.get(h).toString());
                    }
                    write.close();

                    File old = new File("src/resources/students_grades.txt");
                    File temp = new File("src/resources/temp.txt");
                    temp.renameTo(old);
                }
            }
        });

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void initialize(JFrame parent) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        btnAddRecord = new JButton("Add record");

        btnRemoveRecord = new JButton("Remove record");

        btnChangeRecord = new JButton("Change record");

        btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup().addGap(134)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(btnClose, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnChangeRecord, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addComponent(btnRemoveRecord, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addComponent(btnAddRecord, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE))
                        .addContainerGap(145, Short.MAX_VALUE)));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup().addGap(52).addComponent(btnAddRecord).addGap(33)
                        .addComponent(btnRemoveRecord).addGap(33).addComponent(btnChangeRecord).addGap(29)
                        .addComponent(btnClose).addContainerGap(22, Short.MAX_VALUE)));
        contentPane.setLayout(gl_contentPane);
        setLocationRelativeTo(parent);
    }
}
