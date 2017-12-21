package delegator;

import dto.MemberDto;

public class Delegator {
	private static Delegator instance = null;
	private MemberDto current_User = null;
	
	private Delegator() {
	}
	
	public static Delegator getInstance() {
		if(instance==null) {
			instance = new Delegator();
		}
		
		return instance;
	}

	public MemberDto getCurrent_User() {
		return current_User;
	}

	public void setCurrent_User(MemberDto current_User) {
		this.current_User = current_User;
	}
	
	
	
	
}
