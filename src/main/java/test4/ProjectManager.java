package test4;

/**
 * Created by chin on 5/23/16.
 */
public class ProjectManager extends Handler {
    @Override
    public String handleFeeRequest(double fee) {

        String str  = "";
        if (fee < 500) {
            str = "成功,项目经理同意";
        } else {
            if (getSuccessor() != null) {
                return getSuccessor().handleFeeRequest(fee);
            }
        }
        return str;
    }
}
