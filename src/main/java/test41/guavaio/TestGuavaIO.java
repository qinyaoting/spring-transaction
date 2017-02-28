package test41.guavaio;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by chin on 2/28/17.
 */
public class TestGuavaIO {

    public static void main(String[] args) throws IOException {

        //demoFileWrite("/home/chin/test.txt", "abcef");


        // Files类提供了readLines方法可以方便的读取文件的内容，如下demo代码：
        /*
        String testFilePath = "/home/chin/tcplog1.txt";
        File testFile = new File(testFilePath);
        List<String> lines = Files.readLines(testFile, Charsets.UTF_16);
        for (String line : lines) {
            System.out.println(line);
        }
        */

        // 读取大文件
        String testFilePath = "/home/chin/tcplog1.txt";
        File testFile = new File(testFilePath);
        CounterLine counter = new CounterLine();
        Files.readLines(testFile, Charsets.UTF_16, counter);
        System.out.println(counter.getResult());


        /**
         这个readLines的重载，需要我们实现一个LineProcessor的泛型接口，在这个接口的实现方法processLine方法中我们可以对行文本进行处理，getResult方法可以获得一个最终的处理结果，这里我们只是简单的返回了一个行计数。
         另外还有readBytes方法可以对文件的字节做处理，readFirstLine可以返回第一行的文本，Files.toString(File,Charset)可以返回文件的所有文本内容。
         */


        /**
         * Guava的Files类中还提供了其他一些文件的简捷方法。比如

         touch方法创建或者更新文件的时间戳。
         createTempDir()方法创建临时目录
         Files.createParentDirs(File) 创建父级目录
         getChecksum(File)获得文件的checksum
         hash(File)获得文件的hash
         map系列方法获得文件的内存映射
         getFileExtension(String)获得文件的扩展名
         getNameWithoutExtension(String file)获得不带扩展名的文件名
         */


    }


    /**
     * 往文件中写内容
     * @param fileName
     * @param content
     */
    public static void demoFileWrite(final String fileName, final String content)  {
        checkNotNull(fileName, "Provided file name for writing must NOT be null");
        checkNotNull(content, "Unable to write null contents");
        final  File newFile = new File(fileName);
        try {
            Files.write(content.getBytes(), newFile );
        } catch (IOException err) {
            /*err.println(  "ERROR trying to write to file '" + fileName + "' - "
                    + fileIoEx.toString());*/
        }
    }

    /**
     * 演示如何使用guava的Files.copy方法复制文件
     *
     * @param sourceFileName 复制的源文件名
     * @param targetFileName 目标文件名
     */
    public void demoSimpleFileCopy(
            final String sourceFileName, final String targetFileName)
    {
        checkNotNull(sourceFileName, "Copy source file name must NOT be null.");
        checkNotNull(targetFileName, "Copy target file name must NOT be null.");
        final File sourceFile = new File(sourceFileName);
        final File targetFile = new File(targetFileName);
        try
        {
            Files.copy(sourceFile, targetFile);
        }
        catch (IOException fileIoEx)
        {

        }
    }

    /**
     * 演示 Files.equal(File,File) 来比较两个文件的内容
     *
     * @param fileName1 比较的文件1文件名
     * @param fileName2 比较的文件2文件名
     */
    public void demoEqual(final String fileName1, final String fileName2)
    {
        checkNotNull(fileName1, "First file name for comparison must NOT be null.");
        checkNotNull(fileName2, "Second file name for comparison must NOT be null.");
        final File file1 = new File(fileName1);
        final File file2 = new File(fileName2);
        try
        {
            System.out.println(
                    "File '" + fileName1 + "' "
                            + (Files.equal(file1, file2) ? "IS" : "is NOT")
                            + " the same as file '" + fileName2 + "'.");
        }
        catch (IOException fileIoEx)
        {

        }
    }

}
class CounterLine implements LineProcessor<Integer> {
    private int rowNum = 0;
    @Override
    public boolean processLine(String line) throws IOException {
        rowNum ++;
        return true;
    }

    @Override
    public Integer getResult() {
        return rowNum;
    }
}
