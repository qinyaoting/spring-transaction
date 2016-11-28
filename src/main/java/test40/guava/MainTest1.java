package test40.guava;


import com.google.common.base.*;

/**
 * Created by chin on 11/28/16.
 *
 * http://www.cnblogs.com/Jack47/p/Guava-Common-Utilities.html
 *
 *
 */
public class MainTest1 {

    public static void main(String[] args) {
//        Optional<Integer> possible = Optional.of(null);
//        boolean isPresend = possible.isPresent();
//        System.out.println(isPresend);


        boolean invalid = Strings.isNullOrEmpty("");
        boolean invalid2 = Strings.isNullOrEmpty(null);
        boolean invalid3 = Strings.isNullOrEmpty("abc");
        //System.out.println(invalid3);


        int i = -1;
        //Preconditions.checkArgument(i>=0,"Argument was %s but expected nonnegative",i);


        boolean b = Objects.equal("a","a");
        boolean bb = Objects.equal(null,"a");
        boolean bbb = Objects.equal(null,null);
        System.out.println(bbb);

        //Objects.hashCode(field1, field2,...,fieldn)


        Person person = new Person(1,"tom",20.00);
        //System.out.println(person.toString());

        Joiner joiner = Joiner.on("; ").skipNulls();

        String str = joiner.join("Harry", null, "Ron", "Hermione");
        System.out.println(str);

        // 切分(Splitter)
        Splitter.on(',').split("foo, bar,,   qux,");


    }
}
