package test33.mail;

/**
 * Created by chin on 11/16/16.
 */
public class Mail {

    private String groupId;
    private String title;
    private String content;
    private String targetAddr;
    private String sourceAddr;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTargetAddr() {
        return targetAddr;
    }

    public void setTargetAddr(String targetAddr) {
        this.targetAddr = targetAddr;
    }

    public String getSourceAddr() {
        return sourceAddr;
    }

    public void setSourceAddr(String sourceAddr) {
        this.sourceAddr = sourceAddr;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "groupId='" + groupId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", targetAddr='" + targetAddr + '\'' +
                ", sourceAddr='" + sourceAddr + '\'' +
                '}';
    }

    public Mail(String groupId, String title, String content, String targetAddr, String sourceAddr) {
        this.groupId = groupId;
        this.title = title;
        this.content = content;
        this.targetAddr = targetAddr;
        this.sourceAddr = sourceAddr;
    }
}
