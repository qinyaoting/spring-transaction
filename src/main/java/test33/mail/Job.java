package test33.mail;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chin on 11/16/16.
 */
public class Job {

    private Mail mail;

    private String ctime;
    private String etime;

    private String status;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Job(Mail mail) {
        this.mail = mail;
        ctime = sdf.format(new Date());
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        etime = sdf.format(new Date());
    }

    @Override
    public String toString() {
        return "Job{" +
                "mail=" + mail +
                ", ctime='" + ctime + '\'' +
                ", etime='" + etime + '\'' +
                ", status='" + status + '\'' +
                ", sdf=" + sdf +
                '}';
    }
}
