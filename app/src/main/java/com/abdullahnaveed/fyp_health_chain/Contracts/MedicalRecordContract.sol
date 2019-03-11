pragma solidity >=0.4.21 <0.6.0;

contract MedicalRecordContract {

    struct MedicalRecord {
        uint id;
        string patientPPS;
        string encryptedRecord;
    }

    mapping (uint => MedicalRecord) public medicalRecords; //stores all the encrypted medical records
    uint recordsCounter; //counter

    function storeMedicalRecord(string memory  _patientPPS, string memory  _encryptedRecord) public {
        recordsCounter++;
        medicalRecords[recordsCounter] = MedicalRecord(recordsCounter, _patientPPS, _encryptedRecord);
    }

    function getNumberOfRecords() public view returns (uint){
        return recordsCounter;
    }

    function retrieveMedicalRecord(uint counter) public view returns (uint, string memory, string memory){
        return (medicalRecords[counter].id, medicalRecords[counter].patientPPS, medicalRecords[counter].encryptedRecord);
    }

}
