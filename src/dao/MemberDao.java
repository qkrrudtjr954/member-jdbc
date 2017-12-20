package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBClose;
import db.DBConnection;
import dto.MemberDto;

public class MemberDao {
	private static MemberDao dao = null;
	
	private MemberDao() {
		
	}
	
	public static MemberDao getInstance() {
		if(dao==null) {
			dao = new MemberDao();
		}
		return dao;
	}
	
	public boolean getId(String id) {
		// jdbc 코드
		return true;
	}
	
	public MemberDto search(String id, char[] pwd) {
		String pwds = new String(pwd);
		
		String sql = " select id, email, name, auth "
				+ " from member "
				+ " where id='"+id+"' and pwd='"+pwds+"'";
		
		Connection conn = DBConnection.makeConnection();
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		MemberDto member = null;
		
		System.out.println("sql : "+sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);	// query 를 실행하라 그리고 그 값을 rs에 저장해라.
			
			if(rs.next()) {
				member = new MemberDto();
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setPwd(pwd);
				member.setAuth(rs.getInt("auth"));
				member.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, conn, rs);
		}
		return member;
	}
	
	
	
	public int insert(String id, char[] pwd, String name, String email) {
		// 접속을 가져온다.
		Connection conn = DBConnection.makeConnection();
		PreparedStatement stmt = null;
		
		String pwds = new String(pwd);
		
		String sql = ""
				+ "insert into member(id, pwd, name, email, auth) "
				+ "values('"+id+"', '"+pwds+"', '"+name+"', "+email+", 3)";
		
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

