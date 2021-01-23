package login;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import dao.UserDao;
import act.User;
/**
 * package login 里面的代码是网上搜索然后做部分修改得到的，希望能够看看好点的图书管理系统的登陆界面。
 */

/**
 * 登录界面类
 */
@SuppressWarnings("serial")
public class Login extends JFrame {
	
	private final JFrame frame = new JFrame("图书管理系统");
	private final Container container = frame.getContentPane();
	
	// 用户名框
	private JTextField texFieldtUserName  = new JTextField();
	// 密码框
	private JPasswordField textFieldPasswd = new JPasswordField();
	// 登录按钮
	private JButton buttonLogin = new JButton();
	
	public Login() {
		
		container.setLayout(null);
	
		// 设置背景图片
		new BackgroundImage(frame,container,"login.jpg");
		
		// 设置用户名框以及监听事件
		setTextFieldUserName();
		
		// 设置密码框以及监听事件
		setPasswdField();
		
		// 设置登录按钮以及添加监听事件
		setButtonLogin();

			
		container.add(texFieldtUserName);
		container.add(textFieldPasswd);
		container.add(buttonLogin);

		// 设置窗口大小、位置、可视、默认关闭方式等
		frame.setVisible(true);
		// 窗口不可调整大小
		frame.setResizable(false);
		frame.setSize(550, 388);
		frame.setLocation(300,150);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);	
	}

	
	/**
	 * 设置登录按钮以及监听事件
	 */
	private void setButtonLogin() {
	
		buttonLogin.setIcon(new ImageIcon("res/button_login.jpg"));
		buttonLogin.setBounds(150,280,90,30);
		buttonLogin.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {

				String username = texFieldtUserName.getText();
				String password = new String(textFieldPasswd.getPassword());
				UserDao userDao = new UserDao();						
				try {		   
					    List<User> userList = userDao.findUserList();
				        for (User user : userList) {	
				        	if (user.getUser().equals(username) && user.getPasswd().equals(password)) {
				        		frame.setVisible(false);
				        		new MainFrame();
				        	} else if(username.equals("") || password.equals("")) {
				        		JOptionPane.showMessageDialog(null,"用户名或密码不能为空","错误"
										,JOptionPane.PLAIN_MESSAGE);
				        	} else {
				        		JOptionPane.showMessageDialog(null,"用户名或密码错误","错误"
										,JOptionPane.PLAIN_MESSAGE);		
				        	}
				        }			        
				}catch(Exception ex) {
					ex.printStackTrace();
				}  
			}
		});
	}

	/**
	 * 设置密码框以及添加监听事件
	 */
	private void setPasswdField() {
		// 限制用户输入长度
		textFieldPasswd.setDocument(new LimitTextLength(8));
		textFieldPasswd.setBounds(235,210,180,30);
		// 键盘事件，如果按下回车则模拟鼠标点击登录按钮
		new KeyListener(textFieldPasswd,buttonLogin);
	}

	
	/**
	 * 设置用户名框以及添加监听事件
	 */
	private void setTextFieldUserName() {
		// 限制用户输入长度
		texFieldtUserName.setDocument(new LimitTextLength(8));
		texFieldtUserName.setBounds(235,145,180,30);
		// 键盘事件，如果按下回车则模拟鼠标点击登录按钮
		new KeyListener(texFieldtUserName,buttonLogin);
	}



	
}
