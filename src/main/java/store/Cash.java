package store;

import datatype.Euro;

public class Cash {

    protected Euro saldo;

    public Cash() {
        saldo = new Euro(0, 0);
    }

    public void addMoney(Euro e) {
        saldo.addition(e);
    }

    public void subtractMoney(Euro e) {
        saldo.subtract(e);
    }

    public void setCashAccount(Euro e) {
        resetToZeroCashAccount();
        addMoney(e);
    }

    public void resetToZeroCashAccount() {
        saldo = new Euro(0, 0);
    }

    public Euro show() {
        return saldo;
    }
}
