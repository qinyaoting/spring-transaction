package test12.factory;

/**
 * Created by chin on 8/30/16.
 */
public class SqlServerDepartment implements IDepartment {
    @Override
    public void insert(Department u) {
        System.out.println("SqlServerDepartment 插入一条用户数据");
    }

    @Override
    public Department get(String id) {
        System.out.println("SqlServerDepartment 查询一条用户");
        return null;
    }
}
