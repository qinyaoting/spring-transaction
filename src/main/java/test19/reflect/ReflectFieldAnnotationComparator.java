package test19.reflect;

import test17.codec.SentenceField;

import java.lang.reflect.Field;
import java.util.Comparator;

/**
 * Created by wuf2 on 3/20/2015.
 */
public class ReflectFieldAnnotationComparator implements Comparator<Field> {
    @Override
    public int compare(Field o1, Field o2) {
        ReflectField a = o1.getAnnotation(ReflectField.class);
        ReflectField b = o2.getAnnotation(ReflectField.class);

        return new Integer(a.order()).compareTo(new Integer(b.order()));
    }
}
