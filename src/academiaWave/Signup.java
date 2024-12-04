package academiaWave;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import academiaWave.Signup;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import academiaWave.Student.StudentSignup;
import academiaWave.Teacher.TeacherSignup;

public class Signup extends JFrame implements ActionListener{
    JPanel panel;
     JButton StudentSignUp, TeacherSignUp;
     JLabel title;
    public Signup(){
        super("Signup");
        setSize(360,220);
        setLocation(550,365);
        setLayout(new BorderLayout());
        
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/systemIcon.png"));
        setIconImage(icon.getImage());
        
        panel = new JPanel();
        panel.setLayout(null);
        
        title = new JLabel("SignUp Here", JLabel.CENTER);
        title.setBackground(Color.LIGHT_GRAY);
        title.setForeground(Color.BLACK);
        title.setOpaque(true);
        title.setFont(title.getFont ().deriveFont (22.0f));
        
        
        StudentSignUp = new  JButton("Student SignUp");
        StudentSignUp.addActionListener((ActionListener) this);
        StudentSignUp.setHorizontalAlignment(JButton.CENTER);
        StudentSignUp.setBounds(115, 15, 130, 40);
        
        TeacherSignUp = new  JButton("Teacher SignUp");
        TeacherSignUp.addActionListener((ActionListener) this);
        TeacherSignUp.setHorizontalAlignment(JButton.CENTER);
        TeacherSignUp.setBounds(115, 95, 130, 40);

        
        
        // add to panel
        panel.add(StudentSignUp);
        panel.add(TeacherSignUp);
        
        // Add to Frame
        add(title, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
         
        // Settings for the frame
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
     @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == StudentSignUp){
            setVisible(false);
            new StudentSignup();
        }
        else if(ae.getSource() == TeacherSignUp){
            setVisible(false);
            new TeacherSignup();
        }
    }
    public static void main(String[] args) {
        new Signup();
    }
}
