package test18.codec;

/**
 * Created by wuf2 on 2/21/2015.
 */
public class GllNmeaCodec extends ParametricSentenceCodec<GllNmeaObject> {
    public GllNmeaCodec() {
        super(GllNmeaObject.class);
    }
}
