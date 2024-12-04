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
import academiaWave.Student.StudentChangeName;
import academiaWave.Student.StudentLogin;

public class StudentChangeName extends JFrame implements ActionListener{
    JLabel NewFirstNamelbl;
    JLabel NewLastNamelbl;
    JTextField NewFirstName;
    JTextField NewLastName;
    JButton UpdateNameButton;
    public StudentChangeName(){
    	NewFirstNamelbl = new JLabel("First Name");
    	NewFirstNamelbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	NewFirstNamelbl.setBounds(10, 10, 150, 30);
            add(NewFirstNamelbl);
            

            NewFirstName = new JTextField();
            NewFirstName.setFont(new Font("Tahoma", Font.PLAIN, 22));
            NewFirstName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            NewFirstName.setBounds(150, 10, 150, 40);
            add(NewFirstName);
            NewFirstName.setColumns(20);
            
            NewLastNamelbl = new JLabel("Last Name");
            NewLastNamelbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
            NewLastNamelbl.setBounds(10, 80, 150, 30);
            add(NewLastNamelbl);
            

            NewLastName = new JTextField();
            NewLastName.setFont(new Font("Tahoma", Font.PLAIN, 22));
            NewLastName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            NewLastName.setBounds(150, 80, 150, 40);
            add(NewLastName);
            NewLastName.setColumns(20);
            
            UpdateNameButton = new JButton("Update");
            UpdateNameButton.setBounds(80, 140, 150, 30);
            UpdateNameButton.addActionListener((ActionListener) this);
            add(UpdateNameButton);
            
//            ChangePassFrame.setResizable(false);
            setLayout(null);
            setSize(320,220);
            setLocation(500,320);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == UpdateNameButton){
            String FirstName = NewFirstName.getText();
            String LastName = NewLastName.getText();
            try{
                DBConnection c1 = new DBConnection();

                String q = "update Student SET fname = '"+ FirstName +"', lname = '"+ LastName +"'"
                        + "Where stdID ='" + StudentLogin.currentStudentID + "'";

                int x = c1.s.executeUpdate(q);
                if(x == 0){
                    JOptionPane.showMessageDialog(null, " Error ");
                }else{
                    JOptionPane.showMessageDialog(null, " Name Updated Successfully");
                    setVisible(false);
                }
            }catch(Exception e){
                e.printStackTrace();
                }
        }
    }
    public static void main(String[] args) {
        new StudentChangeName();
    }
}