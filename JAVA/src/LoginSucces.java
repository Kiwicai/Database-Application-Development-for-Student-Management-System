import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

	public class LoginSucces extends JFrame
	{
		private JTextArea txa;
		private static final long serialVersionUID = 59437871248273125L;
		
		/**
		 * 登陆按钮
		 */
		private static final JButton transcriptButton = new JButton("transcript");
		private static final JButton enrollButton = new JButton("enroll");
		private static final JButton withdrawButton = new JButton("withdraw");
		private static final JButton personalDetailButton = new JButton("personalDetail");
		private static final JButton logoutButton = new JButton("logout");
		private static final JLabel resultLable = new JLabel("Courses currently taking :");
		
		/**
		 * 构造方法
		 * @param userName
		 * @throws SQLException 
		 */
		public LoginSucces(int userName, String password) throws SQLException
		{
			setSize(new Dimension(500, 500));
			setTitle("StudentMenu");
			setLayout(null);
			
			initUI();
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			
			db3 db = new db3(userName, password);
			//输出流重定向
			ConsolePrintStream cps = new ConsolePrintStream(System.out, txa);
			System.setOut(cps);
			db.StudentMenu(userName, password);
			
			transcriptButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						dispose();
						new Transcript(userName, password);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			});
			
			enrollButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						setVisible(false);
						dispose();
						new enrollScreen2(userName, password);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			});
			
			withdrawButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						dispose();
						new withdrawScreen(userName, password);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			});
			
			personalDetailButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						dispose();
						new ShowPersonalInfo(userName, password);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			
			logoutButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					new LoginFrame();
				}
				
			});
			
		}
		
		private void initUI()
		{
			// TODO Auto-generated method stub
			resultLable.setBounds(150, 60, 300, 21);
			txa = new JTextArea(50, 20);
			txa.setBounds(50, 100, 350, 200);
			transcriptButton.setBounds(10, 10, 100, 21);
			enrollButton.setBounds(120, 10, 100, 21);
			withdrawButton.setBounds(230, 10, 100, 21);
			personalDetailButton.setBounds(340, 10, 100, 21);
			logoutButton.setBounds(350, 400, 100, 21);
//			exitButton.setBounds(290, 100, 60, 21);
			
			
			add(resultLable);
			add(txa);
			add(transcriptButton);
			add(enrollButton);
			add(withdrawButton);
			add(personalDetailButton);
			add(logoutButton);
//			add(exitButton);
		}
	public static void main(String[] args) throws SQLException
		{
//			new LoginSucces(3213, "LUNCH");
		}
	}

