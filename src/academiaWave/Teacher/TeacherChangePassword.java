package academiaWave.Teacher;

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
import academiaWave.Teacher.TeacherChangePassword;
import academiaWave.Teacher.TeacherLogin;

public class TeacherChangePassword extends JFrame implements ActionListener{
    JLabel NewPasswlbl;
    JPasswordField NewPassw;
    JButton UpdatePass;
    public TeacherChangePassword(){
    	NewPasswlbl = new JLabel("New Password");
    	NewPasswlbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	NewPasswlbl.setBounds(10, 10, 150, 30);
            add(NewPasswlbl);
            

            NewPassw = new JPasswordField();
            NewPassw.setFont(new Font("Tahoma", Font.PLAIN, 22));
            NewPassw.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            NewPassw.setBounds(150, 10, 150, 40);
            add(NewPassw);
            NewPassw.setColumns(20);
            
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
            String password = String.valueOf(NewPassw.getPassword());
            try{
                DBConnection c1 = new DBConnection();

                String q = "update Teacher SET password = '"+ password +"'"
                        + "Where teacherID ='" + TeacherLogin.currentTeacherID + "'";

                int x = c1.s.executeUpdate(q);
                if(x == 0){
                    JOptionPane.showMessageDialog(null, " Error ");
                }else{
                    JOptionPane.showMessageDialog(null, " Password Updated Successfully ");
                    setVisible(false);
                }
            }catch(Exception e){
                e.printStackTrace();
                }
        }
    }
    public static void main(String[] args) {
        new TeacherChangePassword();
    }
}
