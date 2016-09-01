package test10.job;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;


public class CmdUtil {

    /**
     * 所有任务列表
     *
     * @return
     */
    public static List<String> tasklist() {
        return getLines(exec("tasklist"));
    }

    /**
     * 所有服务列表
     *
     * @return
     */
    public static List<String> services() {
        return getLines(exec("net", "start"));
    }

    /**
     * 根据端口号获得PID
     *
     * @param port
     * @return
     */
    public static List<String> getPidByPort(String... port) {
        List<String> lines = getLines(exec("netstat", "-ano"));
        for(int i = 0; i < port.length; i++) {
            port[i] = "^\\s*(\\d+\\.){3}\\d+:" + port[i] + "\\s*$";
        }
        return getColByAnotherCol(lines, "本地地址", "PID", port);
    }

    /**
     * 根据PID获取程序名称
     * @param pid
     * @return
     */
    public static List<String> getProcByPid(String... pid) {
        if (empty(pid)) return Collections.emptyList();

        List<String> tasks = tasklist();
        return getColByAnotherCol(tasks, "PID", "映像名称", pid);
    }

    /**
     * 根据端口号获得程序名
     * @param port
     * @return
     */
    public static List<String> getProcByPort(String... port) {
        return getProcByPid(getPidByPort(port).toArray(new String[0]));
    }

    /**
     * 直接执行bat文件(不会显示窗口)
     * @param batPath
     * @return
     */
    public static Process bat(String batPath) {
        return exe(batPath);
    }
    /**
     * 执行bat文件,显示控制台窗口
     * @param path
     */
    public static Process batWithWindow(String path) {
        return exec("cmd.exe", "/c", "start" , path);
    }

