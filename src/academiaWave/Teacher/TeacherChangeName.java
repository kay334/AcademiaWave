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
import javax.swing.JTextField;

import academiaWave.DBConnection;
import academiaWave.Teacher.TeacherChangeName;
import academiaWave.Teacher.TeacherLogin;

public class TeacherChangeName extends JFrame implements ActionListener{
    JLabel New_First_Namelbl;
    JLabel New_Last_Namelbl;
    JTextField New_First_Name;
    JTextField New_Last_Name;
    JButton UpdateName;
    public TeacherChangeName(){
    	New_First_Namelbl = new JLabel("First Name");
    	New_First_Namelbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	New_First_Namelbl.setBounds(10, 10, 150, 30);
            add(New_First_Namelbl);
            

            New_First_Name = new JTextField();
            New_First_Name.setFont(new Font("Tahoma", Font.PLAIN, 22));
            New_First_Name.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            New_First_Name.setBounds(150, 10, 150, 40);
            add(New_First_Name);
            New_First_Name.setColumns(20);
            
            New_Last_Namelbl = new JLabel("Last Name");
            New_Last_Namelbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
            New_Last_Namelbl.setBounds(10, 80, 150, 30);
            add(New_Last_Namelbl);
            

            New_Last_Name = new JTextField();
            New_Last_Name.setFont(new Font("Tahoma", Font.PLAIN, 22));
            New_Last_Name.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            New_Last_Name.setBounds(150, 80, 150, 40);
            add(New_Last_Name);
            New_Last_Name.setColumns(20);
            
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
            String FirstName = New_First_Name.getText();
            String LastName = New_Last_Name.getText();
            try{
                DBConnection c1 = new DBConnection();

                String q = "update Teacher SET fname = '"+ FirstName +"', lname = '"+ LastName +"'"
                        + "Where teacherID ='" + TeacherLogin.currentTeacherID + "'";

                int x = c1.s.executeUpdate(q);
                if(x == 0){
                    JOptionPane.showMessageDialog(null, "Got some error");
                }else{
                    JOptionPane.showMessageDialog(null, "Your Name Updated Successfully");
                    setVisible(false);
                }
            }catch(Exception e){
                e.printStackTrace();
                }
        }
    }
    public static void main(String[] args) {
        new TeacherChangeName();
    }
}