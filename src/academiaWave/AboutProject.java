package academiaWave;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import academiaWave.AboutProject;

public class AboutProject extends JFrame{
    JTextArea aboutText;
    JLabel title;
    public AboutProject() {
        super("About Project");
        setSize(540,380);
        setLocation(430,280);
        setLayout(new BorderLayout());
        
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/systemIcon.png"));
        setIconImage(icon.getImage());
        
        title = new JLabel("AcademiaWave", JLabel.CENTER);
        title.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        title.setBackground(Color.LIGHT_GRAY);
        title.setForeground(Color.BLUE);
        title.setOpaque(true);
        add(title, BorderLayout.NORTH);
       
        
        String txt = "\t-->  AcademiaWave System is a desktop-based Windows application "
                + "developed in Java with Swing and AWT. The project aims to serve Students and Teachers in "
                + "Online based learning. \n\n" +
                        "\t-->  Admins are able to add subjects, Teachers are able add courses in a particular subject and "
                + "Students can Enroll courses and study them, and also Students can message other participants in a "
                + "particular course except the Teacher teaching that course.";
        
        aboutText = new JTextArea(txt);
        aboutText.setLineWrap(true);
        aboutText.setWrapStyleWord(true);
        aboutText.setFont(new Font(Font.DIALOG, Font.ITALIC, 19));
        aboutText.setForeground(Color.BLACK);
        aboutText.setBackground(Color.ORANGE);
        aboutText.setBorder(new LineBorder(Color.BLUE, 2, true));
        aboutText.setEditable(false);
        add(aboutText, BorderLayout.CENTER);
        
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new AboutProject();
    }
}