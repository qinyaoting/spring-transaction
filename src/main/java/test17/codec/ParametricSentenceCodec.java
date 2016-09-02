package test17.codec;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wuf2 on 3/20/2015.
 */
public class ParametricSentenceCodec<T extends AbstractNmeaObject> extends AbstractNmeaCodec {  //类加了泛型AbstractNmeaObject的子类


    private final Logger logger = LoggerFactory.getLogger(ParametricSentenceCodec.class);

    private Constructor<T> ctor;    //类的构造器,传gga class,保存的就是gga的构造器,vdm的就是vdm的构造器

    ParametricSentenceCodec(Class<T> impl) {
        try {
            ctor = impl.getConstructor(String.class);
        } catch (NoSuchMethodException e) {
            logger.error("ctor fail: ", e);
            throw new AssertionError();
        }
    }

    // 5.父类的工作是,截断字符串----> 给对象赋值---->通知观察者

    @Override
    public void decode(String content) {
        Preconditions.checkNotNull(content);

        String rawContent = NmeaCodecUtil.makeRawContent(content);
        Tokenizer tokenizer = new Tokenizer(rawContent, NmeaConst.FIELD_SEP);

        try {
            final T object = ctor.newInstance(tokenizer.nextToken());   //GGA VDM RMC...会创建对应的对象    1.得有构造对象

            Arrays.stream(object.getClass().getFields())                // 全部字段
                    .filter(field -> field.isAnnotationPresent(SentenceField.class))        //只留注解字段
                    .sorted(new SentenceFieldAnnotationComparator())        //排序    3.需要排序
                    .forEach(field -> {             //遍历
                        try {
                            field.set(object, tokenizer.nextToken());       //赋值
                        } catch (Exception e) {
                            logger.error("decode fail: {}", e);
                            throw new IllegalArgumentException();       //不存在的字段
                        }
                    });

            logger.debug("{}", object);//4.就通过反射得到赋好值对象了
            setChanged();
            notifyObservers(object);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            logger.error("decode fail: ", e);
        }
    }

    @Override
    public List<String> encode(AbstractNmeaObject obj) {
        Preconditions.checkNotNull(obj);

        T object = (T) obj;
        StringBuilder sb = new StringBuilder();

        sb.append(object.getObjType()).append(NmeaConst.FIELD_SEP);
        String str = Arrays.stream(object.getClass().getFields())
                .filter(field -> field.isAnnotationPresent(SentenceField.class))
                .sorted(new SentenceFieldAnnotationComparator())
                .map(field -> {
                    try {
                        return field.get(object).toString();
                    } catch (IllegalAccessException e) {
                        logger.error("decode fail: {}", e);
                        throw new IllegalArgumentException();
                    }
                })                  //变成map
                .collect(Collectors.joining(","));      //用逗号连接起来
        sb.append(str);

        sb.append(NmeaCodecUtil.calcCheckSum(sb.toString()));
        sb.insert(0, NmeaConst.MSG_START);
        sb.append(NmeaConst.MSG_END);
        return Arrays.asList(sb.toString());
    }
}
