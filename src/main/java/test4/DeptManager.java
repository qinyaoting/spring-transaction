package test4;

/**
 * Created by chin on 5/23/16.
 */
public class DeptManager extends Handler {
    @Override
    public String handleFeeRequest( double fee) {


        String str = "";
        if (fee < 1000) {
            System.out.println("部门经理批准......");
            str = "部门经理批准";
        } else {
            if (getSuccessor() != null) {
                return getSuccessor().handleFeeRequest(fee);
            }
        }

        return str;
    }
}
