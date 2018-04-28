package test19.reflect;

import org.apache.htrace.fasterxml.jackson.core.JsonProcessingException;
import org.apache.htrace.fasterxml.jackson.databind.ObjectMapper;
import test17.codec.SentenceFieldAnnotationComparator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chin on 9/7/16.
 */
public class Test {

    public static void main(String[] args) throws JsonProcessingException {


        // 反射获得对象的属性,如果属性是私有的,不能得到

        Object dog = new Dog();
        Arrays.stream(dog.getClass().getFields())
                .forEach(field -> {
                    System.out.println("dog object field:" + field);
                });


        //假设客户端传过来个map<field,value>

        Map<String, Object> map = new HashMap<String,Object>();
        map.put("name","tom");
        map.put("age",22);
        //map.put("height",175.0);
        map.put("height",null);     //付空值不会有异常
        map.put("sex","F");         //没有在对象属性上做标记, 得不到值

        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(map);
        System.out.printf("客户端传过来的参数是 %s \n", requestJson);

        // 通过反射赋值
        Object obj = new Person();
        System.out.println(obj.toString());
        Arrays.stream(obj.getClass().getFields())
                .filter(field -> field.isAnnotationPresent(ReflectField.class))
                //.sorted(new ReflectFieldAnnotationComparator())
                .forEach(field -> {
                    try {
                        Object val = map.get(field.getName());
                        if (val != null) {
                            field.set(obj, val);
                        }

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });


        System.out.printf("通过反射获得的参数是 %s \n", obj.toString());







        Map<String, Object> map2 = new HashMap<String,Object>();
        map2.put("name","cat");
        map2.put("age",2);
        map2.put("height",50.5);
        map2.put("sex","F");            //Animal对象没有这个属性

        // 通过反射赋值
        Object obj2 = new Animal();
        System.out.println(obj2.toString());
        Arrays.stream(obj2.getClass().getFields())
                .filter(field -> field.isAnnotationPresent(ReflectField.class))
                        //.sorted(new ReflectFieldAnnotationComparator())
                .forEach(field -> {
                    try {
                        Object val = map2.get(field.getName());
                        if (val != null) {
                            field.set(obj2,val);
                        }

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });


        System.out.println(obj2.toString());


    }
}
