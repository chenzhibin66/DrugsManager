package jiemian;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DeleteVip
{
    public static void deletevip(BigInteger[] id) {
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//SQL数据库引擎
        String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=药房管理系统;characterEncoding=UTF-8";//数据源  ！！！！注意若出现加载或者连接数据库失败一般是这里出现问题
        String Name = "sa";
        String Pwd = "123456";
        Connection conn;
        Statement cs;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(dbURL, Name, Pwd);
            cs = conn.createStatement();
            for (int i = 0; i < id.length; i++) {
                cs.addBatch("delete from 会员 where Cno=" + id[i]);
            }
            cs.executeBatch();
            cs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
