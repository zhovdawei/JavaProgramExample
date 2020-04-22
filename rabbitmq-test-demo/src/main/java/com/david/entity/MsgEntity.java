package com.david.entity;

public class MsgEntity {

    public MsgEntity(String batchId, int index, String message) {
        this.batchId = batchId;
        this.index = index;
        this.message = message;
    }

    private String batchId;

    private int index;

    private String message;

    public String getBatchId() {
        return batchId;
    }

    public int getIndex() {
        return index;
    }

    public String getMessage() {
        return message;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MsgEntity{" +
                "batchId='" + batchId + '\'' +
                ", index=" + index +
                ", message='" + message + '\'' +
                '}';
    }
}
