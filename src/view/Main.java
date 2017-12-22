package view;

import javax.swing.*;
import javax.swing.border.LineBorder;

import delegator.Delegator;

import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
	JLabel label;

	public Main() {

		super("TaTao Talk");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container contentPane = getContentPane();
		contentPane.setBackground(Color.yellow);
		contentPane.setLayout(null);

		JButton button = new JButton("start");
		button.setBounds(110, 400, 150, 50);
		button.setBorder(new LineBorder(Color.black, 2));

		button.addActionListener((ActionEvent e) -> {
			if (Delegator.getInstance().getCurrent_User() == null) {
				new Login();
				super.dispose();
			} else {
				new Bbs();
				super.dispose();
			}
		});
		contentPane.add(button);

		// --------------------------------------------------
		label = new JLabel("---");
		label.setBounds(0, 0, 200, 30);
		contentPane.add(label);
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				label.setText("x: " + x + "  y: " + y);
			}
		});
		// --------------------------------------------------

		setBounds(100, 100, 375, 667);
		setResizable(false);
		setVisible(true);
	}
}
