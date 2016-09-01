package test12.factory;

/**
 * Created by chin on 8/30/16.
 */
public class Test {

    public static void main(String[] args) {

        User user = new User();
        Department d = new Department();
        IFactory factory = new SqlServerFactory();
        //IFactory factory = new AccessFactory();
        IUser iu = factory.createUser();
        iu.insert(user);
        iu.get(1);

        IDepartment id = factory.createDepartment();
        id.insert(d);
        id.get("1");


        // 用简单工厂来简化
        IUser u2 = DataAccess.createUser();
        u2.insert(user);
        u2.get(1);


    }
}
