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

    private Euro smallBagsPrice;

    public Purchase(CashAccount cassa, Depository magazzino, EmployeeRegister anag, Euro price) {
        aCassa = cassa;

        aMagazzino = magazzino;
        aAnagrafe = anag;
        smallBagsPrice = price;
    }

    public void purchaseVisitor(Beverages typeSmallBags, int numberSmallBags) {
        if (aMagazzino.areThereSmallBags(typeSmallBags, numberSmallBags)) {
            Euro costoTotaleBustine = smallBagsPrice.multiply(numberSmallBags);
            aCassa.addMoney(costoTotaleBustine);
            aMagazzino.goOutSmallBags(typeSmallBags, numberSmallBags);
            System.out.println("\nDone");
        } else {
            System.out.println("\nSmall bags finished");
        }
    }

    public void purchaseEmployee(Employee p, Beverages typeSmallBags, int numberSmallBags, boolean accr) {
        if (accr) {
            if (aMagazzino.areThereSmallBags(typeSmallBags, numberSmallBags)) {
                Euro costoTotaleBustine = smallBagsPrice.multiply(numberSmallBags);
                aAnagrafe.debit(p, costoTotaleBustine);
                aMagazzino.goOutSmallBags(typeSmallBags, numberSmallBags);
                System.out.println("\nDone");
            } else {
                System.out.println("\nSmall bags finished");
            }
        } else {
            purchaseVisitor(typeSmallBags, numberSmallBags);
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
