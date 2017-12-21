package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.BbsDto;

public class BbsDao {
	private static BbsDao dao = null;
	
	private BbsDao() {
		
	}
	
	public static BbsDao getInstance() {
		if(dao==null) {
			dao = new BbsDao();
		}
		return dao;
	}
	
	public List<BbsDto> getBbsList(){
		String sql = " select * from bbs where del != 1";
		
		Connection conn = DBConnection.makeConnection();
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		List<BbsDto> bbsList = new ArrayList<>();
		
		System.out.println("sql : "+sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);	// query 를 실행하라 그리고 그 값을 rs에 저장해라.
			
			
			while(rs.next()) {
				BbsDto bbs =  new BbsDto();
				
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
		} finally {
			DBClose.close(pstmt, conn, rs);
		}
		
		return bbsList;
	}
	
	
	public List<BbsDto> search(String query) {
		String sql = " select id, email, name, auth "
				+ " from member "
				+ " where id=' '";
		
		Connection conn = DBConnection.makeConnection();
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		List<BbsDto> bbsList = null;
		
		System.out.println("sql : "+sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);	// query 를 실행하라 그리고 그 값을 rs에 저장해라.
			
			if(rs.next()) {
				BbsDto bbs =  new BbsDto();
				
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, conn, rs);
		}
		return bbsList;
	}
	
	
	
	public int insert(String id, char[] pwd, String name, String email) {
		// 접속을 가져온다.
		Connection conn = DBConnection.makeConnection();
		PreparedStatement stmt = null;
		
		String pwds = new String(pwd);
		
		String sql = ""
				+ "insert into member(id, pwd, name, email, auth) "
				+ "values('"+id+"', '"+pwds+"', '"+name+"', '"+email+"', 3)";
		
		int count = 0;
		
		System.out.println("sql : " + sql);
		
		try {
			
			stmt = conn.prepareStatement(sql);	//	initializing
			count = stmt.executeUpdate(sql);	// .executeUpdate() : 데이터베이스를 바꾸는 작업 (insert, update, delete)
			// count는 바뀐 수

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { 
			DBClose.close(stmt, conn, null);
		}
		
		return count;
	}
	
	
	public int delete(int id, char[] pwd) {
		
		String pwds = new String(pwd);
		
		String sql = "delete from userdto where id='"+id+"' and pwd='"+pwds+"'";
		
		PreparedStatement stmt = null;
		
		Connection conn = DBConnection.makeConnection();

		int count = 0;
		
		System.out.println("sql : "+sql);
		
		try {
			stmt = conn.prepareStatement(sql);
			count = stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(stmt, conn, null);
		}

		return count;
	}
}

