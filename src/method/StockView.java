package method;

import C3P0Utils.C3P0Utils;
import Bean.Stock;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockView {
    public List selectView() {
        Connection conn = null;
        Statement cs = null;
        String sql = "select * from v_库存表";
        try {
            conn = C3P0Utils.getConnection();
            System.out.println("连接数据库成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        List list = new ArrayList();
        try {
            cs = conn.createStatement();
            ResultSet rs = cs.executeQuery(sql);
            while (rs.next()) {
                Stock stock = new Stock();
                stock.setStocknum(rs.getString(1));
                stock.setDrugnum(rs.getString(2));
                stock.setDrugname(rs.getString(3));
                stock.setDprice(rs.getFloat(4));
                stock.setOutdate(rs.getString(5));
                stock.setStockquantity(rs.getInt(6));
                list.add(stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
