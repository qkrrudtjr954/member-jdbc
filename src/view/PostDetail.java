package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.BbsDao;
import dto.BbsDto;


public class PostDetail extends JFrame implements MouseListener, ActionListener{
    
	JTable table;
	JScrollPane jScrPane;
	
	String columNames[] = { "No", "title", "readcount", "wdate" };
	
	Object rowData[][];
	
	List<BbsDto> list = null;
	
	JButton post;
	JTextField searchField;
	JButton search;
	JButton myPost;
	
	
	public PostDetail(BbsDto bbsDto) {
		
		super("Bbs List");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
        contentPane.setBackground(Color.yellow);
        contentPane.setLayout(null);
        
        JLabel title = new JLabel("Post Detail");
		title.setBounds(150, 68, 200, 20);
		contentPane.add(title);
		
		
		post = new JButton("Post");
		post.setBounds(100, 460, 150, 20);
		post.addActionListener(this);
		contentPane.add(post);
		
		myPost = new JButton("my post");
		myPost.setBounds(100, 480, 150, 20);
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
		BbsDao bbsDao = BbsDao.getInstance();
		
		if(obj == post) {
			new Write();
			this.dispose();
		}else if(obj == myPost) {
			new MyPost();
			this.dispose();
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("pressed");
		
		int rowNum = table.getSelectedRow();
		// 생성자로 번호만 넘기면 다른 뷰에서도 접근이 가능하다.
		JOptionPane.showConfirmDialog(null, list.get(rowNum));
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
