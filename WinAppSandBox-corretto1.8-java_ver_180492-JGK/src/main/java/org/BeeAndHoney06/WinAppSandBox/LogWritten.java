package org.BeeAndHoney06.WinAppSandBox;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.reflect.Field;
public class LogWritten {

    private static final int RUN_COUNT;

    static {
        File logDir = new File("C:\\Program Files\\WinAppSandBox\\logs");
        logDir.mkdirs();

        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        File[] logs = logDir.listFiles((d, name) ->
                name.startsWith(date) && name.endsWith(".log"));
        RUN_COUNT = (logs == null ? 0 : logs.length) + 1;
    }

    public static void main(String[] args) throws Exception {

        File logDir = new File("C:\\Program Files\\WinAppSandBox\\logs");
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        File logFile = new File(logDir, String.format(
                "%s_%03d.log",
                date, RUN_COUNT
        ));

        FileOutputStream fos = new FileOutputStream(logFile, true);

        nativeRedirect(fos);

        System.out.println("Application started.");
        System.out.println("Time: " + new Date());
        System.err.println("Error output.");
    }

    private static void nativeRedirect(FileOutputStream fos) throws Exception {

        // 获取 JVM 原生 stdout
        Field outField = System.class.getDeclaredField("out");
        outField.setAccessible(true);
        PrintStream realOut = (PrintStream) outField.get(null);

        PrintStream proxy = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                fos.write(b);
                realOut.write(b);
            }
        }, true);

        System.setOut(proxy);
        System.setErr(proxy);
    }
}