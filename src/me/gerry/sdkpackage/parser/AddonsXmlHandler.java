package me.gerry.sdkpackage.parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import me.gerry.sdkpackage.domain.AndroidPlatformResource;
import me.gerry.sdkpackage.domain.AndroidPlatformResourceEntity;
import me.gerry.sdkpackage.domain.Archive;
import me.gerry.sdkpackage.domain.SdkResource;
import me.gerry.sdkpackage.domain.SdkResourceEntity;
import me.gerry.sdkpackage.domain.XmlElement;
import me.gerry.sdkpackage.domain.addon.SystemImage;
import me.gerry.sdkpackage.domain.repository.SdkPlatform;
import me.gerry.sdkpackage.util.LogPrinter;
import me.gerry.sdkpackage.util.XmlElementPool;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 对https://dl.google.com/android/repository/repository-xx.xml 的解析处理。
 * 
 * @author Gerry
 *
 */
public class AddonsXmlHandler extends DefaultHandler {
    /**
     * 是否开始接受文本元素内容。
     */
    private boolean           mReceiveCharacter;
    /**
     * 操作文本元素内容。
     */
    private StringBuffer      mStrBuf;
    /**
     * 文本内容。
     */
    private String            mCharacter;

    private List<SdkResource> mResources;
    private SdkResource       mResource;
    private XmlElementPool    mElements;
    private Archive           mArchive;

    /**
     * 日志输出。
     */
    private LogPrinter        mLogger;

