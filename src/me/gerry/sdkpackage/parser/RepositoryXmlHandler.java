package me.gerry.sdkpackage.parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import me.gerry.sdkpackage.domain.AndroidPlatformResource;
import me.gerry.sdkpackage.domain.AndroidPlatformResourceEntity;
import me.gerry.sdkpackage.domain.Archive;
import me.gerry.sdkpackage.domain.SdkResourceEntity;
import me.gerry.sdkpackage.domain.XmlElement;
import me.gerry.sdkpackage.domain.repository.BuildTool;
import me.gerry.sdkpackage.domain.repository.Doc;
import me.gerry.sdkpackage.domain.repository.Ndk;
import me.gerry.sdkpackage.domain.repository.PlatformTool;
import me.gerry.sdkpackage.domain.repository.SdkPlatform;
import me.gerry.sdkpackage.domain.repository.SdkSample;
import me.gerry.sdkpackage.domain.repository.SdkSource;
import me.gerry.sdkpackage.domain.repository.Tool;
import me.gerry.sdkpackage.util.LogPrinter;
import me.gerry.sdkpackage.util.XmlElementPool;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
/**
 * 对https://dl.google.com/android/repository/repository-xx.xml 的解析处理。
 * @author Gerry
 *
 */
public class RepositoryXmlHandler extends DefaultHandler {
    /**
     * 是否开始接受文本元素内容。
     */
    private boolean                 mReceiveCharacter;
    /**
     * 操作文本元素内容。
     */
    private StringBuffer            mStrBuf;
    /**
     * 文本内容。
     */
    private String                  mCharacter;

    private List<SdkResourceEntity> mResources;
    private SdkResourceEntity       mResource;
    private XmlElementPool          mElements;
    private Archive                 mArchive;
    
    /**
     * 日志输出。
     */
    private LogPrinter mLogger;

    public RepositoryXmlHandler() {
        super();
        this.mReceiveCharacter = false;
        this.mStrBuf = new StringBuffer();
        this.mCharacter = "";
        this.mElements = new XmlElementPool();
        try {
            this.mLogger = new LogPrinter("src/me/gerry/sdkpackage/logs/repository.txt");
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
        mResources = new ArrayList<SdkResourceEntity>();
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

            case XmlElement.NDK:
                this.mLogger.println("找到 "
                        + SdkResourceEntity.RESOURCE_TYPE_NDK + " :");
                mResource = new Ndk();
                break;
                
            case XmlElement.PLATFORM:
                this.mLogger.println("找到 "
                        + SdkResourceEntity.RESOURCE_TYPE_SDKPLATFORM + " :");
                mResource = new SdkPlatform();
                break;

            case XmlElement.SAMPLE:
                this.mLogger.println("找到 "
                        + SdkResourceEntity.RESOURCE_TYPE_SDKSAMPLE + " :");
                mResource = new SdkSample();
                break;

            case XmlElement.PLATFORM_TOOL:
                this.mLogger.println("找到 "
                        + SdkResourceEntity.RESOURCE_TYPE_PLATFORMTOOL + " :");
                mResource = new PlatformTool();
                break;

            case XmlElement.BUILD_TOOL:
                this.mLogger.println("找到 "
                        + SdkResourceEntity.RESOURCE_TYPE_BUILDTOOL + " :");
                mResource = new BuildTool();
                break;

            case XmlElement.DOC:
                this.mLogger.println("找到 " + SdkResourceEntity.RESOURCE_TYPE_DOC
                        + " :");
                mResource = new Doc();
                break;

            case XmlElement.TOOL:
                this.mLogger.println("找到 " + SdkResourceEntity.RESOURCE_TYPE_TOOL
                        + " :");
                mResource = new Tool();
                break;

            case XmlElement.SOURCE:
                this.mLogger.println("找到 "
                        + SdkResourceEntity.RESOURCE_TYPE_SDKSOURCE + " :");
                mResource = new SdkSource();
                break;

            case XmlElement.VERSION:
                this.mReceiveCharacter = true;
                break;

            case XmlElement.CODENAME:
                this.mReceiveCharacter = true;
                break;

            case XmlElement.API_LEVEL:
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
                
            case XmlElement.HOST_BITS:
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

            case XmlElement.NDK:
            case XmlElement.PLATFORM:
            case XmlElement.SAMPLE:
            case XmlElement.PLATFORM_TOOL:
            case XmlElement.BUILD_TOOL:
            case XmlElement.DOC:
            case XmlElement.TOOL:
            case XmlElement.SOURCE:
                this.mLogger.println("    " + mResource.toString());
                mResource.printArchives(this.mLogger);
                mResources.add(mResource);
                break;

            case XmlElement.VERSION:
                this.mReceiveCharacter = false;
                mCharacter = mStrBuf.toString();
                mStrBuf.setLength(0);

                if (XmlElement.PLATFORM.equals(mElements
                        .previousElement())) {
                    if (SdkResourceEntity.RESOURCE_TYPE_SDKPLATFORM.equals(mResource
                            .getResourceType())) {
                        SdkPlatform sp = (SdkPlatform) mResource;
                        sp.setVersion(mCharacter);
                    }
                }
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
                if (-1 == mStrBuf.indexOf("https://dl-ssl.google.com/android/repository/")) {
                    mStrBuf.insert(0,
                            "https://dl-ssl.google.com/android/repository/");
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
                
            case XmlElement.HOST_BITS:
                this.mReceiveCharacter = false;
                mCharacter = mStrBuf.toString();
                mStrBuf.setLength(0);
                
                if (XmlElement.ARCHIVE
                        .equals(mElements.previousElement())) {
                        mArchive.setHostBits(mCharacter);
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
