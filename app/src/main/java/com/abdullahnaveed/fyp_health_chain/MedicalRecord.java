package com.abdullahnaveed.fyp_health_chain;

public class MedicalRecord {

    /**
     * PROBABLY WONT BE USED. BUT THE FIELDS ARE THE SAME!
     * Keep for now...
     */

    private String interactionType; //interaction type: call/visit/scheduled appointment/phone ... drop down menu
    private String date;
    private String gpNumber;
    private String notes;
    private String prescription;
    private String outcome; //resolved/referred to hospital/another visit required/.. drop down menu

    public MedicalRecord(String interactionType, String date, String gpNumber, String notes, String prescription, String outcome) {
        this.interactionType = interactionType;
        this.date = date;
        this.gpNumber = gpNumber;
        this.notes = notes;
        this.prescription = prescription;
        this.outcome = outcome;
    }

    public String getInteractionType() {
        return interactionType;
    }

    public void setInteractionType(String interactionType) {
        this.interactionType = interactionType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGpNumber() {
        return gpNumber;
    }

    public void setGpNumber(String gpNumber) {
        this.gpNumber = gpNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPrescription() { return prescription; }

    public void setPrescription(String prescription) { this.prescription = prescription; }

    public String getOutcome() { return outcome; }

    public void setOutcome(String outcome) { this.outcome = outcome; }


    public static final class MedicalRecordBuilder {
        private String interactionType; //interaction type: call/visit/scheduled appointment/phone ... drop down menu
        private String date;
        private String gpNumber;
        private String notes;
        private String prescription;
        private String outcome; //resolved/referred to hospital/another visit required/.. drop down menu

        public MedicalRecordBuilder() {
        }

        public static MedicalRecordBuilder aMedicalRecord() {
            return new MedicalRecordBuilder();
        }

        public MedicalRecordBuilder interactionType(String interactionType) {
            this.interactionType = interactionType;
            return this;
        }

        public MedicalRecordBuilder date(String issueDate) {
            this.date = issueDate;
            return this;
        }

        public MedicalRecordBuilder gpNumber(String gpNumber) {
            this.gpNumber = gpNumber;
            return this;
        }

        public MedicalRecordBuilder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public MedicalRecordBuilder prescription(String prescription) {
            this.prescription = prescription;
            return this;
        }

        public MedicalRecordBuilder outcome(String outcome) {
            this.outcome = outcome;
            return this;
        }

        public MedicalRecord build() {
            return new MedicalRecord(interactionType, date, gpNumber, notes, prescription, outcome);
        }
    }
}
