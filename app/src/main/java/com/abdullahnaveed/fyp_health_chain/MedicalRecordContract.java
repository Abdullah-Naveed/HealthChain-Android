package com.abdullahnaveed.fyp_health_chain;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.1.1.
 */
public class MedicalRecordContract extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b5061068b806100206000396000f3fe608060405234801561001057600080fd5b5060043610610068577c0100000000000000000000000000000000000000000000000000000000600035046333045235811461006d5780636d1627871461019c5780637564a13c1461029e578063949f2cef146102bb575b600080fd5b61019a6004803603604081101561008357600080fd5b81019060208101813564010000000081111561009e57600080fd5b8201836020820111156100b057600080fd5b803590602001918460018302840111640100000000831117156100d257600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929594936020810193503591505064010000000081111561012557600080fd5b82018360208201111561013757600080fd5b8035906020019184600183028401116401000000008311171561015957600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295506102d5945050505050565b005b6101b9600480360360208110156101b257600080fd5b5035610343565b604051808481526020018060200180602001838103835285818151815260200191508051906020019080838360005b838110156102005781810151838201526020016101e8565b50505050905090810190601f16801561022d5780820380516001836020036101000a031916815260200191505b50838103825284518152845160209182019186019080838360005b83811015610260578181015183820152602001610248565b50505050905090810190601f16801561028d5780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b6101b9600480360360208110156102b457600080fd5b503561048a565b6102c36105c0565b60408051918252519081900360200190f35b600180548101808255604080516060810182528281526020808201878152828401879052600094855284825292909320815181559151805191949293610320938501929101906105c7565b506040820151805161033c9160028401916020909101906105c7565b5050505050565b60008181526020818152604080832080546001808301805485516002938216156101000260001901909116839004601f8101889004880282018801909652858152606096879694959294939092019290918491908301828280156103e85780601f106103bd576101008083540402835291602001916103e8565b820191906000526020600020905b8154815290600101906020018083116103cb57829003601f168201915b5050845460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152959750869450925084019050828280156104765780601f1061044b57610100808354040283529160200191610476565b820191906000526020600020905b81548152906001019060200180831161045957829003601f168201915b505050505090509250925092509193909250565b600060208181529181526040908190208054600180830180548551600293821615610100026000190190911692909204601f81018790048702830187019095528482529194929390928301828280156105245780601f106104f957610100808354040283529160200191610524565b820191906000526020600020905b81548152906001019060200180831161050757829003601f168201915b50505060028085018054604080516020601f60001961010060018716150201909416959095049283018590048502810185019091528181529596959450909250908301828280156105b65780601f1061058b576101008083540402835291602001916105b6565b820191906000526020600020905b81548152906001019060200180831161059957829003601f168201915b5050505050905083565b6001545b90565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061060857805160ff1916838001178555610635565b82800160010185558215610635579182015b8281111561063557825182559160200191906001019061061a565b50610641929150610645565b5090565b6105c491905b80821115610641576000815560010161064b56fea165627a7a72305820224c083caea0b9b732488ecb73887954ec2c25e97d83e4b9b9e2bcad0da117bf0029";

    public static final String FUNC_STOREMEDICALRECORD = "storeMedicalRecord";

    public static final String FUNC_RETRIEVEMEDICALRECORD = "retrieveMedicalRecord";

    public static final String FUNC_MEDICALRECORDS = "medicalRecords";

    public static final String FUNC_GETNUMBEROFRECORDS = "getNumberOfRecords";

    @Deprecated
    protected MedicalRecordContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected MedicalRecordContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected MedicalRecordContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected MedicalRecordContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> storeMedicalRecord(String _patientPPS, String _encryptedRecord) {
        final Function function = new Function(
                FUNC_STOREMEDICALRECORD, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_patientPPS), 
                new org.web3j.abi.datatypes.Utf8String(_encryptedRecord)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple3<BigInteger, String, String>> retrieveMedicalRecord(BigInteger counter) {
        final Function function = new Function(FUNC_RETRIEVEMEDICALRECORD, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(counter)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple3<BigInteger, String, String>>(
                new Callable<Tuple3<BigInteger, String, String>>() {
                    @Override
                    public Tuple3<BigInteger, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, String, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<Tuple3<BigInteger, String, String>> medicalRecords(BigInteger param0) {
        final Function function = new Function(FUNC_MEDICALRECORDS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple3<BigInteger, String, String>>(
                new Callable<Tuple3<BigInteger, String, String>>() {
                    @Override
                    public Tuple3<BigInteger, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, String, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<BigInteger> getNumberOfRecords() {
        final Function function = new Function(FUNC_GETNUMBEROFRECORDS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static MedicalRecordContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new MedicalRecordContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static MedicalRecordContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MedicalRecordContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static MedicalRecordContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new MedicalRecordContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static MedicalRecordContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new MedicalRecordContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<MedicalRecordContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(MedicalRecordContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<MedicalRecordContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(MedicalRecordContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<MedicalRecordContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(MedicalRecordContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<MedicalRecordContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(MedicalRecordContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
