package cz.venteria.wallet;

import cz.venteria.wallet.enums.CryptoType;
import cz.venteria.wallet.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Transaction {

    private String privateWalletKey;

    private TransactionStatus status;

    private CryptoType cryptoType;

    private double amount;

}
