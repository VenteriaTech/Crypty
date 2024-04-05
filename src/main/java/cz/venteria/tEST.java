package cz.venteria;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class tEST {


    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
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
        System.out.println(jsonResponse[1].replace("\"price\":\"", "").replace("\"}", ""));

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);

        SecretKey secretKey = keyGenerator.generateKey();

        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < encodedKey.length(); i += 2) {
            builder.append(encodedKey.charAt(i));
        }
        System.out.println(builder.toString());
        /*
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
        */
    }

}
