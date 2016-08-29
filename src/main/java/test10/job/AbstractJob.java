package test10.job;

import java.util.Observable;

/**
 * Created by chin on 8/29/16.
 */
public abstract class AbstractJob extends Observable {

    private String jobId = "";

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public abstract void begin();


    public JobStatus getStatus() {

        return JobStatus.RUNNING;
    }

    public enum JobStatus {DONE,ERROR,RUNNING,WAITING};


    // 取消job,需要接收manager的信息,把任务停掉,这也是个观察者?
}
