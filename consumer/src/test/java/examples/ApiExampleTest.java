package examples;

import org.junit.Test;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import java.io.StringReader;

import static examples.ExampleReader.readExample;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class ApiExampleTest {

    @Test
     public void apiExampleGeneratesValidatesToContract() throws Exception {
        XPath xPath = XPathFactory.newInstance().newXPath();
        String value = xPath.evaluate("/output/content/partB", getSource(readExample("apiexample.xml")));
        assertThat(value,is(notNullValue()));
     }

    private InputSource getSource(String xml) {
        return new InputSource(new StringReader(xml));
    }
}
