package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Account extends JFrame implements ActionListener {

	JTextField id;
	JTextField name;
	JPasswordField password;
	JPasswordField pwdConfirm;

	JButton signIn;
	JButton signUp;
	JButton main;

	JLabel label;

	public Account(){
		super("Sign Up");

		Container contentPane = getContentPane();
		contentPane.setBackground(Color.orange);
		contentPane.setLayout(null);

		JLabel title = new JLabel("<html><span style='color:white;'>SIGN UP</span></html>");
		title.setBounds(150, 68, 200, 20);
		contentPane.add(title);


		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(100, 130, 50, 20);
		contentPane.add(idLabel);

		id = new JTextField();
		id.setBounds(100, 150, 150, 20);
		contentPane.add(id);


		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(100, 180, 100, 20);
		contentPane.add(nameLabel);

		name = new JTextField();
		name.setBounds(100, 200, 150, 20);
		contentPane.add(name);

		JLabel pwdLabel = new JLabel("PASSWORD");
		pwdLabel.setBounds(100, 230, 50, 20);
		contentPane.add(pwdLabel);

		password = new JPasswordField();
		password.setBounds(100, 250, 150, 20);
		contentPane.add(password);

		JLabel pwdConfirmLabel = new JLabel("PASSWORD");
		pwdConfirmLabel.setBounds(100, 280, 100, 20);
		contentPane.add(pwdConfirmLabel);

		pwdConfirm = new JPasswordField();
		pwdConfirm.setBounds(100, 300, 150, 20);
		contentPane.add(pwdConfirm);




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

		if(obj == signIn){
			new Login();
			this.dispose();
		}else if(obj == signUp){

		}else{
			new Main();
			this.dispose();
		}
	}

}
