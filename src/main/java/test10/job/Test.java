package test10.job;

/**
 * Created by chin on 8/29/16.
 */
public class Test {

    public static void main(String[] args) {


        AbstractJob job = new FtpJob();
        JobManager manager = JobManager.getInstance();
        String jobId = manager.addJob(job);


        AbstractJob runningJob = manager.getJob(jobId);
        System.out.println(runningJob.getStatus());

        //manager.cancel(String jobId);
        //manager.delete(String jobId;
        //manager.getAll();


        //manager观察job,当job完成时,manager可以得到状态,job观察manager,当取消job时,manger发消息给job,job收到消息,中断操作

        // 这个设计不好啊

    }
}
