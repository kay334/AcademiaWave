package academiaWave.Teacher;

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

import academiaWave.DBConnection;
import academiaWave.Main;
import academiaWave.Admin.Admin;
import academiaWave.Teacher.AddCourse;
import academiaWave.Teacher.TeacherAccountDetails;
import academiaWave.Teacher.TeacherDeleteAccount;
import academiaWave.Teacher.TeacherLogin;
import academiaWave.Teacher.TeacherManageAccount;
import academiaWave.Teacher.UpdateCourses;
import academiaWave.Teacher.ViewMyCourses;
import academiaWave.Teacher.ViewMyStudents;

public class Teacher extends JFrame implements ActionListener, WindowStateListener{
    JPanel sidePanel, rightPanel, buttonsPanel;
    JLabel Usericon, Username;
    JButton ViewProfile, Logout;
    JPanel panel;
    JButton Manage,Delete, View, Add, View2, View3, Update;
    BufferedImage bufferedImage = null;
    public Teacher(){
        super("Teacher Module");
        setLayout(new BorderLayout());
        setSize(1280,720);
        setLocation(35,30);
        
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/systemIcon.png"));
        setIconImage(icon.getImage());
      
        sidePanel = new JPanel();
        sidePanel.setLayout(null);
        sidePanel.setBackground(new Color(0, 26, 195));
        Dimension sidePanelSize = new Dimension(180, 720);
        sidePanel.setPreferredSize(sidePanelSize);
        add(sidePanel, BorderLayout.WEST);
        
        //
        String firstName = null,lastName = null, gender = "";
        byte[] bytImage = null;
        try{
            DBConnection c1 = new DBConnection();
            PreparedStatement ps = c1.c.prepareStatement("select * from Teacher where teacherID = '"+ TeacherLogin.currentTeacherID +"'");
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
        Usericon = new JLabel(resizeImage(bufferedImage));
        Usericon.setBounds(38, 5, 96, 96);
        Usericon.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        sidePanel.add(Usericon);

        Username = new JLabel();
        Username.setFont(new Font(Font.SERIF,Font.BOLD, 20));
        Username.setForeground(new Color(45,255,3));
        Username.setBounds(20, 98, 150, 40);
        Username.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        Username.setText(firstName + " " + lastName);
        sidePanel.add(Username);
        
        ViewProfile = new JButton("View Profile");
        ViewProfile.setFont(new Font(Font.SERIF,Font.BOLD, 13));
        ViewProfile.setBackground(Color.BLACK);
        ViewProfile.setForeground(Color.WHITE);
        ViewProfile.setBounds(30, 150, 120, 28);
        ViewProfile.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        ViewProfile.addActionListener((ActionListener) this);
        sidePanel.add(ViewProfile);
        
        
        Logout = new JButton("Logout");
        Logout.setFont(new Font(Font.SERIF,Font.BOLD, 13));
        Logout.setBackground(Color.BLACK);
        Logout.setForeground(Color.WHITE);
        Logout.setBounds(30, 600, 120, 28);
        Logout.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        Logout.addActionListener((ActionListener) this);
        sidePanel.add(Logout);
        
        //rightPanel Code
        rightPanel = new JPanel(new BorderLayout());
        add(rightPanel, BorderLayout.CENTER);
        
        JLabel mainTitle = new JLabel("Teacher Module");
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
        JLabel buttonSectionTitle2 = new JLabel("Teacher Operations");
        buttonSectionTitle2.setFont(new Font(Font.SERIF,Font.BOLD, 25));
        buttonSectionTitle2.setForeground(Color.BLACK);
        buttonSectionTitle2.setHorizontalAlignment(JLabel.LEFT);
        buttonSectionTitle2.setBounds(6,150,225,50);
        buttonsPanel.add(buttonSectionTitle2);
       
        // 64 is the size of Button Icon https://icons8.com/
        Add = new JButton("Add Course");
        Add.setIcon(new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/addSubject.png")));
        Add.setBounds(250,210,130,90);
        Add.setHorizontalTextPosition(JButton.CENTER);
        Add.setVerticalTextPosition(JButton.BOTTOM);
        Add.addActionListener((ActionListener) this);
        buttonsPanel.add(Add);
        
        View2 = new JButton("View My Students");
        View2.setIcon(new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/viewStudents.png")));
        View2.setBounds(410,210,145,90);
        View2.setHorizontalTextPosition(JButton.CENTER);
        View2.setVerticalTextPosition(JButton.BOTTOM);
        View2.addActionListener((ActionListener) this);
        buttonsPanel.add(View2);
        
        View3 = new JButton("View My Courses");
        View3.setIcon(new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/myCourses.png")));
        View3.setBounds(570,210,140,90);
        View3.setHorizontalTextPosition(JButton.CENTER);
        View3.setVerticalTextPosition(JButton.BOTTOM);
        View3.addActionListener((ActionListener) this);
        buttonsPanel.add(View3);
        
        Update = new JButton("Update Courses");
        Update.setIcon(new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/updateCourse.png")));
        Update.setBounds(250,320,130,90);
        Update.setHorizontalTextPosition(JButton.CENTER);
        Update.setVerticalTextPosition(JButton.BOTTOM);
        Update.addActionListener((ActionListener) this);
        buttonsPanel.add(Update);

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
            new TeacherManageAccount();
        }
        else if(ae.getSource() == Delete){
            TeacherDeleteAccount delete = new TeacherDeleteAccount();
            if(delete.input == 0){
                dispose();
                new Main();
            }
        }
        else if(ae.getSource() == View){
            new TeacherAccountDetails();
        }
        else if(ae.getSource() == Add){
            new AddCourse();
        }
        else if(ae.getSource() == View2){
            new ViewMyStudents();
        }
        else if(ae.getSource() == View3){
            new ViewMyCourses();
        }
        else if(ae.getSource() == Update){
            new UpdateCourses();
        }
        else if(ae.getSource() == ViewProfile){
            JOptionPane.showMessageDialog(this, new ImageIcon(bufferedImage), "Profile Picture", JOptionPane.PLAIN_MESSAGE);
        }
        else if(ae.getSource() == Logout){
            java.util.Date dt = new java.util.Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String last_Login = dateFormat.format(dt);
            try{
                DBConnection c1 = new DBConnection();

                String q = "update Teacher "
                        + "Set Last_Login = '"+ last_Login +"'"
                        + "Where teacherID = '"+ TeacherLogin.currentTeacherID +"'";
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
    }
    @Override
    public void windowStateChanged(WindowEvent e) {
        // normal state
        if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.NORMAL){
        	Logout.setLocation(30, 600);
        }
        // maximized
        else if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH){
        	Logout.setLocation(30, this.getHeight() - 120);
        }
    }
}
