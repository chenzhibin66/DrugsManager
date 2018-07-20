package jiemian;

import com.bean.Stock;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockView
{
    public List selectView()
    {
        Connection conn=null;
        Statement cs=null;
        String sql="select * from v_库存表";
        try {
            String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//SQL数据库引擎
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=药房管理系统;characterEncoding=UTF-8";//数据源  ！！！！注意若出现加载或者连接数据库失败一般是这里出现问题
            String Name = "sa";
            String Pwd = "123456";
//            Class.forName(driverName);
             conn = DriverManager.getConnection(dbURL, Name, Pwd);
            System.out.println("连接数据库成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        List list=new ArrayList();
        try {
             cs=conn.createStatement();
            ResultSet rs=cs.executeQuery(sql);
            while(rs.next())
            {
                Stock stock=new Stock();
                stock.setStocknum(rs.getString(1));
                stock.setDrugnum(rs.getString(2));
                stock.setDrugname(rs.getString(3));
                stock.setDprice(rs.getFloat(4));
                stock.setOutdate(rs.getString(5));
                stock.setStockquantity(rs.getInt(6));
                list.add(stock);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return list;
    }
}
