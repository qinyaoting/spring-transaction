package test6.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by chin on 8/22/16.
 */



@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLInteger {

    //字段名称
    String name() default "";

    //字段附加属性
    Constrains constrain() default @Constrains;
}
