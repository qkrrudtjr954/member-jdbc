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

public class Msg extends JFrame {
	JLabel label;

	public Msg(String msg) {

		super("TaTao Talk");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container contentPane = getContentPane();
		contentPane.setBackground(Color.yellow);
		contentPane.setLayout(null);

		JButton button = new JButton("Main");
		button.setBounds(50, 50, 100, 100);
		button.setBorder(new LineBorder(Color.black, 2));

		button.addActionListener((ActionEvent e) -> {
			if (Delegator.getInstance().getCurrent_User() == null) {
				new Main();
				super.dispose();
			} else {
				List<BbsDto> list = BbsDao.getInstance().getBbsList();
				new Bbs(list);
				super.dispose();
			}
		});
		contentPane.add(button);
		
		
		JLabel msgLabel = new JLabel(msg);
		msgLabel.setBounds(10, 250, 300, 50);
		contentPane.add(msgLabel);

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
