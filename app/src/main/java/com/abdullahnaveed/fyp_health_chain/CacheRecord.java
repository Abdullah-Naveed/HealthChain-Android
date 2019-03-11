package com.abdullahnaveed.fyp_health_chain;

public class CacheRecord {
    private int ID;
    private String userName;
    private String medicalRecord;

    public CacheRecord(int ID, String userName, String medicalRecord) {
        this.ID = ID;
        this.userName = userName;
        this.medicalRecord = medicalRecord;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}
