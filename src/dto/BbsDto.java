package dto;

import java.io.Serializable;

/*
 * 
 drop table bbs cascade constraints;

create table bbs(
	seq number(8) 				primary key,
	id varchar2(50) 				not null,
	title varchar2(200)			not null,
	content varchar2(4000) 	not null,
	wdate date						not null,
	del number(1)					not null,
	readcount number(8)		not null
);

drop sequence seq_bbs;

create sequence seq_bbs
start with 1 increment by 1;


alter table bbs
add constraint fk_bbs foreign key (id)
references member;
*/


/*
 create table bbs(
	seq 		int		auto_increment	primary key,
	id 		varchar(50)		not null,
	title 		varchar(200)			not null,
	content text(4000) 	not null,
	wdate date						not null,
	del 		int(1)					not null,
	readcount int(8)		not null
);

*/
public class BbsDto implements Serializable {
	private int seq;
	private String id;
	
	private String title;
	private String content;
	private String wdate;
	
	private int del;
	private int readcount;
	
	
	/*
	private int ref; 		// 그룹 번호
	private int step;		// 열 번호
	private int depth;	//
	*/
	
	public BbsDto() {
		// TODO Auto-generated constructor stub
	}


	public BbsDto(int seq, String id, String title, String content, String wdate, int del, int readcount) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.readcount = readcount;
	}


	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
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


	public int getDel() {
		return del;
	}


	public void setDel(int del) {
		this.del = del;
	}


	public int getReadcount() {
		return readcount;
	}


	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}


	@Override
	public String toString() {
		return "BbsDto [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", wdate=" + wdate
				+ ", del=" + del + ", readcount=" + readcount + "]";
	}
	
	
	
	
	
}
