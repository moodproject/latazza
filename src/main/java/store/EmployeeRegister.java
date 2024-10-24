package store;

import java.sql.*;
import java.util.*;

import datatype.Euro;

public class EmployeeRegister {

    private Connection conn;

    public EmployeeRegister(Connection c) {
        conn = c;
    }

    public void accredit(Employee p, Euro e) {
        Euro saldoDB = this.getBalanceDB(p);
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE ANAGRAFE SET SALDO = ? WHERE ID =" + p.getId());
            Euro totale = saldoDB.addition(e);
            pstmt.setString(1, totale.toString());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void debit(Employee p, Euro e) {
        Euro saldoDB = this.getBalanceDB(p);
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE ANAGRAFE SET SALDO = ? WHERE ID =" + p.getId());
            Euro totale = saldoDB.subtract(e);
            pstmt.setString(1, totale.toString());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Employee> getDebtors() {
        LinkedList<Employee> debitori = new LinkedList<Employee>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ANAGRAFE ORDER BY COGNOME");
            while (rs.next()) {
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                String cognome = rs.getString(3);
                Double saldo_double = rs.getDouble(4);
                Euro saldo = new Euro(saldo_double);
                Employee p = new Employee(id, nome, cognome, saldo);
                Euro zero = new Euro(0);
                if (!p.getBalance().minor(zero)) {
                    debitori.add(p);
                }
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return debitori;
    }

    public List<Employee> readRegisterEmployeeDB() {
        LinkedList<Employee> personale = new LinkedList<Employee>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ANAGRAFE ORDER BY ID");
            while (rs.next()) {
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                String cognome = rs.getString(3);
                double saldo_double = rs.getDouble(4);
                Euro saldo = new Euro(saldo_double);
                Employee p = new Employee(id, nome, cognome, saldo);
                personale.add(p);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return personale;
    }

    public String[] readRegisterEmployeeDBString() {
        int size = readRegisterEmployeeDB().size();
        String[] personale = new String[size + 1];
        personale[0] = "";
        int i = 1;
        for (Employee p : readRegisterEmployeeDB()) {
            personale[i] = p.getName() + " " + p.getSurname();
            i++;
        }
        return personale;
    }

    public int getId(String nome_cognome) {
        for (Employee p : readRegisterEmployeeDB()) {
            if (nome_cognome.equals(p.getName() + " " + p.getSurname())) {
                return p.getId();
            }
        }
        return 999;
    }

    public boolean isTheIdInTheDB(int id) {
        for (Employee p : readRegisterEmployeeDB()) {
            if (id == p.getId()) {
                return true;
            }
        }
        return false;
    }

    public Employee getEmployee(int id) {
        for (Employee p : readRegisterEmployeeDB()) {
            if (id == p.getId()) {
                return p;
            }
        }
        return null;
    }

    public Euro getBalanceDB(Employee p) {
        Euro saldoDB = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SALDO FROM ANAGRAFE WHERE ID=" + p.getId());
            rs.next();
            Double saldo_double = rs.getDouble(1);
            saldoDB = new Euro(saldo_double);
            rs.close();
            return saldoDB;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return saldoDB;
    }

    public void addEmployeeToDB(Employee p) {
        int id = p.getId();
        String nome = p.getName();
        String cognome = p.getSurname();
        String saldo = p.printBalance();
        try {
            conn.createStatement().execute("INSERT INTO ANAGRAFE VALUES("
                    + id + ",'"
                    + nome + "','"
                    + cognome + "','"
                    + saldo + "')");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void removeEmployeeFromDB(int id) {
        try {
            conn.createStatement().execute("DELETE FROM ANAGRAFE WHERE ID=" + id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateEmployeeDB(int id, String nome, String cognome) {
        try {
            conn.createStatement().execute("UPDATE ANAGRAFE SET "
                    + "NOME='" + nome + "', COGNOME='" + cognome
                    + "' WHERE ID=" + id
            );
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void showEmployeesDB() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ANAGRAFE ORDER BY COGNOME");
            while (rs.next()) {
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                String cognome = rs.getString(3);
                String saldo_stringa = rs.getString(4);
                System.out.println(id + " " + nome + " " + cognome + " " + saldo_stringa);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteAllTheEmployeesFromDB() {
        try {
            conn.createStatement().execute("DELETE FROM ANAGRAFE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
