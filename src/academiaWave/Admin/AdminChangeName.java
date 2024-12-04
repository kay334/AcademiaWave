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
import academiaWave.Admin.AdminChangeName;
import academiaWave.Admin.AdminLogin;

public class AdminChangeName extends JFrame implements ActionListener{
    JLabel NewFirst_Namelbl, NewLast_Namelbl;
    JTextField NewFirst_Name,  NewLastName;
    JButton UpdateName;
    public AdminChangeName(){
    	NewFirst_Namelbl = new JLabel("First Name");
    	NewFirst_Namelbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	NewFirst_Namelbl.setBounds(10, 10, 150, 30);
            add(NewFirst_Namelbl);
            

            NewFirst_Name = new JTextField();
            NewFirst_Name.setFont(new Font("Tahoma", Font.PLAIN, 22));
            NewFirst_Name.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            NewFirst_Name.setBounds(150, 10, 150, 40);
            add(NewFirst_Name);
            NewFirst_Name.setColumns(20);
            
            NewLast_Namelbl = new JLabel("Last Name");
            NewLast_Namelbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
            NewLast_Namelbl.setBounds(10, 80, 150, 30);
            add(NewLast_Namelbl);
            

            NewLastName = new JTextField();
            NewLastName.setFont(new Font("Tahoma", Font.PLAIN, 22));
            NewLastName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            NewLastName.setBounds(150, 80, 150, 40);
            add(NewLastName);
            NewLastName.setColumns(20);
            
            UpdateName = new JButton("Update");
            UpdateName.setBounds(80, 140, 150, 30);
            UpdateName.addActionListener((ActionListener) this);
            add(UpdateName);
            
//            ChangePassFrame.setResizable(false);
            setLayout(null);
            setSize(320,220);
            setLocation(500,320);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == UpdateName){
            String FirstName = NewFirst_Name.getText();
            String LastName = NewLastName.getText();
            try{
                DBConnection c1 = new DBConnection();

                String q = "update Admin SET fname = '"+ FirstName +"', lname = '"+ LastName +"'"
                        + "Where Adminid ='" + AdminLogin.currentAdminID + "'";

                int x = c1.s.executeUpdate(q);
                if(x == 0){
                    JOptionPane.showMessageDialog(null, " Error ");
                }else{
                    JOptionPane.showMessageDialog(null, " Name Updated Successfully ");
                    setVisible(false);
                }
            }catch(Exception e){
                e.printStackTrace();
                }
        }
    }
    public static void main(String[] args) {
        new AdminChangeName();
    }
}