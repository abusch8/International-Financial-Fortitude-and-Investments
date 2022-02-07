package com.iffi.entity;

public abstract class Asset {

    private final String code;
    private final String label;

    protected Asset(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }
}
