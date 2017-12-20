package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.MemberDao;
import dto.MemberDto;

public class Login extends JFrame implements ActionListener {

	JLabel label;

	JTextField id;
	JPasswordField pwd;

	JButton signIn;
	JButton signUp;

	public Login(){
		super("Sign In");

		Container contentPane = getContentPane();
		contentPane.setBackground(Color.yellow);
		contentPane.setLayout(null);

		JLabel title = new JLabel("Sign In");
		title.setBounds(150, 68, 200, 20);
		contentPane.add(title);


		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(100, 170, 50, 20);
		contentPane.add(idLabel);

		id = new JTextField();
		id.setBounds(100, 210, 150, 20);
		contentPane.add(id);

		JLabel pwdLabel = new JLabel("PASSWORD");
		pwdLabel.setBounds(100, 248, 150, 20);
		contentPane.add(pwdLabel);

		pwd = new JPasswordField();
		pwd.setBounds(100, 288, 150, 20);
		contentPane.add(pwd);



		JPanel panel = new JPanel();
		panel.setSize(150, 100);
		panel.setLayout(new FlowLayout());
		panel.setLocation(100, 450);

		signIn = new JButton("SIGN IN");
		signIn.setSize(150, 50);
		signIn.addActionListener(this);
		panel.add(signIn);

		signUp = new JButton("SIGN UP");
		signUp.setSize(75, 50);
		signUp.addActionListener(this);

		JButton h = new JButton("SIGN UP");
		h.setSize(75, 50);
		h.addActionListener(this);

		panel.add(h);

		contentPane.add(panel);

		setBounds(100, 100, 375, 667);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == signIn){
			String _id = id.getText();
			char[] _pwd = pwd.getPassword();
			
			MemberDao dao = MemberDao.getInstance();
			MemberDto member = dao.search(_id, _pwd);
			
			if(member==null) {
				JOptionPane.showMessageDialog(null, "아이디 및 비밀번호가 일치하지 않습니다.");
				pwd.setText("");
			}
		}else if(obj == signUp){
			new Account();
			this.dispose();
		}else{

		}
	}

}
