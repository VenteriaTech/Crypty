package cz.venteria.wallet;

import cz.venteria.wallet.enums.CryptoType;
import cz.venteria.wallet.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
public class Wallet {

    private String key; // SHA

    private String publicKey;

    private HashMap<CryptoType, Double> cryptoCurrencies;

    public double getCrypto(CryptoType cryptoType) {
        return cryptoCurrencies.get(cryptoType);
    }

    public Status takeCrypto(CryptoType cryptoType, double amount) {
        double current = getCrypto(cryptoType);

        if ((current - amount) < 0) {
            return Status.DECLINED;
        }

        cryptoCurrencies.replace(cryptoType, (current - amount));
        return Status.SUCCESS;
    }

    public Status giveCrypto(CryptoType cryptoType, double amount) {
        double current = getCrypto(cryptoType);

        if (cryptoType == null) {
            return Status.DECLINED;
        }
        cryptoCurrencies.replace(cryptoType, (current + amount));
        return Status.SUCCESS;
    }


    @SneakyThrows
    public double getPrice(CryptoType type) {
        String url = "https://api.binance.com/api/v3/ticker/price?symbol=ETHUSDT";
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        connection.disconnect();

        String[] jsonResponse = response.toString().split(",");
        return Double.parseDouble(jsonResponse[1].replace("\"price\":\"", "").replace("\"}", ""));
    }



}
