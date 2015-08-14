package me.gerry.sdkpackage.test;

import me.gerry.sdkpackage.parser.SystemImageXmlParser;

import org.junit.Test;

public class AddonsParseTest {

    @Test
    public void testParse() {
        new SystemImageXmlParser().parse();
    }

}
