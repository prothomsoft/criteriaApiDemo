package com.lumesse.tutorial.dto;

public class KeyValueEntry {

    private final Object key;
    private final Object value;

    public KeyValueEntry(Object key, Object value) {
        super();
        this.key = key;
        this.value = value;
    }

    public Object getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

}
