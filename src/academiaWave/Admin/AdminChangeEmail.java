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
import javax.swing.JTextField;

import academiaWave.DBConnection;
import academiaWave.Admin.AdminChangeEmail;
import academiaWave.Admin.AdminLogin;

public class AdminChangeEmail extends JFrame implements ActionListener{
    JLabel New_Emaillbl;
    JTextField New_Email;
    JButton UpdateEmail;
    public AdminChangeEmail(){
    	New_Emaillbl = new JLabel("New Email");
    	New_Emaillbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	New_Emaillbl.setBounds(10, 10, 150, 30);
            add(New_Emaillbl);
            

            New_Email = new JTextField();
            New_Email.setFont(new Font("Tahoma", Font.PLAIN, 22));
            New_Email.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            New_Email.setBounds(150, 10, 150, 40);
            add(New_Email);
            New_Email.setColumns(20);
            
            UpdateEmail = new JButton("Update");
            UpdateEmail.setBounds(80, 90, 150, 30);
            UpdateEmail.addActionListener((ActionListener) this);
            add(UpdateEmail);
            
//            ChangePassFrame.setResizable(false);
            setLayout(null);
            setSize(320,220);
            setLocation(500,320);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == UpdateEmail){
            String EmailID = New_Email.getText();
            try{
                DBConnection c1 = new DBConnection();

                String q = "update Admin SET Email_ID = '"+ EmailID +"'"
                        + "Where Adminid ='" + AdminLogin.currentAdminID + "'";

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
        new AdminChangeEmail();
    }
}