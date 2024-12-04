package academiaWave.Admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import academiaWave.DBConnection;
import academiaWave.Admin.DeleteSubject;

public class DeleteSubject extends JFrame implements ActionListener{
    JLabel title, subjectCbLbl;
    JComboBox SubjectsComboBox, CoursesComboBox;
    JButton DeleteButton;
    JPanel middlePanel;
    public DeleteSubject(){
        super("Delete Subject");
        setLayout(new BorderLayout());
        
        title = new JLabel("Delete Subject", JLabel.CENTER);
        title.setFont(title.getFont().deriveFont (22.0f));
        title.setBackground(Color.LIGHT_GRAY);
        title.setForeground(Color.BLACK);
        title.setOpaque(true);
        add(title, BorderLayout.NORTH);
        
        middlePanel = new JPanel();
        middlePanel.setLayout(null);
        add(middlePanel, BorderLayout.CENTER);
        
        subjectCbLbl = new JLabel("Select Subject");
        subjectCbLbl.setFont(new Font(Font.SERIF,Font.BOLD, 16));
        subjectCbLbl.setBounds(80, 30, 120, 28);
        subjectCbLbl.setHorizontalAlignment(JLabel.CENTER);
        middlePanel.add(subjectCbLbl);

        SubjectsComboBox = new JComboBox(this.getSubjects());
        SubjectsComboBox.setSelectedIndex(-1);
        SubjectsComboBox.setBounds(200, 30, 140, 28);
        SubjectsComboBox.addActionListener(this);
        middlePanel.add(SubjectsComboBox);
        
        DeleteButton = new JButton("Delete");
        DeleteButton.setFont(new Font(Font.SERIF,Font.BOLD, 15));
        DeleteButton.setHorizontalAlignment(JButton.CENTER);
        DeleteButton.addActionListener(this);
        add(DeleteButton, BorderLayout.SOUTH);
        

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(500,200);
        setLocation(420,320);
        setVisible(true);
    }
    private String[] getSubjects(){
        String[] subjectsData = null;
        try{
            DBConnection c1 = new DBConnection();

            String q = "select * from Subjects";

            ResultSet rs = c1.s.executeQuery(q);
            int rowCount = 0;
            while(rs.next())
                rowCount++;
            subjectsData = new String[rowCount];
            rs.beforeFirst();
            int i=0;
            while(rs.next()){
                subjectsData[i] = rs.getString("Name");
                i++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return subjectsData;
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == DeleteButton){
            Object selected = SubjectsComboBox.getSelectedItem();
            String subjectName = selected.toString();
            int input = JOptionPane.showConfirmDialog(null, "Deleting Subject will delete all Corresponding Courses to this subject"
                    + ", do you want to proceed?", "Select an Option...",
            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            
            if(input == 0){
                DBConnection c1 = new DBConnection();
                try{
                    String q = "Delete From Subjects Where Name ='" + subjectName + "'";
                    int x = c1.s.executeUpdate(q);
                    if(x == 0){
                        JOptionPane.showMessageDialog(null, " Error ");
                    }else{
                        JOptionPane.showMessageDialog(null, " Deleted Successfully ");
                        dispose();
                    }
            }catch(Exception e){
                e.printStackTrace();
                }
            finally{
                c1.Close();
                }
            }
        }
        }
        public static void main(String[] args) {
            new DeleteSubject();
        }

}