import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame
{
	private static final long serialVersionUID = -3374450823823567437L;
	/**
	 * �û���
	 */
	private static final JLabel usernameLabel = new JLabel("username");
	private static final JTextField usernameInput = new JTextField();
	
	/**
	 * ����
	 */
	private static final JLabel passwordLabel = new JLabel("password");
	private static final JTextField passwordInput = new JPasswordField();
	
	/**
	 * ��½��ť
	 */
	private static final JButton loginButton = new JButton("login");
	private static final JButton exitButton = new JButton("exit");
	/**
	 * ���췽��
	 */
	
	public LoginFrame()
	{
		setSize(new Dimension(380, 180));
		setTitle("Login");
		setLayout(null);
		
		//��ʼ������(���Թ�)
		initUI();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		//�ô�Ϊ��Ƶ�½��ť�ļ���
		loginButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String userName = usernameInput.getText().trim();
				String password = passwordInput.getText().trim();
				
				//�û���Ϊ��ʱ��ʾ�û�����
				if (userName.equals(""))
				{
					JOptionPane.showMessageDialog(LoginFrame.this, "Please enter username!");
					return;
				}
				int username2 = Integer.parseInt(userName);
				db3 db = new db3(username2, password);
				
				int rst;
				try {
					rst = db.loginCheck(username2, password);
					if (rst == 1) {
						// login successfully
						//���ȹرյ�ǰ����
						dispose();
						//�򿪵�½�ɹ�����
						new LoginSucces(username2, password);
						
					} else {
						
						// fail to login 
						JOptionPane.showMessageDialog(LoginFrame.this, "Wrong username or password!");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

//				studentMenu sm = new studentMenu(db);
//				sm.setVisible(true);
//				ConsolePrintStream cps = new ConsolePrintStream(System.out, sm.txa);
//				System.setOut(cps);
//				db.StudentMenu(id, password);
				
			}
		});
		
		//�ô�Ϊ����˳���ť�ļ���
		exitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
	}

	/**
	 * ��ʼ������
	 */
	private void initUI()
	{
		// TODO Auto-generated method stub
		usernameLabel.setBounds(10, 10, 100, 21);
		usernameInput.setBounds(150, 10, 200, 21);
		passwordLabel.setBounds(10, 40, 100, 21);
		passwordInput.setBounds(150, 40, 200, 21);
		loginButton.setBounds(190, 100, 80, 21);
		exitButton.setBounds(290, 100, 60, 21);

		add(usernameLabel);
		add(usernameInput);
		add(passwordLabel);
		add(passwordInput);
		add(loginButton);
		add(exitButton);
	}
	
	public static void main(String[] args)
	{
		new LoginFrame();
	}
}
