package com.dredh.model;

public class KeyFetchRecord {

    private int key;
    private String server;

    public KeyFetchRecord(int key, String server) {
        this.key = key;
        this.server = server;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}
