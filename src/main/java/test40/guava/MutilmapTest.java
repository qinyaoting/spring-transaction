package test40.guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chin
 * Date: 5/28/18
 * Time: 2:06 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class MutilmapTest {


    // 我们代码中经常出现以下这种数据结构，比如key是类目，value是个List，list存储着这个类目下各个子类目的统计数据等。
    // Map<String,List<MyClass>> myClassListMap = new HashMap<String,List<MyClass>>()

    public static void main(String[] args) {
        Multimap<String, String> myMultimap = ArrayListMultimap.create();
        myMultimap.put("女装", "内衣");
        myMultimap.put("女装", "羽绒服");
        myMultimap.put("女装", "风衣");
        myMultimap.put("男装", "皮夹克");
        // 获取key "女装"对应的list
        Collection<String> womenDressList = myMultimap.get("女装");
        System.out.println(womenDressList.toString());
        // 删除key "女装"对应List中的"羽绒服"
        myMultimap.remove("女装", "羽绒服");
        System.out.println(womenDressList.toString());
    }


    /* 插入时候, 需要这样处理, 如果删除起中的某个类别会更繁琐, 用上边的方法来替代
    void putMyObject(String key, Object value) {
        List<Object> myClassList = myClassListMap.get(key);
        if(myClassList == null) {
            myClassList = new ArrayList<object>();
            myClassListMap.put(key,myClassList);
        }
        myClassList.add(value);
    }*/
}
