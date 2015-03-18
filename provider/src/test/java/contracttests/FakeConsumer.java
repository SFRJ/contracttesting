package contracttests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.String;import java.lang.StringBuffer;import java.net.HttpURLConnection;
import java.net.URL;

public class FakeConsumer {

    public String getValueFromProvider(String target) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(target).openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader inputStream = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        return readFromStream(inputStream);
    }

    private String readFromStream(BufferedReader stream) throws IOException {
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = stream.readLine()) != null) {
            response.append(inputLine);
        }

        stream.close();
        return response.toString();
    }
}
