package com.dredh.model;

public class KeyGenerator {

    private int id;
    private int key;

    public KeyGenerator() {
    }

    public KeyGenerator(int id, int key) {
        this.id = id;
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
