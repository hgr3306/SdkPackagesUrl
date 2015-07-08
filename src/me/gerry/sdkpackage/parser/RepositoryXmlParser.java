package me.gerry.sdkpackage.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class RepositoryXmlParser {

    public RepositoryXmlParser() {
    }

    // TODO: 测试使用，要封装成函数
    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(".\\src\\output.txt"));
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        XMLReader xr = null;
        // InputStream file = null;
        try {
            xr = XMLReaderFactory.createXMLReader();
            RepositoryXmlHandler handler = new RepositoryXmlHandler();

            xr.setContentHandler(handler);
            xr.setErrorHandler(handler);

            // file = new FileInputStream(".\\src\\xml\\repository-10.xml");
            // xr.parse(new InputSource(file));

            // TODO: 将地址存在properties文件中
            xr.parse("http://dl.google.com/android/repository/repository-10.xml");
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
