package net.sqlitetutorial;

import java.util.Map;

public class index {
    public static void main(String[] args) {
        Connect.connect();
        createDb.createNewDatabase("learn.db");
        ReadOui oui = new ReadOui("./oui.txt");
        Map<String, String> ouidata = oui.getOuiData();
        createTable.createNewTable();
        // insertData insert = new insertData();
        Transaction tc = new Transaction();
        tc.addInventory(ouidata);
    }
}