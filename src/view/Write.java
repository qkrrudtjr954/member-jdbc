package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import dao.BbsDao;
import dto.BbsDto;

public class Write extends JFrame implements ActionListener {

	JTextField title;
	JTextPane content;
	
	
	JLabel label;
	
	JButton post;
	JButton allPost;

	public Write(){
		super("Sign Up");

		Container contentPane = getContentPane();
		contentPane.setBackground(Color.yellow);
		contentPane.setLayout(null);

		JLabel maintitle = new JLabel("Post");
		maintitle.setBounds(150, 68, 200, 20);
		contentPane.add(maintitle);


		JLabel idLabel = new JLabel("Title");
		idLabel.setBounds(20, 130, 50, 20);
		contentPane.add(idLabel);

		title = new JTextField();
		title.setBounds(20, 150, 335, 20);
		contentPane.add(title);

		JLabel contentLabel = new JLabel("Content");
		contentLabel.setBounds(20, 180, 100, 20);
		contentPane.add(contentLabel);

//		content = new JTextArea();
//		content.setBounds(20, 200, 335, 200);
//		contentPane.add(content);
		
		
		content = new JTextPane();
		content.setEditable(true);
		content.setBounds(20, 200, 335, 200);
		
		JScrollPane panel = new JScrollPane(content);
		panel.setBounds(20, 200, 335, 200);
		
		contentPane.add(panel);
		
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




		post = new JButton("Post");
		post.setBounds(100, 500, 150, 20);
		post.addActionListener(this);
		contentPane.add(post);
		
		
		allPost = new JButton("All Post");
		allPost.setBounds(100, 520, 150, 20);
		allPost.addActionListener(this);
		contentPane.add(allPost);



		setBounds(100, 100, 375, 667);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		BbsDao dao = BbsDao.getInstance();

		if(obj == post){
			if (!title.getText().equals("") || title.getText().length() < 2 || !content.getText().equals("") || content.getText().length() < 10) {
				BbsDto bbsDto = dao.insert(title.getText(), content.getText());
				
				if(bbsDto != null) {
					JOptionPane.showMessageDialog(null, "글이 등록되었습니다.");
					new PostDetail(bbsDto);
					this.dispose();
				}
			}
		}else {
			new Bbs();
			this.dispose();
		}
	}
}
