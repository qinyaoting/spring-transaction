package test22.watchservice;

import java.io.File;
import java.io.IOException;

/**
 * Created by chin on 9/26/16.
 */
public interface FileListener {


    public void onFileCreated(File file) throws IOException;

    public void onFileModify(File file) throws IOException;




}
