package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import store.EmployeeRegister;
import store.CashAccount;
import store.Depository;
import store.Employee;

import datatype.Euro;
import datatype.Beverages;
import datatype.Selling;

public class Main {

    protected static final Euro BoxPrice = new Euro(31);
    protected static final Euro SmallBugsPrice = new Euro(0.62);

    protected Euro cassa;
    protected Selling situazione_prodotti;

    protected Connection conn;

    protected CashAccount pCassa;
    protected Depository pMagazzino;
    protected EmployeeRegister pAnagrafe;

    @SuppressWarnings("unused")
    protected Supply rif;
    protected Purchase acq;
    protected Payment pag;

    public Main() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection("jdbc:h2:latazza", "sa", "");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        pCassa = new CashAccount(conn);
        pMagazzino = new Depository(conn);
        pAnagrafe = new EmployeeRegister(conn);

        rif = new Supply(pCassa, pMagazzino, BoxPrice);
        acq = new Purchase(pCassa, pMagazzino, pAnagrafe, SmallBugsPrice);
        pag = new Payment(pCassa, pAnagrafe);
    }

    public void showCashAccount() {
        System.out.print("Cash Account = ");
        System.out.println(pCassa.show());
    }

    public void showDebtors() {
        List<Employee> debitori = pAnagrafe.getDebtors();
        System.out.println("Debtors");
        for (Employee p : debitori) {
            p.printEmployee();
        }
    }

    public void showAllEmployee() {
        List<Employee> list = pAnagrafe.readRegisterEmployeeDB();
        System.out.println("Employees");
        for (Employee e : list) {
            e.printEmployee();
        }
    }

    public void showDepository() {
        System.out.println("Depository");
        pMagazzino.showQuantityOfSmallBugs().printSelling();
    }

    public void close() {
        System.exit(0);
    }

    public void setCashAccount(Euro e) {
        pCassa.resetToZeroCashAccount();
        pCassa.addMoney(e);
    }

    public void setDepository(int caffe, int arabico, int the, int thelimone, int camomilla) {
        pMagazzino.resetToZeroDepository();
        pMagazzino.enterSmallBugs(Beverages.CAFFE, caffe);
        pMagazzino.enterSmallBugs(Beverages.ARABICO, arabico);
        pMagazzino.enterSmallBugs(Beverages.THE, the);
        pMagazzino.enterSmallBugs(Beverages.THELIMONE, thelimone);
        pMagazzino.enterSmallBugs(Beverages.CAMOMILLA, camomilla);
    }

    public void deleteAllTheEmployeesFromTheDataBase() {
        pAnagrafe.deleteAllTheEmployeesFromDB();
    }

    public CashAccount getCashAccount() {
        return pCassa;
    }

    public Depository getDepository() {
        return pMagazzino;
    }

    public EmployeeRegister getEmployeeRegister() {
        return pAnagrafe;
    }

    public Supply getRif() {
        return rif;
    }

    public Purchase getAcq() {
        return acq;
    }

    public Payment getPayment() {
        return pag;
    }

    public Connection getConnection() {
        return conn;
    }
}
