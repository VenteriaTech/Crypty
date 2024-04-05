package cz.venteria.wallet;

import cz.venteria.Main;
import cz.venteria.wallet.enums.CryptoType;
import cz.venteria.wallet.enums.Status;
import cz.venteria.wallet.enums.TransactionStatus;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.entity.Player;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.HashMap;

public class WalletAPI {

    private Main plugin;

    private HashMap<Player, String> currentlyOpenedWallets;
    @Getter
    private HashMap<String, Wallet> wallets;

    public WalletAPI() {
        //this.plugin = Main;
    }

    public Status createWallet() {
        Wallet wallet = new Wallet(createPrivateKey(), createPublicKey(), new HashMap<>());
        wallets.put(wallet.getKey(), wallet);
        return Status.SUCCESS;
    }

    public Status openWallet(Player player, String code) {
        for (Wallet wallet : wallets.values()) {
            String openKey = privateKeyToOpenKey(wallet.getKey());
            if (openKey.equals(code)) {
                openPlayerWallet(player, wallet.getKey());
                return Status.SUCCESS;
            }
            continue;
        }
        return Status.DECLINED;
    }

    public Status closeWallet(Player player) {
        if (currentlyOpenedWallets.containsKey(player)) {
            currentlyOpenedWallets.remove(player);
            return Status.SUCCESS;
        }
        return Status.DECLINED;
    }


    private void closePlayerWallet(Player player) {
        if (currentlyOpenedWallets.containsKey(player)) {
            currentlyOpenedWallets.remove(player);
            return;
        }
        return;
    }

    private void openPlayerWallet(Player player, String privateKey) {
        if (currentlyOpenedWallets.containsKey(player)) {
            currentlyOpenedWallets.remove(player);
            currentlyOpenedWallets.put(player, privateKey);
            return;
        }
        currentlyOpenedWallets.put(player, privateKey);
        return;
    }


    private String privateKeyToOpenKey(String privateKey) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < privateKey.length(); i += 3) {
            builder.append(privateKey.charAt(i));
        }
        return builder.toString();
    }

    private String publicKeyToSenderKey(String privateKey) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < privateKey.length(); i += 2) {
            builder.append(privateKey.charAt(i));
        }
        return builder.toString();
    }

    public Status sendCrypto(Player player, String publicKey, CryptoType cryptoType, double amount) {
        Wallet senderWallet = getPlayerWallet(player);
        Wallet targetwallet = getWalletByPublicKey(publicKey);

        if (senderWallet == null) {
            return Status.NOT_LOGGED;
        }

        if (targetwallet == null) {
            return Status.DECLINED;
        }

        if (senderWallet.getCrypto(cryptoType) <= amount) {
            return Status.NOT_ENOUGH_RICH;
        }

        senderWallet.takeCrypto(cryptoType, amount);
        targetwallet.giveCrypto(cryptoType, amount);

        //TODO DB TRANSACTION
        createTransaction(senderWallet.getKey(), TransactionStatus.SENT, cryptoType, amount);
        createTransaction(targetwallet.getKey(), TransactionStatus.RECEIVED, cryptoType, amount);

        return Status.SUCCESS;
    }

    public Wallet getWalletByPublicKey(String publicKey) {
        for (Wallet wallet : wallets.values()) {
            if (wallet.getPublicKey().equals(publicKey)) {
                return wallet;
            }
            continue;
        }
        return null;
    }

    public void createTransaction(String privateWalletKey, TransactionStatus transactionStatus, CryptoType cryptoType, double amount) {
        Transaction transaction = new Transaction(privateWalletKey, transactionStatus, cryptoType, amount);
        //TODO DB

    }



    /*---------------------------------------------------------------------------------------------------------------*/

    public Status deleteWallet(String privateKey) {
        Wallet wallet = getWallet(privateKey);
        if (wallet != null) {
            getWallets().remove(privateKey);
            //TODO REMOVE DB
            return Status.SUCCESS;
        }

        return Status.DECLINED;
    }

    public Wallet getWallet(String privateKey) {
        return wallets.get(privateKey);
    }

    public Wallet getPlayerWallet(Player player) {
        return getWallet(currentlyOpenedWallets.get(player));
    }

    @SneakyThrows
    public String createPrivateKey() {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);

        SecretKey secretKey = keyGenerator.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    @SneakyThrows
    public String createPublicKey() {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);

        SecretKey secretKey = keyGenerator.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }
}
