package method;

import C3P0Utils.C3P0Utils;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Statement;

public class DeleteVip {
    public static void deletevip(BigInteger[] id) {
        Connection conn = null;
        Statement cs;
        try {
            conn = C3P0Utils.getConnection();
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
