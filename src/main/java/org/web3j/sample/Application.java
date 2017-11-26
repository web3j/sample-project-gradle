package org.web3j.sample;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.*;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.sample.contracts.generated.Greeter;
import org.web3j.tuples.generated.*;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import com.locipro.LociBackend;

/**
 * A simple web3j application that demonstrates a number of core features of web3j:
 *
 * <ol>
 *     <li>Connecting to a node on the Ethereum network</li>
 *     <li>Loading an Ethereum wallet file</li>
 *     <li>Sending Ether from one address to another</li>
 *     <li>Deploying a smart contract to the network</li>
 *     <li>Reading a value from the deployed smart contract</li>
 *     <li>Updating a value in the deployed smart contract</li>
 *     <li>Viewing an event logged by the smart contract</li>
 * </ol>
 *
 * <p>To run this demo, you will need to provide:
 *
 * <ol>
 *     <li>Ethereum client (or node) endpoint. The simplest thing to do is
 *     <a href="https://infura.io/register.html">request a free access token from Infura</a></li>
 *     <li>A wallet file. This can be generated using the web3j
 *     <a href="https://docs.web3j.io/command_line.html">command line tools</a></li>
 *     <li>Some Ether. This can be requested from the
 *     <a href="https://www.rinkeby.io/#faucet">Rinkeby Faucet</a></li>
 * </ol>
 *
 * <p>For further background information, refer to the project README.
 */
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        new Application().run();
    }

    private void run() throws Exception {
        String testnet = "ropsten";

        // We start by creating a new web3j instance to connect to remote nodes on the network.
        Web3j web3j = Web3j.build(new HttpService(
                "https://"+testnet+".infura.io/<infura api key>"));
        log.info("Connected to Ethereum client version: "
                + web3j.web3ClientVersion().send().getWeb3ClientVersion());

        // We then need to load our Ethereum wallet file
        // Instructions for wallet file generation at: https://docs.web3j.io/command_line.html#wallet-tools
        // You'll need to ensure your account has ether in order to deploy the contract and send transactions.
        Credentials credentials =
                WalletUtils.loadCredentials(
                        "<wallet password>",
                        "/path/to/wallet-file.json");
        log.info("Credentials loaded");

        LociBackend contract;
        String contractAddress = null; // Once you've run this once, paste the contract address here to interact with it
        if (contractAddress == null) {
            log.info("Deploying LociBackend");
            contract = LociBackend.deploy(
                    web3j, credentials,
                    ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT).send();
            contractAddress = contract.getContractAddress();
            log.info("Smart contract deployed to address " + contractAddress);
            log.info("View contract at https://"+testnet+".etherscan.io/address/" + contractAddress);

        } else {
            contract = LociBackend.load(
                contractAddress,
                web3j,
                credentials,
                ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
        }

        // Change this if you want to add a new claim
        String claimID = "foo";

        Bool exists = contract.claimExist(stringToBytes32(claimID)).send();

        if (exists.getValue()) {
            log.info(claimID + " claim exists. Here is its data....");
            Tuple6<Bytes32,Uint256,Uint256,Uint256,Bytes32,Address> claimData =
                contract.claims(stringToBytes32(claimID)).send();
            log.info("claimCreateDate: " + claimData.getValue2().getValue());
            log.info("disclosureDate: " + claimData.getValue3().getValue());
            log.info("timestamp: " + claimData.getValue4().getValue());
            log.info("userId: " + new String(claimData.getValue5().getValue()));
            log.info("owner: " + claimData.getValue6().getValue());

        } else {
            log.info(claimID + " claim doesn't exist. Claim it!");
            TransactionReceipt transactionReceipt = contract.addNewClaim(
                stringToBytes32(claimID),
                new Uint256(10),
                new Uint256(20),
                stringToBytes32("vbuterin"),
                new Address("0xa8ea1dac284c259d7a345ae91f13c8c9b996094e")).send();

            // Events enable us to log specific events happening during the execution of our smart
            // contract to the blockchain. Index events cannot be logged in their entirety.
            // For Strings and arrays, the hash of values is provided, not the original value.
            // For further information, refer to https://docs.web3j.io/filters.html#filters-and-events
            for (LociBackend.ClaimAddedEventResponse event : contract.getClaimAddedEvents(transactionReceipt)) {
                log.info("ClaimAdded event fired, claimID: " + new String(event.claimID.getValue()));
            }
        }
    }

    public static Bytes32 stringToBytes32(String string) {
        byte[] byteValue = string.getBytes();
        byte[] byteValueLen32 = new byte[32];
        System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.length);
        return new Bytes32(byteValueLen32);
    }
}
