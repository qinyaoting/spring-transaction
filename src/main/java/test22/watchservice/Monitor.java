package test22.watchservice;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

/**
 * Created by chin on 9/26/16.
 */
public class Monitor {

   private FileListener listener;
   private String filePath;

    public Monitor(String dir, FileListener lis) throws IOException, InterruptedException {
        this.filePath = dir;
        this.listener = lis;
        init(filePath);
    }


    public void init(String filePath) throws IOException, InterruptedException {

        WatchService watcher = FileSystems.getDefault().newWatchService();
        Paths.get(filePath).register(watcher,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        while (true) {
            WatchKey key = watcher.take();
            for (WatchEvent<?> event : key.pollEvents()) {
                //System.out.println(event.context() + " comes to " + event.kind());

                if (listener != null) {


                    if (event.kind().equals(StandardWatchEventKinds.ENTRY_CREATE)) {
                        listener.onFileCreated(new File(filePath+"/"+event.context()));
                    } else if (event.kind().equals(StandardWatchEventKinds.ENTRY_MODIFY)) {
                        listener.onFileModify(new File(filePath+"/"+event.context()));
                    } else {
                        //sout...
                    }
                }


            }

            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
    }

    public void start() {


    }
}
