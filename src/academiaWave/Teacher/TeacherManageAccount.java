package academiaWave.Teacher;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import academiaWave.DBConnection;
import academiaWave.Teacher.TeacherChangeEmail;
import academiaWave.Teacher.TeacherChangeName;
import academiaWave.Teacher.TeacherChangePassword;
import academiaWave.Teacher.TeacherLogin;
import academiaWave.Teacher.TeacherManageAccount;

public class TeacherManageAccount extends JFrame implements ActionListener{
    JPanel panel;
    JLabel title;
    JButton ChangePass, ChangeName, ChangeEmail, ChangeProfile, Exit;
    FileInputStream fis = null;
    File f = null;
    public TeacherManageAccount(){
        super("Manage Teacher Account");
        
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        title = new JLabel("<html><h1><strong>Manage Account</strong></h1></html>");
        panel.add(title, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttons = new JPanel(new GridBagLayout());
        ChangePass = new JButton("Change Password");
        ChangePass.addActionListener((ActionListener) this);
        ChangeName = new JButton("Change Name");
        ChangeName.addActionListener((ActionListener) this);
        ChangeEmail = new JButton("Change Email");
        ChangeEmail.addActionListener((ActionListener) this);
        ChangeProfile = new JButton("Change Profile");
        ChangeProfile.addActionListener((ActionListener) this);
        Exit = new JButton("Exit"); 
        Exit.addActionListener((ActionListener) this);
        buttons.add(ChangePass, gbc);
        buttons.add(ChangeName, gbc);
        buttons.add(ChangeEmail, gbc);
        buttons.add(ChangeProfile, gbc);
        buttons.add(Exit, gbc);

        gbc.weighty = 1;
        panel.add(buttons, gbc);
        
        add(panel);
        
        setSize(420,320);
        setLocation(460,280);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == ChangePass){
            new TeacherChangePassword();
        }
        else if(ae.getSource() == ChangeName){
            new TeacherChangeName();
        }
        else if(ae.getSource() == ChangeEmail){
            new TeacherChangeEmail();
        }
        else if(ae.getSource() == ChangeProfile){
            String fname = null;
            JFileChooser fchoser=new JFileChooser();
            fchoser.showOpenDialog(null);
            f = fchoser.getSelectedFile();
            fname = f.getAbsolutePath();
            ImageIcon micon=new ImageIcon(fname);
            File image=new File(fname);
            try {
                fis = new FileInputStream(image);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TeacherManageAccount.class.getName()).log(Level.SEVERE, null, ex);
            }
  

            try {
                DBConnection c1 = new DBConnection();

                PreparedStatement ps = c1.c.prepareStatement("update Teacher SET picture =? Where teacherID =?");
                ps.setBinaryStream(1,(InputStream)fis,(int)f.length());
                ps.setInt(2, TeacherLogin.currentTeacherID);
                int x =  ps.executeUpdate();
                if(x == 0){
                   JOptionPane.showMessageDialog(null, "Got Some Error");
               }else{
                   JOptionPane.showMessageDialog(null, "Profile updated Successfully!");
                    dispose();
               }
                ps.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        else if(ae.getSource() == Exit){
            dispose();
        }
    }
    public static void main(String[] args) {
        new TeacherManageAccount();
    }
}
