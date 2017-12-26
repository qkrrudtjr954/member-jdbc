package admin;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import dao.AdminDao;
import dao.BbsDao;
import dto.BbsDto;

public class AdminEdit extends JFrame implements ActionListener {

	JTextField title;
	JTextPane content;
	JTextField readcount;
	
	JLabel label;
	
	JButton edit;
	JButton allPost;
	
	int seq;

	public AdminEdit(BbsDto bbsDto){
		super("Edit");

		this.seq = bbsDto.getSeq();
		
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.yellow);
		contentPane.setLayout(null);

		JLabel maintitle = new JLabel("Post");
		maintitle.setBounds(150, 68, 200, 20);
		contentPane.add(maintitle);


		JLabel idLabel = new JLabel("Title");
		idLabel.setBounds(20, 130, 50, 20);
		contentPane.add(idLabel);

		title = new JTextField(bbsDto.getTitle());
		title.setBounds(20, 150, 335, 20);
		contentPane.add(title);

		JLabel contentLabel = new JLabel("Content");
		contentLabel.setBounds(20, 180, 100, 20);
		contentPane.add(contentLabel);

//		content = new JTextArea();
//		content.setBounds(20, 200, 335, 200);
//		contentPane.add(content);
		
		
		content = new JTextPane();
		content.setText(bbsDto.getContent());
		content.setEditable(true);
		content.setBounds(20, 200, 335, 200);
		
		
		JScrollPane panel = new JScrollPane(content);
		panel.setBounds(20, 200, 335, 200);
		
		contentPane.add(panel);
		
		JLabel readcountLabel = new JLabel("Read Count");
		readcountLabel.setBounds(20, 420, 100, 20);
		contentPane.add(readcountLabel);
		
		
		readcount = new JTextField();
		readcount.setText(bbsDto.getReadcount()+"");
		readcount.setBounds(20, 440, 335, 20);
		contentPane.add(readcount);
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




		edit = new JButton("Edit");
		edit.setBounds(100, 500, 150, 20);
		edit.addActionListener(this);
		contentPane.add(edit);
		
		
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
		AdminDao dao = AdminDao.getInstance();

		if(obj == edit){
			if (!title.getText().equals("") || title.getText().length() < 2 || !content.getText().equals("") || content.getText().length() < 10) {
				if(validateReadCount()) {
					BbsDto bbsDto = dao.update(this.seq, title.getText(), content.getText(), readcount.getText());
					
					if(bbsDto != null) {
						JOptionPane.showMessageDialog(null, "글이 수정되었습니다.");
						new AdminDetail(bbsDto);
						this.dispose();
					}					
				}else {
					JOptionPane.showMessageDialog(null, "조회수는 숫자만 입력 가능합니다.");
				}
			}
		}else {
			List<BbsDto> list = AdminDao.getInstance().getAllBbsList();
			new Admin(list);
			this.dispose();
		}
	}
	
	public boolean validateReadCount() {
		String rc = readcount.getText();
		
		char temp;
		
		for(int i=0; i<rc.length(); i++) {
			temp = rc.charAt(i);
			
			if ((int)temp < 48 || (int)temp > 57){
		        //만약 0~9사이의 문자가 아닌 tempCh가 .도 아니거나
		        //.의 개수가 이미 1개 이상이라면 그 문자열은 숫자가 아니다.
		        return false;
			}
		}
		
		return true;
	}
}
