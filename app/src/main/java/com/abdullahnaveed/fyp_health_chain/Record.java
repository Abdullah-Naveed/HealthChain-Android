package com.abdullahnaveed.fyp_health_chain;

public class Record {

    private String ppsNumber;
    private String encryptedRecord;

    public Record(String ppsNumber, String encryptedRecord) {
        this.ppsNumber = ppsNumber;
        this.encryptedRecord = encryptedRecord;
    }

    public String getPpsNumber() {
        return ppsNumber;
    }

    public void setPpsNumber(String ppsNumber) {
        this.ppsNumber = ppsNumber;
    }

    public String getEncryptedRecord() {
        return encryptedRecord;
    }

    public void setEncryptedRecord(String encryptedRecord) {
        this.encryptedRecord = encryptedRecord;
    }
}
