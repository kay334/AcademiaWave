package academiaWave;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import academiaWave.Login;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import academiaWave.Admin.AdminLogin;
import academiaWave.Student.StudentLogin;
import academiaWave.Teacher.TeacherLogin;


public class Login extends JFrame implements ActionListener{
     JPanel panel;
     JButton Student, Teacher, Admin;
     JLabel title;
    public Login(){
        super("Login");
        setSize(360,260);
        setLocation(550,365);
        setLayout(new BorderLayout());
        
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/systemIcon.png"));
        setIconImage(icon.getImage());
        
        panel = new JPanel();
        panel.setLayout(null);
        
        title = new JLabel("Login Here", JLabel.CENTER);
        title.setFont(title.getFont().deriveFont (22.0f));
        title.setBackground(Color.LIGHT_GRAY);
        title.setForeground(Color.BLACK);
        title.setOpaque(true);
        
        Student = new  JButton("Student Login");
        Student.addActionListener((ActionListener) this);
        Student.setHorizontalAlignment(JButton.CENTER);
        Student.setBounds(115, 15, 120, 40);
        
        Teacher = new  JButton("Teacher Login");
        Teacher.addActionListener((ActionListener) this);
        Teacher.setHorizontalAlignment(JButton.CENTER);
        Teacher.setBounds(115, 75, 120, 40);
        
        Admin = new  JButton("Admin Login");
        Admin.addActionListener((ActionListener) this);
        Admin.setHorizontalAlignment(JButton.CENTER);
        Admin.setBounds(115, 135, 120, 40);
        
        // add to panel
        panel.add(Student);
        panel.add(Teacher);
        panel.add(Admin);
        
        // Add to Frame
        add(title, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
     @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == Student){
            setVisible(false);
            new StudentLogin();
        }
        else if(ae.getSource() == Teacher){
            setVisible(false);
            new TeacherLogin();
        }
        else if(ae.getSource() == Admin){
            setVisible(false);
            new AdminLogin();
        }
    }
    public static void main(String[] args) {
        new Login();
    }
}
