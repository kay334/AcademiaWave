package academiaWave.Teacher;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import academiaWave.DBConnection;
import academiaWave.Teacher.TeacherLogin;
import academiaWave.Teacher.TeacherSignup;

public class TeacherSignup extends JFrame implements ActionListener, FocusListener{
    JPanel contentPane;
    JTextField First_Name, Last_Name, Email, User_name;
    JPasswordField Password;
    JButton Register, Upload;
    JRadioButton Male, Female;
    ButtonGroup RadioButtons;
    JLabel First_Name_Validation, Last_Name_Validation, Email_Validation, User_Name_Validation, Password_Validation, ProfilePic;
    FileInputStream fis = null;
    File f = null;
    public TeacherSignup(){
        super("Teacher SignUp");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1014,515);
        setLocation(210,110);

        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

                
        ProfilePic = new JLabel();
        ProfilePic.setIcon(new ImageIcon (ClassLoader.getSystemResource("javasemesterproject/icons/uploadPicIcon.png")));
        ProfilePic.setBounds(456, 18, 96, 96);
        contentPane.add(ProfilePic);
        
        Upload = new JButton("Upload");
        Upload.setBounds(470, 120, 75, 23);
        Upload.addActionListener(this);
        contentPane.add(Upload);

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

        First_Name_Validation = new JLabel();
        First_Name = new JTextField();
        First_Name.setFont(new Font("Tahoma", Font.PLAIN, 32));
        First_Name.setBounds(214, 151, 228, 50);
        First_Name.addFocusListener(this);
        First_Name_Validation.setBounds(214, 205, 150, 10);
        contentPane.add(First_Name_Validation);
        contentPane.add(First_Name);
        First_Name.setColumns(10);

        Last_Name_Validation = new JLabel();
        Last_Name = new JTextField();
        Last_Name.setFont(new Font("Tahoma", Font.PLAIN, 32));
        Last_Name.setBounds(214, 235, 228, 50);
        Last_Name.addFocusListener(this);
        Last_Name_Validation.setBounds(214, 289, 150, 10);
        contentPane.add(Last_Name_Validation);
        contentPane.add(Last_Name);
        Last_Name.setColumns(10);

        Email_Validation = new JLabel();
        Email = new JTextField();
        Email.setFont(new Font("Tahoma", Font.PLAIN, 32));
        Email.setBounds(214, 320, 228, 50);
        Email.addFocusListener(this);
        Email_Validation.setBounds(214, 374, 150, 10);
        contentPane.add(Email_Validation);
        contentPane.add(Email);
        Email.setColumns(10);

        User_Name_Validation = new JLabel();
        User_name = new JTextField();
        User_name.setFont(new Font("Tahoma", Font.PLAIN, 32));
        User_name.setBounds(707, 151, 228, 50);
        User_name.addFocusListener(this);
        User_Name_Validation.setBounds(707, 205, 150, 10);
        contentPane.add(User_Name_Validation);
        contentPane.add(User_name);
        User_name.setColumns(10);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUsername.setBounds(542, 159, 99, 29);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPassword.setBounds(542, 245, 99, 24);
        contentPane.add(lblPassword);

        Password = new JPasswordField();
        Password.setFont(new Font("Tahoma", Font.PLAIN, 32));
        Password.setBounds(707, 235, 228, 50);
        contentPane.add(Password);
        
        JLabel genderLbl = new JLabel("Gender");
        genderLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
        genderLbl.setBounds(542, 331, 99, 24);
        contentPane.add(genderLbl);
        
        Male = new JRadioButton("Male");
        Female = new JRadioButton("Female");
        RadioButtons = new ButtonGroup();

        Male.setFont(new Font("Tahoma", Font.PLAIN, 25));
        Male.setBounds(707, 321, 110, 50);
        Male.setActionCommand("Male");
        contentPane.add(Male);
        
        Female.setFont(new Font("Tahoma", Font.PLAIN, 25));
        Female.setBounds(825, 321, 120, 50);
        Female.setActionCommand("Female");
        contentPane.add(Female);
        
        RadioButtons.add(Male);
        RadioButtons.add(Female);

        Register = new JButton("Register");
        Register.setBounds(410, 400, 228, 60);
        Register.addActionListener((ActionListener) this);
        contentPane.add(Register);
        
