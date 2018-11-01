package org.web3j.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import java.math.BigDecimal;

public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        new Application().run();
    }

    private void run() throws Exception {
        // We start by creating a new web3j instance to connect to remote nodes on the network.
        Web3j web3j = Web3j.build(new HttpService());
        log.info("接続するEthereumクライアントのバージョン: "
                + web3j.web3ClientVersion().send().getWeb3ClientVersion());
        Credentials credentials =
                WalletUtils.loadCredentials(
                        "trustno1$$letmein",
                        "chaindata\\keystore\\UTC--2018--LetTheEtherBeWithU");
        log.info("クレデンシャルをロード");
        log.info("Ether送信中...");
        TransactionReceipt transferReceipt = Transfer.sendFunds(
                web3j, credentials,
                "0xf900f390FEEf974FFBC85000B27C801D3HJX84266",  // you can put any address here
                BigDecimal.valueOf(100), Convert.Unit.ETHER)  // 1 wei = 10^-18 Ether
                .sendAsync().get();
        log.info("トランザクション完了: "
                + transferReceipt.getTransactionHash());
    }
}