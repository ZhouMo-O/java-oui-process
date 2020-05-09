package net.sqlitetutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * createTable
 */
public class createTable {

    public static void createNewTable() {
        String url = "jdbc:sqlite:D:/xiaowu/ProjectForHBuiderl/javaOui/db/data/learn.db";

        String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n" + "	id integer PRIMARY KEY,\n"
                + "	key text NOT NULL,\n" + "	inc text NOT NULL\n" + ");";

        try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] argv) {
        createNewTable();
    }
}