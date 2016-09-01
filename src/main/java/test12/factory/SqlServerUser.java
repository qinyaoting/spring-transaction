package test12.factory;

/**
 * Created by chin on 8/30/16.
 */
public class SqlServerUser implements IUser {
    @Override
    public void insert(User u) {
        System.out.println("sqlserver 插入一条用户数据");
    }

    @Override
    public User get(int id) {
        System.out.println("sqlserver 查询一条用户");
        return null;
    }
}
