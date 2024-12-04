package academiaWave.Admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import academiaWave.DBConnection;
import academiaWave.Admin.AddSubject;
import academiaWave.Admin.AdminLogin;

public class AddSubject extends JFrame implements ActionListener{
    JLabel Tittle, subjectCbLbl;
    JButton addBtn;
    JPanel middlePanel;
    JTextField Name;
    public AddSubject(){
        super("Add Subject");
        setLayout(new BorderLayout());
        
        Tittle = new JLabel("Add Subject", JLabel.CENTER);
        Tittle.setFont(Tittle.getFont().deriveFont (22.0f));
        Tittle.setBackground(Color.LIGHT_GRAY);
        Tittle.setForeground(Color.BLACK);
        Tittle.setOpaque(true);
        add(Tittle, BorderLayout.NORTH);
        
        middlePanel = new JPanel();
        middlePanel.setLayout(null);
        add(middlePanel, BorderLayout.CENTER);
        
        subjectCbLbl = new JLabel("Subject Name");
        subjectCbLbl.setFont(new Font(Font.SERIF,Font.BOLD, 16));
        subjectCbLbl.setBounds(80, 50, 120, 28);
        subjectCbLbl.setHorizontalAlignment(JLabel.CENTER);
        middlePanel.add(subjectCbLbl);
        
                
        Name = new JTextField();
        Name.setBounds(200, 50, 140, 28);
        middlePanel.add(Name);
       

        addBtn = new JButton("Add");
        addBtn.setFont(new Font(Font.SERIF,Font.BOLD, 15));
        addBtn.setHorizontalAlignment(JButton.CENTER);
        addBtn.addActionListener(this);
        add(addBtn, BorderLayout.SOUTH);
        

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(500,250);
        setLocation(420,320);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == addBtn){
            String subject_Name = Name.getText();
                try{
                    DBConnection c1 = new DBConnection();

                    String q = "INSERT INTO Subjects (Name, Adminid) "
                            + "Values ('"+ subject_Name +"', '"+ AdminLogin.currentAdminID +"')";
                    
                    int x = c1.s.executeUpdate(q);
                    if(x == 0){
                        JOptionPane.showMessageDialog(null, " Subject already exists ");
                    }else{
                        JOptionPane.showMessageDialog(null, " Subject Added Successfully! ");
                        dispose();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
        }
    }
    public static void main(String[] args) {
        new AddSubject();
    }
 
}