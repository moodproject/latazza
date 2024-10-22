package store;

import datatype.Beverages;

public class GeneralDepository {

    protected int caffe;
    protected int arabico;
    protected int the;
    protected int theLimone;
    protected int camomilla;

    public GeneralDepository() {
        this.caffe = 0;
        this.arabico = 0;
        this.the = 0;
        this.theLimone = 0;
        this.camomilla = 0;
    }

    public void enterSmallBags(Beverages tipoBust, int number) {
        switch (tipoBust) {
            case CAFFE:
                caffe = caffe + number;
                break;
            case ARABICO:
                arabico = arabico + number;
                break;
            case THE:
                the = the + number;
                break;
            case THELIMONE:
                theLimone = theLimone + number;
                break;
            case CAMOMILLA:
                camomilla = camomilla + number;
                break;
        }
    }

    public void goOutSmallBags(Beverages tipoBust, int number) {
        switch (tipoBust) {
            case CAFFE:
                caffe = caffe - number;
                break;
            case ARABICO:
                arabico = arabico - number;
                break;
            case THE:
                the = the - number;
                break;
            case THELIMONE:
                theLimone = theLimone - number;
                break;
            case CAMOMILLA:
                camomilla = camomilla - number;
                break;
        }
    }

    public void resetToZeroDepository() {
        this.caffe = 0;
        this.arabico = 0;
        this.the = 0;
        this.theLimone = 0;
        this.camomilla = 0;
    }

    public void setDepository(int caffe, int arabico, int the, int thelimone, int camomilla) {
        resetToZeroDepository();
        enterSmallBags(Beverages.CAFFE, caffe);
        enterSmallBags(Beverages.ARABICO, arabico);
        enterSmallBags(Beverages.THE, the);
        enterSmallBags(Beverages.THELIMONE, thelimone);
        enterSmallBags(Beverages.CAMOMILLA, camomilla);
    }
}
