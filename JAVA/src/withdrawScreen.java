import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

public class withdrawScreen extends JFrame{
	private JTextArea txa;
	private static final long serialVersionUID = 59437871248273125L;
	
	private static final JLabel courseNumLabel = new JLabel("Enter Course Number");
	private static final JTextField courseNumInput = new JTextField();
	
	
	private static final JLabel semLabel = new JLabel("Enter semester ");
	private static final JTextField semInput = new JTextField();
	
	private static final JLabel yearLabel = new JLabel("Enter year");
	private static final JTextField yearInput = new JTextField();
	
	/**
	 * 按钮
	 */
	private static final JButton withdrawButton = new JButton("withdraw");
	private static final JButton gobackButton = new JButton("StudentMenu");
	
	public withdrawScreen(int userName, String password) throws SQLException {
		
		JLabel labelCourseGrade= new JLabel("Courses could withdraw");
		labelCourseGrade.setBounds(200, 70, 200, 21);
		setSize(new Dimension(500, 500));
		setTitle("Withdraw");
		add(labelCourseGrade);
		setLayout(null);
		txa = new JTextArea(100, 100);
		initUI();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		db3 db = new db3(userName, password);
		//输出流重定向
		ConsolePrintStream cps = new ConsolePrintStream(System.out, txa);
		System.setOut(cps);
		db.courseCouldWithdraw(userName, password);
		
		
		withdrawButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					
					String courseNumber = courseNumInput.getText();
					if (courseNumber.equals(""))
					{
						JOptionPane.showMessageDialog(withdrawScreen.this, "courseNumber cannot be empty!");
						return;
					}
					String year = yearInput.getText();

					if (year.equals(""))
					{
						JOptionPane.showMessageDialog(withdrawScreen.this, "year cannot be empty!");
						return;
					}
					String semester = semInput.getText();
					if (semester.equals(""))
					{
						JOptionPane.showMessageDialog(withdrawScreen.this, "courseNumber cannot be empty!");
						return;
					}
					int result = db.withdraw(userName, password, courseNumber,Integer.parseInt(year),semester);
					
					if (result == 1) {
						JOptionPane.showMessageDialog(withdrawScreen.this, "Withdraw Successfully!");
						ConsolePrintStream cps2 = new ConsolePrintStream(System.out, txa);
						System.setOut(cps2);
						db.courseCouldWithdraw(userName, password);
					}	
					int r2 = db.withdrawTrigger();
					if (r2 == 1) {
						// 激发trigger
						JOptionPane.showMessageDialog(withdrawScreen.this, "Enrollnumber goes below 50% of Maxenrollment!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		gobackButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				try {
					dispose();
					new LoginSucces(userName, password);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
	}
	
	private void initUI()
	{
		// TODO Auto-generated method stub
		txa.setBounds(50, 100, 350, 200);
		courseNumLabel.setBounds(10, 10, 150, 21);
		courseNumInput.setBounds(140, 10, 100, 21);
		semLabel.setBounds(10, 30, 150, 21);
		semInput.setBounds(140, 30, 100, 21);
		yearLabel.setBounds(10, 50, 150, 21);
		yearInput.setBounds(140, 50, 100, 21);
		withdrawButton.setBounds(300, 10, 100, 21);
		gobackButton.setBounds(320, 340, 150, 21);
		
		
		add(courseNumLabel);
		add(txa);
		add(courseNumInput);
		add(withdrawButton);
		add(gobackButton);
		add(semLabel);
		add(semInput);
		add(yearLabel);
		add(yearInput);
	}
	
	public static void main(String[] args) throws SQLException {
		new withdrawScreen(3213, "lunch");
	}
}
