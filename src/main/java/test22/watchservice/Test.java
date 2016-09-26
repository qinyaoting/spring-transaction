package test22.watchservice;

import java.io.IOException;
import java.nio.file.*;

/**
 * Created by chin on 9/26/16.
 */
public class Test {

    public static void main(String[] args) throws IOException, InterruptedException {
        WatchService watcher = FileSystems.getDefault().newWatchService();
        Paths.get("/home/chin/company/workspace/github/spring-transaction").register(watcher,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        while (true) {
            WatchKey key = watcher.take();
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println(event.context() + " comes to " + event.kind());
            }

            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
    }
}