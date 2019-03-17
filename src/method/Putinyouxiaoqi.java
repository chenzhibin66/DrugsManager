package method;

import C3P0Utils.C3P0Utils;

import javax.swing.*;
import java.sql.*;
import java.util.Vector;

//获取库存表中我需要的数据
public class Putinyouxiaoqi {
    public static Vector getRows() {
        PreparedStatement preparedStatement = null;
        Vector rows = null;
        Vector columnHeads = null;
        Connection conn = null;
        try {
            conn = C3P0Utils.getConnection();
            preparedStatement = conn.prepareStatement("select Lno,Dname,Dclass,Pquantity Residualquantity,outdate from 库存表");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.wasNull()) {
                JOptionPane.showMessageDialog(null, "表中无记录");
            }
            rows = new Vector();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                System.out.println("111");
                rows.addElement(getNextRow(resultSet, rsmd));
            }
        } catch (SQLException e) {
            System.out.println("未成功打开数据库");
            e.printStackTrace();
        }
        return rows;
    }

    public static Vector getHead() {
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//SQL数据库引擎
        String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=药房管理系统;characterEncoding=UTF-8";//数据源
        String Name = "sa";
        String Pwd = "123456";
        PreparedStatement preparedStatement = null;
        Vector columnHeads = null;
        Connection conn;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(dbURL, Name, Pwd);
            preparedStatement = conn.prepareStatement("select Lno,Dname,Dclass,Pquantity Residualquantity,outdate from 库存表");
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean moreRecords = resultSet.next();
            if (!moreRecords) {
                JOptionPane.showMessageDialog(null, "表中无记录");
            }
            columnHeads = new Vector();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                columnHeads.addElement(rsmd.getColumnName(i));
            }
        } catch (ClassNotFoundException e) {
            System.out.println("未成功加载驱动");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("未成功打开数据库");
            e.printStackTrace();
        }
        return columnHeads;
    }

    private static Vector getNextRow(ResultSet rs, ResultSetMetaData rsmd) throws SQLException {
        Vector currentRow = new Vector();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            currentRow.addElement(rs.getString(i));
        }
        return currentRow;
    }
}
