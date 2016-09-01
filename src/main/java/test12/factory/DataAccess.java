package test12.factory;

/**
 * Created by chin on 8/30/16.
 */
public class DataAccess {

    public static final int db = 1;//"Sqlserver"

    public static IUser createUser() {
        IUser iuser = null;
        switch (db){
            case 1:
                iuser = new SqlServerUser();
                break;
            case 2:
                iuser = new AccessUser();
                break;
        }
        return iuser;
    }

    public static IDepartment createDepartment() {
        IDepartment iDepartment = null;
        switch (db){
            case 1:
                iDepartment = new SqlServerDepartment();
                break;
            case 2:
                iDepartment = new AccessDepartment();
                break;
        }
        return iDepartment;
    }
}
