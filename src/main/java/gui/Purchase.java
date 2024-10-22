package gui;

import store.EmployeeRegister;
import store.CashAccount;
import store.Depository;
import store.Employee;
import datatype.Euro;
import datatype.Beverages;

public class Purchase {

    private CashAccount aCassa;
    private Depository aMagazzino;
    private EmployeeRegister aAnagrafe;

    private Euro smallBugsPrice;

    public Purchase(CashAccount cassa, Depository magazzino, EmployeeRegister anag, Euro price) {
        aCassa = cassa;

        aMagazzino = magazzino;
        aAnagrafe = anag;
        smallBugsPrice = price;
    }

    public void purchaseVisitor(Beverages typeSmallBugs, int numberSmallBugs) {
        if (aMagazzino.areThereSmallBugs(typeSmallBugs, numberSmallBugs)) {
            Euro costoTotaleBustine = smallBugsPrice.multiply(numberSmallBugs);
            aCassa.addMoney(costoTotaleBustine);
            aMagazzino.goOutSmallBugs(typeSmallBugs, numberSmallBugs);
            System.out.println("done");
        } else {
            System.out.println("small-bugs finished");
        }
    }

    public void purchaseEmployee(Employee p, Beverages typeSmallBugs, int numberSmallBugs, boolean accr) {
        if (accr) {
            if (aMagazzino.areThereSmallBugs(typeSmallBugs, numberSmallBugs)) {
                Euro costoTotaleBustine = smallBugsPrice.multiply(numberSmallBugs);
                aAnagrafe.debit(p, costoTotaleBustine);
                aMagazzino.goOutSmallBugs(typeSmallBugs, numberSmallBugs);
                System.out.println("done");
            } else {
                System.out.println("small-bugs finished");
            }
        } else {
            purchaseVisitor(typeSmallBugs, numberSmallBugs);
        }
    }

    public CashAccount getCashAccountAcq() {
        return aCassa;
    }

    public Depository getDepositoryAcq() {
        return aMagazzino;
    }

    public EmployeeRegister getEmployeeRegister() {
        return aAnagrafe;
    }

}
