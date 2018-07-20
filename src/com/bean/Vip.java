package com.bean;

public class Vip
{
    private String vipnum;
    private String vipname;
    private String vipage;
    private String vipphone;
    private String vipemail;
    private String vipaddress;
    public Vip()
    {
    }

    public Vip(String vipnum,String vipname,String vipage,String vipphone,String vipemail,String vipaddress)
    {
        this.vipnum=vipnum;
        this.vipname=vipname;
        this.vipage=vipage;
        this.vipphone=vipphone;
        this.vipemail=vipemail;
        this.vipaddress=vipaddress;
    }

    public String getVipnum() {
        return vipnum;
    }

    public void setVipnum(String vipnum) {
        this.vipnum = vipnum;
    }

    public String getVipname() {
        return vipname;
    }

    public void setVipname(String vipname) {
        this.vipname = vipname;
    }

    public String getVipage() {
        return vipage;
    }

    public void setVipage(String vipage) {
        this.vipage = vipage;
    }

    public String getVipphone() {
        return vipphone;
    }

    public void setVipphone(String vipphone) {
        this.vipphone = vipphone;
    }

    public String getVipemail() {
        return vipemail;
    }

    public void setVipemail(String vipemail) {
        this.vipemail = vipemail;
    }

    public String getVipaddress() {
        return vipaddress;
    }

    public void setVipaddress(String vipaddress) {
        this.vipaddress = vipaddress;
    }
}
