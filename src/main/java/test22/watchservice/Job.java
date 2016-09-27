package test22.watchservice;

/**
 * Created by chin on 9/27/16.
 */
public class Job {

    private String exeid;

    private String jobid;

    public String getExeid() {
        return exeid;
    }

    public void setExeid(String exeid) {
        this.exeid = exeid;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    enum Status{WAITING, RUNNING,FINISHED,EXCEPTION};

    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
