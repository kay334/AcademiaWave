package academiaWave.Student;

import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import academiaWave.DBConnection;
import academiaWave.Main;
import academiaWave.Admin.Admin;
import academiaWave.Student.EnrollCourse;
import academiaWave.Student.Inbox;
import academiaWave.Student.SentBox;
import academiaWave.Student.StudentAccountDetails;
import academiaWave.Student.StudentDeleteAccount;
import academiaWave.Student.StudentLogin;
import academiaWave.Student.StudentManageAccount;
import academiaWave.Student.StudyCourse;
import academiaWave.Student.ViewParticipants;
import academiaWave.Student.WithdrawCourse;

public class Student extends JFrame implements ActionListener, WindowStateListener{
    JPanel sidePanel, rightPanel, buttonsPanel;
    JLabel User_icon, UsernameLabel;
    JButton ViewProfile_Button, LogoutButton;
    JButton Manage,Delete, View, Enroll, Study, Withdraw, View2, InboxButton, SentBoxButton;
    BufferedImage bufferedImage = null;
    public Student(){
        super("Student Module");
        setLayout(new BorderLayout());
        setSize(1280,720);
        setLocation(35,30);
        
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/systemIcon.png"));
        setIconImage(icon.getImage());
        // add windowstate Lister
        this.addWindowStateListener(this);
      
        sidePanel = new JPanel();
        sidePanel.setLayout(null);
        sidePanel.setBackground(new Color(0, 26, 195));
        Dimension sidePanelSize = new Dimension(180, 720);
        sidePanel.setPreferredSize(sidePanelSize);
        add(sidePanel, BorderLayout.WEST);
        
        //
        String firstName = null, lastName = null, gender = "";
        byte[] bytImage = null;
        try{
            DBConnection c1 = new DBConnection();
            PreparedStatement ps = c1.c.prepareStatement("select * from Student where stdID = '"+ StudentLogin.currentStudentID +"'");
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                firstName = rs.getString("fname");
                lastName = rs.getString("lname");
                gender = rs.getString("Gender");
                //get image as byte
                bytImage = rs.getBytes("picture");
            }else{
                JOptionPane.showMessageDialog(null, "Not Found");
            }
        }catch(HeadlessException | NumberFormatException | SQLException e){
            e.printStackTrace();
        }
        
