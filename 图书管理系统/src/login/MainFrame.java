package login;

import java.awt.Container;

import javax.swing.JFrame;


/**
 * 主界面类
 */
public class MainFrame {
	
	
	JFrame frame = new JFrame("图书管理系统");
	Container container = frame.getContentPane();
	
	public MainFrame() {

		// 设置背景图片
		new BackgroundImage(frame,container,"mainFrame.jpg");
		
		// 设置窗口大小、位置、可视、默认关闭方式等
		new FrameOption(frame);
	}


}
