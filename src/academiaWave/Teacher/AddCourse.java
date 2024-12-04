package academiaWave.Teacher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import academiaWave.DBConnection;
import academiaWave.Teacher.AddCourse;
import academiaWave.Teacher.TeacherLogin;

public class AddCourse extends JFrame implements ActionListener{
    JLabel title, Subject,Course, Description, Content;
    JComboBox Subjects;
    JTextArea course_Details, courseContent;
    JButton Add;
    JPanel middlePanel;
    JTextField Name;
    String SubjectsData[];
    JScrollPane scroll, scroll2;
    public AddCourse(){
        super("Add Course");
        setLayout(new BorderLayout());
        
        title = new JLabel("Add Course", JLabel.CENTER);
        title.setFont(title.getFont().deriveFont (22.0f));
        title.setBackground(Color.LIGHT_GRAY);
        title.setForeground(Color.BLACK);
        title.setOpaque(true);
        add(title, BorderLayout.NORTH);
        
        middlePanel = new JPanel();
        middlePanel.setLayout(null);
        add(middlePanel, BorderLayout.CENTER);
        
        Subject = new JLabel("Select Subject");
        Subject.setFont(new Font(Font.SERIF,Font.BOLD, 16));
        Subject.setBounds(80, 30, 120, 28);
        Subject.setHorizontalAlignment(JLabel.LEFT);
        middlePanel.add(Subject);
        
        this.getSubjects();
        Subjects = new JComboBox(SubjectsData);
        Subjects.setSelectedIndex(-1);
        Subjects.setBounds(200, 30, 140, 28);
        Subjects.addActionListener(this);
        middlePanel.add(Subjects);
        
        
        Course = new JLabel("Course Name");
        Course.setFont(new Font(Font.SERIF,Font.BOLD, 16));
        Course.setBounds(80, 75, 140, 28);
        Course.setHorizontalAlignment(JLabel.LEFT);
        middlePanel.add(Course);
        
        Name = new JTextField();
        Name.setFont(new Font(Font.SERIF,Font.BOLD, 16));
        Name.setBounds(200, 75, 120, 28);
        Name.setHorizontalAlignment(JLabel.CENTER);
        middlePanel.add(Name);
        
        
        Description = new JLabel("Course Description");
        Description.setFont(new Font(Font.SERIF,Font.BOLD, 16));
        Description.setBounds(80, 125, 140, 28);
        Description.setHorizontalAlignment(JLabel.LEFT);
        middlePanel.add(Description);
        
        course_Details = new JTextArea();
        course_Details.setLineWrap(true);
        course_Details.setWrapStyleWord(true);
        scroll = new JScrollPane(course_Details);
        scroll.setBounds(150, 155, 460, 100);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        course_Details.setEditable(true);
        middlePanel.add(scroll);
        
        
        Content = new JLabel("Course Content");
        Content.setFont(new Font(Font.SERIF,Font.BOLD, 16));
        Content.setBounds(80, 260, 140, 28);
        Content.setHorizontalAlignment(JLabel.LEFT);
        middlePanel.add(Content);
        
        courseContent = new JTextArea();
        courseContent.setLineWrap(true);
        courseContent.setWrapStyleWord(true);
        scroll2 = new JScrollPane(courseContent);
        scroll2.setBounds(10, 290, 685, 150);
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        courseContent.setEditable(true);
        middlePanel.add(scroll2);
       

        Add = new JButton("Add");
        Add.setFont(new Font(Font.SERIF,Font.BOLD, 15));
        Add.setHorizontalAlignment(JButton.CENTER);
        Add.addActionListener(this);
        add(Add, BorderLayout.SOUTH);
        

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(720,570);
        setLocation(375,145);
        setVisible(true);
    }
    private void getSubjects(){
        try{
            DBConnection c1 = new DBConnection();

            String q = "select * from Subjects";

            ResultSet rs = c1.s.executeQuery(q);
            int rowCount = 0;
            while(rs.next())
                rowCount++;
            SubjectsData = new String[rowCount];
            rs.beforeFirst();
            int i=0;
            while(rs.next()){
                SubjectsData[i] = rs.getString("Name");
                i++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == Add){
            String subjectStr = Subjects.getSelectedItem().toString();
            String courseNameStr = Name.getText();
            String courseDescriptionStr = course_Details.getText();
            String courseContentStr = courseContent.getText();
            try{
                DBConnection c1 = new DBConnection();
                String q1  = "Select Subject_ID From Subjects  Where Name = '"+ subjectStr +"'";
                ResultSet rs = c1.s.executeQuery(q1); 
                int subject_ID;
                rs.next();
                subject_ID = rs.getInt("Subject_ID");
                
                String q = "INSERT INTO Courses (Name, Description, Content, teacherID, Subject_ID) "
                        + "Values ('" + courseNameStr +"', '" + courseDescriptionStr +"', '" + courseContentStr +"', "
                        + TeacherLogin.currentTeacherID +", " + subject_ID + ")";

                int x = c1.s.executeUpdate(q);
                if(x == 0){
                    JOptionPane.showMessageDialog(null, "Some Error Occured");
                }else{
                    JOptionPane.showMessageDialog(null, "Course Added!");
                    dispose();
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "This Course is Already being Offered By other Teacher!");
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        new AddCourse();
    }
 
}
