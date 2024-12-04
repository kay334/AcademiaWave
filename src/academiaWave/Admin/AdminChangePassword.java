package academiaWave.Admin;

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
import academiaWave.Admin.AdminChangePassword;
import academiaWave.Admin.AdminLogin;

public class AdminChangePassword extends JFrame implements ActionListener{
    JLabel NewPasslbl;
    JPasswordField NewPass;
    JButton updatePassbtn;
    public AdminChangePassword(){
    	NewPasslbl = new JLabel("New Password");
    	NewPasslbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	NewPasslbl.setBounds(10, 10, 150, 30);
            add(NewPasslbl);
            

            NewPass = new JPasswordField();
            NewPass.setFont(new Font("Tahoma", Font.PLAIN, 22));
            NewPass.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            NewPass.setBounds(150, 10, 150, 40);
            add(NewPass);
            NewPass.setColumns(20);
            
            updatePassbtn = new JButton("Update");
            updatePassbtn.setBounds(80, 90, 150, 30);
            updatePassbtn.addActionListener((ActionListener) this);
            add(updatePassbtn);
            
//            ChangePassFrame.setResizable(false);
            setLayout(null);
            setSize(320,220);
            setLocation(500,320);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == updatePassbtn){
            String password = String.valueOf(NewPass.getPassword());
            try{
                DBConnection c1 = new DBConnection();

                String q = "update Admin SET password = '"+ password +"'"
                        + "Where Adminid ='" + AdminLogin.currentAdminID + "'";

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
        new AdminChangePassword();
    }
}
