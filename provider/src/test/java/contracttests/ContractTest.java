package contracttests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.InputSource;
import production.Provider;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import java.io.StringReader;

import static contracttests.ContractReader.readContract;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ContractTest {

    private Provider provider;

    @Before
     public void setup() throws Exception {
        provider = new Provider();
     }

    @Test
     public void applicationOutputFollowsContract() throws Exception {
        FakeConsumer fakeConsumer = new FakeConsumer();
        String contract = readContract("contract.txt");
        String outputFromApplication = fakeConsumer.getValueFromProvider("http://localhost:8888/provider");

        XPath xPath = XPathFactory.newInstance().newXPath();
        String value = xPath.evaluate(contract,getSource(outputFromApplication));
        assertThat(value,is("B"));
    }

    private InputSource getSource(String xml) {
        return new InputSource(new StringReader(xml));
    }

    @After
    public void tearDown() throws Exception {
        provider.stopServer();

    }
}
