package views;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Main implements ActionListener {

    private JFrame frame;
    private JButton btnExit;
    private JButton btnGradeInput;
    private JButton btnRecordSearch;
    private JButton btnRosterCreation;
    private JButton btnNewButton;
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

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width / 2, screenSize.height / 2);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblInstruction = new JLabel("Please select what you want to do");
        lblInstruction.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblInstruction.setHorizontalAlignment(SwingConstants.CENTER);

        btnRosterCreation = new JButton("Roster Creation");
        btnGradeInput = new JButton("Grade Input");
        btnRecordSearch = new JButton("Record Search");
        btnShowList = new JButton("Show List");
        btnNewButton = new JButton("Change Record");
        btnStatistics = new JButton("Statistics");
        btnExit = new JButton("Exit");

        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout
                .setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup().addGap(342).addComponent(lblInstruction)
                                .addContainerGap(344, Short.MAX_VALUE))
                        .addGroup(Alignment.LEADING,
                                groupLayout.createSequentialGroup().addGap(383)
                                        .addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 184,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(387, Short.MAX_VALUE))
                        .addGroup(groupLayout.createSequentialGroup().addGap(210)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(btnGradeInput, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE)
                                        .addComponent(btnRecordSearch, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnRosterCreation, GroupLayout.PREFERRED_SIZE, 184,
                                                GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE)
                                        .addComponent(btnStatistics, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE)
                                        .addComponent(btnShowList, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 184,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGap(208)));
        groupLayout
                .setVerticalGroup(
                        groupLayout.createParallelGroup(Alignment.LEADING)
                                .addGroup(
                                        groupLayout.createSequentialGroup().addGap(33).addComponent(lblInstruction)
                                                .addGap(83)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(btnRosterCreation).addComponent(btnShowList))
                                                .addGap(49)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(btnGradeInput).addComponent(btnNewButton))
                                                .addGap(50)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(btnRecordSearch).addComponent(btnStatistics))
                                                .addGap(82).addComponent(btnExit)
                                                .addContainerGap(89, Short.MAX_VALUE)));
        frame.getContentPane().setLayout(groupLayout);
    }

    public void addListener() {
        btnRosterCreation.addActionListener(this);
        btnExit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnRosterCreation) {
            RosterCreation rc = new RosterCreation(frame);
            rc.setModal(true);
            rc.setVisible(true);
        } else if (ae.getSource() == btnExit) {
            System.exit(0);
        }
    }
}
