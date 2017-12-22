package main;

import delegator.Delegator;
import dto.MemberDto;
import view.Main;

public class mainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		new Account();
//		new Bbs();
		MemberDto member = new MemberDto();
		member.setAuth(1);
		member.setEmail("qkrrudtjr954@naver.com");
		member.setId("qkrrudtjr954");
		member.setName("Parker");
		char[] pwd = { 'r', 'u', 'd', 't', 'j', 'r' };
		member.setPwd(pwd);
		
		Delegator.getInstance().setCurrent_User(member);
		
//		new Write();
		
		new Main();
		

		
		
	}
}
