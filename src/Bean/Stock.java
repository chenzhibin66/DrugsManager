package Bean;

import java.util.Date;

public class Stock
{
    private String stocknum;
    private String Drugnum;
    private String Drugname;
    private String Drugtype;
    private int stockquantity;
    private String ldate;
    private String outdate;
    private float Dbidd;
    private float Dprice;
    private String Sname;
    private float TotalPrice;
    public Stock(){}

    public Stock(String stocknum,String Drugnum,String Drugname,String Drugtype,int stockquantity,String ldate,String outdate,float Dbidd,float Dprice,String Sname,float TotalPrice)
    {
          this.stocknum=stocknum;
          this.Drugnum=Drugnum;
          this.Drugname=Drugname;
          this.Drugtype=Drugtype;
          this.stockquantity=stockquantity;
          this.ldate=ldate;
          this.outdate=outdate;
          this.Dbidd=Dbidd;
          this.Dprice=Dprice;
          this.Sname=Sname;
          this.TotalPrice=TotalPrice;
    }

    public String getStocknum() {
        return stocknum;
    }

    public void setStocknum(String stocknum) {
        this.stocknum = stocknum;
    }

    public String getDrugnum() {
        return Drugnum;
    }

    public void setDrugnum(String drugnum) {
        Drugnum = drugnum;
    }

    public String getDrugname() {
        return Drugname;
    }

    public void setDrugname(String drugname) {
        Drugname = drugname;
    }

    public String getDrugtype() {
        return Drugtype;
    }

    public void setDrugtype(String drugtype) {
        Drugtype = drugtype;
    }

    public int getStockquantity() {
        return stockquantity;
    }

    public void setStockquantity(int quantity) {
        this.stockquantity = quantity;
    }

    public String getLdate() {
        return ldate;
    }

    public void setLdate(String ldate) {
        this.ldate = ldate;
    }

    public String getOutdate() {
        return outdate;
    }

    public void setOutdate(String outdate) {
        this.outdate = outdate;
    }

    public float getDbidd() {
        return Dbidd;
    }

    public void setDbidd(float dbidd) {
        Dbidd = dbidd;
    }

    public float getDprice() {
        return Dprice;
    }

    public void setDprice(float dprice) {
        Dprice = dprice;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public void setTotalPrice(Float totalPrice) {
        TotalPrice = totalPrice;
    }

    public float getTotalPrice() {
        return TotalPrice;
    }
}
