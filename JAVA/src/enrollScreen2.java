import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
	
public class enrollScreen2 extends JFrame{

	private JTextArea txa;
	private static final long serialVersionUID = 59437871248273125L;
		
	private static final JLabel enterCourseNum = new JLabel("Enter Course Number");
	private static final JTextField courseNumInput = new JTextField();
		
	private static final JLabel enterCourseSemester = new JLabel("Enter Course semester");
	private static final JTextField courseSemInput = new JTextField();
		
	private static final JLabel enterCourseYear = new JLabel("Enter Course Year");
	private static final JTextField courseYearInput = new JTextField();
		
		
	/**
	 * 按钮
	 */
	private static final JButton enrollButton = new JButton("enroll");
	private static final JButton gobackButton = new JButton("StudentMenu");
		
	public enrollScreen2(int userName, String password) throws SQLException {
			
		JLabel labelCourseOffering= new JLabel("Courses offering");
		setSize(new Dimension(500, 500));
		setTitle("Enrollment");
		add(labelCourseOffering);
		setLayout(null);
		txa = new JTextArea(80, 80);
		initUI();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
			
		db3 db = new db3(userName, password);
		//输出流重定向
		ConsolePrintStream cps = new ConsolePrintStream(System.out, txa);
		System.setOut(cps);
		db.courseCouleEnroll();
//		if (result != 1) {
//			JOptionPane.showMessageDialog(enrollScreen2.this, "Enter again!");
//		}
//caicai			
		enrollButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				
				try {
					String courseNumber = courseNumInput.getText();
					String courseSem = courseSemInput.getText();
					int courseYear = Integer.parseInt(courseYearInput.getText());
					
					//输出流重定向
					ConsolePrintStream cps = new ConsolePrintStream(System.out, txa);
					System.setOut(cps);
					int r = db.enroll(userName, courseNumber, courseSem, courseYear);
					if (r == 1) {
						JOptionPane.showMessageDialog(enrollScreen2.this, "Enroll Successfully!");
					} else {
						JOptionPane.showMessageDialog(enrollScreen2.this, "Enter again!");
					}
					db.courseCouleEnroll();
					
					
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
							
							setVisible(false);
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
			txa.setBounds(10, 100, 380, 380);
			enterCourseNum.setBounds(10, 10, 150, 21);
			courseNumInput.setBounds(150, 10, 150, 21);
			enterCourseSemester.setBounds(10, 40, 150, 21);
			courseSemInput.setBounds(150, 40, 150, 21);
			enterCourseYear.setBounds(10, 70, 150, 21);
			courseYearInput.setBounds(150, 70, 150, 21);
			enrollButton.setBounds(350, 40, 120, 21);
			gobackButton.setBounds(350, 70, 120, 21);
			
			add(enterCourseNum);
			add(txa);
			add(courseNumInput);
			add(enterCourseNum);
			add(gobackButton);
			add(enterCourseSemester);
			add(courseSemInput);
			add(enterCourseYear);
			add(courseYearInput);
			add(enrollButton);
		}
		
		public static void main(String[] args) throws SQLException {
			new enrollScreen2(5123, "butterflY");
		}
	}



