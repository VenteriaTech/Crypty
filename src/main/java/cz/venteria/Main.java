package cz.venteria;

import cz.venteria.wallet.WalletAPI;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Main extends JavaPlugin {

    @Getter
    private static Main instance;

    @Getter
    private WalletAPI walletAPI;

    @Override
    public void onEnable() {
        instance = this;

        walletAPI = new WalletAPI();
    }

    @Override
    public void onDisable() {
        // TODO SHUTDOWN DB
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);

        SecretKey secretKey = keyGenerator.generateKey();

        System.out.println(secretKey);

        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        System.out.println(encodedKey);

        // RSOiXJwwn+tVAibZ+HMDGQ==
        // FEYTGgS8/x6VldDzKP00tg==
        // 0nMX9s+Im59niu3UpCoZhg==

        // tns8Ll2YkS21LW6cg0xMXxXOwjpUZNvphlxP+S4DDwY=
        // MLvHl27nrXH0urhsy+V16Pe9be3BLDdxCHFRyAsvemY=
        // BjoetxYAyHjDJi7NXSmeg+QTomMSxub9uN4ej2DTs5I=
        // oxyD7SgT4ej2DTs5I=

        String loginKey = "BjoetxYAyHjDJi7NXSmeg+QTomMSxub9uN4ej2DTs5I=";
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < loginKey.length(); i += 2) {
            builder.append(loginKey.charAt(i));
        }

        System.out.println(builder.toString());

    }


}