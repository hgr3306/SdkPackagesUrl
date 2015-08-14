package me.gerry.sdkpackage.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ResourceBundle;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class RepositoryXmlParser {

    private ResourceBundle mBundle = ResourceBundle
                                           .getBundle("me.gerry.sdkpackage.properties.resources_xml");
    private String         mUrl;

    public RepositoryXmlParser() {
        this.mUrl = this.mBundle.getString("Repository");
    }

    /**
     * 解析https://dl.google.com/android/repository/repository-xx.xml文件。
     */
    public void parse() {

        XMLReader xr = null;
        // InputStream file = null;
        try {
            xr = XMLReaderFactory.createXMLReader();
            RepositoryXmlHandler handler = new RepositoryXmlHandler();

            xr.setContentHandler(handler);
            xr.setErrorHandler(handler);

            // file = new FileInputStream(".\\src\\xml\\repository-10.xml");
            // xr.parse(new InputSource(file));

            xr.parse(this.mUrl);
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
