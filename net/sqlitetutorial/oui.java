package net.sqlitetutorial;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

public class oui {

    private Connection connect() {
        String url = "jdbc:sqlite:D:/xiaowu/ProjectForHBuiderl/javaOui/db/data/oui.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                System.out.println("The Driver name is " + conn.getMetaData().getDriverName());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    /**
     * 先去查找数据表，没有就创建一个。
     **/
    private void createTable() {
        String sql = "CREATE TABLE ouiList (\n" + "	id integer PRIMARY KEY,\n" + "	keys char(12) NOT NULL,\n"
                + "	inc char(254) NOT NULL\n" + ");";

        String findTable = "SELECT id FROM ouiList ";// 查找

        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            try {
                stmt.execute(findTable);
                System.out.println("database exist");
            } catch (SQLException e) {
                stmt.execute(sql);
                System.out.println("databases creation completed");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void insert(Map<String, String> ouiMap) {
        createTable();

        String insetSql = "INSERT INTO ouiList(?,?) VALUE(?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = this.connect();
            if (conn == null) {
                return;
            }

            conn.setAutoCommit(false);

            pstm = conn.prepareStatement(insetSql);
            Iterator<String> iterators = ouiMap.keySet().iterator();
            while (iterators.hasNext()) {
                String key = iterators.next();
                String inc = ouiMap.get(key).toString();
                pstm.setString(1, key);
                pstm.setString(1, inc);
                pstm.executeUpdate();
            }

            conn.commit();

        } catch (SQLException e1) {
            try {
                if (conn != null) {// 一开始疑惑为何不是null的时候在执行rollback后面想明白了，如果啥都没有了 rollback自然不可能生效。
                    conn.rollback();
                }
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
            System.out.println(e1.getMessage());
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e3) {
                System.out.println(e3.getMessage());
            }
        }
    }

    void deleteAll() {

    }

    void update() {

    }

    void search() {

    }

    public static void main(String[] args) {
        ReadOui readOuiFile = new ReadOui("./oui.txt");
        oui ouiDb = new oui();

        Map ouiData = readOuiFile.getOuiData();
        ouiDb.insert(ouiData);
    }
}