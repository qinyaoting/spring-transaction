package test10.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by chin on 8/29/16.
 */
public class JobManager implements Observer{

    public static final int JOB_RUNNING_SIZE = 20;
    private List<AbstractJob> running = new ArrayList<AbstractJob>(JOB_RUNNING_SIZE);
    private List<AbstractJob> error = new ArrayList<AbstractJob>();
    private List<AbstractJob> done = new ArrayList<AbstractJob>();//太多的集合

    public AbstractJob getJob(String jobId) {

        return null;
    }

    public String addJob(AbstractJob job) {

        running.add(job);
        return "jobid111";
    }

    public static JobManager ins = new JobManager();

    //取消和删除,需要通知job,

    public static JobManager getInstance() {
        return ins;
    }


    private JobManager() {

        // 启动5个线程,从running中取出job,运行,如果running空,阻塞,如果
        // 阻塞队列
        AbstractJob aj = running.get(0);
        aj.addObserver(this);
        aj.begin();
    }


    @Override
    public void update(Observable o, Object arg) {

        AbstractJob job = (AbstractJob)arg;
        if (job.getStatus() == AbstractJob.JobStatus.DONE) {
            done.add(job);
        } else if (job.getStatus() == AbstractJob.JobStatus.ERROR) {
            error.add(job);
        }
        // 这个设计不好


    }
}
