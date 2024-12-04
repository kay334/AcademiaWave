package academiaWave;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import academiaWave.AboutProject;
import academiaWave.Login;
import academiaWave.Main;
import academiaWave.Signup;

public class Main extends JFrame implements ActionListener{
    JPanel Header, Main, Footer;
    JButton Login, Signup;
    JLabel   mainScreenLbl;
    JMenuBar Menu;
    JMenu file, about;
    JMenuItem ProjectInfo,DeveloperInfo, exit;
    public static Main main;
    public Main(){
        super("AcademiaWave");
        setSize(1280,720);
        setLocation(35,30);
        setLayout(new BorderLayout());
        this.main = this;
        
     
        
        Header = new JPanel(new BorderLayout());
        add(Header, BorderLayout.NORTH);
        
        Main = new JPanel();
        Main.setLayout(null);
        add(Main, BorderLayout.CENTER);
        
        Footer = new JPanel(new BorderLayout());
        add(Footer, BorderLayout.SOUTH);
        
        
        //Header Code
        ProjectInfo = new JMenuItem("About Project");    
        DeveloperInfo = new JMenuItem("About Developers");  
        Menu = new JMenuBar();    
        about = new JMenu("About");
        exit = new JMenuItem("Exit");
        // Adding Shortcut key to close the window
        KeyStroke ctrlE = KeyStroke.getKeyStroke(KeyEvent.VK_E, Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask());
        exit.setAccelerator(ctrlE);
        file = new JMenu("File");
        file.add(exit);
        about.add(ProjectInfo); about.add(DeveloperInfo);
        exit.addActionListener(this);
        DeveloperInfo.addActionListener(this);
        ProjectInfo.addActionListener(this);
        Menu.add(file);
        Menu.add(about);
        
        
        Header.add(Menu,BorderLayout.NORTH);
        
        
        
        
        
        JLabel clock = new JLabel();
        clock.setHorizontalAlignment(JLabel.RIGHT);
        DateFormat df = new SimpleDateFormat("yyyy-MMM-dd hh:mm:ss aa");
        Calendar calobj = Calendar.getInstance();
        clock.setText(df.format(calobj.getTime()));
        clock.setFont(new Font(Font.SERIF,Font.BOLD, 19));
        clock.setForeground(new Color(14,168,29));
        Header.add(clock, BorderLayout.SOUTH);
        
        
        // A Swing timer fires one or more action events after a specified delay.
        Timer t = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateFormat df = new SimpleDateFormat("yyyy-MMM-dd hh:mm:ss aa");
                Calendar calobj = Calendar.getInstance();
              clock.setText(df.format(calobj.getTime()));
            }
         });
         t.setRepeats(true);
         t.setCoalesce(true);
         t.setInitialDelay(0);
         t.start();
         
        //Main Center Code
        ImageIcon loginImg1 = new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/login.png"));
        Image loginImg2 = loginImg1.getImage().getScaledInstance(96  ,96 ,Image.SCALE_DEFAULT);
        ImageIcon loginImg3 =  new ImageIcon(loginImg2);
        Login = new  JButton("Login", loginImg3);
        Login.setHorizontalAlignment(JButton.CENTER);
        Login.setBounds(420,15,190,150);
        Login.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        Login.setBackground(new Color(21,76,121));
        Login.setForeground(Color.WHITE);
        Login.setHorizontalTextPosition(JButton.CENTER);
        Login.setVerticalTextPosition(JButton.BOTTOM);
        Login.addActionListener((ActionListener) this);
        
        
        ImageIcon signUpImg1 = new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/signup.png"));
        Image signUpImg2 = signUpImg1.getImage().getScaledInstance(96  ,96 ,Image.SCALE_DEFAULT);
        ImageIcon signUpImg3 =  new ImageIcon(signUpImg2);
        Signup = new  JButton("SignUp", signUpImg3);
        Signup.setHorizontalAlignment(JButton.CENTER);
        Signup.setBounds(715,15,210,150);
        Signup.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        Signup.setBackground(new Color(21,76,121));
        Signup.setForeground(Color.WHITE);
        Signup.setHorizontalTextPosition(JButton.CENTER);
        Signup.setVerticalTextPosition(JButton.BOTTOM);
        Signup.addActionListener((ActionListener) this);
        
        
        
        
        // add to mainPanel
        Main.add(Login);
        Main.add(Signup);

        
       
        
         
        // Settings for the frame
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
     @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == Login){
            new Login();
        }
        else if(ae.getSource() == Signup){
            new Signup();
        }
    
         else if (ae.getSource() == ProjectInfo){
            new AboutProject();
        }
        else if(ae.getSource() == exit){
             System.exit(0);
         }
    }
    public static void main(String[] args) {
        new Main();
    }
}
