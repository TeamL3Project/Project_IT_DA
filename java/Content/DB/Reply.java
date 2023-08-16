package Content.DB;

import java.sql.Timestamp;

public class Reply {
	private int replyNum;
	private int boardNum;
	private String replyWriter;
	private String replyContent;
	private int replyref;
	private int replylev;
	private int replyseq;
	private Timestamp replyDate;
	private Timestamp replyUpdate;
	
	private int cnt;
	
	
	
	
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getReplyref() {
		return replyref;
	}
	public void setReplyref(int replyref) {
		this.replyref = replyref;
	}
	public int getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getReplyWriter() {
		return replyWriter;
	}
	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public int getReplylev() {
		return replylev;
	}
	public void setReplylev(int replylev) {
		this.replylev = replylev;
	}
	public int getReplyseq() {
		return replyseq;
	}
	public void setReplyseq(int replyseq) {
		this.replyseq = replyseq;
	}
	public Timestamp getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Timestamp replyDate) {
		this.replyDate = replyDate;
	}
	public Timestamp getReplyUpdate() {
		return replyUpdate;
	}
	public void setReplyUpdate(Timestamp replyUpdate) {
		this.replyUpdate = replyUpdate;
	}
	
	
}
