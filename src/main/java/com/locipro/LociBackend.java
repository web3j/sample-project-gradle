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
import org.web3j.tuples.generated.Tuple5;
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
    private static final String BINARY = "0x6060604052341561000f57600080fd5b336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555061030f8061005e6000396000f300606060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063208f59661461005c57806339965a75146100a2578063eff0f592146100e1575b600080fd5b341561006757600080fd5b6100a060048080356000191690602001909190803590602001909190803590602001909190803560001916906020019091905050610148565b005b34156100ad57600080fd5b6100c760048080356000191690602001909190505061027b565b604051808215151515815260200191505060405180910390f35b34156100ec57600080fd5b6101066004808035600019169060200190919050506102ad565b60405180866000191660001916815260200185815260200184815260200183815260200182600019166000191681526020019550505050505060405180910390f35b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156101a357600080fd5b6101ac8461027b565b1515156101b857600080fd5b83600019167fa842dc800f8d342c1af833ce08638680a422d46e9935f9fbbd0dacdd6b06d47c60405160405180910390a260a060405190810160405280856000191681526020018481526020018381526020014281526020018260001916815250600160008660001916600019168152602001908152602001600020600082015181600001906000191690556020820151816001015560408201518160020155606082015181600301556080820151816004019060001916905590505050505050565b600080600102600160008460001916600019168152602001908152602001600020600001546000191614159050919050565b600160205280600052604060002060009150905080600001549080600101549080600201549080600301549080600401549050855600a165627a7a72305820704a229b8ef5494a990832e243e9433608c4ae5dfbf4b14e09bf9c8e65e31dde0029";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<>();
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

    public RemoteCall<TransactionReceipt> addNewClaim(Bytes32 _claimID, Uint256 _claimCreateDate, Uint256 _disclosureDate, Bytes32 _userId) {
        Function function = new Function(
                "addNewClaim", 
                Arrays.<Type>asList(_claimID, _claimCreateDate, _disclosureDate, _userId), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Bool> claimExist(Bytes32 _claimID) {
        Function function = new Function("claimExist", 
                Arrays.<Type>asList(_claimID), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Tuple5<Bytes32, Uint256, Uint256, Uint256, Bytes32>> claims(Bytes32 param0) {
        final Function function = new Function("claims", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}));
        return new RemoteCall<Tuple5<Bytes32, Uint256, Uint256, Uint256, Bytes32>>(
                new Callable<Tuple5<Bytes32, Uint256, Uint256, Uint256, Bytes32>>() {
                    @Override
                    public Tuple5<Bytes32, Uint256, Uint256, Uint256, Bytes32> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple5<Bytes32, Uint256, Uint256, Uint256, Bytes32>(
                                (Bytes32) results.get(0), 
                                (Uint256) results.get(1), 
                                (Uint256) results.get(2), 
                                (Uint256) results.get(3), 
                                (Bytes32) results.get(4));
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