        //sidePanel Code
        InputStream is = new ByteArrayInputStream(bytImage);
        try {
            bufferedImage = ImageIO.read(is);
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        User_icon = new JLabel(resizeImage(bufferedImage));
        User_icon.setBounds(38, 5, 96, 96);
        User_icon.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        sidePanel.add(User_icon);
        
        
        
        UsernameLabel = new JLabel();
        UsernameLabel.setFont(new Font(Font.SERIF,Font.BOLD, 20));
        UsernameLabel.setForeground(new Color(45,255,3));
        UsernameLabel.setBounds(20, 98, 150, 40);
        UsernameLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        UsernameLabel.setText(firstName + " " + lastName);
        sidePanel.add(UsernameLabel);
        
        ViewProfile_Button = new JButton("View Profile");
        ViewProfile_Button.setFont(new Font(Font.SERIF,Font.BOLD, 13));
        ViewProfile_Button.setBackground(Color.BLACK);
        ViewProfile_Button.setForeground(Color.WHITE);
        ViewProfile_Button.setBounds(30, 150, 120, 28);
        ViewProfile_Button.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        ViewProfile_Button.addActionListener((ActionListener) this);
        sidePanel.add(ViewProfile_Button);
        
        
        LogoutButton = new JButton("Logout");
        LogoutButton.setFont(new Font(Font.SERIF,Font.BOLD, 13));
        LogoutButton.setBackground(Color.BLACK);
        LogoutButton.setForeground(Color.WHITE);
        LogoutButton.setBounds(30, 600, 120, 28);
        LogoutButton.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        LogoutButton.addActionListener((ActionListener) this);
        sidePanel.add(LogoutButton);
        
        //rightPanel Code
        rightPanel = new JPanel(new BorderLayout());
        add(rightPanel, BorderLayout.CENTER);
        
        JLabel mainTitle = new JLabel("Student Module");
        mainTitle.setHorizontalAlignment(JLabel.CENTER);
        mainTitle.setFont(new Font(Font.SERIF,Font.BOLD, 50));
        mainTitle.setBackground(Color.BLACK);
        mainTitle.setForeground(Color.WHITE);
        mainTitle.setOpaque(true);
        rightPanel.add(mainTitle, BorderLayout.NORTH);
        
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(null);
        rightPanel.add(buttonsPanel, BorderLayout.CENTER);
        
        JLabel buttonSectionTitle = new JLabel("My Account");
        buttonSectionTitle.setFont(new Font(Font.SERIF,Font.BOLD, 25));
        buttonSectionTitle.setForeground(Color.BLACK);
        buttonSectionTitle.setHorizontalAlignment(JLabel.LEFT);
        buttonSectionTitle.setBounds(6,6,150,50);
        buttonsPanel.add(buttonSectionTitle);
       
        // 64 is the size of Button Icon https://icons8.com/
        
        Manage = new JButton("Manage Account");
        Manage.setIcon(new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/ManageAccount.png")));
        Manage.setBounds(250,60,130,90);
        Manage.setHorizontalTextPosition(JButton.CENTER);
        Manage.setVerticalTextPosition(JButton.BOTTOM);
        Manage.addActionListener((ActionListener) this);
        buttonsPanel.add(Manage);
        
        Delete = new JButton("Delete Account");
        Delete.setIcon(new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/DeleteAccount.png")));
        Delete.setBounds(410,60,130,90);
        Delete.setHorizontalTextPosition(JButton.CENTER);
        Delete.setVerticalTextPosition(JButton.BOTTOM);
        Delete.addActionListener((ActionListener) this);
        buttonsPanel.add(Delete);
        
        // gap to 160 Horizontally
        View = new JButton("View Account");
        View.setIcon(new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/viewAccount.png")));
        View.setBounds(570,60,130,90);
        View.setHorizontalTextPosition(JButton.CENTER);
        View.setVerticalTextPosition(JButton.BOTTOM);
        View.addActionListener((ActionListener) this);
        buttonsPanel.add(View);
        
        
        // second Row of Buttons
        JLabel buttonSectionTitle2 = new JLabel("Student Operations");
        buttonSectionTitle2.setFont(new Font(Font.SERIF,Font.BOLD, 25));
        buttonSectionTitle2.setForeground(Color.BLACK);
        buttonSectionTitle2.setHorizontalAlignment(JLabel.LEFT);
        buttonSectionTitle2.setBounds(6,150,225,50);
        buttonsPanel.add(buttonSectionTitle2);
       
        // 64 is the size of Button Icon https://icons8.com/
        
        Enroll = new JButton("Enroll Course");
        Enroll.setIcon(new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/addSubject.png")));
        Enroll.setBounds(250,210,130,90);
        Enroll.setHorizontalTextPosition(JButton.CENTER);
        Enroll.setVerticalTextPosition(JButton.BOTTOM);
        Enroll.addActionListener((ActionListener) this);
        buttonsPanel.add(Enroll);
        
        Study = new JButton("Study Course");
        Study.setIcon(new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/StartCourse.png")));
        Study.setBounds(410,210,130,90);
        Study.setHorizontalTextPosition(JButton.CENTER);
        Study.setVerticalTextPosition(JButton.BOTTOM);
        Study.addActionListener((ActionListener) this);
        buttonsPanel.add(Study);
        
        Withdraw = new JButton("Withdraw Course");
        Withdraw.setIcon(new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/withdrawCourse.png")));
        Withdraw.setBounds(570,210,140,90);
        Withdraw.setHorizontalTextPosition(JButton.CENTER);
        Withdraw.setVerticalTextPosition(JButton.BOTTOM);
        Withdraw.addActionListener((ActionListener) this);
        buttonsPanel.add(Withdraw);
        
        View2 = new JButton("View Particepants");
        View2.setIcon(new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/participants.png")));
        View2.setBounds(250,320,140,90);
        View2.setHorizontalTextPosition(JButton.CENTER);
        View2.setVerticalTextPosition(JButton.BOTTOM);
        View2.addActionListener((ActionListener) this);
        buttonsPanel.add(View2);
        
        InboxButton = new JButton("Inbox");
        InboxButton.setIcon(new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/inbox.png")));
        InboxButton.setBounds(950,10,130,125);
        InboxButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        InboxButton.setHorizontalTextPosition(JButton.CENTER);
        InboxButton.setVerticalTextPosition(JButton.BOTTOM);
        InboxButton.setBorderPainted(false);
        InboxButton.addActionListener(this);
        buttonsPanel.add(InboxButton);
        
        SentBoxButton = new JButton("Sentbox");
        SentBoxButton.setIcon(new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/sentbox.png")));
        SentBoxButton.setBounds(950,150,130,125);
        SentBoxButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        SentBoxButton.setHorizontalTextPosition(JButton.CENTER);
        SentBoxButton.setVerticalTextPosition(JButton.BOTTOM);
        SentBoxButton.setBorderPainted(false);
        SentBoxButton.addActionListener(this);
        buttonsPanel.add(SentBoxButton);
        

        setResizable(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);   
    }
    // This code use to resize image to fit lable
    public ImageIcon resizeImage(BufferedImage bufferedImage){
        int width = bufferedImage.getWidth();
        BufferedImage circleBuffer = new BufferedImage(width, width, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = circleBuffer.createGraphics();
        g2.setClip(new Ellipse2D.Float(0, 0, width, width));
        g2.drawImage(bufferedImage, 0, 0, width, width, null);
        ImageIcon icon = new ImageIcon(circleBuffer);
        Image i2 = icon.getImage().getScaledInstance(96 ,96 ,Image.SCALE_DEFAULT);
        ImageIcon i3 =  new ImageIcon(i2);
        return i3;
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == Manage){
            new StudentManageAccount();
        }
        else if(ae.getSource() == Delete){
            StudentDeleteAccount delete = new StudentDeleteAccount();
            if(delete.input == 0){
                dispose();
                new Main();
            }
        }
        else if(ae.getSource() == View){
            new StudentAccountDetails();
        }
         else if(ae.getSource() == Enroll){
            new EnrollCourse();
        }
        else if(ae.getSource() == Study){
            new StudyCourse();
        }
        else if(ae.getSource() == Withdraw){
            new WithdrawCourse();
        }
        else if(ae.getSource() == View2){
            new ViewParticipants();
        }
        else if(ae.getSource() == ViewProfile_Button){
            JOptionPane.showMessageDialog(this, new ImageIcon(bufferedImage), "Profile Picture", JOptionPane.PLAIN_MESSAGE);
        }
        else if(ae.getSource() == LogoutButton){
            java.util.Date dt = new java.util.Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String last_Login = dateFormat.format(dt);
            try{
                DBConnection c1 = new DBConnection();

                String q = "update Student "
                        + "Set Last_Login = '"+ last_Login +"'"
                        + "Where stdID = '"+ StudentLogin.currentStudentID +"'";

                int x = c1.s.executeUpdate(q);
                if(x == 0){
                JOptionPane.showMessageDialog(null, "Got an Error");
                }else{
                    JOptionPane.showMessageDialog(null, "Loggin Out...");
                     new Main();
                     dispose();
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        else if(ae.getSource() == InboxButton){
            new Inbox();
        }
        else if(ae.getSource() == SentBoxButton){
            new SentBox();
        }
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
          // normal state
        if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.NORMAL){
        	InboxButton.setLocation(950,10);
        	SentBoxButton.setLocation(950,150);
           LogoutButton.setLocation(30, 600);
        }
        // maximized
        else if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH){
        	InboxButton.setLocation(this.getWidth()- 335, 10);
        	SentBoxButton.setLocation(this.getWidth()- 335, 150);
            LogoutButton.setLocation(30, this.getHeight() - 120);
        }
    }
}
