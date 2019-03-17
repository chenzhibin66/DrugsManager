package Bean;

import java.util.Date;

public class Sell
{
    private String sellnum;
    private String stocknum;
    private String vipnum;
    private float sellprice;
    private  int sellquantity;
    private float sellmoney;
    private String ldate;

    public Sell(){}

    public Sell(String sellnum,String stocknum,String vipnum,float sellprice,int sellquantity,float sellmoney,String ldate)
    {
        this.sellnum=sellnum;
        this.stocknum=stocknum;
        this.vipnum=vipnum;
        this.sellprice=sellprice;
        this.sellmoney=sellmoney;
        this.ldate=ldate;

    }

    public String getSellnum() {
        return sellnum;
    }

    public void setSellnum(String sellnum) {
        this.sellnum = sellnum;
    }

    public String getStocknum() {
        return stocknum;
    }

    public void setStocknum(String stocknum) {
        this.stocknum = stocknum;
    }

    public String getVipnum() {
        return vipnum;
    }

    public void setVipnum(String vipnum) {
        this.vipnum = vipnum;
    }

    public float getSellprice() {
        return sellprice;
    }

    public void setSellprice(float sellprice) {
        this.sellprice = sellprice;
    }

    public float getSellmoney() {
        return sellmoney;
    }

    public void setSellmoney(float sellmoney) {
        this.sellmoney = sellmoney;
    }

    public String getLdate() {
        return ldate;
    }

    public void setLdate(String ldate) {
        this.ldate = ldate;
    }

    public int getSellquantity() {
        return sellquantity;
    }

    public void setSellquantity(int sellquantity) {
        this.sellquantity = sellquantity;
    }


}
