package test40.guava;


import com.google.common.base.*;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chin on 11/28/16.
 *
 * http://www.cnblogs.com/Jack47/p/Guava-Common-Utilities.html
 *
 *
 */
public class MainTest2 {

    public static void main(String[] args) {
        ///Map<String, Map<Long, List<String>>> map = new HashMap<String, Map<Long,List<String>>>();

        //Map<String, Map<Long,List<String>>> map = Maps.newHashMap();


        ImmutableList<String> of = ImmutableList.of("a", "b", "c", "d");
        ImmutableMap<String,String> map = ImmutableMap.of("key1", "value1", "key2", "value2");



        int compare = Ints.compare(-2,2);
        System.out.println(compare);

        //assertEquals("89983", CharMatcher.DIGIT.retainFrom("some text 89983 and more"));
        //assertEquals("some text  and more", CharMatcher.DIGIT.removeFrom("some text 89983 and more"));

        int[] numbers = { 1, 2, 3, 4, 5 };

        String numberAsString = Joiner.on(";").join(Ints.asList(numbers));
        System.out.println(numberAsString);

        String str2 = Ints.join(";",numbers);


        Iterable split = Splitter.on(",").split(numberAsString);




        String testString = "foo , what,,,more,";
        Iterable<String> split2 = Splitter.on(",").omitEmptyStrings().trimResults().split(testString);
        System.out.println();




    }
}
