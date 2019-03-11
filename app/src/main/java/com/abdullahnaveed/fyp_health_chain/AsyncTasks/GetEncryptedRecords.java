package com.abdullahnaveed.fyp_health_chain.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.abdullahnaveed.fyp_health_chain.MedicalRecordContract;
import com.abdullahnaveed.fyp_health_chain.Record;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple3;

import java.math.BigInteger;
import java.util.ArrayList;

import static org.web3j.tx.gas.DefaultGasProvider.GAS_LIMIT;
import static org.web3j.tx.gas.DefaultGasProvider.GAS_PRICE;

public class GetEncryptedRecords extends AsyncTask<Void, Void, ArrayList<Record>> {

    private ArrayList<Record> records = new ArrayList<>();

    public ArrayList<Record> getRecords() {
        return records;
    }

    public void addRecord(Record record){
        records.add(record);
    }

    @Override
    protected ArrayList<Record> doInBackground(Void... voids) {

        try {
            Web3j web3j = Web3j.build(new HttpService("https://ropsten.infura.io/v3/7777dae91b0642888389c02a139b1baf"));
            Log.d("Connected to Ethereum client version: ", web3j.web3ClientVersion().send().getWeb3ClientVersion());
            Log.d("web3",web3j.toString());

            String privateKey = "8A14C354113FEFEA21BF33C5C8C18F338A554435DA1904EBD5F7B1612361BCCA";
            BigInteger privkey = new BigInteger(privateKey, 16);
            ECKeyPair ecKeyPair = ECKeyPair.create(privkey);
            Credentials credentials = Credentials.create(ecKeyPair);
            MedicalRecordContract medicalRecordContract = MedicalRecordContract.load(
                    "0xC806dD79B2a95AC351adc3bf565FD2Dc22b7ec60", web3j, credentials, GAS_PRICE, GAS_LIMIT);

            Log.d("VALID: ", String.valueOf(medicalRecordContract.isValid()));


            BigInteger numOfRecords = medicalRecordContract.getNumberOfRecords().send();

            for (BigInteger bi = BigInteger.valueOf(1);
                 bi.compareTo(numOfRecords) <= 0;
                 bi = bi.add(BigInteger.ONE)) {

                Tuple3<BigInteger, String, String> record = medicalRecordContract.retrieveMedicalRecord(bi).send();
                publishProgress(new Record(record.getValue2(), record.getValue3()));
            }

            return records;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void publishProgress(Record record) {
        super.onProgressUpdate();
        System.out.println("here G");
        addRecord(record);
    }
}
