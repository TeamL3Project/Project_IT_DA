package Channel.DB;

import java.sql.Timestamp;

public class ChannelBean {
	private int chnum; // 채널번호
	private String ownerid; // 소유id
	private String chname; // 채널이름
	private String chprofile; // 채널프로필
	private String chinfo; // 채널 소개
	private int cate_id; // 카테고리 id
	private int chfollow; // 구독자수
	private Timestamp chopendate; // 채널오픈일
	private int chvisit; // 채널 방문자수

	public int getChnum() {
		return chnum;
	}

	public void setChnum(int chnum) {
		this.chnum = chnum;
	}

	public String getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}

	public String getChname() {
		return chname;
	}

	public void setChname(String chname) {
		this.chname = chname;
	}

	public String getChprofile() {
		return chprofile;
	}

	public void setChprofile(String chprofile) {
		this.chprofile = chprofile;
	}

	public String getChinfo() {
		return chinfo;
	}

	public void setChinfo(String chinfo) {
		this.chinfo = chinfo;
	}

	public int getCate_id() {
		return cate_id;
	}

	public void setCate_id(int cate_id) {
		this.cate_id = cate_id;
	}

	public int getChfollow() {
		return chfollow;
	}

	public void setChfollow(int chfollow) {
		this.chfollow = chfollow;
	}

	public Timestamp getChopendate() {
		return chopendate;
	}

	public void setChopendate(Timestamp chopendate) {
		this.chopendate = chopendate;
	}

	public int getChvisit() {
		return chvisit;
	}

	public void setChvisit(int chvisit) {
		this.chvisit = chvisit;
	}

}
