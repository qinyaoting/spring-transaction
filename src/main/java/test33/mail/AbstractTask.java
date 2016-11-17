package test33.mail;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chin on 11/17/16.
 */
public abstract class AbstractTask {

    protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String taskId;
    private String ctime;
    private String etime;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    private String status;

    public void setStatus(String status) {
        this.status = status;
        setEtime(sdf.format(new Date()));
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "{" +
                "taskId='" + taskId + '\'' +
                ", ctime='" + ctime + '\'' +
                ", etime='" + etime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
