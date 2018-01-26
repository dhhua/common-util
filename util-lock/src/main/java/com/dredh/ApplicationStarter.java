package com.dredh;

public class ApplicationStarter {

    public static void main(String[] args) {

        new LockTester("server" + args[0]).run();
    }

}
