package academiaWave.Admin;

import java.awt.BorderLayout;
import java.awt.Color;
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
import academiaWave.Admin.AddSubject;
import academiaWave.Admin.Admin;
import academiaWave.Admin.AdminAccountDetails;
import academiaWave.Admin.AdminDeleteAccount;
import academiaWave.Admin.AdminLogin;
import academiaWave.Admin.AdminManageAccount;
import academiaWave.Admin.AdminSignup;
import academiaWave.Admin.DeleteSubject;
import academiaWave.Admin.ViewCourses;
import academiaWave.Admin.ViewStudents;
import academiaWave.Admin.ViewTeachers;

public class Admin extends JFrame implements ActionListener, WindowStateListener{
    JPanel Side, Right, buttonsPanel;
    JButton manageAccount, ViewProfile, Logout;
    JLabel label, backgroundPic;
    JButton Manage,Delete, View, Add, Delete2, View2, View3, View4, Add2;
    JLabel usericon, lblUsername;
    BufferedImage bufferedImage = null;
    public Admin(){
        super("Admin Module");
        setSize(1280,720);
        setLocation(35,30);
        setLayout(new BorderLayout());
        
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/systemIcon.png"));
        setIconImage(icon.getImage());
      
        Side = new JPanel();
        Side.setLayout(null);
        Side.setBackground(new Color(0, 26, 195));
        Dimension sidePanelSize = new Dimension(180, 720);
        Side.setPreferredSize(sidePanelSize);
        add(Side, BorderLayout.WEST);
        
        
        //get Name and Profile from database
        String firstName = null,lastName = null;
        byte[] bytImage = null;
        try{
            DBConnection c1 = new DBConnection();
            
            PreparedStatement ps = c1.c.prepareStatement("select * from Admin where Adminid = '"+ AdminLogin.currentAdminID +"'");
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                firstName = rs.getString("fname");
                lastName = rs.getString("lname");
                //get image as byte
                bytImage = rs.getBytes("picture");
            }else{
                JOptionPane.showMessageDialog(null, "Not Found");
            }
            ps.close();
        }catch(HeadlessException | NumberFormatException | SQLException e){
            e.printStackTrace();
        }
        
