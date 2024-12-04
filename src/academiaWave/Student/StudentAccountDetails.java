package academiaWave.Student;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import academiaWave.DBConnection;
import academiaWave.Student.StudentAccountDetails;
import academiaWave.Student.StudentLogin;

public class StudentAccountDetails extends JFrame implements ActionListener{
    JPanel contentPane;
    JTextField First_Name, Last_name, Email, User_name, Password_Field, Gender;
    JButton OkayButton;
    public StudentAccountDetails(){
        super("Student Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1014,550);
        setLocation(230,110);

        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblTeacherDetails = new JLabel("Student Details");
        lblTeacherDetails.setFont(new Font("Times New Roman", Font.PLAIN, 42));
        lblTeacherDetails.setBounds(362, 52, 325, 50);
        contentPane.add(lblTeacherDetails);

        JLabel lblName = new JLabel("First name");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblName.setBounds(58, 152, 99, 43);
        contentPane.add(lblName);

        JLabel lblNewLabel = new JLabel("Last name");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(58, 243, 110, 29);
        contentPane.add(lblNewLabel);

        JLabel lblEmailAddress = new JLabel("Email\r\n address");
        lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblEmailAddress.setBounds(58, 324, 124, 36);
        contentPane.add(lblEmailAddress);
        
        String firstName = null,lastName = null, 
                emailId = null, userName = null, 
                password = null, userGender = null;
        try{
            DBConnection c1 = new DBConnection();
            String q = "select * from Student where stdID = '"+ StudentLogin.currentStudentID +"'";

            ResultSet rs = c1.s.executeQuery(q);
            if(rs.next()){
                firstName = rs.getString("fname");
                lastName = rs.getString("lname");
                emailId = rs.getString("Email_ID");
                userName = rs.getString("username");
                password = rs.getString("password");
                userGender = rs.getString("Gender");
            }else{
                JOptionPane.showMessageDialog(null, "Not Found");
            }
        }catch(HeadlessException | NumberFormatException | SQLException e){
            e.printStackTrace();
        }
        
 
        First_Name = new JTextField();
        First_Name.setFont(new Font("Tahoma", Font.PLAIN, 32));
        First_Name.setBounds(214, 151, 228, 50);
        First_Name.setEditable(false);
        First_Name.setText(firstName);
        contentPane.add(First_Name);

        Last_name = new JTextField();
        Last_name.setFont(new Font("Tahoma", Font.PLAIN, 32));
        Last_name.setBounds(214, 235, 228, 50);
        Last_name.setEditable(false);
        Last_name.setText(lastName);
        contentPane.add(Last_name);

        Email = new JTextField();
        Email.setFont(new Font("Tahoma", Font.PLAIN, 32));
        Email.setBounds(214, 320, 320, 50);
        Email.setEditable(false);
        Email.setText(emailId);
        contentPane.add(Email);

        User_name = new JTextField();
        User_name.setFont(new Font("Tahoma", Font.PLAIN, 32));
        User_name.setBounds(707, 151, 228, 50);
        User_name.setEditable(false);
        User_name.setText(userName);
        contentPane.add(User_name);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUsername.setBounds(542, 159, 99, 29);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPassword.setBounds(542, 245, 99, 24);
        contentPane.add(lblPassword);

        Password_Field = new JTextField();
        Password_Field.setFont(new Font("Tahoma", Font.PLAIN, 32));
        Password_Field.setBounds(707, 235, 228, 50);
        Password_Field.setEditable(false);
        Password_Field.setText(password);
        contentPane.add(Password_Field);
        
        JLabel lblGender = new JLabel("Gender");
        lblGender.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblGender.setBounds(542, 321, 99, 24);
        contentPane.add(lblGender);

        Gender = new JTextField();
        Gender.setFont(new Font("Tahoma", Font.PLAIN, 32));
        Gender.setBounds(707, 311, 228, 50);
        Gender.setEditable(false);
        Gender.setText(userGender);
        contentPane.add(Gender);

        OkayButton = new JButton("Ok");
        OkayButton.setBounds(410, 440, 228, 60);
        OkayButton.addActionListener((ActionListener) this);
        contentPane.add(OkayButton);
        
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == OkayButton){
            dispose();
        }
    }
    
    public static void main(String[] args) {
        new StudentAccountDetails();
    }
}
