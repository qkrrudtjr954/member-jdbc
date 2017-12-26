package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import dao.BbsDao;
import delegator.Delegator;
import dto.BbsDto;

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
				List<BbsDto> list = BbsDao.getInstance().getBbsList();
				new Bbs(list);
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
