package Bean;

public class User
{
    private String Uzhanghao;
    private String Upassword;
    private String Uname;
    private String Uphone;
    private String Uemail;
    private String Uaddress;


    public User()
    {
    }

    public User(String Uzhanghao,String Upassword,String Uname,String Uphone,String Uemail,String Uaddress)
    {
        this.Uzhanghao=Uzhanghao;
        this.Upassword=Upassword;
        this.Uname=Uname;
        this.Uphone=Uphone;
        this.Uemail=Uemail;
        this.Uaddress=Uaddress;
    }
    public String getUzhanghao()
    {
        return Uzhanghao;
    }
    public void setUzhanghao(String Uzhanghao)
    {
        this.Uzhanghao=Uzhanghao;
    }
    public String getUpassword()
    {
        return Upassword;
    }
    public void setUpassword(String Upassword)
    {
        this.Upassword=Upassword;
    }
    public String getUname()
    {
        return Uname;
    }
    public void setUname(String Uname)
    {
        this.Uname=Uname;
    }
    public String getUphone()
    {
        return Uphone;
    }
    public void setUphone(String Uphone)
    {
        this.Uphone=Uphone;
    }
    public String getUemail()
    {
        return Uemail;
    }
    public void setUemail(String Uemail)
    {
        this.Uemail=Uemail;
    }
    public String getUaddress()
    {
        return Uaddress;
    }
    public void setUaddress(String Uaddress)
    {
        this.Uaddress=Uaddress;
    }
}
