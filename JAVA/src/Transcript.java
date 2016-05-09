import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

public class Transcript extends JFrame{
	private JTextArea txa;
	private static final long serialVersionUID = 59437871248273125L;
	
	private static final JLabel courseNumLabel = new JLabel("Enter Course Number");
	private static final JTextField courseNumInput = new JTextField();
	
	/**
	 * 按钮
	 */
	private static final JButton detailButton = new JButton("showdetail");
	private static final JButton gobackButton = new JButton("StudentMenu");
	
	public Transcript(int userName, String password) throws SQLException {
		
		JLabel labelCourseGrade= new JLabel("Course & Grade");
		setSize(new Dimension(500, 500));
		setTitle("Transcript");
		add(labelCourseGrade);
		setLayout(null);
		txa = new JTextArea(50, 20);
		initUI();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		db3 db = new db3(userName, password);
		//输出流重定向
		ConsolePrintStream cps = new ConsolePrintStream(System.out, txa);
		System.setOut(cps);
		db.showTranscript();
		
		
		detailButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				try {
					String courseNumber = courseNumInput.getText();
					
					ConsolePrintStream cps = new ConsolePrintStream(System.out, txa);
					System.setOut(cps);
					db.showCourseDetail(courseNumber);
					
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
		detailButton.setBounds(300, 10, 100, 21);
		gobackButton.setBounds(320, 340, 150, 21);
//		logoutButton.setBounds(350, 400, 100, 21);
//		exitButton.setBounds(290, 100, 60, 21);
		
		add(courseNumLabel);
		add(txa);
		add(courseNumInput);
		add(detailButton);
		add(gobackButton);
	}
	
	public static void main(String[] args) throws SQLException {
		new Transcript(3213, "dinner");
	}
}
