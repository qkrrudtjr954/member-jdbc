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
import delegator.Delegator;
import dto.BbsDto;


public class MyBbs extends JFrame implements MouseListener, ActionListener{
    
	JTable table;
	JScrollPane jScrPane;
	
	String columNames[] = { "No", "title", "readcount", "wdate" };
	
	Object rowData[][];
	
	List<BbsDto> list = null;
	
	JButton post;
	JTextField searchField;
	JButton search;
	JButton allPost;
	JButton logout;
	
	
	public MyBbs() {
		
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
		
		
		
		post = new JButton("글쓰기");
		post.setBounds(100, 420, 150, 20);
		post.addActionListener(this);
		contentPane.add(post);
		
		
		searchField = new JTextField();
		searchField.setBounds(20, 480, 150, 20);
		contentPane.add(searchField);
		
		search = new JButton("검색");
		search.setBounds(170, 480, 150, 20);
		search.addActionListener(this);
		contentPane.add(search);
		
		
		allPost = new JButton("모든 글보기");
		allPost.setBounds(100, 440, 150, 20);
		allPost.addActionListener(this);
		contentPane.add(allPost);
		
		logout = new JButton("로그아웃");
		logout.setBounds(275, 0, 100, 20);
		logout.addActionListener(this);
		contentPane.add(logout);
		
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
			String str = searchField.getText();
			
			BbsDao.getInstance().search(str);
		}else if(obj == allPost) {
			new Bbs();
			this.dispose();
		}else if(obj == logout) {
			Delegator.getInstance().setCurrent_User(null);
			new Main();
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
		new PostDetail(list.get(rowNum));
		this.dispose();
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