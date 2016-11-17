package test33.mail;

import java.util.Date;
import java.util.UUID;

/**
 * Created by chin on 11/16/16.
 */
public class MailTask extends AbstractTask{

    private String title;
    private String content;
    private String targetAddr;
    private String sourceAddr;

    public MailTask(String title, String content, String targetAddr, String sourceAddr) {
        this.title = title;
        this.content = content;
        this.targetAddr = targetAddr;
        this.sourceAddr = sourceAddr;
        setTaskId(UUID.randomUUID().toString());
        setCtime(sdf.format(new Date()));

    }

    @Override
    public String toString() {
        return super.toString() + "Mail{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", targetAddr='" + targetAddr + '\'' +
                ", sourceAddr='" + sourceAddr + '\'' +
                "} " ;
    }
}
