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


public class MyPost extends JFrame implements MouseListener, ActionListener{
    
	JTable table;
	JScrollPane jScrPane;
	
	String columNames[] = { "No", "title", "readcount", "wdate" };
	
	Object rowData[][];
	
	List<BbsDto> list = null;
	
	JButton post;
	JTextField searchField;
	JButton search;
	JButton allPost;
	
	
	public MyPost() {
		
		super("My Bbs List");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
        contentPane.setBackground(Color.yellow);
        contentPane.setLayout(null);
        
        JLabel title = new JLabel("My Post List");
		title.setBounds(150, 68, 200, 20);
		contentPane.add(title);
		
		BbsDao bbsDao = BbsDao.getInstance();
		list = bbsDao.getMyBbsList();
		
		rowData = new Object[list.size()][columNames.length];
		
		for (int i = 0; i < list.size(); i++) {
			rowData[i][0] = list.get(i).getSeq();
			rowData[i][1] = list.get(i).getTitle();
			rowData[i][2] = list.get(i).getReadcount();
			rowData[i][3] = list.get(i).getWdate();
		}
		
		
		// 앞은 데이터, 뒤는 제목들
		table = new JTable(rowData, columNames);
		table.addMouseListener(this);
		jScrPane = new JScrollPane(table);
		
		jScrPane.setBounds(0, 100, 375, 300);
		contentPane.add(jScrPane);
		
		
		post = new JButton("Post");
		post.setBounds(100, 420, 150, 20);
		post.addActionListener(this);
		contentPane.add(post);
		
		
		search = new JButton("Search");
		search.setBounds(100, 440, 150, 20);
		search.addActionListener(this);
		contentPane.add(search);
		
		searchField = new JTextField();
		searchField.setBounds(100, 460, 150, 20);
		contentPane.add(searchField);
		
		
		allPost = new JButton("all post");
		allPost.setBounds(100, 480, 150, 20);
		allPost.addActionListener(this);
		contentPane.add(allPost);
		
		
		
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
		}else if(obj == search) {
			String str = search.getText();
			
			BbsDao.getInstance().search(str);
		}else if(obj == allPost) {
			new Bbs();
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
