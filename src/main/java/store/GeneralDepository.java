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

    public void enterSmallBugs(Beverages tipoBust, int number) {
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

    public void goOutSmallBugs(Beverages tipoBust, int number) {
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
        enterSmallBugs(Beverages.CAFFE, caffe);
        enterSmallBugs(Beverages.ARABICO, arabico);
        enterSmallBugs(Beverages.THE, the);
        enterSmallBugs(Beverages.THELIMONE, thelimone);
        enterSmallBugs(Beverages.CAMOMILLA, camomilla);
    }
}
