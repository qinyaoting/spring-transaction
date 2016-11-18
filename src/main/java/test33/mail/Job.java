package test33.mail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by chin on 11/16/16.
 */
public class Job {

    private String jobId;

    public String getJobId() {
        return jobId;
    }

    private LinkedBlockingDeque<AbstractTask> tasks;

    public LinkedBlockingDeque<AbstractTask> getTasks() {
        return tasks;
    }

    public void setTasks(LinkedBlockingDeque<AbstractTask> tasks) {
        this.tasks = tasks;
    }

    private String ctime;
    private String etime;

    private String status;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Job(String jobId, LinkedBlockingDeque<AbstractTask> tasks) {
        this.jobId = jobId;
        this.tasks = tasks;
        ctime = sdf.format(new Date());
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
        return "";
    }

    public int getTaskSize() {
        return tasks.size();
    }
}
