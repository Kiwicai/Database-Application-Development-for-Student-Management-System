import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PipedReader;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.*;

public class studentMenu extends JFrame{

	private static JLabel label;
	private static studentMenu st;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private JButton b5;
	private final JTextField txt;
	private static db3 db;
	JTextArea txa;
	
	public studentMenu(db3 data) {
		super("student menu");
		this.db = data;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		
		//设置窗体布局　
		setLayout(new FlowLayout());
		//添加标签
//		label = new JLabel("Enter username: ");
//		add(label);
//		
//		//添加文本输入框
//		txt1 = new JTextField(30);
//		add(txt1);
//		
//		//添加标签
//		label = new JLabel("Enter password: ");
//		add(label);
//		
//		//添加文本输入框
//		txt2 = new JTextField(30);
//		add(txt2);
		txt = new JTextField("Successfully Log In !");
		
		//添加按钮
		b1 = new JButton("Transcript");
		b2 = new JButton("Enroll");
		b3 = new JButton("Withdraw");
		b4 = new JButton("PersonalDetails");
		b5 = new JButton("LogOut");
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		
		//添加事件
		b1.addActionListener(new TranscriptListener());
//		b2.addActionListener(new EnrollListener());	
//		b3.addActionListener(new WithdrawListener());
//		b4.addActionListener(new PersonalDetailsListener());
//		b5.addActionListener(new LogOutListener());
		
		//添加文本区域
		txa = new JTextArea(22,26);
		add(txa);
	}
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				st = new studentMenu(db);
			}
		});
		
		/*TimeUnit.SECONDS.sleep(1);
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				label.setText(Hi,this is a different);
			}
		});*/
	}
	
	class TranscriptListener implements ActionListener{
		db3 d = new db3();
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
//			int id = Integer.parseInt(txt1.getText());
//			String password = txt2.getText();
			try {
				setVisible(false);
				studentMenu sm = new studentMenu(db);
				sm.setVisible(true);
				ConsolePrintStream cps = new ConsolePrintStream(System.out, txa);
				System.setOut(cps);
				d.showTranscript();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
//			txt1.setText("");
//			txt2.setText("");
		}
	}
	
	
//	class ClearListener implements ActionListener{
//		@Override
//		public void actionPerformed(ActionEvent event) {
//			// TODO Auto-generated method stub
//			//将按钮的名称显示在TextField中
//			txt1.setText("");
//			txt2.setText("");
//		}
//	}
}
