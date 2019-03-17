package Bean;

public class Drug
{
    private String Drugnum;
    private String Drugname;
    private String Drugtype;
    private String suppliername;

    public Drug()
    {
    }

    public Drug(String Drugnum,String Drugname,String Drugtype,String suppliername)
    {
        this.Drugnum=Drugnum;
        this.Drugname=Drugname;
        this.Drugtype=Drugtype;
    }

    public String getDrugnum()
    {
        return  Drugnum;
    }
    public void setDrugnum(String Drugnum)
    {
        this.Drugnum=Drugnum;
    }
    public String getDrugname()
    {
        return  Drugname;
    }
    public void setDrugname(String Drugname)
    {
        this.Drugname=Drugname;
    }
    public String getDrugtype()
    {
        return Drugtype;
    }
    public void setDrugtype(String Drugtype)
    {
        this.Drugtype=Drugtype;
    }

    public String getSuppliername() {
        return suppliername;
    }

    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }
}

