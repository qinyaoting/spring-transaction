package test17.codec;

/**
 * Created by wuf2 on 2/21/2015.
 */
public class GgaNmeaCodec extends ParametricSentenceCodec<GgaNmeaObject> {

    // 4.只放了个构造方法,其他都交给父类去做了

    public GgaNmeaCodec() {
        super(GgaNmeaObject.class);
    }
}
