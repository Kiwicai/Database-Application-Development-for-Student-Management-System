import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ShowPersonalInfo extends JFrame{
	private JTextArea txa;
	String newPW = null;
	private static final long serialVersionUID = 59437871248273125L;
	
	private static final JLabel changePWLabel = new JLabel("Enter new password");
	private static final JTextField changePWInput = new JTextField();
	
	private static final JLabel changeADLabel = new JLabel("Enter new address");
	private static final JTextField changeADInput = new JTextField();
	
	
	/**
	 * 按钮
	 */
	private static final JButton changePWButton = new JButton("ChangePassword");
	private static final JButton changeADButton = new JButton("ChangeAddress");
	private static final JButton showPersonalInfoButton = new JButton("PersonalInfo");
	private static final JButton studenMenuButton = new JButton("StudentMenu");
	
	public ShowPersonalInfo(int userName, String password) throws SQLException {
		
		JLabel labelCourseGrade= new JLabel("Personal Information");
		setSize(new Dimension(500, 500));
		setTitle("Personal Information");
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
		db.showPersonInfo();
		
		changePWButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				try {
					
					newPW = changePWInput.getText();
					//用户名为空时提示用户输入
					if (newPW.equals(""))
					{
						JOptionPane.showMessageDialog(ShowPersonalInfo.this, "Password cannot be empty!");
						return;
					}
					ConsolePrintStream cps = new ConsolePrintStream(System.out, txa);
					System.setOut(cps);
					db.changePersonInfo_password(newPW);
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		showPersonalInfoButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				try {
					if (newPW == null) {
						dispose();
						new ShowPersonalInfo(userName, password);
					}
						
					else {
						dispose();
						new ShowPersonalInfo(userName, newPW);
					}
						
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		changeADButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				try {
					String newad = changeADInput.getText();
					if (newad.equals(""))
					{
						JOptionPane.showMessageDialog(ShowPersonalInfo.this, "Address cannot be empty!");
						return;
					}
					ConsolePrintStream cps = new ConsolePrintStream(System.out, txa);
					System.setOut(cps);
					db.changePersonInfo_address(newad);
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		studenMenuButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				try {
					if (newPW == null) {
						dispose();
						new LoginSucces(userName, password);
					}
						
					else {
						dispose();
						new LoginSucces(userName, newPW);
					}
				}
				catch (SQLException e1) {
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
		changePWLabel.setBounds(10, 10, 150, 21);
		changeADLabel.setBounds(10, 30, 150, 21);
		changePWInput.setBounds(130, 10, 200, 21);
		changeADInput.setBounds(130, 30, 200, 21);
		changePWButton.setBounds(320, 10, 180, 21);
		changeADButton.setBounds(320, 30, 180, 21);
		showPersonalInfoButton.setBounds(200, 350, 120, 21);
		studenMenuButton.setBounds(200,380, 120, 21);
		
		add(changePWLabel);
		add(txa);
		add(changeADLabel);
		add(changePWInput);
		add(changeADInput);
		add(changePWButton);
		add(changeADButton);
		add(showPersonalInfoButton);
		add(studenMenuButton);
	}
	
		public static void main(String[] args) throws SQLException {
			new ShowPersonalInfo(3213, "dinner");
		}
}
