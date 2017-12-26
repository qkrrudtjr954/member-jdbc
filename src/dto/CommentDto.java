package dto;

import java.io.Serializable;

/*
create table comments(
	content varchar(1000),
    wdate datetime,
    user_id varchar(50),
    constraint fk_user_id foreign key(user_id) references member(id) on delete cascade,
    bbs_id int,
    constraint fk_bbs_id foreign key(bbs_id) references bbs(seq) on delete cascade,
    del int(1)
);
 */
public class CommentDto implements Serializable {
	String content;
	String wdate;
	String user_id;
	int bbs_id;
	int del;
	
	public CommentDto() {
		// TODO Auto-generated constructor stub
	}
	public CommentDto(String content, String wdate, String user_id, int bbs_id, int del) {
		super();
		this.content = content;
		this.wdate = wdate;
		this.user_id = user_id;
		this.bbs_id = bbs_id;
		this.del = del;
	}
	@Override
	public String toString() {
		return "CommentDto [content=" + content + ", wdate=" + wdate + ", user_id=" + user_id + ", bbs_id=" + bbs_id
				+ ", del=" + del + "]";
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getBbs_id() {
		return bbs_id;
	}
	public void setBbs_id(int bbs_id) {
		this.bbs_id = bbs_id;
	}
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
	}
	
	

	
	
}
