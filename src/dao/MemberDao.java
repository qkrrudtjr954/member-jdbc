package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBClose;
import db.DBConnection;
import db.MySqlConnection;
import db.OracleConnection;
import dto.MemberDto;

public class MemberDao {
	private static MemberDao dao = null;
	private static DBConnection DBConnector = null;
	
	private MemberDao() {
		
	}
	
	public static MemberDao getInstance() {
		if(dao==null) {
			dao = new MemberDao();
//			DBConnector = new OracleConnection();
			DBConnector = new MySqlConnection();
		}
		return dao;
	}
	
	public boolean getId(String id) {
		//result == true 중복하는 값이 있다
		//result == false 중복하는 값이 없다.
		boolean result = false;
		
		String sql = "select id from member where id='"+id+"'";
		
		Connection conn = DBConnector.makeConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println("dao.getId()  sql : "+sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			
			if(rs.next()) {
				// rs에 값이 있다면 중복하는 값이 이미 테이블에 존재한다는 뜻이다.
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, conn, rs);
		}
		return result;
	}
	
	public MemberDto search(String id, char[] pwd) {
		String pwds = new String(pwd);
		
		String sql = " select id, email, name, auth "
				+ " from member "
				+ " where id='"+id+"' and pwd='"+pwds+"'";
		
		Connection conn = DBConnector.makeConnection();
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
	
	
	public int delMember(int id, char[] pwd) {
		
		String pwds = new String(pwd);
		
		String sql = "delete from userdto where id='"+id+"' and pwd='"+pwds+"'";
		
		PreparedStatement stmt = null;
		
		Connection conn = DBConnector.makeConnection();

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
	
	
	public int addMember(MemberDto dto) {
		String pwds = new String(dto.getPwd());
		
		String sql = " insert into member (id, pwd, name, email, auth ) "
				+ "values ('"+dto.getId()+"', '"+pwds+"', '"+dto.getName()+"', '"+dto.getEmail()+"', 3) ";

		Connection conn = null;
		PreparedStatement stmt = null;

		int count = 0;

		System.out.println("sql : " + sql);

		try {
			conn = DBConnector.makeConnection();
			System.out.println("1/6 addMemeber Success");
			stmt = conn.prepareStatement(sql); // initializing
			System.out.println("2/6 addMemeber Success");
			
			// count는 바뀐 수
			count = stmt.executeUpdate(sql); // .executeUpdate() : 데이터베이스를 바꾸는 작업 (insert, update, delete)
			System.out.println("3/6 addMemeber Success");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("addMemeber Fail");
		} finally {
			System.out.println("4/6 addMemeber Success");
			DBClose.close(stmt, conn, null);
		}

		return count;
	}
}
