package com.locipro;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.1.1.
 */
public final class LociBackend extends Contract {
    private static final String BINARY = "0x6060604052341561000f57600080fd5b336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506103eb8061005e6000396000f300606060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806339965a751461005c578063ee7d7a8b1461009b578063eff0f59214610100575b600080fd5b341561006757600080fd5b61008160048080356000191690602001909190505061019a565b604051808215151515815260200191505060405180910390f35b34156100a657600080fd5b6100fe6004808035600019169060200190919080359060200190919080359060200190919080356000191690602001909190803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506101cc565b005b341561010b57600080fd5b610125600480803560001916906020019091905050610363565b60405180876000191660001916815260200186815260200185815260200184815260200183600019166000191681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001965050505050505060405180910390f35b600080600102600160008460001916600019168152602001908152602001600020600001546000191614159050919050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561022757600080fd5b6102308561019a565b15151561023c57600080fd5b84600019167fa842dc800f8d342c1af833ce08638680a422d46e9935f9fbbd0dacdd6b06d47c60405160405180910390a260c06040519081016040528086600019168152602001858152602001848152602001428152602001836000191681526020018273ffffffffffffffffffffffffffffffffffffffff16815250600160008760001916600019168152602001908152602001600020600082015181600001906000191690556020820151816001015560408201518160020155606082015181600301556080820151816004019060001916905560a08201518160050160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055509050505050505050565b60016020528060005260406000206000915090508060000154908060010154908060020154908060030154908060040154908060050160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050865600a165627a7a72305820f672a099e6f3735986573032d09565f2cb45e983f6320cda5181b9a90bbbeca70029";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<>();
        _addresses.put("3", "0xa8ea1dac284c259d7a345ae91f13c8c9b996094e");
    }

    private LociBackend(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private LociBackend(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<ClaimAddedEventResponse> getClaimAddedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("ClaimAdded", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}),
                Arrays.<TypeReference<?>>asList());
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<ClaimAddedEventResponse> responses = new ArrayList<ClaimAddedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            ClaimAddedEventResponse typedResponse = new ClaimAddedEventResponse();
            typedResponse.claimID = (Bytes32) eventValues.getIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ClaimAddedEventResponse> claimAddedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("ClaimAdded", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}),
                Arrays.<TypeReference<?>>asList());
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ClaimAddedEventResponse>() {
            @Override
            public ClaimAddedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                ClaimAddedEventResponse typedResponse = new ClaimAddedEventResponse();
                typedResponse.claimID = (Bytes32) eventValues.getIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public RemoteCall<Bool> claimExist(Bytes32 _claimID) {
        Function function = new Function("claimExist", 
                Arrays.<Type>asList(_claimID), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> addNewClaim(Bytes32 _claimID, Uint256 _claimCreateDate, Uint256 _disclosureDate, Bytes32 _userId, Address _claimOwner) {
        Function function = new Function(
                "addNewClaim", 
                Arrays.<Type>asList(_claimID, _claimCreateDate, _disclosureDate, _userId, _claimOwner), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple6<Bytes32, Uint256, Uint256, Uint256, Bytes32, Address>> claims(Bytes32 param0) {
        final Function function = new Function("claims", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        return new RemoteCall<Tuple6<Bytes32, Uint256, Uint256, Uint256, Bytes32, Address>>(
                new Callable<Tuple6<Bytes32, Uint256, Uint256, Uint256, Bytes32, Address>>() {
                    @Override
                    public Tuple6<Bytes32, Uint256, Uint256, Uint256, Bytes32, Address> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple6<Bytes32, Uint256, Uint256, Uint256, Bytes32, Address>(
                                (Bytes32) results.get(0), 
                                (Uint256) results.get(1), 
                                (Uint256) results.get(2), 
                                (Uint256) results.get(3), 
                                (Bytes32) results.get(4), 
                                (Address) results.get(5));
                    }
                });
    }

    public static RemoteCall<LociBackend> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(LociBackend.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<LociBackend> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(LociBackend.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static LociBackend load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new LociBackend(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static LociBackend load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new LociBackend(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class ClaimAddedEventResponse {
        public Bytes32 claimID;
    }
}
