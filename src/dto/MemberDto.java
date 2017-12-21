package dto;

import java.io.Serializable;
import java.util.Arrays;

/*
DROP TABLE MEMBER CASCADE CONSTRAINTS;

CREATE TABLE MEMBER (
	ID VARCHAR2(50) 		PRIMARY KEY,
	PWD VARCHAR2(50) 		NOT NULL,
	NAME VARCHAR2(50)		NOT NULL,
	EMAIL VARCHAR2(50) 	UNIQUE,
	AUTH NUMBER(1)			NOT NULL
);
 */

public class MemberDto implements Serializable {
	String id;
	char[] pwd;
	String name;
	String email;
	int auth;

	public MemberDto() {
		// TODO Auto-generated constructor stub
	}

	public MemberDto(String id, char[] pwd, String name, String email) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public char[] getPwd() {
		return pwd;
	}

	public void setPwd(char[] pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", pwd=" + Arrays.toString(pwd) + ", name=" + name + ", email=" + email + "]";
	}

}
