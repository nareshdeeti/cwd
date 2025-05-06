package com.ndeeti.cwd;


import java.io.IOException;

public class CwdApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        CwdExecutor cwdExecutor = new CwdExecutor();
        String absoluteCWD = cwdExecutor.absoluteCWD();
        System.out.println("Absolute: " + absoluteCWD);
        System.out.println("Real: " + cwdExecutor.realCWD());
        String osName = System.getProperty("os.name");
        if (osName.equalsIgnoreCase("Linux")) {
            Thread.sleep(2000);
        }
    }

}
