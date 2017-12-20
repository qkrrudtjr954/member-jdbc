package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import dao.MemberDao;

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

		Container contentPane = getContentPane();
		contentPane.setBackground(Color.orange);
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
		idCheck.setBounds(260, 130, 50, 20);
		idCheck.addActionListener(this);
		contentPane.add(idCheck);
		


		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(100, 180, 100, 20);
		contentPane.add(nameLabel);

		name = new JTextField();
		name.setBounds(100, 200, 150, 20);
		contentPane.add(name);

		JLabel pwdLabel = new JLabel("PASSWORD");
		pwdLabel.setBounds(100, 230, 50, 20);
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

		email = new JPasswordField();
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
			if(id.getText().equals("") || id.getText().length() < 5) {
				JOptionPane.showMessageDialog(null, "아이디는 5글자 이상 입력해주세요.");
			}
			int count = dao.insert(id.getText(), pwd.getPassword(), name.getText(), email.getText());
			
			if(count==1) {
				JOptionPane.showMessageDialog(new Login(), "회원 가입에 성공했습니다.");
				this.dispose();
			}else {
				JOptionPane.showMessageDialog(null, "다시 시도해주세요.");
			}
		}else if(obj == idCheck) {
			if(id.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
			}else {
				boolean check = dao.getId(id.getText());
				if(check) {
					
				}
			}
		}
		else{
			new Main();
			this.dispose();
		}
	}

}
