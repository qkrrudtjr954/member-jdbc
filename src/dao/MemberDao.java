package dao;

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
}