    /**
     * 执行bat文件，会显示窗口
     * @param batFile
     * @param dir
     * @return
     */
    public static Process batWithWindow(String batFile, File dir) {
        try {
            return Runtime.getRuntime().exec("cmd.exe /c start " + batFile, null, dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 执行bat文件但不显示控制台窗口
     * @param path
     */
    public static Process batNoWindow(String path) {
        return exe("cmd.exe /c " + path);
    }

    /**
     * 释放进程的输入流和错误流
     * @param proc
     */
    public static void releaseCache(final Process proc) {
        if(proc == null) {
            return;
        }
        new Thread() {
            @Override
            public void run() {
                InputStream in = proc.getInputStream();
                byte[] cache = new byte[1024];
                int len = -1;
                try {
                    while((len = in.read(cache)) != -1) {
                        System.out.println("INPUT:" + new String(cache, 0, len, "GBK"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("INPUT:release cache finished.");
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                InputStream in = proc.getErrorStream();
                byte[] cache = new byte[1024];
                int len = -1;
                try {
                    while((len = in.read(cache)) != -1) {
                        System.out.println("ERROR:" + new String(cache, 0, len, "GBK"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("ERROR:release cache finished.");
            }
        }.start();
    }

    /**
     * 使用系统默认程序打开文件
     * @param path
     * @return
     */
    public static Process execFile(String path) {
        return exec("cmd", "/c", "\"" + path + "\"");
    }

    /**
     * 杀死进程
     * @param proc
     */
    public static void taskkill(String proc) {
        if(!empty(proc)) {
            exec("taskkill", "/f", "/im", proc);
        }
    }

    /**
     * 通过pid结束进程
     *
     * @param pid
     */
    public static void taskkillByPID(String pid) {
        if (!empty(pid)) {
            exe("taskkill /pid " + pid + " /f");
        }
    }

    /**
     * 获取所有进程信息
     * @return
     */
    public static List<String> getProcessInfo() {
        return getLines(exec("wmic", "process"));
    }

    /**
     * 获取指定进程的启动路径
     * @param caption
     * @return 当有多个进程符合时，返回多个路径，若没有，则返回一个空集合
     */
    public static List<String> getProcessPath(String caption) {
        Process proc = exe("wmic process where caption=\"" + caption + "\" get ExecutablePath");
        // 如果不关掉输出流，在XP，2003上执行会堵塞程序
        try {
            proc.getOutputStream().flush();
            proc.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> lines = getLines(proc, 3000);
        List<String> paths = new ArrayList<String>();
        for(String line : lines) {
            if(trimEmpty(line)) continue;
            if(line.contains(caption)) {
                paths.add(line.trim());
            }
        }
        return paths;
    }

    /**
     * 关闭所有的cmd进程
     */
    public static void killAllCmdProcess() {
        exe("cmd.exe /C start wmic process where name='cmd.exe' call terminate");
    }


    /**
     * windows上执行命令行
     *
     * @param cmdarray
     * @return
     */
    public static Process exec(String... cmdarray) {
        try {
            return Runtime.getRuntime().exec(cmdarray);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 单命令执行
     * @param cmd
     * @return
     */
    public static Process exe(String cmd) {
        try {
            return Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 获取子进程的输出流，每一行对应返回结果集合的一个元素
     *
     * @param proc
     * @return
     */
    public static List<String> getLines(Process proc) {
        List<String> lines = new ArrayList<String>();
        if (proc != null) {
            InputStream istream = proc.getInputStream();
            if (istream != null) {
                InputStreamReader isr = new InputStreamReader(istream, Charset.forName("GBK"));
                BufferedReader reader = new BufferedReader(isr);
                String line = null;
                try {
                    while ((line = reader.readLine()) != null) {
                        lines.add(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        istream.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
        return lines;
    }

    /**
     * 读取proc的输入流，新建一个线程来读取，millis秒之后返回<br>
     * 如果读取proc的输入流有可能导致堵塞的话，可以使用该方法
     * @param proc
     * @param millis
     * @return
     */
    private static List<String> getLines(Process proc, long millis) {
        final List<String> lines = new ArrayList<String>();
        if (proc != null) {
            final InputStream istream = proc.getInputStream();
            if (istream != null) {

                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        InputStreamReader isr = new InputStreamReader(istream, Charset.forName("GBK"));
                        BufferedReader reader = new BufferedReader(isr);
                        String line = null;
                        try {
                            while ((line = reader.readLine()) != null) {
                                lines.add(line);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                istream.close();
                            } catch (IOException e) {}
                        }
                    }
                });

                thread.start();

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {

                }

            }
        }
        return lines;
    }

    /**
     * 将字符串按照空格进行分割，空格判断规则：\\s+
     *
     * @param line
     * @return
     */
    public static String[] splitBySpace(String line) {
        if (empty(line)) {
            return new String[0];
        }
        return line.trim().split("\\s+");
    }

    /**
     * 字符串是否为空
     *
     * @param s
     * @return
     */
    public static boolean empty(String s) {
        return s == null || s.length() < 1;
    }

    /**
     * 判断集合是否为空
     * @param col
     * @return
     */
    public static boolean empty(Collection<?> col) {
        return col == null || col.size() == 0;
    }

    /**
     * 数组是否为空
     * @param arr
     * @return
     */
    public static <T> boolean empty(T[] arr) {
        return arr == null || arr.length == 0;
    }

    /**
     * 当字符串为null,"",或去掉前后的空格后为空时，返回true
     * @param s
     * @return
     */
    public static boolean trimEmpty(String s) {
        return empty(s) || s.trim().length() < 1;
    }

    /**
     * 根据col1的值获得col2的值
     * @param lines 每一行代表一行记录
     * @param col1 第一列的标题
     * @param col2 第二列的标题
     * @param col1Pattern 第一列的匹配，若第一列的值得到匹配，则返回相应行的col2列的值
     * @return
     */
    public static List<String> getColByAnotherCol(List<String> lines, String col1, String col2, String... col1Pattern) {
        List<String> col2Value = new ArrayList<String>();
        if(empty(lines)) return col2Value;

        // 1. 查找标题
        int col1_i = -1, col2_i = -1, lines_i = 0;
        int lines_size = lines.size();
        for (; lines_i < lines_size; lines_i++) {
            String[] words = splitBySpace(lines.get(lines_i));
            if (words.length == 0) continue;
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(col1)) col1_i = i;
                if (words[i].equals(col2)) col2_i = i;
            }
            if (col1_i != -1 && col2_i != -1) break;
        }
        if (col1_i == -1 || col2_i == -1) return col2Value;

        // 2. 查找值
        for (lines_i++; lines_i < lines_size; lines_i++) {
            String[] words = splitBySpace(lines.get(lines_i));
            for(String p : col1Pattern) {
                if (Pattern.matches(p, words[col1_i])) col2Value.add(words[col2_i < words.length ? col2_i : words.length - 1]);
            }
        }

        return col2Value;
    }

    /**
     * 获得系统CPU使用率
     * @return 类似30.0或0.0
     */
    public static double getCpuRatioForWindows() {
        // get后的名称可在命令行下使用wmic process get /?查看
        String procCmd = System.getenv("windir")
                + "//system32//wbem//wmic.exe process get "
                + "Caption,CommandLine,KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";
        // 取进程信息
        long[] c0 = readCpu(exe(procCmd));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        long[] c1 = readCpu(exe(procCmd));
        if (c0 != null && c1 != null) {
            // 5毫秒内两次空闲时间、忙碌时间差
            long idletime = c1[0] - c0[0];
            long busytime = c1[1] - c0[1];
            // 忙碌时间 / 总时间
            return 100 * (busytime) * 1.0 / (busytime + idletime);
        } else {
            return 0.0;
        }
    }
    /**
     * 解析“获取CUP使用率进程”的输出，计算出系统空闲时间和忙碌时间
     * @param proc
     * @return [0] => 空闲时间，[1] => 忙碌时间，或者null
     */
    private static long[] readCpu(final Process proc) {
        if(proc == null) {
            return null;
        }
        long[] retn = new long[2];
        try {
            proc.getOutputStream().close();
            InputStreamReader ir = new InputStreamReader(proc.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line = input.readLine(); // 标题行
            if (line == null || line.length() < 10) {
                return null;
            }
            int capidx = line.indexOf("Caption");
            int cmdidx = line.indexOf("CommandLine");
            int kmtidx = line.indexOf("KernelModeTime");
            int rocidx = line.indexOf("ReadOperationCount");
            // 缺少ThreadCount的索引
            int umtidx = line.indexOf("UserModeTime");
            int wocidx = line.indexOf("WriteOperationCount");
            long idletime = 0; // 闲置时间
            long kneltime = 0;
            long usertime = 0;
            while ((line = input.readLine()) != null) {
                if (line.length() < wocidx) {
                    continue;
                }
                // 字段出现顺序：Caption,CommandLine,KernelModeTime,ReadOperationCount,
                // ThreadCount,UserModeTime,WriteOperation
                String caption = StringUtil.substr_byte(line, capidx, cmdidx).trim();
                String cmd = StringUtil.substr_byte(line, cmdidx, kmtidx).trim();
                if (cmd.indexOf("wmic.exe") >= 0) {
                    continue;
                }
                String s1 = StringUtil.substr_byte(line, kmtidx, rocidx).trim(); // KernelModeTime
                String s2 = StringUtil.substr_byte(line, umtidx, wocidx).trim(); // UserModeTime
                if (caption.equals("System Idle Process") || caption.equals("System")) {
                    if (s1.length() > 0) idletime += StringUtil.convertLong(StringUtil.firstNumberFromStr(s1), 0L);
                    if (s2.length() > 0) idletime += StringUtil.convertLong(StringUtil.firstNumberFromStr(s2), 0L);
                    continue;
                }
                if (s1.length() > 0) kneltime += StringUtil.convertLong(StringUtil.firstNumberFromStr(s1), 0L);
                if (s2.length() > 0) usertime += StringUtil.convertLong(StringUtil.firstNumberFromStr(s2), 0L);
            }
            retn[0] = idletime;
            retn[1] = kneltime + usertime;
            return retn;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                proc.getInputStream().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }



}
