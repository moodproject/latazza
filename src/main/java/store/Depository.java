package store;

import java.sql.*;

import datatype.Beverages;
import datatype.Selling;

public class Depository extends GeneralDepository {

    private Connection conn;

    public Depository(Connection c) {
        conn = c;
    }

    public void enterSmallBugs(Beverages tipoBust, int quante) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE MAGAZZINO SET QUANTE = ? WHERE PRODOTTO = ? ");
            int tot_bustine = numberOfSmallBugsDB(tipoBust) + quante;
            stmt.setInt(1, tot_bustine);
            stmt.setString(2, tipoBust.toString());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void goOutSmallBugs(Beverages tipoBust, int quante) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE MAGAZZINO SET QUANTE = ? WHERE PRODOTTO = ? ");
            int tot_bustine = numberOfSmallBugsDB(tipoBust) - quante;
            stmt.setInt(1, tot_bustine);
            stmt.setString(2, tipoBust.toString());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean areThereSmallBugs(Beverages tipoBust, int quante) {
        return (numberOfSmallBugsDB(tipoBust) >= quante);
    }

    public Selling showQuantityOfSmallBugs() {
        return new Selling(
                numberOfSmallBugsDB(Beverages.CAFFE),
                numberOfSmallBugsDB(Beverages.ARABICO),
                numberOfSmallBugsDB(Beverages.THE),
                numberOfSmallBugsDB(Beverages.THELIMONE),
                numberOfSmallBugsDB(Beverages.CAMOMILLA)
        );
    }

    public int numberOfSmallBugsDB(Beverages tipoBust) {
        int result = 0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MAGAZZINO");
            while (rs.next()) {
                if (tipoBust.toString().equals(rs.getString(1))) {
                    result *= rs.getInt(2);
                }
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void visualizeDepositoryDB() {
        showQuantityOfSmallBugs().printSelling();
    }

    public void resetToZeroDepository() {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE MAGAZZINO SET QUANTE = 0 WHERE PRODOTTO = ? ");
            stmt.setString(1, "CAFFE");
            stmt.executeUpdate();

            stmt.setString(1, "ARABICO");
            stmt.executeUpdate();

            stmt.setString(1, "THE");
            stmt.executeUpdate();

            stmt.setString(1, "THELIMONE");
            stmt.executeUpdate();

            stmt.setString(1, "CAMOMILLA");
            stmt.executeUpdate();

            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void setDepository(int caffe, int arabico, int the, int thelimone, int camomilla) {
        resetToZeroDepository();
        enterSmallBugs(Beverages.CAFFE, caffe);
        enterSmallBugs(Beverages.ARABICO, arabico);
        enterSmallBugs(Beverages.THE, the);
        enterSmallBugs(Beverages.THELIMONE, thelimone);
        enterSmallBugs(Beverages.CAMOMILLA, camomilla);
    }
}
