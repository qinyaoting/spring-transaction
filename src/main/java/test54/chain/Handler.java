package test54.chain;

/**
 * Created by chin on 5/23/16.
 */
public abstract class Handler {

    protected Handler successor = null;

    public Handler getSuccessor() {
        return successor;
    }

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public abstract String handleFeeRequest(double fee);


}
