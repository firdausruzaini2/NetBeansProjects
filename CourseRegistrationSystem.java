package course;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CourseRegistrationSystem {

    ArrayList<Detail> courselist = new ArrayList<Detail>();
    String header[] = new String[]{"ID", "name", "synopsis", "course" ,"credit", "school"};
    DefaultTableModel dtm = new DefaultTableModel(header, 1);

    CourseRegistrationSystem() {
        JFrame frame = new JFrame("Course Registration System");
        frame.setSize(640, 600);

        JLabel jlabel1 = new JLabel("ID              :");
        jlabel1.setBounds(30, 10, 70, 20);
        frame.add(jlabel1);

        JLabel jlabel2 = new JLabel("Name       :");
        jlabel2.setBounds(30, 32, 70, 20);
        frame.add(jlabel2);

        JLabel jlabel3 = new JLabel("Synopsis :");
        jlabel3.setBounds(30, 52, 70, 20);
        frame.add(jlabel3);
        
        JLabel jlabel4 = new JLabel("Course     :");
        jlabel4.setBounds(30, 158, 70, 20);
        frame.add(jlabel4);

        JLabel jlabel5 = new JLabel("Credit       :");
        jlabel5.setBounds(30, 180, 70, 20);
        frame.add(jlabel5);
        
        JLabel jlabel6 = new JLabel("School     :");
        jlabel6.setBounds(30, 203, 70, 20);
        frame.add(jlabel6);

        JTextField jtfiD = new JTextField();
        jtfiD.setBounds(90, 10, 100, 20);
        frame.add(jtfiD);

        JTextField jtfName = new JTextField();
        jtfName.setBounds(90, 32, 400, 20);
        frame.add(jtfName);

        JTextArea jtfSynopsis = new JTextArea();
        jtfSynopsis.setBounds(90, 54, 486, 100);
        frame.add(jtfSynopsis);
        
        JTextField jtfCourse = new JTextField();
        jtfCourse.setBounds(90, 158, 350, 20);
        frame.add(jtfCourse);

        JTextField jtfCredit = new JTextField();
        jtfCredit.setBounds(90, 180, 50, 20);
        frame.add(jtfCredit);

        JTextField jtfSchool = new JTextField();
        jtfSchool.setBounds(90, 203, 50, 20);
        frame.add(jtfSchool);

        JButton jbuttonAdd = new JButton("ADD");
        jbuttonAdd.setBounds(90, 250, 90, 20);
        frame.add(jbuttonAdd);

        JButton jbuttondelete = new JButton("DELETE");
        jbuttondelete.setBounds(200, 250, 90, 20);
        frame.add(jbuttondelete);

        JButton jbuttonsearch = new JButton("SEARCH");
        jbuttonsearch.setBounds(310, 250, 90, 20);
        frame.add(jbuttonsearch);

        JButton jbuttonupdate = new JButton("UPDATE");
        jbuttonupdate.setBounds(420, 250, 90, 20);
        frame.add(jbuttonupdate);

        JButton jbuttonexit = new JButton("EXIT");
        jbuttonexit.setBounds(500, 500, 90, 20);
        frame.add(jbuttonexit);

        JTable jtable = new JTable();
        jtable.setBounds(10, 300, 550, 150);
        frame.add(jtable);
        jtable.setModel(dtm);
        JScrollPane scrollPane = new JScrollPane(jtable);
        scrollPane.setBounds(30, 300, 550, 150);
        new JScrollPane(scrollPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        frame.add(scrollPane);
        jtable.getColumnModel().getColumn(0).setPreferredWidth(80);
        jtable.getColumnModel().getColumn(1).setPreferredWidth(400);
        jtable.getColumnModel().getColumn(2).setPreferredWidth(486);
        jtable.getColumnModel().getColumn(3).setPreferredWidth(350);
        jtable.getColumnModel().getColumn(4).setPreferredWidth(50);
        jtable.getColumnModel().getColumn(5).setPreferredWidth(50);

        jbuttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String iD = jtfiD.getText();
                String name = jtfName.getText();
                String synopsis = jtfSynopsis.getText();
                String course = jtfCourse.getText();
                String credit = jtfCredit.getText();
                String school = jtfSchool.getText();

                if (name.length() < 3) {
                    JOptionPane.showMessageDialog(frame, "Name should contain more than 3 char!");
                    return;
                }
                if (!isInteger(iD)) {
                    JOptionPane.showMessageDialog(frame, "ID should only contain integer!!!");
                    return;
                }
                if (synopsis.length() < 3) {
                    JOptionPane.showMessageDialog(frame, "Synopsis should contain string!!!");
                    return;
                }
                if (course.length() < 3) {
                    JOptionPane.showMessageDialog(frame, "Course should contain string!!!");
                    return;
                }
                if (!isInteger(credit)) {
                    JOptionPane.showMessageDialog(frame, "Credit should only 1 integer!!!");
                    return;
                }
                if (school.length() < 3) {
                    JOptionPane.showMessageDialog(frame, "Name should contain more than 3 string!");
                    return;
                }

                Detail detail = new Detail(iD, name, synopsis, course, credit, school);
                courselist.add(detail);
                writeData();
            }

        });

        jbuttondelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String iD = JOptionPane.showInputDialog("ID report to delete?");
                if (iD != null) {
                    System.out.println("not null");
                    for (int i = 0; i < courselist.size(); i++) {
                        if (courselist.get(i).getID().equalsIgnoreCase(iD)) {
                            courselist.remove(i);
                        }
                    }
                    writeData();
                }
            }
        });

        jbuttonsearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String iD = JOptionPane.showInputDialog("Enter ID Number?");
                if (iD != null) {
                    for (int i = 0; i < courselist.size(); i++) {
                        if (courselist.get(i).getID().equalsIgnoreCase(iD)) {
                            JOptionPane.showMessageDialog(frame, "Found!!!");
                            jtfiD.setText(courselist.get(i).getID());
                            jtfName.setText(courselist.get(i).getName());
                            jtfSynopsis.setText(courselist.get(i).getSynopsis());
                            jtfCourse.setText(courselist.get(i).getCourse());
                            jtfCredit.setText(courselist.get(i).getCredit());
                            jtfSchool.setText(courselist.get(i).getSchool());
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "Not Found!!!");
                }
            }
        });

        jbuttonupdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String iD = jtfiD.getText();
                String name = jtfName.getText();
                String synopsis = jtfSynopsis.getText();
                String credit = jtfCredit.getText();
                String school = jtfSchool.getText();

                if (iD != null) {
                    for (int i = 0; i < courselist.size(); i++) {
                        if (courselist.get(i).getID().equalsIgnoreCase(iD)) {
                            courselist.get(i).setSynopsis(synopsis);;
                            courselist.get(i).setSchool(school);
                            JOptionPane.showMessageDialog(frame, "Updated!!!");
                        }
                    }
                }
                writeData();
            }

            private void writeData() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        jbuttonexit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.setVisible(false);
                frame.dispose();
            }
        });

        readData();
        jtable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = jtable.getSelectedRow();
                jtfiD.setText(dtm.getValueAt(row, 0).toString());
                jtfName.setText(dtm.getValueAt(row, 1).toString());
                jtfSynopsis.setText(dtm.getValueAt(row, 2).toString());
                jtfCourse.setText(dtm.getValueAt(row, 2).toString());
                jtfCredit.setText(dtm.getValueAt(row, 3).toString());
                jtfSchool.setText(dtm.getValueAt(row, 4).toString());
            }
        });
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    public static void main(String[] args) {
        // TODO code application logic here
        new Login();
        JFrame frame = new JFrame("Login");
        frame.setSize(300, 150);
        frame.setLayout(null);

        JLabel label = new JLabel("Username");
        label.setBounds(10, 10, 70, 20);
        JTextField jtextfield1 = new JTextField();
        jtextfield1.setBounds(100, 10, 170, 20);
        JLabel labe12 = new JLabel("Password");
        labe12.setBounds(10, 30, 70, 20);
        JTextField jtextfield2 = new JTextField();
        jtextfield2.setBounds(100, 30, 170, 20);
        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(100, 60, 170, 20);
        frame.add(label);
        frame.add(jtextfield1);
        frame.add(labe12);
        frame.add(jtextfield2);
        frame.add(btnLogin);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String name = jtextfield1.getText();
                String pass = jtextfield2.getText();
                if (name.equals("firdaus") && pass.equals("123456")) {
                    new CourseRegistrationSystem();
                    frame.setVisible(false);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect login data");
                }
            }
        });

    }

    private void writeData() {
        try (FileWriter f = new FileWriter("data.txt")) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < courselist.size(); i++) {
                sb.append(courselist.get(i).getID() + "," + courselist.get(i).getName() + "," + courselist.get(i).getSynopsis() + "," + courselist.get(i).getCourse() + ","
                         + courselist.get(i).getCredit() + "," + courselist.get(i).getSchool() + ",");
            }
            f.write(sb.toString());
            f.close();
        } catch (IOException e) {
            return;
        }
        dtm.setRowCount(0);
        for (int i = 0; i < courselist.size(); i++) {
            Object[] objs = {courselist.get(i).getID(),courselist.get(i).getName(),
                    courselist.get(i).getSynopsis(), courselist.get(i).getCourse(), courselist.get(i).getCredit(), courselist.get(i).getSchool()};
            dtm.addRow(objs);
        }
    } 
    
    void readData() {
        try {
            File file = new File("data.txt");
            file.createNewFile();
            FileReader f = new FileReader("data.txt");
            StringBuffer sb = new StringBuffer();
            while (f.ready()) {
                char c = (char) f.read();
                if (c == '-') {
                    System.out.println(sb);
                    String courseArray[] = sb.toString().split(",");
                    Detail detail = new Detail(courseArray[0], courseArray[1], courseArray[2], courseArray[3], courseArray[4], courseArray[5]);
                    courselist.add(detail);
                    sb = new StringBuffer();
                } else {
                    sb.append(c);
                }
            }
            dtm.setRowCount(0);
            for (int i = 0; i < courselist.size(); i++) {
                Object[] objs = {courselist.get(i).getID(),courselist.get(i).getName(),
                    courselist.get(i).getSynopsis(), courselist.get(i).getCourse(), courselist.get(i).getCredit(), courselist.get(i).getSchool()};
                dtm.addRow(objs);
            }
        } catch (IOException e) {
        }
    }

    public boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

   
}
