package academiaWave.Student;

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
import javax.swing.ScrollPaneConstants;

import academiaWave.DBConnection;
import academiaWave.Student.EnrollCourse;
import academiaWave.Student.StudentLogin;

public class EnrollCourse extends JFrame implements ActionListener{
    JLabel title, Subject, Course, courseDescriptionLbl;
    JTextArea courseDescription;
    JComboBox SubjectsComboBox, coursesCb;
    JButton EnrollButton;
    JPanel middlePanel;
    JScrollPane scroll;
    public EnrollCourse(){
        super("Enroll Course");
        setLayout(new BorderLayout());
        
        title = new JLabel("Enroll Course", JLabel.CENTER);
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
        Subject.setHorizontalAlignment(JLabel.CENTER);
        middlePanel.add(Subject);

        SubjectsComboBox = new JComboBox(this.getSubjects());
        SubjectsComboBox.setSelectedIndex(-1);
        SubjectsComboBox.setBounds(200, 30, 140, 28);
        SubjectsComboBox.addActionListener(this);
        middlePanel.add(SubjectsComboBox);
        
        Course = new JLabel("Select Course");
        Course.setFont(new Font(Font.SERIF,Font.BOLD, 16));
        Course.setBounds(80, 80, 120, 28);
        Course.setHorizontalAlignment(JLabel.CENTER);
        middlePanel.add(Course);
        
        
        coursesCb = new JComboBox();
        coursesCb.setSelectedIndex(-1);
        coursesCb.setBounds(200, 80, 140, 28);
        coursesCb.addActionListener(this);
        middlePanel.add(coursesCb);
        
        
        courseDescriptionLbl = new JLabel("Course Description");
        courseDescriptionLbl.setFont(new Font(Font.SERIF,Font.BOLD, 16));
        courseDescriptionLbl.setBounds(80, 130, 140, 28);
        courseDescriptionLbl.setHorizontalAlignment(JLabel.CENTER);
        middlePanel.add(courseDescriptionLbl);
        
        courseDescription = new JTextArea();
        courseDescription.setLineWrap(true);
        courseDescription.setWrapStyleWord(true);
        scroll = new JScrollPane(courseDescription);
        scroll.setBounds(150, 160, 230, 100);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        courseDescription.setEditable(false);
        middlePanel.add(scroll);
       

        EnrollButton = new JButton("Enroll");
        EnrollButton.setFont(new Font(Font.SERIF,Font.BOLD, 15));
        EnrollButton.setHorizontalAlignment(JButton.CENTER);
        EnrollButton.addActionListener(this);
        add(EnrollButton, BorderLayout.SOUTH);
        

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(500,400);
        setLocation(420,320);
        setVisible(true);
    }
    private String[] getSubjects(){
        String[] subjectsData = null;
        try{
            DBConnection c1 = new DBConnection();

            String q = "select * from Subjects";

            ResultSet rs = c1.s.executeQuery(q);
            int rowCount = 0;
            while(rs.next())
                rowCount++;
            subjectsData = new String[rowCount];
            rs.beforeFirst();
            int i=0;
            while(rs.next()){
                subjectsData[i] = rs.getString("Name");
                i++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return subjectsData;
    }
        private void getSetCourses(int subjectID){
            String[] coursesData;
        try{
            DBConnection c1 = new DBConnection();
            String q = "select * from Courses Where Subject_ID = '"+ subjectID +"'";

            ResultSet rs = c1.s.executeQuery(q);
            int rowCount = 0;
            while(rs.next())
                rowCount++;
            coursesData = new String[rowCount];
            rs.beforeFirst();
            int i=0;
            while(rs.next()){
                coursesData[i] = rs.getString("Name");
                i++;
            }
            for(int j=0; j< coursesData.length ; j++)
                coursesCb.addItem(coursesData[j]);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == SubjectsComboBox){
            coursesCb.removeAllItems();
            courseDescription.setText(null);
            Object selected = SubjectsComboBox.getSelectedItem();
            String subjectName = selected.toString();
            try{
                DBConnection c1 = new DBConnection();
                String q1  = "Select Subject_ID From Subjects Where Name = '"+ subjectName +"'";
                ResultSet rs = c1.s.executeQuery(q1); 
                int subjectID;
                rs.next();
                subjectID = rs.getInt("Subject_ID");
                this.getSetCourses(subjectID);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource() == coursesCb){
            Object selectedMain = coursesCb.getSelectedItem();
            if(selectedMain == null){
                System.out.println("Null Value");
            }
            else{
                Object selected = coursesCb.getSelectedItem();
                String courseName = selected.toString();
                try{
                    DBConnection c1 = new DBConnection();
                    String q1  = "Select Description From Courses Where Name = '"+ courseName +"'";
                    ResultSet rs = c1.s.executeQuery(q1);
                    String course_Description;
                    rs.next();
                    course_Description = rs.getString("Description");
    //                courseDescriptionLbl.setVisible(true);
    //                scroll.setVisible(true);
                    courseDescription.setText(course_Description);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        else if(ae.getSource() == EnrollButton){
                String courseNameStr = coursesCb.getSelectedItem().toString();
                java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
                try{
                    DBConnection c1 = new DBConnection();
                    String q1  = "Select course_ID From Courses Where Name = '"+ courseNameStr +"'";
                    ResultSet rs = c1.s.executeQuery(q1); 
                    int course_ID;
                    rs.next();
                    course_ID = rs.getInt("course_ID");

                    String q = "INSERT INTO Enrollments (Enrollment_Date, course_ID, stdID) "
                            + "Values ('" + sqlDate +"', '" + course_ID +"', '" + StudentLogin.currentStudentID +"')";

                    int x = c1.s.executeUpdate(q);
                    if(x == 0){
                        JOptionPane.showMessageDialog(null, "Some Error Occured");
                    }else{
                        JOptionPane.showMessageDialog(null, "Course Enrollement Successfull!");
                        dispose();
                    }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "You are Already Enrolled in this Course!");
                    e.printStackTrace();
                }
        }
        }
        public static void main(String[] args) {
            new EnrollCourse();
        }

    }