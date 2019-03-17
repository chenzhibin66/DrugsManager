package method;

import C3P0Utils.C3P0Utils;

import java.sql.*;
import java.util.Vector;

public class PutinVip {
    public static Vector getRows() {
        PreparedStatement preparedStatement = null;
        Vector rows = null;
        Vector columnHeads = null;
        Connection conn = null;
        try {
            conn = C3P0Utils.getConnection();
            preparedStatement = conn.prepareStatement("select * from 会员");
            ResultSet resultSet = preparedStatement.executeQuery();
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
            preparedStatement = conn.prepareStatement("select * from 会员");
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean moreRecords = resultSet.next();
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
