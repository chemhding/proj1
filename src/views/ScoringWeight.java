package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import com.sun.glass.events.WindowEvent;
//import com.sun.org.apache.xml.internal.security.utils.RFC2253Parser;

//import sun.awt.image.codec.JPEGParam;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class ScoringWeight extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JButton okButton;
    private JTextField tfHwWeight;
    private JTextField tfProjWeight;
    private JTextField tfExamWeight;
    private JLabel lblHomework;
    private JLabel lblProject;
    private JLabel lblExam;

    private int hwWgt = 0;
    private int projWgt = 0;
    private int examWgt = 0;

    /*
     * create window
     */
    public ScoringWeight(JPanel parent) {
        initialize(parent);
        ScoringWeightHanlder swh = new ScoringWeightHanlder();
        okButton.addActionListener(swh);
        tfHwWeight.addActionListener(swh);
        tfProjWeight.addActionListener(swh);
        tfExamWeight.addActionListener(swh);
    }

    /*
     * initialize the window
     */
    public void initialize(JPanel parent) {
        setTitle("Set scoring weight");
        setSize(300, 200);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        lblHomework = new JLabel("Homework:");
        lblProject = new JLabel("Project:");
        lblExam = new JLabel("Exam:");
        tfHwWeight = new JTextField();
        tfHwWeight.setText("20");
        tfHwWeight.setColumns(10);
        tfProjWeight = new JTextField();
        tfProjWeight.setText("20");
        tfProjWeight.setColumns(10);
        tfExamWeight = new JTextField();
        tfExamWeight.setText("60");
        tfExamWeight.setColumns(10);
        GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
        gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPanel.createSequentialGroup().addGap(77)
                        .addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addComponent(lblHomework)
                                .addComponent(lblProject).addComponent(lblExam))
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                                .addComponent(tfHwWeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfProjWeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfExamWeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(57, Short.MAX_VALUE)));
        gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
                .createSequentialGroup().addGap(26)
                .addGroup(gl_contentPanel
                        .createParallelGroup(Alignment.BASELINE).addComponent(lblHomework).addComponent(tfHwWeight,
                                GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18)
                .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblProject).addComponent(
                        tfProjWeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18)
                .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblExam).addComponent(
                        tfExamWeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE)));
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
        }
        setLocationRelativeTo(parent);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    // get homework scoring weight
    public int getHwWgt() {
        return hwWgt;
    }

    // get project scoring weight
    public int getProjWgt() {
        return projWgt;
    }

    // get exam scoring weight
    public int getExamWgt() {
        return examWgt;
    }

    /* get scoring weight from user
     * show wrong message if total is not 100
     */
    private class ScoringWeightHanlder implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int hwWeight = Integer.valueOf(tfHwWeight.getText().trim());
                int projWeight = Integer.valueOf(tfProjWeight.getText().trim());
                int examWeight = Integer.valueOf(tfExamWeight.getText().trim());
                if (hwWeight + projWeight + examWeight != 100)
                    JOptionPane.showMessageDialog(contentPanel, "Please make total weight equals to 100", "Message", 0);
                else {
                    hwWgt = hwWeight;
                    projWgt = projWeight;
                    examWgt = examWeight;
                    dispose();
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(contentPanel, "Please enter a number.", "Message", 0);
            }
        }

    }
}
