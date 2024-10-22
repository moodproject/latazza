package store;

import java.sql.*;

import datatype.Euro;

public class CashAccount extends Cash {

    private Connection conn;

    public CashAccount(Connection c) {
        conn = c;
    }

    public void addMoney(Euro e) {
        Euro tot = show().addition(e);
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE CASSA SET SALDO = ?");
            stmt.setString(1, tot.toString());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void subtractMoney(Euro e) {
        Euro tot = show().subtract(e);
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE CASSA SET SALDO = ?");
            stmt.setString(1, tot.toString());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Euro show() {
        Euro saldo;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CASSA");
            rs.next();
            Double d = rs.getDouble(1);
            saldo = new Euro(d);
            rs.close();
            stmt.close();
            return saldo;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void setCashAccount(Euro e) {
        resetToZeroCashAccount();
        addMoney(e);
    }

    public void resetToZeroCashAccount() {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE CASSA SET SALDO = ? ");
            String zero = "0.0";
            stmt.setString(1, zero);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
