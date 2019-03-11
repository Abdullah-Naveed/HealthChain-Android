package com.abdullahnaveed.fyp_health_chain;

import java.util.ArrayList;
import java.util.HashMap;

public class Patient {

    private String id;
    private String name;
    private int age;
    private String gender;
    private String address;
    private String nationality;
    private String ppsNumber;
    private String gpNumber;
    private String ethAddress;
    private String secretKey;
    private ArrayList<HashMap<String, String>> trustedGPs;

    public Patient(String id, String name, int age, String gender, String address, String nationality, String ppsNumber, String gpNumber, String ethAddress, String secretKey) {
        this.trustedGPs = new ArrayList<>();
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.nationality = nationality;
        this.ppsNumber = ppsNumber;
        this.gpNumber = gpNumber;
        this.ethAddress = ethAddress;
        this.secretKey = secretKey;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getNationality() { return nationality; }

    public void setNationality(String nationality) { this.nationality = nationality; }

    public String getPpsNumber() { return ppsNumber; }

    public void setPpsNumber(String ppsNumber) { this.ppsNumber = ppsNumber; }

    public String getGpNumber() { return gpNumber; }

    public void setGpNumber(String gpNumber) { this.gpNumber = gpNumber; }

    public String getEthAddress() { return ethAddress; }

    public void setEthAddress(String ethAddress) { this.ethAddress = ethAddress; }

    public String getSecretKey() { return secretKey; }

    public void setSecretKey(String secretKey) { this.secretKey = secretKey; }

    public ArrayList<HashMap<String, String>> getTrustedGPs() { return trustedGPs; }

    public void setTrustedGPs(ArrayList<HashMap<String, String>> trustedGPs) { this.trustedGPs = trustedGPs; }

    public void addTrustedGP(String GPName, String GPEthAddress){
        HashMap<String, String> map = new HashMap<>();
        map.put(GPName, GPEthAddress);
        this.trustedGPs.add(map);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", age=" + getAge() +
                ", gender='" + getGender() + "'" +
                ", address='" + getAddress() + "'" +
                ", nationality='" + getNationality() + "'" +
                ", ppsNumber='" + getPpsNumber() + "'" +
                ", gpNumber='" + getGpNumber() + "'" +
                ", ethAddress='" + getEthAddress() + "'" +
                ", trustedGPs='" + getTrustedGPs() + "'" +
                "}";
    }

    public static final class PatientBuilder {
        private String id;
        private String name;
        private int age;
        private String gender;
        private String address;
        private String nationality;
        private String ppsNumber;
        private String gpNumber;
        private String ethAddress;
        private String secretKey;

        public PatientBuilder() {
        }

        public PatientBuilder id(String id) {
            this.id = id;
            return this;
        }

        public PatientBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PatientBuilder age(int age) {
            this.age = age;
            return this;
        }

        public PatientBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public PatientBuilder address(String address) {
            this.address = address;
            return this;
        }

        public PatientBuilder nationality(String nationality) {
            this.nationality = nationality;
            return this;
        }

        public PatientBuilder ppsNumber(String ppsNumber) {
            this.ppsNumber = ppsNumber;
            return this;
        }

        public PatientBuilder gpNumber(String gpNumber) {
            this.gpNumber = gpNumber;
            return this;
        }

        public PatientBuilder ethAddress(String ethAddress) {
            this.ethAddress = ethAddress;
            return this;
        }

        public void secretKey(String secretKey) {
            this.secretKey = secretKey;
        }

        public Patient build() {
            return new Patient(id, name, age, gender, address, nationality, ppsNumber, gpNumber, ethAddress, secretKey);
        }
    }
}
