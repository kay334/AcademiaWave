package academiaWave.Student;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import academiaWave.DBConnection;
import academiaWave.Student.StudentChangeEmail;
import academiaWave.Student.StudentLogin;

public class StudentChangeEmail extends JFrame implements ActionListener{
    JLabel NewEmaillbl;
    JTextField New_Email;
    JButton Update_Emailbtn;
    public StudentChangeEmail(){
    	NewEmaillbl = new JLabel("New Email");
    	NewEmaillbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	NewEmaillbl.setBounds(10, 10, 150, 30);
            add(NewEmaillbl);
            

            New_Email = new JTextField();
            New_Email.setFont(new Font("Tahoma", Font.PLAIN, 22));
            New_Email.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            New_Email.setBounds(150, 10, 150, 40);
            add(New_Email);
            New_Email.setColumns(20);
            
            Update_Emailbtn = new JButton("Update");
            Update_Emailbtn.setBounds(80, 90, 150, 30);
            Update_Emailbtn.addActionListener((ActionListener) this);
            add(Update_Emailbtn);
            
//            ChangePassFrame.setResizable(false);
            setLayout(null);
            setSize(320,220);
            setLocation(500,320);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == Update_Emailbtn){
            String EmailID = New_Email.getText();
            try{
                DBConnection c1 = new DBConnection();

                String q = "update Student SET Email_ID = '"+ EmailID +"'"
                        + "Where stdID ='" + StudentLogin.currentStudentID + "'";

                int x = c1.s.executeUpdate(q);
                if(x == 0){
                    JOptionPane.showMessageDialog(null, " Error ");
                }else{
                    JOptionPane.showMessageDialog(null, " Email Updated Successfully ");
                    setVisible(false);
                }
            }catch(Exception e){
                e.printStackTrace();
                }
        }
    }
    public static void main(String[] args) {
        new StudentChangeEmail();
    }
}