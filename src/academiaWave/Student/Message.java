package academiaWave.Student;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import academiaWave.DBConnection;


public class Message extends JFrame implements ActionListener{
    JTextArea Message_Text_Area;
    JButton SendButton;
    JLabel ReceiverNameLabel, WriteMessageLbl;
    JScrollPane scroll;
    JPanel mainPanel;
    int to_ID;
    public Message(int from_ID, String toName, int to_ID){
        super("Message");
        setLayout(new BorderLayout());
        this.to_ID = to_ID;
        ReceiverNameLabel = new JLabel(toName);
        ReceiverNameLabel.setHorizontalAlignment(JLabel.CENTER);
        ReceiverNameLabel.setFont(new Font(Font.SERIF,Font.BOLD, 23));
        add(ReceiverNameLabel, BorderLayout.NORTH);
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        
        WriteMessageLbl = new JLabel("Write Message");
        WriteMessageLbl.setHorizontalAlignment(JLabel.LEFT);
        WriteMessageLbl.setFont(new Font(Font.DIALOG,Font.PLAIN, 12));
        mainPanel.add(WriteMessageLbl, BorderLayout.NORTH);
        
        Message_Text_Area = new JTextArea();
        Message_Text_Area.setLineWrap(true);
        Message_Text_Area.setWrapStyleWord(true);
        Message_Text_Area.setFont(new Font(Font.SERIF,Font.PLAIN, 18));
        Message_Text_Area.setToolTipText( "Write Message Here." );
        scroll = new JScrollPane(Message_Text_Area);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        mainPanel.add(scroll, BorderLayout.CENTER);
        
        SendButton = new JButton("Send");
        SendButton.setPreferredSize(new Dimension(0, 50));
        SendButton.addActionListener(this);
        add(SendButton, BorderLayout.SOUTH);
        
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,320);
        setLocation(515,300);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == SendButton){
            String messageStr = Message_Text_Area.getText();
            java.util.Date dt = new java.util.Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String time_Stamp = dateFormat.format(dt);
            try{
                DBConnection c1 = new DBConnection();

                String q = "INSERT INTO Messages (message, time_Stamp, User_ID, toUser_ID) "
                        + "Values ('" + messageStr +"', '" + time_Stamp +"', '" + StudentLogin.currentStudentID +"',"
                        + " '"+ to_ID +"')";
                int x = c1.s.executeUpdate(q);
               String q1 = "select Max(Message_ID) As Message_ID From Messages";          
                ResultSet rs1 = c1.s.executeQuery(q1);
                rs1.next();
                int Message_ID = rs1.getInt("Message_ID");
                String q2 = "INSERT INTO MessageUsers (User_ID, Message_ID) "
                        + "Values ('" + StudentLogin.currentStudentID +"', '"+ Message_ID +"' )"; 
                int x2 = c1.s.executeUpdate(q2);
                String q3 = "INSERT INTO MessageUsers (User_ID, Message_ID) "
                        + "Values ('" + to_ID +"', '"+ Message_ID +"' )"; 
                int x3 = c1.s.executeUpdate(q3);
                
                if(x == 0 || x2 == 0 || x3 == 0){
                    JOptionPane.showMessageDialog(null, "Some Error Occured!");
                }else{
                    JOptionPane.showMessageDialog(null, "Message Sent");
                    dispose();
                }
        }
            catch(Exception exception){
                exception.printStackTrace();
            }
        }
    }
}
