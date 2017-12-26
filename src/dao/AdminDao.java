package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import db.MySqlConnection;
import dto.BbsDto;

public class AdminDao {
	private static AdminDao dao = null;
	private static DBConnection DBConnector = null;
	private AdminDao() {
		
	}
	
	public static AdminDao getInstance() {
		if(dao==null) {
			dao = new AdminDao();
//			DBConnector = new OracleConnection();
			DBConnector = new MySqlConnection();
		}
		return dao;
	}
	
	public List<BbsDto> getAllBbsList(){
		List<BbsDto> bbsList = new ArrayList<>();
		DBConnector.initConnect();
		
		String sql = " select * from bbs order by wdate desc ";
		
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = DBConnector.makeConnection();
			ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery(sql);
			
			while(rs.next()) {
				BbsDto bbs = new BbsDto();
				
				bbs.setSeq(rs.getInt("seq"));
				bbs.setId(rs.getString("id"));
				
				bbs.setTitle(rs.getString("title"));
				bbs.setContent(rs.getString("content"));
				bbs.setWdate(rs.getString("wdate"));
				
				bbs.setDel(rs.getInt("del"));
				bbs.setReadcount(rs.getInt("readcount"));
				
				bbsList.add(bbs);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bbsList;
	}

	public BbsDto update(int seq, String title, String content, String readcount) {
		// TODO Auto-generated method stub
		Connection conn = DBConnector.makeConnection();
		PreparedStatement ptmt = null;
		
		String sql = "update bbs set title='"+title+"', content='"+content+"', readcount="+readcount+" where seq="+seq;

		BbsDto bbsDto = null;
		
		int count = 0;
		
		System.out.println(" * AdminDao .update() sql : " + sql);

		try {
			ptmt = conn.prepareStatement(sql);	//	initializing
			count = ptmt.executeUpdate(sql);	// .executeUpdate() : 데이터베이스를 바꾸는 작업 (insert, update, delete)
			// count는 바뀐 수
			
			if (count > 0) {
				bbsDto = BbsDao.getInstance().getBbsBySeq(seq);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { 
			DBClose.close(ptmt, conn, null);
		}
		
		return bbsDto;
	}
	
}
