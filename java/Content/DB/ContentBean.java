package Content.DB;

import java.sql.Timestamp;

public class ContentBean {
    private int boardNum, chNum, boardHeart, chCate_id, boardVisit;
    private String writer;
    private String boardTitle;
    private String boardContent;
    private String boardOpen;
    private String boardNore;
    private String thumbNail;
    private Timestamp boardDate, boardUpdate;
    private int cnt;
    
	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	private String chcate_name;
    private String chname;
    
	private int tagid;
	private String tagname;
	
    
    
    public int getTagid() {
		return tagid;
	}

	public void setTagid(int tagid) {
		this.tagid = tagid;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	public String getChname() {
		return chname;
	}

	public void setChname(String chname) {
		this.chname = chname;
	}

    public String getChcate_name() {
		return chcate_name;
	}

	public void setChcate_name(String chcate_name) {
		this.chcate_name = chcate_name;
	}

	public int getChCate_id() {
        return chCate_id;
    }

    public void setChCate_id(int chCate_id) {
        this.chCate_id = chCate_id;
    }

    public int getBoardVisit() {
        return boardVisit;
    }

    public void setBoardVisit(int boardVisit) {
        this.boardVisit = boardVisit;
    }

    public Timestamp getBoardDate() {
    	return boardDate;
    }

    public void setBoardDate(Timestamp boardDate) {

    	this.boardDate = boardDate;
    }

    public Timestamp getBoardUpdate() {
        return boardUpdate;
    }

    public void setBoardUpdate(Timestamp boardUpdate) {
        this.boardUpdate = boardUpdate;
    }

    public String getThumbNail() {
        return thumbNail;
    }

    public void setThumbNail(String thumbNail) {
                this.thumbNail = thumbNail;
    }


    public int getChNum() {
        return chNum;
    }

    public int getBoardNum() {
        return boardNum;
    }

    public void setBoardNum(int boardNum) {
        this.boardNum = boardNum;
    }

    public void setChNum(int chNum) {
        this.chNum = chNum;
    }

    public int getBoardHeart() {
        return boardHeart;
    }

    public void setBoardHeart(int boardHeart) {
        this.boardHeart = boardHeart;
    }

    public int getChcate_id() {
        return chCate_id;
    }

    public void setChcate_id(int chcate_id) {
        this.chCate_id = chcate_id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public String getBoardOpen() {
        return boardOpen;
    }

    public void setBoardOpen(String boardOpen) {
        this.boardOpen = boardOpen;
    }

    public String getBoardNore() {
        return boardNore;
    }

    public void setBoardNore(String boardNore) {
        this.boardNore = boardNore;
    }
}
