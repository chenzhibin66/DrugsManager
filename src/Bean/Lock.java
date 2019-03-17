package Bean;

import java.util.Date;

public class Lock
{
    private String stocknum;
    private String Drugnum;
    private int stockquantity;
    private Date lockdate;
    private String astate;
    public Lock()
    {

    }
    public Lock(String stocknum,String Drugnum,int stockquantity,Date lockdate,String astate)
    {
        this.stocknum=stocknum;
        this.Drugnum=Drugnum;
        this.stockquantity=stockquantity;
        this.lockdate=lockdate;
        this.astate=astate;
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

    public int getStockquantity() {
        return stockquantity;
    }

    public void setStockquantity(int stockquantity) {
        this.stockquantity = stockquantity;
    }

    public Date getLockdate() {
        return lockdate;
    }

    public void setLockdate(Date lockdate) {
        this.lockdate = lockdate;
    }

    public String getAstate() {
        return astate;
    }

    public void setAstate(String astate) {
        this.astate = astate;
    }
}