    public AddonsXmlHandler() {
        super();
        this.mReceiveCharacter = false;
        this.mStrBuf = new StringBuffer();
        this.mCharacter = "";
        this.mElements = new XmlElementPool();
        try {
            this.mLogger = new LogPrinter("src/me/gerry/sdkpackage/logs/addons.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if (this.mReceiveCharacter) {
            if (mStrBuf.length() < 100) {
                mStrBuf.append(ch, start, length);
            } else {
                mStrBuf.append("\nToo Long...\n");
                this.mReceiveCharacter = false;
            }
        }
    }

    @Override
    public void startDocument() throws SAXException {
        this.mLogger.println("开始解析XML文件----->");
        mResources = new ArrayList<SdkResource>();
    }

    @Override
    public void endDocument() throws SAXException {
        this.mLogger.println("<-------解析结束");
        this.mLogger.close();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {

        if (!("".equals(uri))) {

            switch (localName) {

            case XmlElement.SYSTEMIMAGE:
                this.mLogger.println("找到 "
                        + SdkResourceEntity.RESOURCE_TYPE_SYSTEMIMAGE + " :");
                mResource = new SystemImage();
                break;

            case XmlElement.CODENAME:
                this.mReceiveCharacter = true;
                break;

            case XmlElement.API_LEVEL:
                this.mReceiveCharacter = true;
                break;
                
            case XmlElement.ABI:
                this.mReceiveCharacter = true;
                break;

            case XmlElement.REVISION:
                this.mReceiveCharacter = true;
                break;

            case XmlElement.MAJOR:
                mStrBuf.setLength(0);
                break;

            case XmlElement.MINOR:
                this.mReceiveCharacter = true;
                break;

            case XmlElement.MICRO:
                this.mReceiveCharacter = true;
                break;

            case XmlElement.DESCRIPTION:
                this.mReceiveCharacter = true;
                break;

            case XmlElement.OBSOLETE:
                mResource.setObsolete(true);
                break;

            case XmlElement.ARCHIVE:
                mArchive = new Archive();
                break;

            case XmlElement.SIZE:
                this.mReceiveCharacter = true;
                break;

            case XmlElement.CHECKSUM:
                if (XmlElement.ARCHIVE.equals(mElements.previousElement())) {
                    String type = attributes.getValue(attributes.getIndex("type"));
                    mArchive.setChecksumType(type);
                    this.mReceiveCharacter = true;
                }
                break;

            case XmlElement.URL:
                if (XmlElement.ARCHIVE.equals(mElements.previousElement())) {
                    this.mReceiveCharacter = true;
                }
                break;

            case XmlElement.HOST_OS:
                if (XmlElement.ARCHIVE
                        .equals(mElements.previousElement())) {
                    this.mReceiveCharacter = true;
                }
                break;

            }

            mElements.addElement(localName);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        if (!("".equals(uri))) {
            mElements.removeElement();

            switch (localName) {

            case XmlElement.SYSTEMIMAGE:
                this.mLogger.println("    " + mResource.toString());
                mResource.printArchives(this.mLogger);
                mResources.add(mResource);
                break;

            case XmlElement.CODENAME:
                this.mReceiveCharacter = false;
                mCharacter = mStrBuf.toString();
                mStrBuf.setLength(0);

                if (mResource instanceof AndroidPlatformResourceEntity) {
                    AndroidPlatformResource ap = (AndroidPlatformResource) mResource;
                    ap.setCodeName(mCharacter);
                }
                break;

            case XmlElement.API_LEVEL:
                this.mReceiveCharacter = false;
                mCharacter = mStrBuf.toString();
                mStrBuf.setLength(0);

                if (mResource instanceof AndroidPlatformResourceEntity) {
                    AndroidPlatformResource ap = (AndroidPlatformResource) mResource;
                    ap.setApiLevel(mCharacter);
                }
                break;
                
            case XmlElement.ABI:
                this.mReceiveCharacter = false;
                mCharacter = mStrBuf.toString();
                mStrBuf.setLength(0);
                
                if (mResource instanceof SystemImage) {
                    SystemImage ap = (SystemImage) mResource;
                    ap.setAbi(mCharacter);
                }
                break;

            case XmlElement.REVISION:
                this.mReceiveCharacter = false;
                mCharacter = mStrBuf.toString();
                mStrBuf.setLength(0);

                if (!("layoutlib".equals(mElements.previousElement()))) {
                    mResource.setRevision(mCharacter);
                }
                break;

            case XmlElement.MAJOR:
                if (XmlElement.REVISION.equals(mElements
                        .previousElement())) {
                    mStrBuf.append('.');
                }
                this.mReceiveCharacter = false;
                break;

            case XmlElement.MINOR:
                if (XmlElement.REVISION.equals(mElements
                        .previousElement())) {
                    mStrBuf.append('.');
                }
                this.mReceiveCharacter = false;
                break;

            case XmlElement.MICRO:
                this.mReceiveCharacter = false;
                break;

            case XmlElement.DESCRIPTION:
                this.mReceiveCharacter = false;
                mCharacter = mStrBuf.toString();
                mStrBuf.setLength(0);

                if (XmlElement.PLATFORM.equals(mElements
                        .previousElement())) {
                    if (SdkResourceEntity.RESOURCE_TYPE_SDKPLATFORM
                            .equals(mResource.getResourceType())) {
                        SdkPlatform sp = (SdkPlatform) mResource;
                        sp.setDescription(mCharacter);
                    }
                }
                break;

            case XmlElement.ARCHIVE:
                mResource.addArchive(mArchive);
                break;

            case XmlElement.SIZE:
                this.mReceiveCharacter = false;
                mCharacter = mStrBuf.toString();
                mStrBuf.setLength(0);

                if (XmlElement.ARCHIVE
                        .equals(mElements.previousElement())) {
                    mArchive.setSize(Long.parseLong(mCharacter));
                }
                break;

            case XmlElement.CHECKSUM:
                this.mReceiveCharacter = false;
                mCharacter = mStrBuf.toString();
                mStrBuf.setLength(0);

                if (XmlElement.ARCHIVE
                        .equals(mElements.previousElement())) {
                    mArchive.setChecksumValue(mCharacter);
                }
                break;

            case XmlElement.URL:
                this.mReceiveCharacter = false;
                if (-1 == mStrBuf.indexOf("https://")) {
//                    if (-1 == mStrBuf.indexOf("https://dl-ssl.google.com/android/repository/")) {
//                        mStrBuf.insert(0,
//                                "https://dl-ssl.google.com/android/repository/");
//                    }
                    //最新版镜像的下载目录
                    if (-1 == mStrBuf.indexOf("https://dl-ssl.google.com/android/repository/")) {
                        mStrBuf.insert(0,
                                "https://dl-ssl.google.com/android/repository/sys-img/android/");
                    }
                }
                mCharacter = mStrBuf.toString();
                mStrBuf.setLength(0);

                if (XmlElement.ARCHIVE
                        .equals(mElements.previousElement())) {
                    mArchive.setUrl(mCharacter);
                }
                break;

            case XmlElement.HOST_OS:
                this.mReceiveCharacter = false;
                mCharacter = mStrBuf.toString();
                mStrBuf.setLength(0);

                if (XmlElement.ARCHIVE
                        .equals(mElements.previousElement())) {
                    if (0 == "windows".compareToIgnoreCase(mCharacter)) {
                        mArchive.setHostOS(Archive.HOSTOS_WINDOWS);
                    }

                    if (0 == "macosx".compareToIgnoreCase(mCharacter)) {
                        mArchive.setHostOS(Archive.HOSTOS_MACOSX);
                    }

                    if (0 == "linux".compareToIgnoreCase(mCharacter)) {
                        mArchive.setHostOS(Archive.HOSTOS_LINUX);
                    }
                }
                break;

            }
        }
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        this.mLogger.close();
        super.error(e);
    }

}
