package jiemian;

import com.bean.*;
import zhujiemian.HomeScreen;

import javax.swing.*;
import java.sql.*;

import static jiemian.myWindow.run;

public class SQLsever {
    Connection conn;
    PreparedStatement ps;

    ResultSet rs = null;
    String Name, Pwd;

    public void ConnectSQL() {
        try {
            String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//SQL数据库引擎
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=药房管理系统;characterEncoding=UTF-8";//数据源  ！！！！注意若出现加载或者连接数据库失败一般是这里出现问题
            Name = "sa";
            Pwd = "123456";
//            Class.forName(driverName);
            conn = DriverManager.getConnection(dbURL, Name, Pwd);
            System.out.println("连接数据库成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Connection  Connect() {
        try {
            String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//SQL数据库引擎
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=药房管理系统;characterEncoding=UTF-8";//数据源  ！！！！注意若出现加载或者连接数据库失败一般是这里出现问题
            Name = "sa";
            Pwd = "123456";
//            Class.forName(driverName);
            conn = DriverManager.getConnection(dbURL, Name, Pwd);
            System.out.println("连接数据库成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  conn;
    }


    public void Userzegis(User user) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO 管理员(Mno,Mpassword,Mname,Mphone,Memail,Maddress) values(?,?,?,?,?,?)");
            ps.setString(1, user.getUzhanghao());
            ps.setString(2, user.getUpassword());
            ps.setString(3, user.getUname());
            ps.setString(4, user.getUphone());
            ps.setString(5, user.getUemail());
            ps.setString(6, user.getUaddress());
            int i = ps.executeUpdate();
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "注册成功", "提示消息", JOptionPane.WARNING_MESSAGE);
            }
            if(user.getUzhanghao().equals(null)&&user.getUpassword().equals(null)&user.getUname().equals(null)){
                JOptionPane.showMessageDialog(null, "注册失败", "提示信息", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ie) {
            ie.printStackTrace();
        }
    }

    //登录验证
    public Boolean SQLverify(String a, String b) {
        try {
            ps = conn.prepareStatement("select *from 管理员 where Mno=? and Mpassword=?");
            ps.setString(1, a);
            ps.setString(2, b);
            //把结果返回成一张表
            rs = ps.executeQuery();
            if (rs.next()) {
                Name = rs.getString(1);
                Pwd = rs.getString(2);
                System.out.println("成功获取密码和用户名from数据库");
                System.out.println(Name + "\t" + Pwd + "\t");
                run(new HomeScreen(), 800, 600);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "用户名或密码错误，请重新输入！", "提示消息", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean getVipnum(String a) {
        try {
            ps = conn.prepareStatement("select *from 会员 where Cno=? ");
            ps.setString(1, a);
            //把结果返回成一张表
            rs = ps.executeQuery();
            if (rs.next())
            {
                int num=rs.getInt(1);
                System.out.println("会员ID："+num);
                return true;
            }
            else {
                return false;
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;

        }
    }

//注册验证
    public void zhuceVerify(User user) {
        try {
            ps = conn.prepareStatement("select * from 管理员 where Mno=?");
            ps.setString(1, user.getUzhanghao());
            rs = ps.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "该账号已经存在，请更换其他账号！", "提示信息", JOptionPane.WARNING_MESSAGE);
            } else {
                System.out.println("账号:" + user.getUzhanghao() + "密码" + user.getUpassword());
                this.Userzegis(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//销售验证
    public void AddSellVerity(Sell sell) {
        try {
            ps = conn.prepareStatement("select * from 销售表 where Slno=?");
            ps.setString(1, sell.getSellnum());
            rs = ps.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "该订单已经存在！", "提示信息", JOptionPane.WARNING_MESSAGE);
            } else {
                this.InsertSell(sell);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //对利润视图中的总进价求和
    public String selectPrfit()
    {
        String totalp=null;
        try {
            ps = conn.prepareStatement("select sum(TotalPrice) from v_利润 ");
            rs = ps.executeQuery();
            while (rs.next()) {
                totalp = rs.getString(1);
                System.out.println(totalp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalp;
    }
    //对利润视图中的总售价求和
    public String selectSell()
    {
        String totalS=null;
        try {
            ps = conn.prepareStatement("select sum(TotalSell) from v_利润 ");
            rs = ps.executeQuery();
            while (rs.next()) {
                totalS = rs.getString(1);
                System.out.println(totalS);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalS;
    }
    //更新销售数据
    public void updateSell()
    {
        try {
            ps = conn.prepareStatement("UPDATE 库存表 set 库存表.Pquantity = (库存表.Pquantity - 销售表.Mquantity) from 销售表\n" +
                    " where 库存表.Lno IN (select Lno from 销售表)");

            ps.executeUpdate();
            System.out.println("更新成功");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
//生成的销售数据插入销售表
    public void InsertSell(Sell sell) {
        try {
            PreparedStatement statement = conn.prepareStatement("insert into 销售表 values(?,?,?,?,?,?,?)");
            statement.setString(1, sell.getSellnum());
            statement.setString(2, sell.getStocknum());
            statement.setString(3, sell.getVipnum());
            statement.setFloat(4, sell.getSellprice());
            statement.setInt(5, sell.getSellquantity());
            statement.setFloat(6, sell.getSellmoney());
            statement.setString(7, sell.getLdate());
            int i = statement.executeUpdate();
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "添加成功", "提示消息", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "添加失败", "提示信息", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //药品入库验证
    public void AddStockVerity(Stock stock) {
        try {
            ps = conn.prepareStatement("select * from 库存表 where Lno=?");
            ps.setString(1, stock.getStocknum());
            rs = ps.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "该药品已经存在！", "提示信息", JOptionPane.WARNING_MESSAGE);
            } else {
                this.InsertStock(stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void InsertStock(Stock stock) {
        try {
            PreparedStatement statement = conn.prepareStatement("insert into 库存表 values(?,?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1, stock.getStocknum());
            statement.setString(2, stock.getDrugnum());
            statement.setString(3, stock.getDrugname());
            statement.setString(4, stock.getDrugtype());
            statement.setInt(5, stock.getStockquantity());
            statement.setString(6, stock.getLdate());
            statement.setString(7, stock.getOutdate());
            statement.setFloat(8, stock.getDbidd());
            statement.setFloat(9, stock.getDprice());
            statement.setString(10, stock.getSname());
            statement.setFloat(11, stock.getTotalPrice());

            int i = statement.executeUpdate();
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "添加成功", "提示消息", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "添加失败", "提示信息", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //添加药品验证
    public void AddMedVerity(Drug drug) {
        try {
            ps = conn.prepareStatement("select * from 药品 where Dname=?");
            ps.setString(1, drug.getDrugname());
            rs = ps.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "该药品已经存在，请添加其他药品！", "提示信息", JOptionPane.WARNING_MESSAGE);
            } else {
                System.out.println("药品编号：" + drug.getDrugnum() + "药品名称：" + drug.getDrugname());
                this.InsertWare(drug);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void AddVipVerity(Vip vip) {
        try {
            ps = conn.prepareStatement("select * from 会员 where Cno=?");
            ps.setString(1, vip.getVipnum());
            rs = ps.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "该会员已经存在！", "提示信息", JOptionPane.WARNING_MESSAGE);
            } else {
                System.out.println("会员ID：" +  vip.getVipnum()+ "会员姓名：" + vip.getVipname());
                this.InsertVip(vip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void InsertVip(Vip vip) {
        try {
            PreparedStatement statement = conn.prepareStatement("insert into 会员 values(?,?,?,?,?,?)");
            statement.setString(1, vip.getVipnum());
            statement.setString(2, vip.getVipname());
            statement.setString(3, vip.getVipage());
            statement.setString(4, vip.getVipphone());
            statement.setString(5, vip.getVipemail());
            statement.setString(6, vip.getVipaddress());
            int i = statement.executeUpdate();
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "添加成功", "提示消息", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "添加失败", "提示信息", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//将药品信息插入药品表
    public void InsertWare(Drug drug)
    {
        try {
            PreparedStatement statement = conn.prepareStatement("insert into 药品 values(?,?,?,?)");
            statement.setString(1, drug.getDrugnum());
            statement.setString(2, drug.getDrugname());
            statement.setString(3, drug.getDrugtype());
            statement.setString(4, drug.getSuppliername());
            int i = statement.executeUpdate();
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "添加成功", "提示消息", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "添加失败", "提示信息", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //更新药品信息
    public void updateDrug(Drug drug,String no)
    {
        try
        {
            PreparedStatement statement=conn.prepareStatement("update 药品 set Dname=?,Dclass=?,Sname=? where Dno='"+no+"'");
            statement.setString(1,drug.getDrugname());
            statement.setString(2,drug.getDrugtype());
            statement.setString(3,drug.getSuppliername());
            statement.execute();
            JOptionPane.showMessageDialog(null,"修改成功！","提示消息",JOptionPane.WARNING_MESSAGE);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
//更新会员数据
    public void updateVip(Vip vip,String id)
    {
        try
        {
            PreparedStatement statement=conn.prepareStatement("update 会员 set Cname=?,Cage=?,Cphone=?,Cemail=?,Caddress=? where Cno='"+id+"'");
            statement.setString(1,vip.getVipname());
            statement.setString(2,vip.getVipage());
            statement.setString(3,vip.getVipphone());
            statement.setString(4,vip.getVipemail());
            statement.setString(5,vip.getVipaddress());
            statement.execute();
            JOptionPane.showMessageDialog(null,"修改成功！","提示消息",JOptionPane.WARNING_MESSAGE);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public int getRowCountofStock()
    {
        Statement stmt=null;
        ResultSet rest=null;
        int row=0;
        try {
            String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//SQL数据库引擎
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=药房管理系统;characterEncoding=UTF-8";//数据源  ！！！！注意若出现加载或者连接数据库失败一般是这里出现问题
            Name = "sa";
            Pwd = "123456";
//            Class.forName(driverName);
            conn = DriverManager.getConnection(dbURL, Name, Pwd);
            System.out.println("连接数据库成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try
        {
            stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rest=stmt.executeQuery("select * from 库存表");
            rest.last();
            row=rest.getRow();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return row;

    }

    public int getRowCountofSell()
    {
        Statement stmt=null;
        ResultSet rest=null;
        int row=0;
        try {
            String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//SQL数据库引擎
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=药房管理系统;characterEncoding=UTF-8";//数据源  ！！！！注意若出现加载或者连接数据库失败一般是这里出现问题
            Name = "sa";
            Pwd = "123456";
//            Class.forName(driverName);
            conn = DriverManager.getConnection(dbURL, Name, Pwd);
            System.out.println("连接数据库成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try
        {
            stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rest=stmt.executeQuery("select * from 销售表");
            rest.last();
            row=rest.getRow();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return row;

    }





}
