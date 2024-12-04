package academiaWave.Student;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import academiaWave.DBConnection;
import academiaWave.Main;
import academiaWave.Student.Student;
import academiaWave.Student.StudentLogin;

public class StudentLogin extends JFrame implements ActionListener{
    JLabel Username,Password;
    JTextField t1;
    JPasswordField t2;
    JButton Login,Cancel;
    public static int currentStudentID;
    public StudentLogin(){
        super("Student Login");
        
        setLayout(null);

        Username = new JLabel("Username");
        Username.setBounds(40,20,100,30);
        add(Username);
        
        Username = new JLabel("Password");
        Username.setBounds(40,70,100,30);
        add(Username);
 
        t1=new JTextField();
        t1.setBounds(150,20,150,30);
        add(t1);

        t2=new JPasswordField();
        t2.setBounds(150,70,150,30);
        add(t2);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i3 =  new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(350,20,150,150);
        add(l3);


        Login = new JButton("Login");
        Login.setBounds(40,140,120,30);
        Login.setFont(new Font("serif",Font.BOLD,15));
        Login.addActionListener(this);
        Login.setBackground(Color.BLACK);
        Login.setForeground(Color.WHITE);
        add(Login);

        Cancel=new JButton("Cancel");
        Cancel.setBounds(180,140,120,30);
        Cancel.setFont(new Font("serif",Font.BOLD,15));
        Cancel.setBackground(Color.BLACK);
        Cancel.setForeground(Color.WHITE);
        add(Cancel);
        Cancel.addActionListener(this);
        
        getContentPane().setBackground(Color.WHITE);
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600,300);
        setLocation(420,365);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == Login){
            try{
                DBConnection c1 = new DBConnection();
                String u = t1.getText();
                String v = String.valueOf(t2.getPassword());

                String q = "select * from Student where username='"+u+"' and password='"+v+"'";

                ResultSet rs = c1.s.executeQuery(q);
                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "login Successfull");
                    currentStudentID = Integer.parseInt(rs.getString("stdID"));
                    setVisible(false);
                    new Student();
                    Main.main.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid login");
                }
        }catch(Exception e){
            e.printStackTrace();
            }
        }
        else if(ae.getSource() == Cancel){
            dispose();
        }
    }
    public static void main(String[] args) {
        new StudentLogin();
    }
}
