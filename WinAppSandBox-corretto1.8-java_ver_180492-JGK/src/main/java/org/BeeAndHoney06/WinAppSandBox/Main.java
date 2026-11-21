package org.BeeAndHoney06.WinAppSandBox;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        LogWritten logWritten = new LogWritten();
        FileIntegrity fileIntegrity = new FileIntegrity();
        logWritten.main(new String[]{});
        fileIntegrity.main(new String[]{});
    }
}