package method;

import C3P0Utils.C3P0Utils;

import javax.swing.*;
import java.sql.*;
import java.util.Vector;

//获取利润视图，选取我需要的数据
public class PutinProfit {
    public static Vector getRows() {
        PreparedStatement preparedStatement = null;
        Vector rows = null;
        Vector columnHeads = null;
        Connection conn;
        try {
            conn = C3P0Utils.getConnection();
            preparedStatement = conn.prepareStatement("select DISTINCT Lno,TotalPrice,TotalSell,SellingTime from v_利润");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.wasNull()) {
                JOptionPane.showMessageDialog(null, "表中无记录");
            }
            rows = new Vector();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                rows.addElement(getNextRow(resultSet, rsmd));
            }
        } catch (SQLException e) {
            System.out.println("未成功打开数据库");
            e.printStackTrace();
        }
        return rows;
    }

    public static Vector getHead() {
        PreparedStatement preparedStatement = null;
        Vector columnHeads = null;
        Connection conn = null;
        try {
            conn = C3P0Utils.getConnection();
            preparedStatement = conn.prepareStatement("select * from v_利润");
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
