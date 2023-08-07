package controller.channel;

import java.sql.Timestamp;

public class Channel {

    private int chNum;
    private String ownerid;
    private String chName;
    private String chprofile;
    private String chinfo;
    private int cate_id;
    private int chFollow;
    private Timestamp chOpendate;
    private int chvisit;


    public Channel() {
    }

    public Channel(int chNum, String ownerid, String chName, String chprofile, String chinfo,
                   int cate_id, int chFollow, Timestamp chOpendate, int chvisit) {
        this.chNum = chNum;
        this.ownerid = ownerid;
        this.chName = chName;
        this.chprofile = chprofile;
        this.chinfo = chinfo;
        this.cate_id = cate_id;
        this.chFollow = chFollow;
        this.chOpendate = chOpendate;
        this.chvisit = chvisit;
    }

    public int getChNum() {
        return chNum;
    }

    public void setChNum(int chNum) {
        this.chNum = chNum;
    }

    public String getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
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

    public int getChFollow() {
        return chFollow;
    }

    public void setChFollow(int chFollow) {
        this.chFollow = chFollow;
    }

    public Timestamp getChOpendate() {
        return chOpendate;
    }

    public void setChOpendate(Timestamp chOpendate) {
        this.chOpendate = chOpendate;
    }

    public int getChvisit() {
        return chvisit;
    }

    public void setChvisit(int chvisit) {
        this.chvisit = chvisit;
    }
}
