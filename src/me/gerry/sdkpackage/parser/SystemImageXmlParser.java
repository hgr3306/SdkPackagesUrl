package me.gerry.sdkpackage.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ResourceBundle;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class SystemImageXmlParser {

    private ResourceBundle mBundle = ResourceBundle
                                           .getBundle("me.gerry.sdkpackage.properties.resources_xml");
    private String         mUrl;

    public SystemImageXmlParser() {
        this.mUrl = this.mBundle.getString("AndroidSystemImages");
    }

    /**
     * 解析https://dl.google.com/android/repository/sys-img/android/sys-img.xml文件。
     */
    public void parse() {

        XMLReader xr = null;
        // InputStream file = null;
        try {
            xr = XMLReaderFactory.createXMLReader();
            AddonsXmlHandler handler = new AddonsXmlHandler();

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
