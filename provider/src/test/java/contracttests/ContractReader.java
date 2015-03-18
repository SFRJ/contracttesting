package contracttests;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ContractReader {

    public static String readContract(String fileName) throws URISyntaxException, IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(fileName);
        Path resPath = Paths.get(url.toURI());
        return new String(java.nio.file.Files.readAllBytes(resPath), "UTF8");
    }
}