        //sidePanel Code           
        if(bytImage != null){
           InputStream is = new ByteArrayInputStream(bytImage);
            try {
                bufferedImage = ImageIO.read(is);
                usericon = new JLabel(resizeImage(bufferedImage));
            } catch (IOException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            usericon = new JLabel(new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/uploadPicIcon.png")));
        }

        usericon.setBounds(38, 5, 96, 96);
        usericon.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        Side.add(usericon);
       
        
        lblUsername = new JLabel();
        lblUsername.setFont(new Font(Font.SERIF,Font.BOLD, 20));
        lblUsername.setForeground(new Color(45,255,3));
        lblUsername.setBounds(20, 98, 150, 40);
        lblUsername.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lblUsername.setText(firstName + " " + lastName);
        Side.add(lblUsername);
        
        ViewProfile = new JButton("View Profile");
        ViewProfile.setFont(new Font(Font.SERIF,Font.BOLD, 13));
        ViewProfile.setBackground(Color.BLACK);
        ViewProfile.setForeground(Color.WHITE);
        ViewProfile.setBounds(30, 150, 120, 28);
        ViewProfile.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        ViewProfile.addActionListener((ActionListener) this);
        Side.add(ViewProfile);
        
        
        Logout = new JButton("Logout");
        Logout.setFont(new Font(Font.SERIF,Font.BOLD, 13));
        Logout.setBackground(Color.BLACK);
        Logout.setForeground(Color.WHITE);
        Logout.setBounds(30, 600, 120, 28);
        Logout.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        Logout.addActionListener((ActionListener) this);
        Side.add(Logout);
        
        //rightPanel Code
        Right = new JPanel(new BorderLayout());
        add(Right, BorderLayout.CENTER);
       
        
        JLabel mainTitle = new JLabel("Admin Module");
        mainTitle.setHorizontalAlignment(JLabel.CENTER);
        mainTitle.setFont(new Font(Font.SERIF,Font.BOLD, 50));
        mainTitle.setBackground(Color.BLACK);
        mainTitle.setForeground(Color.WHITE);
        mainTitle.setOpaque(true);
        Right.add(mainTitle, BorderLayout.NORTH);
        
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(null);
        Right.add(buttonsPanel, BorderLayout.CENTER);
        
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
        JLabel buttonSectionTitle2 = new JLabel("Admin Operations");
        buttonSectionTitle2.setFont(new Font(Font.SERIF,Font.BOLD, 25));
        buttonSectionTitle2.setForeground(Color.BLACK);
        buttonSectionTitle2.setHorizontalAlignment(JLabel.LEFT);
        buttonSectionTitle2.setBounds(6,150,210,50);
        buttonsPanel.add(buttonSectionTitle2);
       
        // 64 is the size of Button Icon https://icons8.com/
        
        Add = new JButton("Add Subject");
        Add.setIcon(new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/addSubject.png")));
        Add.setBounds(250,210,130,90);
        Add.setHorizontalTextPosition(JButton.CENTER);
        Add.setVerticalTextPosition(JButton.BOTTOM);
        Add.addActionListener((ActionListener) this);
        buttonsPanel.add(Add);
        
        Delete2 = new JButton("Delete Subject");
        Delete2.setIcon(new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/deleteSubject.png")));
        Delete2.setBounds(410,210,130,90);
        Delete2.setHorizontalTextPosition(JButton.CENTER);
        Delete2.setVerticalTextPosition(JButton.BOTTOM);
        Delete2.addActionListener((ActionListener) this);
        buttonsPanel.add(Delete2);
        
        // gap to 160 Horizontally
        View2 = new JButton("View Students");
        View2.setIcon(new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/viewStudents.png")));
        View2.setBounds(570,210,130,90);
        View2.setHorizontalTextPosition(JButton.CENTER);
        View2.setVerticalTextPosition(JButton.BOTTOM);
        View2.addActionListener((ActionListener) this);
        buttonsPanel.add(View2);
        
        View3 = new JButton("View Teachers");
        View3.setIcon(new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/viewTeachers.png")));
        View3.setBounds(250,320,130,90);
        View3.setHorizontalTextPosition(JButton.CENTER);
        View3.setVerticalTextPosition(JButton.BOTTOM);
        View3.addActionListener((ActionListener) this);
        buttonsPanel.add(View3);
        
        View4 = new JButton("View Courses");
        View4.setIcon(new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/viewCourses.png")));
        View4.setBounds(410,320,130,90);
        View4.setHorizontalTextPosition(JButton.CENTER);
        View4.setVerticalTextPosition(JButton.BOTTOM);
        View4.addActionListener((ActionListener) this);
        buttonsPanel.add(View4);
        
        
        Add2 = new JButton("Add New Admin");
        Add2.setIcon(new ImageIcon(ClassLoader.getSystemResource("academiaWave/icons/addNewAdmin.png")));
        Add2.setBounds(570,320,130,90);
        Add2.setHorizontalTextPosition(JButton.CENTER);
        Add2.setVerticalTextPosition(JButton.BOTTOM);
        Add2.addActionListener((ActionListener) this);
        buttonsPanel.add(Add2);

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
            new AdminManageAccount();
        }
        else if(ae.getSource() == Delete){
            AdminDeleteAccount delete = new AdminDeleteAccount();
            if(delete.input == 0){
                dispose();
                new Main();
            }
        }
        else if(ae.getSource() == Add){
            new AddSubject();
        }
        else if(ae.getSource() == Delete2){
            new DeleteSubject();
        }
        else if(ae.getSource() == View){
            new AdminAccountDetails();
        }
        else if(ae.getSource() == ViewProfile){
            JOptionPane.showMessageDialog(this, new ImageIcon(bufferedImage), "Profile Picture", JOptionPane.PLAIN_MESSAGE);
        }
        else if(ae.getSource() == Logout){
            JOptionPane.showMessageDialog(null, "Loggin Out...");
            new Main();
            dispose();
        }
        else if(ae.getSource() == View2){
            new ViewStudents();
        }
        else if(ae.getSource() == View3){
            new ViewTeachers();
        }
        else if(ae.getSource() == View4){
            new ViewCourses();
        }
        else if(ae.getSource() == Add2){
            new AdminSignup();
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
