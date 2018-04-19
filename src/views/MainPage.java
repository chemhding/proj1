package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainPage extends JFrame {

    private JPanel contentPane;
    private JButton btnExit;
    private JButton btnStatistics;
    private JButton btnRosterCreation;
    private JButton btnGradeInput;
    private JButton btnRecordSearch;
    private JButton btnShowList;
    private JButton btnChangeRecord;
    private JLabel lblInstruction;

    public MainPage() {
        createView();

        btnRosterCreation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RosterCreation rc = new RosterCreation();
            }
        });

    }

    private void createView() {
        setTitle("GPAMS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 540, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        lblInstruction = new JLabel("Please select what you want to do", SwingConstants.CENTER);
        lblInstruction.setToolTipText("Select an option and click");

        btnRosterCreation = new JButton("Roster Creation");
        btnGradeInput = new JButton("Grade Input");
        btnRecordSearch = new JButton("Record Search");
        btnShowList = new JButton("Show List");
        btnChangeRecord = new JButton("Change Record");
        btnStatistics = new JButton("Statistics");
        btnExit = new JButton("Exit");

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.CENTER).addGroup(gl_contentPane
                .createSequentialGroup().addGap(198)
                .addGroup(gl_contentPane.createParallelGroup(Alignment.CENTER, false)
                        .addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnStatistics, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRosterCreation)
                        .addComponent(btnGradeInput, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRecordSearch, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnShowList, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnChangeRecord, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
                .addGap(343))
                .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup().addGap(132)
                        .addComponent(lblInstruction, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE).addGap(262)));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
                .createSequentialGroup().addGap(50)
                .addComponent(lblInstruction, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE).addGap(18)
                .addComponent(btnRosterCreation, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE).addGap(18)
                .addComponent(btnGradeInput, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE).addGap(18)
                .addComponent(btnRecordSearch, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE).addGap(18)
                .addComponent(btnShowList, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE).addGap(18)
                .addComponent(btnChangeRecord, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE).addGap(18)
                .addComponent(btnStatistics, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE).addGap(18)
                .addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE)));
        contentPane.setLayout(gl_contentPane);
        setVisible(true);
    }

    public static void main(String[] args) {
        MainPage mp = new MainPage();
    }
}
