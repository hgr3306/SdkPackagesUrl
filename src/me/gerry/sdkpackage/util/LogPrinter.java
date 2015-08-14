package me.gerry.sdkpackage.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 * 输出日志信息。
 * @author Gerry
 *
 */
public class LogPrinter extends PrintWriter {

    public LogPrinter(File file, String csn) throws FileNotFoundException,
            UnsupportedEncodingException {
        super(file, csn);
    }

    public LogPrinter(File file) throws FileNotFoundException {
        super(file);
    }

    public LogPrinter(OutputStream out, boolean autoFlush) {
        super(out, autoFlush);
    }

    public LogPrinter(OutputStream out) {
        super(out);
    }

    public LogPrinter(String fileName, String csn) throws FileNotFoundException,
            UnsupportedEncodingException {
        super(fileName, csn);
    }

    public LogPrinter(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public LogPrinter(Writer out, boolean autoFlush) {
        super(out, autoFlush);
    }

    public LogPrinter(Writer out) {
        super(out);
    }
    
}
