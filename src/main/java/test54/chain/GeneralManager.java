package test54.chain;

/**
 * Created by chin on 5/23/16.
 */
public class GeneralManager extends Handler {
    @Override
    public String handleFeeRequest(double fee) {


        String str = "";
        if (fee >= 1000) {
            str = "成功：总经理同意";
        } else {
            if (getSuccessor() != null) {
                return getSuccessor().handleFeeRequest(fee);
            }
        }
        return str;
    }
}
