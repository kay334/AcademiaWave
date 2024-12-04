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
import javax.swing.JPasswordField;

import academiaWave.DBConnection;
import academiaWave.Student.StudentChangePassword;
import academiaWave.Student.StudentLogin;

public class StudentChangePassword extends JFrame implements ActionListener{
    JLabel newPasswlbl;
    JPasswordField NewPass;
    JButton UpdatePass;
    public StudentChangePassword(){
    	newPasswlbl = new JLabel("New Password");
    	newPasswlbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	newPasswlbl.setBounds(10, 10, 150, 30);
            add(newPasswlbl);
            

            NewPass = new JPasswordField();
            NewPass.setFont(new Font("Tahoma", Font.PLAIN, 22));
            NewPass.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            NewPass.setBounds(150, 10, 150, 40);
            add(NewPass);
            NewPass.setColumns(20);
            
            UpdatePass = new JButton("Update");
            UpdatePass.setBounds(80, 90, 150, 30);
            UpdatePass.addActionListener((ActionListener) this);
            add(UpdatePass);
            
//            ChangePassFrame.setResizable(false);
            setLayout(null);
            setSize(320,220);
            setLocation(500,320);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == UpdatePass){
            String password = String.valueOf(NewPass.getPassword());
            try{
                DBConnection c1 = new DBConnection();

                String q = "update Student SET password = '"+ password +"'"
                        + "Where stdID ='" + StudentLogin.currentStudentID + "'";

                int x = c1.s.executeUpdate(q);
                if(x == 0){
                    JOptionPane.showMessageDialog(null, "Got some error");
                }else{
                    JOptionPane.showMessageDialog(null, "Your Password Updated Successfully");
                    setVisible(false);
                }
            }catch(Exception e){
                e.printStackTrace();
                }
        }
    }
    public static void main(String[] args) {
        new StudentChangePassword();
    }
}