        setVisible(true);
    }
    // This code use to resize image to fit lable
    public ImageIcon resizeImage(String imagePath){
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            Logger.getLogger(TeacherSignup.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        if(ae.getSource() == Upload){
            String fname = null;
            JFileChooser fchoser=new JFileChooser();
            fchoser.showOpenDialog(null);
            f = fchoser.getSelectedFile();
            fname=f.getAbsolutePath();
            ImageIcon micon=new ImageIcon(fname);
            
            try {
                File image=new File(fname);
                fis = new FileInputStream(image);
                ProfilePic.setIcon(resizeImage(fname));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        else{
            String firstName = First_Name.getText();
            String lastName = Last_Name.getText();
            String emailId = Email.getText();
            String userName = User_name.getText();
            String password = String.valueOf(Password.getPassword());
            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
            String msg = "" + firstName;
            String genderStr = "";
            if(RadioButtons.getSelection() != null)
                genderStr = RadioButtons.getSelection().getActionCommand();

            if(First_Name_Validation.getText().isEmpty() && 
            		Last_Name_Validation.getText().isEmpty() && 
            		Email_Validation.getText().isEmpty() && 
            		User_Name_Validation.getText().isEmpty()){
                if(firstName.isEmpty() || lastName.isEmpty() || emailId.isEmpty() || userName.isEmpty() || password.isEmpty()
                    || genderStr.isEmpty() || this.f == null || this.fis == null){
                JOptionPane.showMessageDialog(null, "Please Fill All Information !");
                }
                else{
                    try{
                        DBConnection c1 = new DBConnection();

                        PreparedStatement ps = c1.c.prepareStatement("Insert into Teacher (fname, lname, Email_ID, username, password, Registration_Date, Gender, picture) "
                                + "values(?,?,?,?,?,?,?,?)");
                        ps.setString(1, firstName);
                        ps.setString(2, lastName);
                        ps.setString(3, emailId);
                        ps.setString(4, userName);
                        ps.setString(5, password);
                        ps.setDate(6, sqlDate);
                        ps.setString(7, genderStr);
                        ps.setBinaryStream(8,(InputStream)fis,(int)f.length());
                        int x =  ps.executeUpdate();
                        if(x == 0){
                            JOptionPane.showMessageDialog(null, "This is User already exist");
                        }else{
                            JOptionPane.showMessageDialog(null, "Welcome, "+ msg + " Your account is successfully created."
                                    + "You can Now Log into your Account.");
                            setVisible(false);
                            new TeacherLogin();
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Please Fill accurate Info !");
            }
        }
    }
    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource() == First_Name)
        	First_Name_Validation.setText("");
        else if(e.getSource() == Last_Name)
        	Last_Name_Validation.setText("");
        else if(e.getSource() == Email)
        	Email_Validation.setText("");
        else if(e.getSource() == User_name)
        	User_Name_Validation.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource() == First_Name){
            String fName = First_Name.getText();
            if(fName.isEmpty()){
            	First_Name_Validation.setText("Enter First Name");
            }
            else{
                boolean valid = fName.matches("[A-Z][a-z]*");
                if(!valid)
                	First_Name_Validation.setText("Invalid First Name");
                else
                	First_Name_Validation.setText("");
            }
        }
        else if(e.getSource() == Last_Name){
            String LName = Last_Name.getText();
            if(LName.isEmpty()){
            	Last_Name_Validation.setText("Enter Last Name");
            }
            else{
                boolean valid = LName.matches("[A-Z][a-z]*");
                if(!valid)
                	Last_Name_Validation.setText("Invalid Last Name");
                else
                	Last_Name_Validation.setText("");
            }
        }
        else if(e.getSource() == Email){
            String emailTxt = Email.getText();
            if(emailTxt.isEmpty()){
            	Email_Validation.setText("Enter Email");
            }
            else{
                boolean valid = emailTxt.matches("[\\w]+@[\\w]+\\.[a-zA-Z]{2,3}");
                if(!valid)
                	Email_Validation.setText("Invalid Email");
                else
                	Email_Validation.setText("");
            }
        }
        else if(e.getSource() == User_name){
            String usernameTxt = User_name.getText();
            if(usernameTxt.isEmpty()){
            	User_Name_Validation.setText("Enter UserName");
            }
            else{
                boolean valid = usernameTxt.matches("\\b[a-zA-Z][a-zA-Z0-9\\-._]{3,}\\b");
                if(!valid)
                	User_Name_Validation.setText("Invalid UserName");
                else
                	User_Name_Validation.setText("");
            }
        }
    }
    public static void main(String[] args) {
        new TeacherSignup();
    }
}
