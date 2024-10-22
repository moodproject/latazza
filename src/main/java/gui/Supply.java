package gui;

import store.CashAccount;
import store.Depository;
import datatype.Euro;
import datatype.Beverages;

public class Supply {

    private CashAccount rCassa;
    private Depository rMagazzino;

    private Euro boxPrice;

    public Supply(CashAccount cassa, Depository magazzino, Euro price) {
        rCassa = cassa;
        rMagazzino = magazzino;
        boxPrice = price;
    }

    public boolean supply(Beverages tipoBust, int quanteScatole) {
        Euro costoRifornimento = boxPrice.multiply(quanteScatole);
        if (!rCassa.show().major(costoRifornimento) || rCassa.show().equalTo(costoRifornimento)) {
            rMagazzino.enterSmallBags(tipoBust, quanteScatole * 50);
            rCassa.subtractMoney(costoRifornimento);
            System.out.println("done");
            return true;
        } else {
            System.out.println("Cash account not sufficient");
            return false;
        }
    }


    public CashAccount getCashAccountRif() {
        return rCassa;
    }

    public Depository getDepositoryRif() {
        return rMagazzino;
    }
}
