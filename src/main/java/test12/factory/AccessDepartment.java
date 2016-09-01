package test12.factory;

/**
 * Created by chin on 8/30/16.
 */
public class AccessDepartment implements IDepartment {
    @Override
    public void insert(Department u) {
        System.out.println("AccessDepartment 插入一条用户数据");
    }

    @Override
    public Department get(String id) {
        System.out.println("AccessDepartment 查询一条用户");
        return null;
    }
}
