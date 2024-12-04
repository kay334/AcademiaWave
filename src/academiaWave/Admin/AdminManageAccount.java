package academiaWave.Admin;


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
import academiaWave.Admin.AdminChangeEmail;
import academiaWave.Admin.AdminChangeName;
import academiaWave.Admin.AdminChangePassword;
import academiaWave.Admin.AdminLogin;
import academiaWave.Admin.AdminManageAccount;

public class AdminManageAccount extends JFrame implements ActionListener{
    JPanel panel;
    JLabel title;
    JButton Change_Password, ChangeName, ChangeEmail, ChangeProfile, Exit;
    FileInputStream fis = null;
    File f = null;
    public AdminManageAccount(){
        super("Manage Admin Account");
        
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
        Change_Password = new JButton("Change Password");
        Change_Password.addActionListener((ActionListener) this);
        ChangeName = new JButton("Change Name");
        ChangeName.addActionListener((ActionListener) this);
        ChangeEmail = new JButton("Change Email");
        ChangeEmail.addActionListener((ActionListener) this);
        ChangeProfile = new JButton("Change Profile");
        ChangeProfile.addActionListener((ActionListener) this);
        Exit = new JButton("Exit"); 
        Exit.addActionListener((ActionListener) this);
        buttons.add(Change_Password, gbc);
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
        if(ae.getSource() == Change_Password){
            new AdminChangePassword();
        }
        else if(ae.getSource() == ChangeName){
            new AdminChangeName();
        }
        else if(ae.getSource() == ChangeEmail){
            new AdminChangeEmail();
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
                Logger.getLogger(AdminManageAccount.class.getName()).log(Level.SEVERE, null, ex);
            }

            DBConnection c1 = new DBConnection();
            try {
                PreparedStatement ps = c1.c.prepareStatement("update Admin SET picture =? Where Adminid =?");
                ps.setBinaryStream(1,(InputStream)fis,(int)f.length());
                ps.setInt(2, AdminLogin.currentAdminID);
                int x =  ps.executeUpdate();
                if(x == 0){
                   JOptionPane.showMessageDialog(null, " Error ");
               }else{
                   JOptionPane.showMessageDialog(null, " Profile updated ");
                    dispose();
               }
                ps.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            finally{
                c1.Close();
            }
        }
        else if(ae.getSource() == Exit){
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new AdminManageAccount();
    }
}
