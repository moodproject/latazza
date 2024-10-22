package gui;

import store.EmployeeRegister;
import store.CashAccount;
import store.Employee;
import datatype.Euro;

public class Payment {

    private CashAccount pagCassa;
    private EmployeeRegister pagAnagrafe;

    Employee personale;
    Euro quanto;

    public Payment(CashAccount cassa, EmployeeRegister anagrafe) {
        pagCassa = cassa;
        pagAnagrafe = anagrafe;
    }

    public void pay(Employee p, Euro quanto) {
        pagAnagrafe.accredit(p, quanto);
        pagCassa.addMoney(quanto);
        pagCassa.addMoney(quanto);
        System.out.println("done");
    }

    public EmployeeRegister getEmployeeRegister() {
        return pagAnagrafe;
    }
}
