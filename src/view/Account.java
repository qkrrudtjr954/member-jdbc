package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
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

public class Account extends JFrame implements ActionListener {

	JTextField id;
	JTextField name;
	JTextField email;
	JPasswordField pwd;
	JPasswordField pwdConfirm;

	JButton signIn;
	JButton signUp;
	JButton main;
	JButton idCheck;

	JLabel label;
	

	public Account(){
		super("Sign Up");

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.yellow);
		contentPane.setLayout(null);

		JLabel title = new JLabel("SIGN UP");
		title.setBounds(150, 68, 200, 20);
		contentPane.add(title);


		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(100, 130, 50, 20);
		contentPane.add(idLabel);

		id = new JTextField();
		id.setBounds(100, 150, 150, 20);
		contentPane.add(id);
		
		
		idCheck = new JButton("중복체크");
		idCheck.setBounds(250, 150, 100, 20);
		idCheck.addActionListener(this);
		contentPane.add(idCheck);
		


		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(100, 180, 100, 20);
		contentPane.add(nameLabel);

		name = new JTextField();
		name.setBounds(100, 200, 150, 20);
		contentPane.add(name);

		JLabel pwdLabel = new JLabel("PASSWORD");
		pwdLabel.setBounds(100, 230, 100, 20);
		contentPane.add(pwdLabel);

		pwd = new JPasswordField();
		pwd.setBounds(100, 250, 150, 20);
		contentPane.add(pwd);

		JLabel pwdConfirmLabel = new JLabel("PASSWORD");
		pwdConfirmLabel.setBounds(100, 280, 100, 20);
		contentPane.add(pwdConfirmLabel);

		pwdConfirm = new JPasswordField();
		pwdConfirm.setBounds(100, 300, 150, 20);
		contentPane.add(pwdConfirm);
		
		JLabel emailLabel = new JLabel("EMAIL");
		emailLabel.setBounds(100, 330, 100, 20);
		contentPane.add(emailLabel);

		email = new JTextField();
		email.setBounds(100, 350, 150, 20);
		contentPane.add(email);




		JPanel panel = new JPanel();
		panel.setSize(150, 100);
		panel.setLayout(new GridLayout(2,1));
		panel.setLocation(100, 450);

		signUp = new JButton("SIGN UP");
		signUp.addActionListener(this);
		panel.add(signUp);


		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(1, 2));

		signIn = new JButton("SIGN IN");
		signIn.addActionListener(this);
		btnPanel.add(signIn);


		main = new JButton("MAIN");
		main.addActionListener(this);
		btnPanel.add(main);

		panel.add(btnPanel);

		contentPane.add(panel);



		setBounds(100, 100, 375, 667);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		MemberDao dao = MemberDao.getInstance();

		if(obj == signIn){
			new Login();
			this.dispose();				
		}else if(obj == signUp){
			// validation 이 false이면 입력에 문제가 있다
			if(validation()) {
				// .getId() 가 false이면 아이디를 사용해도 좋다(중복이 없다)
				if(!dao.getId(id.getText())) {
					if(checkPwd()) {
						String _id = id.getText();
						char[] _pwd = pwd.getPassword();
						String _name = name.getText();
						String _email = email.getText();
						
						MemberDto member = new MemberDto(_id, _pwd, _name, _email);
						
						int count = dao.addMember(member);
						
						if(count==1) {
							JOptionPane.showMessageDialog(new Login(), "회원 가입에 성공했습니다.");
							this.dispose();
						}else {
							JOptionPane.showMessageDialog(null, "다시 시도해주세요.");
						}						
					}else {
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
					}
				}else {
					JOptionPane.showMessageDialog(null, "이미 사용중인 아이디 입니다.");
				}
			}
		}else if(obj == idCheck) {
			if(id.getText().length() < 5) {
				JOptionPane.showMessageDialog(null, "아이디는 5글자 이상입니다.");
			}else {
				// .getId() 가 false이면 아이디를 사용해도 좋다(중복이 없다)
				if(!dao.getId(id.getText())) {
					JOptionPane.showMessageDialog(null, "사용 가능한 아이디 입니다.");
				}else {
					JOptionPane.showMessageDialog(null, "이미 사용중인 아이디 입니다.");
				}				
			}
		}
		else{
			new Main();
			this.dispose();
		}
	}
	
	private boolean validation() {
		if(id.getText().equals("") || id.getText().length() < 5) {
			JOptionPane.showMessageDialog(null, "아이디는 5글자 이상 입력해주세요.");
			return false;
		}else if(pwd.getPassword().length < 6) {
			JOptionPane.showMessageDialog(null, "비밀 번호는 6글자 이상 입력해주세요.");
			return false;
		}else if(name.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "이름을 입력해주세요.");
			return false;
		}else if(email.getText().equals("") || !email.getText().contains("@")) {
			JOptionPane.showMessageDialog(null, "이메일의 형식을 확인해주세요.");
			return false;
		}
		
		return true;
	}
	
	// 비밀번호가 일치하는지 확인한다.
	private boolean checkPwd() {
		char[] pwd1 = pwd.getPassword();
		char[] pwd2 = pwdConfirm.getPassword();
		
		if(pwd1.length != pwd2.length) {
			return false;
		}else {
			for(int i=0; i<pwd1.length; i++) {
				if(pwd1[i]!=pwd2[i]) {
					return false;
				}
			}			
		}
		return true;
	}
}
