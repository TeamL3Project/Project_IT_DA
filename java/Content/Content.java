package Content;

public class Content {
    private int boardNum, chNum, boardHeart, chCate_id, boardVisit;
    private String writer;
    private String boardTitle;
    private String boardContent;
    private String boardOpen;
    private String boardNore;
    private String thumbNail;


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

    public int getBoardvisit() {
        return boardVisit;
    }

    public void setBoardvisit(int boardvisit) {
        this.boardVisit = boardvisit;
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
