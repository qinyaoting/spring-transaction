package test18.codec;

import com.google.common.base.MoreObjects;

/**
 * Created by wuf2 on 2/21/2015.
 */
public class GllNmeaObject extends AbstractNmeaObject {


    /**
     String content = "$GPGLL,3751.65,S,14507.36,E*77\r\n";
     codec.decode(content);

     content = "$GPGLL,4916.45,N,12311.12,W,225444,A\r\n";
     codec.decode(content);

     content = "$GPGLL,5133.81,N,00042.25,W*75\r\n";
     codec.decode(content);
     */

    @SentenceField(order = 0)
    public String latitude;

    @SentenceField(order = 1)
    public String directionOfLatitude;

    @SentenceField(order = 2)
    public String longitude;

    @SentenceField(order = 3)
    public String directionOfLongitude;

    @SentenceField(order = 4)
    public String utcTime;

    @SentenceField(order = 5)
    public String dataValid;

    @SentenceField(order = 6)
    public String modeIndicator;

    public GllNmeaObject() {
        super(NmeaConst.MSG_TYPE_GLL);
    }

    public GllNmeaObject(String objType) {
        super(objType);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .addValue(super.toString())
                .add("latitude", latitude)
                .add("directionOfLatitude", directionOfLatitude)
                .add("longitude", longitude)
                .add("directionOfLongitude", directionOfLongitude)
                .add("utcTime", utcTime)
                .add("dataValid", dataValid)
                .add("modeIndicator", modeIndicator)
                .toString();
    }
}
