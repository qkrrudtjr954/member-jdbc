package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import dao.BbsDao;
import dao.MemberDao;
import delegator.Delegator;
import dto.BbsDto;


public class PostDetail extends JFrame implements MouseListener, ActionListener{
	
	JButton allPost;
	JButton myPost;
	
	JButton deleteBtn;
	JButton editBtn;
	
	
	JLabel title;
	JLabel wdate;
	JTextPane content;
	
	public PostDetail(BbsDto bbsDto) {
		
		super("Detail");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
        contentPane.setBackground(Color.yellow);
        contentPane.setLayout(null);
        

        title = new JLabel(bbsDto.getTitle());
		title.setBounds(150, 20, 200, 20);
		title.setFont(new Font(bbsDto.getTitle(), Font.PLAIN, 25));
		contentPane.add(title);
		
		wdate = new JLabel(bbsDto.getWdate());
		wdate.setBounds(220, 70, 200, 20);
		contentPane.add(wdate);
		
		JLabel readcount = new JLabel("조회수 : "+bbsDto.getReadcount());
		readcount.setBounds(10, 70, 100, 20);
		contentPane.add(readcount);
		
		JLabel contentLabel = new JLabel("Content");
		contentLabel.setBounds(10, 100, 50, 20);
		contentPane.add(contentLabel);

		
		content = new JTextPane();
		content.setText(bbsDto.getContent());
		content.setEditable(false);
		content.setBounds(20, 200, 335, 200);
		
		JScrollPane panel = new JScrollPane(content);
		panel.setBounds(0, 120, 375, 350);
		
		contentPane.add(panel);
		
		System.out.println(bbsDto.getId());
		System.out.println(Delegator.getInstance().getCurrent_User().getId());
		System.out.println(bbsDto.getId().equals(Delegator.getInstance().getCurrent_User().getId()));
		if (bbsDto.getId().equals(Delegator.getInstance().getCurrent_User().getId())) {
	
			deleteBtn = new JButton("Delete");
			deleteBtn.setBounds(10, 480, 100, 50);
			deleteBtn.addActionListener((ActionEvent e)-> {
				if(JOptionPane.showConfirmDialog(null, "정말 삭제하겠습니까?") == 0) {
					System.out.println("delete");
					BbsDao.getInstance().delete(bbsDto);
					new Msg("삭제되었습니다.");
					this.dispose();
				}
			});
			contentPane.add(deleteBtn);
			
			editBtn = new JButton("Edit");
			editBtn.setBounds(110, 480, 100, 50);
			editBtn.addActionListener((ActionEvent e)-> {
				
			});
			contentPane.add(editBtn);
			
		}
		
		allPost = new JButton("All Post");
		allPost.setBounds(100, 530, 150, 20);
		allPost.addActionListener(this);
		contentPane.add(allPost);
		
		myPost = new JButton("my post");
		myPost.setBounds(100, 550, 150, 20);
		myPost.addActionListener(this);
		contentPane.add(myPost);
		
		
		setBounds(100, 100, 375, 667);
        setResizable(false);
        setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		
		if(obj == allPost) {
			new Bbs();
		}else if(obj == myPost) {
			new MyPost();
		}
		this.dispose();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
