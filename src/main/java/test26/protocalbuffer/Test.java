package test26.protocalbuffer;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.Arrays;

/**
 * Created by chin on 10/22/16.
 */
public class Test {

    //http://aiilive.blog.51cto.com/1925756/1563449
    //http://blog.csdn.net/zhu_xun/article/details/19397081
    //https://developers.google.com/protocol-buffers/docs/javatutorial
    //http://f.dataguru.cn/forum.php?mod=viewthread&tid=664756&highlight=protocol%2Bbuffers

    public static void main(String[] args) {
        //构建一个Person对象
        AddressBookProtos.Person person = AddressBookProtos.Person
                .newBuilder()
                .setEmail("zhangsan@163.com")
                .setId(10086)
                .setName("zhangsan")
                .addPhone(
                        AddressBookProtos.Person.PhoneNumber.newBuilder().setNumber("186")
                                .setType(AddressBookProtos.Person.PhoneType.HOME).build())
                .build();
        System.out.println("打印输出Person对象信息：");
        System.out.println(person);
        System.out.println("Person对象调用toString()方法：");
        System.out.println(person.toString());

        System.out.println("Person对象字段是否初始化：" + person.isInitialized());

        // 序列号
        System.out.println("Person对象调用toByteString()方法：");
        System.out.println(person.toByteString());

        System.out.println("Person对象调用toByteArray()方法:");
        System.out.println(Arrays.toString(person.toByteArray()));

        try {
            System.out.println("反序列化后的对象信息：");
            // 反序列化
            AddressBookProtos.Person newPerson = AddressBookProtos.Person.parseFrom(person.toByteArray());
            System.out.println(newPerson);
            newPerson = AddressBookProtos.Person.parseFrom(person.toByteString());
            System.out.println(newPerson);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        // 向地址簿添加两条Person信息
        AddressBookProtos.AddressBook.Builder books = AddressBookProtos.AddressBook.newBuilder();
        books.addPerson(person);
        books.addPerson(AddressBookProtos.Person.newBuilder(person).setEmail("tom@163.com")
                .build());
        System.out.println("AddressBook对象信息：");
        System.out.println(books.build());

    }
}
